package br.edu.ufersa.poo.Pizzaria.controller;

import Exceptions.UsuarioInvalido;
import br.edu.ufersa.poo.Pizzaria.model.bo.UserBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLogin {
  private UserBO userbo = new UserBO();

  @FXML private TextField login;
  @FXML private PasswordField senha;
  @FXML private Label erroaut;
  
  public void Autenticar(ActionEvent event) {
    Usuario usuario = new Usuario();
    usuario.setEmail(login.getText());
    usuario.setSenha(senha.getText());

    try {
      userbo.Autenticar(usuario);
      erroaut.setVisible(false);
      Telas.TelaInicial();
    } catch (UsuarioInvalido e) {
      erroaut.setText("Email ou senha inválido");
      erroaut.setVisible(true);
    } catch (Exception e) {
      erroaut.setText("Email ou senha inválido");
      erroaut.setVisible(true);
    }
  }
}