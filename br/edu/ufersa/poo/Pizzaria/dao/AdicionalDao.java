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

public class AdicionalDao extends BaseDaoImpl<Adicional>{
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
      
      ps.setLong(7, entity.getId());

      ps.executeUpdate();
    } catch (Exception e) {
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
            adicional.setValor(rs.getLong("valor"));
            adicional.setQuantidade(rs.getInt("quantidade"));
            
            lista.add(adicional);
        }
    } catch (Exception e){
        e.printStackTrace();
    } finally {
        closeConnection();
    }

    return lista;
  }
}
