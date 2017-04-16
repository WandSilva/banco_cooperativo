package comunicacao;

import java.io.*;
import java.net.Socket;

public class Comunicacao {

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    String chave;
    String resposta;
    String pacoteTransmissao;

    public Comunicacao() {
        startSocket();
        chave = null;
        resposta = null;
        pacoteTransmissao = null;
    }

    public void startSocket() {
        try {
            socket = new Socket("192.168.15.10", 7777);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String criarConta(String senha, String tipoConta, String cpfTitular) {
        System.out.println("entrei na comunicação p/ criar conta");
        chave = "100";
        pacoteTransmissao = chave + "_" + senha + "_" + tipoConta + "_" + cpfTitular;

        try {
            out.writeObject(pacoteTransmissao);
            System.out.println("mandei os dados:" + pacoteTransmissao);

            resposta = (String) in.readObject();
            System.out.println("recebi a resposta: " + resposta);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;
    }

    public void criarUsuario(String cpf, String primeiroNome, String sobreNome, String tipo) {
        chave = "101";
        pacoteTransmissao = chave + "_" + cpf + "_" + primeiroNome + "_" + sobreNome + "_" + tipo;

        try {

            out.writeObject(pacoteTransmissao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTitular(String numeroContaLogada, String registroUnico, String primeiroNome, String sobreNome, String tipo) {
        chave = "102";
        pacoteTransmissao = chave + "_" + numeroContaLogada + "_" + registroUnico + "_" + primeiroNome +
                "_" + sobreNome + "_" + tipo;

        try {
            out.writeObject(pacoteTransmissao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String logarConta(String numeroConta, String senha) {
        chave = "103";
        pacoteTransmissao = chave + "_" + numeroConta + "_" + senha;

        String resposta = null;

        try {

            out.writeObject(pacoteTransmissao);


            resposta = (String) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;
    }

    public void depositar(String numeroConta, String valor) {
        chave = "104";

        pacoteTransmissao = chave + "_" + numeroConta + "_" + valor;

        try {
            out.writeObject(pacoteTransmissao);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String verSaldo(String numeroConta) {
        chave = "105";

        pacoteTransmissao = chave + "_" + numeroConta;
        try {

            out.writeObject(pacoteTransmissao);

            resposta = (String) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;
    }

    public String transferir(String contaorigem, String contadestino, String valor) {
        chave = "106";
        pacoteTransmissao = chave+"_"+contaorigem+"_"+contadestino+"_"+valor;
        try {
            out.writeObject(pacoteTransmissao);

            resposta = (String) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;

    }

    public void fecharConexao() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
