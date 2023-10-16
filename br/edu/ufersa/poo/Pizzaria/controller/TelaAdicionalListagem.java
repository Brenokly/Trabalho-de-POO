package br.edu.ufersa.poo.Pizzaria.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.ufersa.poo.Pizzaria.model.bo.AdicionalBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class TelaAdicionalListagem implements Initializable {
  AdicionalBO adicionalBO = new AdicionalBO();

  @FXML private Button adicionar, editar;
  @FXML private Button inicio, clientes, pedidos, sabores, adicionais, funcionarios, sair;
  @FXML private TextField searchTextField;
  @FXML private TableView<Adicional> table;
  @FXML private TableColumn<Adicional, Long> id;
  @FXML private TableColumn<Adicional, String> nome;
  @FXML private TableColumn<Adicional, Integer> quantidade;
  @FXML private TableColumn<Adicional, Double> valor;

  ObservableList<Adicional> list = FXCollections.observableArrayList();
  ObservableList<Adicional> allAdicionais = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    List<Adicional> adicionais = null;
    try {
      adicionais = adicionalBO.buscarTodos();
      list.addAll(adicionais);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Configurar as colunas da TableView
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

    // Adicionar os dados à TableView
    table.setItems(list);

    // Adicionar os dados originais à lista allAdicionais
    allAdicionais.addAll(adicionais);
  }

  @FXML
  private void onSearchKeyReleased(KeyEvent event) {
    String searchTerm = searchTextField.getText().toLowerCase();

    if (searchTerm.isEmpty()) {
      // Campo de pesquisa vazio, exiba todos os dados originais
      table.setItems(allAdicionais);
    } else {
      // Realize a pesquisa e atualize a TableView com os resultados
      List<Adicional> resultados = new ArrayList<>();

      for (Adicional adicional : allAdicionais) {
        if (adicional.getNome().toLowerCase().contains(searchTerm) ||
            String.valueOf(adicional.getId()).toLowerCase().contains(searchTerm)) {
          resultados.add(adicional);
        }
      }

      ObservableList<Adicional> resultadosObservable = FXCollections.observableArrayList();
      resultadosObservable.addAll(resultados);

      table.setItems(resultadosObservable);
    }
  }

  @FXML
  void AddAdicional(ActionEvent event) {
    try {
      Telas.TelaAdicionalCadastro();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void AdicionalClicked(MouseEvent event) {

  }

  @FXML
  void EditarAdicional(ActionEvent event) {
    Adicional adicional = table.getSelectionModel().getSelectedItem();

    if (adicional != null) {
      try {
        // Chame o método TelaAdicional3 e passe o Adicional selecionado
        Telas.TelaAdicionalEditar(adicional);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
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
  void carregarAdicionais(ActionEvent event) throws Exception {
    Telas.TelaAdicionalListagem();
  }

  @FXML
  void CadastrarAdicional(ActionEvent event) throws Exception {
    Telas.TelaAdicionalCadastro();
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
}
