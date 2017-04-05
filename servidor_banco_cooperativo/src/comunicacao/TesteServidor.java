package comunicacao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TesteServidor {

    public static void main (String args[]) throws Exception {

       // String mensagemCliente;
       // String mensagemModificada;

       // System.out.println("Iniciando servidor...");
       // ServerSocket servidor = new ServerSocket(7777);
       // System.out.println("Servidor iniciado");

//        while (true){
       //     Socket connectionSocker = servidor.accept();

         //   BufferedReader entradaCliente = new BufferedReader(
           //         new InputStreamReader(connectionSocker.getInputStream()));

//            DataOutputStream outToCliente = new DataOutputStream
  //                  (connectionSocker.getOutputStream());

//            mensagemCliente = entradaCliente.readLine();
  //          mensagemModificada = mensagemCliente.toUpperCase() + "\n";
    //        outToCliente.writeBytes(mensagemModificada+"\n");
      //  }


        ServerSocket servidor = null;
        Socket socket;
        ObjectOutputStream out;
        ObjectInputStream in;
        List<String> lista = new ArrayList<String>();
        lista.add("Wand 123 12343 234343");
        lista.add("Silva 143 67343 165343");
        lista.add("Wbs 323 43566 234343");

        try {
            System.out.println("Iniciando servidor...");
            servidor = new ServerSocket( 4321,300 );
            System.out.println("Servidor iniciado");
        } catch( IOException e ) {
            System.out.println( e );
        }
        while( true ) {
            try {
                socket = servidor.accept();



                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(lista);//passando a lista

                in= new ObjectInputStream(socket.getInputStream());

                System.out.println(in.readObject());//recebendo "Ok" do user

             



                socket.close();
            } catch( IOException e ) {
                System.out.println( e );
            }
        }























    }

}
