package br.edu.ufersa.poo.Pizzaria.model.entity;

import Exceptions.*;

public class TiposPizzas {
  private Long id;
  private String nome;
  private double valorGrande;
  private double valorPequena;

  public TiposPizzas() {
  }

  public TiposPizzas(TiposPizzas pizza) throws Exception {
    setNome(pizza.getNome());
    setValorGrande(pizza.getValorGrande());
    setValorPequena(pizza.getValorPequena());
  }

  public TiposPizzas(String nome, double valorGrande, double valorPequena) throws Exception {
    setNome(nome);
    setValorGrande(valorGrande);
    setValorPequena(valorPequena);
  }

  public void setId(Long id) throws IdInvalido {
    if (id != null) {
      this.id = id;
    }
    else {
      throw new IdInvalido("Id do sabor da pizza não pode ser vazio");
    }
  }

  public Long getId() {
    return id;
  }

  public void setNome(String nome) throws NomeInvalido {
    if (nome != null && !nome.isEmpty()) {
      this.nome = nome;
    }
    else {
      throw new NomeInvalido("Nome da pizza não pode ser vazio");
    }
  }

  public String getNome() {
    return nome;
  }

  public void setValorGrande(double valorGrande) throws ValorInvalido {
    if (valorGrande > 0) {
      this.valorGrande = valorGrande;
    }
    else {
      throw new ValorInvalido("Valor da pizza grande Inválido");
    }
  }

  public double getValorGrande() {
    return valorGrande;
  }

  public void setValorPequena(double valorPequena) throws ValorInvalido {
    if (valorPequena > 0) {
      this.valorPequena = valorPequena;
    }
    else {
      throw new ValorInvalido("Valor da pizza pequena Inválido");
    }
  }

  public double getValorPequena() {
    return valorPequena;
  }
}
