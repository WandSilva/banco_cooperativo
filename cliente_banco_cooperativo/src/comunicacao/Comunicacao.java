package comunicacao;

import controller.Facade;
import model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Comunicacao {

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    String chave;

    public Comunicacao(){
        startSocket();
    }

    public void startSocket(){
        try {
            socket = new Socket("127.0.0.1", 7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String carregarDadosUsuario(String cpf) {
        chave = "carregarUsuario";
        String dadosUsuario = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(chave + "_" + cpf);

            in = new ObjectInputStream(socket.getInputStream());
            dadosUsuario = (String) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dadosUsuario;
    }

    public String carregarDadosContas(String numeroConta) {
        chave = "carregarConta";
        String dadosConta = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(chave + "_" + numeroConta);

            in = new ObjectInputStream(socket.getInputStream());
            dadosConta = (String) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dadosConta;
    }
}
