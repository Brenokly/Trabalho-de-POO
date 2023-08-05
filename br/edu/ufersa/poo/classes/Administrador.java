package br.edu.ufersa.poo.classes;

class Administrador {
    private String nome;
    private String login;
    private String senha;

    public Administrador() {
        nome = "";
        senha = "";
        login = "";
    }

    public Administrador(String nome, String login, String senha) {
        if (nome != null && nome.equals("") && senha != null && senha.equals("") && login != null && login.equals("")) {
            this.nome = nome;
            this.login = login;
            this.senha = senha;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && nome.equals(""))
            this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login != null && login.equals(""))
            this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && senha.equals(""))
            this.senha = senha;
    }

    public boolean login(String login, String senha) {
        boolean check = false;

        if (this.login.equals(login) && this.senha.equals(senha)) {
            System.out.println("Logando...");
            check = true;

            return check;
        } else
            return check;
    }
}