package br.edu.ufersa.poo.Pizzaria.controller;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.bo.AdicionalBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
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

public class TelaAdicional2 {
  @FXML
  private Label AdicionalExistente;

  @FXML
  private Button cancelar;

  @FXML
  private Button funcionarios;

  @FXML
  private Button inicio;

  @FXML
  private TextField nome;

  @FXML
  private TextField preco;

  @FXML
  private TextField quantidade;

  @FXML
  private Button sair;

  @FXML
  private Button salvar;

  @FXML
  void SalvarAdicional(ActionEvent event) throws Exception {
    AdicionalBO adicionalBo = new AdicionalBO();
    AdicionalExistente.setVisible(false);

    String nomeTexto = nome.getText();
    String precoTexto = preco.getText();
    String quantidadeTexto = quantidade.getText();

    boolean nomeValido = !nomeTexto.isEmpty();
    boolean nomeApenasLetras = nomeTexto.matches("^[\\p{L}\\s]*$");
    boolean precoValido = isValidDouble(precoTexto);
    boolean quantidadeValida = isValidInteger(quantidadeTexto);

    if (nomeValido && nomeApenasLetras && precoValido && quantidadeValida) {
      // Todas as entradas são válidas, continue com a criação do Adicional
      double precoDouble = Double.parseDouble(precoTexto);
      int quantidadeInt = Integer.parseInt(quantidadeTexto);

      Adicional adicional = new Adicional(nomeTexto, precoDouble, quantidadeInt);

      try {
        adicionalBo.create(adicional);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Inserção Bem-sucedida");
        alert.setContentText("O adicional foi inserido com sucesso!");

        // Crie um botão "OK" no alerta
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        // Exiba o alerta e aguarde o clique no botão "OK"
        alert.showAndWait();

        // Retorne para a TelaAdicional1 após o sucesso
        Telas.TelaAdicional();
      } catch (AdicionaJaExiste e) {
        AdicionalExistente.setVisible(true);
      } catch (QuantidadeInvalida e) {
        e.printStackTrace();
      } catch (ValorInvalido e) {
        e.printStackTrace();
      } catch (NomeInvalido e) {
        e.printStackTrace();
      }
    } else {
      // Alguma entrada é inválida, exiba alertas de erro
      if (!nomeValido) {
        displayAlert("Nome inválido", "O nome não pode estar vazio.");
      } else if (!nomeApenasLetras) {
        displayAlert("Nome inválido", "O nome deve conter apenas letras.");
      }

      if (!precoValido) {
        displayAlert("Preço inválido", "O preço deve ser um número decimal maior que 0.0.");
      }

      if (!quantidadeValida) {
        displayAlert("Quantidade inválida", "A quantidade deve ser um número inteiro maior ou igual a 0.");
      }
    }
  }

  private boolean isValidDouble(String value) {
    try {
      double parsedValue = Double.parseDouble(value);
      return parsedValue > 0.0;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean isValidInteger(String value) {
    try {
      int parsedValue = Integer.parseInt(value);
      return parsedValue >= 0;
    } catch (NumberFormatException e) {
      return false;
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
  void cancelarAdicional(ActionEvent event) throws Exception {
    Telas.TelaAdicional();
  }

  @FXML
  void carregarFuncionarios(ActionEvent event) throws Exception {
    Telas.TelaAdicional();
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
