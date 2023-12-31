package br.edu.ufersa.poo.Pizzaria.dao;

import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDaoImpl<Usuario> {

    public Long inserir(Usuario usuario) {
        String insertUserSql = "INSERT INTO tb_user (nome, cpf, email, senha, isAdmin) VALUES (?, ?, ?, ?, ?)";
        Long userId = null;

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(insertUserSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setBoolean(5, usuario.isAdmin());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getLong(1);
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
        String deleteUserSql = "DELETE FROM tb_user WHERE id = ?";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(deleteUserSql)) {
            ps.setLong(1, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void alterar(Usuario entity) {
        String sql = "UPDATE tb_user SET nome = ?, cpf = ?, email = ?, senha = ?, isAdmin = ? WHERE id = ?";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCpf());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getSenha());
            ps.setBoolean(5, entity.isAdmin());
            ps.setLong(6, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public Usuario buscarPorECpf(Usuario bo) {
        String sql = "SELECT * FROM tb_user WHERE email = ? OR cpf = ?";
        Usuario user = null;

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bo.getEmail());
            ps.setString(2, bo.getCpf());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getLong("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setAdmin(rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return user;
    }

    
    public Usuario buscar(Usuario entity) {
        String sql = "SELECT * FROM tb_user WHERE id = ?";
        Usuario user = null;

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, entity.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getLong("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setAdmin(rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return user;
    }

    public List<Usuario> buscarINE(Usuario entity) {
        String sql = "SELECT * FROM tb_user WHERE ";
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
        if (entity.getEmail() != null && !entity.getEmail().isEmpty()) {
            conditions.add("email = ?");
            parameters.add(entity.getEmail());
        }

        if (conditions.isEmpty()) {
            // Se nenhum campo válido foi fornecido, não execute a consulta.
            return new ArrayList<>(); // Retorna uma lista vazia.
        }

        sql += String.join(" OR ", conditions);

        List<Usuario> usuarios = new ArrayList<>();

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getLong("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                usuarios.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return usuarios;
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_user";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getLong("id"));
                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                lista.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return lista;
    }
}