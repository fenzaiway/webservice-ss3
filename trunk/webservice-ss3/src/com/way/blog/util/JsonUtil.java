package com.way.blog.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	
	
	/**
	 * 根据传入的对象生成json格式数据
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		Gson gson = new Gson();;
		return gson.toJson(obj);
	}
	
	public static String returnJsonByObjectOfExpose(Object obj){
		Gson gson = new Gson();;
		String jsonString;
		GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();//只转化添加注解了的属性
		gson = builder.create();
		jsonString = gson.toJson(obj);
		return jsonString;
	}
	
}
