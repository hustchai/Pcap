package com.model;

/**
 * tcp/ip 协议五元组
 * @author chai
 *
 */
public class FiveElementNode {
	
	private String ip_1;
	private String ip_2;
	
	private int port_1;
	private int port_2;
	
	private long timestamp;
	
	private String protocolType;  //协议类型
	private long ackNumber;     //确认号
	private long seqNumber;     //序列号
	
	private byte[] head;
	private byte[] body;      //包体
	
	private boolean isReverse = false; // false 表示未反向  true表示反向
	
	
	public FiveElementNode next;
	
	public FiveElementNode pred;
	
	
	public FiveElementNode() {
	}
	

	public boolean isReverse() {
		return isReverse;
	}

	public void setReverse() {
		this.isReverse = true;
	}

	public FiveElementNode(String ip_1, String ip_2, int port_1, int port_2,
			 String protocolType, long timestamp, byte[] head,byte[] body,
			long ackNumber,long seqNumber) {
		super();
		this.ip_1 = ip_1;
		this.ip_2 = ip_2;
		this.port_1 = port_1;
		this.port_2 = port_2;
		this.timestamp = timestamp;
		this.protocolType = protocolType;
		this.body = body;
		this.head = head;
		this.ackNumber = ackNumber;
		this.seqNumber = seqNumber;
	}
	
	

	public FiveElementNode(String ip_1, String ip_2, int port_1, int port_2,
			long timestamp, String protocolType, byte[] body,
			FiveElementNode next,long ackNumber,long seqNumber) {
		super();
		this.ip_1 = ip_1;
		this.ip_2 = ip_2;
		this.port_1 = port_1;
		this.port_2 = port_2;
		this.timestamp = timestamp;
		this.protocolType = protocolType;
		this.body = body;
		this.next = next;
		this.ackNumber = ackNumber;
		this.seqNumber = seqNumber;
	}

	public FiveElementNode(String ip_1,String ip_2) {
		this.ip_1 = ip_1;
		this.ip_2 = ip_2;
	}
	
	public FiveElementNode(String ip_1,String ip_2,int port_1,int port_2,String protocolType,long timestamp,byte[] body,FiveElementNode next,long ackNumber,long seqNumber) {
		this(ip_1, ip_2);
		this.port_1 = port_1;
		this.port_2 = port_2;
		this.protocolType = protocolType;
		this.timestamp = timestamp;
		this.body = body;
		this.head = head;
		this.seqNumber = seqNumber;
		this.ackNumber = ackNumber;
	}
	
	public FiveElementNode swap() {
		String t = ip_1;
		ip_1 = ip_2;
		ip_2 = t;
		
		int i = port_1;
		port_1 = port_2;
		port_2 = i;
		
		long l = ackNumber;
		ackNumber = seqNumber;
		seqNumber = l;
		
		return this;
	}

	public byte[] getHead() {
		return head;
	}



	public void setHead(byte[] head) {
		this.head = head;
	}



	public String getIp_1() {
		return ip_1;
	}

	public void setIp_1(String ip_1) {
		this.ip_1 = ip_1;
	}

	public String getIp_2() {
		return ip_2;
	}

	public void setIp_2(String ip_2) {
		this.ip_2 = ip_2;
	}

	public int getPort_1() {
		return port_1;
	}

	public void setPort_1(int port_1) {
		this.port_1 = port_1;
	}

	public int getPort_2() {
		return port_2;
	}

	public void setPort_2(int port_2) {
		this.port_2 = port_2;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public long getAckNumber() {
		return ackNumber;
	}

	public void setAckNumber(int ackNumber) {
		this.ackNumber = ackNumber;
	}

	public long getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	public FiveElementNode getNext() {
		return next;
	}

	public void setNext(FiveElementNode next) {
		this.next = next;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * <p>Description:判断两个node是否为同向node </p>
	 * @return  同向返回true 异向返回false
	 * @author chai
	 * @date 2015-12-17 下午4:59:08
	 * @version 1.0
	 */
	public boolean identifyCheck(FiveElementNode other) {
		if(other.ip_1.equals(this.ip_1) && other.ip_2.equals(this.ip_2))
			return true;
		else
			return false;
		
	}
	
	
	/**
	 * 判断两个五元组是否是同一个五元组
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj)
			return true;
		
		if(obj == null)
			return false;
		
		if(getClass() != obj.getClass())
			return false;
		
		FiveElementNode other = (FiveElementNode)obj;
		
		if(other.ip_1.equals(this.ip_1) && other.ip_2.equals(this.ip_2) && other.port_1 == this.port_1 && other.port_2 == this.port_2 && other.getProtocolType().equals(this.protocolType) && other.getAckNumber() == this.getAckNumber() && other.getSeqNumber() == this.getSeqNumber())
			return true;
		return false;
	}
	
	

	@Override
	public String toString() {
		return "this.ip_1: " + this.ip_1 + "\r\n" +
				"this.ip_2: " + this.ip_2 + "\r\n" +
				"this.port_1: " + this.port_1 + "\r\n" +
				"this.port_2: " + this.port_2 + "\r\n" +
				"this.protocolType: " + this.protocolType + "\r\n" +
				"this.timestamp: " + this.timestamp + "\r\n" +
				"this.seqNumber: " + this.seqNumber + "\r\n" +
				"this.ackNumber: " + this.ackNumber + "\r\n" ;
	}
	
	
}
