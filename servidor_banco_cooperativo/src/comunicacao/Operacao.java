package comunicacao;

import java.io.IOException;

/**
 * Created by wanderson on 13/04/17.
 */
public class Operacao {

    Controller controller;

    public Operacao() {
        this.controller = new Controller();
    }

    public void operacoes(String mensagemCliente) {


        String partesMensagem[] = mensagemCliente.split("_");

        System.out.println("Operação recebida: " + partesMensagem[0]);

        String resposta = null;
        switch (partesMensagem[0]) {
            case "100":
                String numeroConta = controller.criarConta(partesMensagem[1],
                        partesMensagem[2], partesMensagem[3]);
                //  out.writeObject(numeroConta);
                break;
            case "101":
                controller.criarUsuario(partesMensagem[1], partesMensagem[2],
                        partesMensagem[3], partesMensagem[4]);
                break;
            case "102":
                controller.addTitular(partesMensagem[1], partesMensagem[2], partesMensagem[3],
                        partesMensagem[4], partesMensagem[5]);
                //  out.writeObject(resposta);
                break;
            case "103":
                resposta = controller.logarConta(partesMensagem[1], partesMensagem[2]);
                //  out.writeObject(resposta);
                break;
            case "104":
                resposta = controller.depositar(partesMensagem[1], partesMensagem[2]);
                //  out.writeObject(resposta);
                break;
            case "105":
                System.out.println("case 105");
                String saldo = controller.verSaldo(partesMensagem[1]);
                //  out.writeObject(saldo);
                break;
            case "106":
                break;
        }

    }
}
