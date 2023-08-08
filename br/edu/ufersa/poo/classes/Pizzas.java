package br.edu.ufersa.poo.classes;

import java.util.ArrayList;
import java.util.List;

public class Pizzas {
    private TiposPizzas tipopizza;
    private EnumTamanho tamanho;
    private List<Adicionais> adicional = new ArrayList<Adicionais>();
    private double valor;

    public Pizzas(){ // Para pizzas sem adicionais
        tipopizza = new Tipospizzas();
        setTamanho(EnumTamanho.GRANDE); // Por padrão as pizzas são grandes, com possibilidade de alterar.
        calcValor();
    }

    public Pizzas(){ // Para pizzas com adicionais
        tipopizza = new Tipospizzas();
        adicional.add(new Adicionais());
        setTamanho(EnumTamanho.GRANDE);
        calcValor();
    }

    public Pizzas(TiposPizzas tipopizza, EnumTamanho tamanho){ // Para o caso de uma pizza sem adicional
        setTipopizza(tipopizza);
        setTamanho(EnumTamanho.GRANDE);
        calcValor();
    }

    public Pizzas(TiposPizzas tipopizza, EnumTamanho tamanho, Adicionais adicional){ // Para pizzas com adicionais
        setTipopizza(tipopizza);
        setTamanho(tamanho);
        setAdicional(adicional);
        calcValor();
    }

    public TiposPizzas getTipopizza() {
        return tipopizza;
    }

    public void setTipopizza(TiposPizzas tipopizza) { // setTipo substitui o método AlterarTipo do diagrama de classe
        if (tipopizza != null) {
        this.tipopizza = tipopizza;
        calcValor();
        }
        else {}
    }

    public EnumTamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(EnumTamanho tamanho) {   // setTamanho substitui o método AlterarTamahno do diagrama de classe.
        if ("grande".equals(tamanho.getDescricao()) || "pequena".equals(tamanho.getDescricao())){ 
        this.tamanho = tamanho;
        calcValor();
        }
        else {}
    }

    public Adicionais getAdicional() {
        return adicional.get(0);
    }

    public void setAdicional(Adicionais adicional) { // setAdicional substitui o método AlterarAdicional que faltou no diagrama.
        if (adicional != null){
        this.adicional.clear();
        this.adicional.add(adicional);

        calcValor();
        }
        else {}
    }

    public void addAdicional(Adicionais adicional){
        this.adicional.add(adicional);
        
        calcValor();
    }

    public void removerAdicional(Adicionais adicional){
        this.adicional.remove(adicional);

        calcValor();
    }

    public double getValor() { 
        return valor; 
    }

    public void setValor(double valor) { // setValor substitui o método AlterarValor que estava no diagrama de classe.
        if (valor > 0.0)
        this.valor = valor;
    }

    public boolean cadastrarPizza(Pizzas pizza){
        System.out.println("Pizza cadastrada!");

        return true;
    }

    public boolean editarPizza(Pizzas pizza){
        System.out.println("Pizza editada!");

        return true;
    }

     public boolean excluirPizza(Pizzas pizza){
        System.out.println("Pizza excluída!");

        return true;
    }

    public void calcValor(){
        double Valor = 0.0;

        if (getTamanho() == EnumTamanho.GRANDE) {
            valor += getTipopizza().getValorGrande();
        }
        else{
            valor += getTipopizza().getValorPequena();
        }

        for (Adicionais add : adicional) {
            valor += add.getValor();
        }

        setValor(Valor);
    }

    public Pizzas[] buscarPizzas(TiposPizzas pizza){
        System.out.println("Buscando pizzas por tipo...");
    }

    public Pizzas[] buscarPizzas(Clientes pizza){
        System.out.println("Buscando pizzas por clientes...");
    }
}