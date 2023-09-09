package br.edu.ufersa.poo.Pizzaria.model.entity;

public enum Estado {
    PENDENTE("pendente"),
    PREPARANDO("preparando"),
    ENTREGUE("entregue");
    
    private String descricao;

    Estado(String descricao) {
        if ("pendente".equals(descricao) || "preparando".equals(descricao) || "entregue".equals(descricao)) {
            this.descricao = descricao;
        }
    }

    public String getDescricao() {return this.descricao;}
}