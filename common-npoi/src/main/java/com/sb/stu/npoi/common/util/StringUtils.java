package com.sb.stu.npoi.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static String getStringSuffix(String str) {
		if (null == str || "".equals(str)) {
			return "";
		}
		String suffix = "";

		int idx = str.lastIndexOf(".");
		if (idx > 0) {
			suffix = str.substring(idx + 1);
		}

		return suffix;
	}

	public static String renderString(String content, Map<String, Object> map){
		Set<Map.Entry<String, Object>> sets = map.entrySet();
		for(Map.Entry<String, Object> entry : sets) {
			String regex = "\\$\\{" + entry.getKey() + "\\}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(content);
			content = matcher.replaceAll(entry.getValue().toString());
		}
		return content;
	}

	public static void main(String[] args) {
		System.out.println(getStringSuffix("a.txtx"));

		String content = "hello ${name}, 1 2 3 4 5 ${six} 7, again ${name}. ";
		Map<String, Object> map = new HashMap<>();
		map.put("name", "java");
		map.put("six", "6");
		content = StringUtils.renderString(content, map);
		System.out.println(content);
	}

}
