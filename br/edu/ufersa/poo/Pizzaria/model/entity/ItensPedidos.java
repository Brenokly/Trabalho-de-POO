package br.edu.ufersa.poo.Pizzaria.model.entity;

import java.util.ArrayList;
import java.util.List;
import Exceptions.*;

public class ItensPedidos extends Produto {
  private Long IdPedido;
  private TiposPizzas pizza;
  private Tamanho tamanho;
  private List<Adicional> adicionais = new ArrayList<>(3);

  public ItensPedidos() {
    super();
    pizza = new TiposPizzas();
  }

  public ItensPedidos(Long id) throws IdInvalido {
    super();
    pizza = new TiposPizzas();
    pizza.setId(id);
  }

  public ItensPedidos(TiposPizzas Tpizza, Tamanho tamanho, Long idProduto, List<Adicional> adicionais, String descricao)
      throws Exception {
    super(descricao, idProduto);
    pizza = Tpizza; // Atribua a instância diretamente, não precisa criar outra

    if (("grande".equals(tamanho.getDescricao()))) {
      super.setValor(Tpizza.getValorGrande());
    } else if (("pequena".equals(tamanho.getDescricao()))) {
      super.setValor(Tpizza.getValorPequena());
    }

    this.adicionais = new ArrayList<>(adicionais);
    setPizza(pizza);
    setTamanho(tamanho);
    calcValor(getValor(), adicionais);
  }

  public ItensPedidos(TiposPizzas Tpizza, Long IdPedido, Tamanho tamanho, Long idProduto, List<Adicional> adicionais,
      String descricao)
      throws Exception {
    super(descricao, idProduto);
    pizza = Tpizza; // Atribua a instância diretamente, não precisa criar outra

    if (("grande".equals(tamanho.getDescricao()))) {
      super.setValor(Tpizza.getValorGrande());
    } else if (("pequena".equals(tamanho.getDescricao()))) {
      super.setValor(Tpizza.getValorPequena());
    }

    this.setAdicionais(adicionais);
    setIdPedido(IdPedido);
    setPizza(pizza);
    setTamanho(tamanho);
    calcValor(getValor(), adicionais);
  }

  public ItensPedidos(TiposPizzas Tpizza, Tamanho tamanho, Long idProduto, List<Adicional> adicionais)
      throws Exception {
    super();
    pizza = Tpizza; // Atribua a instância diretamente, não precisa criar outra

    if (("grande".equals(tamanho.getDescricao()))) {
      super.setValor(Tpizza.getValorGrande());
    } else if (("pequena".equals(tamanho.getDescricao()))) {
      super.setValor(Tpizza.getValorPequena());
    }

    this.setAdicionais(adicionais);
    setPizza(pizza);
    setTamanho(tamanho);
    calcValor(getValor(), adicionais);
  }

  public void setIdPedido(Long idPedido) throws IdInvalido {
    if (idPedido != null) {
      this.IdPedido = idPedido;
    } else {
      throw new IdInvalido("Id inválido");
    }
  }

  public Long getIdPedido() {
    return this.IdPedido;
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
    return super.getDescricao();
  }

  public void setDescricao() throws DescricaoInvalida {
    super.setDescricao("Pizza de " + this.pizza.getNome() + " - " + this.tamanho.getDescricao()
        + " - Adicionais: " + this.adicionais.toString());
  }

  public List<Adicional> getAdicionais() {
    return adicionais;
  }

  public void setAdicionais(List<Adicional> adicionais) {
    if (adicionais != null) {
      this.adicionais = adicionais;
    } else {
      throw new NullPointerException("Adicionais inválidos");
    }
  }

  public void setAdicionais(Adicional adicionais) {
    List<Adicional> adicionaisArray = new ArrayList<>();
    adicionaisArray.add(adicionais);
    this.adicionais = adicionaisArray;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id do pedido: ").append(getIdPedido()).append("\n");
    sb.append("Pizza: ").append(getPizza().getNome()).append("\n");
    sb.append("Id Pizza: ").append(getPizza().getId()).append("\n");
    sb.append("Tamanho: ").append(getTamanho().getDescricao()).append("\n");
    sb.append("Valor: ").append(getValor()).append("\n");
    sb.append("Descrição: ").append(getDescricao()).append("\n");

    sb.append("Adicionais:\n");
    for (Adicional adicional : adicionais) {
      sb.append(" - Nome: ").append(adicional.getNome()).append("\n");
      sb.append("   Valor: ").append(adicional.getValor()).append("\n");
      sb.append("   Quantidade: ").append(adicional.getQuantidade()).append("\n");
    }

    return sb.toString();
  }

  public TiposPizzas getTipoPizza() {
    return pizza;
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

  // metodo tostring que exibe todos os atributos dessa classe formatados:
  public void tostring() {
    System.out.println("Id do pedido: " + getIdPedido() + "\n" + "Pizza: " + getPizza().getNome() + "\n" + "Tamanho: "
        + getTamanho().getDescricao() + "\n" + "\n" + "Valor: " + getValor() + "\n"
        + "Descrição: " + getDescricao() + "\n");
    for (Adicional adicional : adicionais) {
      System.out.println("Adicional: " + adicional.getNome() + "\n" + "Valor: " + adicional.getValor() + "\n");
    }
  }
}
