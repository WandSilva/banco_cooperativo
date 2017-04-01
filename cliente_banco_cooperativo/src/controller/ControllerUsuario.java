package controller;

import java.util.ArrayList;

import exeption.UsuarioNaoEncontradoExcep;
import model.Usuario;

public class ControllerUsuario {

	private ArrayList<Usuario> listaUsuarios;
	
	
	public void criarUsuario(String primeiroNome, String sobreNome, String cpf, int tipo){
		Usuario usuario = new Usuario(primeiroNome, sobreNome, cpf, tipo);
		listaUsuarios.add(usuario);
	}
	
	public Usuario buscarUsuario(String cpf) throws UsuarioNaoEncontradoExcep{			
		for(Usuario usuario: listaUsuarios){
			if(usuario.getCpf().equals("cpf"))
				return usuario;
		}
		throw new UsuarioNaoEncontradoExcep("Usuario Não encontrado");
	}
}
