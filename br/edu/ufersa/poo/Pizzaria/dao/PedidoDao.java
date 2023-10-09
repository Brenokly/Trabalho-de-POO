package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.entity.Pedido;
import br.edu.ufersa.poo.Pizzaria.classes.EnumEstado;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import br.edu.ufersa.poo.Pizzaria.model.entity.Estado;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import br.edu.ufersa.poo.Pizzaria.model.entity.ItensPedidos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PedidoDao extends BaseDaoImpl<Pedido> {
    public Long inserir(Pedido pedido) {
        Long pedidoId = null;
        ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
        String insertPedidoSql = "INSERT INTO tb_pedido (id_cliente, estado, data) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(insertPedidoSql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, pedido.getCliente().getId());
            ps.setString(2, pedido.getEstado().getDescricao());
            ps.setObject(3, pedido.getData());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedidoId = rs.getLong(1);
                    try {
                        pedido.setId(pedidoId);
                        for (ItensPedidos itemPedido : pedido.getItensPedido()) {
                            itemPedido.setIdPedido(pedidoId);
                            itensPedidosDao.inserir(itemPedido);
                        }
                    } catch (IdInvalido ii) {
                        ii.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return pedidoId;
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

    public void alterar(Pedido pedido) {
        Connection con = getConnection();
        String sql = "UPDATE tb_pedido SET id_cliente = ?, estado = ?, data = ?, valor = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, pedido.getCliente().getId());
            ps.setString(2, pedido.getEstado().getDescricao());
            ps.setObject(3, pedido.getData());
            ps.setDouble(4, pedido.getValor());
            ps.setLong(5, pedido.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public Pedido buscar(Pedido entity) {
        Connection con = getConnection();
        Pedido resultado = new Pedido();

        String sql = "SELECT * FROM vw_itenspedido WHERE id_pedido = ?";

        try (PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setLong(1, entity.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                try {
                    resultado.setId(rs.getLong("id_pedido"));
                    entity.setId(rs.getLong("id_pedido"));

                    ClienteDao ClienteDao = new ClienteDao();
                    Cliente cliente = ClienteDao.buscar(rs.getLong("id_cliente"));
                    entity.setCliente(cliente);
                    resultado.setCliente(cliente);

                    resultado.setEstado(rs.getString("estado"));
                    entity.setEstado(rs.getString("estado"));

                    LocalDate data = rs.getDate("data").toLocalDate();
                    resultado.setData(data);
                    entity.setData(data);

                    ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
                    List<ItensPedidos> itensPedido = itensPedidosDao.buscar(resultado);
                    entity.setItensPedido(itensPedido);
                    resultado.setItensPedido(itensPedido);

                    resultado.setValor(rs.getDouble("valor"));
                    entity.setValor(rs.getDouble("valor"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

                        ClienteDao ClienteDao = new ClienteDao();
                        Cliente cliente = ClienteDao.buscar(rs.getLong("id_cliente"));

                        resultado.setCliente(cliente);
                        resultado.setEstado(rs.getString("estado"));

                        LocalDate data = rs.getDate("data").toLocalDate();
                        resultado.setData(data);

                        ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
                        List<ItensPedidos> itensPedido = itensPedidosDao.buscar(resultado);

                        resultado.setItensPedido(itensPedido);
                        resultado.setValor(rs.getDouble("valor"));

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
        List<Pedido> resultados = new ArrayList<>();

        String sql = "SELECT * from tb_pedido";

        try (Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido resultado = new Pedido();
                try {
                    resultado.setId(rs.getLong("id"));

                    ClienteDao ClienteDao = new ClienteDao();
                    Cliente cliente = ClienteDao.buscar(rs.getLong("id_cliente"));

                    resultado.setCliente(cliente);
                    resultado.setEstado(rs.getString("estado"));

                    LocalDate data = rs.getDate("data").toLocalDate();
                    resultado.setData(data);

                    ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
                    List<ItensPedidos> itensPedido = itensPedidosDao.buscar(resultado);

                    resultado.setItensPedido(itensPedido);
                    resultado.setValor(rs.getDouble("valor"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                resultados.add(resultado);
            }
        } catch (

        SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return resultados;
    }

    public List<Pedido> gerarRelatorio(Estado estado) {
        Connection con = getConnection();
        List<Pedido> resultados = new ArrayList<>();

        String sql = "SELECT * FROM tb_pedido WHERE estado = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, estado.getDescricao());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido resultado = new Pedido();
                try {
                    resultado.setId(rs.getLong("id"));

                    ClienteDao ClienteDao = new ClienteDao();
                    Cliente cliente = ClienteDao.buscar(rs.getLong("id_cliente"));

                    resultado.setCliente(cliente);
                    resultado.setEstado(rs.getString("estado"));

                    LocalDate data = rs.getDate("data").toLocalDate();
                    resultado.setData(data);

                    ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
                    List<ItensPedidos> itensPedido = itensPedidosDao.buscar(resultado);

                    resultado.setItensPedido(itensPedido);
                    resultado.setValor(rs.getDouble("valor"));

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

                    ClienteDao ClienteDao = new ClienteDao();
                    Cliente cliente = ClienteDao.buscar(rs.getLong("id_cliente"));

                    resultado.setCliente(cliente);
                    resultado.setEstado(rs.getString("estado"));

                    LocalDate data = rs.getDate("data").toLocalDate();
                    resultado.setData(data);

                    ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
                    List<ItensPedidos> itensPedido = itensPedidosDao.buscar(resultado);

                    resultado.setItensPedido(itensPedido);
                    resultado.setValor(rs.getDouble("valor"));

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
        Connection con = getConnection();
        List<Pedido> resultados = new ArrayList<>();

        String sql = "Select * FROM tb_pedido WHERE EXISTS (SELECT id_pedido FROM tb_itenspedido WHERE id_tipopizza = ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, tipoPizza.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido resultado = new Pedido();
                try {
                    resultado.setId(rs.getLong("id"));

                    ClienteDao ClienteDao = new ClienteDao();
                    Cliente cliente = ClienteDao.buscar(rs.getLong("id_cliente"));

                    resultado.setCliente(cliente);
                    resultado.setEstado(rs.getString("estado"));

                    LocalDate data = rs.getDate("data").toLocalDate();
                    resultado.setData(data);

                    ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
                    List<ItensPedidos> itensPedido = itensPedidosDao.buscar(resultado);

                    resultado.setItensPedido(itensPedido);
                    resultado.setValor(rs.getDouble("valor"));

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

                    ClienteDao ClienteDao = new ClienteDao();
                    cliente = ClienteDao.buscar(rs.getLong("id_cliente"));

                    resultado.setCliente(cliente);
                    resultado.setEstado(rs.getString("estado"));

                    LocalDate data = rs.getDate("data").toLocalDate();
                    resultado.setData(data);

                    ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
                    List<ItensPedidos> itensPedido = itensPedidosDao.buscar(resultado);

                    resultado.setItensPedido(itensPedido);
                    resultado.setValor(rs.getDouble("valor"));

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

    public List<Pedido> gerarRelatorio(ItensPedidos pizza) {
        Connection con = getConnection();
        List<Pedido> resultados = new ArrayList<>();

        String sql = "Select * FROM tb_pedido WHERE EXISTS (SELECT id_pedido FROM tb_itenspedido WHERE id = ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, pizza.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido resultado = new Pedido();
                try {
                    resultado.setId(rs.getLong("id"));

                    ClienteDao ClienteDao = new ClienteDao();
                    Cliente cliente = ClienteDao.buscar(rs.getLong("id_cliente"));

                    resultado.setCliente(cliente);
                    resultado.setEstado(rs.getString("estado"));

                    LocalDate data = rs.getDate("data").toLocalDate();
                    resultado.setData(data);

                    ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
                    List<ItensPedidos> itensPedido = itensPedidosDao.buscar(resultado);

                    resultado.setItensPedido(itensPedido);
                    resultado.setValor(rs.getDouble("valor"));

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

                    ClienteDao ClienteDao = new ClienteDao();
                    Cliente cliente = ClienteDao.buscar(rs.getLong("id_cliente"));

                    resultado.setCliente(cliente);
                    resultado.setEstado(rs.getString("estado"));

                    LocalDate data = rs.getDate("data").toLocalDate();
                    resultado.setData(data);

                    ItensPedidosDao itensPedidosDao = new ItensPedidosDao();
                    List<ItensPedidos> itensPedido = itensPedidosDao.buscar(resultado);

                    resultado.setItensPedido(itensPedido);
                    resultado.setValor(rs.getDouble("valor"));

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