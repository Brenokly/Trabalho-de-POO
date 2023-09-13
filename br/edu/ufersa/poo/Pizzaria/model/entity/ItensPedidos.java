package br.edu.ufersa.poo.Pizzaria.model.entity;

import java.util.ArrayList;
import java.util.List;
import Exceptions.*;

public class ItensPedidos extends Produto {
  private TiposPizzas pizza;
  private Tamanho tamanho;
  private List<Adicional> adicionais;

  public ItensPedidos() {
    super();
    pizza = new TiposPizzas();
    this.adicionais = new ArrayList<>();
  }

  public ItensPedidos(Long id) throws IdInvalido {
    super();
    pizza = new TiposPizzas();
    pizza.setId(id);
    this.adicionais = new ArrayList<>();
  }

  public ItensPedidos(TiposPizzas Tpizza, Tamanho tamanho, Long idProduto, List<Adicional> adicionais, String descricao)
      throws Exception {
    super(descricao, idProduto);
    pizza = new TiposPizzas();

    if (("grande".equals(tamanho.getDescricao()))) {
      super.setValor(Tpizza.getValorGrande());
    } else if (("pequena".equals(tamanho.getDescricao()))) {
      super.setValor(Tpizza.getValorPequena());
    }

    this.pizza.setNome(Tpizza.getNome());
    this.pizza.setId(Tpizza.getId());
    this.adicionais = new ArrayList<>(adicionais);
    setPizza(pizza);
    setTamanho(tamanho);
    calcValor(getValor(), adicionais);
  }

  public void setPizza(TiposPizzas pizza) throws PizzaInvalida {
    if (pizza != null) {
      this.pizza = pizza;
    } else {
      throw new PizzaInvalida("Pizza inválida");
    }
  }

  public TiposPizzas getPizza() {
    return this.pizza;
  }

  public void setTamanho(String tamanho) throws TamanhoInvalido {
    if ("grande".equals(tamanho)) {
      this.tamanho = Tamanho.GRANDE;
    } else if ("pequena".equals(tamanho)) {
      this.tamanho = Tamanho.PEQUENA;
    } else {
      throw new TamanhoInvalido("Tamanho inválido");
    }
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

  public void setAdicionais(List<Adicional> adicionais) {
    this.adicionais = adicionais;
  }

  public void calcValor(double valor, List<Adicional> adicionais) throws ValorInvalido {
    if (valor >= 0.0) {
      for (Adicional adicional : adicionais) {
        valor += adicional.getValor();
      }
      super.setValor(valor);
    } else {
      throw new ValorInvalido("Valor inválido");
    }
  }
}
