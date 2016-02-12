package com.model;

/**
 * ipPair
 * @author chai
 */
public class IPpair {
	
	private String ip_1;
	private String ip_2;
	private int port_1;
	private int port_2;
	
	public IPpair() {}
	
	public IPpair(String ip_1, String ip_2, int port_1, int port_2) {
		this.ip_1 = ip_1;
		this.ip_2 = ip_2;
		this.port_1 = port_1;
		this.port_2 = port_2;
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

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(obj == null)
			return false;
		
		if(getClass() != obj.getClass())
			return false;
		
		IPpair other = (IPpair)obj;
		
		if(other.ip_1.equals(this.ip_1) && other.ip_2.equals(this.ip_2)) {
			if(other.port_1 == this.port_1 && other.port_2 == this.port_2)
					return true;
		} else if(other.ip_1.equals(this.ip_2) && other.ip_2.equals(this.ip_1))
		{
			if(other.port_1 == this.port_2 && other.port_2 == this.port_1)
					return true;
		}
		
		return false;
		
	}
	
	@Override
	public int hashCode() {
		int h1 = ip_1.hashCode() * 1137 ;
		int h2 = ip_2.hashCode() * 1137 ;
		int h3 = port_1 * 1137;
		int h4 = port_2 * 1137;
		return h1 + h2 + h3 + h4;
	}
	

}
