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
                    "('Michelangelo', '56789012345', 'mikey', 'senha101', true), " +
                    "('Hamato Yoshi', '67890123456', 'splinter', 'senha202', false)");

            // Tabela tb_cliente
            statement.executeUpdate("INSERT INTO tb_cliente (nome, cpf, endereco) VALUES " +
                    "('Leonardo', '12345678901', 'Endereço 1'), " +
                    "('Raphael', '98765432109', 'Endereço 2'), " +
                    "('Donatello', '45678901234', 'Endereço 3'), " +
                    "('Michelangelo', '56789012345', 'Endereço 4'), " +
                    "('Splinter', '67890123456', 'Endereço 5'), " +
                    "('April O''Neil', '98765432100', 'Endereço 6'), " +
                    "('Shredder', '56789012399', 'Endereço 7'), " +
                    "('Casey Jones', '67890123488', 'Endereço 8'), " +
                    "('Karai', '12345678977', 'Endereço 9'), " +
                    "('Bebop', '98765432166', 'Endereço 10'), " +
                    "('Rocksteady', '56789012355', 'Endereço 11'), " +
                    "('Krang', '67890123444', 'Endereço 12'), " +
                    "('Baxter Stockman', '12345678933', 'Endereço 13'), " +
                    "('Rat King', '98765432122', 'Endereço 14'), " +
                    "('Vernon Fenwick', '56789012311', 'Endereço 15')");

            // Tabela tb_pedido
            statement.executeUpdate("INSERT INTO tb_pedido (id_cliente, estado, valor, data) VALUES " +
                    "(1, 'pendente', 50.0, '2023-10-01'), " +
                    "(2, 'preparando', 75.5, '2023-10-02'), " +
                    "(3, 'pendente', 60.0, '2023-10-03'), " +
                    "(4, 'entregue', 90.0, '2023-10-04'), " +
                    "(5, 'pendente', 55.5, '2023-10-05'), " +
                    "(6, 'pendente', 70.0, '2023-10-15'), " +
                    "(7, 'preparando', 45.5, '2023-10-14'), " +
                    "(8, 'entregue', 85.0, '2023-10-13'), " +
                    "(9, 'pendente', 60.5, '2023-10-12'), " +
                    "(10, 'entregue', 92.0, '2023-10-11'), " +
                    "(11, 'preparando', 55.0, '2023-10-10'), " +
                    "(12, 'entregue', 72.5, '2023-10-09'), " +
                    "(13, 'pendente', 63.0, '2023-10-08'), " +
                    "(14, 'entregue', 80.0, '2023-10-07'), " +
                    "(15, 'pendente', 59.5, '2023-10-06')");

            // Tabela tb_tipospizza
            statement.executeUpdate("INSERT INTO tb_tipospizza (nome, valor_grande, valor_pequena) VALUES " +
                    "('Pizza Margherita', 30.0, 20.0), " +
                    "('Pizza Pepperoni', 35.0, 25.0), " +
                    "('Pizza Quatro Queijos', 40.0, 30.0), " +
                    "('Pizza Frango Catupiry', 35.0, 25.0), " +
                    "('Pizza Calabresa', 33.0, 23.0)");

            // Tabela tb_itenspedido
            // Tabela tb_itenspedido
statement.executeUpdate(
    "INSERT INTO tb_itenspedido (id_pedido, id_tipopizza, tamanho, valor, descricao) VALUES " +
        "(1, 1, 'grande', 30.0, 'Pizza Margherita Grande'), " +
        "(2, 2, 'pequena', 25.0, 'Pizza Pepperoni Pequena'), " +
        "(3, 3, 'grande', 40.0, 'Pizza Quatro Queijos Grande'), " +
        "(4, 4, 'pequena', 25.0, 'Pizza Frango Catupiry Pequena'), " +
        "(5, 2, 'grande', 35.0, 'Pizza Pepperoni Grande'), " +
        "(6, 1, 'grande', 30.0, 'Pizza Margherita Grande'), " +
        "(7, 2, 'pequena', 25.0, 'Pizza Pepperoni Pequena'), " +
        "(8, 3, 'grande', 40.0, 'Pizza Quatro Queijos Grande'), " +
        "(9, 4, 'pequena', 25.0, 'Pizza Frango Catupiry Pequena'), " +
        "(10, 2, 'grande', 35.0, 'Pizza Pepperoni Grande'), " +
        "(11, 1, 'grande', 30.0, 'Pizza Margherita Grande'), " +
        "(12, 2, 'pequena', 25.0, 'Pizza Pepperoni Pequena'), " +
        "(13, 3, 'grande', 40.0, 'Pizza Quatro Queijos Grande'), " +
        "(14, 4, 'pequena', 25.0, 'Pizza Frango Catupiry Pequena'), " +
        "(15, 2, 'grande', 35.0, 'Pizza Pepperoni Grande')");


            // Tabela tb_adicional
            statement.executeUpdate("INSERT INTO tb_adicional (nome, valor, quantidade) VALUES " +
                    "('Borda Recheada', 5.0, 1), " +
                    "('Refrigerante 2L', 8.0, 2), " +
                    "('Batata Frita', 12.0, 1), " +
                    "('Sobremesa', 10.0, 3), " +
                    "('Molho de Tomate', 2.5, 7), " +
                    "('Camarão', 6.5, 5), " +
                    "('Cebola', 1.5, 6), " +
                    "('Coentro', 0.5, 10), " +
                    "('Azeite', 1.5, 3), " +
                    "('Bacon', 5.5, 8), " +
                    "('Pimentões', 1.5, 4), " +
                    "('Manjericão', 1.75, 2), " +
                    "('Frango', 4.5, 9), " +
                    "('Catupiry', 3.5, 3), " +
                    "('Queijo Mussarela', 3.5, 15), " +
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
