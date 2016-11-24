package com.xawl.shop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateUtil {

	public static String getSqlDate() {
		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = myFmt1.format(new Date());
		return format;
	}

	public static boolean compTo(String currentTime, int number)
			throws ParseException {
		SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date current = new java.util.Date();
		Date parse = myFmt1.parse(currentTime);
		long time = parse.getTime();
		long time2 = current.getTime();
		if ((time2 - time) / 60000 <= number) {
			return true;
		} else {
			return false;
		}
	}
}
