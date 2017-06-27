package com.rjxx.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/23.
 */
public class HtmlUtils {

    /**
     * 忽略html标签
     *
     * @param htmlContent
     * @return
     */
    public static String ignoreHtmlTag(String htmlContent) {
        return htmlContent.replaceAll("<(!|/)?(.|\\n)*?>", "").replaceAll("&nbsp;", "");
    }

    /**
     * 获取域名
     *
     * @param request
     * @return
     */
    public static String getDomainPath(HttpServletRequest request) {
        String domainPath = null;
        if (request.getServerPort() == 80 || request.getServerPort() == 443) {
            domainPath = request.getScheme() + "://" + request.getServerName() + "/";
        } else {
            domainPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        }
        return domainPath;
    }

    /**
     * 获取项目路径
     *
     * @param request
     * @return
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = null;
        if (request.getServerPort() == 80 || request.getServerPort() == 443) {
            basePath = request.getScheme() + "://" + request.getServerName() + path + "/";
        } else {
            basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        }
        return basePath;
    }

    /**
     * 补齐url，以http开头
     *
     * @param request
     * @param url
     * @return
     */
    public static String finishedUrl(HttpServletRequest request, String url) {
        if (url.startsWith("http:") || url.startsWith("https:")) {
            return url;
        } else if (url.startsWith("/")) {
            return getBasePath(request) + url.substring(1);
        } else {
            return getBasePath(request) + url;
        }
    }

    /**
     * 将查询字符串转换成Map
     *
     * @param queryString
     * @return
     */
    public static Map<String, String> parseQueryString(String queryString) {
        String[] arr1 = queryString.split("&");
        Map<String, String> ret = new HashMap<>();
        for (String str : arr1) {
            int pos = str.indexOf("=");
            if (pos != -1) {
                ret.put(str.substring(0, pos), str.substring(pos + 1));
            } else {
                ret.put(str, "");
            }
        }
        return ret;
    }

    /**
     * 获取请求参数转换成string
     *
     * @param request
     * @return
     */
    public static String getRequestParamsString(HttpServletRequest request) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }
}
