package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import javafx.scene.Node;

import java.util.Optional;
import br.edu.ufersa.poo.Pizzaria.model.bo.UserBO;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

public class TelaFuncionariosEditar extends Dialog<Usuario> {
    private Usuario funcionario = new Usuario();

    private PseudoClass error = PseudoClass.getPseudoClass("error");

    @FXML private Label FuncionarioExistente;
    @FXML private Button inicio, clientes, pedidos, sabores, adicionais, funcionarios, sair;
    @FXML private TextField nome, cpf, email;
    @FXML private PasswordField senha1, senha2;
    @FXML private Button salvar, excluir, cancelar;

    @FXML
    void ExcluirFuncionario(ActionEvent event) {
        if (funcionario != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Excluir Funcionário");
            alert.setContentText("Tem certeza de que deseja excluir este funcionário?");

            ButtonType buttonTypeSim = new ButtonType("Sim", ButtonData.OK_DONE);
            ButtonType buttonTypeNao = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            alert.initOwner(stage);
            alert.setX(330);
            alert.setY(400);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeSim) {
                // O usuário confirmou a exclusão, proceda com a exclusão do funcionário
                UserBO userBO = new UserBO();
                try {
                    userBO.deletar(funcionario);
                    Telas.TelaFuncionariosListagem();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Caso contrário, o usuário optou por não excluir o funcionário
        }
    }

    @FXML
    void SalvarFuncionario(ActionEvent event) {
        if (funcionario != null) {
            String novoNome = nome.getText();
            String novoCpf = cpf.getText();
            String novoEmail = email.getText();
            String novaSenha = senha1.getText();
            String novaSenha2 = senha2.getText();

            // Modifique o objeto Usuario com os novos valores
            try {
                if (novaSenha.equals(novaSenha2)) {
                    funcionario.setNome(novoNome);
                    funcionario.setCpf(novoCpf);
                    funcionario.setEmail(novoEmail);
                    funcionario.setSenha(novaSenha);

                    // Atualize o objeto no banco de dados
                    UserBO userBO = new UserBO();
                    userBO.update(funcionario);

                    // Redirecione para a tela desejada após o salvamento bem-sucedido
                    Telas.TelaFuncionariosListagem();
                } else {
                    senha2.pseudoClassStateChanged(error, false);
                    exibirMensagemDeErro("Senhas diferentes", "As senhas não coincidem. Verifique novamente.");
                } 
            } catch (Exception e){
                exibirMensagemDeErro("Erro ao salvar funcionário", e.getMessage());
                if (e.getMessage().equals("Usuário já cadastrado")) {
                    FuncionarioExistente.setVisible(true);
                } else {
                    FuncionarioExistente.setVisible(false);
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
    void Cancelar(ActionEvent event) throws Exception {
        Telas.TelaFuncionariosListagem();
    }

    public void setFuncionario(Usuario funcionario) {
        try {
            this.funcionario.setNome(funcionario.getNome());
            this.funcionario.setCpf(funcionario.getCpf());
            this.funcionario.setEmail(funcionario.getEmail());
            this.funcionario.setSenha(funcionario.getSenha());
            this.funcionario.setId(funcionario.getId());
            nome.setText(funcionario.getNome());
            cpf.setText(funcionario.getCpf());
            email.setText(funcionario.getEmail());
            senha1.setText(funcionario.getSenha());
        } catch (Exception e) {
            nome.pseudoClassStateChanged(error, false);
            exibirMensagemDeErro("Nome inválido", e.getMessage());
        }
    }

    @FXML
    void onTextFieldContentChanged(KeyEvent event) {
        TextField sourceTextField = (TextField) event.getSource();

        if (sourceTextField == nome) { FuncionarioExistente.setVisible(false); }

        sourceTextField.pseudoClassStateChanged(error, false);
    }

    @FXML
    void carregarInicio(ActionEvent event) throws Exception {
        Telas.TelaInicial();
    }

    @FXML
    void carregarClientes(ActionEvent event) throws Exception {
        Telas.TelaClientes();
    }

    @FXML
    void carregarPedidos(ActionEvent event) throws Exception {
        Telas.TelaPedidos();
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
    void carregarFuncionarios(ActionEvent event) throws Exception {
        Telas.TelaFuncionariosListagem();
    }

    @FXML
    void carregarLogin(ActionEvent event) throws Exception {
        Telas.TelaLogin();
    }
}
