package br.edu.ufersa.poo.Pizzaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create_DB {
    public static void main(String[] args) {
        final String URL = "jdbc:postgresql://localhost/PizzariaMichelangelo";
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
                "p.id AS id_pedido, " +
                "p.id_cliente, " +
                "p.estado, " +
                "p.data, " +
                "p.valor, " +
                "ip.id AS id_itenspedido, " +
                "ip.id_tipopizza, " +
                "tp.nome AS nome_tipopizza, " +
                "ip.tamanho, " +
                "ip.valor AS valor_itenspedido, " +
                "ip.descricao, " +
                "pa.id_adicional, " +
                "a.nome AS nome_adicional, " +
                "a.valor AS valor_adicional, " +
                "pa.quantidade AS quantidade_adicional, " +
                "pa.id AS id_pizza_adicional " +
                "FROM " +
                "tb_pedido p " +
                "JOIN tb_itenspedido ip ON p.id = ip.id_pedido " +
                "JOIN tb_tipospizza tp ON ip.id_tipopizza = tp.id " +
                "LEFT JOIN tb_pizza_adicional pa ON ip.id = pa.id_pizza " +
                "LEFT JOIN tb_adicional a ON pa.id_adicional = a.id;";

            statement.executeUpdate(createTables);
            statement.executeUpdate(createView);

            System.out.println("Tabelas e view criadas ou modificadas com sucesso!");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
