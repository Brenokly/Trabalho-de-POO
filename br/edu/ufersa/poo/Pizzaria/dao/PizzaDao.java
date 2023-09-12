package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufersa.poo.Pizzaria.model.entity.*;
import java.sql.Statement;

public class PizzaDao extends BaseDaoImpl<Pizza> {

    public Long inserir(Pizza pizza) {
        Connection con = getConnection();
        Long pizzaId = null;   

        try {
            TiposPizzasDao tiposPizzasDao = new TiposPizzasDao(); // Sei que essa parte é no bo, quando for implementar lá, eu mudo
            TiposPizzas tiposPizzasExistente = tiposPizzasDao.buscar(pizza.getPizza());

            System.out.println(tiposPizzasExistente.getNome());
    
            if (tiposPizzasExistente != null) {
                // Verifique se os Adicionais existem no banco de dados
                AdicionalDao adicionalDao = new AdicionalDao();
                List<Adicional> adicionais = adicionalDao.buscar(pizza.getAdicionais());
                boolean adicionaisValidos = adicionais.size() == pizza.getAdicionais().size();

                con = getConnection();

                double valorTotalAdicionais = 0.0; 
    
                for (Adicional adicional : pizza.getAdicionais()) {
                    boolean adicionalEncontrado = false;
                    for (Adicional adicionalNoBanco : adicionais) {
                        if (adicionalNoBanco.getNome().equals(adicional.getNome()) && adicionalNoBanco.getQuantidade() >= adicional.getQuantidade()) {
                            adicionalEncontrado = true;
                            // Acrescenta o valor do adicional ao valor total dos adicionais
                            valorTotalAdicionais += adicionalNoBanco.getValor() * adicional.getQuantidade();
                            break; // Sai do loop interno se o adicional for válido
                        }
                    }
                    if (!adicionalEncontrado) {
                        adicionaisValidos = false;
                        break; // Saia do loop principal se um adicional não for válido
                    }
                }

                if (adicionaisValidos) {
                    //alterarQuant(pizza.getAdicionais()); // Quando alguém implementar o método
                }

                if (adicionaisValidos || pizza.getAdicionais().size() == 0) {
                    String insertPizzaSql = "INSERT INTO tb_pizza (id_tipo_pizza, tamanho, valor, descricao) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(insertPizzaSql, Statement.RETURN_GENERATED_KEYS);
                    double valorTotalPizza = pizza.getValor() + valorTotalAdicionais;

                    // Apenas isso 
                    // Insira a Pizza no banco de dados com o novo valor total
                    ps.setLong(1, tiposPizzasExistente.getId());
                    ps.setString(2, pizza.getTamanho().getDescricao());
                    ps.setDouble(3, valorTotalPizza); // Usa o novo valor total da pizza
                    ps.setString(4, pizza.getDescricao());
                    ps.executeUpdate();
    
                    // Obtenha o ID da Pizza recém-inserida
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            pizzaId = rs.getLong(1);

                            // Insira as informações dos adicionais na tabela tb_pizza_adicional
                            String insertPizzaAdicionalSql = "INSERT INTO tb_pizza_adicional (id_pizza, id_adicional, quantidade_pedida) VALUES (?, ?, ?)";
                            try (PreparedStatement psAdicional = con.prepareStatement(insertPizzaAdicionalSql)) {
                                for (int i = 0;  i < pizza.getAdicionais().size(); i++) {
                                    psAdicional.setLong(1, pizzaId);
                                    psAdicional.setLong(2, adicionais.get(i).getId()); // Use o ID do adicional no banco de dados, não o ID do adicional que veio do front-end
                                    psAdicional.setInt(3, pizza.getAdicionais().get(i).getQuantidade()); // Use a quantidade do adicional
                                    psAdicional.executeUpdate();
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return pizzaId;
    }

    public void deletar(Pizza entity) {
        Connection con = getConnection();
        String sql = "DELETE FROM tb_pizza WHERE id = ?";
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

    public void alterar(Pizza entity) {
        Connection con = getConnection();
        String sql = "UPDATE tb_pizza SET id_tipo_pizza = ?, tamanho = ?, valor = ?, descricao = ? WHERE id = ?";
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

    public Pizza buscar(Pizza entity) {
        Connection con = getConnection();
        Pizza resultado = null;

        String sql = "SELECT * FROM tb_pizza as e WHERE e.id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, entity.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Se encontrou um registro, crie um objeto Pizza
                resultado = new Pizza();

                try {
                  resultado.setId(rs.getLong("id"));
                  resultado.getPizza().setId(rs.getLong("id_tipo_pizza"));
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

    public List<Pizza> listar() {
        Connection con = getConnection();
        List<Pizza> resultados = new ArrayList<>();

        String sql = "SELECT * FROM tb_pizza";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Se encontrou um registro, crie um objeto Pizza
                Pizza resultado = new Pizza();
                try {
                resultado.setId(rs.getLong("id"));
                resultado.getPizza().setId(rs.getLong("id_tipo_pizza"));
                resultado.setTamanho(rs.getString("tamanho"));
                resultado.setValor(rs.getDouble("valor"));
                resultado.setDescricao(rs.getString("descricao"));
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
