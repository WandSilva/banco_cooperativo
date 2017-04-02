package controller;

import java.util.ArrayList;

import exeption.UsuarioNaoEncontradoExcep;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.Usuario;

public class ControllerUsuario {

	private ArrayList<Usuario> listaUsuarios;

	public ControllerUsuario(){
		this.listaUsuarios = new ArrayList<Usuario>();
	}
	
	
	public void criarPessoaFisica(String primeiroNome, String sobreNome, String cpf){
		Usuario usuario = new PessoaFisica(primeiroNome, sobreNome, cpf);
		listaUsuarios.add(usuario);
	}
	public void criarPessoaJuridica(String primeiroNome, String sobreNome, String cpf){
		Usuario usuario = new PessoaJuridica(primeiroNome, sobreNome, cpf);
		listaUsuarios.add(usuario);
	}

	public Usuario buscarUsuario(String cpfCnpj) throws UsuarioNaoEncontradoExcep{
		for(Usuario usuario: listaUsuarios){
			if(usuario.getRegistroUnico().equals(cpfCnpj))
				return usuario;
		}
		throw new UsuarioNaoEncontradoExcep("Usuario Não encontrado");
	}

	public void listarUsuarios(){
		for (Usuario usuario: listaUsuarios){
			System.out.println("Nome: "+usuario.getPrimeiroNome() +" "+"Cpf: "+usuario.getRegistroUnico());
		}
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
}
