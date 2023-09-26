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
      String novoNome = nome.getText();
      double novoPreco = 0.0; 
      int novaQuantidade = 0; 

      try {
        novoPreco = Double.parseDouble(preco.getText());
        novaQuantidade = Integer.parseInt(quantidade.getText());
      } catch (NumberFormatException e) {
        // Exiba uma mensagem de erro para o usuário
        exibirMensagemDeErro("Valores inválidos", "Os valores devem ser números válidos.");
        return; // Saia do método para manter a tela aberta
      }

      // Modifique o objeto Adicional com os novos valores
      try {
        adicional.setNome(novoNome);
        adicional.setValor(novoPreco);
        adicional.setQuantidade(novaQuantidade);

        // Atualize o objeto no banco de dados
        AdicionalBO adicionalBo = new AdicionalBO();
        adicionalBo.update(adicional);

        // Redirecione para a tela desejada após o salvamento bem-sucedido
        Telas.TelaAdicional();
      } catch (NomeInvalido e) {
        exibirMensagemDeErro("Nome inválido", e.getMessage());
      } catch (ValorInvalido e) {
        exibirMensagemDeErro("Valor inválido", "O valor deve ser um número decimal maior que 0.0");
      } catch (QuantidadeInvalida e) {
        exibirMensagemDeErro("Quantidade inválida", "A quantidade deve ser um número inteiro maior que 0");
      } catch (Exception e) {
        exibirMensagemDeErro("Erro ao carregar adicional", e.getMessage());
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
