package com.util.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
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
	 * 获取两个日期之间差值
	 * 
	 * @param sstartDate yyyyMMdd
	 * @param sendDate yyyyMMdd
	 * @return 两个日期间相差天数
	 * @throws ParseException
	 */
	public static int getBetweenDays( String startDate, String endDate ) {

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd" );
		LocalDate startLocalDate = null;
		LocalDate endLocalDate = null;
		try {
			startLocalDate = new LocalDate( sdf.parse( startDate ) );
			endLocalDate = new LocalDate( sdf.parse( endDate ) );
		}
		catch( ParseException e ) {
			e.getMessage();
		}

		return Days.daysBetween( startLocalDate, endLocalDate ).getDays();
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

	/**
	 * 转换日期
	 * 
	 * @param Time yyyy-MM-dd or yyyyMMdd
	 * @return MMdd(4.1 5.12)
	 */
	public static String transferTime( String Time ) {
		Time = Time.replaceAll( "-", "" );
		String transfer = "";
		StringBuilder sBuilder = new StringBuilder();
		String month = Time.substring( 4, 6 );
		String day = Time.substring( 6 );
		if( month.startsWith( "0" ) ) {
			sBuilder.append( month.substring( 1 ) );
		}
		else {
			sBuilder.append( month );
		}
		sBuilder.append( "." );
		if( day.startsWith( "0" ) ) {
			sBuilder.append( day.substring( 1 ) );
		}
		else {
			sBuilder.append( day );
		}
		transfer = sBuilder.toString();

		return transfer;
	}

	/**
	 * 获取时间段内的日期列表
	 * 
	 * @param beginTime
	 * @param endTime
	 * @param format
	 * @return
	 */
	public static List<String> getLabelForDay( String beginTime, String endTime, String format ) {
		DateTime dateTimeStart = new DateTime( beginTime );
		DateTime dateTimeEnd = new DateTime( endTime );
		List<String> list = new ArrayList<>();
		while( !dateTimeStart.isAfter( dateTimeEnd ) ) {
			list.add( dateTimeStart.toString( format ) );
			dateTimeStart = dateTimeStart.plusDays( 1 );
		}
		return list;
	}

	/**
	 * 转换日期
	 * 
	 * @param time
	 * @return 00:00:00
	 */
	public static String formatTime( int time ) {
		String timeStr = "00:00:00";
		int hour = 0;
		int minute = 0;
		int second = 0;
		if( time <= 0 ) {
			return timeStr;
		}

		minute = time / 60;
		if( minute < 60 ) {
			second = time % 60;
			timeStr = "00" + ":" + unitFormat( minute ) + ":" + unitFormat( second );
		}
		else {
			hour = minute / 60;
			minute = minute % 60;
			second = time - hour * 3600 - minute * 60;
			timeStr = unitFormat( hour ) + ":" + unitFormat( minute ) + ":" + unitFormat( second );
		}

		return timeStr;
	}

	public static String unitFormat( int i ) {
		String retStr = null;
		if( i >= 0 && i < 10 )
			retStr = "0" + String.valueOf( i );
		else
			retStr = "" + i;
		return retStr;
	}

	/**
	 * 获取该日期的季度
	 * 
	 * @param today
	 * @return
	 */
	public static int getCurQuarter( String today ) {
		int curQuarter;
		int month = getMonthOfDate( today );
		switch( month ) {
			case 1:
			case 2:
			case 3:
				curQuarter = 1;
				break;

			case 4:
			case 5:
			case 6:
				curQuarter = 2;
				break;

			case 7:
			case 8:
			case 9:
				curQuarter = 3;
				break;

			case 10:
			case 11:
			case 12:
				curQuarter = 4;
				break;

			default:
				curQuarter = 1;
				break;
		}
		return curQuarter;
	}

	/**
	 * 获取改日期的月份
	 * 
	 * @param dateString
	 * @return
	 */
	public static int getMonthOfDate( String dateString ) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyyMMdd" );
		LocalDate localDate = formatter.parseLocalDate( dateString.replace( "-", "" ) );
		int month = localDate.getMonthOfYear();
		return month;
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
