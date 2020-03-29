package com.sprboot.plugin.resttempex.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 编  号：
 * 名  称：DateUtil
 * 描  述：
 * 完成日期：2020/3/29 22:25
 * @author：felix.shao
 */
public final class DateUtil {
	
	private static Log log = LogFactory.getLog(DateUtil.class);
	
	/**
	 * 私有的构造方法可以禁止此类实例化
	 */
	private DateUtil() {}
	
	/**
	 * language 参数的可选值--中文
	 */
	public static final String _zh_cn = "zh_cn";
	
	/**
	 * language 参数的可选值--英文
	 */
	public static final String _en_us = "en_us";
	
	/**
	 * 日期格式化格式定义, 中文默认<br/>
	 * "yyyy-MM-dd HH:mm:ss.SSS"
	 */
	public static final String date_format_zh_cn_default = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/**
	 * 日期格式化格式定义, 中文常用<br/>
	 * "yyyy-MM-dd HH:mm:ss"
	 */
	public static final String date_format_zh_cn_common = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式化格式定义, 中文无分隔<br/>
	 * "yyyyMMddHHmmss"
	 */
	public static final String date_format_zh_cn_nosplit = "yyyyMMddHHmmss";
	
	/**
	 * 日期格式化格式定义, 中文两位数表示年(仅日期) - 分隔<br/>
	 * "yy-MM-dd"
	 */
	public static final String date_format_zh_cn_twodigityear_onlydate_ = "yy-MM-dd";
	
	/**
	 * 日期格式化格式定义, 中文仅日期 - 分隔<br/>
	 * "yyyy-MM-dd"
	 */
	public static final String date_format_zh_cn_onlydate_ = "yyyy-MM-dd";
	
	/**
	 * 日期格式化格式定义, 中文两位数表示年(仅日期) / 分隔<br/>
	 * "yy/MM/dd"
	 */
	public static final String date_format_zh_cn_twodigityear_onlydate = "yy/MM/dd";
	
	/**
	 * 日期格式化格式定义, 中文仅日期 / 分隔<br/>
	 * "yyyy/MM/dd"
	 */
	public static final String date_format_zh_cn_onlydate = "yyyy/MM/dd";
	
	/**
	 * 日期格式化格式定义, 中文仅时间<br/>
	 * "HH:mm:ss"
	 */
	public static final String date_format_zh_cn_onlytime = "HH:mm:ss";
	
	/**
	 * 日期格式化格式定义数组
	 */
	public static String[] date_format_array = {
		date_format_zh_cn_default, date_format_zh_cn_common, 
		date_format_zh_cn_nosplit, date_format_zh_cn_twodigityear_onlydate_, 
		date_format_zh_cn_onlydate_, date_format_zh_cn_twodigityear_onlydate, 
		date_format_zh_cn_onlydate, date_format_zh_cn_onlytime };
	
	/**
	 * 一天的开始时间的字符串<br/>
	 * " 00:00:00.000"
	 */
	private static String day_starttime_str = " 00:00:00.000";

	/**
	 * 一天的结束时间的字符串<br/>
	 * " 23:59:59.999"
	 */
	private static String day_endtime_str = " 23:59:59.999";
	
	/**
	 * 日期格式化默认对象, 使用中文默认格式进行格式化<br/>
	 * "yyyyMMdd HHmmss SSS"
	 */
	private static DateFormat defaultDateFormat = new SimpleDateFormat(date_format_zh_cn_default);
	
	/**
	 * 获取一个公共的日历对象,在获取或转换时间时使用
	 */
	private static Calendar calendar = Calendar.getInstance();
	
	/**
	 * 中文汉字与阿拉伯数字对照表<br/>
	 * 主要用来将汉字数字字符串解析到阿拉伯数字的年月日时分秒时间字符串(1位数字要左补0)
	 */
	private static Map<String, String> zh_cn_num = new HashMap<String, String>(60);
	
