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
    Connection con = getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    Long pizzaId = null;

    try {
      TiposPizzasDao tiposPizzasDao = new TiposPizzasDao();
      TiposPizzas tiposPizzasExistente = tiposPizzasDao.buscar(pizza.getPizza());
      
        
        if (tiposPizzasExistente != null) {
            // Verifique se os Adicionais existem no banco de dados
            List<Adicional> adicionais = buscar(pizza.getAdicionais());
            boolean adicionaisValidos = adicionais.size() == pizza.getAdicionais().size();

            if (adicionaisValidos) {
                // Insira a Pizza no banco de dados
                String insertPizzaSql = "INSERT INTO tb_pizza (id_tipo_pizza, tamanho, valor, descricao) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(insertPizzaSql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, tiposPizzasExistente.getId());
                ps.setString(2, pizza.getTamanho().getDescricao());
                ps.setDouble(3, pizza.getValor());
                ps.setString(4, pizza.getDescricao());
                ps.executeUpdate();

                // Obtenha o ID da Pizza recém-inserida
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    pizzaId = rs.getLong(1);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Feche recursos, incluindo ResultSet, PreparedStatement e Connection
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
