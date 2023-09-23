package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;

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
