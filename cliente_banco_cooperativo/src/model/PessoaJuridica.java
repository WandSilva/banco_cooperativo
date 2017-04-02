package model;

/**
 * Created by wanderson on 02/04/17.
 */
public class PessoaJuridica extends Usuario {

   private String cnpj;

    public PessoaJuridica(String primeiroNome, String sobreNome, String cnpj) {
        super(primeiroNome, sobreNome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getRegistroUnico() {
        return this.cnpj;
    }
}
