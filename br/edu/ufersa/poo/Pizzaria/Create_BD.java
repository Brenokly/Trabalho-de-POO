package br.edu.ufersa.poo.Pizzaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create_BD {
    public static void main(String[] args) {
        // Configuração da conexão com o banco de dados
        final String URL = "jdbc:postgresql://localhost/Teste";
        final String USER = "postgres";
        final String PASS = "admin";

        try {
            // Conectando ao banco de dados
            Connection connection = DriverManager.getConnection(URL, USER, PASS);

            // Criando um objeto Statement para executar comandos SQL
            Statement statement = connection.createStatement();

            // Comandos SQL para criar as tabelas
            String createTableUser = "CREATE TABLE tb_user ("
                    + "id BIGSERIAL PRIMARY KEY,"
                    + "nome VARCHAR(50),"
                    + "cpf VARCHAR(14),"
                    + "email VARCHAR(50),"
                    + "senha VARCHAR(50),"
                    + "isadmin BOOLEAN"
                    + ")";
            
            String createTableCliente = "CREATE TABLE tb_cliente ("
                    + "id BIGSERIAL PRIMARY KEY,"
                    + "nome VARCHAR(50),"
                    + "cpf VARCHAR(14),"
                    + "endereco VARCHAR(50)"
                    + ")";
            
            String createTablePedido = "CREATE TABLE tb_pedido ("
                    + "id BIGSERIAL PRIMARY KEY,"
                    + "id_cliente BIGINT NOT NULL,"
                    + "estado VARCHAR(12) NOT NULL,"
                    + "data DATE NOT NULL,"
                    + "valor NUMERIC(6,2),"
                    + "FOREIGN KEY (id_cliente) REFERENCES tb_cliente(id) ON DELETE CASCADE ON UPDATE CASCADE"
                    + ")";
            
            String createTableTiposPizza = "CREATE table tb_tiposPizzas("
	                + "id BIGSERIAL PRIMARY KEY," 
	                + "nome varchar(30),"
	                + "valorGrande numeric(8, 2)," 
	                + "valorPequena numeric(8, 2)"
	                + ");";

            String createTableItensPedido = "CREATE TABLE tb_itensPedido ("
                    + "id BIGSERIAL PRIMARY KEY,"
                    + "id_pedido BIGINT NOT NULL,"
                    + "id_tipoPizza BIGINT NOT NULL,"
                    + "tamanho VARCHAR(10) NOT NULL,"
                    + "descricao TEXT NOT NULL,"
                    + "valor NUMERIC(10,2) NOT NULL,"
                    + "FOREIGN KEY (id_pedido) REFERENCES tb_pedido(id) ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "FOREIGN KEY (id_tipoPizza) REFERENCES tb_tiposPizzas(id) ON DELETE CASCADE ON UPDATE CASCADE"
                    + ")";
            
            String createTableAdicional = "CREATE TABLE tb_adicional ("
                    + "id BIGSERIAL PRIMARY KEY,"
                    + "nome VARCHAR(50),"
                    + "valor NUMERIC(10,2),"
                    + "quantidade INT"
                    + ")";
            
            String createTablePizzaAdicional = "CREATE TABLE tb_pizza_adicional ("
                    + "id_pizza BIGINT NOT NULL,"
                    + "id_adicional BIGINT NOT NULL,"
                    + "quantidade INT NOT NULL,"
                    + "FOREIGN KEY (id_pizza) REFERENCES tb_itensPedido(id) ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "FOREIGN KEY (id_adicional) REFERENCES tb_adicional(id) ON DELETE CASCADE ON UPDATE CASCADE"
                    + ")";
                    
            statement.executeUpdate(createTableUser);
            statement.executeUpdate(createTableCliente);
            statement.executeUpdate(createTableTiposPizza);
            statement.executeUpdate(createTablePedido);
            statement.executeUpdate(createTableItensPedido);
            statement.executeUpdate(createTableAdicional);
            statement.executeUpdate(createTablePizzaAdicional);

            System.out.println("Tabelas criadas com sucesso!");

            // Fechando a conexão
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
