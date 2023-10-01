package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import br.edu.ufersa.poo.Pizzaria.model.bo.UserBO;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

public class TelaFuncionarios3 extends Dialog<Usuario> {
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
    private TextField senha;

    @FXML
    private Button salvar;

    @FXML
    private Button cancelar;

    @FXML
    void ExcluirFuncionario(ActionEvent event) {
        if (funcionario != null) {
            UserBO userBO = new UserBO();
            try {
                userBO.deletar(funcionario);
                Telas.TelaFuncionarios();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void SalvarFuncionario(ActionEvent event) {
        if (funcionario != null) {
            String novoNome = nome.getText();
            String novoCpf = cpf.getText();
            String novoEmail = email.getText();
            String novaSenha = senha.getText();

            // Modifique o objeto Usuario com os novos valores
            try {
                funcionario.setNome(novoNome);
                funcionario.setCpf(novoCpf);
                funcionario.setEmail(novoEmail);
                funcionario.setSenha(novaSenha);

                // Atualize o objeto no banco de dados
                UserBO userBO = new UserBO();
                userBO.update(funcionario);

                // Redirecione para a tela desejada após o salvamento bem-sucedido
                Telas.TelaFuncionarios();
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
        Telas.TelaFuncionarios();
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
            senha.setText(funcionario.getSenha());
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
        // Telas.TelaPedidos();
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
        Telas.TelaAdicional();
    }

    @FXML
    private Button funcionarios;

    @FXML
    void carregarFuncionarios(ActionEvent event) throws Exception {
        Telas.TelaFuncionarios();
    }

    @FXML
    private Button sair;

    @FXML
    void carregarLogin(ActionEvent event) throws Exception {
        Telas.TelaLogin();
    }
}
