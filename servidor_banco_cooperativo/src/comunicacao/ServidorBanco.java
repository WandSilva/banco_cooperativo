package comunicacao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanderson on 04/04/17.
 */
public class ServidorBanco {

    public static void main(String args[]) {

        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Controller controller = new Controller();

        System.out.println("Iniciando servidor...");
        ServerSocket servidor = null;
        try {
            servidor = new ServerSocket(7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Servidor iniciado");
        //controller.carregarUsuarios();
        //controller.carregarContas();
        Socket socket = null;

        String partesMensagem[] = null;
        try {
            socket = servidor.accept();
            System.out.println("cliente conectado");
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }



        while (true) {

            if (socket.isConnected()) {
                try {

                    String mensagemCliente = (String) in.readObject();

                    partesMensagem = mensagemCliente.split("_");
                    System.out.println("Operação recebida: " + partesMensagem[0]);
                    String resposta = null;
                    switch (partesMensagem[0]) {
                        case "100":
                            String numeroConta = controller.criarConta(partesMensagem[1],
                                    partesMensagem[2], partesMensagem[3]);
                            out.writeObject(numeroConta);
                            TimeUnit.SECONDS.sleep(1);
                            break;
                        case "101":
                            controller.criarUsuario(partesMensagem[1], partesMensagem[2],
                                    partesMensagem[3], partesMensagem[4]);
                            TimeUnit.SECONDS.sleep(1);
                            break;
                        case "102":
                            controller.addTitular(partesMensagem[1], partesMensagem[2], partesMensagem[3],
                                    partesMensagem[4], partesMensagem[5]);

                            break;
                        case "103":
                            String resposta103 = controller.logarConta(partesMensagem[1], partesMensagem[2]);
                            out.writeObject(resposta103);
                            TimeUnit.SECONDS.sleep(1);
                            break;
                        case "104":
                            controller.depositar(partesMensagem[1], partesMensagem[2]);
                            TimeUnit.SECONDS.sleep(1);

                            break;
                        case "105":
                            String saldo = controller.verSaldo(partesMensagem[1]);
                            out.writeObject(saldo);
                            TimeUnit.SECONDS.sleep(1);
                            break;
                        case "106":
                            String resposta106 = controller.tranferir(partesMensagem[1], partesMensagem[2], partesMensagem[3]);
                            out.writeObject(resposta106);
                            TimeUnit.SECONDS.sleep(1);
                            break;
                    }
                } catch (IOException | ClassNotFoundException e){
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
