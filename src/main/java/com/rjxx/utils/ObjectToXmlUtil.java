package com.rjxx.utils;


import java.io.File;
import java.io.IOException;  
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;  
import java.util.Map;  
  
  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
  
public class ObjectToXmlUtil  
{  
    /** 
     *  
     * @param xmlPath xml的路径 
     * @param xmlName xml的名称
     * @param code 数据编码格式 
     * @param dataMap 需要渲染到xml中的map数据 
     * @return xmlString
     */  
    public  String createXmlFile(String xmlPath,String xmlName,String code, Map dataMap)  
    {  
    	String result = "";  
   
        //得FreeMarker配置对象
    	// 创建Configuration对象  
        Configuration cfg = new Configuration();  
        //设置模板编码格式
        cfg.setEncoding(Locale.getDefault(),code); 
         
        //得FreeMarker的关键对象---------模板  
 
        // 创建Template对象  
        Template template = null;  
        try  
        {  
        	// 设置FreeMarker的模版文件位置  
            //cfg.setClassForTemplateLoading(this.getClass(),"");
        	cfg.setDirectoryForTemplateLoading(new File(xmlPath));
            template = cfg.getTemplate(xmlName);  
        }  
        catch (IOException e1)  
        {  
            e1.printStackTrace();  
        }  
        template.setEncoding(code);  
  
        //String path = ServletActionContext.getServletContext().getRealPath("/");  
  
       /* File dir = new File(path + htmlPath);  
        if (!dir.exists())  
        {  
            dir.mkdirs();  
        } */ 
  
       // File fileName = new java.io.File(path + htmlPath + htmlName);  
        //System.out.println("html file:" + fileName.getPath());  
  
        //Writer writer = null;  
        java.io.StringWriter w =new StringWriter();  
        try  
        {  
            // 生成xml
            template.process(dataMap, w);  
            System.out.println(w.toString());  
            result = w.toString();  
        }  
        catch (TemplateException e)  
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }   
  
        return result;  
    } 
    
}