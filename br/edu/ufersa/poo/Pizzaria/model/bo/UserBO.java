package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;

import Exceptions.UsuarioInvalido;
import br.edu.ufersa.poo.Pizzaria.dao.UserDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;

public class UserBO implements BaseBO<Usuario> {

  public Boolean Autenticar(Usuario bo) throws UsuarioInvalido {
    UserDao UserDao = new UserDao();
    Usuario usuarioAutenticado = UserDao.buscarPorECpf(bo);
    if (usuarioAutenticado != null && usuarioAutenticado.getSenha().equals(bo.getSenha())) {
      // Usuário autenticado com sucesso
      return usuarioAutenticado.isAdmin();
    } else {
      throw new UsuarioInvalido("Login ou senha não encontrados");
    }
  }

  @Override
  public void create(Usuario bo) throws Exception {
    // Método para criar usuário verificando se já não existe um funcionario no
    // banco de dados com o mesmo nome
    UserDao UserDao = new UserDao();
    Usuario usuario = UserDao.buscarPorECpf(bo);

    if (usuario == null) {
      UserDao.inserir(bo);
    } else {
      throw new UsuarioInvalido("Usuário já cadastrado");
    }
  }

  @Override
  public void update(Usuario bo) throws Exception {
    UserDao UserDao = new UserDao();
    Usuario usuario = UserDao.buscarPorECpf(bo);

    if (usuario != null) {
      UserDao.alterar(bo);
    } else {
      throw new UsuarioInvalido("Usuário não encontrado");
    }
  }

  @Override
  public void deletar(Usuario bo) throws Exception {
    UserDao UserDao = new UserDao();
    Usuario usuarioAutenticado = UserDao.buscarPorECpf(bo);

    if (usuarioAutenticado != null) {
      UserDao.deletar(bo);
    } else {
      throw new UsuarioInvalido("Usuário não encontrado");
    }
  }

  @Override
  public Usuario buscar(Usuario bo) throws Exception {
    UserDao UserDao = new UserDao();
    Usuario usuario = UserDao.buscar(bo);

    if (usuario != null) {
      return usuario;
    } else {
      throw new UsuarioInvalido("Usuário não encontrado");
    }
  }

  public List<Usuario> buscarINE(Usuario bo) throws Exception {
    UserDao UserDao = new UserDao();
    List<Usuario> usuario = UserDao.buscarINE(bo);

    if (usuario != null) {
      return usuario;
    } else {
      throw new UsuarioInvalido("Nenhum usuário encontrado");
    }
  }

  @Override
  public List<Usuario> buscarTodos() throws Exception {
    UserDao UserDao = new UserDao();
    List<Usuario> usuarios = UserDao.listar();

    if (usuarios != null) {
      return usuarios;
    } else {
      throw new UsuarioInvalido("Nenhum usuário encontrado");
    }
  }

}