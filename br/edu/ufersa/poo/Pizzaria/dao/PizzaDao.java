package br.edu.ufersa.poo.Pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.model.entity.Pizza;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import java.sql.Statement;

public class PizzaDao extends BaseDaoImpl<Pizza> {

  public Long inserir(Pizza pizza) {
    Long pizzaId = null;
    String insertPizzaSql = "INSERT INTO tb_pizza (id_tipo_pizza, tamanho, valor, descricao) VALUES (?, ?, ?, ?)";

    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(insertPizzaSql, Statement.RETURN_GENERATED_KEYS)) {

        TiposPizzasDao tiposPizzasDao = new TiposPizzasDao();
        TiposPizzas tiposPizzasExistente = tiposPizzasDao.buscar(pizza.getPizza());

        if (tiposPizzasExistente != null) {
            // Verifique se os Adicionais existem no banco de dados
            List<Adicional> adicionais = buscar(pizza.getAdicionais());
            boolean adicionaisValidos = adicionais.size() == pizza.getAdicionais().size();

            for (Adicional adicional : pizza.getAdicionais()) {
                boolean adicionalEncontrado = false;
                for (Adicional adicionalNoBanco : adicionais) {
                    if (adicionalNoBanco.getId() == adicional.getId() && adicionalNoBanco.getQuantidade() >= adicional.getQuantidade()) {
                        adicionalEncontrado = true;
                        break; // Sai do loop interno se o adicional for válido
                    }
                }
                if (!adicionalEncontrado) {
                    adicionaisValidos = false;
                    break; // Saia do loop principal se um adicional não for válido
                }
            }

            if (adicionaisValidos) {
                // Insira a Pizza no banco de dados
                ps.setLong(1, tiposPizzasExistente.getId());
                ps.setString(2, pizza.getTamanho().getDescricao());
                ps.setDouble(3, pizza.getValor());
                ps.setString(4, pizza.getDescricao());
                ps.executeUpdate();

                // Obtenha o ID da Pizza recém-inserida
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        pizzaId = rs.getLong("id");

                        // insire as informações dos adicionais na tabela tb_pizza_adicional
                        String insertPizzaAdicionalSql = "INSERT INTO tb_pizza_adicional (id_pizza, id_adicional, quantidade_pedida) VALUES (?, ?, ?)";
                        try (PreparedStatement psAdicional = con.prepareStatement(insertPizzaAdicionalSql)) {
                            for (Adicional adicional : pizza.getAdicionais()) {
                                psAdicional.setLong(1, pizzaId);
                                psAdicional.setLong(2, adicional.getId());
                                psAdicional.setInt(3, adicional.getQuantidade()); // Use a quantidade do adicional
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
  }

  public void alterar(Pizza entity) {
    Connection con = getConnection();
  }

  public Pizza buscar(Pizza entity) {
    Connection con = getConnection();
    return null;
  }

  public List<Adicional> buscar(List<Adicional> entidades) {
    Connection con = getConnection();
    List<Adicional> resultados = new ArrayList<>();

    String sql = "SELECT * FROM tb_adicionais WHERE id = ?";
    
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        
        for (Adicional adicional : entidades) {
            ps.setLong(1, adicional.getId());
            
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

  public List<Pizza> listar() {
    Connection con = getConnection();
    return null;
  }


}
