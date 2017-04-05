package comunicacao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by wanderson on 04/04/17.
 */
public class GerenciaArquivo {

    File arquivoContas;
    File arquivoUsuarios;
    BufferedWriter escrever;

    public GerenciaArquivo() {
        this.arquivoContas = new File("arquivo_contas.data");
        this.arquivoUsuarios = new File("arquivo_usuarios.data");
    }

    public void salvarCliente(String dadosCliente){

    }
    public String carregarCliente(String cpf){
        //String x = "Testando o método split";
        //String array[] = x.split(" ");
        return null;
    }
    public void salvarConta(String dadosConta){

    }
    public String carregarConta(String numeroConta){
        return null;
    }





}
