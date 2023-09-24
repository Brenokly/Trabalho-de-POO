package br.edu.ufersa.poo.Pizzaria.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaAdicional1 implements Initializable {
  public static Telas telas = new Telas();

  @FXML private TableView<Adicional> table;
  @FXML private TableColumn<Adicional, Long> id;
  @FXML private TableColumn<Adicional, String> nome;
  @FXML private TableColumn<Adicional, Double> valor;
  @FXML private TableColumn<Adicional, Integer> quantidade;

  ObservableList<Adicional> list = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    AdicionalDao adicionalDao = new AdicionalDao();

    List<Adicional> adicionais = adicionalDao.listar();

    list.addAll(adicionais);

    // Configurar as colunas da TableView
    id.setCellValueFactory(new PropertyValueFactory<>("id")); 
    nome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
    valor.setCellValueFactory(new PropertyValueFactory<>("valor")); 
    quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade")); 

    // Adicionar os dados à TableView
    table.setItems(list);
  }

}
