package br.edu.ufersa.poo.Pizzaria.model.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Exceptions.*;

public class Pedido {
    private Long id;
    private ArrayList<Pizza> pizzas;
    private Cliente cliente;
    private Estado estado;
    private LocalDate data;
    private double valor;

    public Pedido() {
    }

    public Pedido(ArrayList<Pizza> pizzas, Cliente cliente, Estado estado) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        LocalDate data = LocalDate.now();
        formatter.format(data);

        setPizzas(pizzas);
        setCliente(cliente);
        setEstado(estado);
        setData(data);
        setValor(calcValor());
    }

    public Pedido(Pizza pizza, Cliente cliente, Estado estado) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        ArrayList<Pizza> pizzaArray = new ArrayList<>();
        pizzaArray.add(pizza);
        LocalDate data = LocalDate.now();
        formatter.format(data);

        setPizzas(pizzaArray);
        setCliente(cliente);
        setEstado(estado);
        setData(data);
        setValor(calcValor());
    }

    public Pedido(ArrayList<Pizza> pizzas, Cliente cliente, Estado estado, LocalDate data) {
        setPizzas(pizzas);
        setCliente(cliente);
        setEstado(estado);
        setData(data);
        setValor(calcValor());
    }

    public Pedido(Pizza pizza, Cliente cliente, Estado estado, LocalDate data) {
        ArrayList<Pizza> pizzaArray = new ArrayList<>();
        pizzaArray.add(pizza);
        setPizzas(pizzaArray);
        setCliente(cliente);
        setEstado(estado);
        setData(data);
        setValor(calcValor());
    }

    public Long getId() { return this.id; }
    public void setId(Long id) {
        if (id >= 1) {
            this.id = id;
        } else {
            throw new IdInvalido("ID inválido");
        }
    }
    public LocalDate getData() { return this.data; }
    public void setData(LocalDate data) {
        LocalDate now = LocalDate.now();
        if (data.isBefore(now)) {
            this.data = data;
        } else {
            throw new IllegalArgumentException("Data inválida");
        }
    }

    public ArrayList<Pizza> getPizzas(){ return this.pizzas; }
    public void setPizzas(ArrayList<Pizza> pizzas) {
        if (pizzas.size() >= 1) {
            this.pizzas = pizzas;
        } else {
            throw new IllegalArgumentException("Sem pizzas");            
        }
    }

    public Cliente getCliente() { return this.cliente; }
    public void setCliente(Cliente cliente) {
        if (cliente.getId() > 0) {
            this.cliente = cliente;
        } else {
            throw new IllegalArgumentException("Cliente inválido");
        }
    }

    public Estado getEstado() { return this.estado; }
    public void setEstado(Estado estado) {
        if ("pendente".equals(estado.getDescricao()) || "preparando".equals(estado.getDescricao()) || "entregue".equals(estado.getDescricao())) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado inválido");
        }
    }

    public double getValor() { return this.valor; }
    public void setValor(double valor) {
        if (valor >= 0.0) {
            this.valor = valor;
        } else {
            throw new ValorInvalido("Valor inválido");
        }
    }

    public double calcValor() {
        double valor = 0.0;
        for (Pizza pizza : this.pizzas) {
            valor += pizza.getValor();
        }
        return valor;
    }
}