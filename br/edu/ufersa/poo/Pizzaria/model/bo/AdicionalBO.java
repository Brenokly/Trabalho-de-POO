package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;

import Exceptions.AdicionaJaExiste;
import Exceptions.NomeInvalido;
import Exceptions.QuantidadeInvalida;
import Exceptions.ValorInvalido;
import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;

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
      throw new AdicionaJaExiste("Adicional com o mesmo nome já existe.");
    }

    // Agora você pode chamar o método de inserção do AdicionalDao
    adicionalDao.inserir(adicional);
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

    System.out.println("Cleitin" + adicional.getNome()  + " " + adicional.getValor() + " " + adicional.getQuantidade() + " " + adicional.getId());
    System.out.println("afons" + existingAdicional.getNome()  + " " + existingAdicional.getValor() + " " + existingAdicional.getQuantidade() + " " + existingAdicional.getId());

    adicionalDao.alterar(adicional);
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Adicional> buscarTodos() throws Exception {
    AdicionalDao adicionalDao = new AdicionalDao();

    if (adicionalDao.listar().size() <= 0) {
      throw new Exception("Erro no banco de dados, nenhum adicional encontrado.");
    }

    return adicionalDao.listar();
  }
}
