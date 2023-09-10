package br.edu.ufersa.poo.Pizzaria.model.entity;

import Exceptions.*;

public class Produto {
  private Long id;
  private double valor;
  private String descricao;

  public Produto() {}

  public Produto(String descricao, double valor) throws Exception {
    setDescricao(descricao);
    setValor(valor);
  }

  public Produto(String descricao, Long id) throws Exception {
    setDescricao(descricao);
    setId(id);
  }

  public void setId(Long id) throws IdInvalido {
    if (id != null) {
      this.id = id;
    }
    else {
      throw new IdInvalido("Id inválido");
    }
  }

  public Long getId() {
    return id;
  }

  public void setValor(double valor) throws ValorInvalido {
    if (valor > 0) {
      this.valor = valor;
    }
    else {
      throw new ValorInvalido("Valor inválido");
    }
  }

  public double getValor() {
    return valor;
  }

  public void setDescricao(String descricao) throws DescricaoInvalida {
    if (descricao != null && !descricao.isEmpty()) {
      this.descricao = descricao;
    }
    else {
      throw new DescricaoInvalida("Descrição inválida");
    }
  }

  public String getDescricao() {
    return descricao;
  }
}
