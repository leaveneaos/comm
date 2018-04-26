package com.rjxx.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


/**
 * Created by Administrator on 2016/9/6.
 */
public class AESUtils {



    static String password = "DLCUAOFM";
    static Cipher cipher;
    static final String KEY_ALGORITHM = "AES";
    static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";
    /*
     *
     */
    static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
    /*
     * AES/CBC/NoPadding 要求
     * 密钥必须是16位的；Initialization vector (IV) 必须是16位
     * 待加密内容的长度必须是16的倍数，如果不是16的倍数，就会出如下异常：
     * javax.crypto.IllegalBlockSizeException: Input length not multiple of 16 bytes
     *
     *  由于固定了位数，所以对于被加密数据有中文的, 加、解密不完整
     *
     *  可 以看到，在原始数据长度为16的整数n倍时，假如原始数据长度等于16*n，则使用NoPadding时加密后数据长度等于16*n，
     *  其它情况下加密数据长 度等于16*(n+1)。在不足16的整数倍的情况下，假如原始数据长度等于16*n+m[其中m小于16]，
     *  除了NoPadding填充之外的任何方 式，加密数据长度都等于16*(n+1).
     */
    static final String CIPHER_ALGORITHM_CBC_NoPadding = "AES/CBC/NoPadding";

    static SecretKey secretKey;

    static {
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            secretKey = kgen.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String message = "aaa";
        System.out.println(decrypt(encrypt(message)));
    }

    /**
     * 使用AES 算法 加密，默认模式  AES/ECB
     */
//    public static void method1(String str) throws Exception {
//        cipher = Cipher.getInstance(KEY_ALGORITHM);
//        //KeyGenerator 生成aes算法密钥
//        secretKey = KeyGenerator.getInstance(KEY_ALGORITHM).generateKey();
//        System.out.println("密钥的长度为：" + secretKey.getEncoded().length);
//
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);//使用加密模式初始化 密钥
//        byte[] encrypt = cipher.doFinal(str.getBytes()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
//
//        System.out.println("method1-加密：" + Arrays.toString(encrypt));
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);//使用解密模式初始化 密钥
//        byte[] decrypt = cipher.doFinal(encrypt);
//        System.out.println("method1-解密后：" + new String(decrypt));
//
//    }

    /**
     * 使用AES 算法 加密，默认模式 AES/ECB/PKCS5Padding
     */
    public static byte[] encrypt(String str) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用加密模式初始化 密钥
        byte[] encrypt = cipher.doFinal(str.getBytes()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
        return encrypt;


    }

    /**
     * 解密
     *
     * @param encryptBytes
     * @return
     * @throws Exception
     */
    public static String decrypt(byte[] encryptBytes) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用解密模式初始化 密钥
        byte[] decrypt = cipher.doFinal(encryptBytes);
        return new String(decrypt);
    }

    public static byte[] getIV() {
        String iv = "%$#@&*()@$#(!&$^"; //IV length: must be 16 bytes long
        return iv.getBytes();
    }

    /**
     * 使用AES 算法 加密，默认模式 AES/CBC/PKCS5Padding
     */
//    public static void method3(String str) throws Exception {
//        cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
//        //KeyGenerator 生成aes算法密钥
//        secretKey = KeyGenerator.getInstance(KEY_ALGORITHM).generateKey();
//        System.out.println("密钥的长度为：" + secretKey.getEncoded().length);
//
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用加密模式初始化 密钥
//        byte[] encrypt = cipher.doFinal(str.getBytes()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
//
//        System.out.println("method3-加密：" + Arrays.toString(encrypt));
//        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用解密模式初始化 密钥
//        byte[] decrypt = cipher.doFinal(encrypt);
//        System.out.println("method3-解密后：" + new String(decrypt));
//
//    }

    /**
     * 使用AES 算法 加密，默认模式 AES/CBC/NoPadding  参见上面对于这种mode的数据限制
     */
//    public static void method4(String str) throws Exception {
//        cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC_NoPadding);
//        //KeyGenerator 生成aes算法密钥
//        secretKey = KeyGenerator.getInstance(KEY_ALGORITHM).generateKey();
//        System.out.println("密钥的长度为：" + secretKey.getEncoded().length);
//
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用加密模式初始化 密钥
//        byte[] encrypt = cipher.doFinal(str.getBytes(), 0, str.length()); //按单部分操作加密或解密数据，或者结束一个多部分操作。
//
//        System.out.println("method4-加密：" + Arrays.toString(encrypt));
//        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(getIV()));//使用解密模式初始化 密钥
//        byte[] decrypt = cipher.doFinal(encrypt);
//
//        System.out.println("method4-解密后：" + new String(decrypt));
//
//    }

    /**
     * 加密
     * @param str
     * @param key
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null){ return null;
        }
        byte[] byteKey= hexStringToBytes(key);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(byteKey, "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return StringUtils.replaceSpecialStr(new BASE64Encoder().encode(bytes));
    }

    /**
     * 解密
     * @param str
     * @param key
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(byte[] str, String key) throws Exception {
        if (str == null || key == null){ return null;}

        byte[] byteKey= hexStringToBytes(key);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(byteKey, "AES"));
        //byte[] bytes = hexStringToBytes(str);
        byte[]  bytes = cipher.doFinal(str);
        return new String(bytes, "utf-8");
    }


    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private static String byteToHexString(byte[] data) {
        String str = "";
        for (int i = 0; i < data.length; i++) {

            String value = Integer.toHexString(data[i] & 0xFF);

            if (value.length() == 1) {
                value = "0" + value;
            }

            str += value;
        }

        return str.toUpperCase();
    }
}
