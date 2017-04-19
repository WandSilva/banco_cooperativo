package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wanderson on 07/04/17.
 */
public class Conta implements Serializable{


    private int numero;
    private static int auxNumero = 1000;
    private ArrayList<String> senhas;
    private double saldo;
    private String tipo; //[1] - polpanca / [0] - corrent
    private ArrayList<Usuario> titulares;

    public String getTipo() {
        return tipo;
    }

    public Conta(String senha, String tipo) {
        atualizarNumero();
        this.senhas=new ArrayList<>();
        this.senhas.add(senha);
        this.saldo =0;
        this.tipo = tipo;
        this.titulares = new ArrayList<>();
    }

    public int getNumero(){
        return this.numero;
    }

    public String getSenha(int index){
        return this.senhas.get(index);
    }

    public ArrayList<Usuario> getListaTitulares(){
        return this.titulares;
    }

    public void addTitular(Usuario usuario) {
        this.titulares.add(usuario);
    }
    public void addSenha(String senha){
        this.senhas.add(senha);
    }

    public void depositar(int valor) {
        this.saldo += valor;
    }

    public void debitar(int valor) {
        this.saldo -= valor;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void atualizarNumero(){
        this.numero = auxNumero;
        auxNumero++;
    }
    public void retomarContagemNumero(int numero){
        this.auxNumero = numero;
        atualizarNumero();
    }


}
