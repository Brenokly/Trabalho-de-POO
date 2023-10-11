package br.edu.ufersa.poo.Pizzaria.model.entity;

public class Usuario {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private boolean isAdmin;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String email, String senha, boolean isAdmin) {
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setSenha(senha);
        setAdmin(isAdmin);
    }

    public Usuario(Long id, String nome, String cpf, String email, String senha, boolean isAdmin) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setSenha(senha);
        setAdmin(isAdmin);
    }

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
        validateStringInput(nome, "Nome inválido");
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        validateStringInput(cpf, "CPF inválido");
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateStringInput(email, "E-mail inválido");
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        validateStringInput(senha, "Senha inválida");
        this.senha = senha;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    private void validateStringInput(String value, String errorMessage) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}