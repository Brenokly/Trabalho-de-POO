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

public class TelaInicialListagem implements Initializable {
    private PedidoBO pedidoBO = new PedidoBO();

    @FXML private TableView<Pedido> table;
    @FXML private TableColumn<Pedido, Long> id;
    @FXML private TableColumn<Pedido, String> cliente;
    @FXML private TableColumn<Pedido, String> data;
    @FXML private TableColumn<Pedido, String> estado;
    @FXML private TableColumn<Pedido, Double> valor;
    @FXML private TextField searchTextField;
    @FXML private Button inicio, clientes, pedidos, sabores, adicionais, funcionarios, sair;
    @FXML private Button editar;

    ObservableList<Pedido> list = FXCollections.observableArrayList();
    ObservableList<Pedido> allPedidos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Pedido> pedido = null;
        List<Pedido> pedido2 = new ArrayList<>();
        try {
            pedido = pedidoBO.buscarTodos();
            if (pedido != null) {
                for (Pedido p : pedido) {
                    if (p.getEstado().getDescricao().equals("Pendente") || p.getEstado().getDescricao().equals("Preparando")
                           && !p.getItensPedido().isEmpty()) {
                        pedido2.add(p);
                    }
                }

                list.addAll(pedido2);
            }
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

        if (list != null) {
            // Adicionar os dados à TableView
            table.setItems(list);

            // Adicionar os dados originais à lista allAdicionais
            allPedidos.addAll(pedido2);
        }
    }

    @FXML
    void AdicionalClicked(MouseEvent event) {

    }

    @FXML
    void EditarPedido(ActionEvent event) {
        Pedido pedido = table.getSelectionModel().getSelectedItem();

        if (pedido != null) {
            try {
                Telas.TelaPedidosEditar(pedido);
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
        Telas.TelaPedidos();
    }

    @FXML
    void carregarFuncionarios(ActionEvent event) throws Exception {
        Telas.TelaFuncionariosListagem();
    }

    @FXML
    void carregarInicio(ActionEvent event) throws Exception {
        Telas.TelaInicial();
    }

    @FXML
    void carregarLogin(ActionEvent event) throws Exception {
        Telas.TelaLogin();
    }

    @FXML
    void onSearchKeyReleased(KeyEvent event) {
        String searchTerm = searchTextField.getText().toLowerCase();

        if (allPedidos != null) {
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

}
