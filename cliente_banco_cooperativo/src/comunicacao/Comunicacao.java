package comunicacao;

import java.io.*;
import java.net.Socket;

/**
 * Classe respossável pela comunicação entre cliente e servidor
 */
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

    /**
     * Inicia o socket e os atibutos responsáveis pelo envio e
     * recebimentos de dados do servidor
     */
    public void startSocket() {
        try {
            socket = new Socket("127.0.0.1", 7777);
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
            e.printStackTrace();
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
    public void addTitular(String numeroContaLogada, String registroUnico, String primeiroNome,
                           String sobreNome, String tipo, String senha) {
        chave = "102";
        pacoteTransmissao = chave + "_" + numeroContaLogada + "_" + registroUnico + "_" + primeiroNome +
                "_" + sobreNome + "_" + tipo +"_" +senha;

        try {
            out.writeObject(pacoteTransmissao);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Manda para o servidor a requusição para validar as informações inseridas e fazer login
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resposta;
    }

    /**
     * Manda para o servidor a requisição para fazer uma tranaferência entre duas contas
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
            e.printStackTrace();
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
}
