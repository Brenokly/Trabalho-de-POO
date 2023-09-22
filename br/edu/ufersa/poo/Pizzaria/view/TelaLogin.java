package br.edu.ufersa.poo.Pizzaria.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaLogin extends Application {
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage arg0) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("VE/TelaLogin.fxml"));

    Scene scene = new Scene(root);

    arg0.setTitle("Pizzaria Michelangeclo");
    arg0.setScene(scene);
    arg0.show();
  }
}
