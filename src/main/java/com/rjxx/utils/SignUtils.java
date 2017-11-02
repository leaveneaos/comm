package com.rjxx.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by xlm on 2017/7/21.
 */
public class SignUtils {

    /**
     * 接口签名生成方法
     * @param QueryData
     * @param key
     * @return
     */
    public static String getSign(String QueryData,String key){
        String signSourceData = "data=" + QueryData + "&key=" + key;
        String newSign =  DigestUtils.md5Hex(signSourceData);
        return newSign;
    }
    public static String getSign2(String QueryData,String key){
        String signSourceData = "on=" + QueryData + "&key=" + key;
        String newSign =  DigestUtils.md5Hex(signSourceData);
        return newSign;
    }
    public static void main(String[] args) {
        String s=SignUtils.getSign2("092120221701307042144","eb27684df1279b68d29e578b421daa58");
        System.out.println(s);
    }
}
