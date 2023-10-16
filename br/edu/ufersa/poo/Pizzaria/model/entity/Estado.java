package br.edu.ufersa.poo.Pizzaria.model.entity;

public enum Estado {
    PENDENTE("Pendente"),
    PREPARANDO("Preparando"),
    ENTREGUE("Entregue");
    
    private String descricao;

    Estado(String descricao) {
        if ("Pendente".equals(descricao) || "Preparando".equals(descricao) || "Entregue".equals(descricao)) {
            this.descricao = descricao;
        }
    }

    public String getDescricao() {return this.descricao;}
}