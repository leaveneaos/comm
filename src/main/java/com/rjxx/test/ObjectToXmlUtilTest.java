package com.rjxx.test;

import java.util.*;

import com.rjxx.utils.ObjectToXmlUtil;

public class ObjectToXmlUtilTest {
   
	
	 public static void main(String args[]){
		  
		  ObjectToXmlUtil t = new ObjectToXmlUtil();
		  Map map =demo();
		  t.createXmlFile("/","demo.xml","gbk", map);
	  }
	   
	  
	    //封装object对象样例  FPKJ报文
	    private static Map demo(){
	    	COMMON_FPKJ_FPT t = new COMMON_FPKJ_FPT();
	    	t.setFPQQLSH("发票请求流水号");
	    	t.setKPLX("开票类型");
	    	t.setBMB_BBH("编码表版本号");
	    	t.setZSFS("征税方式");
	    	t.setXSF_NSRSBH("销售方纳税人识别号");
	    	t.setXSF_MC("销售方名称");
	    	t.setSKR("收款人");
	    	t.setFHR("复核人");
	    	t.setYFP_DM("");
	    	t.setYFP_HM("");
	    	t.setJSHJ("价税合计");
	    	t.setHJJE("合计金额");
	    	t.setHJSE("合计税额");
	    	t.setKCE("扣除额");
	    	t.setBZ("备注");
	    	t.setXSF_DZDH("销售方地址、电话");
	    	t.setXSF_YHZH("销售方银行账号");
	    	t.setGMF_NSRSBH("购买方纳税人识别号");
	    	t.setGMF_MC("购买方名称");
	    	t.setGMF_DZDH("购买方地址、电话");
	    	t.setGMF_YHZH("购买方银行账号");
	    	t.setKPR("开票人");
	    	List<COMMON_FPKJ_XMXX> xmxxList = new ArrayList();
	  	  for(int i=0;i<3;i++){
	  		  COMMON_FPKJ_XMXX common_fpkj_xmxx = new COMMON_FPKJ_XMXX();
	  		  common_fpkj_xmxx.setFPHXZ("01"+i);
	  		  common_fpkj_xmxx.setSPBM("02"+i);
	  		  common_fpkj_xmxx.setZXBM("");
	  		  xmxxList.add(common_fpkj_xmxx);
	  	  }
	  	  Map map = new HashMap();
	  	  map.put("xmxxList",xmxxList);
	  	  map.put("xmxxLength",xmxxList.size());
	  	  map.put("t", t);
	  	  return map;
	    }
	    
}
