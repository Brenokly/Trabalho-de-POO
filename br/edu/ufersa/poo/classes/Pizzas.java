package br.edu.ufersa.poo.classes;

public class Pizzas {
    private TiposPizzas tipopizza;
    private EnumTamanho tamanho;
    private Adicionais adicional;
    private double valor;

    public Pizzas(){
        tipopizza = new Tipospizzas();
        adicional = new Adicionais();
        valor = 0,0;
    }

    public Pizzas(TiposPizzas tipopizza, EnumTamanho tamanho, Adicionais adicional, double valor){
        setTipopizza(tipopizza);
        setTamanho(tamanho);
        setAdicional(adicional);
        setValor(valor);
    }

    public TiposPizzas getTipopizza() {
        return tipopizza;
    }

    public void setTipopizza(TiposPizzas tipopizza) {
        this.tipopizza = tipopizza;
    }

    public EnumTamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(EnumTamanho tamanho) {
        if ("grande".equals(tamanho.getDescricao()) || "pequena".equals(tamanho.getDescricao())) 
        this.tamanho = tamanho;
    }

    public Adicionais getAdicional() {
        return adicional;
    }

    public void setAdicional(Adicionais adicional) {
        this.adicional = adicional;
    }

    public double getValor() { 
        return valor; 
    }

    public void setValor(double valor) {
        if (valor > 0.0)
        this.valor = valor;
    }
}