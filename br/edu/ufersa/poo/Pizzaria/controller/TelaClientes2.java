package br.edu.ufersa.poo.Pizzaria.controller;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.bo.ClienteBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaClientes2 {

  @FXML
  private Button salvar;

  @FXML
  private Button cancelar;

  @FXML
  private TextField nome;

  @FXML
  private TextField cpf;

  @FXML
  private TextField endereco;

  @FXML
  private TextField senha;

  @FXML
  private Label clientesExistente;

  @FXML
  void salvarCliente(ActionEvent event) throws Exception {
    ClienteBO ClienteBO = new ClienteBO();

    String nomeTexto = nome.getText();
    String cpfTexto = cpf.getText();
    String enderecoTexto = endereco.getText();

    boolean nomeValido = !nomeTexto.isEmpty();
    boolean nomeApenasLetras = nomeTexto.matches("^[\\p{L}\\s]*$");

    boolean cpfValido = !cpfTexto.isEmpty();
    boolean cpfApenasNumeros = cpfTexto.matches("^[0-9]*$");
    boolean cpfTamanhoValido = cpfTexto.length() == 11 || cpfTexto.length() == 14;

    if (nomeValido && nomeApenasLetras && cpfValido && cpfApenasNumeros && cpfTamanhoValido) {
      // Todas as entradas são válidas, continue com a criação do Funcionario

      Cliente cliente = new Cliente();
      cliente.setId(Long.valueOf(ClienteBO.buscarTodos().size() + 1));
      cliente.setNome(nomeTexto);

      cliente.setCpf(cpfTexto);
      cliente.setEndereco(enderecoTexto);

      try {
        ClienteBO.create(cliente);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Inserção Bem-sucedida");
        alert.setContentText("O cliente foi inserido com sucesso!");

        // Crie um botão "OK" no alerta
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        // Exiba o alerta e aguarde o clique no botão "OK"
        alert.showAndWait();

        // Retorne para a TelaFuncionario1 após o sucesso
        Telas.TelaClientes();
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
        displayAlert("Cpf inválido", "O cpf não pode estar vazio. ");
      }

      if (!cpfApenasNumeros) {
        displayAlert("Cpf inválido", "O cpf deve conter apenas números.");
      }
    }
  }

  private void displayAlert(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erro");
    alert.setHeaderText(title);
    alert.setContentText(message);
    alert.showAndWait();
  }

  @FXML
  void cancelarClientes(ActionEvent event) throws Exception {
    Telas.TelaClientes();
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