package model;

import java.util.ArrayList;

public class ContaPolpanca extends Conta {

	private String numero;
	private String senha;
	private int saldo;
	private ArrayList<Usuario> titulares;
	
	public ContaPolpanca(String numero, String Senha, ArrayList<Usuario> titulares) {
		super(numero, Senha, titulares);
	
	}

	@Override
	public String getNumero(){
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
