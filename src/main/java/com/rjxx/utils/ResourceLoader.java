package com.rjxx.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 资源加载类
 * Created by Him on 2015-09-22.
 */
public class ResourceLoader {
    public static String CLASS_PATH_PREFIX ="classpath:";

    /**
     * classpath中获取资源
     * @Title: getResource
     * @Description: classpath中获取资源
     * @param resource
     * @return
     * @author
     */
    public static URL getResource(String resource) {
        ClassLoader classLoader = null;
        classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource(resource);
    }

    /**
     *  classpath 中搜索路径
     * @Title: getPath
     * @Description:
     * @param resource
     * @return
     * @author
     */
    public static String getPath(String resource){
        if(resource!=null){
            if(resource.startsWith(CLASS_PATH_PREFIX)){
                resource = getPath("")+resource.replaceAll(CLASS_PATH_PREFIX, "");
            }
        }

        URL url = getResource(resource);
        if(url==null)
            return null;
        return url.getPath().replaceAll("%20", " ");
    }
    /*public static void main(String[] args){
    	System.out.println(new File(getPath("")).getParentFile()
				.getParentFile());
    	try {
    		System.out.println(ReadProperties.read("serverUrl"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
    /**
     *
     * @Title: getPath
     * @Description:
     * @param resource
     * @param clazz
     * @return
     * @author
     */
    public static String getPath(String resource,Class clazz){
        URL url = getResource(resource, clazz);
        if(url==null)
            return null;
        return url.getPath().replaceAll("%20", " ");
    }

    /**
     * 指定class中获取资源
     * @Title: getResource
     * @Description: 指定class中获取资源
     * @param resource
     * @param clazz
     * @return
     * @author
     */
    public static URL getResource(String resource,Class clazz){
        return clazz.getResource(resource);
    }

    /**
     * If running under JDK 1.2 load the specified class using the
     * <code>Thread</code> <code>contextClassLoader</code> if that fails try
     * Class.forname. Under JDK 1.1 only Class.forName is used.
     *
     */
    public static Class loadClass(String clazz) throws ClassNotFoundException {
        return Class.forName(clazz);
    }
}
