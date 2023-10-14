package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.Optional;
import Exceptions.NomeInvalido;
import Exceptions.ValorInvalido;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import br.edu.ufersa.poo.Pizzaria.model.bo.TiposPizzasBO;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.scene.Node;

public class TelaSaboresEditar extends Dialog<TiposPizzas> {
  private TiposPizzas sabor = new TiposPizzas();

  private PseudoClass error = PseudoClass.getPseudoClass("error");

  @FXML private Button excluir;
  @FXML private Button funcionarios;
  @FXML private Button inicio;
  @FXML private TextField nome;
  @FXML private TextField valor_grande;
  @FXML private TextField valor_pequena;
  @FXML private Button sair;
  @FXML private Button salvar;
  @FXML private Button cancelar;

  @FXML
  void ExcluirSabor(ActionEvent event) {
    if (sabor != null) {
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirmação");
      alert.setHeaderText("Excluir Sabor");
      alert.setContentText("Tem certeza de que deseja excluir este sabor?");

      ButtonType buttonTypeSim = new ButtonType("Sim", ButtonData.OK_DONE);
      ButtonType buttonTypeNao = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
      alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      alert.initOwner(stage);
      alert.setX(330);
      alert.setY(400);

      Optional<ButtonType> result = alert.showAndWait();

      if (result.isPresent() && result.get() == buttonTypeSim) {
        TiposPizzasBO tiposPizzaBo = new TiposPizzasBO();
        try {
          tiposPizzaBo.deletar(sabor);
          Telas.TelaSabores();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  @FXML
  void SalvarSabor(ActionEvent event) {
    if (sabor != null) {
      String novoNome = nome.getText();
      double novoValorP = 0.0;
      double novoValorG = 0.0;

      try {
        novoValorG = Double.parseDouble(valor_grande.getText());
        novoValorP = Double.parseDouble(valor_pequena.getText());
      } catch (NumberFormatException e) {
        exibirMensagemDeErro("Valores inválidos", "Os valores devem ser números válidos.");
        return; // Não tente salvar o sabor se os valores forem inválidos
      }

      // Modifique o objeto Sabor com os novos valores
      try {
        sabor.setNome(novoNome);
        sabor.setValorGrande(novoValorG);
        sabor.setValorPequena(novoValorP);

        // Atualize o objeto no banco de dados
        TiposPizzasBO tiposPizzasBO = new TiposPizzasBO();
        tiposPizzasBO.update(sabor);

        // Redireciona para a tela desejada após o salvamento bem-sucedido
        Telas.TelaSabores();
      } catch (NomeInvalido e) {
        exibirMensagemDeErro("Nome inválido", e.getMessage());
      } catch (ValorInvalido e) {
        exibirMensagemDeErro("Valor inválido", "O valor deve ser um número decimal maior que 0.0");
      } catch (Exception e) {
        exibirMensagemDeErro("Erro ao carregar sabor", e.getMessage());
      }
    }
  }

  @FXML
  void onTextFieldContentChanged(KeyEvent event) {
    TextField sourceTextField = (TextField) event.getSource();
    
    sourceTextField.pseudoClassStateChanged(error, false);
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
  void cancelarSabor(ActionEvent event) throws Exception {
    Telas.TelaSabores();
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
    // Telas.TelaPedidos();
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

  public void setTiposPizzas(TiposPizzas tiposPizza) {
    try {
      this.sabor.setNome(tiposPizza.getNome());
      this.sabor.setValorGrande(tiposPizza.getValorGrande());
      this.sabor.setValorPequena(tiposPizza.getValorPequena());
      this.sabor.setId(tiposPizza.getId());
      nome.setText(tiposPizza.getNome());
      valor_grande.setText(String.valueOf(tiposPizza.getValorGrande()));
      valor_pequena.setText(String.valueOf(tiposPizza.getValorPequena()));
    } catch (NomeInvalido e) {
      exibirMensagemDeErro("Nome inválido", e.getMessage());
    } catch (ValorInvalido e) {
      exibirMensagemDeErro("Valor inválido", "O valor deve ser um número decimal maior que 0.0");
    } catch (Exception e) {
      exibirMensagemDeErro("Erro ao carregar sabor", e.getMessage());
    }
  }
}