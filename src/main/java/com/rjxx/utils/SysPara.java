package com.rjxx.utils;

import java.io.InputStream;
import java.util.Properties;

public class SysPara {
    private static Properties property = null;
    private static String propertyfile = "/url.properties";

    public SysPara() {
        this(propertyfile);
    }

    public SysPara(String propertyfile) {
        try {
            property = new Properties();
            InputStream inputStream = getClass().getResourceAsStream(propertyfile);
            property.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    static {
        init();
    }

    private static void init() {
        try {
            property = new Properties();
            InputStream inputStream = SysPara.class.getClassLoader().getResourceAsStream("url.properties");
            property.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
        }
        try {

        } catch (Exception e) {
        }
    }

    public static String getValue(String key) {
        String temp = null;
        try {
            temp = property.getProperty(key, "");
            temp = new String(temp.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
           e.printStackTrace();
        }
        return temp;
    }

    public static void main(String[] args) {

        System.out.println(SysPara.getValue("title"));
    }
}

