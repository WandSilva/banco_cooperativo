package View;

import java.util.Scanner;

import controller.Facade;
import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;

public class InterfaceLinhaComando {


    public static void main(String args[]) {
        Facade facade = new Facade();


        System.out.println("SISTEMA BANCARIO" + "\n\n");
        Scanner scan = new Scanner(System.in);
        String opcaoMenuInicial = "0";
        int aux = 0;


        while (true) {
            System.out.println("1 - Criar conta.");
            System.out.println("2 - Login");

            opcaoMenuInicial = scan.nextLine();
            switch (opcaoMenuInicial) {
                case "1":
                    System.out.println("Digite seu primeiro nome");
                    String primeiroNome = scan.nextLine();

                    System.out.println("Digite seu sobrenome");
                    String sobreNome = scan.nextLine();

                    System.out.println("Digite seu cpf/cnpj");
                    String cpfCnpj = scan.nextLine();

                    System.out.println("Tipo de usuário -" + "\n" + "[0] -> Pessoa juríca" +
                            "\n" + "[1] -> Pessoa fisica");
                    String tipo = scan.nextLine();

                    facade.criarUsuario(cpfCnpj, primeiroNome, sobreNome, tipo);

                    System.out.println("tipo de conta: [0] - corrente / [1] polpanca");

                    String tipoConta = scan.nextLine();


                    System.out.println("Informe uma senha para a conta");
                    String senha = scan.nextLine();

                    String numeroMsg = facade.criarConta(senha, tipoConta, cpfCnpj);
                    System.out.println("CONTA CRIADA COM SUCESSO");
                    System.out.println("Numero: " + numeroMsg + "\n" + "Senha: " + senha);
                    break;

                case "2":
                    System.out.println("Informe seu CPF");
                    String cpfCnpjLogin = scan.nextLine();

                    System.out.println("Informe o numero da conta");
                    String numeroLogin = scan.nextLine();
                    System.out.println("Informe a senha da conta");
                    String senhaLogin = scan.nextLine();

                    int validador = 0;

                    validador = facade.logarConta(numeroLogin, senhaLogin);


                    if (validador == 0) {
                        System.out.println("login/senha invalido(s)");
                    } else {
                        facade.setContaLogada(numeroLogin);
                        String opcaoMenu2 = "0";
                        int loop2 = 0;
                        while (loop2 == 0) {
                            System.out.println("Selecione a operação" + "\n" +
                                    "1 - Consultar saldo" + "\n" +
                                    "2 - Depositar" + "\n" +
                                    "3 - Transferencia" + "\n" +
                                    "4 - Adcionar titular" + "\n" +
                                    "5 - Sair");
                            opcaoMenu2 = scan.nextLine();
                            switch (opcaoMenu2) {
                                case "1":

                                    System.out.println("Saldo: " + facade.consultarSaldo(facade.getContaLogada()));

                                    break;
                                case "2":
                                    System.out.println("digite o valor");
                                    String valor = scan.nextLine();

                                    facade.depositar(facade.getContaLogada(), valor);

                                    break;

                                case "3":
                                    System.out.println("Informe o numero da Conta de destino");
                                    String numeroDestino = scan.nextLine();
                                    System.out.println("Informe o valor");
                                    valor = scan.nextLine();

                                    try {
                                        facade.transferir(facade.getContaLogada(), numeroDestino, valor);
                                    } catch (SaldoInsuficienteExcep saldoInsuficienteExcep) {
                                        System.err.println("Saldo insuficiente");
                                    } catch (ContaNaoEncontradaExcep contaNaoEncontradaExcep) {
                                        System.err.println("Conta de destino não encontrada");
                                    }

                                    break;
                                case "4":
                                    System.out.println("Digite o primeiro nome");
                                    primeiroNome = scan.nextLine();

                                    System.out.println("Digite o sobrenome");
                                    sobreNome = scan.nextLine();

                                    System.out.println("Digite o cpf/cnpj");
                                    cpfCnpj = scan.nextLine();

                                    System.out.println("Tipo de usuário -" + "\n" + "[0] -> Pessoa juríca" +
                                            "\n" + "[1] -> Pessoa fisica");
                                    tipo = scan.nextLine();

                                    facade.addTitular(facade.getContaLogada(), cpfCnpj, primeiroNome, sobreNome, tipo);
                                    break;
                                case "5":
                                    facade.setContaLogada(null);
                                    loop2 = 1;
                                    break;
                            }
                        }
                    }
                    break;
                case "3":
                    facade.fecharConexao();
                    System.exit(0);
                    break;
            }

        }
    }
}

