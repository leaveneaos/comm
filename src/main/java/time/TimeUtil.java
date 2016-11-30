package time;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
/**
 * 通用时间类
 *
 */
public class TimeUtil {	
	/**
	 * 获得java.sql.Timestamp格式的日期
	 * @return
	 */
	public static Timestamp getNowDate() {
		return new Timestamp(getSysDate().getTime());
		
	}
	
	/**
	 * 日期的字符串形式转成Timestamp
	 * @param date String 默认格式为yyyyMMddHHmmss
	 * @param  format 格式，null则表示默认
	 * @return Date 日期
	 */
	public static Timestamp getSysDateInDate(String date,String format) {
		if (format == null)
			format = GlobsAttributes.TIME_FORMAT_ONE;
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
		return new Timestamp(sysdate.getTime());
	}
	/**
	 * 获取当前时间
	 * @return 时间对象
	 */
	public static Date getSysDate(){	
		return Calendar.getInstance().getTime();
	}
	/**
	 * 根据格式，获取当前时间
	 * @param format 时间格式，默认为"yyyy-MM-dd"
	 * 	"2003-02-01"标示为"yyyy-MM-dd",
	 *  24小时："23:12:21"-->"HH:mm:ss"
	 *  12小时：设为"hh:mm:ss"
	 * @return 当前时间字符串
	 */
	public static String getCurrentDate(String format){
		return formatDate(getSysDate(),format);
	}
	/**
	 * 格式化时间
	 * @param date 时间对象
	 * @param format 时间格式，默认为"yyyy-MM-dd"
	 * @return 格式化后的时间
	 */
	public static String formatDate(Date date,String format){
		if (StringUtils.isEmpty(format))
			format = GlobsAttributes.TIME_FORMAT_TWO;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}	
	/**
	 * 日期的字符串形式转成Date
	 * @param date String 默认格式为yyyy-MM-dd
	 * @param  format 格式，null则表示默认
	 * @return Date 日期
	 */
	public static Date getStringInDate(String date,String format) {
		if (format == null)
			format = GlobsAttributes.TIME_FORMAT_TWO;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date1 = null;
		if (date == null) {
			return null;
		}
		try {
			date1 = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}
	
	/**
	 * 得到某天N天后日期
	 * @param someDay yyyyMMdd数字型日期
	 * @param days N天
	 * @return  yyyyMMdd数字型日期
	 */
	@SuppressWarnings("deprecation")
	public static String getAfterDays(String someDay,int days) {
		Date d = getSysDateInDate(someDay,GlobsAttributes.TIME_FORMAT_TWO);
		d.setDate(d.getDate() + days);
		return formatDate(d, null);
	}

	/**
	 * 得到某天N天前日期
	 * @param someDay yyyyMMdd数字型日期
	 * @param days N天
	 * @return  yyyyMMdd数字型日期
	 */
	@SuppressWarnings("deprecation")
	public static String getBeforeDays(String someDay,int days) {
		Date d = getSysDateInDate(someDay,GlobsAttributes.TIME_FORMAT_TWO);
		d.setDate(d.getDate()-days);
		return formatDate(d,null);
	}
	public static void main(String[] args){
		Date d=getStringInDate("2015-12-11",null);
		d.setDate(d.getDate()-1);
		System.out.println(formatDate(d, null));
	}
}
