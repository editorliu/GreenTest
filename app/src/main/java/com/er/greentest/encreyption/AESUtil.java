package com.er.greentest.encreyption;

import android.app.Application;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by THTF on 2017/10/20.
 * Desc:
 */

public class AESUtil {
    /*
    可以使用seed种子生成密钥，也可以直接以密码的方式直接生成，本例直接使用密码生成
     */

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY = "1234567890123456";//AES 限制为128bit（16个字节）
    private static final String KEY_ALGORITHM = "AES";
    private static final byte[] IV_PARAMETER = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6};//AES 中 16 bytes or 128 bits
    private static final String WORDS = "我爱你中国123456+-*/";

    public static void test() {
        String encryption = encryption(WORDS);
        Log.w("xEnDe", "-----------encryption:"+encryption);
        byte[] decryption = decryption(encryption);
//        byte[] decryption = decryption("7tkKWBkrM126vHdUh+HrfqIgqkaiycn1gG7LG1y8EFo=");
        Log.w("xEnDe", "-----------decryption:" + new String(decryption));
    }

    private static String encryption(String words) {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_PARAMETER);
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), KEY_ALGORITHM);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            byte[] doFinal = cipher.doFinal(words.getBytes());
//            return Base64.getEncoder().encodeToString(doFinal);
//            return Base64.encodeToString(doFinal, Base64.DEFAULT);
//            return Base64.encodeToString(doFinal, Base64.CRLF);
//            return Base64.encodeToString(doFinal, Base64.NO_CLOSE);
            return Base64.encodeToString(doFinal, Base64.CRLF);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decryption(String words) {
        byte[] decode = Base64.decode(words, Base64.CRLF);
        System.out.println("decode:" + new String(decode));

        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_PARAMETER);
        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), KEY_ALGORITHM);
//        SecretKeySpec key = getKey(BaseApplication.getApp());
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            byte[] doFinal = cipher.doFinal(decode);
            System.out.println("doFinal:" + new String(doFinal));
            return doFinal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static SecretKeySpec getKey(Application application) {
        ObjectInputStream ois = null;
        InputStream is = null;
        try {
            is = application.getResources().getAssets().open("mykey");
            ois = new ObjectInputStream(is);
            return ((SecretKeySpec) ois.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
           }
            }

            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