	static {
		zh_cn_num.put("一", "01");
		zh_cn_num.put("二", "02");zh_cn_num.put("两", "02");
		zh_cn_num.put("三", "03");
		zh_cn_num.put("四", "04");
		zh_cn_num.put("五", "05");
		zh_cn_num.put("六", "06");
		zh_cn_num.put("七", "07");
		zh_cn_num.put("八", "08");
		zh_cn_num.put("九", "09");
		
		zh_cn_num.put("十", "10");zh_cn_num.put("一零", "10");
		zh_cn_num.put("十一", "11");zh_cn_num.put("一一", "11");
		zh_cn_num.put("十二", "12");zh_cn_num.put("一二", "12");
		zh_cn_num.put("十三", "13");zh_cn_num.put("一三", "13");
		zh_cn_num.put("十四", "14");zh_cn_num.put("一四", "14");
		zh_cn_num.put("十五", "15");zh_cn_num.put("一五", "15");
		zh_cn_num.put("十六", "16");zh_cn_num.put("一六", "16");
		zh_cn_num.put("十七", "17");zh_cn_num.put("一七", "17");
		zh_cn_num.put("十八", "18");zh_cn_num.put("一八", "18");
		zh_cn_num.put("十九", "19");zh_cn_num.put("一九", "19");
		
		zh_cn_num.put("二十", "20");zh_cn_num.put("二零", "20");
		zh_cn_num.put("二十一", "21");zh_cn_num.put("二一", "21");
		zh_cn_num.put("二十二", "22");zh_cn_num.put("二二", "22");
		zh_cn_num.put("二十三", "23");zh_cn_num.put("二三", "23");
		zh_cn_num.put("二十四", "24");zh_cn_num.put("二四", "24");
		zh_cn_num.put("二十五", "25");zh_cn_num.put("二五", "25");
		zh_cn_num.put("二十六", "26");zh_cn_num.put("二六", "26");
		zh_cn_num.put("二十七", "27");zh_cn_num.put("二七", "27");
		zh_cn_num.put("二十八", "28");zh_cn_num.put("二八", "28");
		zh_cn_num.put("二十九", "29");zh_cn_num.put("二九", "29");
		
		zh_cn_num.put("三十", "30");zh_cn_num.put("三零", "30");
		zh_cn_num.put("三十一", "31");zh_cn_num.put("三一", "31");
		zh_cn_num.put("三十二", "32");zh_cn_num.put("三二", "32");
		zh_cn_num.put("三十三", "33");zh_cn_num.put("三三", "33");
		zh_cn_num.put("三十四", "34");zh_cn_num.put("三四", "34");
		zh_cn_num.put("三十五", "35");zh_cn_num.put("三五", "35");
		zh_cn_num.put("三十六", "36");zh_cn_num.put("三六", "36");
		zh_cn_num.put("三十七", "37");zh_cn_num.put("三七", "37");
		zh_cn_num.put("三十八", "38");zh_cn_num.put("三八", "38");
		zh_cn_num.put("三十九", "39");zh_cn_num.put("三九", "39");
		
		zh_cn_num.put("四十", "40");zh_cn_num.put("四零", "40");
		zh_cn_num.put("四十一", "41");zh_cn_num.put("四一", "41");
		zh_cn_num.put("四十二", "42");zh_cn_num.put("四二", "42");
		zh_cn_num.put("四十三", "43");zh_cn_num.put("四三", "43");
		zh_cn_num.put("四十四", "44");zh_cn_num.put("四四", "44");
		zh_cn_num.put("四十五", "45");zh_cn_num.put("四五", "45");
		zh_cn_num.put("四十六", "46");zh_cn_num.put("四六", "46");
		zh_cn_num.put("四十七", "47");zh_cn_num.put("四七", "47");
		zh_cn_num.put("四十八", "48");zh_cn_num.put("四八", "48");
		zh_cn_num.put("四十九", "49");zh_cn_num.put("四九", "49");
		
		zh_cn_num.put("五十", "50");zh_cn_num.put("五零", "50");
		zh_cn_num.put("五十一", "51");zh_cn_num.put("五一", "51");
		zh_cn_num.put("五十二", "52");zh_cn_num.put("五二", "52");
		zh_cn_num.put("五十三", "53");zh_cn_num.put("五三", "53");
		zh_cn_num.put("五十四", "54");zh_cn_num.put("五四", "54");
		zh_cn_num.put("五十五", "55");zh_cn_num.put("五五", "55");
		zh_cn_num.put("五十六", "56");zh_cn_num.put("五六", "56");
		zh_cn_num.put("五十七", "57");zh_cn_num.put("五七", "57");
		zh_cn_num.put("五十八", "58");zh_cn_num.put("五八", "58");
		zh_cn_num.put("五十九", "59");zh_cn_num.put("五九", "59");
		
		zh_cn_num.put("六零", "60");
		zh_cn_num.put("六一", "61");
		zh_cn_num.put("六二", "62");
		zh_cn_num.put("六三", "63");
		zh_cn_num.put("六四", "64");
		zh_cn_num.put("六五", "65");
		zh_cn_num.put("六六", "66");
		zh_cn_num.put("六七", "67");
		zh_cn_num.put("六八", "68");
		zh_cn_num.put("六九", "69");
		
		zh_cn_num.put("七零", "70");
		zh_cn_num.put("七一", "71");
		zh_cn_num.put("七二", "72");
		zh_cn_num.put("七三", "73");
		zh_cn_num.put("七四", "74");
		zh_cn_num.put("七五", "75");
		zh_cn_num.put("七六", "76");
		zh_cn_num.put("七七", "77");
		zh_cn_num.put("七八", "78");
		zh_cn_num.put("七九", "79");
		
		zh_cn_num.put("八零", "80");
		zh_cn_num.put("八一", "81");
		zh_cn_num.put("八二", "82");
		zh_cn_num.put("八三", "83");
		zh_cn_num.put("八四", "84");
		zh_cn_num.put("八五", "85");
		zh_cn_num.put("八六", "86");
		zh_cn_num.put("八七", "87");
		zh_cn_num.put("八八", "88");
		zh_cn_num.put("八九", "89");
		
		zh_cn_num.put("九零", "90");
		zh_cn_num.put("九一", "91");
		zh_cn_num.put("九二", "92");
		zh_cn_num.put("九三", "93");
		zh_cn_num.put("九四", "94");
		zh_cn_num.put("九五", "95");
		zh_cn_num.put("九六", "96");
		zh_cn_num.put("九七", "97");
		zh_cn_num.put("九八", "98");
		zh_cn_num.put("九九", "99");
	}
	
	
	/*-----------1.转化方法---------------*/
	
