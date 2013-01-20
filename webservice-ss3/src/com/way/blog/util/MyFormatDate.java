package com.way.blog.util;

import java.text.SimpleDateFormat;
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
	
	public static String getNowDate(){
		return getFullDate(new Date());
	}
}
