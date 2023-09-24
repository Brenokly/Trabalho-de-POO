package br.edu.ufersa.poo.Pizzaria.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TelaInicial implements Initializable {

    @FXML
    private VBox root; // VBox para organizar os elementos verticalmente

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Criar uma lista de nomes (para fins de teste)
        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        for (String nome : nomes) {
            Label label = new Label("Nome: " + nome);
            root.getChildren().add(label);
        }
    }

    public void Pesquisar() {
        // Implemente a lógica de pesquisa aqui, se necessário
    }
}
