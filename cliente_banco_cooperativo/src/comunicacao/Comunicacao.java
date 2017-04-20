package comunicacao;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * Classe respossável pela comunicação entre cliente e servidor
 */
public class Comunicacao {

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    String chave; //código q será usado para identificar as requisições
    String resposta;
    String pacoteTransmissao;
    static String ip;

    public Comunicacao() {
        startSocket();
        chave = null;
        resposta = null;
        pacoteTransmissao = null;
    }

    /**
     * Inicia o socket e os atibutos responsáveis pelo envio e
     * recebimentos de dados do servidor
     */
    public void startSocket() {
        try {
            socket = new Socket(ip, 7777);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Manda para o servidor a requisição para criar uma nova conta
     * @param senha
     * @param tipoConta
     * @param cpfTitular
     * @return numero da conta criada
     */
    public String criarConta(String senha, String tipoConta, String cpfTitular) {
        chave = "100";
        pacoteTransmissao = chave + "_" + senha + "_" + tipoConta + "_" + cpfTitular;

        try {
            out.writeObject(pacoteTransmissao);

            resposta = (String) in.readObject();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Há algum problema com o servidor, feche a aplicação e tente novamente mais tarde.", "Erro de conexão", JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;
    }

    /**
     * Envia para o servidor a requisição para criar um novo usuário
     * @param cpf
     * @param primeiroNome
     * @param sobreNome
     * @param tipo
     */
    public void criarUsuario(String cpf, String primeiroNome, String sobreNome, String tipo) {
        chave = "101";
        pacoteTransmissao = chave + "_" + cpf + "_" + primeiroNome + "_" + sobreNome + "_" + tipo;

        try {

            out.writeObject(pacoteTransmissao);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Há algum problema com o servidor, feche a aplicação e tente novamente mais tarde.", "Erro de conexão", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Manda para o servidor a requisição para adcionar um novo titular a uma conta existente.
     * @param numeroContaLogada
     * @param registroUnico
     * @param primeiroNome
     * @param sobreNome
     * @param tipo
     * @param senha
     */
    public String addTitular(String numeroContaLogada, String registroUnico, String primeiroNome,
                           String sobreNome, String tipo, String senha) {
        chave = "102";
        pacoteTransmissao = chave + "_" + numeroContaLogada + "_" + registroUnico + "_" + primeiroNome +
                "_" + sobreNome + "_" + tipo +"_" +senha;

        try {
            out.writeObject(pacoteTransmissao);

            resposta = (String) in.readObject();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Há algum problema com o servidor, feche a aplicação e tente novamente mais tarde.", "Erro de conexão", JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;
    }

    /**
     * Manda para o servidor a requisição para validar as informações inseridas e fazer login
     * e recebe como resposta o nome do titular e o tipo da conta.
     * @param numeroConta
     * @param senha
     * @return
     */
    public String logarConta(String numeroConta, String senha) {
        chave = "103";
        pacoteTransmissao = chave + "_" + numeroConta + "_" + senha;

        String resposta = null;

        try {

            out.writeObject(pacoteTransmissao);

            resposta = (String) in.readObject();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Há algum problema com o servidor, feche a aplicação e tente novamente mais tarde.", "Erro de conexão", JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;
    }

    /**
     * Manda para o servidor a requisição para fazer um depósito
     * @param numeroConta
     * @param valor
     */
    public void depositar(String numeroConta, String valor) {
        chave = "104";

        pacoteTransmissao = chave + "_" + numeroConta + "_" + valor;

        try {
            out.writeObject(pacoteTransmissao);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Há algum problema com o servidor, feche a aplicação e tente novamente mais tarde.", "Erro de conexão", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * Manda para o servidor a requisição para consultar o saldo da conta
     * @param numeroConta
     * @return
     */
    public String verSaldo(String numeroConta) {
        chave = "105";

        pacoteTransmissao = chave + "_" + numeroConta;
        try {

            out.writeObject(pacoteTransmissao);

            resposta = (String) in.readObject();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Há algum problema com o servidor, feche a aplicação e tente novamente mais tarde.", "Erro de conexão", JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;
    }

    /**
     * Manda para o servidor a requisição para fazer uma transferência entre duas contas
     * @param contaorigem
     * @param contadestino
     * @param valor
     * @return
     */
    public String transferir(String contaorigem, String contadestino, String valor) {
        chave = "106";
        pacoteTransmissao = chave+"_"+contaorigem+"_"+contadestino+"_"+valor;
        try {
            out.writeObject(pacoteTransmissao);

            resposta = (String) in.readObject();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Há algum problema com o servidor, feche a aplicação e tente novamente mais tarde.", "Erro de conexão", JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;

    }

    /**
     * encerra a comunicação com o servidor
     */
    public void fecharConexao() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setIp(String ip) {
        Comunicacao.ip = ip;
    }
}
