package controller;


import comunicacao.Comunicacao;
import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;

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
	
	public String criarConta(String senha, String tipoConta, String cpfTitular) {
		return this.controllerConta.criarConta(senha, tipoConta, cpfTitular);
	}


	public void addTitular(String cpfUsuario, String numeroContaLogada) {
		this.controllerConta.addTitular(cpfUsuario, numeroContaLogada);
	}

	public String getContaLogada(){
		return this.controllerConta.getContaLogada();
	}

	public void depositar(String contaLogada, int valor) throws ContaNaoEncontradaExcep {
		this.controllerConta.depositar(contaLogada, valor);
	}

	public void tranferir(String contaLogada, String numeroContaDestino, int valor)
			throws ContaNaoEncontradaExcep, SaldoInsuficienteExcep{
		
		this.controllerConta.tranferir(contaLogada, numeroContaDestino, valor);
	}

	public int logarConta(int numeroConta, String senha) throws ContaNaoEncontradaExcep {
		return this.controllerConta.logarConta(numeroConta, senha);
	}
	public int consultarSaldo(String contaLogada) throws ContaNaoEncontradaExcep {
		return this.controllerConta.consultarSaldo(contaLogada);
	}
	
	//********************************* METODOS DO CONTROLLER USUARIO ******************************//
	public void criarUsuario(String cpf, String primeiroNome, String sobreNome, String tipo){

		this.controllerUsuario.criarUsuario(cpf, primeiroNome, sobreNome, tipo);
	}
}
