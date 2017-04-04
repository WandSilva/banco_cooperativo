package comunicacao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by wanderson on 03/04/17.
 */
public class TesteCliente {

    public static void main(String args[]) throws Exception {
        String mensagem;
        String mensagemModificada;

        BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(System.in));
        Socket cliente = new Socket("192.168.15.10",7777);
        DataOutputStream outToServer = new DataOutputStream(cliente.getOutputStream());
        BufferedReader entradaServidor = new BufferedReader(
                new InputStreamReader(cliente.getInputStream()));
        mensagem = entradaCliente.readLine();
        outToServer.writeBytes(mensagem + "\n");
        mensagemModificada = entradaServidor.readLine();

        System.out.println("Mesagem do servidor: "+mensagemModificada);
        cliente.close();

    }
}
