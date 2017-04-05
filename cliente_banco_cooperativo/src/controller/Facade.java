package controller;

import java.io.IOException;
import java.util.ArrayList;

import comunicacao.Comunicacao;
import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;
import exeption.UsuarioNaoEncontradoExcep;
import model.Conta;
import model.ContaCorrente;
import model.ContaPolpanca;
import model.Usuario;

public class Facade {

	private ControllerConta controllerConta;
	private ControllerUsuario controllerUsuario;
	private Comunicacao comunicacao;
	
	public Facade() {
		this.comunicacao = new Comunicacao();
		this.controllerConta = new ControllerConta(comunicacao);
		this.controllerUsuario = new ControllerUsuario(comunicacao);
	}
	
	// ************************* METODOS DO ControllerConta ****************************//
	
	public int criarContaCorrente(String senha) {
		return this.controllerConta.criarContaCorrente(senha);
	}

	public int criarContaPolpanca(String senha) {
		return this.controllerConta.criarContaPolpanca(senha);
	}

	public void addTitular(Usuario usuario, Conta contaLogada) throws UsuarioNaoEncontradoExcep {
		this.controllerConta.addTitular(usuario, contaLogada);
	}

	public Conta getContaLogada(){
		return this.controllerConta.getContaLogada();
	}

	public Conta buscarConta(int numeroConta) throws ContaNaoEncontradaExcep {
		return this.controllerConta.buscarConta(numeroConta);
	}

	public void depositar(Conta conta, int valor) throws ContaNaoEncontradaExcep {
		this.controllerConta.depositar(conta, valor);
	}

	public void tranferir(Conta contaLogada, int numeroContaDestino, int valor)
			throws ContaNaoEncontradaExcep, SaldoInsuficienteExcep{
		
		this.controllerConta.tranferir(contaLogada, numeroContaDestino, valor);
	}

	public int logarConta(int numeroConta, String senha) throws ContaNaoEncontradaExcep {
		return this.controllerConta.logarConta(numeroConta, senha);
	}
	public int consultarSaldo(Conta conta) throws ContaNaoEncontradaExcep {
		return this.controllerConta.consultarSaldo(conta);
	}

	public void listarContas(){
		this.controllerConta.listarContas();
	}
	
	//********************************* METODOS DO CONTROLLER USUARIO ******************************//
	public void criarPessoaFisica(String primeiroNome, String sobreNome, String cpf){
		this.controllerUsuario.criarPessoaFisica(primeiroNome, sobreNome, cpf);
	}

	public void criarPessoaJuridica(String primeiroNome, String sobreNome, String cpf){
		this.controllerUsuario.criarPessoaFisica(primeiroNome, sobreNome, cpf);
	}
	
	public Usuario buscarUsuario(String cpf) throws UsuarioNaoEncontradoExcep{			
		return this.controllerUsuario.buscarUsuario(cpf);
	}

	public void listarUsuarios(){
		this.controllerUsuario.listarUsuarios();
	}
}
