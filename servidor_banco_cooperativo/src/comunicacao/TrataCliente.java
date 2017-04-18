package comunicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanderson on 13/04/17.
 */
public class TrataCliente extends Thread {
    private Socket socket;

    public TrataCliente(Socket s) {
        socket = s;
    }

    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Controller controller = new Controller();

        controller.carregarUsuarios();
        controller.carregarContas();

        String partesMensagem[];
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

            if (socket.isConnected()) {
                try {

                    String mensagemCliente = null;
                    try {
                        mensagemCliente = (String) in.readObject();
                    } catch (IOException e) {
                        socket.close();
                    }

                    partesMensagem = mensagemCliente.split("_");
                    System.out.println("Operação recebida: " + partesMensagem[0]);
                    String resposta = null;
                    switch (partesMensagem[0]) {
                        case "100":
                            String numeroConta = controller.criarConta(partesMensagem[1],
                                    partesMensagem[2], partesMensagem[3]);
                            out.writeObject(numeroConta);

                            break;
                        case "101":
                            controller.criarUsuario(partesMensagem[1], partesMensagem[2],
                                    partesMensagem[3], partesMensagem[4]);

                            break;
                        case "102":
                            controller.addTitular(partesMensagem[1], partesMensagem[2], partesMensagem[3],
                                    partesMensagem[4], partesMensagem[5], partesMensagem[6]);

                            break;
                        case "103":
                            String resposta103 = controller.logarConta(partesMensagem[1], partesMensagem[2]);
                            out.writeObject(resposta103);

                            break;
                        case "104":
                            controller.depositar(partesMensagem[1], partesMensagem[2]);
                            break;
                        case "105":
                            String saldo = controller.verSaldo(partesMensagem[1]);
                            out.writeObject(saldo);
                            break;
                        case "106":
                            String resposta106 = controller.tranferir(partesMensagem[1], partesMensagem[2], partesMensagem[3]);
                            out.writeObject(resposta106);
                            break;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public String getIpUsuario() {
        String ip = socket.getInetAddress().getHostAddress();
        return ip;
    }
}
