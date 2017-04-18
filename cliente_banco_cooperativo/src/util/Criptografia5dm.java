package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wanderson on 17/04/17.
 */
public class Criptografia5dm {

    public String criptografia5dm(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));

        String aux = String.format("%32x", hash);
        System.out.println(aux);
        StringBuffer sb = new StringBuffer(aux);
        String senha5dm = sb.reverse().toString();
        System.out.println(senha5dm);
        return senha5dm;
    }
}
