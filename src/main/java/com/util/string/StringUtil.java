package com.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * 
 * @author pengm
 *
 */
public class StringUtil {

	/**
	 * 验证字符串是否为空
	 * 
	 * @param param
	 * @return
	 */
	public static boolean empty( String param ) {
		return param == null || param.trim().length() < 1;
	}

	/**
	 * 转义正则特殊字符 （$()*+.[]?\^{},|）
	 * 
	 * @param keyword
	 * @return
	 */
	public static String escapeExprSpecialWord( String keyword ) {
		String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
		for( String key : fbsArr ) {
			if( keyword.contains( key ) ) {
				keyword = keyword.replace( key, "\\" + key );
			}
		}

		return keyword;
	}

	/**
	 * 判断一个字符串是否为英文中文或数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLetterDigitOrChinese( String str ) {
		String regex = "^[\u4e00-\u9fa5]+$";
		return escapeExprSpecialWord( str ).matches( regex );
	}

	/**
	 * 验证是不是EMAIL
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail( String email ) {
		boolean retval = false;
		if( empty( email ) )
			return retval;
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile( check );
		Matcher matcher = regex.matcher( email );
		retval = matcher.matches();
		return retval;
	}
	

}
