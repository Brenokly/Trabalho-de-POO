package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;

import Exceptions.AdicionaJaExiste;
import Exceptions.NomeInvalido;
import Exceptions.PedidoInvalido;
import Exceptions.QuantidadeInvalida;
import Exceptions.ValorInvalido;
import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.dao.ItensPedidosDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.model.entity.ItensPedidos;

public class AdicionalBO implements BaseBO<Adicional> {
  @Override
  public void create(Adicional adicional) throws Exception {
    AdicionalDao adicionalDao = new AdicionalDao();

    // Verificar se o nome é uma string válida
    if (adicional.getNome() == null || adicional.getNome().isEmpty()) {
      throw new NomeInvalido("Nome inválido");
    }

    // Verificar se o valor é maior que 0.0
    if (adicional.getValor() <= 0.0) {
      throw new ValorInvalido("Valor inválido");
    }

    // Verificar se a quantidade é maior que 0
    if (adicional.getQuantidade() < 0) {
      throw new QuantidadeInvalida("Quantidade inválida");
    }

    // Verificar se um adicional com o mesmo nome já existe
    Adicional existingAdicional = adicionalDao.buscar(adicional);

    if (existingAdicional != null) {
      System.out.println("Adicional já existe");
      System.out.println(
          "Adicional existente: " + existingAdicional.getNome() + " " + existingAdicional.getValor() + " " + existingAdicional.getQuantidade());
      throw new AdicionaJaExiste("Adicional com o mesmo nome já existe.");
    } else {
      System.out.println(
          "Adicional existente: " + adicional.getNome() + " " + adicional.getValor() + " " + adicional.getQuantidade());
      adicionalDao.inserir(adicional);
    }
  }

  @Override
  public void update(Adicional adicional) throws Exception {
    AdicionalDao adicionalDao = new AdicionalDao();
    // Verificar se o nome é uma string válida
    if (adicional.getNome() == null || adicional.getNome().isEmpty()) {
      throw new NomeInvalido("Nome inválido");
    }

    // Verificar se o valor é maior que 0.0
    if (adicional.getValor() <= 0.0) {
      throw new ValorInvalido("Valor inválido");
    }

    // Verificar se a quantidade é maior que 0
    if (adicional.getQuantidade() < 0) {
      throw new QuantidadeInvalida("Quantidade inválida");
    }

    // Verificar se um adicional com o mesmo nome já existe
    Adicional existingAdicional = adicionalDao.buscarGeral(adicional);

    if (existingAdicional == null) {
      throw new Exception("Erro no banco de dados, adicional não encontrado.");
    }

    adicionalDao.alterar(adicional);
  }

  public void updateQuant(Adicional adicional) throws Exception {
    AdicionalDao adicionalDao = new AdicionalDao();
    // Verificar se o nome é uma string válida
    if (adicional.getNome() == null || adicional.getNome().isEmpty()) {
      throw new NomeInvalido("Nome inválido");
    }

    // Verificar se o valor é maior que 0.0
    if (adicional.getValor() <= 0.0) {
      throw new ValorInvalido("Valor inválido");
    }

    // Verificar se a quantidade é maior que 0
    if (adicional.getQuantidade() < 0) {
      throw new QuantidadeInvalida("Quantidade inválida");
    }

    // Verificar se um adicional com o mesmo nome já existe
    Adicional existingAdicional = adicionalDao.buscar(adicional);

    if (existingAdicional == null) {
      throw new Exception("Erro no banco de dados, adicional não encontrado.");
    }

    adicionalDao.alterarQuant(adicional);
  }

  public void deleteAdicionaisPD(ItensPedidos bo, List<Adicional> adicionaisRemovidos) throws Exception {
    if (bo != null) {
      ItensPedidosDao pedidos = new ItensPedidosDao();
      pedidos.buscar(bo);

      if (bo != null) {
        AdicionalDao adicionalDao = new AdicionalDao();
        adicionalDao.deletarAdicionaisPD(adicionaisRemovidos);
      }
    } else {
      throw new PedidoInvalido("Adicional inválido");
    }
  }

  public void createAdicionaisPD(ItensPedidos bo, List<Adicional> adicionaisAdicionados) throws Exception {
    if (bo != null) {
      ItensPedidosDao pedidos = new ItensPedidosDao();
      pedidos.buscar(bo);

      if (bo != null) {
        AdicionalDao adicionalDao = new AdicionalDao();
        adicionalDao.inserirAdicionaisPD(bo, adicionaisAdicionados);
      }
    } else {
      throw new PedidoInvalido("Adicional inválido");
    }
  }

  @Override
  public void deletar(Adicional bo) throws Exception {
    AdicionalDao adicionalDao = new AdicionalDao();

    // Verificar se o nome é uma string válida
    if (bo.getNome() == null || bo.getNome().isEmpty()) {
      throw new NomeInvalido("Nome inválido");
    }

    // Verificar se o valor é maior que 0.0
    if (bo.getValor() <= 0.0) {
      throw new ValorInvalido("Valor inválido");
    }

    // Verificar se a quantidade é maior que 0
    if (bo.getQuantidade() < 0) {
      throw new QuantidadeInvalida("Quantidade inválida");
    }

    Adicional existingAdicional = adicionalDao.buscarGeral(bo);

    if (existingAdicional == null) {
      throw new Exception("Erro no banco de dados, adicional não encontrado.");
    }

    adicionalDao.deletar(bo);
  }

  @Override
  public Adicional buscar(Adicional bo) throws Exception {
    AdicionalDao adicionalDao = new AdicionalDao();

    // Verificar se o nome é uma string válida
    if (bo.getNome() == null || bo.getNome().isEmpty()) {
      throw new NomeInvalido("Nome inválido");
    }

    // Verificar se o valor é maior que 0.0
    if (bo.getValor() <= 0.0) {
      throw new ValorInvalido("Valor inválido");
    }

    // Verificar se a quantidade é maior que 0
    if (bo.getQuantidade() < 0) {
      throw new QuantidadeInvalida("Quantidade inválida");
    }

    Adicional existingAdicional = adicionalDao.buscar(bo);

    if (existingAdicional == null) {
      throw new Exception("Erro no banco de dados, adicional não encontrado.");
    }

    return existingAdicional;
  }

  @Override
  public List<Adicional> buscarTodos() throws Exception {
    AdicionalDao adicionalDao = new AdicionalDao();

    if (adicionalDao.listar().size() <= 0) {
      throw new Exception("Erro no banco de dados, nenhum adicional encontrado.");
    }

    return adicionalDao.listar();
  }

  public List<Adicional> buscarAdicionaisPD(ItensPedidos bo) throws Exception {
    AdicionalDao adicionalDao = new AdicionalDao();

    if (bo != null) {
      List<Adicional> adicional = adicionalDao.buscarAdPedido(bo);
      return adicional;
    } else {
      throw new Exception("Erro no banco de dados, nenhum adicional encontrado.");
    }
  }
}
