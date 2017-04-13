package controller;

import comunicacao.Comunicacao;

public class ControllerUsuario {

	Comunicacao comunicacao;

	public ControllerUsuario(Comunicacao comunicacao) {
		this.comunicacao = comunicacao;
	}
	
	
	public void criarUsuario(String cpf, String primeiroNome, String sobreNome, String tipo){
		comunicacao.criarUsuario(cpf, primeiroNome, sobreNome, tipo);
	}

	//public void criarPessoaFisica(){}

	//public void CriarPessoaJuridica(){}

	//public void buscarUsuario(){}

}
