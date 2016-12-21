package com.rjxx.utils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class CheckUtil {
    public static void main(String[] args) {

        String s = "123;";
        //System.out.print(s.replace(s.substring(s.length()-1), ""));
        System.out.print(s.charAt(s.length() - 1));
        System.out.print(Double.parseDouble(".00") == 0);
    }

    public static String checkAll(String lsh, Map main, Map seller, Map buyer, String xfsh, String xfmc, String xfdz, String xfdh
            , String issend, String email, String gfmc, String xmmc, String sl,
                                  List Details, String num) {
        String s = "";
        String RtnStr = "";
        if (lsh == null || "".equals(lsh)) {
            s += "9000:电商平台请求参数SerialNumber为空;";
        } else {
            if (lsh.length() > 20 || isString(lsh) == false) {
                s += "9001:电商平台请求参数SerialNumber长度或格式有误;";
            }
        }
        if (main == null) {
            s += "9040:接口报文Main节点不存在;";
        }
        if (seller == null) {
            s += "9034:电商平台请求Seller信息有误;";
        }
        if (xfsh == null || "".equals(xfsh)) {
            s += "9002:电商平台请求参数Seller-Identifier为空;";
        } else {
            if (xfsh.length() > 20 || isString(xfsh) == false) {
                s += "9003:电商平台请求参数Seller-Identifier长度或格式有误;";
            }
        }
        if (xfmc == null || "".equals(xfmc)) {
            s += "9004:电商平台请求参数Seller-Name为空;";
        } else {
            if (xfmc.length() > 100) {
                s += "9005:电商平台请求参数Seller-Name长度有误;";
            }
        }

        if (xfdz == null || "".equals(xfdz)) {
            s += "9006:电商平台请求参数Seller-Address为空;";
        } else {
            if (xfdz.length() > 100) {
                s += "9007:电商平台请求参数Seller-Address长度有误;";
            }
        }
        if (xfdh == null || "".equals(xfdh)) {
            s += "9008:电商平台请求参数Seller-TelephoneNumber为空;";
        }/*else{
            if(isPhone(xfdh)==false){
			    s+="9009:电商平台请求参数Seller-TelephoneNumber格式有误;";
			}
		}*/
        if (buyer == null) {
            s += "9035:电商平台请求Buyer信息有误;";
        }
        if (issend == null || "".equals(issend)) {
            s += "9010:电商平台请求参数Buyer-IsSend为空;";
        } else if (issend == "1" || "1".equals(issend)) {
            if (email == null || "".equals(email)) {
                s += "9012:电商平台请求参数Buyer-Email为空;";
            }/*else{
                if(isEmail(email)==false){
		    		s+="9013:电商平台请求参数'Buyer-Email'格式有误;";	
		    	}
		    }*/
        } else {
            if (issend.length() > 1) {
                s += "9011:电商平台请求参数Buyer-IsSend长度有误;";
            }
        }
        if (gfmc == null || "".equals(gfmc)) {
            s += "9014:电商平台请求参数Buyer-Name为空;";
        } else {
            if (gfmc.length() > 100) {
                s += "9015:电商平台请求参数Buyer-Name长度有误;";
            }
        }
        if (xmmc == null || "".equals(xmmc)) {
            s += "9016:电商平台请求参数ProductItem-Description为空;";
        } else {
            if (xmmc.length() > 100) {
                s += "9017:电商平台请求参数ProductItem-Description长度有误;";
            }
        }
        if (sl == null || "".equals(sl)) {
            s += "9018:电商平台请求参数ProductItem-Rate为空;";
        } else {
            if (isdouble(sl) == false) {
                s += "9019:电商平台请求参数ProductItem-Rate格式有误;";
            }

        }
        if (num == null || "".equals(num)) {
            s += "9020:电商平台请求参数ProductItem-Quantity为空;";
        } else {
            if (isdouble(num) == false && isNumber(num) == false) {
                s += "9021:电商平台请求参数ProductItem-Quantity格式有误;";
            }
        }
        Map detail;
        int l = Details.size();
        if (l < 1) {
            s += "9036:电商平台请求Details信息有误;";
        }
        Double totaljshj = 0.00;
        for (int i = 0; i < l; i++) {
            detail = (Map) Details.get(i);
            String je = (String) detail.get("AMOUNT");
            String dj = (String) detail.get("UNITPRICE");
            if ((!"".equals(dj)) && dj != null) {
                if (isdouble(dj) == false && isNumber(dj) == false) {
                    s += "9025:电商平台请求参数第" + (i + 1) + "行ProductItem-UnitPrice格式有误;";
                }
            }
            if ("".equals(je) || je == null) {
                s += "9023:电商平台请求参数第" + (i + 1) + "行ProductItem-Amount为空;";
            } else {
                if ((isdouble(je) == false && isNumber(je) == false) || (Double.parseDouble(je) == 0)) {
                    s += "9024:电商平台请求参数第" + (i + 1) + "行ProductItem-Amount格式有误;";
                } else {
                    totaljshj += Double.parseDouble(je);
                    /*try{
                    if (Double.valueOf(dj) * Double.valueOf(num) != Double.valueOf(je)) {
	    			    s+="9026:电商平台请求参数第"+(i+1)+"行不满足ProductItem-UnitPrice*ProductItem-Quantity= ProductItem-Amount;";
	    		    }
	    			}catch(Exception e){}*/
                }

            }
        }
        URL url =Thread.currentThread().getContextClassLoader().getResource("attr.properties");
		Properties p = new Properties(); 
		try {
			p.load(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        String invoiceMaximumAmount =String.valueOf(p.get("Invoice_Maximum_Amount")) ;
        if (totaljshj > Double.parseDouble(invoiceMaximumAmount)) {
            s += "9041:订单合计金额超过单张发票开票最大限额;";
        }
        if (s != "" && !"".equals(s)) {
            //s=s.replace(s.charAt(s.length()-1)+"", ".");
            RtnStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<Responese>\n  <ReturnCode>9999</ReturnCode>\n"
                    + "  <Djh></Djh>\n <ReturnMessage>" + s + "</ReturnMessage>\n</Responese>";
        }
        return RtnStr.replace(";<", ".<");
    }

    public static boolean isString(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        return pattern.matcher(str).matches();
    }

    public static boolean isPhone(String str) {
        Pattern p1 = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\d{8})?$");
        //guhua
        Pattern p2 = Pattern.compile("^(0[0-9]{2,3}\\-)?([1-9][0-9]{6,7})$");
        if (((str.length() == 11 && p1.matcher(str).matches())
                || (str.length() < 16 && p2.matcher(str).matches()))) {
            return true;
        }
        return false;
    }

    public static boolean isEmail(String str) {
        //"\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"
        String check = "([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}";
        Pattern pattern = Pattern.compile(check);
        return pattern.matcher(str).matches();
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isdouble(String s) {
        boolean f = true;
        try {
            Double.parseDouble(s);
        } catch (Exception e) {
            f = false;
        }
        return f;
    }

}
