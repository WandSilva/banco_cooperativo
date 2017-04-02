package model;

import java.util.ArrayList;

public abstract class Conta {


	
	public Conta(){
	}
	
	public abstract int getNumero();
	
	public abstract String getSenha();
	
	public abstract ArrayList getListaTitulares();
	
	public abstract void addTitular(Usuario usuario);
	
	public abstract void depositar(int valor);
	
	public abstract void debitar(int valor);
	
	public abstract int getSaldo();

	
}
