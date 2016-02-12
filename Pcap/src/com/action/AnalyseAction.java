package com.action;

import com.model.IPUtil;

/**
 * 输入frame 返回5元组
 * @author chai
 */
public class AnalyseAction {
	
	private byte[] frame;   //传进来的frame包
	
	private int ipHeadLength;
	private String ip_1;
	private String ip_2;
	private int port_1 ;
	private int port_2 ;
	private String protocolType;
	private long ackNumber = 0;
	private long seqNumber = 0;
	private long timestamp = 0;
	
	public AnalyseAction(byte[] frame) throws Exception {
		this.frame = frame;
		setIpHeadLength();
		setProtocolType();
		String s = getProtocolType();
		if(s == null) 
			throw new Exception();
		setIp_1();
		setIp_2();
		setPort_1();
		setPort_2();
		if(this.protocolType.equals("TCP")) {
			setSeqNumber();
			setAckNumber();
		}
		
	}

	

	public int getIpHeadLength() {
		return ipHeadLength;
	}


	/**
	 * 头长度
	 */
	public void setIpHeadLength() {
			this.ipHeadLength =  IPUtil.ipHeadLength(frame[14]) * 4;
	}



	public String getIp_1() {
		return ip_1;
	}

	/**
	 * IP1
	 */
	public void setIp_1() {
		byte[] temp = {frame[26],frame[27],frame[28],frame[29]};
		this.ip_1 = IPUtil.getIP(temp);
	}

	public String getIp_2() {
		return ip_2;
	}

	/**
	 * IP2
	 */
	public void setIp_2() {
		byte[] temp = {frame[30],frame[31],frame[32],frame[33]};
		this.ip_2 = IPUtil.getIP(temp);
	}

	public int getPort_1() {
		return port_1;
	}

	public void setPort_1() {
		byte[] temp = {frame[14+ipHeadLength],frame[14+ipHeadLength+1]};
		this.port_1 = IPUtil.getProt(temp);
	}

	public int getPort_2() {
		return port_2;
	}

	public void setPort_2() {
		byte[] temp = {frame[14+ipHeadLength+2],frame[14+ipHeadLength+3]};
		this.port_2 = IPUtil.getProt(temp);
	}

	public String getProtocolType() {
		return protocolType;
	}

	/**
	 * 设置协议 返回 “TCP” “UDP” “null”
	 */
	public void setProtocolType() {
		this.protocolType = IPUtil.getprotocolType(frame[23]);
	}

	public long getAckNumber() {
		return ackNumber;
	}

	public void setAckNumber() {
		byte[] temp = {frame[14+ipHeadLength+8],frame[14+ipHeadLength+9],frame[14+ipHeadLength+10],frame[14+ipHeadLength+11]};
		this.ackNumber = IPUtil.getNumber(temp);
	}

	public long getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber() {
		byte[] temp = {frame[14+ipHeadLength+4],frame[14+ipHeadLength+5],frame[14+ipHeadLength+6],frame[14+ipHeadLength+7]};
		this.seqNumber = IPUtil.getNumber(temp);
	}
	



	@Override
	public String toString() {
		return "===========================================\r\n" +
				"frame长度是：" + this.frame.length + "\n" +
				"ip首部长度是：" + this.ipHeadLength + "\n" +
				"ip_1: " + this.ip_1 + "\n" +
				"ip_2: " + this.ip_2 + "\n" +
				"port_1: " + this.port_1 + "\n" +
				"port_2: " + this.port_2 + "\n" +
				"seqNumber: " + this.seqNumber + "\n" + 
				"ackNumber: " + this.ackNumber + "\n" +
				"protocolType: " + this.protocolType + "\n" +
				"============================================\r\n";
	}
	
	
	

}
