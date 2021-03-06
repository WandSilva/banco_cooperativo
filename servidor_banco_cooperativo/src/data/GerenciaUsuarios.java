package data;

import model.Usuario;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by wanderson on 06/04/17.
 */
public class GerenciaUsuarios implements Serializable {

    public GerenciaUsuarios() {
    }

    /**
     * recebe a lista de usuários e salva os dados em um arquivo.
     * @param listaUsuarios
     */
    public void gravarDados(ArrayList<Usuario> listaUsuarios) {

        try {
            FileOutputStream arquivoUsuarios = new FileOutputStream("arquivo_usuarios.data");
            ObjectOutputStream objectOutput = new ObjectOutputStream(arquivoUsuarios);
            objectOutput.writeObject(listaUsuarios);
            objectOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * carrega os dados do arquivo de usuário em uma lista encadeada.
     * @return
     */
    public ArrayList<Usuario> carregarDados() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try {
            Boolean existe = new File("arquivo_usuarios.data").exists();
            if (existe) {
                FileInputStream arquivoUsuarios = new FileInputStream("arquivo_usuarios.data");
                ObjectInputStream objectInput = new ObjectInputStream(arquivoUsuarios);
                listaUsuarios = (ArrayList) objectInput.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }
}
