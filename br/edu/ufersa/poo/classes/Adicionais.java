package br.edu.ufersa.poo.classes;

class Adicionais {
	private String nome;
	private int estoque;
	private double valor;
	
	public Adicionais(String nome, int estoque, double valor) {
		setNome(nome);
        setEstoque(estoque);
        setValor(valor);
	}
	
	public String getNome() {return this.nome;}
	public void setNome(String nome) {
		if (!nome.isEmpty()) {
			this.nome = nome;
		} else {
			System.out.println("Nome inválido");
            this.nome = "Anon";
		}
	}
	
	public int getEstoque() {return this.estoque;}
    public void setEstoque(int estoque) {
        if (estoque >= 0) {
            this.estoque = estoque;
        } else {
            System.out.println("Quantidade inválida");
            this.estoque = 0;
        }
    }
	public double getValor() {return this.valor;}
    public void setValor(double valor) {
        if (valor >= 0.0) {
            this.valor = valor;
        } else {
            System.out.println("Valor inválida");
            this.valor = 0.0;
        }
    }

    public void alterarEstoque(int quantidade) {
        if (this.estoque + quantidade >= 0) {
            this.estoque += quantidade;            
        }
        else {
            System.out.println("Não há estoque o suficiente para essa diminuição.");
        }
    }
}