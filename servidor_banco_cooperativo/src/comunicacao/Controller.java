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

    private ArrayList<Conta> listaContas;
    private ArrayList<Usuario> listaUsuarios;
    private GerenciaContas gerenciaContas;
    private GerenciaUsuarios gerenciaUsuarios;


    public Controller() {
        this.listaContas = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.gerenciaContas = new GerenciaContas();
        this.gerenciaUsuarios = new GerenciaUsuarios();
    }

    public String tranferir(String contaorigem, String contadestino, String valor) {
        int valorInt = Integer.parseInt(valor);

        Conta contaOrigem = buscarConta(contaorigem);
        if (contaOrigem.getSaldo() < valorInt) {
            return "305";
        } else {
            Conta contaDestio = buscarConta(contadestino);
            contaOrigem.debitar(valorInt);
            contaDestio.depositar(valorInt);
            return "205";
        }
    }

    public String depositar(String numeroConta, String valor) {
        int valorInt = Integer.parseInt(valor);

        Conta conta = buscarConta(numeroConta);
        conta.depositar(valorInt);
        return "204";
    }

    public String verSaldo(String numeroConta) {
        Conta conta = buscarConta(numeroConta);
        return Integer.toString(conta.getSaldo());
    }

    public String criarConta(String senha, String tipo, String cpfTitular) {
        Conta conta = new Conta(senha, tipo);
        conta.addTitular(buscarUsuario(cpfTitular));
        listaContas.add(conta);
        salvarContas();
        return conta.getNumero();
    }

    public void addTitular(String numeroConta, String registroUnico, String primeiroNome,
                             String sobreNome, String tipo) {
        Conta conta = buscarConta(numeroConta);

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getRegistroUnico().equals(registroUnico)) {
                conta.addTitular(usuario);
            }
        }
        conta.addTitular(criarUsuario(registroUnico, primeiroNome, sobreNome, tipo));
    }

    public Conta buscarConta(String numeroConta) {

        for (Conta conta : listaContas) {
            if (numeroConta.equals(conta.getNumero())) {
                return conta;
            }
        }
        return null;
    }

    public String logarConta(String numeroConta, String senha) {
        Conta conta = buscarConta(numeroConta);
        if (conta != null) {
            if (senha.equals(conta.getSenha())) {
                return "203";
            } else return "303";
        } else return "303";
    }

    public Usuario criarUsuario(String registroUnico, String primeiroNome, String sobreNome, String tipo) {
        Usuario usuario = new Usuario(registroUnico, primeiroNome, sobreNome, tipo);
        listaUsuarios.add(usuario);
        salvarUsuarios();
        return usuario;

    }

    public Usuario buscarUsuario(String registroUnico) {

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (registroUnico.equals(listaUsuarios.get(i).getRegistroUnico()))
                return listaUsuarios.get(i);
        }
        return null;
    }

    public void salvarUsuarios() {
        gerenciaUsuarios.gravarDados(listaUsuarios);
    }

    public void salvarContas() {
        gerenciaContas.gravarDados(listaContas);
    }

    public void carregarUsuarios() {
        this.listaUsuarios = gerenciaUsuarios.carregarDados();
    }

    public void carregarContas() {
        this.listaContas = gerenciaContas.carregarDados();
    }
}