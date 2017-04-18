package controller;

import java.security.NoSuchAlgorithmException;

import comunicacao.Comunicacao;
import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;
import util.Criptografia5dm;

/**
 * Classe responsável por gerenciar todas as funcionalidas pertencentes a uma conta e
 * enviar para a classe responsável pela comunicação.
 */
public class ControllerConta {


    private String dadosConta;
    private String contaLogada;
    private Comunicacao comunicacao;

    public ControllerConta(Comunicacao comunicacao) {
        this.dadosConta = null;
        this.comunicacao = comunicacao;
    }

    /**
     * Recebe as informações sobre a conta, criptografa a senha e envia as informações para
     * a classe responsável para o comunicação com o servidor.
     * @param senha
     * @param tipoConta
     * @param cpfTitular
     * @return
     */
    public String criarConta(String senha, String tipoConta, String cpfTitular) {
        String senhaCriptografada = null;
        try {
            senhaCriptografada = new Criptografia5dm().criptografia5dm(senha);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String numeroConta = comunicacao.criarConta(senhaCriptografada, tipoConta, cpfTitular);
        return numeroConta;
    }

    /**
     * Recebe as informações sobre o novo titular, criptografa a senha e envia as informações para
     * a classe responsável para o comunicação com o servidor.
     * @param numeroConta
     * @param registroUnico
     * @param primeiroNome
     * @param sobreNome
     * @param tipo
     * @param senha
     */
    public void addTitular(String numeroConta, String registroUnico, String primeiroNome, String sobreNome, String tipo, String senha) {
        String senhaCriptografada = null;
        try {
            senhaCriptografada = new Criptografia5dm().criptografia5dm(senha);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        comunicacao.addTitular(numeroConta, registroUnico, primeiroNome, sobreNome, tipo, senhaCriptografada);
    }

    /**
     * @param contaLogada
     * @param valor
     */
    public void depositar(String contaLogada, String valor) {
        comunicacao.depositar(contaLogada, valor);
    }

    /**
     *
     * @param contaLogada
     * @param numeroContaDestino
     * @param valor
     * @throws SaldoInsuficienteExcep
     * @throws ContaNaoEncontradaExcep
     */
    public void tranferir(String contaLogada, String numeroContaDestino, String valor) throws SaldoInsuficienteExcep, ContaNaoEncontradaExcep {
        String resposta = this.comunicacao.transferir(contaLogada, numeroContaDestino, valor);

        if (resposta.equals("305")) {
            throw new SaldoInsuficienteExcep();
        }
        if (resposta.equals("3051")) {
            throw new ContaNaoEncontradaExcep();
        }
    }

    /**
     * Método utulizado para a aplicação sempre saber qual a conta que está logada
     * @param contaLogada
     */
    public void setContaLogada(String contaLogada) {
        this.contaLogada = contaLogada;
    }

    /**
     *
     * @return número da conta logada
     */
    public String getContaLogada() {

        return contaLogada;
    }

    /**
     * Recebe a informações de login, codifica a senha e manda para a classe
     * reponsável pela comunicação com o servidor
     * @param numero
     * @param senha
     * @return
     */
    public String[] logarConta(String numero, String senha) {
        String senhaCriptografada = null;
        try {
            senhaCriptografada = new Criptografia5dm().criptografia5dm(senha);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String resopostaServidor[] = this.comunicacao.logarConta(numero, senhaCriptografada).split("_");
        String codeLogin[] = new String[2];

        if (resopostaServidor[0].equals("203")) {
            codeLogin[0] = "1";
            codeLogin[1] = resopostaServidor[1];
            return codeLogin;
        } else {
            codeLogin[0] = "0";
            return codeLogin;
        }
    }

    /**
     *
     * @param numeroConta
     * @return
     */
    public String consultarSaldo(String numeroConta) {
        return this.comunicacao.verSaldo(numeroConta);
    }

    /**
     * fecha a conexão com o servidor
     */
    public void fecharConexao() {
        this.comunicacao.fecharConexao();
    }
}
