package fr.triedge.fwk.security;

import java.util.Base64;

public class SPassword {
    private static final String prefix = "sbi-";
    private String tmp;
    private String decrypted;
    private String encrypted;

    public SPassword(String password){
        tmp = password;
        processPassword();
    }

    private boolean isEncrypted(){
        if (tmp == null || tmp.equals(""))
            throw new RuntimeException("Provided password is null or empty");
        String deco = "";
        try {
            deco = new String(Base64.getDecoder().decode(tmp.getBytes()));
        }catch (Exception e){
            return false;
        }
        return deco.startsWith(prefix);
    }

    private void processPassword(){
        if (isEncrypted()){
            decrypted = new String(Base64.getDecoder().decode(tmp.getBytes())).replace(prefix,"");
            encrypted = tmp;
        }else{
            decrypted = tmp;
            encrypted = new String(Base64.getEncoder().encode((prefix+tmp).getBytes()));
        }
    }

    public String getEncrypted(){
        return encrypted;
    }

    public String getDecrypted(){
        return decrypted;
    }
}
