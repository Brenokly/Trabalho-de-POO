package br.edu.ufersa.poo.classes;

class Funcionarios {
    private String nome;
    private String cpf;
    private String login;
    private String senha;

    public Funcionarios() {
    }

    public Funcionarios(String nome, String cpf, String login, String senha) {
        setNome(nome);
        setCpf(cpf);
        setLogin(login);
        setSenha(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.isEmpty() && nome != null)
            this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!cpf.isEmpty() && cpf != null)
            this.cpf = cpf;
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

    public boolean cadastrarFuncionario(Funcionarios funcionario) {
        System.out.println("Castrado com sucesso!"); 

        return true;
    }
    
    public boolean editarFuncionario(Funcionarios funcionario){
        System.out.println("Editado com sucesso!"); 

        return true;
    }

    public boolean excluirFuncionario(Funcionarios funcionario){
       System.out.println("Excluído com sucesso!"); 

        return true;
    }

    public boolean login(String login, String senha) {

        if (this.login.equals(login) && this.senha.equals(senha)) {
            System.out.println("Logando...");

            return true;
        } else
            return false;
    }
}