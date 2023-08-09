package br.edu.ufersa.poo.classes;

public enum EnumEstado {
    PENDENTE("pendente"),
    PREPARANDO("preparando"),
    ENTREGUE("entregue");
    
    private String descricao;

    EnumEstado(String descricao) {
        if ("pendente".equals(descricao) || "preparando".equals(descricao) || "entregue".equals(descricao)) {
            this.descricao = descricao;
        }
    }

    public String getDescricao() {return this.descricao;}
}
