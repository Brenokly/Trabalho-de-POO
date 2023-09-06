package br.edu.ufersa.poo.classes;

import java.util.ArrayList;

class Pedidos {
    private Clientes cliente;
    private ArrayList<Pizzas> pizza = new ArrayList<>(1);
    private EnumEstado estado;
	private double valor;

    public Pedidos(Clientes cliente, Pizzas pizza, EnumEstado estado) {
		ArrayList<Pizzas> pizzaArray = new ArrayList<>(1);
		pizzaArray.add(pizza);
		setCliente(cliente);
		setPizza(pizzaArray);
		setEstado(estado);
		setValor(calcValorPizza(pizzaArray));
    }
	
	public Pedidos(Clientes cliente, ArrayList<Pizzas> pizza, EnumEstado estado) {
		setCliente(cliente);
		setPizza(pizza);
		setEstado(estado);
		setValor(calcValorPizza(pizza));
    }

    public Clientes getCliente() {return this.cliente;}
	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	
	public ArrayList<Pizzas> getPizza() {return this.pizza;}
	public void setPizza(ArrayList<Pizzas> pizza) {
		if (!pizza.isEmpty()) {
			this.pizza = pizza;
		}
	}
	public void setPizza(Pizzas pizza) {
		this.pizza.add(pizza);
	}
	
	public EnumEstado getEstado() {return this.estado;}
	public void setEstado(EnumEstado estado) {
		if ("pendente".equals(estado.getDescricao()) || "preparando".equals(estado.getDescricao()) || "entregue".equals(estado.getDescricao())) {
			this.estado = estado;
		}
	}

	public double getValor() {return this.valor;}
    public void setValor(double valor) {
        if (valor >= 0.0) {
            this.valor = valor;
        } else {
            System.out.println("Valor inválido");
            this.valor = 0.0;
        }
    }

	private double calcValorPizza(ArrayList<Pizzas> listaPizzas) {
		double valor = 0.0;
		for (Pizzas pizza : listaPizzas) {
			valor += pizza.getValor();
		}
		return valor;
	}
    
}