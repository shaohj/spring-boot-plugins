package com.sb.stu.netty4chatroom.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service 
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
