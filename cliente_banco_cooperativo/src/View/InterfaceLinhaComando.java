package View;

import java.util.Scanner;

import controller.Facade;

public class InterfaceLinhaComando {

	private static Facade facade;
	
	public static void main(String args[]){
		
		System.out.println("SISTEMA BANCARIO" + "\n\n");
		
		Scanner scan = new Scanner(System.in);
		int opcaoMenuInicial =0;
		
		System.out.println("1 - Criar conta.");
		System.out.println("2 - Login");
		
		opcaoMenuInicial = scan.nextInt();
		
		switch (opcaoMenuInicial){
		case 1:
			System.out.println("Digite seu primeiro nome");
			String primeiroNome = scan.next();
			
			System.out.println("Digite seu sobrenome");
			String sobreNome = scan.next();
			
			System.out.println("Digite seu cpf");
			String cpf = scan.next();
			
			System.out.println("Tipo de usuário -" + "\n" + "[0] -> Pessoa juríca" +
			"\n" + "[1] -> Pessoa fisica");
			int tipo = scan.nextInt();
			
			System.out.println(primeiroNome + sobreNome + cpf + tipo);
			facade.criarUsuario(primeiroNome, sobreNome, cpf, tipo);
			
		}
	}
}
