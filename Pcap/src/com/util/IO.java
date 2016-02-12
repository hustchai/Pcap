package com.util;

/**
 * 采用JDK的方式使用.
 */
public class IO
{
	/**
	 * 平台的错误输出.
	 * @param obj 输出的内容
	 */
	public static void error(String obj)
	{
		System.err.println(obj);	
	}
	/**
	 * 平台的错误输出.
	 * @param obj 输出的内容
	 * @param e   异常对象
	 */
	public static void error(String obj,Exception e)
	{
		System.err.println(obj);	
		e.printStackTrace();
	}
	/**
	 * 平台的标准输出.
	 * @param obj 输出的内容
	 */
	public static void print(String obj)
	{
		System.out.print(obj);
	}
	/**
	 * 输出指定的类
	 * @param cls 输出的类
	 */
	public static void print(Class<?> cls)
	{
		print(cls.toString());
	}	
}
