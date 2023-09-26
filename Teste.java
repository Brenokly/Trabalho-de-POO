import br.edu.ufersa.poo.Pizzaria.dao.UserDao;
import br.edu.ufersa.poo.Pizzaria.model.bo.UserBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.*;
import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.dao.ItensPedidosDao;

import java.util.ArrayList;
import java.util.List;

public class Teste {
  public static void main(String[] args) {
    UserBO userBO = new UserBO();

    List<Usuario> user = new ArrayList<Usuario>();
    Usuario user2 = new Usuario();
    user2.setNome("cleitin");

    try {
      user = userBO.buscarINE(user2);
    } catch (Exception e) {
      e.printStackTrace();
    }

    for (Usuario usuario : user) {
      System.out.println(usuario.getNome() + " " + usuario.getId() + " " + usuario.getCpf() + " " + usuario.getEmail()
          + " " + usuario.getSenha() + " " + usuario.isAdmin());
    }
  }
}