package controller;

import java.security.NoSuchAlgorithmException;

import comunicacao.Comunicacao;
import exeption.ContaNaoEncontradaExcep;
import exeption.SaldoInsuficienteExcep;
import util.Criptografia5dm;


public class ControllerConta {


    private String dadosConta;
    private String contaLogada;
    private Comunicacao comunicacao;

    public ControllerConta(Comunicacao comunicacao) {
        this.dadosConta = null;
        this.comunicacao = comunicacao;
    }

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

    public void addTitular(String numeroConta, String registroUnico, String primeiroNome, String sobreNome, String tipo, String senha) {
        String senhaCriptografada = null;
        try {
            senhaCriptografada = new Criptografia5dm().criptografia5dm(senha);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        comunicacao.addTitular(numeroConta, registroUnico, primeiroNome, sobreNome, tipo, senhaCriptografada);
    }

    public void depositar(String contaLogada, String valor) {
        comunicacao.depositar(contaLogada, valor);
    }

    public void tranferir(String contaLogada, String numeroContaDestino, String valor) throws SaldoInsuficienteExcep, ContaNaoEncontradaExcep {
        String resposta = this.comunicacao.transferir(contaLogada, numeroContaDestino, valor);

        if (resposta.equals("305")) {
            throw new SaldoInsuficienteExcep();
        }
        if (resposta.equals("3051")) {
            throw new ContaNaoEncontradaExcep();
        }
    }

    public void setContaLogada(String contaLogada) {
        this.contaLogada = contaLogada;
    }

    public String getContaLogada() {

        return contaLogada;
    }

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

    public String consultarSaldo(String numeroConta) {
        return this.comunicacao.verSaldo(numeroConta);
    }


    public void fecharConexao() {
        this.comunicacao.fecharConexao();
    }
}
