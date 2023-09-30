package br.edu.ufersa.poo.Pizzaria.controller;

import Exceptions.UsuarioInvalido;
import br.edu.ufersa.poo.Pizzaria.model.bo.UserBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLogin {
  private UserBO userbo = new UserBO();

  @FXML private TextField login;
  @FXML private PasswordField senha;
  
  public void Autenticar(ActionEvent event) {
    Usuario usuario = new Usuario();
    String userLogin = login.getText();
    String userSenha = senha.getText();

    PseudoClass error = PseudoClass.getPseudoClass("error");
    PseudoClass starter = PseudoClass.getPseudoClass("empty");

    if (userLogin == null || userLogin.isEmpty()) {
      login.pseudoClassStateChanged(error, true);
      return;
    } else if (userSenha == null || userSenha.isEmpty()) {
      senha.pseudoClassStateChanged(error, true);
      return;
    }

    usuario.setEmail(userLogin);
    usuario.setSenha(userSenha);

    try {
      userbo.Autenticar(usuario);
      login.pseudoClassStateChanged(starter, true);
      senha.pseudoClassStateChanged(starter, true);
      Telas.TelaInicial();
    } catch (UsuarioInvalido e) {
      login.pseudoClassStateChanged(error, true);
      senha.pseudoClassStateChanged(error, true);
    } catch (Exception e) {
    }
  }
}