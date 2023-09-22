package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;

import Exceptions.UsuarioInvalido;
import br.edu.ufersa.poo.Pizzaria.dao.UserDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;

public class UserBO implements BaseBO<Usuario> {

  public Usuario Autenticar(Usuario bo) throws UsuarioInvalido {
    UserDao UserDao = new UserDao();
    Usuario usuarioAutenticado = UserDao.buscarPorEmail(bo);
      if (usuarioAutenticado != null && usuarioAutenticado.getSenha().equals(bo.getSenha())) {
        // Usuário autenticado com sucesso
        return usuarioAutenticado;
      } else {
        throw new UsuarioInvalido("Login ou senha não encontrados");
      }
  }

  @Override
  public void create(Usuario bo) throws Exception {

    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public void update(Usuario bo) throws Exception {

    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void deletar(Usuario bo) throws Exception {

    throw new UnsupportedOperationException("Unimplemented method 'deletar'");
  }

  @Override
  public Usuario buscar(Usuario bo) throws Exception {

    throw new UnsupportedOperationException("Unimplemented method 'buscar'");
  }

  @Override
  public List<Usuario> buscarTodos() throws Exception {

    throw new UnsupportedOperationException("Unimplemented method 'buscar'");
  }

}
