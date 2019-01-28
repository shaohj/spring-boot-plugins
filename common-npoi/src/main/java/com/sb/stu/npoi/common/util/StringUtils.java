package com.sb.stu.npoi.common.util;

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

	public static void main(String[] args) {
		System.out.println(getStringSuffix("a.txtx"));
	}

}
