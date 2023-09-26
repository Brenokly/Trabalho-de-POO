package br.edu.ufersa.poo.Pizzaria.controller;

import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaInicial {

    @FXML
    private Button adicionais;

    @FXML
    void carregarAdicionais(ActionEvent event) throws Exception {
        Telas.TelaAdicional();
    }

    @FXML
    private Button funcionarios;

    @FXML
    void carregarFuncionarios(ActionEvent event) throws Exception {
        Telas.TelaFuncionarios();
    }

    @FXML
    private Button sair;

    @FXML
    void carregarLogin(ActionEvent event) throws Exception {
        Telas.TelaLogin();
    }

}
