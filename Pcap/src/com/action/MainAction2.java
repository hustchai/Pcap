package com.action;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.model.DoubleFiveElementNodeLinkedList;
import com.model.FiveElementNode;
import com.model.FiveElementNodeLinkedList;
import com.model.PcapPackHead;
import com.model.TcpData;
import com.util.ConstantsUtil;

public class MainAction2 {
	
	private DoubleFiveElementNodeLinkedList fenll = null;
	
	public MainAction2() {
		run();
	}
	
	public void run() {
		File file =	ConstantsUtil.outPutSavePath;
		int len = file.listFiles().length;
		int a = 1;
		for (int i = 0; i < len; i++) {
			fenll = new DoubleFiveElementNodeLinkedList();
			File f = file.listFiles()[i];
			String fileName = f.getName();
			Pattern pattern = Pattern.compile("\\[(TCP)\\]\\[(\\S+)\\]\\[(\\S+)\\]\\[(\\S+)\\]\\[(\\S+)\\]\\.pcap");
			Matcher matcher = pattern.matcher(fileName);
			if(matcher.matches()) {
//				System.out.println("正在处理第" + a++ +"个tcp会话");
				try {
					DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
					dis.skip(24);         //跳过头24字节
					while(dis.available() > 0 ) {
						PcapPackHead pph = new PcapPackHead(dis);
						if(pph.pcapPackBody.length < 14)
							break;
						AnalyseAction aa = new AnalyseAction(pph.pcapPackBody);
						FiveElementNode fen = new FiveElementNode(aa.getIp_1(), aa.getIp_2(), aa.getPort_1(), aa.getPort_2(),pph.getTs_usec(), aa.getProtocolType(), new TcpData(pph.pcapPackBody).getTcpdata(), null,aa.getAckNumber(), aa.getSeqNumber());
						
						if(fenll.isEmpty()) {
							fenll.insert(fen);
						} else {
							FiveElementNode head = fenll.getHead();
							if(head.identifyCheck(fen)) {
								fenll.insert(fen);
							} else {
								FiveElementNode reverse = new FiveElementNode(fen.getIp_2(), fen.getIp_1(), fen.getPort_2(), fen.getPort_1(), fen.getProtocolType(), fen.getTimestamp(), null, fen.getBody(), fen.getSeqNumber(),fen.getAckNumber());
								reverse.setReverse();
								fenll.insert(reverse);
							}
							
						}
								
					}
				
					FiveElementNode fiveElementNode = fenll.getHead();
					RandomAccessFile raf = new RandomAccessFile(new File(ConstantsUtil.outPutDataPath+"//"+fileName+".txt"), "rw");
					while(fiveElementNode.next != null) {
						if(fiveElementNode.getBody() != null) {
							int length = (int)raf.length();
							raf.seek(length);
							raf.write(fiveElementNode.getBody());
						}
						
						fiveElementNode = fiveElementNode.next;
						if(fiveElementNode != null && fiveElementNode.isReverse()) {
							byte[] t = new byte[]{0x0d,0x0a,0x0d,0x0a};
							int length = (int)raf.length();
							raf.seek(length);
							raf.write(t);
						}
						
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			
		}
		
		
	}
	
	

}
