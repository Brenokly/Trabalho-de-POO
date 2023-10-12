package br.edu.ufersa.poo.Pizzaria.controller;

import Exceptions.UsuarioInvalido;
import br.edu.ufersa.poo.Pizzaria.model.bo.UserBO;
import br.edu.ufersa.poo.Pizzaria.model.entity.Usuario;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TelaLogin {
  private UserBO userbo = new UserBO();
  
  private PseudoClass error = PseudoClass.getPseudoClass("error");

  @FXML private TextField login;
  @FXML private PasswordField senha;

  @FXML
  void onTextFieldContentChanged(KeyEvent event) {
    TextField sourceTextField = (TextField) event.getSource();
    
    sourceTextField.pseudoClassStateChanged(error, false);
  }
  
  public void Autenticar(ActionEvent event) {
    Usuario usuario = new Usuario();
    String userLogin = login.getText();
    String userSenha = senha.getText();

    if (userLogin == null || userLogin.isEmpty()) {
      login.pseudoClassStateChanged(error, true);
      return;
    } else if (userSenha == null || userSenha.isEmpty()) {
      senha.pseudoClassStateChanged(error, true);
      return;
    }

    usuario.setEmail(userLogin);
    usuario.setSenha(userSenha);

    Boolean isAdmin = false;
    try {
      isAdmin = userbo.Autenticar(usuario);
      Telas.setAdmin(isAdmin);
      Telas.TelaInicial();
    } catch (UsuarioInvalido e) {
      login.pseudoClassStateChanged(error, true);
      senha.pseudoClassStateChanged(error, true);
    } catch (Exception e) {
    }
  }
}