package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtil {
	
	/**
	 * @param regex 正则表达式
	 * @param str  将要匹配的字符串
	 * @return
	 */
	public static String matches(String regex,String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if(matcher.matches()) 
			return matcher.group(1);
		return null;
	}

}
