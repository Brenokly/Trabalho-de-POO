package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;

import Exceptions.IdInvalido;
import Exceptions.TiposPizzasInvalido;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import br.edu.ufersa.poo.Pizzaria.dao.TiposPizzasDao;
import java.util.ArrayList;

public class TiposPizzasBO implements BaseBO<TiposPizzas> {

  @Override
  public void create(TiposPizzas bo) throws Exception {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
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
