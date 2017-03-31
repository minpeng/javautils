package com.util.string;

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
}
