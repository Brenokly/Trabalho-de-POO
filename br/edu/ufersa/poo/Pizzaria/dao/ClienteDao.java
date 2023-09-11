package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import java.sql.Statement;

public class ClienteDao extends BaseDaoImpl<Cliente> {
    public Long inserir(Cliente cliente) {
        Long clienteId = null;
        String insertClienteSql = "INSERT INTO tb_cliente (nome, cpf, endereco) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(insertClienteSql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEndereco());
            ps.executeQuery();

            ResultSet rs = ps.getGeneratedKeys();
            clienteId = rs.getLong("id");   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienteId;
    }

    public void deletar(Cliente entity) {
        Connection con = getConnection();

        String sql = "DELETE FROM tb_cliente WHERE id = ?";
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

    public void alterar(Cliente entity) {
        Connection con = getConnection();

        String sql = "UPDATE tb_cliente SET nome = ?, cpf = ?, endereco = ? WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getNome());
            ps.setString(2, entity.getCpf());
            ps.setString(3, entity.getEndereco());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public Cliente buscar(Cliente entity) {
        Connection con = getConnection();
        Cliente resultado = new Cliente();

        String sql = "SELECT * FROM tb_cliente WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                resultado.setId(rs.getLong("id"));
                resultado.setNome(rs.getString("nome"));
                resultado.setCpf(rs.getString("cpf"));
                resultado.setEndereco(rs.getString("endereco"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return resultado;
    }

    public List<Cliente> buscar(List<Cliente> entities) {
        Connection con = getConnection();
        List<Cliente> resultados = new ArrayList<>(null);

        String sql = "SELECT * FROM tb_cliente WHERE id = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            for (Cliente cliente : entities) {
                ps.setLong(1, cliente.getId());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Cliente resultado = new Cliente();
                    try {
                        resultado.setId(rs.getLong("id"));
                        resultado.setNome(rs.getString("nome"));
                        resultado.setCpf(rs.getString("cpf"));
                        resultado.setEndereco(rs.getString("endereco"));
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

    public List<Cliente> listar() {
        Connection con = getConnection();
        List<Cliente> resultados = new ArrayList<>();

        String sql = "SELECT * from tb_cliente";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente resultado = new Cliente();
                try {
                    resultado.setId(rs.getLong("id"));
                    resultado.setNome(rs.getString("nome"));
                    resultado.setCpf(rs.getString("cpf"));
                    resultado.setEndereco(rs.getString("endereco"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                resultados.add(resultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return resultados;
    }
}
