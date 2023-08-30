package br.edu.ufersa.poo.classes;

public class TiposPizzas {
    private String tipo;
    private double valorGrande;
    private double valorPequena;

    public TiposPizzas(){
        tipo = "";
        valorGrande = 0.0;
        valorPequena = 0.0;
    }

    public TiposPizzas(String tipo, double valorGrande, double valorPequena) {
        setTipo(tipo);
        setValorGrande(valorGrande);
        setValorPequena(valorPequena);
    }

    public String getTipo() { 
        return tipo;
    }

    public void setTipo(String tipo){
        if (tipo != null && !tipo.isEmpty())
        this.tipo = tipo; 
    }

    public double getValorGrande() {
        return valorGrande;
    }

    public void setValorGrande(double valorGrande) {
        if (valorGrande > 0)
        this.valorGrande = valorGrande;
    }

    public double getValorPequena() {
        return valorPequena;
    }

    public void setValorPequena(double valorPequena) {
        if (valorPequena > 0)
        this.valorPequena = valorPequena;
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