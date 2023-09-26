package br.edu.ufersa.poo.Pizzaria.model.entity;

import Exceptions.IdInvalido;
import Exceptions.NomeInvalido;
import Exceptions.QuantidadeInvalida;
import Exceptions.ValorInvalido;

public class Adicional {
  private Long id;
  private String nome;
  private double valor;
  private int quantidade;

  public Adicional() {
  }

  public Adicional(String nome, int quantidade_pedida) throws Exception {
    setNome(nome);
    setQuantidade(quantidade_pedida);
  }

  public Adicional(String nome, double valor, int quantidade_pedida)
      throws NomeInvalido, QuantidadeInvalida, ValorInvalido {
    setValor(valor);
    setNome(nome);
    setQuantidade(quantidade_pedida);
  }

  public Adicional(Long id, String nome, double valor, int quantidade) throws Exception {
    setNome(nome);
    setValor(valor);
    setQuantidade(quantidade);
  }

  public void setId(Long id) throws IdInvalido {
    if (id != null) {
      this.id = id;
    } else {
      throw new IdInvalido("O id do adicional não pode ser vazio");
    }
  }

  public Long getId() {
    return id;
  }

  public void setNome(String nome) throws NomeInvalido {
    if (nome != null && !nome.isEmpty()) {
      this.nome = nome;
    } else {
      throw new NomeInvalido("O nome do adicional não pode estar vazio.");
    }
  }

  public String getNome() {
    return nome;
  }

  public void setValor(double valor) throws ValorInvalido {
    if (valor > 0.0) {
      this.valor = valor;
    } else {
      throw new ValorInvalido("O valor do adicional deve ser maior que zero.");
    }
  }

  public double getValor() {
    return valor;
  }

  public void setQuantidade(int quantidade) throws QuantidadeInvalida {
    if (quantidade >= 0) {
      this.quantidade = quantidade;
    } else {
      throw new QuantidadeInvalida("A quantidade inválida!");
    }
  }

  public int getQuantidade() {
    return quantidade;
  }
}
