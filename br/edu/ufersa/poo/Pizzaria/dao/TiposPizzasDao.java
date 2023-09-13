package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;

public class TiposPizzasDao extends BaseDaoImpl<TiposPizzas> {

  public Long inserir(TiposPizzas entity) {
    Connection con = getConnection();
    Long pizzaId = null;

    String insertPizzaSql = "INSERT INTO tb_tipos_pizzas (nome, valor_grande, valor_pequena) VALUES (?, ?, ?)";
    try (PreparedStatement ps = con.prepareStatement(insertPizzaSql, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, entity.getNome());
      ps.setDouble(2, entity.getValorGrande());
      ps.setDouble(3, entity.getValorPequena());
      ps.executeUpdate();

      // Obtenha o ID da Pizza recém-inserida
      try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) {
          pizzaId = rs.getLong(1);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return pizzaId;
  };

  public void deletar(TiposPizzas entity) {
    Connection con = getConnection();
    String sql = "DELETE FROM tb_tipos_pizzas WHERE id = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setLong(1, entity.getId());
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
  };

  public void alterar(TiposPizzas entity) {
    Connection con = getConnection();
    String sql = "UPDATE tb_tipos_pizzas SET nome = ?, valor_grande = ?, valor_pequena = ? WHERE id = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {

      ps.setString(1, entity.getNome());
      ps.setDouble(2, entity.getValorGrande());
      ps.setDouble(3, entity.getValorPequena());
      ps.setLong(4, entity.getId());
      ps.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
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

  public List<TiposPizzas> listar() {
    Connection con = getConnection();
    List<TiposPizzas> resultados = new ArrayList<>(null);

    String sql = "SELECT * FROM tb_tipos_pizzas";

    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        TiposPizzas resultado = new TiposPizzas();
        try {
          resultado.setId(rs.getLong("id"));
          resultado.setNome(rs.getString("nome"));
          resultado.setValorGrande(rs.getDouble("valor_grande"));
          resultado.setValorPequena(rs.getDouble("valor_pequena"));
        } catch (Exception e) {
          e.printStackTrace();
        }
        resultados.add(resultado);
      }
      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return resultados;
  };

}
