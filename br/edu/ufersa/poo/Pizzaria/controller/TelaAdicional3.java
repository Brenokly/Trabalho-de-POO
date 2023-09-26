package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Exceptions.NomeInvalido;
import Exceptions.QuantidadeInvalida;
import Exceptions.ValorInvalido;
import br.edu.ufersa.poo.Pizzaria.model.bo.AdicionalBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

public class TelaAdicional3 extends Dialog<Adicional> {
  private Adicional adicional = new Adicional();

  @FXML
  private Label AdicionalExistente;

  @FXML
  private Button excluir;

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
  private Button cancelar;

  @FXML
  void ExcluirAdicional(ActionEvent event) {
    if (adicional != null) {
      AdicionalBO adicionalBo = new AdicionalBO();
      try {
        adicionalBo.deletar(adicional);
        Telas.TelaAdicional();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @FXML
  void SalvarAdicional(ActionEvent event) {
    if (adicional != null) {
      // Obtenha os valores dos campos de texto
      String novoNome = nome.getText();
      double novoPreco = Double.parseDouble(preco.getText());
      int novaQuantidade = Integer.parseInt(quantidade.getText());

      // Modifique o objeto Adicional com os novos valores
      try {
        adicional.setNome(novoNome);
        adicional.setValor(novoPreco);
        adicional.setQuantidade(novaQuantidade);
      } catch (NomeInvalido e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Nome inválido");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
      } catch (ValorInvalido e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Valor inválido");
        alert.setContentText("O valor deve ser um número decimal maior que 0.0");
        alert.showAndWait();
      } catch (QuantidadeInvalida e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Quantidade inválida");
        alert.setContentText("A quantidade deve ser um número inteiro maior que 0");
        alert.showAndWait();
      } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro ao carregar adicional");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
      }

      AdicionalBO adicionalBo = new AdicionalBO();
      try {
        adicionalBo.update(adicional);
        Telas.TelaAdicional();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @FXML
  void Cancelar(ActionEvent event) throws Exception {
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

  public void setAdicional(Adicional adicional) {
    try {
      this.adicional.setNome(adicional.getNome());
      this.adicional.setQuantidade(adicional.getQuantidade());
      this.adicional.setId(adicional.getId());
      this.adicional.setValor(adicional.getValor());
      nome.setText(adicional.getNome());
      preco.setText(String.valueOf(adicional.getValor()));
      quantidade.setText(String.valueOf(adicional.getQuantidade()));
    } catch (NomeInvalido e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText("Nome inválido");
      alert.setContentText(e.getMessage());
      alert.showAndWait();
    } catch (ValorInvalido e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText("Valor inválido");
      alert.setContentText("O valor deve ser um número decimal maior que 0.0");
      alert.showAndWait();
    } catch (QuantidadeInvalida e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText("Quantidade inválida");
      alert.setContentText("A quantidade deve ser um número inteiro maior que 0");
      alert.showAndWait();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText("Erro ao carregar adicional");
      alert.setContentText(e.getMessage());
      alert.showAndWait();
    }
  }
}
