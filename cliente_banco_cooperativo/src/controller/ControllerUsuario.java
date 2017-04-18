package controller;

import comunicacao.Comunicacao;

/**
 * Classe responsável por gerenciar as funcionalidas pertencentes a uma usuário e
 * enviar para a classe responsável pela comunicação.
 */
public class ControllerUsuario {

	Comunicacao comunicacao;

	public ControllerUsuario(Comunicacao comunicacao) {
		this.comunicacao = comunicacao;
	}

	/**
	 * recebe os dados sobre um novo usuário e manda para a classe responsável pela comunicação
	 * @param cpf
	 * @param primeiroNome
	 * @param sobreNome
	 * @param tipo
	 */
	public void criarUsuario(String cpf, String primeiroNome, String sobreNome, String tipo){
		comunicacao.criarUsuario(cpf, primeiroNome, sobreNome, tipo);
	}

	//public void criarPessoaFisica(){}

	//public void CriarPessoaJuridica(){}

	//public void buscarUsuario(){}

}
