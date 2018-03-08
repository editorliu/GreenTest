package com.er.greentest;

import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by THTF on 2018/2/28.
 * Desc:
 */

public class RSAUtil {

    private static final int KEY_SIZE = 2048;//密钥长度
    private static final int MAX_LENGTH = (KEY_SIZE / 8) - 11;//加密最大字节数

    //keySize 1024
//    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsJWKexa0mKuHteG6lyRolqnIBgl7hosqbHlLc4pFIWz++54LjRSm9xEg6PtTZrj5Pd4m7jMqDEaog1yx7jNFDSrwzP6QymybtCuF17hOPZcB1I4Dqgfq74OiFRnMi+kxVWUg94R3vJI2I2Bp77iFF+waZsHv2YlU3DfSYGLDyyQIDAQAB";
//    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKwlYp7FrSYq4e14bqXJGiWqcgGCXuGiypseUtzikUhbP77nguNFKb3ESDo+1NmuPk93ibuMyoMRqiDXLHuM0UNKvDM/pDKbJu0K4XXuE49lwHUjgOqB+rvg6IVGcyL6TFVZSD3hHe8kjYjYGnvuIUX7Bpmwe/ZiVTcN9JgYsPLJAgMBAAECgYB1BlUsqiUzNSOxU5WkjjmAMYafEAmASjKBkPOzxTIOhjmnhaqs5W+i2bRgJCZ+dL1XOGQhY2yn/XIyMRTESM5YWviUQ4z+wkGCYWmj967CUNNegkIJmy4CDAprlLOY++LBd3lzDFyV30+Cl+5ZDYowaNRKctDrcDFM4j8UcEz8IQJBANkg8aIqPUti0BQxLWDDSyDW5m/FB7kYwX6dB9s1nsrig+HyzilKHkHRse/2+KNouA5HhQYax5JSZf4UFaIMOGMCQQDK9uBeFAhBSMxdFXnG5Y/MWX7DuljBqvgqasv8C+3qk555VvBZ6lPrkyVstQtWSVJgvpop+DND3vpSUzWPsDHjAkBNJRRVLrbKcxhFJFfgGmM2DsSfD5032aDeEIL7EqkRoruC6xbuuqPXdN7IyLH8tTc385dC3P7tcRNF2/L3uWnLAkBwns5exYTTHN7xI7J/z++bt2kkmCkZxZGQj9QCVmg8yo6by8bwTZslU2/nwwm4ZlUEg56tfSCNH5QMzEkjIuR7AkEAvfu5h1WH1sMhRKNUfOn/T4jE2TeIt/yFbcPIjxmxSN/eZQpc6DZhxgCvHFZ6jZn/05xre6HdHNtof4JeRGw+ow==";

