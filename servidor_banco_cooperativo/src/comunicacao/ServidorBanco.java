package comunicacao;

import java.io.*;
import java.net.ServerSocket;

/**
 * Created by wanderson on 04/04/17.
 */
public class ServidorBanco implements Runnable {

    ServerSocket servidor;

    /**
     * inicia o servidor
     * @throws IOException
     */
    public ServidorBanco() throws IOException {
        System.out.println("Iniciando o servidor...");
        this.servidor = new ServerSocket(7777);
        System.out.println("Servidor iniciado. Aguardando clintes...");
        new Thread(this).start();
    }

    /**
     * método responsável por aceita a conexão com o cliente e iniciar uma nova thread.
     */
    @Override
    public void run() {
        try {
            while (true) {
                // aceitando a conexão com o cliente e inicializando a outra thread
                TrataCliente trataCliente = new TrataCliente(servidor.accept());
                trataCliente.start();
                System.out.println("Um cliente foi conectado! IP: "+trataCliente.getIpUsuario());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String args[]) {
        try {
            new ServidorBanco();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

