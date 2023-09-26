package br.edu.ufersa.poo.Pizzaria.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import br.edu.ufersa.poo.Pizzaria.dao.ClienteDao;
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

public class TelaClientes implements Initializable {
  ClienteDao ClienteDao = new ClienteDao();
  public static Telas telas = new Telas();

  @FXML
  private TableView<Cliente> table;
  @FXML
  private TableColumn<Cliente, Long> id;
  @FXML
  private TableColumn<Cliente, String> nome;
  @FXML
  private TableColumn<Cliente, Double> cpf;
  @FXML
  private TableColumn<Cliente, Integer> endereco;

  ObservableList<Cliente> list = FXCollections.observableArrayList();
  ObservableList<Cliente> allSabores = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    List<Cliente> sabores = ClienteDao.listar();

    list.addAll(sabores);

    // Configurar as colunas da TableView
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

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
      List<Cliente> resultados = new ArrayList<>();

      for (Cliente cliente : allSabores) {
        if (cliente.getNome().toLowerCase().contains(searchTerm) ||
            cliente.getCpf().toLowerCase().contains(searchTerm) ||
            cliente.getEndereco().toLowerCase().contains(searchTerm) ||
            String.valueOf(cliente.getId()).toLowerCase().contains(searchTerm)) {
          resultados.add(cliente);
        }
      }

      ObservableList<Cliente> resultadosObservable = FXCollections.observableArrayList();
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

  @FXML
  private Button adicionais;

  @FXML
  void carregarAdicionais(ActionEvent event) throws Exception {
    Telas.TelaAdicional();
  }

  @FXML
  private Button sabores;

  @FXML
  void carregarSabores(ActionEvent event) throws Exception {
    //Telas.TelaSabores();
  }
}
