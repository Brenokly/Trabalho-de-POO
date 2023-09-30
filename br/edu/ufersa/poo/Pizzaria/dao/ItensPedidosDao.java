package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Exceptions.IdInvalido;

import java.sql.Statement;
import br.edu.ufersa.poo.Pizzaria.model.entity.*;

public class ItensPedidosDao extends BaseDaoImpl<ItensPedidos> {
    public Long inserir(ItensPedidos entity) {
        Connection con = getConnection();
        Long pizzaId = null;

        String insertPizzaSql = "INSERT INTO tb_itenspedido (id_tipopizza, id_pedido, tamanho, valor, descricao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(insertPizzaSql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, entity.getPizza().getId()); // Supondo que getPizza() retorna a pizza relacionada
            ps.setLong(2, entity.getIdPedido()); // Supondo que getPedido() retorna o pedido relacionado
            ps.setString(3, entity.getTamanho().getDescricao());
            ps.setDouble(4, entity.getValor());
            ps.setString(5, entity.getDescricao());
            ps.executeUpdate();

            // Obtenha o ID da Pizza recém-inserida
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pizzaId = rs.getLong(1);

                    // Insira as informações dos adicionais na tabela tb_pizza_adicional
                    String insertPizzaAdicionalSql = "INSERT INTO tb_pizza_adicional (id_pizza, id_adicional, quantidade) VALUES (?, ?, ?)";
                    try (PreparedStatement psAdicional = con.prepareStatement(insertPizzaAdicionalSql)) {
                        for (Adicional adicional : entity.getAdicionais()) {
                            psAdicional.setLong(1, pizzaId);
                            psAdicional.setLong(2, adicional.getId()); // Use o ID do adicional no banco de dados
                            psAdicional.setInt(3, adicional.getQuantidade()); // Use a quantidade do adicional
                            psAdicional.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return pizzaId;
    }

    public void deletar(ItensPedidos entity) {
        Connection con = getConnection();
        String sql = "DELETE FROM tb_itenspedido WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, entity.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void alterar(ItensPedidos entity) {
        Connection con = getConnection();
        String sql = "UPDATE tb_itenspedido SET id_tipopizza = ?, tamanho = ?, valor = ?, descricao = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getPizza().getId());
            ps.setString(2, entity.getTamanho().getDescricao());
            ps.setDouble(3, entity.getValor());
            ps.setString(4, entity.getDescricao());
            ps.setLong(5, entity.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public ItensPedidos buscar(ItensPedidos entity) {
        Connection con = getConnection();
        ItensPedidos resultado = null;

        String sql = "SELECT * FROM tb_itenspedido as e WHERE e.id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Se encontrou um registro, crie um objeto Pizza
                resultado = new ItensPedidos();

                try {
                    resultado.setId(rs.getLong("id"));
                    resultado.setIdPedido(rs.getLong("id_pedido"));
                    resultado.getPizza().setId(rs.getLong("id_tipopizza"));
                    resultado.setTamanho(rs.getString("tamanho"));
                    resultado.setValor(rs.getDouble("valor"));
                    resultado.setDescricao(rs.getString("descricao"));
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

    public List<ItensPedidos> buscar(Cliente cliente) {
        Connection con = getConnection();
        List<ItensPedidos> pizzas = new ArrayList<>();

        String sql = "SELECT * FROM tb_itenspedido WHERE EXISTS (SELECT id FROM tb_pedido WHERE id_cliente = ?);";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, cliente.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Obtenha os detalhes da pizza usando o ID do pedido
                ItensPedidos pizza;
                try {
                    pizza = buscar(new ItensPedidos(rs.getLong("id")));
                    pizzas.add(pizza);
                } catch (IdInvalido e) {
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

        return pizzas;
    }

    public List<ItensPedidos> buscar(TiposPizzas tipoPizza) {
        Connection con = getConnection();
        List<ItensPedidos> pizzas = new ArrayList<>();

        String sql = "SELECT * FROM tb_itenspedido WHERE id_tipopizza = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, tipoPizza.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItensPedidos pizza = new ItensPedidos();
                try {
                    pizza.setId(rs.getLong("id"));
                    pizza.setIdPedido(rs.getLong("id_pedido"));
                    pizza.getPizza().setId(rs.getLong("id_tipopizza"));
                    pizza.setTamanho(rs.getString("tamanho"));
                    pizza.setValor(rs.getDouble("valor"));
                    pizza.setDescricao(rs.getString("descricao"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pizzas.add(pizza);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return pizzas;
    }

    public List<ItensPedidos> buscar(Pedido pedido) {
        Connection con = getConnection();
        List<ItensPedidos> pizzas = new ArrayList<>();

        String sql = "SELECT * FROM vw_itenspedido WHERE id_pedido = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, pedido.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItensPedidos pizza = new ItensPedidos();
                try {
                    pizza.setId(rs.getLong("id_itenspedido"));
                    pizza.setIdPedido(rs.getLong("id_pedido"));
                    pizza.getPizza().setId(rs.getLong("id_tipopizza"));
                    pizza.setTamanho(rs.getString("tamanho"));
                    pizza.setValor(rs.getDouble("valor"));
                    pizza.getPizza().setNome(rs.getString("nome_tipopizza"));
                    pizza.setDescricao(rs.getString("descricao"));
                    pizza.getAdicionais().add(new Adicional(rs.getLong("id_adicional"), rs.getString("nome_adicional"),
                            rs.getDouble("valor_adicional"), rs.getInt("quantidade_adicional")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pizzas.add(pizza);
            }

            rs.close();
            ps.close();
        } catch (

        SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return pizzas;
    }

    public List<ItensPedidos> listar() {
        Connection con = getConnection();
        List<ItensPedidos> resultados = new ArrayList<>();

        String sql = "SELECT * FROM vw_itenspedido";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Se encontrou um registro, crie um objeto Pizza
                ItensPedidos resultado = new ItensPedidos();
                try {
                    resultado.setId(rs.getLong("id_itenspedido"));
                    resultado.setIdPedido(rs.getLong("id_pedido"));
                    resultado.getPizza().setId(rs.getLong("id_tipopizza"));
                    resultado.setTamanho(rs.getString("tamanho"));
                    resultado.setValor(rs.getDouble("valor"));
                    resultado.getPizza().setNome(rs.getString("nome_tipopizza"));
                    resultado.setDescricao(rs.getString("descricao"));
                    resultado.getAdicionais()
                            .add(new Adicional(rs.getLong("id_adicional"), rs.getString("nome_adicional"),
                                    rs.getDouble("valor_adicional"), rs.getInt("quantidade_adicional")));
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
    }
}
