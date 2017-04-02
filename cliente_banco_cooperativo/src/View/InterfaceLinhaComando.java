package View;

import java.util.Scanner;

import controller.Facade;
import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;
import exeption.UsuarioNaoEncontradoExcep;

public class InterfaceLinhaComando {


    public static void main(String args[]) {
        Facade facade = new Facade();

        System.out.println("tam user: " + facade.getControllerUsuario().getListaUsuarios().size());
        System.out.println("tam contas: " + facade.getControllerConta().getListaContas().size());

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

                    System.out.println("Digite seu cpf");
                    String cpf = scan.next();

                    System.out.println("Tipo de usuário -" + "\n" + "[0] -> Pessoa juríca" +
                            "\n" + "[1] -> Pessoa fisica");
                    int tipo = scan.nextInt();

                    System.out.println(primeiroNome +" " +sobreNome + " "+cpf +" "+ tipo);
                    facade.criarUsuario(primeiroNome, sobreNome, cpf, tipo);

                    do {
                        System.out.println("tipo de conta: [0] - corrente / [1] polpanca");
                        aux = scan.nextInt();
                    } while (aux < 0 || aux > 1);


                    System.out.println("Informe uma senha para a conta");
                    String senha = scan.next();
                    int numeroMsg = 0;

                    if (aux == 0) {
                        numeroMsg = facade.criarContaCorrente(senha);
                        System.out.println("CONTA CORRENT CRIADA COM SUCESSO");
                        System.out.println("Numero: " + numeroMsg +"\n"+"Senha: "+senha);
                    }

                    else if (aux == 1) {
                        System.out.println("CONTA POLPANCA CRIADA COM SUCESSO");
                        System.out.println("Numero: " + numeroMsg +"\n"+"Senha: "+senha);
                        numeroMsg = facade.criarContaPolpanca(senha);
                    }

                 //   try {
                  //      facade.addTitular(facade.buscarUsuario(cpf), numeroMsg);
                   // } catch (UsuarioNaoEncontradoExcep usuarioNaoEncontradoExcep) {
                    //    usuarioNaoEncontradoExcep.printStackTrace();
                    //}
                    break;


                case 2:
                    System.out.println("Informe seu CPF");
                    cpf = scan.next();

                  //  try {
                  //      facade.buscarUsuario(cpf);
                 //   } catch (UsuarioNaoEncontradoExcep usuarioNaoEncontradoExcep) {
                 //       usuarioNaoEncontradoExcep.printStackTrace();
                 //   }

                    System.out.println("Informe o numero da conta");
                    int numeroLogin = scan.nextInt();
                    System.out.println("Informe a senha da conta");
                    String senhaLogin = scan.next();

                    int validador =0;
                    try {
                        validador = facade.logarConta(numeroLogin, senhaLogin);
                    } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
                        contaNaoEncontradaExcep.printStackTrace();
                    }

                    if(validador == 0){
                        System.out.println("login/senha invalido(s)");
                    }
                    else{

                    int opcaoMenu2 = 0;
                    int loop2 = 0;
                    while (loop2 == 0) {
                        System.out.println("Selecione a operação" + "\n" +
                                "1 - Consultar saldo" + "\n" +
                                "2 - Depositar" + "\n" +
                                "3 - Transferencia"+ "\n"+
                                "4 - Sair");
                        opcaoMenu2 = scan.nextInt();
                        switch (opcaoMenu2) {
                            case 1:
                                try {
                                    System.out.println("Saldo: " + facade.consultarSaldo(numeroLogin));
                                } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
                                    contaNaoEncontradaExcep.printStackTrace();
                                }
                                break;
                            case 2:
                                System.out.println("digite o valor");
                                int valor = scan.nextInt();
                                try {
                                    facade.depositar(numeroLogin, valor);
                                } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
                                    contaNaoEncontradaExcep.printStackTrace();
                                }
                                break;

                            case 3:
                                System.out.println("Informe o numero da Conta de destino");
                                int numeroDestino = scan.nextInt();
                                System.out.println("Informe o valor");
                                valor = scan.nextInt();
                                try {
                                    facade.tranferir(numeroLogin, numeroDestino, valor);
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
                case 3:
                    facade.listarContas();
                    facade.listarUsuarios();
            }

        }
    }
}

