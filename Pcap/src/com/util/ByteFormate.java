package com.util;

public class ByteFormate {
	
	/**
	 * 格式化输出16进制字符串
	 * @param b
	 * @return
	 */
	public static String ByteFormate(byte b) {
		return String.format("%02x", b);
	}
	
	/**
	 * 将byte[]数组 逆序 即大小端转换
	 * @param myByte
	 * @return
	 */
	public static byte[] reverse(byte[] myByte)
	{
        byte[] newByte = new byte[myByte.length];

        for (int i = 0; i < myByte.length; i++)
        {
            newByte[i] = myByte[myByte.length - 1 - i];
        }
        return newByte;
}

}
