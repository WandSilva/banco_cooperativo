package util;

/**
 * Created by wanderson on 17/04/17.
 */
public class RevertCriptografia5dm {

    public String revertCriptografia5dm(String senha5dm){
        StringBuffer sb = new StringBuffer(senha5dm);
        String senha = sb.reverse().toString();
        return senha;
    }
}
