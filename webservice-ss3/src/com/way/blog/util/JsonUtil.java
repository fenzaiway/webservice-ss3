package com.way.blog.util;

import com.google.gson.Gson;

public class JsonUtil {
	
	/**
	 * 根据传入的对象生成json格式数据
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
}
