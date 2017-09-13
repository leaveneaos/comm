package com.rjxx.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/7.
 */
public class AppKeySecretUtils {

    /**
     * 随机生成appKey和secret
     * String[0]:appKey
     * String[1]:secret
     *
     * @return
     */
    public static String[] generate() {
        String seed = UUID.randomUUID().toString().replace("-", "");
        String appKey = DigestUtils.md5Hex(seed.substring(7));
        String secret = DigestUtils.md5Hex(seed.substring(8));
        return new String[]{
                appKey, secret
        };
    }

    /**
     * 生成md5
     *
     * @param data
     * @param key
     * @return
     */
    public static String genEncryptQueryString(String data, String key) throws Exception {
        String data1 = data + "&key=" + key;
        String sign = DigestUtils.md5Hex(data1);
        data = data + "&sign=" + sign;
        data = Base64.encodeBase64String(data.getBytes("UTF-8"));
        return java.net.URLEncoder.encode(data, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            String[] arr = generate();
            System.out.println(arr[0].substring(0, 12) + ":" + arr[1]);
        }
        /*String data = "on=201610151255111235&ot=20161013125511&pr=23&sn=sh001";
        String key = "0f2aa080911da0adcfc5f630e9d20e1a";
        System.out.print(genEncryptQueryString(data, key));*/
       String d="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:glob=\"http://sap.com/xi/SAPGlobal20/Global\" xmlns:y1j=\"http://0001092235-one-off.sap.com/Y1JU2A0ZY_\">\n" +
               "   <soapenv:Header/>\n" +
               "   <soapenv:Body>\n" +
               "      <glob:CustomerInvoiceByElementsQuery_sync>\n" +
               "         <CustomerInvoiceSelectionByElements>\n" +
               "            <SelectionByLastChangeDateTime>\n" +
               "               <InclusionExclusionCode>I</InclusionExclusionCode>\n" +
               "               <IntervalBoundaryTypeCode>3</IntervalBoundaryTypeCode>\n" +
               "               <LowerBoundaryCustomerInvoiceLastChangeDateTime>2017-07-21T00:00:00.00000Z</LowerBoundaryCustomerInvoiceLastChangeDateTime>\n" +
               "               <UpperBoundaryCustomerInvoiceLastChangeDateTime>2017-07-31T00:00:00.00000Z</UpperBoundaryCustomerInvoiceLastChangeDateTime>\n" +
               "            </SelectionByLastChangeDateTime>\n" +
               "         </CustomerInvoiceSelectionByElements>\n" +
               "         <ProcessingConditions>\n" +
               "            <QueryHitsMaximumNumberValue>500</QueryHitsMaximumNumberValue>\n" +
               "            <QueryHitsUnlimitedIndicator>false</QueryHitsUnlimitedIndicator>\n" +
               "         </ProcessingConditions>\n" +
               "      </glob:CustomerInvoiceByElementsQuery_sync>\n" +
               "   </soapenv:Body>\n" +
               "</soapenv:Envelope>";

    }

}
