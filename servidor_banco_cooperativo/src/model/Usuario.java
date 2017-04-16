package model;

import java.io.Serializable;

public class Usuario implements Serializable{

    private String primeiroNome;
    private String sobreNome;
    private String tipo; //[1] - fisico / [0] - juridico
    private String registroUnico;


    public Usuario(String registroUnico, String primeiroNome, String sobreNome, String tipo){
        this.primeiroNome = primeiroNome;
        this.sobreNome = sobreNome;
        this.registroUnico = registroUnico;
        this.tipo=tipo;

    }

    public String getPrimeiroNome(){
        return this.primeiroNome;
    }

    public String getSobreNome(){
        return this.sobreNome;
    }

    public String getRegistroUnico() {
        return registroUnico;
    }

}
