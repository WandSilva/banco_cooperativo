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
	
	public void CriarContaCorrente(String numero, String senha, ArrayList<Usuario> titulares) {
		this.controllerConta.CriarContaCorrente(numero, senha, titulares);
	}

	public void CriarContaPolpanca(String numero, String senha, ArrayList<Usuario> titulares) {
		this.controllerConta.CriarContaPolpanca(numero, senha, titulares);
	}

	public void addTitular(Usuario usuario, String numeroConta) throws UsuarioNaoEncontradoExcep {
		this.controllerConta.addTitular(usuario, numeroConta);
	}

	public Conta buscarConta(String numeroConta) throws ContaNaoEncontradaExcep {
		return this.controllerConta.buscarConta(numeroConta);
	}

	public void depositar(String numeroConta, int valor) throws ContaNaoEncontradaExcep {
		this.controllerConta.depositar(numeroConta, valor);
	}

	public void tranferir(String numeroConta, String numeroContaDestino, int valor) 
			throws ContaNaoEncontradaExcep, SaldoInsuficienteExcep{
		
		this.controllerConta.tranferir(numeroConta, numeroContaDestino, valor);
	}
	
	//********************************* METODOS DO CONTROLLER USUARIO ******************************//
	public void criarUsuario(String primeiroNome, String sobreNome, String cpf, int tipo){
		this.controllerUsuario.criarUsuario(primeiroNome, sobreNome, cpf, tipo);
	}
	
	public Usuario buscarUsuario(String cpf) throws UsuarioNaoEncontradoExcep{			
		return this.controllerUsuario.buscarUsuario(cpf);
	}
}
