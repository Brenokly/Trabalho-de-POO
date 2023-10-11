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

        AdicionalBO AdicionaisPD = new AdicionalBO();
        ItensPedidosBO itensPedidosBO = new ItensPedidosBO();
        List<Adicional> adicionaisAtuais = null;
        List<Adicional> adicionaisAntigos = null;
        int TamanhoPedidoAtual = pedido.getItensPedido().size();
        for (int i = 0; i < TamanhoPedidoAtual; i++) {
            if (pedido.getItensPedido().get(i).getAdicionais() != null) {
                adicionaisAtuais = new ArrayList<>(pedido.getItensPedido().get(i).getAdicionais());
            }
            if (pedidoAntigo.getItensPedido().get(i).getAdicionais() != null) {
                adicionaisAntigos = new ArrayList<>(pedidoAntigo.getItensPedido().get(i).getAdicionais());
            }

            for (Adicional adicional : pedido.getItensPedido().get(i).getAdicionais()) {
                for (Adicional adicional2 : pedidoAntigo.getItensPedido().get(i).getAdicionais()) {
                    if (adicional.getNome().equals(adicional2.getNome()) && adicional.getQuantidade() == adicional2.getQuantidade()) {
                        // Remove o adicional com nome igual do conjunto atual
                        adicionaisAtuais.remove(adicional);
                        // Remove o adicional com nome igual do conjunto antigo
                        adicionaisAntigos.remove(adicional2);
                        break;
                    }
                }
            }

            if (adicionaisAtuais.size() != adicionaisAntigos.size()) {
                // Algo foi alterado nos adicionais do item de pedido
                List<Adicional> adicionaisRemovidos = new ArrayList<>(adicionaisAntigos);
                List<Adicional> adicionaisAdicionados = new ArrayList<>(adicionaisAtuais);

                if (!adicionaisRemovidos.isEmpty()) {
                    AdicionaisPD.deleteAdicionaisPD(pedidoAntigo.getItensPedido().get(i), adicionaisRemovidos);
                    for (Adicional adicional : adicionaisRemovidos) {
                        Adicional adicionalAtual = AdicionaisPD.buscar(adicional);
                        adicionalAtual.setQuantidade(adicionalAtual.getQuantidade() + adicional.getQuantidade());
                        AdicionaisPD.updateQuant(adicionalAtual);
                    }
                }

                if (!adicionaisAdicionados.isEmpty()) {
                    AdicionaisPD.createAdicionaisPD(pedido.getItensPedido().get(i), adicionaisAdicionados);
                    for (Adicional adicional : adicionaisAdicionados) {
                        Adicional adicionalAtual = AdicionaisPD.buscar(adicional);
                        adicionalAtual.setQuantidade(adicionalAtual.getQuantidade() - adicional.getQuantidade());
                        AdicionaisPD.updateQuant(adicionalAtual);
                    }
                }
            } else {
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
    public List<Pedido> buscarTodos() {
        PedidoDao PedidoDao = new PedidoDao();
        List<Pedido> arrayExistingPedidos = null;

        arrayExistingPedidos = PedidoDao.listar();

        if (arrayExistingPedidos.size() <= 0) {
            System.out.println("Nenhum pedido pendente encontrado.");
            return null;
        } 
        return arrayExistingPedidos;
    }

}