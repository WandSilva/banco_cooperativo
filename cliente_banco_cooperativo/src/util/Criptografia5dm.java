package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wanderson on 17/04/17.
 * Classe responsável por fazer a criptografia dos dados
 */
public class Criptografia5dm {

    /**
     * Recebe a senha, criptografa usando DM5 e inverte o resultado.
     * @param password
     * @return  senha criptografada
     * @throws NoSuchAlgorithmException
     */
    public String criptografia5dm(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));

        String aux = String.format("%32x", hash);
        StringBuffer sb = new StringBuffer(aux);
        String senha5dm = sb.reverse().toString();
        return senha5dm;
    }
}
