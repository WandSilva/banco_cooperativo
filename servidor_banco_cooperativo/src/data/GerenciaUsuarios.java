package data;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by wanderson on 06/04/17.
 */
public class GerenciaUsuarios implements Serializable{


    ArrayList<String> listaUsuarios = new ArrayList<String>();

    public GerenciaUsuarios() {
        this.listaUsuarios = new ArrayList<String>();
    }

    public void addUsuario(String dadosUsuario) throws IOException {
        listaUsuarios.add(dadosUsuario);
    }

    public String getUsuario(String cpf){
        String dados, aux[];
        for(int i=0; i<listaUsuarios.size(); i++){
            dados = listaUsuarios.get(i);
            aux = dados.split(" ");
            if(cpf.equals(aux[0])){
                return dados;
            }

        }
        System.err.println("Usuario não encontrado");
        return null;
    }
    public void gravarDados(){
        try {
            FileOutputStream arquivoUsuarios = new FileOutputStream("arquivo_contas.data");
            ObjectOutputStream objectOutput = new ObjectOutputStream(arquivoUsuarios);
            objectOutput.writeObject(listaUsuarios);
            objectOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void carregarDados(){
        try {
            FileInputStream arquivoUsuarios = new FileInputStream("arquivo_usuarios.data");
            ObjectInputStream objectInput =  new ObjectInputStream(arquivoUsuarios);
            listaUsuarios = (ArrayList) objectInput.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
