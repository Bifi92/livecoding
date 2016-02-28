package tcc_zanza.no_ip.biz.thirtyminutes.VO;

import android.util.Log;
import android.widget.Toast;

import javax.crypto.BadPaddingException;

import se.simbio.encryption.Encryption;

/**
 * Created by VINICIUS on 12/31/2015.
 */
public class Encryptor {

    private static String globalKey;
    private static Encryption encryption;

    public static void setGlobalKey(String Key){
        globalKey = Key;
    }

    private static String encrypt (String raw){

        return encryption.encryptOrNull(raw);

    }

    private static String decrypt(String encrypted){

        return encryption.decryptOrNull(encrypted);

    }

    public static String toEncrypt(String raw){

        encryption = Encryption.getDefault(globalKey, "Salt", new byte[16]);

        return encrypt(raw);

    }

    public static String toDecrypt(String encripted){

        encryption = Encryption.getDefault(globalKey, "Salt", new byte[16]);

        return decrypt(encripted);

    }

}
