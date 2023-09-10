package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;

// Não está pronta, apenas tive que criar o esqueloto para usar o método buscar na classe PizzaDao

public class TiposPizzasDao extends BaseDaoImpl<TiposPizzas> {

  public  Long inserir(TiposPizzas entity){
    return null;
  };

	public  void deletar(TiposPizzas entity) {
  };

	public  void alterar(TiposPizzas entity){
  };

	public TiposPizzas buscar(TiposPizzas entity) {
    Connection con = getConnection();
    TiposPizzas resultado = null;

    String sql = "SELECT * FROM tb_tipos_pizzas as e WHERE e.nome = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entity.getNome());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // Se encontrou um registro, crie um objeto TiposPizzas
            resultado = new TiposPizzas();
            
            try {
              resultado.setId(rs.getLong("id"));
              resultado.setNome(rs.getString("nome"));
              resultado.setValorGrande(rs.getDouble("valor_grande"));
              resultado.setValorPequena(rs.getDouble("valor_pequena"));
            } catch (Exception e) {
              e.printStackTrace();
            }
        }

        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeConnection();
    }

    return resultado;
  }

	public  List<TiposPizzas> listar(){
    return null;
  };

}