	/**
	 * 日期转为字符串, 使用默认格式化对象<br/>
	 * "yyyyMMdd HHmmss SSS"
	 * @param date 日期对象
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, defaultDateFormat);
	}
	
	/**
	 * 日期转为字符串, 使用传入的格式化字符串的格式
	 * @param date 日期对象
	 * @param formatStr 传入的格式化字符串
	 * @return
	 */
	public static String dateToString(Date date, String formatStr) {
		if(formatStr == null  || formatStr == null || formatStr.isEmpty()) {
			return null;
		}
		return dateToString(date, new SimpleDateFormat(formatStr));
	}
	
	/**
	 * 日期转为字符串, 使用传入的日期格式化对象进行格式化
	 * @param date 日期对象
	 * @param dateFormat 日期格式化对象
	 * @return
	 */
	public static String dateToString(Date date, DateFormat dateFormat) {
		if(date == null || dateFormat == null) {
			return null;
		}
		return dateFormat.format(date);
	}
	
	/**
	 * 字符串转为日期, 使用默认格式化对象解析<br/>
	 * "yyyyMMdd HHmmss SSS"
	 * @param dateStr 时间字符串
	 * @return
	 */
	public static Date stringToDate(String dateStr) {
		Date retdate = null;
		for(int i=0; i<date_format_array.length; i++) {
			retdate = stringToDate(dateStr, date_format_array[i]);
			if(retdate != null) {
				break;
			}
		}
		return retdate;
	}
	
	/**
	 * 字符串转为日期, 使用传入的格式化字符串的格式
	 * @param dateStr 时间字符串
	 * @param formatStr 格式化字符串的格式
	 * @return
	 */
	public static Date stringToDate(String dateStr, String formatStr) {
		if(formatStr == null  || "".equals(formatStr)) {
			return null;
		}
		return stringToDate(dateStr, new SimpleDateFormat(formatStr));
	}
	
