package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.entity.Pedido;
import br.edu.ufersa.poo.Pizzaria.classes.EnumEstado;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import br.edu.ufersa.poo.Pizzaria.model.entity.Estado;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import br.edu.ufersa.poo.Pizzaria.model.entity.Pizza;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PedidoDao extends BaseDaoImpl<Pedido> {
    public Long inserir(Pedido pedido) {
        // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
        return null;
    }

    public void deletar(Pedido entity) {
        Connection con = getConnection();

        String sql = "DELETE FROM tb_pedido WHERE id = ?";
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

    public void alterar(Pedido entity) {
        // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
    }

    public Pedido buscar(Pedido entity) {
        Connection con = getConnection();
        Pedido resultado = new Pedido();

        String sql = "SELECT * FROM tb_pedido WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                resultado.setId(rs.getLong("id"));
                // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return resultado;
    }

    public List<Pedido> buscar(List<Pedido> entities) {
        Connection con = getConnection();
        List<Pedido> resultados = new ArrayList<>(null);

        String sql = "SELECT * FROM tb_pedido WHERE id = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            for (Pedido pedido : entities) {
                ps.setLong(1, pedido.getId());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Pedido resultado = new Pedido();
                    try {
                        resultado.setId(rs.getLong("id"));
                        // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
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

    public List<Pedido> listar() {
        Connection con = getConnection();
        List<Pedido> resultados = new ArrayList<>();

        String sql = "SELECT * from tb_pedido";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido resultado = new Pedido();
                try {
                    resultado.setId(rs.getLong("id"));
                    // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas

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
    
    public List<Pedido> gerarRelatorio(Estado estado) {
        Connection con = getConnection();
        List<Pedido> resultados = new ArrayList<>(null);

        String sql = "SELECT * FROM tb_pedido WHERE estado = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, estado.getDescricao());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pedido resultado = new Pedido();
                try {
                    resultado.setId(rs.getLong("id"));
                    // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
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

    public List<Pedido> gerarRelatorio(LocalDate inicioPeriodo, LocalDate fimPeriodo) {
        Connection con = getConnection();
        List<Pedido> resultados = new ArrayList<>(null);

        String sql = "SELECT * FROM tb_pedido WHERE data BETWEEN ? AND ?";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, formatter.format(inicioPeriodo));
            ps.setString(1, formatter.format(fimPeriodo));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pedido resultado = new Pedido();
                try {
                    resultado.setId(rs.getLong("id"));
                    // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
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

    public List<Pedido> gerarRelatorio(TiposPizzas tipoPizza) {
        // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
        return null;
    }

    public List<Pedido> gerarRelatorio(Cliente cliente) {
        Connection con = getConnection();
        List<Pedido> resultados = new ArrayList<>(null);

        String sql = "SELECT * FROM tb_pedido WHERE id_cliente = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, cliente.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pedido resultado = new Pedido();
                try {
                    resultado.setId(rs.getLong("id"));
                    // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
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

    public List<Pedido> gerarRelatorio(Pizza pizza) {
        // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
        return null;
    }

    public List<Pedido> gerarRelatorio(EnumEstado enumEstado) {
        Connection con = getConnection();
        List<Pedido> resultados = new ArrayList<>(null);

        String sql = "SELECT * FROM tb_pedido WHERE estado = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, enumEstado.getDescricao());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pedido resultado = new Pedido();
                try {
                    resultado.setId(rs.getLong("id"));
                    // Precisa ser verificado como será feita a conexão por chave estrangeira entre pedidos e pizzas
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