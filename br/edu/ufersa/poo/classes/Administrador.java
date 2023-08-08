package br.edu.ufersa.poo.classes;

class Administrador {
    private String nome;
    private String login;
    private String senha;

    public Administrador() {
    }

    public Administrador(String nome, String login, String senha) {
        if (!nome.isEmpty() && nome != null && !senha.isEmpty() && senha != null && !login.isEmpty() && login != null) {
            this.nome = nome;
            this.login = login;
            this.senha = senha;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.isEmpty() && nome != null)
            this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (!login.isEmpty() && login != null)
            this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (!senha.isEmpty() && senha != null)
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