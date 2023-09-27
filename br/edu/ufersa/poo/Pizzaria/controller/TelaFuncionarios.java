package br.edu.ufersa.poo.Pizzaria.controller;

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
import br.edu.ufersa.poo.Pizzaria.classes.Funcionarios;
import br.edu.ufersa.poo.Pizzaria.dao.UserDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TelaFuncionarios implements Initializable {
    private UserDao userDao = new UserDao();

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
    ObservableList<Usuario> allUsuarios = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar as colunas da tabela
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdministrator.setCellValueFactory(new PropertyValueFactory<>("admin"));

        // Obter os dados dos funcionários do banco de dados
        List<Usuario> funcionarios = userDao.listar();

        list.addAll(funcionarios);

        // Preencher a tabela com os dados
        tableFuncionarios.setItems(list);

        // Adicionar os dados originais à lista allUsuarios
        allUsuarios.addAll(funcionarios);
    }

    @FXML
    private TextField searchTextField;

    @FXML
    private void onSearchKeyReleased(KeyEvent event) {
        String searchTerm = searchTextField.getText().toLowerCase();

        if (searchTerm.isEmpty()) {
            // Campo de pesquisa vazio, exiba todos os dados originais
            tableFuncionarios.setItems(allUsuarios);
        } else {
            // Realize a pesquisa e atualize a TableView com os resultados
            List<Usuario> resultados = new ArrayList<>();

            for (Usuario usuario : allUsuarios) {
                if (String.valueOf(usuario.getId()).contains(searchTerm)
                        || usuario.getNome().toLowerCase().contains(searchTerm)
                        || usuario.getEmail().toLowerCase().contains(searchTerm)) {
                    resultados.add(usuario);
                }
            }

            ObservableList<Usuario> resultadosObservable = FXCollections.observableArrayList();
            resultadosObservable.addAll(resultados);

            tableFuncionarios.setItems(resultadosObservable);
        }
    }

    @FXML
    private Button adicionar;

    @FXML
    void CarregarTelaFuncionarios2(ActionEvent event) {
      try {
        Telas.TelaFuncionarios2();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @FXML
    private Button editar;

    @FXML
    void CarregarTelaFuncionarios3(ActionEvent event) {
        Usuario funcionario = tableFuncionarios.getSelectionModel().getSelectedItem();

        if (funcionario != null) {
        try {
            // Chame o método TelaFuncionario3 e passe o Funcionario selecionado
            Telas.TelaFuncionarios3(funcionario);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }

    @FXML
    private Button adicionais;

    @FXML
    void carregarAdicionais(ActionEvent event) throws Exception {
        Telas.TelaAdicional();
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
