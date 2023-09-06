package br.edu.ufersa.poo.classes;

class Clientes {
	private String nome;
	private String endereco;
	private String cpf;
	
	public Clientes(String nome, String endereco, String cpf) {
		setNome(nome);
		setEndereco(endereco);
		setCpf(cpf);
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
	
	public String getEndereco() {return this.endereco;}
	public void setEndereco(String endereco) {
		if (!endereco.isEmpty()) {
			this.endereco = endereco;
		} else {
			System.out.println("Endereço inválido");
            this.endereco = "Endereço não informado";
		}
	}
	
	public String getCpf() {return this.cpf;}
	public void setCpf(String cpf) {
		if (!cpf.isEmpty()) {
			this.cpf = cpf;
		} else {
			System.out.println("CPF inválido");
            this.cpf = "XXX.XXX.XXX-XX";
		}
	}
}