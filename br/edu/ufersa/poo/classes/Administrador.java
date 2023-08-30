package br.edu.ufersa.poo.classes;

public class Administrador {
    private String nome;
    private String login;
    private String senha;

    public Administrador() {
        nome = "";
        login = "";
        senha = "";
    }

    public Administrador(String nome, String login, String senha) {
        setNome(nome);
        setLogin(login);
        setSenha(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login != null && !login.isEmpty())
            this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty())
            this.senha = senha;
    }

    public boolean login(String login, String senha) {

        if (this.login.equals(login) && this.senha.equals(senha)) {
            System.out.println("Logando...");

            return true;
        } else
            return false;
    }
}