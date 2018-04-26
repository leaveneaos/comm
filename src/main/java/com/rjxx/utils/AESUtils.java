package com.rjxx.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by Administrator on 2016/9/6.
 */
public class AESUtils {
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
        System.out.println(byteKey.length);
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
