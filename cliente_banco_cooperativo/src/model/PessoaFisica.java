package model;

/**
 * Created by wanderson on 02/04/17.
 */
public class PessoaFisica extends Usuario {

    private String cpf;
    public PessoaFisica(String primeiroNome, String sobreNome, String cpf) {
        super(primeiroNome, sobreNome);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getRegistroUnico() {
        return this.cpf;
    }
}
