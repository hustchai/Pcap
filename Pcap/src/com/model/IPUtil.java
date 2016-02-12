package com.model;

import com.util.ByteFormate;

/**
 * IP模型
 * @author chai
 */
public class IPUtil {
	

	/**
	 * 获得ip头长度
	 * @param b
	 * @return
	 */
	public static int ipHeadLength(byte b) {
		byte temp = b;
		byte A1 = (byte)(temp << 4);
		return A1 >>> 4;
	}
	
	/**
	 * <p>Description:计算tcp首部长度</p>
	 * @param b
	 * @return
	 * @author chai
	 * @date 2015-12-23 下午9:10:38
	 * @version 1.0
	 */
	public static int tcpHeadLength(byte b) {
		byte temp = b;
		return b >>> 4;
	}
	
	/**
	 * @param ip
	 * @return
	 */
	public static String getIP(byte[] ip) {
		StringBuilder sb = new StringBuilder();
		sb.append(ip[0] & 0xFF);
		sb.append(".");
		sb.append(ip[1] & 0xFF);
		sb.append(".");
		sb.append(ip[2] & 0xFF);
		sb.append(".");
		sb.append(ip[3] & 0xFF);
		return sb.toString();
	}
	
	/**
	 * 计算协议类型
	 * @param b
	 * @return
	 */
	public static String getprotocolType(byte b) {
		int temp = b & 0xFF;
		if( 6 == temp) {
			return "TCP";
		} else if(17 == temp) {
			return "UDP";
		} else {
			return null;
		}
	}
	
	/**
	 * 获得端口号
	 * @param temp
	 * @return
	 */
	public static int getProt(byte[] temp) {
		int A1 = (temp[0] & 0xFF) << 8;
		int A2 = (temp[1] & 0xFF);
		return A1+A2;
	}
	
	
	/**
	 * 获得序列号 或 确认号
	 * @param temp
	 * @return
	 */
	public static long getNumber(byte[] temp) {
		long a = temp[0] & 0xFF;
		long A1 = a << 24;
		long A2 = (temp[1] & 0xFF ) << 16;
		long A3 = (temp[2] & 0xFF ) << 8;
		long A4 = (temp[3] & 0xFF );
		return A1+A2+A3+A4;
	}
	
	/**
	 * 显示byte数组中的数据
	 * @param temp
	 */
	public static void showBytes(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < temp.length; i++) {
			sb.append(ByteFormate.ByteFormate(temp[i]));
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}
	
}
