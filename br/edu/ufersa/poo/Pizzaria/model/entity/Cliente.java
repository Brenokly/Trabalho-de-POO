package br.edu.ufersa.poo.Pizzaria.model.entity;

import Exceptions.*;

public class Cliente{
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;

    public Cliente(){
        setId(0L);
    }

    public Cliente(String nome, String cpf, String endereco) {
        setNome(nome);
        setCpf(cpf);
        setEndereco(endereco);
    }

    public Long getId() { return this.id; }
    public void setId(Long id) {
        if (id >= 1) {
            this.id = id;
        } else {
            throw new IdInvalido("ID inválido");
        }
    }

    public String getNome() { return this.nome; }
    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome inválido");
        }
    }

    public String getCpf() { return this.cpf; }
    public void setCpf(String cpf) {
        if (cpf != null) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public String getEndereco() { return this.endereco; }
    public void setEndereco(String endereco) {
        if (endereco != null) {
            this.endereco = endereco;
        } else {
            throw new IllegalArgumentException("Endereço inválido");
        }
    }
}