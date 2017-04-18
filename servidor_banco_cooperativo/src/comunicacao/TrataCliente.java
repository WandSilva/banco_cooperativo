package comunicacao;

import util.RevertCriptografia5dm;

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

    /**
     * Método responsável por fazer o controle de threads e identificar quais são as requisições
     * solicitadas por cada cliente.
     */
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
                        //pega a mensagem do cliente
                        mensagemCliente = (String) in.readObject();
                    } catch (IOException e) {
                        socket.close();
                    }

                    partesMensagem = mensagemCliente.split("_");
                    String resposta = null;
                    //verifica qual foi a requisição do cliente
                    switch (partesMensagem[0]) {
                        //100 - criar conta
                        case "100":
                            String senha = new RevertCriptografia5dm().revertCriptografia5dm(partesMensagem[1]);
                            String numeroConta = controller.criarConta(senha,
                                    partesMensagem[2], partesMensagem[3]);
                            out.writeObject(numeroConta);

                            break;
                        //101 - criar usuario
                        case "101":
                            controller.criarUsuario(partesMensagem[1], partesMensagem[2],
                                    partesMensagem[3], partesMensagem[4]);

                            break;
                        //102 - add titular
                        case "102":
                            senha = new RevertCriptografia5dm().revertCriptografia5dm(partesMensagem[6]);
                            resposta = controller.addTitular(partesMensagem[1], partesMensagem[2], partesMensagem[3],
                                    partesMensagem[4], partesMensagem[5], senha);
                            out.writeObject(resposta);

                            break;
                        case "103":
                            //103 - logar
                            senha = new RevertCriptografia5dm().revertCriptografia5dm(partesMensagem[2]);
                            resposta = controller.logarConta(partesMensagem[1], senha);
                            out.writeObject(resposta);

                            break;
                        //104 - depositar
                        case "104":
                            controller.depositar(partesMensagem[1], partesMensagem[2]);
                            break;
                        //105 - ver saldo
                        case "105":
                            String saldo = controller.verSaldo(partesMensagem[1]);
                            out.writeObject(saldo);
                            break;
                        //106 - transferir
                        case "106":
                            resposta = controller.tranferir(partesMensagem[1], partesMensagem[2], partesMensagem[3]);
                            out.writeObject(resposta);
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

    /**
     * pega o IP do cliente conectado
     * @return
     */
    public String getIpUsuario() {
        String ip = socket.getInetAddress().getHostAddress();
        return ip;
    }
}
