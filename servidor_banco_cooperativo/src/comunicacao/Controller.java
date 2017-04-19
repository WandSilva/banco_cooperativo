package comunicacao;

import data.GerenciaContas;
import data.GerenciaUsuarios;
import model.Conta;
import model.Usuario;

import java.util.ArrayList;

/**
 * Created by wanderson on 04/04/17.
 */
public class Controller {

    private static ArrayList<Conta> listaContas;
    private static ArrayList<Usuario> listaUsuarios;
    private GerenciaContas gerenciaContas;
    private GerenciaUsuarios gerenciaUsuarios;
    private int numeroProxConta;


    public Controller() {
        this.listaContas = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.gerenciaContas = new GerenciaContas();
        this.gerenciaUsuarios = new GerenciaUsuarios();
    }

    /**
     * Faz tranferência entre das contas
     * @param contaorigem
     * @param contadestino
     * @param valor
     * @return "205" - transferencia concluída
     * @return "305" - saldo insuficiente
     * @return "3051"- conta de destino não encontrada
     */
    public String tranferir(String contaorigem, String contadestino, String valor) {
        int valorInt = Integer.parseInt(valor);

        Conta contaOrigem = buscarConta(contaorigem);

        if (contaOrigem.getSaldo() < valorInt) {
            return "305";
        } else {
            Conta contaDestino = buscarConta(contadestino);
            if (contaDestino == null)
                return "3051";
            contaOrigem.debitar(valorInt);
            contaDestino.depositar(valorInt);
            salvarContas();
            return "205";
        }
    }

    /**
     * faz um depósito
     * @param numeroConta
     * @param valor
     * @return "204" - depósito concluído
     */
    public String depositar(String numeroConta, String valor) {
        int valorInt = Integer.parseInt(valor);

        Conta conta = buscarConta(numeroConta);
        conta.depositar(valorInt);
        salvarContas();
        return "204";
    }

    /**
     *
     * @param numeroConta
     * @return saldo da conta
     */
    public String verSaldo(String numeroConta) {
        Conta conta = buscarConta(numeroConta);
        return Double.toString(conta.getSaldo());
    }

    /**
     * cria uma conta e adciona a lista de contas
     * @param senha
     * @param tipo
     * @param cpfTitular
     * @return
     */
    public String criarConta(String senha, String tipo, String cpfTitular) {
        Conta conta = new Conta(senha, tipo);

        if (conta.getNumero() == 1000 && listaContas.size() >0) {
            this.retomarContagemNumero();
            conta.retomarContagemNumero(numeroProxConta);
        }

        conta.addTitular(buscarUsuario(cpfTitular));
        listaContas.add(conta);
        salvarContas();
        return Integer.toString(conta.getNumero());
    }

    /**
     *
     * @param numeroConta
     * @param registroUnico
     * @param primeiroNome
     * @param sobreNome
     * @param tipo
     * @param senha
     * @return "202" - operação concluída
     * @return "302" - número de titular excedido.
     */
    public String addTitular(String numeroConta, String registroUnico, String primeiroNome,
                             String sobreNome, String tipo, String senha) {
        Conta conta = buscarConta(numeroConta);

        int numeroDeTitulares = conta.getListaTitulares().size();

        if (numeroDeTitulares >= 3) {
            return "302";
        }

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getRegistroUnico().equals(registroUnico)) {
                conta.addTitular(usuario);
                conta.addSenha(senha);
                salvarContas();
                return "202";
            }
        }
        conta.addTitular(criarUsuario(registroUnico, primeiroNome, sobreNome, tipo));
        conta.addSenha(senha);
        salvarContas();

        return "202";
    }

    /**
     * busca uma conta na lista de contas
     * @param numeroConta
     * @return
     */
    public Conta buscarConta(String numeroConta) {
        int aux = Integer.parseInt(numeroConta);
        for (Conta conta : listaContas) {
            if (conta.getNumero() == aux) {
                return conta;
            }
        }
        return null;
    }

    /**
     * @param numeroConta
     * @param senha
     * @return "203" - logado
     * @return "303" - login/senha inválidos
     */
    public String logarConta(String numeroConta, String senha) {


        Conta conta = buscarConta(numeroConta);
        if (conta != null) {

            for (int i = 0; i < conta.getListaTitulares().size(); i++) {
                if (senha.equals(conta.getSenha(i))) {
                    return "203" + "_" + conta.getListaTitulares().get(i).getPrimeiroNome()+
                            "_"+conta.getTipo();
                }
            }
        }
        return "303";
    }

    /**
     * cria um novo usuário e adciona a lista de usuários
     * @param registroUnico
     * @param primeiroNome
     * @param sobreNome
     * @param tipo
     * @return
     */
    public Usuario criarUsuario(String registroUnico, String primeiroNome, String sobreNome, String tipo) {
        Usuario usuario = new Usuario(registroUnico, primeiroNome, sobreNome, tipo);
        listaUsuarios.add(usuario);
        salvarUsuarios();
        return usuario;
    }

    /**
     * busca um usuário na lista de usuários
     * @param registroUnico
     * @return
     */
    public Usuario buscarUsuario(String registroUnico) {

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (registroUnico.equals(listaUsuarios.get(i).getRegistroUnico()))
                return listaUsuarios.get(i);
        }
        return null;
    }

    /**
     * Salva a lista de usuários em um arquivo.
     */
    public void salvarUsuarios() {
        gerenciaUsuarios.gravarDados(listaUsuarios);
    }

    /**
     * salva a lista de contas em um arquivo.
     */
    public void salvarContas() {
        gerenciaContas.gravarDados(listaContas);
    }

    /**
     * carrega dos dados do arquivo de usuários em uma lista encadeada
     */
    public void carregarUsuarios() {
        this.listaUsuarios = gerenciaUsuarios.carregarDados();
    }
    /**
     * carrega dos dados do arquivo e contas em uma lista encadeada
     */
    public void carregarContas() {
        this.listaContas = gerenciaContas.carregarDados();
    }

    /**
     * Caso o servidor seja reiniciado, utiliza esse método para pegar o estado anterior
     * do número das contas, evitando que haja contas com o mesmo número.
     */
    public void retomarContagemNumero() {
        this.numeroProxConta = listaContas.get(listaContas.size()-1).getNumero() + 1;
    }
}