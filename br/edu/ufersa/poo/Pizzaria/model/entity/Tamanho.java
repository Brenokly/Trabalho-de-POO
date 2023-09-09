package br.edu.ufersa.poo.Pizzaria.model.entity;

public enum Tamanho {
    GRANDE("grande"),
    PEQUENA("pequena");

    private String descricao;

    Tamanho(String descricao){
        if ("grande".equals(descricao) || "pequena".equals(descricao)) {
            this.descricao = descricao;
        }
    }

    public String getDescricao() {return descricao;}
}