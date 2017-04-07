package data;

/**
 * Created by wanderson on 07/04/17.
 */
public class teste {

    public static void main(String args[]){

        String conta1 = "12345 Wanderson Silva";
        String conta2 = "54321 Camille Jesus";
        String conta3 = "32145 Alisson Verde";

        GerenciaContas gerenciaContas = new GerenciaContas();

        gerenciaContas.criarConta(conta1);
        gerenciaContas.criarConta(conta2);
        gerenciaContas.criarConta(conta3);

        System.out.println("teste carregar conta: "+gerenciaContas.carregarConta("12345"));

        gerenciaContas.gravarDados();



    }
}
