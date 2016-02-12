package com.model;

public class Http {
	
	private String url;  //GET请求的路径
	private String Referer; //GET请求的referer
	private String Host;   //GET请求的Host
	private String Content_Encoding; // 编码
	private String Content_Type;
	private String Range;    //断点重传
	private String content; //内容
	
	

	public String getRange() {
		return Range;
	}

	public void setRange(String range) {
		Range = range;
	}

	public String getContent_Type() {
		return Content_Type;
	}

	public void setContent_Type(String content_Type) {
		Content_Type = content_Type;
	}

	public String getContent_Encoding() {
		return Content_Encoding;
	}

	public void setContent_Encoding(String content_Encoding) {
		Content_Encoding = content_Encoding;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getHost() {
		return Host;
	}

	public void setHost(String host) {
		Host = host;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReferer() {
		return Referer;
	}

	public void setReferer(String referer) {
		Referer = referer;
	}

	@Override
	public String toString() {
		return "url = "+ this.url + "\r\n" +
		"Referer = "+ this.Referer + "\r\n" +
		"Host = "+ this.Host + "\r\n" +
		"Content_Encoding = "+ this.Content_Encoding + "\r\n" +
		"Content_Type = "+ this.Content_Type + "\r\n" +
		"Range = "+ this.Range + "\r\n" +
		"content = "+ this.content + "\r\n";
	}


	
	
	

}
