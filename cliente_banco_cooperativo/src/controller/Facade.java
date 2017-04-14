package controller;


import comunicacao.Comunicacao;
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


	public void addTitular(String numeroConta, String registroUnico, String primeiroNome, String sobreNome, String tipo) {
		this.controllerConta.addTitular(numeroConta, registroUnico, primeiroNome, sobreNome, tipo);
	}

	public String getContaLogada(){
		return this.controllerConta.getContaLogada();
	}

	public void depositar(String contaLogada, String valor) {
		this.controllerConta.depositar(contaLogada, valor);
	}

	public void tranferir(String contaLogada, String numeroContaDestino, String valor) throws SaldoInsuficienteExcep {
		
		this.controllerConta.tranferir(contaLogada, numeroContaDestino, valor);
	}

	public int logarConta(String numeroConta, String senha)  {
		return this.controllerConta.logarConta(numeroConta, senha);
	}
	public void setContaLogada(String numeroConta){
		this.controllerConta.setContaLogada(numeroConta);
	}

	public String consultarSaldo(String contaLogada) {
		return this.controllerConta.consultarSaldo(contaLogada);
	}

	public void fecharConexao(){
		this.controllerConta.fecharConexao();
	}
	
	//********************************* METODOS DO CONTROLLER USUARIO ******************************//
	public void criarUsuario(String cpf, String primeiroNome, String sobreNome, String tipo){

		this.controllerUsuario.criarUsuario(cpf, primeiroNome, sobreNome, tipo);
	}
}
