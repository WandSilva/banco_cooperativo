package View;

import java.util.Scanner;

import controller.Facade;
import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;
import exeption.UsuarioNaoEncontradoExcep;

public class InterfaceLinhaComando {


    public static void main(String args[]) throws UsuarioNaoEncontradoExcep {
        Facade facade = new Facade();


        System.out.println("SISTEMA BANCARIO" + "\n\n");
        Scanner scan = new Scanner(System.in);
        int opcaoMenuInicial = 0;
        int aux = 0;


        while (true) {
            System.out.println("1 - Criar conta.");
            System.out.println("2 - Login");

            opcaoMenuInicial = scan.nextInt();
            switch (opcaoMenuInicial) {
                case 1:
                    System.out.println("Digite seu primeiro nome");
                    String primeiroNome = scan.next();

                    System.out.println("Digite seu sobrenome");
                    String sobreNome = scan.next();

                    System.out.println("Digite seu cpf/cnpj");
                    String cpfCnpj = scan.next();

                    String tipo;
                    do {
                        System.out.println("Tipo de usuário -" + "\n" + "[0] -> Pessoa juríca" +
                                "\n" + "[1] -> Pessoa fisica");
                        tipo = scan.nextLine();
                    } while (!tipo.equals("0") || !tipo.equals("1"));
                    System.out.println(primeiroNome + " " + sobreNome + " " + cpfCnpj + " " + tipo);

                    facade.criarUsuario(cpfCnpj, primeiroNome, sobreNome, tipo);

                    String tipoConta = null;
                    do {
                        System.out.println("tipo de conta: [0] - corrente / [1] polpanca");
                        aux = scan.nextInt();
                    } while (!tipoConta.equals("0") || !tipoConta.equals("1"));


                    System.out.println("Informe uma senha para a conta");
                    String senha = scan.next();
                    String numeroMsg = null;


                    numeroMsg = facade.criarConta(senha, tipoConta, cpfCnpj);
                    System.out.println("CONTA CRIADA COM SUCESSO");
                    System.out.println("Numero: " + numeroMsg + "\n" + "Senha: " + senha);

                    facade.addTitular(cpfCnpj, numeroMsg);



                case 2:
                    System.out.println("Informe seu CPF");
                    cpfCnpj = scan.next();

                    System.out.println("Informe o numero da conta");
                    int numeroLogin = scan.nextInt();
                    System.out.println("Informe a senha da conta");
                    String senhaLogin = scan.next();

                    int validador = 0;
                    try {
                        validador = facade.logarConta(numeroLogin, senhaLogin);
                    } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
                        contaNaoEncontradaExcep.printStackTrace();
                    }

                    if (validador == 0) {
                        System.out.println("login/senha invalido(s)");
                    } else {

                        int opcaoMenu2 = 0;
                        int loop2 = 0;
                        while (loop2 == 0) {
                            System.out.println("Selecione a operação" + "\n" +
                                    "1 - Consultar saldo" + "\n" +
                                    "2 - Depositar" + "\n" +
                                    "3 - Transferencia" + "\n" +
                                    "4 - Sair");
                            opcaoMenu2 = scan.nextInt();
                            switch (opcaoMenu2) {
                                case 1:
                                    try {
                                        System.out.println("Saldo: " + facade.consultarSaldo(facade.getContaLogada()));
                                    } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
                                        contaNaoEncontradaExcep.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    System.out.println("digite o valor");
                                    int valor = scan.nextInt();
                                    try {
                                        facade.depositar(facade.getContaLogada(), valor);
                                    } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
                                        contaNaoEncontradaExcep.printStackTrace();
                                    }
                                    break;

                                case 3:
                                    System.out.println("Informe o numero da Conta de destino");
                                    String numeroDestino = scan.nextLine();
                                    System.out.println("Informe o valor");
                                    valor = scan.nextInt();
                                    try {
                                        facade.tranferir(facade.getContaLogada(), numeroDestino, valor);
                                    } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
                                        contaNaoEncontradaExcep.printStackTrace();
                                    } catch (SaldoInsuficienteExcep saldoInsuficienteExcep) {
                                        saldoInsuficienteExcep.printStackTrace();
                                    }
                                    break;
                                case 4:
                                    loop2 = 1;
                                    break;
                            }
                        }
                    }
                break;
            }

        }
    }
}

