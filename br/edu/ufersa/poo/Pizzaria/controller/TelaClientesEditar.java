package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import javafx.scene.Node;

import java.util.Optional;

import br.edu.ufersa.poo.Pizzaria.model.bo.ClienteBO;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

public class TelaClientesEditar extends Dialog<Cliente> {
    private Cliente cliente = new Cliente();

    private PseudoClass error = PseudoClass.getPseudoClass("error");

    @FXML private Label clienteExistente;
    @FXML private Button inicio, clientes, pedidos, sabores, adicionais, funcionarios, sair;
    @FXML private Button cancelar, excluir, salvar;
    @FXML private TextField nome, cpf, endereco, senha;

    @FXML
    void ExcluirCliente(ActionEvent event) {
        if (cliente != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Excluir Cliente");
            alert.setContentText("Tem certeza de que deseja excluir este cliente?");

            ButtonType buttonTypeSim = new ButtonType("Sim", ButtonData.OK_DONE);
            ButtonType buttonTypeNao = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            alert.initOwner(stage);
            alert.setX(330);
            alert.setY(400);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeSim) {
                // O usuário confirmou a exclusão, proceda com a exclusão do cliente
                ClienteBO clienteBO = new ClienteBO();
                try {
                    clienteBO.deletar(cliente);
                    Telas.TelaClientes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                exibirMensagemDeErro("Erro ao carregar cliente", e.getMessage());
                if (e.getMessage().equals("Cliente já cadastrado")) {
                    clienteExistente.setVisible(true);
                }
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
    void cancelar(ActionEvent event) throws Exception {
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
            exibirMensagemDeErro("Nome inválido", e.getMessage());
        }
    }

    @FXML
    void onTextFieldContentChanged(KeyEvent event) {
        TextField sourceTextField = (TextField) event.getSource();

        if (sourceTextField == nome) { clienteExistente.setVisible(false); }

        sourceTextField.pseudoClassStateChanged(error, false);
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
        Telas.TelaAdicionalListagem();
    }

    @FXML
    void carregarPedidos(ActionEvent event) throws Exception {
        Telas.TelaPedidos();
    }

    @FXML
    void carregarFuncionarios(ActionEvent event) throws Exception {
        Telas.TelaFuncionariosListagem();
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
