package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式化字符串
 */
public class StringFormat
{
	/**
	 * 替换无效的字
	 * @param str 要替换的内容
	 * @return 替换后的内容
	 */
	public static String replaceSpace(String str)
	{
		
				str = Format(str.trim());
				//str = str.replaceAll("、", "､");
				//str = str.replace("）", ")");
				str = str.replaceAll("★", "");
				str = str.replaceAll("◆", "");
				//str = str.replaceAll("【", "<");
				//str = str.replaceAll("】", ">");
				str = str.replace((char)127, (char)32);//空白字符
				//str = str.replace((char)65288, (char)40);//替换 (
				//str = str.replace((char)65289, (char)41);//替换 )
				str = str.replace((char)61472, (char)32);//
				//str = str.replace((char)12288, (char)32);//全角空格
				//str = str.replaceAll(" ", "");//32
				str = str.replaceAll("　", " ");//全角空格替换为半角空格
				str = str.replaceAll(" ", "");
		return str;
	}
	/**
	 * 删除预留的字符
	 * @param str 要删除的字符串
	 * @return 删除后的字符串
	 */
	private static String Format(String str)
	{
		
		Pattern pattern = Pattern.compile("【[0-9]标§】");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find())
		{
			//matcher.find();
			//System.out.println(matcher.find());
			str = str.replace(matcher.group(), "");
			
		}
		//[Ａ-Ｚ] 全角转化为半角  Ｚ不行
		/**
		Pattern pattern2 = Pattern.compile("[Ａ-Ｚ]");
		Matcher matcher2 = pattern2.matcher(str);
		while (matcher2.find())
		{
			String temp = matcher2.group();
			str = str.replace(matcher2.group(), (char)(temp.charAt(0) - 65248) + "");
			//System.err.println(str);
		}
		*/
		
		return str;
	}
}
