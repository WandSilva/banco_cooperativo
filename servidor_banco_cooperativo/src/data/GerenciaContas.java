package data;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by wanderson on 04/04/17.
 */
public class GerenciaContas implements Serializable {

    ArrayList<String> listaContas = new ArrayList<String>();

    public GerenciaContas() {
        this.listaContas = new ArrayList<String>();
    }


    public void criarConta(String dadosConta) {
        listaContas.add(dadosConta);
    }

    public String carregarConta(String numeroConta){
        String dados, aux[];

        for(int i=0; i<listaContas.size(); i++){
            dados = listaContas.get(i);
            aux = dados.split(" ");
            if(numeroConta.equals(aux[0])){
                return dados;
            }

        }
        System.err.println("Conta não encontrada");
        return null;
    }
    public void gravarDados(){
        try {
            FileOutputStream arquivoContas = new FileOutputStream("arquivo_contas.data");
            ObjectOutputStream objectOutput = new ObjectOutputStream(arquivoContas);
            objectOutput.writeObject(listaContas);
            objectOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados(){
        try {
            FileInputStream arquivoConta = new FileInputStream("arquivo_contas.data");
            ObjectInputStream objectInput = new ObjectInputStream(arquivoConta);
            listaContas = (ArrayList) objectInput.readObject();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
