package br.edu.ufersa.poo.Pizzaria.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import br.edu.ufersa.poo.Pizzaria.dao.TiposPizzasDao;
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

public class TelaSabores implements Initializable {
  TiposPizzasDao tiposPizzasDao = new TiposPizzasDao();
  public static Telas telas = new Telas();

  @FXML
  private TableView<TiposPizzas> table;
  @FXML
  private TableColumn<TiposPizzas, Long> id;
  @FXML
  private TableColumn<TiposPizzas, String> nome;
  @FXML
  private TableColumn<TiposPizzas, Double> valorPequena;
  @FXML
  private TableColumn<TiposPizzas, Double> valorGrande;

  ObservableList<TiposPizzas> list = FXCollections.observableArrayList();
  ObservableList<TiposPizzas> allSabores = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    List<TiposPizzas> sabores = tiposPizzasDao.listar();

    list.addAll(sabores);

    // Configurar as colunas da TableView
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    valorPequena.setCellValueFactory(new PropertyValueFactory<>("valorPequena"));
    valorGrande.setCellValueFactory(new PropertyValueFactory<>("valorGrande"));

    // Adicionar os dados à TableView
    table.setItems(list);

    // Adicionar os dados originais à lista allSabores
    allSabores.addAll(sabores);
  }

  @FXML
  private TextField searchTextField;

  @FXML
  private void onSearchKeyReleased(KeyEvent event) {
    String searchTerm = searchTextField.getText().toLowerCase();

    if (searchTerm.isEmpty()) {
      // Campo de pesquisa vazio, exiba todos os dados originais
      table.setItems(allSabores);
    } else {
      // Realize a pesquisa e atualize a TableView com os resultados
      List<TiposPizzas> resultados = new ArrayList<>();

      for (TiposPizzas tiposPizzas : allSabores) {
        if (tiposPizzas.getNome().toLowerCase().contains(searchTerm) ||
            String.valueOf(tiposPizzas.getId()).toLowerCase().contains(searchTerm)) {
          resultados.add(tiposPizzas);
        }
      }

      ObservableList<TiposPizzas> resultadosObservable = FXCollections.observableArrayList();
      resultadosObservable.addAll(resultados);

      table.setItems(resultadosObservable);
    }
  }

  @FXML
  private Button funcionarios;

  @FXML
  void CarregarClientes(ActionEvent event) throws Exception {
    Telas.TelaClientes();
  }

  @FXML
  void carregarFuncionarios(ActionEvent event) throws Exception {
    Telas.TelaFuncionarios();
  }

  @FXML
  void CarregarPedidos(ActionEvent event) throws Exception {
    // Telas.TelaPedidos();
  }

  @FXML
  private Button inicio;

  @FXML
  void carregarInicio(ActionEvent event) throws Exception {
    Telas.TelaInicial();
  }

  @FXML
  private Button sair;

  @FXML
  void carregarLogin(ActionEvent event) throws Exception {
    Telas.TelaLogin();
  }

  @FXML
  private Button adicionais;

  @FXML
  void carregarAdicionais(ActionEvent event) throws Exception {
    Telas.TelaAdicional();
  }
}
