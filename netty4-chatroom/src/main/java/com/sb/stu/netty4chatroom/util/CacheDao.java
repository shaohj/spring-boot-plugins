package com.sb.stu.netty4chatroom.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 编  号：
 * 名  称：CacheDao
 * 描  述：
 * 完成日期：2018/9/17 20:54
 * @author：felix.shao
 */
public class CacheDao {

	private static final Map<String, String> userNamesMap = new ConcurrentHashMap();

	public static void saveString(String key,String value){
		userNamesMap.put(key, value);
	}

	public static String getString(String key){
		return userNamesMap.get(key);
	}
	
	public static void deleteString(String key){
		userNamesMap.remove(key);
	}
}
