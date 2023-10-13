package br.edu.ufersa.poo.Pizzaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Seed_DB {
    public static void main(String[] args) {
        final String URL = "jdbc:postgresql://localhost/PizzariaMichelangelo";
        final String USER = "postgres";
        final String PASS = "admin";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();

            // Inserir dados nas tabelas
            // Tabela tb_user
            statement.executeUpdate("INSERT INTO tb_user (nome, cpf, email, senha, isadmin) VALUES " +
                                   "('admin', '12345678901', 'admin', 'admin', true), " +
                                   "('matheus', '12345678901', 'matheus', 'matheus', false), " +
                                   "('juhan', '98765432109', 'juhan', 'juhan', false), " +
                                   "('breno', '45678901234', 'breno', 'breno', false), " +
                                   "('Usuário 4', '56789012345', 'usuario4@email.com', 'senha101', true), " +
                                   "('Usuário 5', '67890123456', 'usuario5@email.com', 'senha202', false)");

            // Tabela tb_cliente
            statement.executeUpdate("INSERT INTO tb_cliente (nome, cpf, endereco) VALUES " +
                                   "('Cliente 1', '12345678901', 'Endereço 1'), " +
                                   "('Cliente 2', '98765432109', 'Endereço 2'), " +
                                   "('Cliente 3', '45678901234', 'Endereço 3'), " +
                                   "('Cliente 4', '56789012345', 'Endereço 4'), " +
                                   "('Cliente 5', '67890123456', 'Endereço 5')");

            // Tabela tb_pedido
            statement.executeUpdate("INSERT INTO tb_pedido (id_cliente, estado, valor) VALUES " +
                                   "(1, 'pendente', 50.0), " +
                                   "(2, 'preparando', 75.5), " +
                                   "(3, 'pendente', 60.0), " +
                                   "(4, 'entregue', 90.0), " +
                                   "(5, 'pendente', 55.5)");

            // Tabela tb_tipospizza
            statement.executeUpdate("INSERT INTO tb_tipospizza (nome, valor_grande, valor_pequena) VALUES " +
                                   "('Pizza Margherita', 30.0, 20.0), " +
                                   "('Pizza Pepperoni', 35.0, 25.0), " +
                                   "('Pizza Quatro Queijos', 40.0, 30.0), " +
                                   "('Pizza Frango Catupiry', 35.0, 25.0), " +
                                   "('Pizza Calabresa', 33.0, 23.0)");

            // Tabela tb_itenspedido
            statement.executeUpdate("INSERT INTO tb_itenspedido (id_pedido, id_tipopizza, tamanho, valor, descricao) VALUES " +
                                   "(1, 1, 'Grande', 30.0, 'Pizza Margherita Grande'), " +
                                   "(1, 2, 'Pequena', 25.0, 'Pizza Pepperoni Pequena'), " +
                                   "(2, 3, 'Grande', 40.0, 'Pizza Quatro Queijos Grande'), " +
                                   "(2, 4, 'Pequena', 25.0, 'Pizza Frango Catupiry Pequena'), " +
                                   "(3, 2, 'Grande', 35.0, 'Pizza Pepperoni Grande')");

            // Tabela tb_adicional
            statement.executeUpdate("INSERT INTO tb_adicional (nome, valor, quantidade) VALUES " +
                                   "('Borda Recheada', 5.0, 1), " +
                                   "('Refrigerante 2L', 8.0, 2), " +
                                   "('Batata Frita', 12.0, 1), " +
                                   "('Sobremesa', 10.0, 3), " +
                                   "('Molho Especial', 3.0, 2)");

            // Tabela tb_pizza_adicional
            statement.executeUpdate("INSERT INTO tb_pizza_adicional (id_pizza, quantidade, id_adicional) VALUES " +
                                   "(1, 1, 1), " +
                                   "(1, 2, 2), " +
                                   "(2, 2, 3), " +
                                   "(3, 1, 4), " +
                                   "(4, 3, 5)");

            System.out.println("Dados inseridos nas tabelas com sucesso!");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
