package data;

import model.Conta;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by wanderson on 04/04/17.
 */
public class GerenciaContas implements Serializable {


    public GerenciaContas(){
    }


    public void gravarDados(ArrayList<Conta> listaContas){
        try {
            FileOutputStream arquivoContas = new FileOutputStream("arquivo_contas.data");
            ObjectOutputStream objectOutput = new ObjectOutputStream(arquivoContas);
            objectOutput.writeObject(listaContas);
            objectOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Conta> carregarDados(){
        ArrayList<Conta> listaContas = new ArrayList<Conta>();
        try {
            FileInputStream arquivoConta = new FileInputStream("arquivo_contas.data");
            ObjectInputStream objectInput = new ObjectInputStream(arquivoConta);
            listaContas = (ArrayList) objectInput.readObject();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaContas;
    }
}
