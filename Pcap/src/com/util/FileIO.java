package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 文件的操作.
 */
public class FileIO
{
	/** 获取到文件对象.
	 * 
	 * @param name 要获取的文件名.
	 * @return 文件对象
	 */
	public static File getFile(String name) {
		return new File(name);
	}
	/**
	 * 读取一个文件的内容.
	 * 此处采用Java自带的JDK方式读取.
	 * @param file 要读取的文件.
	 * @param encode 编码.
	 * @return 读取到的内容.
	 */
	public static String readFile(File file,String encode) {
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = null;
		try
		{
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName(encode)));
			char c[] = new char[1024];
			int n;
			while ((n = bufferedReader.read(c)) != -1)
			{
				buffer.append(c,0,n);
			}
			bufferedReader.close();
			return buffer.toString();
		}catch(Exception e)
		{
			IO.error("读取文件失败!文件<" + file.getAbsolutePath() + ">不存在!",e);
			return null;
		}finally
		{
			//关闭输入流
			if(bufferedReader != null)
			{
				try
				{
					bufferedReader.close();
				} catch (IOException e)
				{
					IO.error("发生了I/O错误!",e);
					return null;
				}
			}
		}
	}
	
	/**
	 * 读取文件 按行读
	 * @param file 要读的文件
	 * @param encode 编码方式
	 * @return 读取到的内容
	 */
	public static String readFileByLine(File file,String encode) {
		StringBuffer buffer = new StringBuffer();
		Scanner sc = null;
		try {
			sc = new Scanner(new InputStreamReader(new FileInputStream(file),Charset.forName(encode)));
			while(sc.hasNextLine()) {
				buffer.append(sc.nextLine());
				buffer.append("\n");
			}
			sc.close();
			return buffer.toString();
		} catch (FileNotFoundException e) {
			IO.error("读取文件失败!文件<" + file.getAbsolutePath() + ">不存在!",e);
			return null;
		}finally {
			if(sc != null) {
				try {
					sc.close();
				} catch (Exception e) {
					IO.error("发生了I/O错误!",e);
					return null;
				}
			} 
		}
	}
	
	
	
	/**
	 * 把内容写到文件中.
	 * @param file  要写的文件 
	 * @param chars 要写内容数组
	 * @param off   所写内容数组下标开始位置
	 * @param n     所写内容数组的长度
	 * @return 是否写成功
	 */
	public static boolean writeFile(File file,char[] chars,int off,int n) {
		FileWriter fw = null;
		try
		{
			fw = new FileWriter(file);
			fw.write(chars, off, n);			
		}catch(Exception e)
		{
			IO.error("写入文件失败!文件<" + file.getAbsolutePath() + ">不能写入!",e);
			return false;
		}finally
		{
			//关闭输入流
			if(fw != null)
			{
				try
				{
					fw.close();
				} catch (IOException e)
				{
					IO.error("发生了I/O错误!",e);
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 把内容写到文件中.
	 * @param file  要写的文件 
	 * @param bytes 要写内容数组
	 * @param off   所写内容数组下标开始位置
	 * @param n     所写内容数组的长度
	 * @return 是否写成功
	 */
	public static boolean writeFile(File file,byte[] bytes,int off,int n) {
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
			fos.write(bytes, off, n);	
		}catch(Exception e)
		{
			IO.error("写入文件失败!文件<" + file.getAbsolutePath() + ">不能写入!",e);
			return false;
		}finally
		{
			//关闭输入流
			if(fos != null)
			{
				try
				{
					fos.close();
				} catch (IOException e)
				{
					IO.error("发生了I/O错误!",e);
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 把内容写到文件中.
	 * @param file  要写的文件
	 * @param chars 要写的内容
	 * @return 是否写成功
	 */
	public static boolean writeFile(File file,char[] chars) {
		return writeFile(file, chars, 0, chars.length);
	}
	/**
	 * 把内容写到文件中.
	 * @param file  要写的文件
	 * @param bytes 要写的内容
	 * @return 是否写成功
	 */
	public static boolean writeFile(File file,byte[] bytes) {
		return writeFile(file, bytes,0,bytes.length);
	}
	/**
	 * 把内容写到文件中.
	 * @param file 要写的文件
	 * @param str 要写的内容
	 * @param encode 写时候的编码
	 * @return 是否写成功
	 */
	public static boolean writeFile(File file,String str,String encode) {
		try
		{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName(encode)));
			bw.append(str);
			bw.close();
			return true;
		}catch(Exception e)
		{
			
		}
		return false;
	}
	/**
	 * @param oldFile 原文件
	 * @param newFile 新文件
	 * @return 是否复制成功
	 */
	public static boolean copyFile(File oldFile,File newFile) {
		if(oldFile.exists() == false)
		{
			return false;
		}
		
		
		try
		{
			FileInputStream fis = new FileInputStream(oldFile);
			int n;
			byte buf[] = new byte[1024];
			FileOutputStream fos = new FileOutputStream(newFile);
			while((n = fis.read(buf)) != -1)
			{
				fos.write(buf, 0, n);
			}
			fos.close();
			fis.close();
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("文件复制失败!");
			return false;
		}
	}
	/**
	 * 
	 */
	public static boolean appendFile(File file,String str) {
		return appendFile(file, str,"UTF-8");
	}
	/**
	 * @param file 要写入的文件
	 * @param str  要写入的内容
	 * @param encode 编码
	 */
	@Deprecated
	public static boolean appendFile(File file,String str,String encode) {
		BufferedWriter bufferedWriter = null;
		try
		{
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true), Charset.forName(encode)));
			bufferedWriter.append(str);
			return true;
		}catch (Exception e)
		{
			
		}
		finally
		{
			if(bufferedWriter != null)
			{
				try
				{
					bufferedWriter.close();
				} catch (Exception e){}
			}
		}
		return false;
	}
	
	public static void deleteFile(File file) {
		if(file.isFile())
		{
			file.delete();
		}else
		{
			if(file.exists())
			{
				File files[] = file.listFiles();
				for(File one : files)
				{
					deleteFile(one);
				}
			}
		}
	}
}
