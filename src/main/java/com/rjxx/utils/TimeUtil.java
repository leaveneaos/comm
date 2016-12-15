package com.rjxx.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间操作相关的类
 * @author k
 */
public class TimeUtil {
	/**
	 * 根据格式，获取当前时间
	 * @param format 时间格式，默认为"yyyy-MM-dd"
	 * 	"2003-02-01"标示为"yyyy-MM-dd",
	 *  24小时："23:12:21"-->"HH:mm:ss"
	 *  12小时：设为"hh:mm:ss"
	 * @return 当前时间字符串
	 */
	public static String getCurrentDate(String format){
		return formatDate(Calendar.getInstance().getTime(),format);
	}
	/**
	 * 格式化时间
	 * @param date 时间对象
	 * @param format 时间格式，默认为"yyyy-MM-dd"
	 * @return 格式化后的时间
	 */
	public static String formatDate(Date date,String format){
		if (StringUtils.isEmpty(format))
			format = GlobsAttributes.TIME_FORMAT_TEN;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * 得到六位当前月份
	 * @return
	 */
	public static String getSysDateString() {
		return String.valueOf(getCurrentDate(GlobsAttributes.TIME_FORMAT_FIVE));
	}	
	/**
	 * 日期的字符串形式转成Date
	 * @param date String 默认格式为yyyy-MM-dd
	 * @param  format 格式，null则表示默认
	 * @return Date 日期
	 */
	public static Date getSysDateInDate(String date,String format) {
		if (format == null)
			format = GlobsAttributes.TIME_FORMAT_TEN;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date sysdate = null;
		if (date == null) {
			return null;
		}
		try {
			sysdate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sysdate;
	}

}
