package com.rjxx.utils;


import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.StringWriter;
import java.util.Map;

public class TemplateUtils {

    /**
     * 根据模板生成内容
     *
     * @param templateFile 模板文件
     * @param dataMap      需要渲染到xml中的map数据
     * @return xmlString
     */
    public static String generateContent(File templateFile, Map dataMap, String encoding) throws Exception {
        Configuration configuration = new Configuration(Configuration.getVersion());
        Template template = new Template(templateFile.getCanonicalPath(), FileUtils.readFileToString(templateFile, encoding), configuration);
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        template.process(dataMap, writer);
        String content = stringWriter.toString();
        writer.flush();
        writer.close();
        return content;
    }

    /**
     * 根据模板生成内容
     *
     * @param templateFile
     * @param dataMap
     * @return
     * @throws Exception
     */
    public static String generateContent(File templateFile, Map dataMap) throws Exception {
        return generateContent(templateFile, dataMap, "UTF-8");
    }

    /**
     * 默认模板在classpath下的template文件夹中
     *
     * @param templateName
     * @param dataMap
     * @return
     * @throws Exception
     */
    public static String generateContent(String templateName, Map dataMap) throws Exception {
        String path = java.net.URLDecoder.decode(TemplateUtils.class.getClassLoader().getResource("/template/" + templateName).getPath(), "UTF-8");
        return generateContent(new File(path), dataMap);
    }

}