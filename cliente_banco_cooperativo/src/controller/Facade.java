package controller;

import java.util.ArrayList;

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
	
	public Facade(){
		this.controllerConta = new ControllerConta();
		this.controllerUsuario = new ControllerUsuario();
	}
	
	// ************************* METODOS DO ControllerConta ****************************//
	
	public int criarContaCorrente(String senha) {
		return this.controllerConta.criarContaCorrente(senha);
	}

	public int criarContaPolpanca(String senha) {
		return this.controllerConta.criarContaPolpanca(senha);
	}

	public void addTitular(Usuario usuario, int numeroConta) throws UsuarioNaoEncontradoExcep {
		this.controllerConta.addTitular(usuario, numeroConta);
	}

	public Conta buscarConta(int numeroConta) throws ContaNaoEncontradaExcep {
		return this.controllerConta.buscarConta(numeroConta);
	}

	public void depositar(int numeroConta, int valor) throws ContaNaoEncontradaExcep {
		this.controllerConta.depositar(numeroConta, valor);
	}

	public void tranferir(int numeroConta, int numeroContaDestino, int valor)
			throws ContaNaoEncontradaExcep, SaldoInsuficienteExcep{
		
		this.controllerConta.tranferir(numeroConta, numeroContaDestino, valor);
	}

	public int logarConta(int numeroConta, String senha) throws ContaNaoEncontradaExcep {
		return this.controllerConta.logarConta(numeroConta, senha);
	}
	public int consultarSaldo(int numero) throws ContaNaoEncontradaExcep {
		return this.controllerConta.consultarSaldo(numero);
	}

	
	//********************************* METODOS DO CONTROLLER USUARIO ******************************//
	public void criarUsuario(String primeiroNome, String sobreNome, String cpf, int tipo){
		this.controllerUsuario.criarUsuario(primeiroNome, sobreNome, cpf, tipo);
	}
	
	public Usuario buscarUsuario(String cpf) throws UsuarioNaoEncontradoExcep{			
		return this.controllerUsuario.buscarUsuario(cpf);
	}

	public ControllerConta getControllerConta() {
		return controllerConta;
	}

	public ControllerUsuario getControllerUsuario() {
		return controllerUsuario;
	}
	public void listarContas(){
		this.controllerConta.listarContas();
	}
	public void listarUsuarios(){
		this.controllerUsuario.listarUsuarios();
	}
}
