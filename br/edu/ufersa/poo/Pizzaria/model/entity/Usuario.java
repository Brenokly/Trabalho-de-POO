package br.edu.ufersa.poo.Pizzaria.model.entity;

public class Usuario {
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String senha;
    private boolean isAdmin;

    // public Usuario(String nome, String cpf, String endereco, String email, String password, boolean isAdmin) {
    //     setNome(nome);
    //     setEndereco(endereco);
    //     setEmail(email);
    //     setSenha(password);
    //     setAdmin(isAdmin);

    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
        else {
            throw new IllegalArgumentException("Nome inválido");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf != null && !cpf.isEmpty())
            this.cpf = cpf;
        else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco != null && !endereco.isEmpty())
            this.endereco = endereco;
        else {
            throw new IllegalArgumentException("Endereço inválido");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isEmpty())
            this.email = email;
        else {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty())
            this.senha = senha;
        else {
            throw new IllegalArgumentException("Senha inválido");
        }
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
