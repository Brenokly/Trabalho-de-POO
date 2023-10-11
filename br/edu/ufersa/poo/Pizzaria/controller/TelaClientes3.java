package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import br.edu.ufersa.poo.Pizzaria.model.bo.ClienteBO;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

public class TelaClientes3 extends Dialog<Cliente> {
    private Cliente cliente = new Cliente();

    @FXML
    private Label ClienteExistente;

    @FXML
    private Button excluir;

    @FXML
    private Button inicio;

    @FXML
    private TextField nome;

    @FXML
    private TextField cpf;

    @FXML
    private TextField endereco;

    @FXML
    private TextField senha;

    @FXML
    private Button salvar;

    @FXML
    private Button cancelar;

    @FXML
    private Button clientes;

    @FXML
    private Button pedidos;

    @FXML
    private Button sabores;

    @FXML
    private Button adicionais;

    @FXML
    private Button funcionarios;

    @FXML
    private Button sair;

    @FXML
    void ExcluirCliente(ActionEvent event) {
        if (cliente != null) {
            ClienteBO clienteBO = new ClienteBO();
            try {
                clienteBO.deletar(cliente);
                Telas.TelaClientes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void salvarCliente(ActionEvent event) {
        if (cliente != null) {
            String novoNome = nome.getText();
            String novoCpf = cpf.getText();
            String novoEndereco = endereco.getText();

            // Modifique o objeto Usuario com os novos valores
            try {
                cliente.setNome(novoNome);
                cliente.setCpf(novoCpf);
                cliente.setEndereco(novoEndereco);

                // Atualize o objeto no banco de dados
                ClienteBO clienteBO = new ClienteBO();
                clienteBO.update(cliente);

                // Redirecione para a tela desejada após o salvamento bem-sucedido
                Telas.TelaClientes();
            } catch (Exception e) {
                exibirMensagemDeErro("Erro ao carregar funcionário", e.getMessage());
            }
        }
    }

    // Método para exibir uma mensagem de erro personalizada
    private void exibirMensagemDeErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    void Cancelar(ActionEvent event) throws Exception {
        Telas.TelaClientes();
    }

    public void setCliente(Cliente cliente) {
        try {
            this.cliente.setNome(cliente.getNome());
            this.cliente.setCpf(cliente.getCpf());
            this.cliente.setEndereco(cliente.getEndereco());
            this.cliente.setId(cliente.getId());
            nome.setText(cliente.getNome());
            cpf.setText(cliente.getCpf());
            endereco.setText(cliente.getEndereco());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nome inválido");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void carregarClientes(ActionEvent event) throws Exception {
        Telas.TelaClientes();
    }

    @FXML
    void carregarSabores(ActionEvent event) throws Exception {
        Telas.TelaSabores();
    }

    @FXML
    void carregarAdicionais(ActionEvent event) throws Exception {
        Telas.TelaAdicional();
    }

    @FXML
    void carregarPedidos(ActionEvent event) throws Exception {
        // Telas.TelaPedidos();
    }

    @FXML
    void carregarFuncionarios(ActionEvent event) throws Exception {
        Telas.TelaFuncionarios();
    }

    @FXML
    void carregarInicio(ActionEvent event) throws Exception {
        Telas.TelaInicial();
    }

    @FXML
    void carregarLogin(ActionEvent event) throws Exception {
        Telas.TelaLogin();
    }
}
