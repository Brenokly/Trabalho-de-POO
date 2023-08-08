package br.edu.ufersa.poo.classes;

class TiposPizzas {
    private String tipo;
    private double ValorGrande;
    private double ValorPequena;

    public TiposPizzas(String tipo, double valorGrande, double valorPequena) {
        setTipo(tipo);
        setValorGrande(valorGrande);
        setValorPequena(valorPequena);
    }

    public String getTipo() { 
        return tipo;
    }

    public void setTipo(String tipo){
        if (!tipo.isEmpty() && tipo != null)
       this.tipo = tipo; 
    }

    public double getValorGrande() {
        return ValorGrande;
    }

    public void setValorGrande(double valorGrande) {
        if (valorGrande > 0)
        ValorGrande = valorGrande;
    }

    public double getValorPequena() {
        return ValorPequena;
    }

    public void setValorpequena(double valorpequena) {
        if (valorpequena > 0)
        this.ValorPequena = valorPequena;
    }

    public boolean cadastrarSabor(TiposPizzas pizzas){
        System.out.println("Cadastrado com sucesso!");
        
        return true;
    }

    public boolean editarSabor(TiposPizzas pizzas){
        System.out.println("Editado com sucesso!");

        return true;
    }

     public boolean excluirSabor(TiposPizzas pizzas){
        System.out.println("Excluído com sucesso!");

        return true;
    }
}