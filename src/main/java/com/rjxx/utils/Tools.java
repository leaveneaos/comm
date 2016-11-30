package com.rjxx.utils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Tools {
	public static void out(HttpServletResponse response, String rtnStr)
			throws IOException {
		OutputStreamWriter osw = new OutputStreamWriter(
				response.getOutputStream(), "gbk");
		osw.write(rtnStr.trim());
		osw.flush();
		osw.close();
	}
	/*public static String getErrorXML(int id,Map<String,Object> map) {
		String returnCode=(String) map.get("RETURNCODE");
		String returnMessage=(String) map.get("RETURNMSG");
		String id1=(String) map.get("id");
		returnMessage = returnMessage.replaceAll("\"", "“");		
		return "<?xml version=\"1.0\" encoding=\"gbk\"?>"+
				"<business id=\""+id1 + "\"" +"comment=\"程序异常处理\"><RESPONSE_COMMON_" + id1
				+ "class=\"RESPONSE_COMMON_"+id1+"\">"
				+"<ID>"+id+"</ID>"//  前台传的id值
				+ "<returncode>"+ returnCode+ "</returncode>"
				+ "<returnmsg>"+ returnMessage+ "</returnmsg>" 
				+ "</RESPONSE_COMMON_"+id1+"></business>";
	}*/
	public static String getErrorXML(Map<String,Object> map,int id) {
		String returnCode=(String) map.get("RETURNCODE");
		String returnMessage=(String) map.get("RETURNMSG");		
		returnMessage = returnMessage.replaceAll("\"", "“");		
		return "<?xml version=\"1.0\" encoding=\"gbk\"?>"+
				"<business id=\"RESPONES\" comment=\"程序异常处理\"><RESPONSE_COMMON_RESPONSE "
				+ "class=\"RESPONSE_COMMON_RESPONSE\">"
				+"<ID>"+id+"</ID>"//  前台传的id值
				+"<ORDERNUMBER>"+map.get("ordernumber")+"</ORDERNUMBER>"//订单号  
				+ "<RETURNCODE>"+ returnCode+ "</RETURNCODE>"
				+ "<RETURNMSG>"+ returnMessage+ "</RETURNMSG>" 
				+ "</RESPONSE_COMMON_RESPONSE></business>";
	}
	public static String getErrorXML(int id,String ordernumber,String returnCode, String returnMessage) {
		returnMessage = returnMessage.replaceAll("\"", "“");		
		return "<?xml version=\"1.0\" encoding=\"gbk\"?>"+
				"<business id=\"RESPONES\" comment=\"程序异常处理\"><RESPONSE_COMMON_RESPONSE "
				+ "class=\"RESPONSE_COMMON_RESPONSE\">"
				+"<ID>"+id+"</ID>"//  前台传的id值
				+"<ORDERNUMBER>"+ordernumber+"</ORDERNUMBER>"//订单号  
				+ "<RETURNCODE>"+ returnCode+ "</RETURNCODE>"
				+ "<RETURNMSG>"+ returnMessage+ "</RETURNMSG>" 
				+ "</RESPONSE_COMMON_RESPONSE></business>";
	}
	public static String getErrorXML(String returnCode, String returnMessage) {
		returnMessage = returnMessage.replaceAll("\"", "“");
		return "<?xml version=\"1.0\" encoding=\"gbk\"?>"+
				"<business id=\"RESPONES\" comment=\"程序异常处理\"><RESPONSE_COMMON_RESPONSE "
				+ "class=\"RESPONSE_COMMON_RESPONSE\">"				
				+ "<RETURNCODE>"+ returnCode+ "</RETURNCODE>"
				+ "<RETURNMSG>"+ returnMessage+ "</RETURNMSG>" 
				+ "</RESPONSE_COMMON_RESPONSE></business>";
	}
	public static Map getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				value = "";
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					//value = values[i] + ",";
					value += values[i] +",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
}
