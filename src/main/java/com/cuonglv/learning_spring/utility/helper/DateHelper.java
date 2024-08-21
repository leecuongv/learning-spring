package com.cuonglv.learning_spring.utility.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	public static String convertDateToString(Date date, String format) {

		String result = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		result = simpleDateFormat.format(date);

		return result;
	}
}
