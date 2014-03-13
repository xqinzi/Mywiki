package lesson01;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final String COMMON = "yyyy-MM-dd HH:mm:ss";
	
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(COMMON);
		
		return sdf.format(new Date());
	}
	
	public static String fromDate2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(COMMON);
		
		return sdf.format(date);
	}
	
	public static Date buildDate(int year,int mouth,int day,int hour , int minute , int second) {
		Calendar c = Calendar.getInstance();
		c.set(year,mouth-1, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		
		return c.getTime(); 
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.fromDate2String(DateUtil.buildDate(2014,3,12,21,12,11)));
	}
}
