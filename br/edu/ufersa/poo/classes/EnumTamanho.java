package br.edu.ufersa.poo.classes;

public enum EnumTamanho {
    GRANDE("grande"),
    PEQUENA("pequena");

    private String descricao;

    EnumTamanho(String descricao){
        if ("grande".equals(descricao) || "pequena".equals(descricao)) {
            this.descricao = descricao;
        }
    }

    public String getDescricao() {    return descricao;}
}
