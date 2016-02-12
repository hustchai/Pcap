package com.model;

public class TcpData {
	
	private int macHeadLen = 14;
	private int ipHeadLen;
	private int tcpHeadLen;

	private byte[] tcpdata;
	private byte[] pcapPacketBody ;
	
	public TcpData(byte[] body) {
		pcapPacketBody = body;
		setIpHeadLen();
		setTcpHeadLen();
		setTcpData();
	}
	
	/**
	 * <p>Description:ip头长度</p>
	 * @author chai
	 * @date 2015-12-23 下午9:00:07
	 * @version 1.0
	 */
	public void setIpHeadLen() {
		this.ipHeadLen =  IPUtil.ipHeadLength(pcapPacketBody[14]) * 4;
	}
	
	/**
	 * <p>Description:获得tcp头长度</p>
	 * @author chai
	 * @date 2015-12-23 下午9:15:07
	 * @version 1.0
	 */
	public void setTcpHeadLen() {
		this.tcpHeadLen = IPUtil.tcpHeadLength(pcapPacketBody[46]) * 4;
	}
	
	/**
	 * <p>Description:</p>
	 * @author chai
	 * @date 2015-12-23 下午9:18:37
	 * @version 1.0
	 */
	public void setTcpData() {
		int tcpDataLen = pcapPacketBody.length - 14 - ipHeadLen - tcpHeadLen;
		if(tcpDataLen <= 0) {
			
		} else {
			tcpdata = new byte[tcpDataLen];
			
			int h = 0;
			for (int i = 14 + ipHeadLen + tcpHeadLen; i < pcapPacketBody.length; i++) {
				tcpdata[h++] = pcapPacketBody[i];
			}
		}
		
	}

	public byte[] getTcpdata() {
		return tcpdata;
	}

	public void setTcpdata(byte[] tcpdata) {
		this.tcpdata = tcpdata;
	}
	
	
}
