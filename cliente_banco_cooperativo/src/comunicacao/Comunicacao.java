package comunicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Comunicacao {

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    String chave;
    String pacoteTransmissao;

    public Comunicacao() {
        startSocket();
        chave = null;
        pacoteTransmissao = null;
    }

    public void startSocket() {
        try {
            socket = new Socket("127.0.0.1", 7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String criarConta(String senha, String tipoConta, String cpfTitular) {
        chave = "100";
        pacoteTransmissao = chave + "_" + senha + "_" + tipoConta + "_" + cpfTitular + "\n";
        String numeroContaCriada = null;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(pacoteTransmissao);

            in = new ObjectInputStream(socket.getInputStream());
            numeroContaCriada = (String) in.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return numeroContaCriada;
    }

    public void criarUsuario(String cpf, String primeiroNome, String sobreNome, String tipo) {
        chave = "101";
        pacoteTransmissao = chave + "_" + cpf + "_" + primeiroNome + "_" + sobreNome + "_" + tipo + "\n";

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(pacoteTransmissao);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addTitular(String cpfUsuario, String numeroContaLogada) {
        chave = "102";
        pacoteTransmissao = chave + "_" + cpfUsuario + "_" + numeroContaLogada + "\n";

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(pacoteTransmissao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
