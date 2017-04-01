package model;

import java.util.ArrayList;

public abstract class Conta {

	private String numero;
	private String senha;
	private int saldo;
	private ArrayList<Usuario> titulares;
	
	public Conta(String numero, String Senha, ArrayList<Usuario> titulares){
		this.numero = numero;
		this.senha = senha;
		this.titulares = titulares;
	}
	
	public abstract String getNumero();
	
	public abstract String getSenha();
	
	public abstract ArrayList getListaTitulares();
	
	public abstract void addTitular(Usuario usuario);
	
	public abstract void depositar(int valor);
	
	public abstract void debitar(int valor);
	
	public abstract int getSaldo();
	
	
	
}
