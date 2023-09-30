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
import br.edu.ufersa.poo.Pizzaria.classes.EnumTamanho;
import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.dao.ClienteDao;
import br.edu.ufersa.poo.Pizzaria.dao.TiposPizzasDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Separator;
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
  void SalvarPedido(ActionEvent event) { // falta implementar, precisa pegar os novos valores que foram selecionados nos
                                            // choicebox, recalcular o valor do pedido e inserir no banco de dados
    if (pedido != null) {

      Cliente cliente = new Cliente();
      cliente = pedido.getCliente();

      try {
        
      } catch (Exception e) {
        // TODO: handle exception
      }
      TiposPizzas pizza = new TiposPizzas();
      for (ItensPedidos tp : pedido.getItensPedido()) {
        if (tp.getTipoPizza().getNome().equals(PizzaBox.getValue())) {
          pizza = tp.getTipoPizza();
        }
      }
      
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



      try {
        cliente.setNome(ClienteBox.getValue());
        pizza.setNome(PizzaBox.getValue());

      } catch (Exception e) {
        e.printStackTrace();
      }
      
      PedidoBO pedidoBO = new PedidoBO();
      Pedido pedido = new Pedido();
        

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
    try {
      this.pedido.setCliente(pedido.getCliente());
      ClienteBox.setValue(pedido.getCliente().getNome());

      this.pedido.setEstado(pedido.getEstado());
      EstadoBox.setValue(pedido.getEstado().getDescricao());

      this.pedido.setItensPedido(pedido.getItensPedido());

      for (ItensPedidos item : pedido.getItensPedido()) {
        System.out.println(item.getAdicionais().get(0).getNome());
      }

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

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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
  }
}
