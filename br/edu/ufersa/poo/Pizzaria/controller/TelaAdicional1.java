package br.edu.ufersa.poo.Pizzaria.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
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

public class TelaAdicional1 implements Initializable {
  AdicionalDao adicionalDao = new AdicionalDao();
  public static Telas telas = new Telas();

  @FXML
  private TableView<Adicional> table;
  @FXML
  private TableColumn<Adicional, Long> id;
  @FXML
  private TableColumn<Adicional, String> nome;
  @FXML
  private TableColumn<Adicional, Double> valor;
  @FXML
  private TableColumn<Adicional, Integer> quantidade;

  ObservableList<Adicional> list = FXCollections.observableArrayList();
  ObservableList<Adicional> allAdicionais = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    List<Adicional> adicionais = adicionalDao.listar();

    list.addAll(adicionais);

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
  private TextField searchTextField;

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
            String.valueOf(adicional.getValor()).toLowerCase().contains(searchTerm) ||
            String.valueOf(adicional.getQuantidade()).toLowerCase().contains(searchTerm) ||
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
  private Button funcionarios;

  @FXML
  void carregarFuncionarios(ActionEvent event) throws Exception {
    Telas.TelaFuncionarios();
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
}