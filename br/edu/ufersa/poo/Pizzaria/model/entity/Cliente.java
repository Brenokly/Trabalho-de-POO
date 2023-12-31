package br.edu.ufersa.poo.Pizzaria.model.entity;

import Exceptions.*;

public class Cliente{
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;

    public Cliente(){
    }

    public Cliente(String nome, String cpf, String endereco) throws Exception {
        setNome(nome);
        setCpf(cpf);
        setEndereco(endereco);
    }

    public Cliente(Long id, String nome, String cpf, String endereco) throws Exception {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setEndereco(endereco);
    }

    public Long getId() { return this.id; }
    public void setId(Long id) throws IdInvalido {
        if (id >= 0) {
            this.id = id;
        } else {
            throw new IdInvalido("ID inválido");
        }
    }

    public String getNome() { return this.nome; }
    public void setNome(String nome) throws NomeInvalido {
        if (nome != null) {
            this.nome = nome;
        } else {
            throw new NomeInvalido("Nome inválido");
        }
    }

    public String getCpf() { return this.cpf; }
    public void setCpf(String cpf) throws Cpfnvalido {
        if (cpf != null) {
            this.cpf = cpf;
        } else {
            throw new Cpfnvalido("CPF inválido");
        }
    }

    public String getEndereco() { return this.endereco; }
    public void setEndereco(String endereco) throws EnderecoInvalido {
        if (endereco != null) {
            this.endereco = endereco;
        } else {
            throw new EnderecoInvalido("Endereço inválido");
        }
    }
}