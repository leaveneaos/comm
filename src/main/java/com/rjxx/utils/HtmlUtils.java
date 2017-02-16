package com.rjxx.utils;

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

}
