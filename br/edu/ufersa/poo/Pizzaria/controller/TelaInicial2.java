package br.edu.ufersa.poo.Pizzaria.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.ufersa.poo.Pizzaria.model.bo.PedidoBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import br.edu.ufersa.poo.Pizzaria.model.entity.Estado;
import br.edu.ufersa.poo.Pizzaria.model.entity.ItensPedidos;
import br.edu.ufersa.poo.Pizzaria.model.entity.Pedido;
import br.edu.ufersa.poo.Pizzaria.model.entity.Tamanho;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.dao.ClienteDao;
import br.edu.ufersa.poo.Pizzaria.dao.ItensPedidosDao;
import br.edu.ufersa.poo.Pizzaria.dao.TiposPizzasDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import br.edu.ufersa.poo.Pizzaria.view.Telas;

public class TelaInicial2 extends Dialog<Pedido> implements Initializable {
  private Pedido pedido = new Pedido();

  @FXML
  private ChoiceBox<String> Adicional1Box;

  @FXML
  private ChoiceBox<String> Adicional2Box;

  @FXML
  private ChoiceBox<String> Adicional3Box;

  @FXML
  private Button Adicionar;

  @FXML
  private ChoiceBox<String> ClienteBox;

  @FXML
  private ChoiceBox<String> EstadoBox;

  @FXML
  private ChoiceBox<String> PizzaBox;

  @FXML
  private Button Remover;

  @FXML
  private ChoiceBox<String> TamanhoBox;

  @FXML
  private Button adicionais;

  @FXML
  private Button cancelar;

  @FXML
  private Button funcionarios;

  @FXML
  private Button inicio;

  @FXML
  private Separator linha1;

  @FXML
  private Separator linha2;

  @FXML
  private Separator linha3;

  @FXML
  private Button sair;

  @FXML
  private Button salvar;

  @FXML
  private Pagination Pagina;

  int currentPageIndex = 0; // Adicione esta variável para rastrear a página atual
  int pageIndex = 0;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ClienteDao clienteDao = new ClienteDao(); // será o bo
    List<Cliente> clientes = clienteDao.listar();
    List<String> nomesC = new ArrayList<>();

    for (Cliente cl : clientes) {
      nomesC.add(cl.getNome());
    }

    ClienteBox.getItems().addAll(nomesC);

    EstadoBox.getItems().addAll("entregue", "pendente", "preparando");

    TamanhoBox.getItems().addAll("grande", "pequena");

    TiposPizzasDao tiposPizzasDao = new TiposPizzasDao(); // será o bo
    List<TiposPizzas> tiposPizzas = tiposPizzasDao.listar();
    List<String> nomesP = new ArrayList<>();

    for (TiposPizzas tp : tiposPizzas) {
      nomesP.add(tp.getNome());
    }

    PizzaBox.getItems().addAll(nomesP);

    AdicionalDao adicionalDao = new AdicionalDao(); // será o bo
    List<Adicional> adicional = adicionalDao.listar();
    List<String> nomesA = new ArrayList<>();

    for (Adicional ad : adicional) {
      nomesA.add(ad.getNome());
    }

    Adicional1Box.getItems().addAll(nomesA);
    Adicional2Box.getItems().addAll(nomesA);
    Adicional3Box.getItems().addAll(nomesA);

    Pagina.setCurrentPageIndex(currentPageIndex);

