package br.edu.ufersa.poo.Pizzaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Delete_DB {
    public static void main(String[] args) {
        final String URL = "jdbc:postgresql://localhost/PizzariaMichelangelo";
        final String USER = "postgres";
        final String PASS = "admin";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();

            // SQL para deletar dados das tabelas
            String deleteData = 
                "DELETE FROM tb_pizza_adicional; " +
                "DELETE FROM tb_adicional; " +
                "DELETE FROM tb_itenspedido; " +
                "DELETE FROM tb_tipospizza; " +
                "DELETE FROM tb_pedido; " +
                "DELETE FROM tb_cliente; " +
                "DELETE FROM tb_user;";

            // Verificar se a visão existe
            String checkViewExists = "SELECT EXISTS (SELECT 1 FROM information_schema.views WHERE table_name = 'vw_itenspedido');";
            ResultSet resultSet = statement.executeQuery(checkViewExists);

            if (resultSet.next() && resultSet.getBoolean(1)) {
                // A visão existe, então você pode prosseguir para excluir seus dados
                String deleteViewData = "DELETE FROM tb_itenspedido WHERE id IN (SELECT id_itenspedido FROM vw_itenspedido);";
                statement.executeUpdate(deleteViewData);
                System.out.println("Dados da visão excluídos com sucesso!");
            } else {
                // A visão não existe
                System.out.println("A visão não existe.");
            }

            // SQL para excluir tabelas e visão em cascata
            String dropTablesCascade = 
                "DROP TABLE IF EXISTS tb_pizza_adicional CASCADE; " +
                "DROP TABLE IF EXISTS tb_adicional CASCADE; " +
                "DROP TABLE IF EXISTS tb_itenspedido CASCADE; " +
                "DROP TABLE IF EXISTS tb_tipospizza CASCADE; " +
                "DROP TABLE IF EXISTS tb_pedido CASCADE; " +
                "DROP TABLE IF EXISTS tb_cliente CASCADE; " +
                "DROP TABLE IF EXISTS tb_user CASCADE;" +
                "DROP TABLE IF EXISTS tb_pedido_log CASCADE;";


            // SQL para excluir a visão
            String dropView = "DROP VIEW IF EXISTS vw_itenspedido;";

            // SQL para excluir funções
            String dropFunctions = 
                "DROP FUNCTION IF EXISTS calcular_e_atualizar_valor_total_pedido(bigint); " +
                "DROP FUNCTION IF EXISTS calcular_e_atualizar_valor_item_pedido(bigint);" +
                "DROP FUNCTION IF EXISTS log_pedido_estado() CASCADE;";

            String dropTrigger = "DROP TRIGGER IF EXISTS trigger_log_pedido_estado ON tb_pedido;";
            
            // Executar SQL para deletar dados, excluir tabelas, visão e funções
            statement.executeUpdate(deleteData);
            statement.executeUpdate(dropTablesCascade);
            statement.executeUpdate(dropView);
            statement.executeUpdate(dropFunctions);
            statement.executeUpdate(dropTrigger);

            System.out.println("Tabelas, visão, funções e dados associados excluídos com sucesso!");

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
