package br.edu.ufersa.poo.Pizzaria.controller;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.bo.UserBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaFuncionarios2 {

  @FXML
  private Button salvar;

  @FXML
  private Button cancelar;

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
  private Label funcionariosExistente;

  @FXML
  void salvarFuncionario(ActionEvent event) throws Exception {
    UserBO userBO = new UserBO();

    String nomeTexto = nome.getText();
    String cpfTexto = cpf.getText();
    String emailTexto = email.getText();
    String senhaTexto = senha1.getText();
    String senhaTexto2 = senha2.getText();

    boolean nomeValido = !nomeTexto.isEmpty();
    boolean nomeApenasLetras = nomeTexto.matches("^[\\p{L}\\s]*$");

    boolean cpfValido = !cpfTexto.isEmpty();
    boolean cpfApenasNumeros = cpfTexto.matches("^[0-9]*$");
    boolean cpfTamanhoValido = cpfTexto.length() == 11 || cpfTexto.length() == 14;

    boolean emailValido = !emailTexto.isEmpty() && emailTexto.contains("@") && emailTexto.contains(".");
    boolean senhaValida = !senhaTexto.isEmpty() && senhaTexto.length() >= 8;
    boolean senhasIguais = senhaTexto.equals(senhaTexto2); // Verifica se as senhas são iguais

    if (nomeValido && nomeApenasLetras && cpfValido && cpfApenasNumeros && cpfTamanhoValido && emailValido
        && senhaValida && senhasIguais) {
      // Todas as entradas são válidas, continue com a criação do Funcionário

      Usuario usuario = new Usuario();
      usuario.setNome(nomeTexto);
      usuario.setCpf(cpfTexto);
      usuario.setEmail(emailTexto);
      usuario.setSenha(senhaTexto);
      usuario.setAdmin(false);

      try {
        userBO.create(usuario);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Inserção Bem-sucedida");
        alert.setContentText("O funcionário foi inserido com sucesso!");

        // Crie um botão "OK" no alerta
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        // Exiba o alerta e aguarde o clique no botão "OK"
        alert.showAndWait();

        // Retorne para a TelaFuncionario1 após o sucesso
        Telas.TelaFuncionarios();
      } catch (UsuarioInvalido e) {
        displayAlert("Erro", e.getMessage());
      }
    } else {
      // Alguma entrada é inválida, exiba alertas de erro
      if (!nomeValido) {
        displayAlert("Nome inválido", "O nome não pode estar vazio.");
      } else if (!nomeApenasLetras) {
        displayAlert("Nome inválido", "O nome deve conter apenas letras.");
      }

      if (!cpfValido) {
        displayAlert("CPF inválido", "O CPF não pode estar vazio.");
      }

      if (!cpfApenasNumeros) {
        displayAlert("CPF inválido", "O CPF deve conter apenas números.");
      }

      if (!emailValido) {
        displayAlert("Email inválido", "O email não pode estar vazio e deve conter um @ e um .");
      }

      if (!senhaValida) {
        displayAlert("Senha inválida", "A senha não pode estar vazia e deve conter pelo menos 8 caracteres.");
      }

      if (!senhasIguais) {
        displayAlert("Senhas diferentes", "As senhas não coincidem. Verifique novamente.");
      }
    }
  }

  @FXML
  void cancelarFuncionario(ActionEvent event) throws Exception {
    Telas.TelaFuncionarios();
  }

  private void displayAlert(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erro");
    alert.setHeaderText(title);
    alert.setContentText(message);
    alert.showAndWait();
  }

  @FXML
  private Button inicio;

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
