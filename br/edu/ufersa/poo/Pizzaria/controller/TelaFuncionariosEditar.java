package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import javafx.scene.Node;

import java.util.Optional;
import br.edu.ufersa.poo.Pizzaria.model.bo.UserBO;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

public class TelaFuncionariosEditar extends Dialog<Usuario> {
    private Usuario funcionario = new Usuario();

    @FXML
    private Label FuncionarioExistente;

    @FXML
    private Button excluir;

    @FXML
    private Button inicio;

    @FXML
    private TextField nome;

    @FXML
    private TextField cpf;

    @FXML
    private TextField email;

    @FXML
    private PasswordField senha1;

    @FXML
    private PasswordField senha2;

    @FXML
    private Button salvar;

    @FXML
    private Button cancelar;

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nome inválido");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void carregarInicio(ActionEvent event) throws Exception {
        Telas.TelaInicial();
    }

    @FXML
    private Button clientes;

    @FXML
    void carregarClientes(ActionEvent event) throws Exception {
        Telas.TelaClientes();
    }

    @FXML
    private Button pedidos;

    @FXML
    void carregarPedidos(ActionEvent event) throws Exception {
        Telas.TelaPedidos();
    }

    @FXML
    private Button sabores;

    @FXML
    void carregarSabores(ActionEvent event) throws Exception {
        Telas.TelaSabores();
    }

    @FXML
    private Button adicionais;

    @FXML
    void carregarAdicionais(ActionEvent event) throws Exception {
        Telas.TelaAdicionalListagem();
    }

    @FXML
    private Button funcionarios;

    @FXML
    void carregarFuncionarios(ActionEvent event) throws Exception {
        Telas.TelaFuncionariosListagem();
    }

    @FXML
    private Button sair;

    @FXML
    void carregarLogin(ActionEvent event) throws Exception {
        Telas.TelaLogin();
    }
}
