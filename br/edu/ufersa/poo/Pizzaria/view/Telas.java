package br.edu.ufersa.poo.Pizzaria.view;

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
    TelaSabores();
  } 

  public static void TelaLogin() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaLogin.fxml"));

    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
  }

  public static void TelaAdicional() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaAdicional.fxml"));

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

  public static void TelaSabores() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaSabores.fxml"));

    Scene scene = new Scene(root);
    
    primaryStage.setScene(scene);
  }
}
