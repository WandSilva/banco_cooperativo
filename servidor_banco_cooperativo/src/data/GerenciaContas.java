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

    /**
     * Pega os dados do cliente armazenados em uma lista e grava em um arquivo.
     * @param listaContas
     */
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

    /**
     * carrega os dados do arquivo de contas em uma lista encadeada.
     * @return
     */
    public ArrayList<Conta> carregarDados(){
        ArrayList<Conta> listaContas = new ArrayList<Conta>();
        try {
            Boolean existe = new File("arquivo_contas.data").exists();
            if (existe) {
                FileInputStream arquivoConta = new FileInputStream("arquivo_contas.data");
                ObjectInputStream objectInput = new ObjectInputStream(arquivoConta);
                listaContas = (ArrayList) objectInput.readObject();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaContas;
    }
}
