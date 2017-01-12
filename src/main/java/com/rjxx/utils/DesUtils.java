package com.rjxx.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;


public class DesUtils {

    public static String GLOBAL_DES_KEY = "A!9fF&vH";

    /**
     * des加密后base64
     *
     * @param inputData
     * @param inputKey
     * @return
     * @throws Exception
     */
    public static String DESEncrypt(String inputData, String inputKey) throws Exception {
        //设置密钥
        byte[] DESkey = inputKey.getBytes();
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象

        Cipher enCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(inputData.getBytes("utf-8"));
        return Base64.encodeBase64String(pasByte);
    }

    /**
     * 先反base64，然后解密
     *
     * @param inputData
     * @param inputKey
     * @return
     * @throws Exception
     */
    public static String DESDecrypt(String inputData, String inputKey)
            throws Exception {
        byte[] byteInputData = Base64.decodeBase64(inputData);

        byte[] DESkey = inputKey.getBytes();// 设置密钥
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象

        // using DES in ECB mode
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte decryptedData[] = cipher.doFinal(byteInputData);
        String value = new String(decryptedData, "UTF-8");
        return value;
    }


    public static void main(String[] args) throws Exception {
        String key = "asdfqwer";
        String value = "张三test1234 ";

        System.out.println("加密数据:" + value);
        String a = DESEncrypt(value, key);

        System.out.println("加密后的数据为:" + a);
        String b = DESDecrypt(a, key);
        System.out.println("解密后的数据:" + b);

    }


}