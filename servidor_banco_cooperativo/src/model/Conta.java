package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wanderson on 07/04/17.
 */
public class Conta implements Serializable{


    private String numero;
    private static int auxNumero = 1000;
    private String senha;
    private double saldo;
    private String tipo; //[1] - polpanca / [0] - corrent
    private ArrayList<Usuario> titulares;

    public Conta(String senha, String tipo) {
        atualizarNumero();
        this.senha = senha;
        this.saldo =0;
        this.tipo = tipo;
        this.titulares = new ArrayList<>();
    }

    public String getNumero(){
        return this.numero;
    }

    public String getSenha(){
        return this.senha;
    }

    public ArrayList<Usuario> getListaTitulares(){
        return this.titulares;
    }

    public void addTitular(Usuario usuario) {
        this.titulares.add(usuario);
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
        this.numero = Integer.toString(auxNumero);
        auxNumero++;
    }


}
