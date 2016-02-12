package com.model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.util.ByteFormate;

/**
 * 
 * @author chai
 */
public class PcapPackHead {
	
	private long ts_sec ;  //时间戳 秒 4个字节
	private long ts_usec;  //时间戳 微秒 4个字节
	private int incl_len;  //抓包长度  4个字节 小端需要转成大端
	private byte[] orig_len;  //实际长度 4个字节
	
	public byte[] pcapPacKHead;
	public 	byte[] pcapPackBody;
	
	private InputStream dis;
	
	public PcapPackHead(InputStream is) {
		this.dis = is;
		pcapPacKHead = new byte[16];
		run();
	}
	
	private void run() {
		try {
			dis.read(pcapPacKHead);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setTs_usec();
		setIncl_len();
		readBody();
	}
	
	private void readBody() {
		pcapPackBody = new byte[incl_len];
		try {
			dis.read(pcapPackBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long getTs_sec() {
		return ts_sec;
	}

	public void setTs_sec() {
		byte[] temp = new byte[]{pcapPacKHead[0],pcapPacKHead[1],pcapPacKHead[2],pcapPacKHead[3]};
		this.ts_sec = IPUtil.getNumber(temp);
	}

	public long getTs_usec() {
		return ts_usec;
	}

	public void setTs_usec() {
		byte[] temp = new byte[]{pcapPacKHead[4],pcapPacKHead[5],pcapPacKHead[6],pcapPacKHead[7]};
		this.ts_usec = IPUtil.getNumber(temp);
	}

	public int getIncl_len() {
		return incl_len;
	}

	public void setIncl_len() {
		byte[] temp = new byte[]{pcapPacKHead[8],pcapPacKHead[9],pcapPacKHead[10],pcapPacKHead[11]};
		byte[] temp1 = ByteFormate.reverse(temp);
		
		int A1 = (temp1[0] & 0xFF) << 24;
		int A2 = (temp1[1] & 0xFF) << 16;
		int A3 = (temp1[2] & 0xFF) << 8;
		int A4 = (temp1[3] & 0xFF) ;
		
		incl_len = A1 + A2 + A3 + A4;
 	}

	public byte[] getOrig_len() {
		return orig_len;
	}

	public void setOrig_len() {
		
	}
	

}
