package br.edu.ufersa.poo.Pizzaria;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.dao.ClienteDao;
import br.edu.ufersa.poo.Pizzaria.dao.ItensPedidosDao;
import br.edu.ufersa.poo.Pizzaria.dao.PedidoDao;
import br.edu.ufersa.poo.Pizzaria.dao.TiposPizzasDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.*;

public class teste {
    public static void main(String[] args)  {
    PedidoDao PedidoDao = new PedidoDao();
    TiposPizzas saborMarguerita = new TiposPizzas();
    ItensPedidos pizza = new ItensPedidos();

    try {
      saborMarguerita.setId(1L);
      saborMarguerita.setNome("Marguerita");
      saborMarguerita.setValorGrande(40.00);
      saborMarguerita.setValorPequena(20.00);

      pizza.setPizza(saborMarguerita);
      pizza.setId(3L);
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    List<Pedido> arrayPedidos = PedidoDao.gerarRelatorio(pizza.getPizza());

    for (Pedido pedido : arrayPedidos) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
      String data = formatter.format(pedido.getData());
      System.out.println("Pedido "+pedido.getId() +" - Cliente: " + pedido.getCliente().getNome() + " - Valor: " + pedido.getValor() + " - Situação: " + pedido.getEstado() + " - Data: " + data);
      System.out.println("Itens:");
      for (ItensPedidos itemPedido : pedido.getItensPedido()) {
        System.out.println(itemPedido.getDescricao());
      }
    }


//   usuario.setCpf("600");
//   UserDao.alterar(usuario);
//   System.out.println(UserDao.buscar(UserDao.listar().get(2)));
//   UserDao.alterar(UserDao.listar().get(0));
    }
}

