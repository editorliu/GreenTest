package com.er.greentest;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Created by THTF on 2018/3/8.
 * Desc:
 */

public class AesUtil {
    public static void key(String seed){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            secureRandom.setSeed(seed.getBytes());
            keyGenerator.init(128,secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

    }

}
