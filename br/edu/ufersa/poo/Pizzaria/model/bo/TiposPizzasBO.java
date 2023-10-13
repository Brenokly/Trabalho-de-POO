package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;

import Exceptions.IdInvalido;
import Exceptions.NomeInvalido;
import Exceptions.SaborJaExiste;
import Exceptions.TiposPizzasInvalido;
import Exceptions.ValorInvalido;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import br.edu.ufersa.poo.Pizzaria.dao.TiposPizzasDao;
import java.util.ArrayList;

public class TiposPizzasBO implements BaseBO<TiposPizzas> {

  @Override
  public void create(TiposPizzas tiposPizzas) throws Exception {
    TiposPizzasDao tiposPizzaDao = new TiposPizzasDao();

    // Verificar se o nome é uma string válida
    if (tiposPizzas.getNome() == null || tiposPizzas.getNome().isEmpty()) {
      throw new NomeInvalido("Nome inválido");
    }

    // Verificar se o valor é maior que 0.0
    if (tiposPizzas.getValorGrande() <= 0.0 || tiposPizzas.getValorPequena() <= 0.0) {
      throw new ValorInvalido("Valor inválido");
    }

    // Verificar se um adicional com o mesmo nome já existe
    List<TiposPizzas> existingTiposPizzas = tiposPizzaDao.listar();
    for (TiposPizzas tiposPizzas2 : existingTiposPizzas) {
      if (tiposPizzas.getNome().toLowerCase().equals(tiposPizzas2.getNome().toLowerCase())) {
        throw new SaborJaExiste("Sabor com o mesmo nome já existe.");
      }
    } 
      tiposPizzaDao.inserir(tiposPizzas);
  }

  @Override
  public void update(TiposPizzas bo) throws Exception {
    // TODO Auto-generated method stub
    
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void deletar(TiposPizzas bo) throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deletar'");
  }

  @Override
  public TiposPizzas buscar(TiposPizzas bo) throws Exception {
    TiposPizzasDao itensPedidosDao = new TiposPizzasDao();

    if (bo.getId() == null) {
      throw new IdInvalido("Id não pode ser nulo");
    }

    TiposPizzas existing = itensPedidosDao.buscar(bo);

    if (existing == null) {
      throw new TiposPizzasInvalido("Tipo de pizza não encontrado");
    }

    return existing;
  }

  @Override
  public List<TiposPizzas> buscarTodos() throws Exception {
    TiposPizzasDao tiposPizzasDao = new TiposPizzasDao();
    List<TiposPizzas> arrayExisting = new ArrayList<TiposPizzas>();

    arrayExisting = tiposPizzasDao.listar();

    if (arrayExisting.size() <= 0) {
      System.out.println("Nenhum sabor encontrado.");
      return null;
    }
    return arrayExisting;
  }

}
