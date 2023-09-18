package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import java.sql.Statement;

public class UserDao extends BaseDaoImpl<Usuario> {

  public Long inserir(Usuario usuario) {
    Long userId = null;
    String insertUserSql = "INSERT INTO tb_user (nome, cpf, endereco, email, senha, isAdmin) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, usuario.getNome());
        ps.setString(2, usuario.getCpf());
        ps.setString(3, usuario.getEndereco());
        ps.setString(4, usuario.getEmail());
        ps.setString(5, usuario.getSenha());
        ps.setBoolean(6, usuario.isAdmin());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
          userId = rs.getLong("id");
          usuario.setId(userId);
        }


    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeConnection();
    }
    return userId;
  }

  public void deletar(Usuario entity) {
    Connection con = getConnection();
    String deleteUserSql = "delete from tb_user where id = ?";

    try {
      PreparedStatement ps = con.prepareStatement(deleteUserSql);
      ps.setLong(1, entity.getId());
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
  }

  public void alterar(Usuario entity) {
    Connection con = getConnection();

    String sql = "update tb_user set nome = ?, cpf = ?, endereco = ?, email = ?, senha = ?, isAdmin = ? where id = ?";

    try {
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, entity.getNome());
      ps.setString(2, entity.getCpf());
      ps.setString(3, entity.getEndereco());
      ps.setString(4, entity.getEmail());
      ps.setString(5, entity.getSenha());
      ps.setBoolean(6, entity.isAdmin());
      ps.setLong(7, entity.getId());

      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection();
    }
  }

  public Usuario buscar(Usuario entity) {
    Connection con = getConnection();

    String sql = "SELECT * FROM tb_user where id=?";

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

  public List<Usuario> listar() {
    Connection con = getConnection();
    ArrayList<Usuario> lista = new ArrayList<Usuario>();
    String sql = "SELECT * FROM tb_user";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Usuario user = new Usuario();
            user.setId(rs.getLong("id"));
            user.setNome(rs.getString("nome"));
            user.setCpf(rs.getString("cpf"));
            user.setEndereco(rs.getString("endereco"));
            user.setEmail(rs.getString("email"));
            user.setSenha(rs.getString("senha"));
            user.setAdmin(rs.getBoolean("isAdmin"));
            lista.add(user);
        }
    } catch (Exception e){
        e.printStackTrace();
    } finally {
        closeConnection();
    }

    return lista;
  }

}