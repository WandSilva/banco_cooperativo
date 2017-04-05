package comunicacao;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanderson on 03/04/17.
 */
public class TesteCliente {

    public static void main(String args[]) throws Exception {
        //String mensagem;
        //String mensagemModificada;

        //BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(System.in));
        //Socket cliente = new Socket("192.168.15.10",7777);
        //DataOutputStream outToServer = new DataOutputStream(cliente.getOutputStream());
        //BufferedReader entradaServidor = new BufferedReader(
        //        new InputStreamReader(cliente.getInputStream()));
        //mensagem = entradaCliente.readLine();
        //outToServer.writeBytes(mensagem + "\n");
        //mensagemModificada = entradaServidor.readLine();

        //System.out.println("Mesagem do servidor: "+mensagemModificada);
        //cliente.close();

        Socket socket;
        ObjectInputStream in;
        ObjectOutputStream out;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"),4321 ); //ip , porta


            in= new ObjectInputStream(socket.getInputStream());
            List<String> lista=(ArrayList<String>) in.readObject();//recebendo a lista


            File arquivo = new File("arquivoCliete.txt");
            BufferedWriter escrever = new BufferedWriter (new FileWriter(arquivo));

            out = new ObjectOutputStream(socket.getOutputStream());

            for(String i: lista) {
                System.out.println(i);
                escrever.write(i+"\n");
                out.writeObject("ok");
            }
            escrever.flush();
            escrever.close();

            socket.close();
        }
        catch( IOException e ) {
            System.out.println( e );
        }
        catch( ClassNotFoundException e ) {
            System.out.println( e );
        }





























    }
}
