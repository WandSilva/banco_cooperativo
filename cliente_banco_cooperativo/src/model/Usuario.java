package model;

public class Usuario {

	private String primeiroNome;
	private String sobreNome;
	private String cpf;
	private int tipo; // 0 para pessoas física / 1 para pessoa jurídica.
	
	
	public Usuario(String primeiroNome, String sobreNome, String cpf, int tipo){
		this.primeiroNome = primeiroNome;
		this.sobreNome = sobreNome;
		this.cpf = cpf;
		this.tipo = tipo;
	}
	
	public String getPrimeiroNome(){
		return this.primeiroNome;
	}
	public String getSobreNome(){
		return this.sobreNome;
	}
	public String getCpf(){
		return this.cpf;
	}
	
	
}
