package com.util.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * 时间处理工具类
 * 
 * @author pengm
 *
 */
public class DatetimeUtil {

	/**
	 * 字符串转日期
	 * 
	 * @param sDate yyyy—MM-dd
	 * @return
	 */
	private static Date strToDate( String sDate ) {
		Date date = null;
		try {
			if( sDate != null && !"".equals( sDate.trim() ) ) {
				SimpleDateFormat formatDate = new SimpleDateFormat( "yyyy-MM-dd" );
				String dateStr = sDate.trim();
				date = formatDate.parse( dateStr );
			}
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 格式：yyyy-MM-dd 异常返回null
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString( Date date ) {
		String dateString = null;
		try {
			if( date != null ) {
				SimpleDateFormat formatDate = new SimpleDateFormat( "yyyy-MM-dd" );
				dateString = formatDate.format( date );
			}
		}
		catch( Exception e ) {
			e.printStackTrace();
			return null;
		}
		return dateString;
	}

	/**
	 * 往前推100天
	 * 
	 * @param dateString yyyy-mm-dd
	 * @return yyyy-mm-dd
	 */
	public static String getDateHundredDayBefore( String dateString ) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime( strToDate( dateString ) );
		calendar.add( Calendar.DAY_OF_YEAR, -100 );
		String hundredDayBefore = dateToString( calendar.getTime() );
		return hundredDayBefore;
	}

	/**
	 * 往前推n天
	 * 
	 * @param dateString yyyy-mm-dd
	 * @return yyyy-mm-dd
	 */
	public static String getLastDayofNum( String dateString, int n ) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime( strToDate( dateString ) );
		calendar.add( Calendar.DAY_OF_YEAR, -n );
		String hundredDayBefore = dateToString( calendar.getTime() );
		return hundredDayBefore;
	}

	@Test
	public void testgetLastDayofNum() {
		String dateString = "2017-03-21";
		int dayNum = 10;
		System.out.println( getLastDayofNum( dateString, dayNum ) );
	}
}
