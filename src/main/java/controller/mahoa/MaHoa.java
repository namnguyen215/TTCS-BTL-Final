package controller.mahoa;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class MaHoa {
    public static String encrypt(String input){
        String result="";
        try
        {
            String text = input;
            String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            result= Base64.getEncoder().encodeToString(encrypted);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String input){
        String result="";
        try
        {
            String text = input;
            String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(Base64.getDecoder().decode(text)));
            result= decrypted;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
