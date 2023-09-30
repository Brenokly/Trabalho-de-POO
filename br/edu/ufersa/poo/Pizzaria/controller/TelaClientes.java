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
import br.edu.ufersa.poo.Pizzaria.dao.ClienteDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TelaClientes implements Initializable {
    private ClienteDao clienteDao = new ClienteDao();

    @FXML
    private TableView<Cliente> tableClientes;

    @FXML
    private TableColumn<Cliente, Long> id; // Coluna para o ID, se necessário
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colCpf;
    @FXML
    private TableColumn<Cliente, String> colEndereco;
    // Outras colunas, se necessário

    ObservableList<Cliente> list = FXCollections.observableArrayList();
    ObservableList<Cliente> allClientes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar as colunas da tabela
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        // Obter os dados dos funcionários do banco de dados
        List<Cliente> clientes = clienteDao.listar();

        list.addAll(clientes);

        // Preencher a tabela com os dados
        tableClientes.setItems(list);

        // Adicionar os dados originais à lista allUsuarios
        allClientes.addAll(clientes);
    }

    @FXML
    private TextField searchTextField;

    @FXML
    private void onSearchKeyReleased(KeyEvent event) {
        String searchTerm = searchTextField.getText().toLowerCase();

        if (searchTerm.isEmpty()) {
            // Campo de pesquisa vazio, exiba todos os dados originais
            tableClientes.setItems(allClientes);
        } else {
            // Realize a pesquisa e atualize a TableView com os resultados
            List<Cliente> resultados = new ArrayList<>();

            for (Cliente cliente : allClientes) {
                if (String.valueOf(cliente.getId()).contains(searchTerm)
                        || cliente.getNome().toLowerCase().contains(searchTerm)
                        || cliente.getCpf().toLowerCase().contains(searchTerm)) {
                    resultados.add(cliente);
                }
            }

            ObservableList<Cliente> resultadosObservable = FXCollections.observableArrayList();
            resultadosObservable.addAll(resultados);

            tableClientes.setItems(resultadosObservable);
        }
    }

    @FXML
    private Button adicionar;

    @FXML
    void CarregarTelaClientes2(ActionEvent event) {
      try {
        Telas.TelaClientes2();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @FXML
    private Button editar;

    @FXML
    void CarregarTelaClientes3(ActionEvent event) {
        Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();

        if (cliente != null) {
        try {
            // Chame o método TelaCliente3 e passe o Funcionario selecionado
            Telas.TelaClientes3(cliente);
            
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
