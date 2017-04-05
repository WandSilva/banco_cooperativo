package comunicacao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by wanderson on 04/04/17.
 */
public class ServidorBanco {

    public static void main (String args[]){


        ObjectOutputStream out;

        try {

            System.out.println("Iniciando servidor...");
            ServerSocket servidor = new ServerSocket(7777);
            System.out.println("Servidor iniciado");

            Socket socket = servidor.accept();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String chaveCliente = (String) in.readObject();

            if(chaveCliente.equals("carregarUsuarios")){
                System.out.println("carregamentos de um usuário solicitado pelo cliente");


                out = new ObjectOutputStream(socket.getOutputStream());
                //out.writeObject();


            }
            else if(!chaveCliente.equals("carregarUsuarios"))
                System.out.println("chave errada! Recebido: "+chaveCliente);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
