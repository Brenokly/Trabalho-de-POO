package br.edu.ufersa.poo.Pizzaria;
import br.edu.ufersa.poo.Pizzaria.dao.UserDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.*;

public class teste {
    public static void main(String[] args) {
    UserDao UserDao = new UserDao();
    Usuario usuario = new Usuario();
    try {
        usuario.setCpf("200");
        usuario.setEmail("teste23");
        usuario.setEndereco("teste23");
        usuario.setNome("teste23");
        usuario.setSenha("teste23");
        usuario.setAdmin(true);

        UserDao.inserir(usuario);

    } catch (Exception e) {
      e.printStackTrace();
    }
  
  for (Usuario u: UserDao.listar()) {
    System.out.println("Usuário " + u.getId() + " " + u.getNome());
  }


//   usuario.setCpf("600");
//   UserDao.alterar(usuario);
//   System.out.println(UserDao.buscar(UserDao.listar().get(2)));
//   UserDao.alterar(UserDao.listar().get(0));
}

  
}

