package com.model;

import java.io.IOException;
import java.io.InputStream;

/**
 * pcap文件头模型
 * @author chai
 */
public class PcapFileHead {
	
	private byte[] fileHead;  //文件头24个字节
	
	private InputStream dis;
	
	public PcapFileHead(InputStream is) {
		this.dis = is;
		fileHead = new byte[24];
		read();
	}
	
	/**读取文件头 24个字节*/
	public void read() {
		try {
			dis.read(fileHead);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] getFileHead() {
		return fileHead;
	}

	public void setFileHead(byte[] fileHead) {
		this.fileHead = fileHead;
	}
	
	

}
