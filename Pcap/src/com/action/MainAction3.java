package com.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.model.Http;
import com.util.ConstantsUtil;
import com.util.MatcherUtil;

public class MainAction3 {
	
	private File outPutHttpPath ; 
	private File outPutDataPath;
	private BufferedReader br;
	private HashMap<String, LinkedList<Http>> maps = new HashMap<String, LinkedList<Http>>();
	
	public MainAction3() {
		outPutHttpPath = ConstantsUtil.outPutHttpPath;
		outPutDataPath = ConstantsUtil.outPutDataPath;
		try {
			run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws IOException {
		int len = outPutDataPath.listFiles().length;   //文件的数量
		for (int i = 0; i < len ; i++) {
				File f = outPutDataPath.listFiles()[i];
				String fileName = f.getName();
				Pattern pattern1 = Pattern.compile("\\[(TCP)\\]\\[(\\S+)\\]\\[(80)\\]\\[(\\S+)\\]\\[(\\S+)\\]\\.pcap\\.txt");
				Pattern pattern2 = Pattern.compile("\\[(TCP)\\]\\[(\\S+)\\]\\[(\\S+)\\]\\[(\\S+)\\]\\[(80)\\]\\.pcap\\.txt");
				Matcher matcher1 = pattern1.matcher(fileName);
				Matcher matcher2 = pattern2.matcher(fileName);
				if(matcher1.matches() || matcher2.matches()) {
					try {
						br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
						String temp ;
						LinkedList<Http> https = new LinkedList<Http>();
						Http http = new Http();
						StringBuilder content = new StringBuilder();
						while((temp = br.readLine()) != null) {
							if("".equals(temp))
								continue;
							String t;
							if((t = MatcherUtil.matches("GET(.+)HTTP.+", temp)) != null || (t = MatcherUtil.matches("POST(.+)HTTP.+", temp)) != null) {
								if("".equals(http.getUrl())) {
									http.setUrl(t);
									String range ;
									if((range = MatcherUtil.matches("(.+Range.+)", t)) != null ) {
										http.setRange(range);
									}
									continue;
								}
									
								else {
									if(http.getUrl() != null)
									{
										http.setContent(content.toString());
										https.add(http);
										System.out.println(http);
									}
										
									http = new Http();
									content = new StringBuilder();
									http.setUrl(t);
									String range ;
									if((range = MatcherUtil.matches("(.+Range.+)", t)) != null ) {
										http.setRange(range);
									}
									continue;
								}
							}
							
							if((t = MatcherUtil.matches("Accept:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Referer:(.+)", temp)) != null) {
								http.setReferer(t);
								continue;
							}
							
							if((t = MatcherUtil.matches("Accept-Language:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Accept-Encoding:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("If-Modified-Since:(.+)", temp)) != null) {
								continue;
							}
							if((t = MatcherUtil.matches("If-None-Match:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("User-Agent:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Host:(.+)", temp)) != null) {
								http.setHost(t);
								continue;
							}
							
							if((t = MatcherUtil.matches("Connection:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Cookie:(.+)", temp)) != null) {
								continue;
							}
							if((t = MatcherUtil.matches("Authorization:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("^HTTP(.+)", temp)) != null) {
								continue;
							}
							if((t = MatcherUtil.matches("Location:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Server:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Pragma:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Transfer-Encoding:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Range:(.+)", temp)) != null || (t = MatcherUtil.matches("Content-Range:(.+)", temp)) != null) {
								http.setRange(t);
								continue;
							}
							
							if((t = MatcherUtil.matches("Date:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Cache-Control:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Content-Length:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Content-Language:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Connection:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Content-Type:(.+)", temp)) != null) {
								http.setContent_Type(t);
								continue;
							}
							
							if((t = MatcherUtil.matches("Expires:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Last-Modified:(.+)", temp)) != null) {
								continue;
							}
							if((t = MatcherUtil.matches("Content-Disposition:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Age:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("X-Cache:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("X-AspNet-Version:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("X-Powered-By:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Refresh:(.+)", temp)) != null) {
								continue;
							}
							
							if((t = MatcherUtil.matches("Content-Encoding:(.+)", temp)) != null) {
								http.setContent_Encoding(t);
								continue;
							}
							
							content.append(temp);
						}
						if(http.getUrl() != null) {
							http.setContent(content.toString());
							https.add(http);
						}
						maps.put(fileName, https);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
		}
		
		RandomAccessFile raf = null;
		Iterator<Entry<String, LinkedList<Http>>> iterator = 	maps.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, LinkedList<Http>> entry = iterator.next();
			String fileName = entry.getKey();
			LinkedList<Http> https = entry.getValue();
			raf = new RandomAccessFile(outPutHttpPath+"\\"+fileName+".http.txt", "rw");
			while(!https.isEmpty()) {
				int h = (int) raf.length();
				raf.seek(h);
				raf.writeBytes(https.removeFirst().toString());
				raf.write(0x0d0a);
				raf.write(0x0d0a);
			}
			
		}
		
		if(raf != null) {
			raf.close();
		}
		
	}

}
