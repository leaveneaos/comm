package com.rjxx.utils;

import java.util.HashMap;
import java.util.Map;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
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

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("code", "0518");
		map.put("product", "泰易电子发票");
		SendMessage.sendMessage("泰易电子发票", "SMS_33950398", map, "18721534978");
	}
}
