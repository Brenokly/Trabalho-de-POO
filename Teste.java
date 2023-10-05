import br.edu.ufersa.poo.Pizzaria.model.bo.PedidoBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Teste {
  public static void main(String[] args) {
    PedidoBO pedidoBO = new PedidoBO();

    List<Pedido> pedido = null;

    try {
      pedido = pedidoBO.buscarTodos();
    } catch (Exception e) {
      e.printStackTrace();
    }

    List<Pedido> pedido2 = new ArrayList<>();

    for (Pedido p : pedido) {
      if (p.getEstado().getDescricao().equals("pendente")) {
        pedido2.add(p);
      }

    }

    for (Pedido p : pedido2) {
      p.tostring();
    }
    System.out.println("Tamanho de itens pedidos: " + pedido2.get(0).getItensPedido().size());
  }
}