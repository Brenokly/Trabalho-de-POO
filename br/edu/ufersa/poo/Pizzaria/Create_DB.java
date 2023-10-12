package br.edu.ufersa.poo.Pizzaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create_DB {
    public static void main(String[] args) {
        final String URL = "jdbc:postgresql://localhost/Teste";
        final String USER = "postgres";
        final String PASS = "admin";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();

            // Declarações SQL para criar tabelas
            String createTables = 
                "CREATE TABLE IF NOT EXISTS tb_user (" +
                "id BIGSERIAL PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL," +
                "cpf VARCHAR(14) NOT NULL," +
                "email VARCHAR(255) NOT NULL," +
                "senha VARCHAR(255) NOT NULL," +
                "isadmin BOOLEAN" +
                ");" +

                "CREATE TABLE IF NOT EXISTS tb_cliente (" +
                "id BIGSERIAL PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL," +
                "cpf VARCHAR(14) NOT NULL," +
                "endereco TEXT NOT NULL" +
                ");" +

                "CREATE TABLE IF NOT EXISTS tb_pedido (" +
                "id BIGSERIAL PRIMARY KEY," +
                "id_cliente BIGINT REFERENCES tb_cliente(id) ON DELETE CASCADE ON UPDATE CASCADE," +
                "estado VARCHAR(255)," +
                "data DATE DEFAULT CURRENT_DATE," +
                "valor NUMERIC(10, 2) NOT NULL CHECK(valor >= 0)" +
                ");" +

                "CREATE TABLE IF NOT EXISTS tb_tipospizza (" +
                "id BIGSERIAL PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL," +
                "valor_grande NUMERIC(10, 2) NOT NULL CHECK(valor_grande >= 0)," +
                "valor_pequena NUMERIC(10, 2) NOT NULL CHECK(valor_pequena >= 0)" +
                ");" +

                "CREATE TABLE IF NOT EXISTS tb_itenspedido (" +
                "id BIGSERIAL PRIMARY KEY," +
                "id_pedido BIGINT REFERENCES tb_pedido(id) ON DELETE CASCADE ON UPDATE CASCADE," +
                "id_tipopizza BIGINT REFERENCES tb_tipospizza(id) ON DELETE CASCADE ON UPDATE CASCADE," +
                "tamanho VARCHAR(20) NOT NULL," +
                "valor NUMERIC(10, 2) NOT NULL CHECK(valor >= 0)," +
                "descricao TEXT" +
                ");" +

                "CREATE TABLE IF NOT EXISTS tb_adicional (" +
                "id BIGSERIAL PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL," +
                "valor NUMERIC(10, 2) NOT NULL CHECK(valor >= 0)," +
                "quantidade INTEGER NOT NULL CHECK(quantidade >= 0)" +
                ");" +

                "CREATE TABLE IF NOT EXISTS tb_pizza_adicional (" +
                "id BIGSERIAL PRIMARY KEY," +
                "id_pizza BIGINT REFERENCES tb_itenspedido(id) ON DELETE CASCADE ON UPDATE CASCADE," +
                "quantidade INTEGER NOT NULL CHECK(quantidade >= 0)," +
                "id_adicional BIGINT REFERENCES tb_adicional(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";

            // Declaração SQL para criar a view
            String createView = 
                "CREATE OR REPLACE VIEW vw_itenspedido AS " +
                "SELECT " +
                "P.id AS id_pedido, " +
                "P.id_cliente, " +
                "P.estado, " +
                "P.data, " +
                "P.valor, " +
                "IP.id AS id_itenspedido, " +
                "IP.id_tipopizza, " +
                "TP.nome AS nome_tipopizza, " +
                "IP.tamanho, " +
                "IP.valor AS valor_itenspedido, " +
                "IP.descricao, " +
                "PA.id_adicional, " +
                "A.nome AS nome_adicional, " +
                "A.valor AS valor_adicional, " +
                "PA.quantidade AS quantidade_adicional " +
                "FROM " +
                "tb_pedido AS P " +
                "JOIN tb_itenspedido AS IP ON P.id = IP.id_pedido " +
                "JOIN tb_tipospizza AS TP ON IP.id_tipopizza = TP.id " +
                "LEFT JOIN tb_pizza_adicional AS PA ON IP.id = PA.id_pizza " +
                "LEFT JOIN tb_adicional AS A ON PA.id_adicional = A.id;";

            // Declarações SQL para criar funções
            String createFunctions = 
                "CREATE OR REPLACE FUNCTION calcular_e_atualizar_valor_total_pedido(pedido_id BIGINT) " +
                "RETURNS NUMERIC(10, 2) AS $$ " +
                "DECLARE " +
                "pedido_total NUMERIC(10, 2); " +
                "BEGIN " +
                "SELECT COALESCE(SUM(valor), 0) " +
                "INTO pedido_total " +
                "FROM tb_itenspedido " +
                "WHERE id_pedido = pedido_id; " +
                "UPDATE tb_pedido " +
                "SET valor = pedido_total " +
                "WHERE id = pedido_id; " +
                "RETURN pedido_total; " +
                "END; " +
                "$$ LANGUAGE plpgsql; " +

                "CREATE OR REPLACE FUNCTION calcular_e_atualizar_valor_item_pedido(item_id BIGINT) " +
                "RETURNS NUMERIC(10, 2) AS $$ " +
                "DECLARE " +
                "item_total NUMERIC(10, 2); " +
                "BEGIN " +
                "SELECT COALESCE(SUM(tp.valor), 0) + COALESCE(SUM(a.valor * pa.quantidade), 0) " +
                "INTO item_total " +
                "FROM tb_itenspedido tp " +
                "LEFT JOIN tb_pizza_adicional pa ON tp.id = pa.id_pizza " +
                "LEFT JOIN tb_adicional a ON pa.id_adicional = a.id " +
                "WHERE tp.id = item_id; " +
                "UPDATE tb_itenspedido " +
                "SET valor = item_total " +
                "WHERE id = item_id; " +
                "RETURN item_total; " +
                "END; " +
                "$$ LANGUAGE plpgsql;";

            // Execute todas as declarações SQL para criar tabelas, view e funções
            statement.executeUpdate(createTables);
            statement.executeUpdate(createView);
            statement.executeUpdate(createFunctions);

            System.out.println("Tabelas, view e funções criadas ou modificadas com sucesso!");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
