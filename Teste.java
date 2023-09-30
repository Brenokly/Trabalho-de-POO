import br.edu.ufersa.poo.Pizzaria.model.bo.PedidoBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.*;
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

    for (Pedido p : pedido) {
      p.tostring();
    }
  }
}