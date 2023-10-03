package br.edu.ufersa.poo.Pizzaria.model.bo;

import java.util.List;
import java.util.Set;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.dao.ItensPedidosDao;
import br.edu.ufersa.poo.Pizzaria.dao.TiposPizzasDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.model.entity.ItensPedidos;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;

public class ItensPedidosBO implements BaseBO<ItensPedidos> {

  @Override
  public void create(ItensPedidos pizza) throws Exception{
    try {
      TiposPizzasDao tiposPizzasDao = new TiposPizzasDao();
      TiposPizzas tiposPizzasExistente = tiposPizzasDao.buscar(pizza.getPizza());

      if (tiposPizzasExistente == null) {
        throw new TipoPizzaInvalido("Erro ao tentar inserir pizza: tipo de pizza inválido");
      } else {
        pizza.getPizza().setId(tiposPizzasExistente.getId());
      }

      // Verificar se os adicionais existem no banco de dados
      AdicionalDao adicionalDao = new AdicionalDao();
      List<Adicional> adicionais = adicionalDao.buscar(pizza.getAdicionais());

      if (adicionais.size() != pizza.getAdicionais().size()) {
        throw new TipoPizzaInvalido("Adicional inválido");
      }

      // Verificar a validade dos adicionais
      for (Adicional adicional : pizza.getAdicionais()) {
        boolean adicionalEncontrado = false;
        for (Adicional adicionalNoBanco : adicionais) {
          if (adicionalNoBanco.getNome().equals(adicional.getNome())
              && adicionalNoBanco.getQuantidade() >= adicional.getQuantidade()) {
            adicionalEncontrado = true;
            break;
          }
        }
        if (!adicionalEncontrado) {
          throw new TipoPizzaInvalido("Adicional inválido");
        }
      }

      try {
        pizza.calcValor(pizza.getValor(), adicionais);
        ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
        itensPedidosDao.inserir(pizza);

        // Agora, vamos chamar o método para alterar a quantidade de adicionais
        for (Adicional adicional : pizza.getAdicionais()) {
          adicionalDao.alterarQuant(adicional);
        }
      } catch (ValorInvalido e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      // Lidar com exceções de banco de dados aqui, se necessário
      e.printStackTrace();
    }
  }

  public void deleteAdicionais(ItensPedidos bo, Set<Adicional> adicionaisRemovidos) throws Exception {
    if (bo != null){
      ItensPedidosDao pedidos = new ItensPedidosDao();
      pedidos.buscar(bo);

      if (bo != null){
        pedidos.deletarAdicionais(bo, adicionaisRemovidos);
      }
    }
    else{
      throw new PedidoInvalido("Adicional inválido");
    }
  }

  public void createAdicionais(ItensPedidos bo, Set<Adicional> adicionaisAdicionados) throws Exception {
    if (bo != null){
      ItensPedidosDao pedidos = new ItensPedidosDao();
      pedidos.buscar(bo);

      if (bo != null){
        pedidos.inserirAdicionais(bo, adicionaisAdicionados);
      }
    }
    else{
      throw new PedidoInvalido("Adicional inválido");
    }
  }

  @Override
  public void update(ItensPedidos bo) throws Exception {
    ItensPedidosDao itensPedidosDao = new ItensPedidosDao();

    if (bo.getPizza().getId() <= 0) {
      throw new TipoPizzaInvalido("Tipo de pizza inválido");
    }
    if (bo.getValor() <= 0) {
      throw new ValorInvalido("Valor inválido");
    }
    if (bo.getDescricao().isEmpty()) {
      throw new DescricaoInvalida("Descrição inválida");
    }

    itensPedidosDao.alterar(bo);
  }

  @Override
  public void deletar(ItensPedidos bo) throws Exception {

    throw new UnsupportedOperationException("Unimplemented method 'deletar'");
  }

  @Override
  public ItensPedidos buscar(ItensPedidos bo) throws Exception {

    throw new UnsupportedOperationException("Unimplemented method 'buscar'");
  }

  @Override
  public List<ItensPedidos> buscarTodos() throws Exception {

    throw new UnsupportedOperationException("Unimplemented method 'buscarTodos'");
  }
}
