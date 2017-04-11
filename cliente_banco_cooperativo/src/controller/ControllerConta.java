package controller;

import java.util.ArrayList;

import comunicacao.Comunicacao;
import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;
import exeption.UsuarioNaoEncontradoExcep;
import model.*;

public class ControllerConta {



	private String dadosConta;
	private String contaLogada;
	private Comunicacao comunicacao;

	public ControllerConta(Comunicacao comunicacao){
		this.dadosConta = null;
		this.comunicacao = comunicacao;
	}

	public String criarConta(String senha, String tipoConta, String cpfTitular) {

		String numeroConta = comunicacao.criarConta(senha, tipoConta, cpfTitular);
		this.setContaLogada(numeroConta);
		return null; //tem q retornar o numero da conta
	}

	public void addTitular(String cpfUsuario, String numeroContaLogada) {
		comunicacao.addTitular(cpfUsuario, numeroContaLogada);
	}

	public void depositar(String contaLogada, int valor) throws ContaNaoEncontradaExcep {
	}

	public void tranferir(String contaLogada, String numeroContaDestino, int valor)
			throws ContaNaoEncontradaExcep, SaldoInsuficienteExcep{

	}

	public void setContaLogada(String contaLogada) {
		this.contaLogada = contaLogada;
	}

	public String getContaLogada() {

		return contaLogada;
	}

	public int logarConta(int numero, String senha) throws ContaNaoEncontradaExcep {
		return 0;
	}

	public int consultarSaldo(String numeroConta) throws ContaNaoEncontradaExcep {
		return 0;
	}


}
