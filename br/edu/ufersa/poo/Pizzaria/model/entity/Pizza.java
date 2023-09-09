package br.edu.ufersa.poo.Pizzaria.model.entity;

import java.util.ArrayList;
import java.util.List;
import Exceptions.*;

public class Pizza extends Produto {
  private TiposPizzas pizza;
  private Tamanho tamanho;
  private List<Adicional> adicionais;

  public Pizza(TiposPizzas pizza, Tamanho tamanho, Long idProduto, List<Adicional> adicionais, String descricao) throws Exception {
    super(descricao, idProduto);

    if (("grande".equals(tamanho.getDescricao()))) {
      super.setValor(pizza.getValorGrande());
    } else if (("pequena".equals(tamanho.getDescricao()))) {
      super.setValor(pizza.getValorPequena());
    }
    
    this.adicionais = new ArrayList<>(adicionais);
    super.setId(idProduto);
    setPizza(pizza);
    setTamanho(tamanho);
  }

  public void addAdicional(Adicional adicional) {
    if (adicional != null) {
      adicionais.add(adicional);
    } else {
      throw new IllegalArgumentException("Adicional inválido");
    }
  }

  public void removeAdicional(Adicional adicional) {
    if (adicional != null) {
      adicionais.remove(adicional);
    } else {
      throw new IllegalArgumentException("Adicional inválido");
    }
  }

  public void setPizza(TiposPizzas pizza) throws PizzaInvalida {
    if (pizza != null) {
      this.pizza = pizza;
    } else {
      throw new PizzaInvalida("Pizza inválida");
    }
  }

  public TiposPizzas getPizza() {
    return pizza;
  }

  public void setTamanho(Tamanho tamanho) throws TamanhoInvalido {
    if ("grande".equals(tamanho.getDescricao()) || "pequena".equals(tamanho.getDescricao())) {
      this.tamanho = tamanho;
    } else {
      throw new TamanhoInvalido("Tamanho inválido");
    }
  }

  public Tamanho getTamanho() {
    return tamanho;
  }

  public String getDescricao() {
    return super.getDescricao() + " | " + tamanho.getDescricao();
  }

  public List<Adicional> getAdicionais() {
    return adicionais;
  }
}
