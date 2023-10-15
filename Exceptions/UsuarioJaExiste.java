package Exceptions;

public class UsuarioJaExiste extends Exception {
  public UsuarioJaExiste(String message) {
    super(message);
  }
}
