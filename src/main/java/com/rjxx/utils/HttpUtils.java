package com.rjxx.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-13.
 */
public class HttpUtils {

    /**
     * 发送http的post请求
     *
     * @param url
     * @param nameValuePairList
     * @param charset
     * @return
     */
    public static String doPost(String url, List<NameValuePair> nameValuePairList, String charset) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairList);
        httpPost.setEntity(httpEntity);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        InputStream is = httpResponse.getEntity().getContent();
        String result = IOUtils.toString(is, charset);
        return result;
    }

    /**
     * 发送http的post请求
     *
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static String doPost(String url, Map<String, String> params, String charset) throws Exception {
        List<NameValuePair> nameValuePairList = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            nameValuePairList.add(pair);
        }
        return doPost(url, nameValuePairList, charset);
    }

    /**
     * 发送http的post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) throws Exception {
        return doPost(url, params, "UTF-8");
    }
}
