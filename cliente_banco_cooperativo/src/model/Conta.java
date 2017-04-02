package model;

import java.util.ArrayList;

public abstract class Conta {


	private int numero;
	private String senha;
	private int saldo;
	private ArrayList<Usuario> titulares;

	public Conta(int numero, String senha) {
		this.numero = numero;
		this.senha = senha;
		this.titulares = new ArrayList<Usuario>();
	}
	
	public int getNumero(){
		return this.numero;
	}

	public String getSenha(){
		return this.senha;
	}

	public ArrayList<Usuario> getListaTitulares(){
		return this.titulares;
	}

	public void addTitular(Usuario usuario) {
		this.titulares.add(usuario);
	}

	public void depositar(int valor) {
		this.saldo += valor;
	}

	public void debitar(int valor) {
		this.saldo -= valor;
	}

	public int getSaldo() {
		return this.saldo;
	}

	
}
