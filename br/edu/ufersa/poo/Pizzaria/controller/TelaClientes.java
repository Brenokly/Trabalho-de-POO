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
import javafx.scene.input.MouseEvent;
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
    private Button clientes;

    @FXML
    private Button adicionar;

    @FXML
    private Button adicionais;

    @FXML
    private Button editar;

    @FXML
    private Button funcionarios;

    @FXML
    private Button inicio;

    @FXML
    private Button pedidos;

    @FXML
    private Button sair;

    @FXML
    private Button sabores;

    @FXML
    private TextField searchTextField;

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
    void ClienteClicked(MouseEvent event) {
    }

    @FXML
    void CarregarTelaClientes2(ActionEvent event) {
        try {
            Telas.TelaClientes2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
    void carregarPedidos(ActionEvent event) throws Exception {
        // Telas.TelaPedidos();
    }

    @FXML
    void carregarFuncionarios(ActionEvent event) throws Exception {
        Telas.TelaFuncionarios();
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
