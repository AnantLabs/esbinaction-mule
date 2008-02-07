package esb.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateJiBXSerializer {
	
	private static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date deserialize(String dateValue) {
		if(dateValue == null || dateValue.length() == 0) 
			return null;
		try {
			return simpleFormat.parse(dateValue);
		} catch(Exception e) {
			return null;
		}
	}
	
	public static String serialize(Date dateValue) {
		if(dateValue == null) return null;
		return simpleFormat.format(dateValue);
	}

}
