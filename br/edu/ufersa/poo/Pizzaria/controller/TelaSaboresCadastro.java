package br.edu.ufersa.poo.Pizzaria.controller;

import Exceptions.*;
import br.edu.ufersa.poo.Pizzaria.model.bo.TiposPizzasBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TelaSaboresCadastro {
  private PseudoClass error = PseudoClass.getPseudoClass("error");

  @FXML private Label SaborExistente;
  @FXML private TextField nome, valor_grande, valor_pequena;
  @FXML private Button inicio, clientes, pedidos, sabores, adicionais, funcionarios, sair;
  @FXML private Button salvar, cancelar;

  @FXML
  void SalvarSabor(ActionEvent event) throws Exception {
    TiposPizzasBO tiposPizzasBo = new TiposPizzasBO();
    SaborExistente.setVisible(false);

    String nomeTexto = nome.getText();
    String valor_grandeTexto = valor_grande.getText();
    String valor_pequenaTexto = valor_pequena.getText();

    boolean nomeValido = !nomeTexto.isEmpty();
    boolean nomeApenasLetras = nomeTexto.matches("^[\\p{L}\\s]*$");
    boolean valor_grandeValido = isValidDouble(valor_grandeTexto);
    boolean valor_pequenaValido = isValidDouble(valor_pequenaTexto);

    if (nomeValido && nomeApenasLetras && valor_grandeValido && valor_pequenaValido) {
      // Todas as entradas são válidas, continue com a criação do Sabor
      double valor_grandeDouble = Double.parseDouble(valor_grandeTexto);
      double valor_pequenaDouble = Double.parseDouble(valor_pequenaTexto);

      TiposPizzas tiposPizzas = new TiposPizzas(nomeTexto, valor_grandeDouble, valor_pequenaDouble);

      try {
        tiposPizzasBo.create(tiposPizzas);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText("Inserção Bem-sucedida");
        alert.setContentText("O sabor foi inserido com sucesso!");

        // Crie um botão "OK" no alerta
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        // Exiba o alerta e aguarde o clique no botão "OK"
        alert.showAndWait();

        // Retorne para a TelaSabores após o sucesso
        Telas.TelaSabores();
      } catch (SaborJaExiste e) {
        nome.pseudoClassStateChanged(error, true);
        SaborExistente.setVisible(true);
      } catch (ValorInvalido e) {
        valor_grande.pseudoClassStateChanged(error, true);
        valor_pequena.pseudoClassStateChanged(error, true);
        displayAlert("Valor inválido", "O valor deve ser um número decimal maior que 0.0");
      } catch (NomeInvalido e) {
        nome.pseudoClassStateChanged(error, true);
        displayAlert("Nome inválido", e.getMessage());
      }
    } else {
      // Alguma entrada é inválida, exiba alertas de erro
      if (!nomeValido) {
        nome.pseudoClassStateChanged(error, true);
        displayAlert("Nome inválido", "O nome não pode estar vazio.");
      } else if (!nomeApenasLetras) {
        nome.pseudoClassStateChanged(error, true);
        displayAlert("Nome inválido", "O nome deve conter apenas letras.");
      }

      if (!valor_grandeValido) {
        valor_grande.pseudoClassStateChanged(error, true);
        displayAlert("Valor inválido", "O valor da pizza grande deve ser um número decimal maior que 0.0.");
      }

      if (!valor_pequenaValido) {
        valor_pequena.pseudoClassStateChanged(error, true);
        displayAlert("Valor inválido", "O valor da pizza pequena deve ser um número decimal maior que 0.0.");
      }
    }
  }

  @FXML
  void cancelarSabor(ActionEvent event) throws Exception {
    Telas.TelaSabores();
  }

  @FXML
  void onTextFieldContentChanged(KeyEvent event) {
    TextField sourceTextField = (TextField) event.getSource();

    if (sourceTextField == nome) { SaborExistente.setVisible(false); }

    sourceTextField.pseudoClassStateChanged(error, false);
  }

  private boolean isValidDouble(String value) {
    try {
      double parsedValue = Double.parseDouble(value);
      return parsedValue > 0.0;
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
  void carregarClientes(ActionEvent event) throws Exception {
    Telas.TelaClientes();
  }

  @FXML
  void carregarSabores(ActionEvent event) throws Exception {
    Telas.TelaSabores();
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

  @FXML
  void carregarAdicionais(ActionEvent event) throws Exception {
    Telas.TelaAdicionalListagem();
  }

  @FXML
  void cancelarSabores(ActionEvent event) throws Exception {
    Telas.TelaSabores();
  }
}
