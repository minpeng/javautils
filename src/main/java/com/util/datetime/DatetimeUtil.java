package com.util.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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

	/**
	 * 获取该月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastDayOfMonth( String date ) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyyMMdd" );
		DateTime dateTime = DateTime.parse( date.replaceAll( "-", "" ), formatter ).dayOfMonth().withMaximumValue();
		return dateTime.toString( formatter );

	}

	/**
	 * 获取周一
	 * 
	 * @param date yyyy-MM-dd 或者 yyyyMMdd
	 * @return
	 */
	public static String getMondayOfWeek( String date ) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyyMMdd" );
		DateTime dateTime = DateTime.parse( date.replaceAll( "-", "" ), formatter ).dayOfWeek().withMinimumValue();
		return dateTime.toString( formatter );

	}


	/**
	 * 获取周日
	 * 
	 * @param date yyyy-MM-dd 或者 yyyyMMdd
	 * @return
	 */
	public static String getSundayOfWeek( String date ) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyyMMdd" );
		DateTime dateTime = DateTime.parse( date.replaceAll( "-", "" ), formatter ).dayOfWeek().withMaximumValue();
		return dateTime.toString( formatter );

	}

	@Test
	public void testGetSundayOfWeek() {
		String dateString = "20170206";
		System.out.println( getSundayOfWeek( dateString ) );
		System.out.println( getLastDayOfMonth( dateString ) );
	}
	@Test
	public void testgetLastDayofNum() {
		String dateString = "2017-03-21";
		int dayNum = 10;
		System.out.println( getLastDayofNum( dateString, dayNum ) );
	}
}
