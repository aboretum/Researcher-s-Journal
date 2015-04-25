package com.ase.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUnit {
	public static Date getDateByString(String dateString) throws ParseException{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date result = df.parse(dateString);
		return result;
	}
}
