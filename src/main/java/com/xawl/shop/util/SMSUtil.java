package com.xawl.shop.util;

import java.util.Random;

import SmsService.AbsRestClient;
import SmsService.JsonReqClient;
import SmsService.XmlReqClient;

public class SMSUtil {

	public static String getRandNum(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	public static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	static AbsRestClient InstantiationRestAPI(boolean enable) {
		if (enable) {
			return new JsonReqClient();
		} else {
			return new XmlReqClient();
		}
	}

	public static void testTemplateSMS(boolean json, String accountSid,
			String authToken, String appId, String templateId, String to,
			String param) {
		try {
			String result = InstantiationRestAPI(json).templateSMS(accountSid,
					authToken, appId, templateId, to, param);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String accountSid = "47e9e47509665976c8a94cc4b574f46a";
        String token = "c8b991db1ec194bd76306ca012216222";
        String appId = "9aaa474190aa453f89c7c1ea9b74a00b";
        String templateId = "35254";
        String para = SMSUtil.getRandNum(6);
        String mobile="18292882168";
        SMSUtil.testTemplateSMS(true, accountSid, token, appId, templateId,
                mobile, para);

	}

}