	/**
	 * 字符串转为日期, 使用传入的日期格式化对象进行格式化
	 * @param dateStr 时间字符串
	 * @param dateFormat 日期格式化对象
	 * @return
	 */
	public static Date stringToDate(String dateStr, DateFormat dateFormat) {
		if(dateStr == null || "".equals(dateStr) || dateFormat == null) {
			return null;
		}
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			log.info("日期字符串错误, 或无法与解析格式对应");
		}
		return date;
	}
	/*-----------1.转化方法 end------------*/
	
	
	/*-----------2.获取方法---------------*/
	
	/**
	 * 获取一天起始时间的Date对象
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getDayStartDatetime(Date day) {
		if(day == null) {
			day = new Date();
		}
		String datestr = dateToString(day, date_format_zh_cn_onlydate_);
		datestr += day_starttime_str;
		return stringToDate(datestr, date_format_zh_cn_default);
	}
	
	/**
	 * 获取一天结束时间的Date对象
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getDayEndDatetime(Date day) {
		if(day == null) {
			day = new Date();
		}
		String datestr = dateToString(day, date_format_zh_cn_onlydate_);
		datestr += day_endtime_str;
		return stringToDate(datestr, date_format_zh_cn_default);
	}
	
	/**
	 * 获取某一天在这一周是第几天
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getDayOfWeek(Date day) {
		return getDateField(day, Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取某一天是星期几
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static String getDayOfWeekString(Date day) {
		if(day == null) {
			day = new Date();
		}
		return getDayOfWeekString(day, _zh_cn);
	}
	
	/**
	 * 获取某一天是星期几
	 * @param day 某一天的Date对象
	 * @param language 返回语言类型
	 * @return
	 */
	public static String getDayOfWeekString(Date day, String language) {
		if(day == null) {
			day = new Date();
		}
		String[] _zh_cn_weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		String[] _en_us_weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		Integer dayofweek = getDayOfWeek(day);
		return _en_us.equals(language) ? _en_us_weeks[dayofweek-1] : _zh_cn_weeks[dayofweek-1];
	}
	
	/**
	 * 获得某一天所在的周的开始的日期(第一天的日期即星期日)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getWeekStartDate(Date day) {
		if(day == null) {
			day = new Date();
		}
		int number = getDayOfWeek(day);
		return dayAdd(day, - (number - 1));
	}
	
	/**
	 * 获得某一天所在的周的开始的日期时间
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getWeekStartDateTime(Date day) {
		return getDayStartDatetime(getWeekStartDate(day));
	}
	
	/**
	 * 获得某一天所在的周的结束的日期(最后一天的日期即星期六日)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getWeekEndDate(Date day) {
		if(day == null) {
			day = new Date();
		}
		int number = getDayOfWeek(day);
		return dayAdd(day, 7 - number);
	}
	
	/**
	 * 获得某一天所在的周的结束的日期时间
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getWeekEndDateTime(Date day) {
		return getDayEndDatetime(getWeekEndDate(day));
	}
	
	/**
	 * 获取某一天在这一月是第几天
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getDayOfMonth(Date day) {
		return getDateField(day, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取某一天所在的月有多少天
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getMonthHaveDays(Date day) {
		if(day == null) {
			day = new Date();
		}
		// 设置日历对象到当前时间
		calendar.setTime(day);
		// 按照当前时间计算一个月以后的时间
		calendar.add(Calendar.MONTH, 1);
		// 再计算下个月的第一天的时间
		Date nextMonthFirstDate = getMonthStartDate(calendar.getTime());
		// 然后将日历对象设置到下月第一天的时间
		calendar.setTime(nextMonthFirstDate);
		// 再计算出下月第一天的前一天，也就是本月最后一天
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		// 直接返回本月最后一天在这个月是第几天，则可直接得出本月总共有多少天
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获得某一天所在的月的开始的日期(即该月一号)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getMonthStartDate(Date day) {
		if(day == null) {
			day = new Date();
		}
		return dayAdd(day, - (getDayOfMonth(day) - 1));
	}
	
	/**
	 * 获得某一天所在的月的开始的日期时间
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getMonthStartDateTime(Date day) {
		return getDayStartDatetime(getMonthStartDate(day));
	}
	
	/**
	 * 获得某一天所在的月的结束的日期(该月最后一天即大月的31号小月的30号或2月的28或29号)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getMonthEndDate(Date day) {
		if(day == null) {
			day = new Date();
		}
		return dayAdd(day, getMonthHaveDays(day) - getDayOfMonth(day));
	}
	
	/**
	 * 获得某一天所在的月的结束的日期时间
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getMonthEndDateTime(Date day) {
		return getDayEndDatetime(getMonthEndDate(day));
	}
	
	/**
	 * 获取某一天在这一年是第几天
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getDayOfYear(Date day) {
		return getDateField(day, Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * 获取某一天所在的月有多少天
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getYearHaveDays(Date day) {
		return isLeapYear(day) ? 366 : 365;
	}
	
	/**
	 * 获得某一天所在的年的开始的日期(即该年一月一号)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getYearStartDate(Date day) {
		if(day == null) {
			day = new Date();
		}
		return dayAdd(day, - (getDayOfYear(day) - 1));
	}
	
	/**
	 * 获得某一天所在的年的开始的日期时间
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getYearStartDateTime(Date day) {
		return getDayStartDatetime(getYearStartDate(day));
	}
	
	/**
	 * 获得某一天所在的年的结束的日期(即该年十二月三十一号)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getYearEndDate(Date day) {
		if(day == null) {
			day = new Date();
		}
		return dayAdd(day, getYearHaveDays(day) - getDayOfYear(day));
	}
	
	/**
	 * 获得某一天所在的年的开始的日期时间
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date getYearEndDateTime(Date day) {
		return getDayEndDatetime(getYearEndDate(day));
	}
	
	/**
	 * 获取某一天所在的年
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getYear(Date day) {
		return getDateField(day, Calendar.YEAR);
	}
	
	/**
	 * 获取某一天所在的月(内部已将原生Calendar提供的月份少的1补全)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getMonth(Date day) {
		// 月得到的数字比实际小1, 故在此+1
		return getDateField(day, Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取某一天所在的周在这一月中是第几个周(第几个7天,以一号为一周的第一天开始算,非日历显示)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getDayOfWeekInMonth(Date day) {
		return getDateField(day, Calendar.DAY_OF_WEEK_IN_MONTH);
	}
	
	/**
	 * 获取某一天所在的周在这一月中是第几周(日历显示)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getWeekOfMonth(Date day) {
		return getDateField(day, Calendar.WEEK_OF_MONTH);
	}
	
	/**
	 * 获取某一天所在的周在这一年中是第几周(日历显示)
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Integer getWeekOfYear(Date day) {
		return getDateField(day, Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取小时数,不区分上下午0-12
	 * @param day 某个时间的Date对象
	 * @return
	 */
	public static Integer getHour(Date day) {
		return getDateField(day, Calendar.HOUR);
	}

	/**
	 * 获取小时数,区分上下午0-24
	 * @param day 某个时间的Date对象
	 * @return
	 */
	public static Integer getHourOfDay(Date day) {
		return getDateField(day, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取分钟数
	 * @param day 某个时间的Date对象
	 * @return
	 */
	public static Integer getMinute(Date day) {
		return getDateField(day, Calendar.MINUTE);
	}

	/**
	 * 获取秒数
	 * @param day 某个时间的Date对象
	 * @return
	 */
	public static Integer getSecond(Date day) {
		return getDateField(day, Calendar.SECOND);
	}
	
	/**
	 * 获取某一天的日历属性
	 * @param day 某一天的Date对象
	 * @param field 字段属性, 使用Calendar调起其中的常量(例: Calendar.DAY_OF_WEEK_IN_MONTH)
	 * @return
	 */
	public static Integer getDateField(Date day, int field) {
		if(day == null) {
			day = new Date();
		}
		calendar.setTime(day);
		return calendar.get(field);
	}
	/*-----------2.获取方法 end------------*/
	
	
	/*-----------3.计算方法---------------*/
	
	/**
	 * 给定日期的明天
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date nextDay(Date day) {
		return dayAdd(day, 1);
	}
	
	/**
	 * 给定日期的昨天
	 * @param day 某一天的Date对象
	 * @return
	 */
	public static Date prevDay(Date day) {
		return dayAdd(day, -1);
	}
	
	/**
	 * 按日增加或递减计算给定日期
	 * @param day 某一天的Date对象
	 * @param number 几天前(负数 如-1)或几天后(正数 如2)
	 * @return
	 */
	public static Date dayAdd(Date day, int number) {
		return calendarAdd(day, Calendar.DAY_OF_MONTH, number);
	}
	
	/**
	 * 按月增加或递减计算给定日期
	 * @param day 某一天的Date对象
	 * @param number 几月前(负数 如-1)或几月后(正数 如2)
	 * @return
	 */
	public static Date monthAdd(Date day, int number) {
		return calendarAdd(day, Calendar.MONTH, number);
	}
	
	/**
	 * 按年增加或递减计算给定日期
	 * @param day 某一天的Date对象
	 * @param number 几年前(负数 如-1)或几年后(正数 如2)
	 * @return
	 */
	public static Date yearAdd(Date day, int number) {
		return calendarAdd(day, Calendar.YEAR, number);
	}
	
	/**
	 * 日历的计算方法，计算给定日期
	 * @param day 某一天的Date对象
	 * @param field 进行计算(增加或递减)的字段, 如: Calendar.DAY_OF_MONTH | Calendar.YEAR
	 * @param number 在进行计算的字段上进行增加或递减的数, 整数日期往后算, 负数日期往前算
	 * @return
	 */
	public static Date calendarAdd(Date day, int field, int number) {
		if(day == null) {
			day = new Date();
		}
		calendar.setTime(day);
		calendar.add(field, number);
		return calendar.getTime();
	}
	
	/**
	 * 计算两个日期之前的天数
	 * @param date1 第一个日期Date对象
	 * @param date2 第二个日期Date对象
	 * @return
	 */
	public static Integer betweenDays(Date date1, Date date2) {
		long ms_between = Math.abs(date1.getTime() - date2.getTime());
		long day_between = ms_between%(24 * 60 * 60 * 1000)==0 ? 
				ms_between / (24 * 60 * 60 * 1000) : 
				ms_between / (24 * 60 * 60 * 1000) + 1;
		return new Long(day_between).intValue();
	}
	/*-----------3.计算方法 end------------*/
	
	
	/*-----------4.判断方法---------------*/
	
	/**
	 * 判断某一天所在的年是不是闰年
	 * @param dayOfYear 某一天的Date对象
	 * @return
	 */
	public static Boolean isLeapYear(Date dayOfYear) {
		if(dayOfYear == null) {
			dayOfYear = new Date();
		}
		calendar.setTime(dayOfYear);
		int year = calendar.get(Calendar.YEAR);
		return isLeapYear(year);
	}
	
	/**
	 * 判断某一年是不是闰年
	 * @param year 年份
	 * @return
	 */
	public static Boolean isLeapYear(Integer year) {
		if(year == null) {
			calendar.setTime(new Date());
			year = calendar.get(Calendar.YEAR);
		}
		return ((year%4==0 && year%100!=0) || year%400==0) ? true : false;
	}
	/*-----------4.判断方法 end------------*/
	
	
	/*-----------5.分析方法---------------*/
	
	/**
	 * 解析中文日期时间字符串, 转化为Date对象<br/>
	 * 输入的中文字符串格式错误或者解析失败都会返回当前时间所表示的Date对象
	 * @param str 中文日期时间字符串
	 * @return
	 */
	public static Date parseZhCnDateimeString(String str) {
		// 判空
		if(str == null || str.isEmpty()) {
			return null;
		}
		// 去除所有空格回车制表符等空白字符
		String regex = "\\s";
		str = str.replaceAll(regex, "").trim();
		// 声明当前日期时间对象 和 今天的日期字符串
		Date now = new Date();
		String dateStr = dateToString(now, date_format_zh_cn_onlydate_);
		//String timeStr = dateToString(now, date_format_zh_cn_onlytime);
		String yearStr = ""+getYear(now);
		String monthStr = getMonth(now)<10 ? "0"+getMonth(now) : ""+getMonth(now);
		String dayStr = getDayOfMonth(now)<10 ? "0"+getDayOfMonth(now) : ""+getDayOfMonth(now);
		String hourStr = getHourOfDay(now)<10 ? "0"+getHourOfDay(now) : ""+getHourOfDay(now);
		String minuteStr = getMinute(now)<10 ? "0"+getMinute(now) : ""+getMinute(now);
		String secondStr = getSecond(now)<10 ? "0"+getSecond(now) : ""+getSecond(now);
		// ^()?()?()?()?(){1}(){1}()?$
		Pattern timerstrPattern = Pattern.compile("^([今明后去前]年)?([今明后昨前][天日]){1}$");
		Matcher timerstrMatcher = timerstrPattern.matcher(str);
		// 是否匹配当前正则表达式
		if(timerstrMatcher.matches()) {
			if(timerstrMatcher.group(1) != null) {
				// 如果该正则表达式匹配第一组不为空
				String matchGroup1 = timerstrMatcher.group(1);
				if("今年".equals(matchGroup1)) {
					// nothing
				} else if("明年".equals(matchGroup1)) {
					Date targetday = yearAdd(now, 1);
					yearStr = ""+getYear(targetday);
				} else if("后年".equals(matchGroup1)) {
					Date targetday = yearAdd(now, 2);
					yearStr = ""+getYear(targetday);
				} else if("去年".equals(matchGroup1)) {
					Date targetday = yearAdd(now, -1);
					yearStr = ""+getYear(targetday);
				} else if("前年".equals(matchGroup1)) {
					Date targetday = yearAdd(now, -2);
					yearStr = ""+getYear(targetday);
				}
			}
			// 该正则表达式匹配则第二组必然匹配
			String matchGroup2 = timerstrMatcher.group(2);
			if("今天".equals(matchGroup2) || "今日".equals(matchGroup2)) {
				// nothing
			} else if("明天".equals(matchGroup2) || "明日".equals(matchGroup2)) {
				Date targetday = dayAdd(now, 1);
				dayStr = getDayOfMonth(targetday)<10 ? "0"+getDayOfMonth(targetday) : ""+getDayOfMonth(targetday);
			} else if("后天".equals(matchGroup2) || "后日".equals(matchGroup2)) {
				Date targetday = dayAdd(now, 2);
				dayStr = getDayOfMonth(targetday)<10 ? "0"+getDayOfMonth(targetday) : ""+getDayOfMonth(targetday);
			} else if("昨天".equals(matchGroup2) || "昨日".equals(matchGroup2)) {
				Date targetday = dayAdd(now, -1);
				dayStr = getDayOfMonth(targetday)<10 ? "0"+getDayOfMonth(targetday) : ""+getDayOfMonth(targetday);
			} else if("前天".equals(matchGroup2) || "前日".equals(matchGroup2)) {
				Date targetday = dayAdd(now, -2);
				dayStr = getDayOfMonth(targetday)<10 ? "0"+getDayOfMonth(targetday) : ""+getDayOfMonth(targetday);
			}
			return stringToDate(yearStr + "-" + monthStr + "-" + dayStr, date_format_zh_cn_onlydate_);
		}
		// 普通的包含年月日的正则表达式，年的覆盖为十一世纪到四十世纪
		timerstrPattern = Pattern.compile("^([今明后去前]|[一二三][零一二三四五六七八九][零一二三四五六七八九][零一二三四五六七八九]|[零一二三四五六七八九][零一二三四五六七八九])?(年)?(十?[一二]|[三四五六七八九]){1}(月){1}(二?十?[一二三四五六七八九]?|三十|三十一){1}(日|号){1}$");
		timerstrMatcher = timerstrPattern.matcher(str);
		// 是否匹配当前正则表达式
		if(timerstrMatcher.matches()) {
			String matchGroup1 = timerstrMatcher.group(1);
			if(matchGroup1.length() == 1) {
				if("今".equals(matchGroup1)) {
					// nothing
				} else if("明".equals(matchGroup1)) {
					Date targetday = yearAdd(now, 1);
					yearStr = ""+getYear(targetday);
				} else if("后".equals(matchGroup1)) {
					Date targetday = yearAdd(now, 2);
					yearStr = ""+getYear(targetday);
				} else if("去".equals(matchGroup1)) {
					Date targetday = yearAdd(now, -1);
					yearStr = ""+getYear(targetday);
				} else if("前".equals(matchGroup1)) {
					Date targetday = yearAdd(now, -2);
					yearStr = ""+getYear(targetday);
				}
			} else if(matchGroup1.length() == 2) {
				// 年的数字字符串长度为2，则是只有最后两位的简写
				String yearStr01 = yearStr.substring(0, 2);
				String yearStr23 = yearStr.substring(2);
				Integer nowYearStr23Integer = new Integer(yearStr23);
				Integer inputYearStr23Integer = new Integer(zh_cn_num.get(matchGroup1));
				if((inputYearStr23Integer > nowYearStr23Integer) 
						&& (inputYearStr23Integer - nowYearStr23Integer > 10)) {
					yearStr01 = "" + (new Integer(yearStr01) - 1);
				}
				yearStr23 = zh_cn_num.get(matchGroup1);
				yearStr = yearStr01 + yearStr23;
			} else {
				// 否则年的字符串要分两部分解析
				String yearStr23 = matchGroup1.substring(matchGroup1.length() - 2);
				String yearStr01 = matchGroup1.replace(yearStr23, "");
				yearStr = zh_cn_num.get(yearStr01) + zh_cn_num.get(yearStr23);
			}
			String matchGroup3 = timerstrMatcher.group(3);
			monthStr = zh_cn_num.get(matchGroup3);
			String matchGroup5 = timerstrMatcher.group(5);
			dayStr = zh_cn_num.get(matchGroup5);
			return stringToDate(yearStr + "-" + monthStr + "-" + dayStr, date_format_zh_cn_onlydate_);
		}
		// 普通的包含小时整点、小时半 和 小时带刻
		timerstrPattern = Pattern.compile("^(二?十?[一二三四]?|十?[五六七八九]?|两){1}(点钟|点|时){1}([一二三两]?刻|整|半){1}$");
		timerstrMatcher = timerstrPattern.matcher(str);
		// 是否匹配当前正则表达式
		if(timerstrMatcher.matches()) {
			hourStr = zh_cn_num.get(timerstrMatcher.group(1));
			String matchGroup3 = timerstrMatcher.group(3);
			if(matchGroup3.endsWith("刻")) {
				minuteStr = "" + (new Integer(zh_cn_num.get(matchGroup3.substring(0,1))) * 15);
			} else if("整".equals(matchGroup3)) {
				minuteStr = "00";
			} else if("半".equals(matchGroup3)) {
				minuteStr = "30";
			}
			return stringToDate(dateStr + " " + hourStr + ":" + minuteStr + ":00", date_format_zh_cn_common);
		}
		// 普通的包含小时和分钟的正则表达式
		timerstrPattern = Pattern.compile("^(二?十?[一二三四]?|十?[五六七八九]?|两){1}(点钟|点|时){1}([二三四五]?十?[一二三四五六七八九]?|零?[一二三四五六七八九两]?){1}(分)?$");
		timerstrMatcher = timerstrPattern.matcher(str);
		// 是否匹配当前正则表达式
		if(timerstrMatcher.matches()) {
			hourStr = zh_cn_num.get(timerstrMatcher.group(1));
			String matchGroup3 = timerstrMatcher.group(3);
			if(matchGroup3.startsWith("零")) {
				matchGroup3 = matchGroup3.substring(1);
			}
			minuteStr = zh_cn_num.get(matchGroup3);
			return stringToDate(dateStr + " " + hourStr + ":" + minuteStr + ":00", date_format_zh_cn_common);
		}
		// 普通的包含小时、分钟和秒钟的正则表达式
		timerstrPattern = Pattern.compile("^(二?十?[一二三四]?|十?[五六七八九]?|两){1}(点钟|点|时){1}([二三四五]?十?[一二三四五六七八九]?|零?[一二三四五六七八九两]?){1}(分){1}([二三四五]?十?[一二三四五六七八九]?|零?[一二三四五六七八九两]?){1}(秒){1}$");
		timerstrMatcher = timerstrPattern.matcher(str);
		// 是否匹配当前正则表达式
		if(timerstrMatcher.matches()) {
			hourStr = zh_cn_num.get(timerstrMatcher.group(1));
			String matchGroup3 = timerstrMatcher.group(3);
			if(matchGroup3.startsWith("零")) {
				matchGroup3 = matchGroup3.substring(1);
			}
			minuteStr = zh_cn_num.get(matchGroup3);
			String matchGroup5 = timerstrMatcher.group(5);
			if(matchGroup5.startsWith("零")) {
				matchGroup5 = matchGroup5.substring(1);
			}
			secondStr = zh_cn_num.get(matchGroup5);
			return stringToDate(dateStr + " " + hourStr + ":" + minuteStr + ":" + secondStr, date_format_zh_cn_common);
		}
		// 普通的包含年月日时分秒的正则表达式，年的覆盖为十一世纪到四十世纪
		timerstrPattern = Pattern.compile("^([今明后去前]|[一二三][零一二三四五六七八九][零一二三四五六七八九][零一二三四五六七八九]|[零一二三四五六七八九][零一二三四五六七八九])?(年)?(十?[一二]|[三四五六七八九]){1}(月){1}(二?十?[一二三四五六七八九]?|三十|三十一){1}(日|号){1}(二?十?[一二三四]?|十?[五六七八九]?|两){1}(点钟|点|时){1}([二三四五]?十?[一二三四五六七八九]?|零?[一二三四五六七八九两]?){1}(分){1}([二三四五]?十?[一二三四五六七八九]?|零?[一二三四五六七八九两]?){1}(秒){1}$");
		timerstrMatcher = timerstrPattern.matcher(str);
		// 是否匹配当前正则表达式
		if(timerstrMatcher.matches()) {
			String matchGroup1 = timerstrMatcher.group(1);
			if(matchGroup1.length() == 1) {
				if("今".equals(matchGroup1)) {
					// nothing
				} else if("明".equals(matchGroup1)) {
					Date targetday = yearAdd(now, 1);
					yearStr = ""+getYear(targetday);
				} else if("后".equals(matchGroup1)) {
					Date targetday = yearAdd(now, 2);
					yearStr = ""+getYear(targetday);
				} else if("去".equals(matchGroup1)) {
					Date targetday = yearAdd(now, -1);
					yearStr = ""+getYear(targetday);
				} else if("前".equals(matchGroup1)) {
					Date targetday = yearAdd(now, -2);
					yearStr = ""+getYear(targetday);
				}
			} else if(matchGroup1.length() == 2) {
				// 年的数字字符串长度为2，则是只有最后两位的简写
				String yearStr01 = yearStr.substring(0, 2);
				String yearStr23 = yearStr.substring(2);
				Integer nowYearStr23Integer = new Integer(yearStr23);
				Integer inputYearStr23Integer = new Integer(zh_cn_num.get(matchGroup1));
				if((inputYearStr23Integer > nowYearStr23Integer) 
						&& (inputYearStr23Integer - nowYearStr23Integer > 10)) {
					yearStr01 = "" + (new Integer(yearStr01) - 1);
				}
				yearStr23 = zh_cn_num.get(matchGroup1);
				yearStr = yearStr01 + yearStr23;
			} else {
				// 否则年的字符串要分两部分解析
				String yearStr23 = matchGroup1.substring(matchGroup1.length() - 2);
				String yearStr01 = matchGroup1.replace(yearStr23, "");
				yearStr = zh_cn_num.get(yearStr01) + zh_cn_num.get(yearStr23);
			}
			monthStr = zh_cn_num.get(timerstrMatcher.group(3));
			dayStr = zh_cn_num.get(timerstrMatcher.group(5));
			hourStr = zh_cn_num.get(timerstrMatcher.group(7));
			String matchGroup9 = timerstrMatcher.group(9);
			if(matchGroup9.startsWith("零")) {
				matchGroup9 = matchGroup9.substring(1);
			}
			minuteStr = zh_cn_num.get(matchGroup9);
			String matchGroup11 = timerstrMatcher.group(11);
			if(matchGroup11.startsWith("零")) {
				matchGroup11 = matchGroup11.substring(1);
			}
			secondStr = zh_cn_num.get(matchGroup11);
			return stringToDate(yearStr + "-" + monthStr + "-" + dayStr + " " 
					+ hourStr + ":" + minuteStr + ":" + secondStr, date_format_zh_cn_common);
		}
		return now;
	}
	/*-----------5.分析方法 end------------*/
}
