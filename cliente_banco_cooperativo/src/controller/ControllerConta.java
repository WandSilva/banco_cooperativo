package controller;

import java.util.ArrayList;

import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;
import exeption.UsuarioNaoEncontradoExcep;
import model.*;

public class ControllerConta {



	private ArrayList<Conta> listaContas;
	private int numeroConta;
	private Conta contaLogada;

	public ControllerConta(){
		this.listaContas = new ArrayList<Conta>();
		this.numeroConta = 100;
	}
	//verificar se a conta já existe;
	public int criarContaCorrente(String senha) {

		int numero = numeroConta;
		Conta conta = new ContaCorrente(numero, senha);
		listaContas.add(conta);
		this.setContaLogada(conta);
		numeroConta++;
		return numero;
	}

	public int criarContaPolpanca(String senha) {
		int numero = numeroConta;
		Conta conta = new ContaCorrente(numero, senha);
		listaContas.add(conta);
		this.setContaLogada(conta);
		numeroConta++;
		return numero;
	}

	public void addTitular(Usuario usuario, Conta contaLogada) throws UsuarioNaoEncontradoExcep {
		contaLogada.addTitular(usuario);

	}

	public Conta buscarConta(int numeroConta) throws ContaNaoEncontradaExcep {

		for (int i=0; i<listaContas.size(); i++) {
			if(listaContas.get(i).getNumero() == numeroConta ){
				return listaContas.get(i);
			}
		}


		throw new ContaNaoEncontradaExcep("Conta não encontrada");
	}

	public void depositar(Conta conta, int valor) throws ContaNaoEncontradaExcep {
		conta.depositar(valor);
	}

	public void tranferir(Conta contaLogada, int numeroContaDestino, int valor)
			throws ContaNaoEncontradaExcep, SaldoInsuficienteExcep{

		if (contaLogada.getSaldo() < valor){
			throw new SaldoInsuficienteExcep("Saldo insuficiente");
		}
		else{
			Conta contaDestino = this.buscarConta(numeroContaDestino);
			contaLogada.debitar(valor);
			contaDestino.depositar(valor);
		}
	}
	public int logarConta(int numero, String senha) throws ContaNaoEncontradaExcep {
		Conta conta = this.buscarConta(numero);
		String aux = conta.getSenha();

		if (aux.equals(senha)){
			this.setContaLogada(conta);
			return 1;
		}
		else
			return 0;
	}

	public int consultarSaldo(Conta conta) throws ContaNaoEncontradaExcep {
		return conta.getSaldo();
	}

	public void listarContas(){
		for(Conta conta: listaContas){
			System.out.println("Numero: "+conta.getNumero()+ " "+ "Senha: "+conta.getSenha());
		}
	}

	public Conta getContaLogada() {
		return contaLogada;
	}

	public void setContaLogada(Conta contaLogada) {
		this.contaLogada = contaLogada;
	}

	public ArrayList<Conta> getListaContas() {
		return listaContas;
	}
}
