package comunicacao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TesteServidor {

    public static void main (String args[]) throws Exception {

        String mensagemCliente;
        String mensagemModificada;

        System.out.println("Iniciando servidor...");
        ServerSocket servidor = new ServerSocket(7777);
        System.out.println("Servidor iniciado");

        while (true){
            Socket connectionSocker = servidor.accept();

            BufferedReader entradaCliente = new BufferedReader(
                    new InputStreamReader(connectionSocker.getInputStream()));

            DataOutputStream outToCliente = new DataOutputStream
                    (connectionSocker.getOutputStream());

            mensagemCliente = entradaCliente.readLine();
            mensagemModificada = mensagemCliente.toUpperCase() + "\n";
            outToCliente.writeBytes(mensagemModificada+"\n");
        }
    }

}
