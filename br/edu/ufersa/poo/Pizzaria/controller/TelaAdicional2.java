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

    String nomeTexto = nome.getText();
    double precoDouble;
    int quantidadeInt;

    try {
      if (nomeTexto.isEmpty()) {
        nome.setStyle("-fx-text-fill: red;");
      } else {
        nome.setStyle("-fx-text-fill: black;");
      }

      precoDouble = Double.parseDouble(preco.getText());
      preco.setStyle("-fx-text-fill: black;");

      quantidadeInt = Integer.parseInt(quantidade.getText());
      quantidade.setStyle("-fx-text-fill: black;");

      Adicional adicional = new Adicional(nomeTexto, precoDouble, quantidadeInt);
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
    } catch (NomeInvalido e) {
      quantidade.setStyle("-fx-text-fill: red;");
    } catch (ValorInvalido e) {
      preco.setStyle("-fx-text-fill: red;");
    } catch (QuantidadeInvalida e) {
      quantidade.setStyle("-fx-text-fill: red;");
    } catch (Exception e) {
      AdicionalExistente.setVisible(true);
    }
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
