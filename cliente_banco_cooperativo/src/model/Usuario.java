package model;

public abstract class Usuario {

	private String primeiroNome;
	private String sobreNome;
	private String registroUnico;
	
	
	public Usuario(String primeiroNome, String sobreNome){
		this.primeiroNome = primeiroNome;
		this.sobreNome = sobreNome;

	}
	
	public String getPrimeiroNome(){
		return this.primeiroNome;
	}
	public String getSobreNome(){
		return this.sobreNome;
	}
	public abstract String getRegistroUnico();
	
	
}
