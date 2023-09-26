package br.edu.ufersa.poo.Pizzaria.view;

import br.edu.ufersa.poo.Pizzaria.controller.TelaAdicional3;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas extends Application {
  private static Stage primaryStage;

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
    primaryStage.setTitle("Pizzaria Michelangeclo");
    primaryStage.show();
    TelaLogin();
  }

  public static void TelaLogin() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaLogin.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaInicial() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaInicial.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaFuncionarios() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaFuncionarios.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaAdicional() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaAdicional.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaAdicional2() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaAdicional2.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaAdicional3(Adicional adicional) throws Exception {
    FXMLLoader loader = new FXMLLoader(Telas.class.getResource("VE/TelaAdicional3.fxml"));
    Parent root = loader.load();

    // Obtenha o controlador da TelaAdicional3
    TelaAdicional3 controller = loader.getController();

    // Passe o Adicional para o controlador
    controller.setAdicional(adicional);

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
  }
}
