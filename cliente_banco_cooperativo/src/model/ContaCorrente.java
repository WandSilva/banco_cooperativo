package model;

import java.util.ArrayList;

public class ContaCorrente extends Conta{

	private String numero;
	private String senha;
	private int saldo;
	private ArrayList<Usuario> titulares;
	
	public ContaCorrente(String numero, String senha, ArrayList<Usuario> titulares) {
		super(numero, senha, titulares);
		// TODO Auto-generated constructor stub
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
		return this.saldo;
	}
	
	

}
