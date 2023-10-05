package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.model.entity.ItensPedidos;

import java.sql.Statement;

public class AdicionalDao extends BaseDaoImpl<Adicional> {
  public Long inserir(Adicional adicional) {
    Long adicionalId = null;
    String sql = "INSERT INTO tb_adicional (nome, valor, quantidade) VALUES (?, ?, ?)";

    try (Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, adicional.getNome());
      ps.setDouble(2, adicional.getValor());
      ps.setInt(3, adicional.getQuantidade());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) {
        adicionalId = rs.getLong("id");
        try {
          adicional.setId(adicionalId);
        } catch (IdInvalido e) {
          e.printStackTrace();
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
    return adicionalId;
  }

  public void deletar(Adicional entity) {
    Connection con = getConnection();
    String sql = "delete from tb_adicional where id = ?";

    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setLong(1, entity.getId());
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
  }

  public void alterar(Adicional entity) {
    Connection con = getConnection();

    String sql = "update tb_adicional set nome = ?, valor = ?, quantidade = ? where id = ?";

    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, entity.getNome());
      ps.setDouble(2, entity.getValor());
      ps.setInt(3, entity.getQuantidade());
      ps.setLong(4, entity.getId());

      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
  }

  public void inserirAdicionaisPD(ItensPedidos entity, List<Adicional> adicionais) {
    Connection con = getConnection();
    String sql = "INSERT INTO tb_pizza_adicional (id_pizza, quantidade, id_adicional) VALUES (?, ?, ?)";

    try {
      PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      for (Adicional adicional : adicionais) {
        ps.setLong(1, entity.getId());
        ps.setInt(2, adicional.getQuantidade());
        ps.setLong(3, adicional.getId());
        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
          if (rs.next()) {
            for (Adicional adicional2 : entity.getAdicionais()) {
              if (adicional2.getIdPizzaAdicional() == null) {
                adicional2.setIdPizzaAdicional(rs.getLong("id"));
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deletarAdicionaisPD(List<Adicional> adicionais) {
    Connection con = getConnection();
    String sql = "DELETE FROM tb_pizza_adicional WHERE id = ?";

    try {
      PreparedStatement ps = con.prepareStatement(sql);
      for (Adicional adicional : adicionais) {
        ps.setLong(1, adicional.getIdPizzaAdicional());
        ps.executeUpdate();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void alterar(ItensPedidos entity) {
    Connection con = getConnection();

    String sql = "UPDATE tb_pizza_adicional SET id_adicional = ?, quantidade = ? WHERE id_pizza = ?";

    try {
      PreparedStatement ps = con.prepareStatement(sql);

      List<Adicional> adicionais = entity.getAdicionais();

      for (int i = 0; i < adicionais.size(); i++) {
        Adicional adicional = adicionais.get(i);
        ps.setLong(1, adicional.getId());
        ps.setInt(2, adicional.getQuantidade());
        ps.setLong(3, entity.getId());
        ps.executeUpdate();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
  }

  public void alterarQuant(Adicional entity) {
    Connection con = getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      String sql = "SELECT quantidade FROM tb_adicional WHERE id = ?";
      ps = con.prepareStatement(sql);
      ps.setLong(1, entity.getId());
      rs = ps.executeQuery();

      if (rs.next()) {
        int quantidadeAtual = rs.getInt("quantidade");
        int novaQuantidade = quantidadeAtual - entity.getQuantidade();

        sql = "UPDATE tb_adicional SET quantidade = ? WHERE id = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, novaQuantidade);
        ps.setLong(2, entity.getId());
        ps.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
  }

  public Adicional buscarGeral(Adicional entity) {
    String sql = "SELECT * FROM tb_adicional WHERE ";
    List<String> conditions = new ArrayList<>();
    List<Object> parameters = new ArrayList<>();

    if (entity.getId() != null) {
      conditions.add("id = ?");
      parameters.add(entity.getId());
    }
    if (entity.getNome() != null && !entity.getNome().isEmpty()) {
      conditions.add("nome = ?");
      parameters.add(entity.getNome());
    }

    if (conditions.isEmpty()) {
      // Se nenhum campo válido foi fornecido, não execute a consulta.
      return null; // Retorna null, já que nenhum registro válido foi encontrado.
    }

    sql += String.join(" OR ", conditions);
    Adicional adicional = null;

    try (Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
      for (int i = 0; i < parameters.size(); i++) {
        ps.setObject(i + 1, parameters.get(i));
      }

      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        adicional = new Adicional();
        adicional.setId(rs.getLong("id"));
        adicional.setNome(rs.getString("nome"));
        adicional.setValor(rs.getDouble("valor"));
        adicional.setQuantidade(rs.getInt("quantidade"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }

    return adicional;
  }

  public Adicional buscar(Adicional entity) {
    Connection con = getConnection();

    String sql = "SELECT * FROM tb_adicional where id=?";

    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setLong(1, entity.getId());
      ResultSet rs = ps.executeQuery();
      if (rs.next())
        return entity;

    } catch (Exception e) {

    } finally {
      closeConnection();
    }
    return null;
  }

  public Adicional buscarAdPedido(ItensPedidos pizza) {
    Connection con = getConnection();
    Adicional entity = new Adicional();

    String sql = "SELECT * FROM tb_pizza_adicional where id_pizza = ?";

    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setLong(1, pizza.getId());
      ResultSet rs = ps.executeQuery();
      if (rs.next())
        entity.setId(rs.getLong("id_adicional"));
      entity.setQuantidade(rs.getInt("quantidade"));

      sql = "SELECT * FROM tb_adicional where id = ?";
      ps = con.prepareStatement(sql);
      ps.setLong(1, entity.getId());
      rs = ps.executeQuery();
      if (rs.next()) {
        entity.setNome(rs.getString("nome"));
        entity.setValor(rs.getDouble("valor"));
      }

      return entity;
    } catch (Exception e) {

    } finally {
      closeConnection();
    }
    return null;
  }

  public List<Adicional> buscar(List<Adicional> entidades) {
    Connection con = getConnection();
    List<Adicional> resultados = new ArrayList<>();

    String sql = "SELECT * FROM tb_adicional as e WHERE e.nome =?";

    try {
      PreparedStatement ps = con.prepareStatement(sql);

      for (Adicional adicional : entidades) {
        ps.setString(1, adicional.getNome());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
          // Se encontrou um registro, crie um objeto Adicional
          Adicional resultado = new Adicional();
          try {
            resultado.setId(rs.getLong("id"));
            resultado.setNome(rs.getString("nome"));
            resultado.setValor(rs.getDouble("valor"));
            resultado.setQuantidade(rs.getInt("quantidade"));
          } catch (Exception e) {
            e.printStackTrace();
          }

          resultados.add(resultado);
        }

        rs.close();
      }

      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }

    return resultados;
  }

  public List<Adicional> listar() {
    Connection con = getConnection();
    ArrayList<Adicional> lista = new ArrayList<Adicional>();
    String sql = "SELECT * FROM tb_adicional";

    try {
      PreparedStatement ps = con.prepareStatement(sql);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Adicional adicional = new Adicional();
        adicional.setId(rs.getLong("id"));
        adicional.setNome(rs.getString("nome"));
        adicional.setValor(rs.getDouble("valor"));
        adicional.setQuantidade(rs.getInt("quantidade"));

        lista.add(adicional);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }

    return lista;
  }
}
