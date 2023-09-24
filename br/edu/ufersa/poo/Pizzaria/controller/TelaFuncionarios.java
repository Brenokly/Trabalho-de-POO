package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import br.edu.ufersa.poo.Pizzaria.dao.UserDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TelaFuncionarios implements Initializable {

    @FXML
    private TableView<Usuario> tableFuncionarios;

    @FXML
    private TableColumn<Usuario, Long> id; // Coluna para o ID, se necessário
    @FXML
    private TableColumn<Usuario, String> colNome;
    @FXML
    private TableColumn<Usuario, String> colCpf;
    @FXML
    private TableColumn<Usuario, String> colEmail;
    @FXML
    private TableColumn<Usuario, Boolean> colAdministrator;
    // Outras colunas, se necessário

    ObservableList<Usuario> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar as colunas da tabela
        id.setCellValueFactory(new PropertyValueFactory<>("id")); // Se houver uma coluna de ID
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdministrator.setCellValueFactory(new PropertyValueFactory<>("admin"));
        // Configurar outras colunas, se necessário

        // Obter os dados dos funcionários do banco de dados
        UserDao userDao = new UserDao();
        List<Usuario> funcionarios = userDao.listar();

        list.addAll(funcionarios);

        // Preencher a tabela com os dados
        tableFuncionarios.setItems(list);
    }

    public void Pesquisar() {
        // Implemente a lógica de pesquisa aqui, se necessário
    }
}
