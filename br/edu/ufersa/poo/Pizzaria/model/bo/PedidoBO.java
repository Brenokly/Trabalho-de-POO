package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.dao.PedidoDao;
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