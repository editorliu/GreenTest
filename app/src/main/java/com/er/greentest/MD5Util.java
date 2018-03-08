package com.er.greentest;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by THTF on 2018/3/7.
 * Desc:
 */

public class MD5Util {
    private static final String ALGORITHM = "md5";
    public static  String encrypt(String data){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] digest = messageDigest.digest(data.getBytes());
            Log.w("RSA_LOG", "md5加密后字节长度：" + digest.length);
            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                String s = Integer.toHexString(b & 0xff);//转化成16进制字符串
                if (s.length() == 1) {
                    s = "0"+s;
                }
                result.append(s);
            }
            return  result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 首先初始化一个字符数组，用来存放每个16进制字符
    private static final char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
    public static  String encrypt2(String data){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] digest = messageDigest.digest(data.getBytes());
            Log.w("RSA_LOG", "md5加密后字节长度：" + digest.length);
            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(hexDigits[b >> 4 & 0x0f]);
                result.append(hexDigits[b & 0x0f]);
            }
            return  result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 加密文件
     * @param in 文件输入流
     * @return
     */
    public static String encryptFile(InputStream in){
        DigestInputStream dis = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance(ALGORITHM);
            dis = new DigestInputStream(in, md5);
            byte[] buffer = new byte[1024];
            //DigestInputStream实际上在流处理文件时就在内部就进行了一定的处理
            while (dis.read(buffer) > 0);
            MessageDigest messageDigest = dis.getMessageDigest();
            byte[] digest = messageDigest.digest();

            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(hexDigits[b >> 4 & 0x0f]);
                result.append(hexDigits[b & 0x0f]);
            }
            return  result.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 加盐MD5
     * @param data 待加密数据
     * @return 一个48位字符长度的子浮串
     */
    public static String encryptSalt(String data){
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        String newData = encrypt(data + salt);
        Log.w("RSA_LOG","salt："+salt+" salt长度："+salt.length()+"  newData：" + newData +"  newData长度：" + newData.length());
        char [] resChar = new char[48];
        for (int i = 0; i < resChar.length; i+=3) {
            resChar[i] = newData.charAt(i/3*2);
            resChar[i+1] = salt.charAt(i/3);
            resChar[i+2] = newData.charAt(i/3*2+1);
        }
        return new String(resChar);
    }

    /**
     * 验证加盐加密
     * @param dataSource 元数据
     * @param encryption 加密结果
     * @return 对比元数据和加密数据是否相等
     */
    public static boolean verifySalt(String dataSource,String encryption){
        char[] oldChar = new char[32];
        char[] saltChar = new char[16];
        for (int i = 0; i < encryption.length(); i+=3) {
            oldChar[i/3*2] = encryption.charAt(i);
            oldChar[i/3*2+1] = encryption.charAt(i+2);
            saltChar[i/3] = encryption.charAt(i+1);
        }
        String salt = new String(saltChar);
        String encrypt = encrypt(dataSource+salt);
        return encrypt.equalsIgnoreCase(new String(oldChar));
    }



}

