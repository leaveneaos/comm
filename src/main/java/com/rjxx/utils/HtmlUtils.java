package com.rjxx.utils;

import javax.servlet.http.HttpServletRequest;

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

}
