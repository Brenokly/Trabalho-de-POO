package br.edu.ufersa.poo.Pizzaria.model.entity;

public enum Tamanho {
    GRANDE("Grande"),
    PEQUENA("Pequena");

    private String descricao;

    Tamanho(String descricao){
        if ("Grande".equals(descricao) || "Pequena".equals(descricao)) {
            this.descricao = descricao;
        }
    }

    public String getDescricao() {return descricao;}
}