package com.rjxx.utils;

import java.util.HashMap;
import java.util.Map;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SendMessage {

	public static String sendMessage(String qmmc, String mbCode, Map<String, String> content, String sjhm) {
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "5T6XUKr6uxSfhNAu","a7cBFQR3avT4NSIR6dFtP8GLvzcL5G"
				);
		String message="";
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			request.setSignName(qmmc);
			request.setTemplateCode(mbCode);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(content);
			request.setParamString(json);
			request.setRecNum(sjhm);
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			message = httpResponse.getRequestId();
			return message;
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return message;
	}
	public static String sendSms(String qmmc, String mbCode, Map<String, String> content, String sjhm) {
		String message="";
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "5T6XUKr6uxSfhNAu","a7cBFQR3avT4NSIR6dFtP8GLvzcL5G");
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
			IAcsClient acsClient = new DefaultAcsClient(profile);
			//组装请求对象
			 SendSmsRequest request = new SendSmsRequest();
			 //使用post提交
			 request.setMethod(MethodType.POST);
			 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
			 request.setPhoneNumbers(sjhm);
			 //必填:短信签名-可在短信控制台中找到
			 request.setSignName(qmmc);
			 //必填:短信模板-可在短信控制台中找到
			 request.setTemplateCode(mbCode);
			 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
			 ObjectMapper objectMapper = new ObjectMapper();
			 String json = objectMapper.writeValueAsString(content);
			 request.setTemplateParam(json);
			 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
			 //request.setSmsUpExtendCode("90997");
			 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			 request.setOutId("yourOutId");
			 //请求失败这里会抛ClientException异常
			 SendSmsResponse sendSmsResponse= acsClient.getAcsResponse(request);
			 if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				 message =sendSmsResponse.getRequestId();
				 //请求成功
			 }else if("isv.BUSINESS_LIMIT_CONTROL".equals(sendSmsResponse.getCode())){
				 message="sendFail";
				 System.out.println("业务限流");
			 }else if("isv.MOBILE_NUMBER_ILLEGAL".equals(sendSmsResponse.getCode())){
				 System.out.println("非法手机号");
				 message="sendFail";
			 }else if("isv.TEMPLATE_MISSING_PARAMETERS".equals(sendSmsResponse.getCode())) {
				 System.out.println("模板缺少变量");
				 message="sendFail";
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			message="sendFail";
		}
		return message;
	}
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("code", "051898");
		map.put("product", "容津信息");
		SendMessage.sendSms("泰易电子发票", "SMS_33950398", map, "2387932332");
		//SendMessage.sendMessage("泰易电子发票", "SMS_33950398", map, "23332323");
	}
}
