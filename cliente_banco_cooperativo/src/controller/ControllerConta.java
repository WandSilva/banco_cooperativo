package controller;

import java.util.ArrayList;

import comunicacao.Comunicacao;
import exeption.SaldoInsuficienteExcep;


public class ControllerConta {



	private String dadosConta;
	private String contaLogada;
	private Comunicacao comunicacao;

	public ControllerConta(Comunicacao comunicacao){
		this.dadosConta = null;
		this.comunicacao = comunicacao;
	}

	public String criarConta(String senha, String tipoConta, String cpfTitular) {
		System.out.println("entrei no controller criar conta");
		String numeroConta = comunicacao.criarConta(senha, tipoConta, cpfTitular);
		return numeroConta;
	}

	public void addTitular(String numeroConta, String registroUnico, String primeiroNome, String sobreNome, String tipo) {
		comunicacao.addTitular(numeroConta, registroUnico, primeiroNome, sobreNome, tipo);
	}

	public void depositar(String contaLogada, String valor) {
		comunicacao.depositar(contaLogada, valor);
	}

	public void tranferir(String contaLogada, String numeroContaDestino, String valor) throws SaldoInsuficienteExcep {
		String resposta = this.comunicacao.transferir(contaLogada, numeroContaDestino, valor);

		if (resposta.equals("305")){
			throw new SaldoInsuficienteExcep();
		}
	}

	public void setContaLogada(String contaLogada) {
		this.contaLogada = contaLogada;
	}

	public String getContaLogada() {

		return contaLogada;
	}

	public int logarConta(String numero, String senha) {
		String resopostaServidor = this.comunicacao.logarConta(numero, senha);
		if (resopostaServidor.equals("203"))
			return 1;
		else
			return 0;
	}

	public String consultarSaldo(String numeroConta) {
		return this.comunicacao.verSaldo(numeroConta);
	}


}
