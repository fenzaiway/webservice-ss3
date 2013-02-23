package com.way.blog.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author fenzaiway
 * 时间格式化类
 */
public class MyFormatDate {
	private static SimpleDateFormat sdf = null;
	public static String getFullDate(Date date){
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 获取当格式化后的时间yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNowDate(){
		return getFullDate(new Date());
	}
	
	/**
	 * 判断当前时间与上次时间是不是相隔一个小时
	 * @param lastVisitTime
	 * @return
	 */
	public static boolean withinOneHour(Date lastVisitTime){
		
		//现在的时间
		Calendar now = Calendar.getInstance();

		//上次访问时间
		Calendar last = Calendar.getInstance();
		last.setTime(lastVisitTime);

		last.add(Calendar.HOUR_OF_DAY,1);

		if(last.before(now)){
			return false;
		}

		return true;
	}
}