    //keySize 2048
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgAa7dfm4f4/9sStw05kkjvnzFMCo7ioR90uvGigIeXhnaSG+adKQF/qJmdr0hWUYQ/EFYHH+oJM1Q9orG6Msin4z01wTVBMrIUbXzIcPcdqU1v/t1/8Jpgyqy0aNIfs+k4/qwVXHInF3/rfYgzX5eO9Xlk665Ku8seNrJci3KKFtDal9kSL5IJXsTadk/b2h20L6V6DPb8lCFBBJvr0gxgFbtIUhHZWYgKeyHMAdJSWMpKlHZctSxr55Dg37O621tYM09RUQrhE0GNe92PAXjcaMg9lK8yzMn1UHJC1/qMnrZZpNUO/X1Zbtvk4b47A87WdGS4sPjQnuK1XE+JLjjQIDAQAB";
    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCABrt1+bh/j/2xK3DTmSSO+fMUwKjuKhH3S68aKAh5eGdpIb5p0pAX+omZ2vSFZRhD8QVgcf6gkzVD2isboyyKfjPTXBNUEyshRtfMhw9x2pTW/+3X/wmmDKrLRo0h+z6Tj+rBVccicXf+t9iDNfl471eWTrrkq7yx42slyLcooW0NqX2RIvkglexNp2T9vaHbQvpXoM9vyUIUEEm+vSDGAVu0hSEdlZiAp7IcwB0lJYykqUdly1LGvnkODfs7rbW1gzT1FRCuETQY173Y8BeNxoyD2UrzLMyfVQckLX+oyetlmk1Q79fVlu2+ThvjsDztZ0ZLiw+NCe4rVcT4kuONAgMBAAECggEAOr7cFe5YcnAoALvmMbH7zH90wvkEiwRS6h6YYnAsM1DEYsFRFk3AQMB9k1OdD/irgcF+rhqgtB3YOyJI1Y2ySnpOXZXKaLHDAKn5K6httdiOo3kcMhic3MzDMjNVX1piBGPtXHl3vp5CaWcCunJx3sQ0dIv7xiHnm9i2uce4A7Qm0hmz02qmk0eaF9aHkXZTtigXuWSvHz0Xg0PHu8vv3hl3ovj5EkbDOZAZ35FbY6ihg7Ho3JeLby4ErolIXIJ5KuIt6zOEttK6eA8krxsO05LNfGzS7dMorlS4I15kke2T6Ebfco90+2Emoj7qPwc0ZPuZN4AkQSV6KSp3T8QxAQKBgQDSE49QxL2ItSMhUTxxE4j9SO1LwrhF0ejPnENSegRUH37s6SH8W4HCNuTjSvIWryNzNp4oYv9cY+U8WWjQbqcA8KQQ89mYkxqqfVkVaf4Iyr2hZ2iK7oAMDw1NTYhRXGg1V2EVMPC54bzACeUUlxoIAbzud5uK57P5EXh9fLAMmQKBgQCcA22NDYQRkc5Yk85SCJj7ozEg6hFlJek+OtH64aslx7uFxov4mX1IONfSkWt0Z6wb6bCSuyYQEmgERwQuOY8AHRrcPw7LTrnqJ27ivqDlb1J1+un6s2A3hvjgHJmJJTQ8k+LvT3lMToN3S57KtG6KoHrBFs0pg0T4uNiTd9aTFQKBgGbgSUjGcALoocGJJM5DQMy4z+xP+SnH9/jjj1xugHwpyGCAcrJApbTUaRad9xwAvJ2JW75vbJp0GSiKqHeaGJKvRck/gYnkUCS5qcyA0XTAxEiseVfAIFjHbj4y/9TIKgglOFfGVbzRvTtWHScOMCNHppwpWrSVb19DnPvm+dfRAoGAbLPavd//+DnuWztJE1FYPmSbU4KjciVzGV/kdHiORjmfR/tqBWH0r8CjZ9zY0Pd3LM+rruDlCWe5v9k3aXnDUGH3GCR7i16kHSkykpweAR6Khxe3tW0MCkRp3cUvmSYp+ldUVEe8jVVbNmCjGDuVldNxFF5tvpHvGZm5lS53fG0CgYEAhz42QLBS+M2C36oADWGlRdfQdcZp1ZjdzCGKFjsea5QHEnbTLcqdKB2yhdVFvNwGc0YVEI6X7zJUfPF/yJ30Zq1NW3c5o3+FPE8JGb1k22j78LRBML94lVCdnDIR1av216U/8k4yzCPn5xODERgQq1xfMUbWR9KbakS+EP4Fg/c=";


    private static final String RSA = "RSA";
    private static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    private static PublicKey getPublicKeyFromString(String keyString) {
        byte[] decode = Base64.decode(keyString, Base64.DEFAULT);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode);
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 公钥加密
     * @param data 待加密数据
     * @return 加密后的字符串
     */
    public static String encryption(String data) {
        if (data != null && data.length() > 0) {
            int length = data.getBytes().length;
            if (length <= MAX_LENGTH){
                return encryptionShort(data);
            }else{
                Log.w("RSA_LOG", "-----------分段加密");
                return encryptionSplit(data);
            }
        }
        return null;
    }

