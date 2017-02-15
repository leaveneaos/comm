package com.rjxx.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;


public class DesUtils {

    public static String GLOBAL_DES_KEY = "A!9fF&vH";

    /**
     * des加密后转成16进制形式的字符串
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

        return bytesToHexString(pasByte);
    }

    /**
     * 先16进制字符串转换成byte数组，然后解密
     *
     * @param inputData
     * @param inputKey
     * @return
     * @throws Exception
     */
    public static String DESDecrypt(String inputData, String inputKey)
            throws Exception {
        byte[] byteInputData = hexStringToBytes(inputData);

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

    /**
     * 转换16进制形式的字符串为byte数组
     *
     * @param ss
     * @return
     */
    public static byte[] hexStringToBytes(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }

        return digest;
    }

    /**
     * byte数组转换成16进制形式字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
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