    // Adicione o ouvinte à propriedade currentPageIndex no método initialize
    Pagina.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
      currentPageIndex = newIndex.intValue();
    });
  }

  @FXML
  void SalvarPedido(ActionEvent event) throws Exception {
    try {
      Estado estado = null;
      if (EstadoBox.getValue().equals("entregue")) {
        estado = Estado.ENTREGUE;
      } else if (EstadoBox.getValue().equals("pendente")) {
        estado = Estado.PENDENTE;
      } else if (EstadoBox.getValue().equals("preparando")) {
        estado = Estado.PREPARANDO;
      }

      Tamanho tamanho = null;
      if (TamanhoBox.getValue().equals("grande")) {
        tamanho = Tamanho.GRANDE;
      } else if (TamanhoBox.getValue().equals("pequena")) {
        tamanho = Tamanho.PEQUENA;
      }

      pedido.setEstado(estado);
      pedido.getItensPedido().get(currentPageIndex).setTamanho(tamanho);

      for (Cliente cliente : new ClienteDao().listar()) {
        if (cliente.getNome().equals(ClienteBox.getValue())) {
          pedido.setCliente(cliente);
          break;
        }
      }

      // Alterar itens do pedido com os valores que estão na box PizzaBox
      // e AdicionalBox1,2,3 se existirem
      for (TiposPizzas tp : new TiposPizzasDao().listar()) {
        if (tp.getNome().equals(PizzaBox.getValue())) {
          pedido.getItensPedido().get(currentPageIndex).setPizza(tp);
          pedido.getItensPedido().get(currentPageIndex).setDescricao();
          break;
        }
      }

      List<Adicional> adicionais = new ArrayList<>();
      if (Adicional1Box.isVisible()) {
        for (Adicional ad : new AdicionalDao().listar()) {
          if (ad.getNome().equals(Adicional1Box.getValue())) {
            adicionais.add(ad);
            break;
          }
        }
      }
      if (Adicional2Box.isVisible()) {
        for (Adicional ad : new AdicionalDao().listar()) {
          if (ad.getNome().equals(Adicional2Box.getValue())) {
            adicionais.add(ad);
            break;
          }
        }
      }
      if (Adicional3Box.isVisible()) {
        for (Adicional ad : new AdicionalDao().listar()) {
          if (ad.getNome().equals(Adicional3Box.getValue())) {
            adicionais.add(ad);
            break;
          }
        }
      }

      pedido.getItensPedido().get(currentPageIndex).setAdicionais(adicionais);

      pedido.setValor(pedido.calcValor());
    
      PedidoBO pedidoBO = new PedidoBO();
      pedidoBO.update(pedido);

      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Sucesso");
      alert.setHeaderText(null);
      alert.setContentText("Alteração concluída com sucesso.");
      alert.showAndWait();

      Telas.TelaInicial();
    } catch (Exception e) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText("Erro no banco de dados");
      alert.setContentText("Ocorreu um erro no banco de dados.");
      alert.showAndWait();
      e.printStackTrace();
    }
  }

  @FXML
  void AdicionalADD(ActionEvent event) {
    AdicionalDao adicionalDao = new AdicionalDao(); // será o bo
    List<Adicional> adicional = adicionalDao.listar();
    List<String> nomesA = new ArrayList<>();

    for (Adicional ad : adicional) {
      nomesA.add(ad.getNome());
    }

    if (!Adicional1Box.isVisible()) {
      Adicional1Box.setVisible(true);
      linha1.setVisible(true);
      Adicional1Box.getItems().addAll(nomesA);
    } else if (!Adicional2Box.isVisible()) {
      Adicional2Box.setVisible(true);
      linha2.setVisible(true);
      Adicional2Box.getItems().addAll(nomesA);
    } else if (!Adicional3Box.isVisible()) {
      Adicional3Box.setVisible(true);
      linha3.setVisible(true);
      Adicional3Box.getItems().addAll(nomesA);

    }
  }

  @FXML
  void RemoverADD(ActionEvent event) {
    if (Adicional3Box.isVisible()) {
      Adicional3Box.setVisible(false);
      linha3.setVisible(false);
      Adicional3Box.getItems().clear();
    } else if (Adicional2Box.isVisible()) {
      Adicional2Box.setVisible(false);
      linha2.setVisible(false);
      Adicional2Box.getItems().clear();
    } else if (Adicional1Box.isVisible()) {
      Adicional1Box.setVisible(false);
      linha1.setVisible(false);
      Adicional1Box.getItems().clear();
    }
  }

  @FXML
  void CancelarPedido(ActionEvent event) throws Exception {
    Telas.TelaInicial();
  }

  @FXML
  void carregarAdicionais(ActionEvent event) throws Exception {
    Telas.TelaAdicional();
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

  public void setPedido(Pedido pedido) {
    Pagina.setPageCount(pedido.getItensPedido().size());

    try {
      this.pedido.setCliente(pedido.getCliente());
      ClienteBox.setValue(pedido.getCliente().getNome());

      this.pedido.setEstado(pedido.getEstado());
      EstadoBox.setValue(pedido.getEstado().getDescricao());

      this.pedido.setItensPedido(pedido.getItensPedido());

      TamanhoBox.setValue(pedido.getItensPedido().get(0).getTamanho().getDescricao());
      PizzaBox.setValue(pedido.getItensPedido().get(0).getPizza().getNome());
      this.pedido.setValor(pedido.getValor());
      this.pedido.setId(pedido.getId());
      this.pedido.setData(pedido.getData());

      if (!pedido.getItensPedido().get(0).getAdicionais().isEmpty()) {
        List<Adicional> adicionais = pedido.getItensPedido().get(0).getAdicionais();

        if (!adicionais.isEmpty()) {
          // A lista de adicionais do primeiro item não está vazia
          if (adicionais.size() >= 1) {

            Adicional1Box.setValue(adicionais.get(0).getNome());
            Adicional1Box.setVisible(true);
            linha1.setVisible(true);
          }
          if (adicionais.size() >= 2) {
            Adicional2Box.setValue(adicionais.get(1).getNome());
            Adicional2Box.setVisible(true);
            linha2.setVisible(true);
          }
          if (adicionais.size() >= 3) {
            Adicional3Box.setValue(adicionais.get(2).getNome());
            Adicional3Box.setVisible(true);
            linha3.setVisible(true);
          }
        }
      }

      // Obtenha o índice da página atual
      currentPageIndex = Pagina.getCurrentPageIndex();

      // Atualize o Pagination para garantir que ele esteja na página correta
      Pagina.setPageFactory(this::createPage);

      System.out.println("currentPageIndex: " + currentPageIndex);

      // Configure a página atual para a página carregada
      Pagina.setCurrentPageIndex(currentPageIndex);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Node createPage(int pageIndex) {
    // Crie um novo Pane para agrupar os elementos
    Pane pageContent = new Pane();

    // Adicione elementos relevantes ao Pane
    Label label = new Label("Pizza ID: " + pedido.getItensPedido().get(pageIndex).getId());
    pageContent.getChildren().add(label);

    // Limpar e ocultar as ChoiceBoxes que precisam ser ocultadas ou redefinidas
    Adicional1Box.getItems().clear();
    Adicional2Box.getItems().clear();
    Adicional3Box.getItems().clear();
    Adicional1Box.setVisible(false);
    Adicional2Box.setVisible(false);
    Adicional3Box.setVisible(false);
    linha1.setVisible(false);
    linha2.setVisible(false);
    linha3.setVisible(false);
    PizzaBox.getItems().clear();

    // Obtenha a lista de nomes de pizzas
    TiposPizzasDao tiposPizzasDao = new TiposPizzasDao(); // será o bo
    List<TiposPizzas> tiposPizzas = tiposPizzasDao.listar();
    List<String> nomesP = new ArrayList<>();

    PizzaBox.setValue(tiposPizzas.get(pageIndex).getNome());

    for (TiposPizzas tp : tiposPizzas) {
      nomesP.add(tp.getNome());
    }

    PizzaBox.getItems().addAll(nomesP);

    TamanhoBox.setValue(pedido.getItensPedido().get(pageIndex).getTamanho().getDescricao());

    EstadoBox.setValue(pedido.getEstado().getDescricao());

    if (!pedido.getItensPedido().isEmpty() && pageIndex >= 0 && pageIndex < pedido.getItensPedido().size()) {
      ItensPedidos itemAtual = pedido.getItensPedido().get(pageIndex);

      if (!itemAtual.getAdicionais().isEmpty()) {
        List<Adicional> adicionais = itemAtual.getAdicionais();

        AdicionalDao adicionalDao = new AdicionalDao(); // será o bo
        List<Adicional> adicional = adicionalDao.listar();
        List<String> nomesA = new ArrayList<>();

        for (Adicional ad : adicional) {
          nomesA.add(ad.getNome());
        }

        if (!adicionais.isEmpty()) {
          if (adicionais.size() >= 1) {
            Adicional1Box.getItems().addAll(nomesA);
            Adicional1Box.setValue(adicionais.get(0).getNome());
            Adicional1Box.setVisible(true);
            linha1.setVisible(true);
          }
          if (adicionais.size() >= 2) {
            Adicional2Box.getItems().addAll(nomesA);
            Adicional2Box.setValue(adicionais.get(1).getNome());
            Adicional2Box.setVisible(true);
            linha2.setVisible(true);
          }
          if (adicionais.size() >= 3) {
            Adicional3Box.getItems().addAll(nomesA);
            Adicional3Box.setValue(adicionais.get(2).getNome());
            Adicional3Box.setVisible(true);
            linha3.setVisible(true);
          }
        }
      }
    }

    // Retorne o Pane como conteúdo da página
    return pageContent;
  }
}