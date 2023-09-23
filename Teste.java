import br.edu.ufersa.poo.Pizzaria.model.entity.*;
import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.dao.ItensPedidosDao;

import java.util.ArrayList;
import java.util.List;

public class Teste {
  public static void main(String[] args) {
    // criar um objeto do tipo TiposPizzas
    AdicionalDao adicionalDao = new AdicionalDao();
    
    // teste do método list de adicionais do adicionaldao
    List<Adicional> adicionais = adicionalDao.listar();
    for (Adicional adicional : adicionais) {
      System.out.println(adicional.getNome() + " " + adicional.getValor() + " " + adicional.getQuantidade());

    }
  }
}