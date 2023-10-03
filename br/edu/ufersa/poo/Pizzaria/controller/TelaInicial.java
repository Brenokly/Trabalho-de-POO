package br.edu.ufersa.poo.Pizzaria.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.ufersa.poo.Pizzaria.model.bo.PedidoBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import br.edu.ufersa.poo.Pizzaria.model.entity.Pedido;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.beans.property.SimpleStringProperty;
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

public class TelaInicial implements Initializable {
    private PedidoBO pedidoBO = new PedidoBO();

    @FXML
    private Button adicionais;

    @FXML
    private TableColumn<Pedido, String> cliente;

    @FXML
    private TableColumn<Pedido, String> data;

    @FXML
    private Button editar;

    @FXML
    private TableColumn<Pedido, String> estado;

    @FXML
    private Button funcionarios;

    @FXML
    private TableColumn<Pedido, Long> id;

    @FXML
    private TableColumn<Pedido, Double> valor;

    @FXML
    private Button sair;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<Pedido> table;

    ObservableList<Pedido> list = FXCollections.observableArrayList();
    ObservableList<Pedido> allPedidos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Pedido> pedido = null;
        try {
            pedido = pedidoBO.buscarTodos();
            List<Pedido> pedido2 = new ArrayList<>();
            for (Pedido p : pedido) {
                if (p.getEstado().getDescricao().equals("pendente")) {
                    p.tostring();
                    pedido2.add(p);
                }
            }

            list.addAll(pedido2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar as colunas da TableView
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cliente.setCellValueFactory(cellData -> {
            // cellData.getValue() retorna um objeto Pedido
            // A partir do objeto Pedido, você pode acessar o cliente e, em seguida, o nome
            Cliente clienteDoPedido = cellData.getValue().getCliente();
            String nomeDoCliente = clienteDoPedido != null ? clienteDoPedido.getNome() : "";
            return new SimpleStringProperty(nomeDoCliente);
        });
        valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));

        // Adicionar os dados à TableView
        table.setItems(list);

        // Adicionar os dados originais à lista allAdicionais
        allPedidos.addAll(pedido);
    }

    @FXML
    void AdicionalClicked(MouseEvent event) {

    }

    @FXML
    void EditarPedido(ActionEvent event) {
        Pedido pedido = table.getSelectionModel().getSelectedItem();

        if (pedido != null) {
            try {
                Telas.TelaInicial2(pedido);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void CarregarPedidos(ActionEvent event) throws Exception {
        // Telas.TelaPedidos();
    }

    @FXML
    void CarregarClientes(ActionEvent event) throws Exception {
        Telas.TelaClientes();
    }

    @FXML
    void CarregarSabores(ActionEvent event) throws Exception {
        Telas.TelaSabores();
    }

    @FXML
    void carregarAdicionais(ActionEvent event) throws Exception {
        Telas.TelaAdicional();
    }

    @FXML
    void carregarFuncionarios(ActionEvent event) throws Exception {
        Telas.TelaAdicional();
    }

    @FXML
    void carregarLogin(ActionEvent event) throws Exception {
        Telas.TelaLogin();
    }

    @FXML
    void onSearchKeyReleased(KeyEvent event) {
        String searchTerm = searchTextField.getText().toLowerCase();

        if (searchTerm.isEmpty()) {
            // Campo de pesquisa vazio, exiba todos os dados originais
            table.setItems(allPedidos);
        } else {
            // Realize a pesquisa e atualize a TableView com os resultados
            List<Pedido> resultados = new ArrayList<>();

            for (Pedido pedido : allPedidos) { // buscar por cliente, por pizza (nome do sabor) e por estado
                if (pedido.getCliente().getNome().toLowerCase().contains(searchTerm) ||
                        pedido.getEstado().getDescricao().toLowerCase().contains(searchTerm) ||
                        pedido.getItensPedido().get(0).getPizza().getNome().toLowerCase().contains(searchTerm)) {
                    resultados.add(pedido);
                }
            }

            ObservableList<Pedido> resultadosObservable = FXCollections.observableArrayList();
            resultadosObservable.addAll(resultados);

            table.setItems(resultadosObservable);
        }
    }

}