    /**
     * 公钥加密(加密数据长度小于最大限度，直接加密，)
     *
     * @param data 待加密数据
     * @return 加密后的字符串
     */
    private static String encryptionShort(String data) {
        PublicKey publicKey = getPublicKeyFromString(PUBLIC_KEY);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = cipher.doFinal(data.getBytes());
            return Base64.encodeToString(bytes, Base64.DEFAULT);//加密之后  使用Base64编码
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥分段加密
     * @param data 待加密数据
     * @return 加密后的数据
     */
    private static String encryptionSplit(String data){
        byte[] dataBytes = data.getBytes();
        Log.w("RSA_LOG", "元数据字节数:" + dataBytes.length);
//        int nBlock = (dataBytes.length / MAX_LENGTH);
//        if ((data.getBytes().length % MAX_LENGTH) != 0) { // 余数非0，block数再加1
//            nBlock += 1;
//        }
//        Log.w("RSA_LOG", "加密的nBlock:" + nBlock);

//        ByteArrayOutputStream outbuf = new ByteArrayOutputStream(nBlock*(KEY_SIZE / 8));
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            PublicKey publicKey = getPublicKeyFromString(PUBLIC_KEY);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            for (int offset = 0; offset < dataBytes.length; offset += MAX_LENGTH) {
                //剩余字节数
                int inputLen = dataBytes.length - offset;
                if (inputLen > MAX_LENGTH) {
                    inputLen = MAX_LENGTH;
                }
                // 得到分段加密结果
                byte[] encryptedBlock = cipher.doFinal(dataBytes, offset, inputLen);
                // 追加结果到输出buffer中
                outbuf.write(encryptedBlock);
            }
            byte[] input = outbuf.toByteArray();
            Log.w("", "加密后的字节长度：input：" + input.length);
            return Base64.encodeToString(input, Base64.DEFAULT);//加密之后base64编码
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                outbuf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static PrivateKey getPrivateKeyFromString(String keyString) {
        byte[] decode = Base64.decode(keyString, Base64.DEFAULT);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @return 解密字符串
     */
    public static String decryption(String data) {
        PrivateKey privateKey = getPrivateKeyFromString(PRIVATE_KEY);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] dataBytes = Base64.decode(data, Base64.DEFAULT);//加密后使用了Base64，这里使用Base64解码
            byte[] bytes = cipher.doFinal(dataBytes);
            return new String(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分段解密
     * @param data 待解码数据
     * @return 原始数据
     */
    public static String decryptionSplit(String data) {
        // 计算分段加密的block数 (向上取整)
        byte[] dataBytes = Base64.decode(data, Base64.DEFAULT);//加密后使用了Base64，这里使用Base64解码
        Log.w("","解密后字节长度：dataBytes:"+dataBytes.length);
//        int nBlock = (dataBytes.length / (KEY_SIZE/8));
//        if ((dataBytes.length % (KEY_SIZE/8)) != 0) { // 余数非0，block数再加1
//            nBlock += 1;
//        }
//        Log.w("RSA_LOG", "解密的nBlock:" + nBlock);

//        ByteArrayOutputStream outbuf = new ByteArrayOutputStream(nBlock * MAX_LENGTH);
        ByteArrayOutputStream outbuf = new ByteArrayOutputStream();
        try {
            PrivateKey privateKey = getPrivateKeyFromString(PRIVATE_KEY);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            for (int offset = 0; offset < dataBytes.length; offset += (KEY_SIZE/8)) {
                // 剩余字节数
                int inputLen = (dataBytes.length - offset);
                if (inputLen > (KEY_SIZE/8)) {
                    inputLen = (KEY_SIZE/8);
                }
                // 得到分段加密结果
                byte[] decryptedBlock = cipher.doFinal(dataBytes, offset, inputLen);
                // 追加结果到输出buffer中
                outbuf.write(decryptedBlock);
            }
            return new String(outbuf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                outbuf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

