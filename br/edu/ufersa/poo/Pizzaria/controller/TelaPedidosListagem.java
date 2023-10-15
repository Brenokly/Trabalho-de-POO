package br.edu.ufersa.poo.Pizzaria.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ufersa.poo.Pizzaria.model.bo.PedidoBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import br.edu.ufersa.poo.Pizzaria.model.entity.Pedido;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class TelaPedidosListagem implements Initializable {
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
    private Button imprimir;

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

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    private ObservableList<Pedido> list = FXCollections.observableArrayList();
    private ObservableList<Pedido> allPedidos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPedidos();
        setupTableViewColumns();
        setupDatePickers();
    }

    private void loadPedidos() {
        List<Pedido> pedidos = null;
        List<Pedido> filteredPedidos = new ArrayList<>();
        try {
            pedidos = pedidoBO.buscarTodos();
            if (pedidos != null) {
                for (Pedido pedido : pedidos) {
                    if (!pedido.getItensPedido().isEmpty()) {
                        filteredPedidos.add(pedido);
                    }
                }
                list.addAll(filteredPedidos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        allPedidos.addAll(filteredPedidos);
    }

    private void setupTableViewColumns() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cliente.setCellValueFactory(cellData -> {
            Cliente clienteDoPedido = cellData.getValue().getCliente();
            String nomeDoCliente = clienteDoPedido != null ? clienteDoPedido.getNome() : "";
            return new SimpleStringProperty(nomeDoCliente);
        });
        valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));

        if (list != null) {
            table.setItems(list);
        }
    }

    private void setupDatePickers() {
        LocalDate currentDate = LocalDate.now();
        LocalDate minDate = LocalDate.of(2023, 10, 1);

        startDatePicker.setValue(minDate);
        startDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(minDate) || date.isAfter(currentDate));
            }
        });


        endDatePicker.setValue(currentDate);
        endDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(minDate) || date.isAfter(currentDate) || startDatePicker.getValue() != null && date.isBefore(startDatePicker.getValue()));
            }
        });
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
    void atualizarTabelaPorData(ActionEvent event) {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        ObservableList<Pedido> resultados = FXCollections.observableArrayList();

        for (Pedido pedido : allPedidos) {
            LocalDate dataPedido = pedido.getData();

            if (startDate == null && endDate == null) {
                resultados.addAll(allPedidos);
                break;
            }

            if ((startDate == null || dataPedido.isEqual(startDate) || dataPedido.isAfter(startDate)) &&
                    (endDate == null || dataPedido.isEqual(endDate) || dataPedido.isBefore(endDate))) {
                resultados.add(pedido);
            }
        }

        table.setItems(resultados);
    }

    @FXML
    void Imprimir(ActionEvent event) {
        String pdfFileName = "pedidos.pdf";
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
            document.open();

            // Adicionar informações da empresa
            Font empresaFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
            Paragraph empresa = new Paragraph("Pizzaria Michelangelo", empresaFont);
            empresa.setAlignment(Element.ALIGN_CENTER);
            document.add(empresa);

            if (startDate != null || endDate != null) {
                String intervaloTempo = "";

                if (startDate != null) {
                    intervaloTempo += "Tempo Inicial: " + startDate.toString() + "\n";
                }

                if (endDate != null) {
                    intervaloTempo += "Tempo Final: " + endDate.toString() + "\n";
                }

                Paragraph intervaloTempoParagrafo = new Paragraph(intervaloTempo);
                document.add(intervaloTempoParagrafo);
            }

            Paragraph title = new Paragraph("Relatório de Pedidos\n\n");
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            PdfPTable pdfTable = new PdfPTable(5);

            PdfPCell cell = new PdfPCell(new Phrase("ID"));
            pdfTable.addCell(cell);
            cell = new PdfPCell(new Phrase("Estado"));
            pdfTable.addCell(cell);
            cell = new PdfPCell(new Phrase("Cliente"));
            pdfTable.addCell(cell);
            cell = new PdfPCell(new Phrase("Valor"));
            pdfTable.addCell(cell);
            cell = new PdfPCell(new Phrase("Data"));
            pdfTable.addCell(cell);

            for (Pedido pedido : allPedidos) {
                LocalDate dataPedido = pedido.getData();

                if ((startDate == null || dataPedido.isEqual(startDate) || dataPedido.isAfter(startDate)) &&
                        (endDate == null || dataPedido.isEqual(endDate) || dataPedido.isBefore(endDate))) {
                    pdfTable.addCell(String.valueOf(pedido.getId()));
                    pdfTable.addCell(pedido.getEstado().getDescricao());
                    pdfTable.addCell(pedido.getCliente().getNome());
                    pdfTable.addCell(String.valueOf(pedido.getValor()));
                    pdfTable.addCell(dataPedido.toString());
                }
            }

            document.add(pdfTable);
            document.close();
            System.out.println("PDF criado com sucesso em " + pdfFileName);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
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
                table.setItems(allPedidos);
            } else {
                List<Pedido> resultados = new ArrayList<>();

                for (Pedido pedido : allPedidos) {
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
