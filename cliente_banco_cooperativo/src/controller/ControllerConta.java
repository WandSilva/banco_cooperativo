package controller;

import java.util.ArrayList;

import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;
import exeption.UsuarioNaoEncontradoExcep;
import model.*;

public class ControllerConta {

	private ArrayList<Conta> listaContas;

	public void CriarContaCorrente(String numero, String senha, ArrayList<Usuario> titulares) {
		Conta conta = new ContaCorrente(numero, senha, titulares);
		listaContas.add(conta);
	}

	public void CriarContaPolpanca(String numero, String senha, ArrayList<Usuario> titulares) {
		Conta conta = new ContaPolpanca(numero, senha, titulares);
		listaContas.add(conta);
	}

	public void addTitular(Usuario usuario, String numeroConta) throws UsuarioNaoEncontradoExcep {

		for (Conta conta : listaContas) {
			if (conta.getNumero().equals("numeroConta"))
				conta.addTitular(usuario);
		}
	}

	public Conta buscarConta(String numeroConta) throws ContaNaoEncontradaExcep {
		for (Conta conta : listaContas) {
			if (conta.getNumero().equals("numeroConta"))
				return conta;
		}
		throw new ContaNaoEncontradaExcep("Conta não encontrada");
	}

	public void depositar(String numeroConta, int valor) throws ContaNaoEncontradaExcep {
		Conta conta = this.buscarConta(numeroConta);
		conta.depositar(valor);
	}

	public void tranferir(String numeroConta, String numeroContaDestino, int valor) 
			throws ContaNaoEncontradaExcep, SaldoInsuficienteExcep{
		
		Conta conta = this.buscarConta(numeroConta);
		if (conta.getSaldo() < valor){
			throw new SaldoInsuficienteExcep("Saldo insuficiente");
		}
		else{
			Conta contaDestino = this.buscarConta(numeroContaDestino);
			conta.debitar(valor);
			contaDestino.depositar(valor);
		}
	}

}
