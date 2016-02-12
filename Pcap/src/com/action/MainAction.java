package com.action;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.text.html.parser.Entity;

import com.gui.MainFrame;
import com.model.DoubleFiveElementNodeLinkedList;
import com.model.FiveElementNode;
import com.model.IPUtil;
import com.model.IPpair;
import com.model.KV;
import com.model.PcapFileHead;
import com.model.PcapPackHead;
import com.util.ConstantsUtil;
import com.util.FileIO;

public class MainAction {
	
	private File pcapSavePath;
	private File outPutPath;
	private DataInputStream dis ;
	private ArrayList pool = new ArrayList();
	private LinkedList<KV> kvs = new LinkedList<KV>();
	private HashMap<IPpair, DoubleFiveElementNodeLinkedList> maps = new HashMap<IPpair, DoubleFiveElementNodeLinkedList>();
	private PcapFileHead pfh = null;
	
	public MainAction() {
		pcapSavePath = ConstantsUtil.pcapSavePath;
		outPutPath = ConstantsUtil.outPutSavePath;
		Run();
	}
	
	@SuppressWarnings("finally")
	public void Run() {
		int i = 1;
		try {
			dis	= new DataInputStream(new BufferedInputStream(new FileInputStream(pcapSavePath)));
			pfh = new PcapFileHead(dis);  //读取pcap文件头24字节
			while(dis.available() > 0 ) {
				PcapPackHead pph = new PcapPackHead(dis); //读取packet头 并转换包实际长度
				pool.add(pph);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<PcapPackHead> iterators = pool.iterator();
//		while(!pool.isEmpty()) {
		while(iterators.hasNext()) {
			PcapPackHead pph = (PcapPackHead)iterators.next();
//			PcapPackHead pph =	(PcapPackHead)pool.;
			byte[] frame = pph.pcapPackBody;
			String s = IPUtil.getprotocolType(frame[23]); //读frame[23]是否为tcp或者udp
//			System.out.println("正在处理第    "+ i++ +"个包");
			if("UDP".equals(s))
			{
				try {
					AnalyseAction aa = new AnalyseAction(frame);
					IPpair ipp = new IPpair(aa.getIp_1(), aa.getIp_2(), aa.getPort_1(), aa.getPort_2());
					FiveElementNode fen = new FiveElementNode(aa.getIp_1(), aa.getIp_2(), aa.getPort_1(), aa.getPort_2(),aa.getProtocolType(),pph.getTs_usec(),pph.pcapPacKHead ,pph.pcapPackBody,aa.getAckNumber(),aa.getSeqNumber());//设置五元组
					
					boolean pairIsExist = false;
					DoubleFiveElementNodeLinkedList fenll =	maps.get(ipp);
					if(fenll == null) {
						fenll = new DoubleFiveElementNodeLinkedList();
						fenll.insert(fen);
						maps.put(ipp, fenll);
					} else {
						fenll.insert(fen);
					}
//					for (int j = 0; j < kvs.size() ; j++) {
//						IPpair temp = kvs.get(j).getIpp();
//						if(temp.equals(ipp)) {
//							pairIsExist = true;
//							kvs.get(j).getFenll().insert(fen);
//							break;
//						}
//					}
					
//					if(!pairIsExist) {    //如果不存在就增加kv对到kvs内
////						FiveElementNodeLinkedList fenll = new FiveElementNodeLinkedList();
//						DoubleFiveElementNodeLinkedList fenll = new DoubleFiveElementNodeLinkedList();
//						fenll.insert(fen);
//						KV kv = new KV(ipp, fenll);
//						kvs.add(kv);
//					}
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					continue;
				}
			} else if("TCP".equals(s)) {
				AnalyseAction aa;
				try {
					aa = new AnalyseAction(frame);
					IPpair ipp = new IPpair(aa.getIp_1(), aa.getIp_2(), aa.getPort_1(), aa.getPort_2());
					FiveElementNode fen = new FiveElementNode(aa.getIp_1(), aa.getIp_2(), aa.getPort_1(), aa.getPort_2(),aa.getProtocolType(),pph.getTs_usec(),pph.pcapPacKHead, pph.pcapPackBody,aa.getAckNumber(),aa.getSeqNumber());//设置五元组
					
					DoubleFiveElementNodeLinkedList fenll =	maps.get(ipp);
					if(fenll == null) {
						fenll = new DoubleFiveElementNodeLinkedList();
						fenll.insert(fen);
						maps.put(ipp, fenll);
					} else {
						if(fenll.getHead().identifyCheck(fen)) {
							fenll.insert(fen);
						} else {
							fenll.insert(fen.swap());
						}
					}
					
//					boolean pairIsExist = false;
//					for (int j = 0; j < kvs.size() ; j++) {
//						IPpair temp = kvs.get(j).getIpp();
//						if(temp.equals(ipp)) {
//							pairIsExist = true;
//							if(temp.getIp_1().equals(fen.getIp_1()) && temp.getIp_2().equals(fen.getIp_2())) {
//								kvs.get(j).getFenll().insert(fen);
//							} else {
//								kvs.get(j).getFenll().insert(fen.swap());
////								kvs.get(j).getFenll().insert(new FiveElementNode(fen.getIp_2(), fen.getIp_1(), fen.getPort_2(), fen.getPort_1(), fen.getProtocolType(), fen.getTimestamp(),fen.getHead(),fen.getBody(), fen.getSeqNumber(), fen.getAckNumber()));
//							}
//							break;
//						}
//					}
//					
//					if(!pairIsExist) {    //如果不存在就增加kv对到kvs内
////						FiveElementNodeLinkedList fenll = new FiveElementNodeLinkedList();
//						DoubleFiveElementNodeLinkedList fenll = new DoubleFiveElementNodeLinkedList();
//						fenll.insert(fen);
//						KV kv = new KV(ipp, fenll);
//						kvs.add(kv);
//					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		
		if(outPutPath.list().length > 0) 
		{
			FileIO.deleteFile(outPutPath);
		}
		/**
		 * 执行写入
		 */
		int len = 0;
		
		RandomAccessFile raf = null;
		File outTxt = new File(outPutPath+"\\"+"输出报告.txt");
			Iterator<Entry<IPpair,DoubleFiveElementNodeLinkedList>> iterator = maps.entrySet().iterator();
			while(iterator.hasNext()) {
					Entry<IPpair, DoubleFiveElementNodeLinkedList> entry = iterator.next();
					DoubleFiveElementNodeLinkedList fenll = entry.getValue();
					FiveElementNode f = fenll.getHead();
					String fileName = getName(f);
					File file = new File(outPutPath+"\\"+fileName);
					FileIO.appendFile(outTxt, fileName+"\r\n");
					try {
						raf = new RandomAccessFile(file, "rw");
						int length = (int)raf.length();
						raf.seek(length);
						raf.write(pfh.getFileHead());
						while(f != null) {
							int h  = (int)raf.length();
							raf.seek(h);
							raf.write(f.getHead());
							int b  = (int)raf.length();
							raf.seek(b);
							raf.write(f.getBody());
							f = f.next;
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							raf.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
			}
//		for (int j = 0; j < maps.keySet().size(); j++) {
//			DoubleFiveElementNodeLinkedList fenll = maps.values().;
//			FiveElementNode f = fenll.getHead();
//			String fileName = getName(f);
//			File file = new File(outPutPath+"\\"+fileName);
//			FileIO.appendFile(outTxt, fileName+"\r\n");
//			try {
//				raf = new RandomAccessFile(file, "rw");
//				int length = (int)raf.length();
//				raf.seek(length);
//				raf.write(pfh.getFileHead());
//				while(f != null) {
//					int h  = (int)raf.length();
//					raf.seek(h);
//					raf.write(f.getHead());
//					int b  = (int)raf.length();
//					raf.seek(b);
//					raf.write(f.getBody());
//					f = f.next;
//				}
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					raf.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
		
	}
	
	/**
	 * 获得文件的名字
	 * @param fen
	 * @return
	 */
	private String getName(FiveElementNode fen) {
		String ip_1 = fen.getIp_1();
		String ip_2 = fen.getIp_2();
		String ip[] = ip_1.split("\\.");
		String ip2[] = ip_2.split("\\.");
		boolean temp = false;
		for (int i = 0; i < ip.length; i++) {
			if(Integer.parseInt(ip[i]) <= Integer.parseInt(ip2[i])) {
				temp = true;
				break;
			} else {
				temp = false;
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(fen.getProtocolType());
		sb.append("]");
		if(temp) {
			sb.append("[");
			sb.append(ip_1);
			sb.append("]");
			
			sb.append("[");
			sb.append(fen.getPort_1());
			sb.append("]");
			
			sb.append("[");
			sb.append(ip_2);
			sb.append("]");
			
			sb.append("[");
			sb.append(fen.getPort_2());
			sb.append("]");
		} else {
			sb.append("[");
			sb.append(ip_2);
			sb.append("]");
			
			sb.append("[");
			sb.append(fen.getPort_2());
			sb.append("]");
			
			sb.append("[");
			sb.append(ip_1);
			sb.append("]");
			
			sb.append("[");
			sb.append(fen.getPort_1());
			sb.append("]");
		}
		sb.append(".pcap");
		return sb.toString();
	}
	

}
