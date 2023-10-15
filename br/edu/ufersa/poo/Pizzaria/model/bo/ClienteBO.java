package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;

import Exceptions.UsuarioInvalido;
import br.edu.ufersa.poo.Pizzaria.dao.ClienteDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;

public class ClienteBO implements BaseBO<Cliente> {

  @Override
  public void create(Cliente bo) throws Exception {
    // Método para criar usuário verificando se já não existe um funcionario no
    // banco de dados com o mesmo nome
    ClienteDao ClienteDao = new ClienteDao();
    Cliente cliente = ClienteDao.buscarPorCpf(bo);
    
    if (bo.getId() == null || bo.getId() < 1) {
      throw new UsuarioInvalido("ID inválido");
    }

    if (cliente == null) {
      ClienteDao.inserir(bo);
    } else {
      throw new UsuarioInvalido("Cliente já cadastrado");
    }
  }

  @Override
  public void update(Cliente bo) throws Exception {
    ClienteDao clienteDao = new ClienteDao();
    Cliente clienteID = clienteDao.buscar(bo);
    Cliente cliente = clienteDao.buscarPorCpf(bo);

    if (clienteID != null) {
      if (cliente == null) {
        clienteDao.alterar(bo);
      }
      else {
        throw new UsuarioInvalido("Cliente já cadastrado");
      }
    } else {
      throw new UsuarioInvalido("Cliente não encontrado");
    }
  }

  @Override
  public void deletar(Cliente bo) throws Exception {
    ClienteDao clienteDao = new ClienteDao();
    Cliente usuarioAutenticado = clienteDao.buscar(bo);

    if (usuarioAutenticado != null) {
      clienteDao.deletar(bo);
    } else {
      throw new UsuarioInvalido("Usuário não encontrado");
    }
  }

  @Override
  public Cliente buscar(Cliente bo) throws Exception {
    ClienteDao clienteDao = new ClienteDao();
    Cliente cliente = clienteDao.buscar(bo);

    if (cliente != null) {
      return cliente;
    } else {
      throw new UsuarioInvalido("Cliente não encontrado");
    }
  }

  @Override
  public List<Cliente> buscarTodos() throws Exception {
    ClienteDao clienteDao = new ClienteDao();
    List<Cliente> clientes = clienteDao.listar();

    if (clientes != null) {
      return clientes;
    } else {
      throw new UsuarioInvalido("Nenhum cliente encontrado");
    }
  }

}