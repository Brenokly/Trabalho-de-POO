import br.edu.ufersa.poo.Pizzaria.model.entity.*;
import br.edu.ufersa.poo.Pizzaria.dao.PizzaDao;

import java.util.ArrayList;
import java.util.List;

public class Teste {
  public static void main(String[] args) {
    // criar um objeto do tipo TiposPizzas
    TiposPizzas tiposPizzas;
    PizzaDao PizzaDao = new PizzaDao();
    try {
      tiposPizzas = new TiposPizzas(2L,"Frango com catupiry", 45.00, 30.00);

      List<Adicional> adicionais = new ArrayList<>();
      adicionais.add(new Adicional("palmito", 10));

      Tamanho tamanho = Tamanho.GRANDE;

      Pizza pizza = new Pizza(tiposPizzas, tamanho, 1L, adicionais, "Pizza de calabresa com catupiry");

      System.out.println(pizza.getValor() + " " + pizza.getDescricao() + " " + pizza.getPizza().getNome() + " " + pizza.getPizza().getValorGrande() + " " + pizza.getPizza().getValorPequena());

      PizzaDao.inserir(pizza);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}