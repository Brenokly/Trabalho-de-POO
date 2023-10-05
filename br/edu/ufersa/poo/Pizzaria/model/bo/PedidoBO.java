package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.dao.PedidoDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.model.entity.Pedido;

public class PedidoBO implements BaseBO<Pedido> {

    @Override
    public void create(Pedido pedido) throws Exception {
        PedidoDao PedidoDao = new PedidoDao();

        if (pedido.getCliente().getId() <= 0) {
            throw new ClienteInvalido("Cliente Inválido");
        }
        if (pedido.getEstado() == null
                || !Arrays.asList("pendente", "preparando", "entregue").contains(pedido.getEstado().getDescricao())) {
            throw new EstadoInvalido("Estado Inválido");
        }
        if (pedido.getData() == null || pedido.getData().compareTo(LocalDate.now()) > 0) {
            throw new DataInvalida("Data Inválida");
        }
        if (pedido.getValor() <= 0) {
            throw new ValorInvalido("Valor Inválido");
        }
        if (pedido.getItensPedido().size() <= 0) {
            throw new PizzaInvalida("Pedido Inválido");
        }

        PedidoDao.inserir(pedido);
    }

    @Override
    public void update(Pedido pedido) throws Exception {
        PedidoDao PedidoDao = new PedidoDao();

        if (pedido.getCliente().getId() <= 0) {
            throw new ClienteInvalido("Cliente Inválido");
        }
        if (pedido.getEstado() == null
                || !Arrays.asList("pendente", "preparando", "entregue").contains(pedido.getEstado().getDescricao())) {
            throw new EstadoInvalido("Estado Inválido");
        }
        if (pedido.getData() == null || pedido.getData().compareTo(LocalDate.now()) > 0) {
            throw new DataInvalida("Data Inválida");
        }
        if (pedido.getValor() <= 0) {
            throw new ValorInvalido("Valor Inválido");
        }
        if (pedido.getItensPedido().size() <= 0) {
            throw new PizzaInvalida("Pedido Inválido");
        }

        Pedido existingPedido = new Pedido(pedido);

        Pedido pedidoAntigo = PedidoDao.buscar(existingPedido);

        if (pedidoAntigo == null) {
            throw new Exception("Erro no banco de dados, pedido não encontrado.");
        }

        AdicionalBO AdicionaisPD = new AdicionalBO();
        ItensPedidosBO itensPedidosBO = new ItensPedidosBO();
        List<Adicional> adicionaisAtuais = null;
        List<Adicional> adicionaisAntigos = null;
        for (int i = 0; i < pedido.getItensPedido().size(); i++) {
            if (pedido.getItensPedido().get(i).getAdicionais() != null) {
                adicionaisAtuais = new ArrayList<>(pedido.getItensPedido().get(i).getAdicionais());
                System.out.println("Atuais\n---------------------------------------------");
                for (Adicional adicional : adicionaisAtuais) {
                    System.out.println(adicional.getNome());
                }
            }
            if (pedidoAntigo.getItensPedido().get(i).getAdicionais() != null) {
                adicionaisAntigos = new ArrayList<>(pedidoAntigo.getItensPedido().get(i).getAdicionais());
                System.out.println(
                        "Adicionais Antigos\n---------------------------------------------");
                for (Adicional adicional : adicionaisAntigos) {
                    System.out.println(adicional.getNome());
                }
            }

            for (Adicional adicionalAtual : adicionaisAtuais) {
                for (Adicional adicionalAntigo : adicionaisAntigos) {
                    if (adicionalAtual.getNome().equals(adicionalAntigo.getNome())) {
                        // Remove o adicional com nome igual do conjunto atual
                        adicionaisAtuais.remove(adicionalAtual);
                        // Remove o adicional com nome igual do conjunto antigo
                        adicionaisAntigos.remove(adicionalAntigo);
                        break; // Sai do loop interno após encontrar uma correspondência
                    }
                }
            }

            if (adicionaisAtuais.size() != adicionaisAntigos.size()) {
                // Algo foi alterado nos adicionais do item de pedido
                List<Adicional> adicionaisRemovidos = new ArrayList<>(adicionaisAntigos);
                List<Adicional> adicionaisAdicionados = new ArrayList<>(adicionaisAtuais);

                if (!adicionaisRemovidos.isEmpty()) {
                    System.out.println("Removendo adicionais: " + adicionaisRemovidos);
                    AdicionaisPD.deleteAdicionaisPD(pedidoAntigo.getItensPedido().get(i),
                            adicionaisRemovidos);
                }

                if (!adicionaisAdicionados.isEmpty()) {
                    System.out.println("Adicionando adicionais: " + adicionaisAdicionados);
                    AdicionaisPD.createAdicionaisPD(pedido.getItensPedido().get(i),
                            adicionaisAdicionados);
                }
            } else {
                System.out.println("Atualizando item");
                itensPedidosBO.update(pedido.getItensPedido().get(i));
            }
        }

        PedidoDao.alterar(pedido);
    }

    @Override
    public void deletar(Pedido pedido) throws Exception {
        PedidoDao PedidoDao = new PedidoDao();

        if (pedido.getCliente().getId() <= 0) {
            throw new ClienteInvalido("Cliente Inválido");
        }
        if (pedido.getEstado() == null
                || !Arrays.asList("pendente", "preparando", "entregue").contains(pedido.getEstado().getDescricao())) {
            throw new EstadoInvalido("Estado Inválido");
        }
        if (pedido.getData() == null || pedido.getData().compareTo(LocalDate.now()) > 0) {
            throw new DataInvalida("Data Inválida");
        }
        if (pedido.getValor() <= 0) {
            throw new ValorInvalido("Valor Inválido");
        }
        if (pedido.getItensPedido().size() <= 0) {
            throw new PizzaInvalida("Pedido Inválido");
        }

        Pedido existingPedido = PedidoDao.buscar(pedido);

        if (existingPedido == null) {
            throw new Exception("Erro no banco de dados, pedido não encontrado.");
        }

        PedidoDao.deletar(pedido);
    }

    @Override
    public Pedido buscar(Pedido pedido) throws Exception {
        PedidoDao PedidoDao = new PedidoDao();

        if (pedido.getCliente().getId() <= 0) {
            throw new ClienteInvalido("Cliente Inválido");
        }
        if (pedido.getEstado() == null
                || !Arrays.asList("pendente", "preparando", "entregue").contains(pedido.getEstado().getDescricao())) {
            throw new EstadoInvalido("Estado Inválido");
        }
        if (pedido.getData() == null || pedido.getData().compareTo(LocalDate.now()) > 0) {
            throw new DataInvalida("Data Inválida");
        }
        if (pedido.getValor() <= 0) {
            throw new ValorInvalido("Valor Inválido");
        }
        if (pedido.getItensPedido().size() <= 0) {
            throw new PizzaInvalida("Pedido Inválido");
        }

        Pedido existingPedido = PedidoDao.buscar(pedido);

        if (existingPedido == null) {
            throw new Exception("Erro no banco de dados, pedido não encontrado.");
        }

        return existingPedido;
    }

    @Override
    public List<Pedido> buscarTodos() throws Exception {
        PedidoDao PedidoDao = new PedidoDao();
        List<Pedido> arrayExistingPedidos = new ArrayList<>();

        arrayExistingPedidos = PedidoDao.listar();

        if (arrayExistingPedidos.size() <= 0) {
            throw new Exception("Erro no banco de dados, nenhum pedido encontrado.");
        }

        return arrayExistingPedidos;
    }

}