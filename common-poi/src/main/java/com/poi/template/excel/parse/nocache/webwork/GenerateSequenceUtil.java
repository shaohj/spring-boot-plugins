package com.poi.template.excel.parse.nocache.webwork;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * code序列化工具类
 * @author ShaoHj
 *
 */
public class GenerateSequenceUtil {

    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

    private static final Format ID_DATE_FORMAT = new SimpleDateFormat("MMddHHmmssS");

    private static final NumberFormat NUMBER_FORMAT = new DecimalFormat("0000");

    private static int id_seq = 0;
    
    private static int day_seq = 0;

    private static final int MAX = 9999;

    /**
     * 时间格式生成序列
     * @return String
     */
    public static synchronized String generateSequenceNo() {
        Calendar rightNow = Calendar.getInstance();

        StringBuffer sb = new StringBuffer();

        ID_DATE_FORMAT.format(rightNow.getTime(), sb, HELPER_POSITION);

        NUMBER_FORMAT.format(id_seq, sb, HELPER_POSITION);

        if (id_seq == MAX) {
            id_seq = 0;
        } else {
            id_seq++;
        }

        return sb.toString();
    }
    
    /**
     * 年月日_时间格式生成序列
     * @return String
     */
    public static String generateYyyyMmDd() {
    	Calendar rightNow = Calendar.getInstance();
    	
    	StringBuffer sb = new StringBuffer();
    	
    	new SimpleDateFormat("YYYYMMdd").format(rightNow.getTime(), sb, HELPER_POSITION);
    	
    	return sb.toString();
    }
    
    /**
     * 当日具体时间格式生成序列
     * @return String
     */
    public static synchronized String generateHhMmSsS() {
    	Calendar rightNow = Calendar.getInstance();
    	
    	StringBuffer sb = new StringBuffer();
    	
    	new SimpleDateFormat("HHmmssS").format(rightNow.getTime(), sb, HELPER_POSITION);
    	
    	NUMBER_FORMAT.format(day_seq, sb, HELPER_POSITION);
    	
    	if (day_seq == MAX) {
    		day_seq = 0;
    	} else {
    		day_seq++;
    	}
    	
    	return sb.toString();
    }
    
    public static void main(String[] args) {
//    	while(true){
    		System.out.println(generateSequenceNo());
    		System.out.println(generateSequenceNo());
    		System.out.println(generateSequenceNo());
//    	}
    	
    	System.out.println(generateYyyyMmDd());
    	System.out.println(generateHhMmSsS());
    	
    	System.out.println(generateSequenceNo());
    	
    	System.out.println(generateHhMmSsS());
	}
}
