package model;

import java.util.ArrayList;

public class ContaPolpanca extends Conta {

	private int numero;
	private String senha;
	private int saldo;
	private ArrayList<Usuario> titulares;
	
	public ContaPolpanca(int numero, String Senha) {
		this.numero = numero;
		this.senha = senha;
		this.titulares = new ArrayList<Usuario>();
	}

	@Override
	public int getNumero(){
		return this.numero;
	}
	@Override
	public String getSenha(){
		return this.senha;
	}
	@Override
	public ArrayList<Usuario> getListaTitulares(){
		return this.titulares;
	}
	@Override
	public void addTitular(Usuario usuario) {
		this.titulares.add(usuario);
		
	}

	@Override
	public void depositar(int valor) {
		this.saldo += valor;
	}

	@Override
	public void debitar(int valor) {
		this.saldo -= valor;
		
	}
	@Override
	public int getSaldo() {
		// TODO Auto-generated method stub
		return 0;
	}

}
