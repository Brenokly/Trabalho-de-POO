package br.edu.ufersa.poo.Pizzaria.view;

import br.edu.ufersa.poo.Pizzaria.controller.TelaAdicionalEditar;
import br.edu.ufersa.poo.Pizzaria.controller.TelaFuncionariosEditar;
import br.edu.ufersa.poo.Pizzaria.controller.TelaClientesEditar;
import br.edu.ufersa.poo.Pizzaria.controller.TelaPedidosEditar;
import br.edu.ufersa.poo.Pizzaria.controller.TelaSaboresEditar;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.model.entity.Pedido;
import br.edu.ufersa.poo.Pizzaria.model.entity.TiposPizzas;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import br.edu.ufersa.poo.Pizzaria.model.entity.Cliente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Telas extends Application {
  private static Stage primaryStage;
  private static Boolean isAdmin = false;

  public static void setAdmin(Boolean isAdmin) {
    Telas.isAdmin = isAdmin;
  }

  public static Boolean getAdmin() {
    return isAdmin;
  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void setPrimaryStage(Stage primaryStage) {
    Telas.primaryStage = primaryStage;
  }

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage arg0) throws Exception {
    setPrimaryStage(arg0);
    primaryStage.setTitle("Pizzaria Michelangelo");
    primaryStage.getIcons().add(new Image(Telas.class.getResourceAsStream("VE/Assets/Logo.png")));
    primaryStage.setResizable(false);
    primaryStage.show();
    TelaLogin();
  }

  public static void TelaLogin() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaLogin.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaInicial() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaInicialListagem.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaPedidosEditar(Pedido pedido) throws Exception {
    FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/TelaPedidosEditar.fxml"));
    Parent root = loader.load();

    // Obtenha o controlador da TelaFuncionarios2
    TelaPedidosEditar controller = loader.getController();

    controller.setPedido(pedido);

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
  }

  public static void TelaFuncionariosListagem() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaFuncionariosListagem.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaFuncionariosCadastrar() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaFuncionariosCadastrar.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaFuncionariosEditar(Usuario usuario) throws Exception {
    FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/TelaFuncionariosEditar.fxml"));
    Parent root = loader.load();

    // Obtenha o controlador da TelaFuncionarios3
    TelaFuncionariosEditar controller = loader.getController();

    controller.setFuncionario(usuario);

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
  }

  public static void TelaAdicionalListagem() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaAdicional.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaAdicionalCadastro() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaAdicionalCadastro.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaAdicionalEditar(Adicional adicional) throws Exception {
    FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/TelaAdicionalEditar.fxml"));
    Parent root = loader.load();

    // Obtenha o controlador da TelaAdicional3
    TelaAdicionalEditar controller = loader.getController();

    // Passe o Adicional para o controlador
    controller.setAdicional(adicional);

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
  }

  public static void TelaClientes() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaClientesListagem.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaClientes2() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaClientesCadastrar.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaClientes3(Cliente cliente) throws Exception {
    FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/TelaClientesEditar.fxml"));
    Parent root = loader.load();

    TelaClientesEditar controller = loader.getController();

    controller.setCliente(cliente);

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
  }

  public static void TelaSabores() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaSabores.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaSaboresCadastro() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaSaboresCadastro.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaSaboresEditar(TiposPizzas tiposPizzas) throws Exception {
    FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/TelaSaboresEditar.fxml"));
    Parent root = loader.load();

    // Obtenha o controlador da SaboresEditar
    TelaSaboresEditar controller = loader.getController();

    controller.setTiposPizzas(tiposPizzas);

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
  }

  public static void TelaPedidos() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaPedidosListagem.fxml"));

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
  }
}