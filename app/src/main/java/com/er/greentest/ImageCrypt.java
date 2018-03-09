package com.er.greentest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by THTF on 2018/3/8.
 * Desc:
 */

public class ImageCrypt {
    public static final String SOURCE_PATH = Environment.getExternalStorageDirectory().getPath() + "/test/test.jpg";

    public static final String BYTE_MIX_PATH = Environment.getExternalStorageDirectory().getPath() + "/test/byte_mix.jpg";
    public static final String BYTE_MIX_DECRYPT_PATH = Environment.getExternalStorageDirectory().getPath() + "/test/byte_mix_decryption.jpg";
    private static final String BYTE_MIX = "这是混入的字节";
    private static final String AES_KEY = "pass123456zxp098";//AES 限制为128bit（16个字节）

    //加密之后存储路径
    public static final String AES_ENCRYPT_PATH = Environment.getExternalStorageDirectory().getPath()+"/test/aes_encrypt.jpg";
    //解密之后存储路径
    public static final String AES_DECRYPT_PATH = Environment.getExternalStorageDirectory().getPath()+"/test/aes_decrypt.jpg";

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final byte[] IV_PARAMETER = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6};//AES 中 16 bytes or 128 bits


    /**
     * 将图片混入字节加密
     * @param filePath 图片地址
     */
    public static void addByte(String filePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(SOURCE_PATH);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] bytes = baos.toByteArray();
        try {
            FileOutputStream fos = new FileOutputStream(BYTE_MIX_PATH);
            fos.write(BYTE_MIX.getBytes());
            fos.write(bytes);
            fos.flush();
            fos.close();
            Log.w("ImageENCRYPT", "图片加密完成。。。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 将混入的字节去掉，解密出图片
     * @param path 图片地址
     * @return Bitmap对象
     */
    public static Bitmap removeByte(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileOutputStream fos = new FileOutputStream(BYTE_MIX_DECRYPT_PATH);
            byte[] buf = new byte[1024];
            int length = 0;
            int i = 0;//次数
            while ((length = fis.read(buf)) != -1) {
                if (i == 0) {
                    baos.write(buf, BYTE_MIX.getBytes().length, length - BYTE_MIX.getBytes().length);
                } else {
                    baos.write(buf, 0, length);
                }
                i++;
            }
            fis.close();
            baos.close();

            byte[] bytes = baos.toByteArray();
            fos.write(bytes);
            fos.flush();
            fos.close();
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过AES加密图片
     * @param path 图片地址
     */
    public static void aesEncrypt(String path){
        try {
            FileInputStream fis = new FileInputStream(path);//加载进来图片文件
            FileOutputStream fos = new FileOutputStream(AES_ENCRYPT_PATH);//作为加密输出


            SecretKeySpec secretKeySpec = new SecretKeySpec(AES_KEY.getBytes(), ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_PARAMETER);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParameterSpec);

            CipherOutputStream cos = new CipherOutputStream(fos, cipher);

            byte[] buf = new byte[1024];
            int length = 0;
            while((length = fis.read(buf)) != -1){
                cos.write(buf,0,length);
            }
            cos.flush();
            cos.close();
//            fos.close();//cos关闭的时候已经关闭了fos
            fis.close();
            Log.w("ImageENCRYPT", "图片AES加密完成。。。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过AES解密图片
     * @param path 待解密图片地址
     * @return 解密出的图片
     */
    public static Bitmap aesDecrypt(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            FileOutputStream fos = new FileOutputStream(AES_DECRYPT_PATH);//解密之后的文件输出
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            SecretKeySpec secretKeySpec = new SecretKeySpec(AES_KEY.getBytes(), ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_PARAMETER);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec);

            CipherInputStream cis = new CipherInputStream(fis, cipher);

            byte[] buf = new byte[1024];
            int length = 0;
            while((length = cis.read(buf)) != -1){
                baos.write(buf,0,length);
            }
            baos.flush();
            baos.close();
            cis.close();

            //图片
            byte[] bytes = baos.toByteArray();
            fos.write(bytes);
            return BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;
    }


}
