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
}
