package com.rjxx.utils;

/**
 * Created by Administrator on 2016/8/18.
 */
public class ResponseUtils {

    /**
     * 返回错误的消息
     *
     * @param errorMessage
     * @return
     */
    public static String printFailure(String errorMessage) {
        return "<Responese>\n  <ReturnCode>9999</ReturnCode>\n" +
                "  <Djh></Djh>\n  <ReturnMessage>" + errorMessage + "</ReturnMessage>\n</Responese>";
    }
    
    public static String printFailure1(String errorMessage) {
        return "<Responese>\n  <ReturnCode>0000</ReturnCode>\n" +
                " <ReturnMessage>" + errorMessage + "</ReturnMessage>\n</Responese>";
    }

    /**
     * 返回正确结果
     *
     * @param djh
     * @return
     */
    public static String printSuccess(int djh) {
        return "<Responese>\n  <ReturnCode>0000</ReturnCode>\n" +
                "  <Djh>" + djh + "</Djh>\n  <ReturnMessage>待开票数据保存成功</ReturnMessage>\n</Responese>";
    }

}
