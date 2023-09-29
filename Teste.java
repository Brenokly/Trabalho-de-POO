import br.edu.ufersa.poo.Pizzaria.model.bo.PedidoBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.*;

import Exceptions.IdInvalido;

public class Teste {
  public static void main(String[] args) {
    PedidoBO pedidoBO = new PedidoBO();
    Pedido pedido2 = new Pedido();
    try {
      pedido2.setId(2L);
    } catch (IdInvalido e) {
      e.printStackTrace();
    }

    Pedido pedido = null;

    try {
      pedido = pedidoBO.buscar(pedido2);
    } catch (Exception e) {
      e.printStackTrace();
    }

    for (ItensPedidos item : pedido.getItensPedido()) {
      System.out.println(item.getAdicionais().get(0).getNome() + " " + item.getAdicionais().get(0).getValor() + " " + item.getAdicionais().get(0).getQuantidade() + " " + item.getAdicionais().get(0).getId());
    }
  }
}