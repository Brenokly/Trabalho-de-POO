package br.edu.ufersa.poo.Pizzaria.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Exceptions.*;

public class Pedido {
    private Long id;
    private List<ItensPedidos> itensPedido;
    private Cliente cliente;
    private Estado estado;
    private LocalDate data;
    private double valor;

    public Pedido() {
        LocalDate data = LocalDate.now();
        try {
            setData(data);
        } catch(DataInvalida di) {
            di.printStackTrace();
        }
    }

    public Pedido(List<ItensPedidos> itensPedido, Cliente cliente, Estado estado) throws Exception {
        LocalDate data = LocalDate.now();

        setItensPedido(itensPedido);
        setCliente(cliente);
        setEstado(estado);
        setData(data);
        setValor(calcValor());
    }

    public Pedido(ItensPedidos itemPedido, Cliente cliente, Estado estado) throws Exception {
        List<ItensPedidos> itensPedidosArray = new ArrayList<>();
        itensPedidosArray.add(itemPedido);
        
        LocalDate data = LocalDate.now();

        setItensPedido(itensPedidosArray);
        setCliente(cliente);
        setEstado(estado);
        setData(data);
        setValor(calcValor());
    }

    public Pedido(List<ItensPedidos> itensPedido, Cliente cliente, Estado estado, LocalDate data) throws Exception {
        setItensPedido(itensPedido);
        setCliente(cliente);
        setEstado(estado);
        setData(data);
        setValor(calcValor());
    }

    public Pedido(ItensPedidos itemPedido, Cliente cliente, Estado estado, LocalDate data) throws Exception {
        List<ItensPedidos> itensPedidosArray = new ArrayList<>();
        itensPedidosArray.add(itemPedido);

        setItensPedido(itensPedidosArray);
        setCliente(cliente);
        setEstado(estado);
        setData(data);
        setValor(calcValor());
    }

    public Long getId() { return this.id; }

    public void setId(Long id) throws IdInvalido {
        if (id >= 1) {
            this.id = id;
        } else {
            throw new IdInvalido("ID inválido");
        }
    }
    public LocalDate getData() { return this.data; }

    public void setData(LocalDate data) throws DataInvalida {
        if (data.compareTo(LocalDate.now()) <= 0) {
            this.data = data;
        } else {
            throw new DataInvalida("Data inválida");
        }
    }

    public List<ItensPedidos> getItensPedido(){ return this.itensPedido; }

    public void setItensPedido(ItensPedidos itemPedido) throws PizzaInvalida {
        List<ItensPedidos> itensPedidoArray = new ArrayList<>();
        itensPedidoArray.add(itemPedido);

        if (itensPedidoArray.size() >= 1) {
            this.itensPedido = itensPedidoArray;
        } else {
            throw new PizzaInvalida("O campo Pizzas deve conter ao menos uma pizza");            
        }
    }

    public void setItensPedido(List<ItensPedidos> itemsPedido) throws PizzaInvalida {
        if (itemsPedido.size() >= 1) {
            this.itensPedido = itemsPedido;
        } else {
            throw new PizzaInvalida("O campo Pizzas deve conter ao menos uma pizza");            
        }
    }

    public Cliente getCliente() { return this.cliente; }
    public void setCliente(Cliente cliente) throws ClienteInvalido{
        if (cliente.getId() > 0) {
            this.cliente = cliente;
        } else {
            throw new ClienteInvalido("Cliente inválido");
        }
    }

    public Estado getEstado() { return this.estado; }
    public void setEstado(String estado) throws EstadoInvalido {
        if ("pendente".equals(estado)) {
          this.estado = Estado.PENDENTE;
        } else if ("preparando".equals(estado)) {
          this.estado = Estado.PREPARANDO;
        } else if ("entregue".equals(estado)) {
          this.estado = Estado.ENTREGUE;
        } else {
          throw new EstadoInvalido("Estado inválido");
        }
    }

    public void setEstado(Estado estado) throws EstadoInvalido {
        if ("pendente".equals(estado.getDescricao()) || "preparando".equals(estado.getDescricao()) || "entregue".equals(estado.getDescricao())) {
            this.estado = estado;
        } else {
            throw new EstadoInvalido("Estado inválido");
        }
    }

    public double getValor() { return this.valor; }

    public void setValor() throws ValorInvalido {
        if (valor >= 0.0) {
            this.valor = calcValor();
        } else {
            throw new ValorInvalido("Valor inválido");
        }
    }
    
    public void setValor(double valor) throws ValorInvalido {
        if (valor >= 0.0) {
            this.valor = valor;
        } else {
            throw new ValorInvalido("Valor inválido");
        }
    }

    public double calcValor() {
        double valor = 0.0;
        for (ItensPedidos itemPedido : this.itensPedido) {
            valor += itemPedido.getValor();
        }
        return valor;
    }
}