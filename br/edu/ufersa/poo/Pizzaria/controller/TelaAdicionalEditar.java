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
import Exceptions.QuantidadeInvalida;
import Exceptions.ValorInvalido;
import br.edu.ufersa.poo.Pizzaria.model.bo.AdicionalBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.scene.Node;

public class TelaAdicionalEditar extends Dialog<Adicional> {
  private Adicional adicional = new Adicional();

  private PseudoClass error = PseudoClass.getPseudoClass("error");

  @FXML private Button excluir;
  @FXML private Button funcionarios;
  @FXML private Button inicio;
  @FXML private TextField nome;
  @FXML private TextField preco;
  @FXML private TextField quantidade;
  @FXML private Button sair;
  @FXML private Button salvar;
  @FXML private Button cancelar;

  @FXML
  void ExcluirAdicional(ActionEvent event) {
    if (adicional != null) {
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirmação");
      alert.setHeaderText("Excluir Adicional");
      alert.setContentText("Tem certeza de que deseja excluir este adicional?");

      ButtonType buttonTypeSim = new ButtonType("Sim", ButtonData.OK_DONE);
      ButtonType buttonTypeNao = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
      alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      alert.initOwner(stage);
      alert.setX(330);
      alert.setY(400);

      Optional<ButtonType> result = alert.showAndWait();

      if (result.isPresent() && result.get() == buttonTypeSim) {
        AdicionalBO adicionalBo = new AdicionalBO();
        try {
          adicionalBo.deletar(adicional);
          Telas.TelaAdicionalListagem();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  @FXML
  void SalvarAdicional(ActionEvent event) {
    if (adicional != null) {
      String novoNome = nome.getText();
      double novoPreco = 0.0;
      int novaQuantidade = 0;

      try {
        novoPreco = Double.parseDouble(preco.getText());
        novaQuantidade = Integer.parseInt(quantidade.getText());
      } catch (NumberFormatException e) {
        exibirMensagemDeErro("Valores inválidos", "Os valores devem ser números válidos.");
        return; // Não tente salvar o adicional se os valores forem inválidos
      }

      // Modifique o objeto Adicional com os novos valores
      try {
        adicional.setNome(novoNome);
        adicional.setValor(novoPreco);
        adicional.setQuantidade(novaQuantidade);

        // Atualize o objeto no banco de dados
        AdicionalBO adicionalBo = new AdicionalBO();
        adicionalBo.update(adicional);

        // Redireciona para a tela desejada após o salvamento bem-sucedido
        Telas.TelaAdicionalListagem();
      } catch (NomeInvalido e) {
        exibirMensagemDeErro("Nome inválido", e.getMessage());
      } catch (ValorInvalido e) {
        exibirMensagemDeErro("Preço inválido", "O valor deve ser um número decimal maior que 0.0");
      } catch (QuantidadeInvalida e) {
        exibirMensagemDeErro("Quantidade inválida", "A quantidade deve ser um número decimal maior ou igual a 0");
      } catch (Exception e) {
        exibirMensagemDeErro("Erro ao carregar adicional", e.getMessage());
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
  void cancelarAdicional(ActionEvent event) throws Exception {
    Telas.TelaAdicionalListagem();
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
      exibirMensagemDeErro("Nome inválido", e.getMessage());
    } catch (ValorInvalido e) {
      exibirMensagemDeErro("Valor inválido", "O valor deve ser um número decimal maior que 0.0");
    } catch (QuantidadeInvalida e) {
      exibirMensagemDeErro("Quantidade inválida", "A quantidade deve ser um número decimal maior ou igual a 0.0");
    } catch (Exception e) {
      exibirMensagemDeErro("Erro ao carregar adicional", e.getMessage());
    }
  }
}