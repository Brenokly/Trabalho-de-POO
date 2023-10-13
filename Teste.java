import br.edu.ufersa.poo.Pizzaria.dao.PedidoDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.*;
import Exceptions.IdInvalido;

public class Teste {
  public static void main(String[] args) {
    PedidoDao pedidoBO = new PedidoDao();

    Pedido pedido = new Pedido();

    try {
      pedido.setId(2L);
      Pedido pedido2 = pedidoBO.buscar(pedido);
      pedido2.tostring();
      System.out.println(pedido2.getItensPedido().get(0).getAdicionais().size());
    } catch (IdInvalido e) {
      e.printStackTrace();
    }
  }
}