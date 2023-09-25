package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import br.edu.ufersa.poo.Pizzaria.dao.UserDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TelaFuncionarios implements Initializable {
    private UserDao userDao = new UserDao();

    @FXML private TableView<Usuario> tableFuncionarios;

    @FXML private TableColumn<Usuario, Long> id; // Coluna para o ID, se necessário
    @FXML private TableColumn<Usuario, String> colNome;
    @FXML private TableColumn<Usuario, String> colCpf;
    @FXML private TableColumn<Usuario, String> colEmail;
    @FXML private TableColumn<Usuario, Boolean> colAdministrator;
    // Outras colunas, se necessário

    ObservableList<Usuario> list = FXCollections.observableArrayList();
    ObservableList<Usuario> allUsuarios = FXCollections.observableArrayList();

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

        // Adicionar os dados originais à lista allUsuarios
        allUsuarios.addAll(funcionarios);
    }

    @FXML private TextField searchTextField;

    @FXML
    private void onSearchKeyReleased(KeyEvent event) {
        String searchTerm = searchTextField.getText();

        if (searchTerm.isEmpty()) {
            // Campo de pesquisa vazio, exiba todos os dados originais
            tableFuncionarios.setItems(allUsuarios);
        } else {
            // Realize a pesquisa e atualize a TableView com os resultados
            Usuario criteria = new Usuario();
            criteria.setNome(searchTerm);
            criteria.setEmail(searchTerm);
            
            try {
                Long idLong = Long.parseLong(searchTerm);
                criteria.setId(idLong);
            } catch (NumberFormatException e) {
                // Não é um número de ID válido, pode ignorar essa exceção
            }

            List<Usuario> resultados = userDao.buscarINE(criteria);
            ObservableList<Usuario> resultadosObservable = FXCollections.observableArrayList();

            if (resultados != null && !resultados.isEmpty()) {
                resultadosObservable.addAll(resultados);
            }

            tableFuncionarios.setItems(resultadosObservable);
        }
    }
}
