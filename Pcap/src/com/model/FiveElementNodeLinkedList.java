package com.model;

/**
 * <p>Title:FiveElementNodeLinkedList.java </p> 
 * <p>Description:带头结点的五元组单链表 </p> 
 * @author chai
 * @date 2015-12-17 下午1:06:05 
 * @version 1.0
 */
public class FiveElementNodeLinkedList {
	
	private FiveElementNode head;   //头节点
	
	public FiveElementNodeLinkedList() {
		this.head = new FiveElementNode();
	}
	
	/**
	 * <p>Description:判断是否为空</p>
	 * @return
	 * @author chai
	 * @date 2015-12-17 下午1:08:58
	 * @version 1.0
	 */
	public boolean isEmpty() {
		return this.head.next == null;
	}
	
	/**
	 * <p>Description:单链表插入</p>
	 * @param fen
	 * @author chai
	 * @date 2015-12-17 下午1:09:12
	 * @version 1.0
	 */
	public void insert(FiveElementNode fen) {
		if(fen == null)
			return ;
		
		FiveElementNode p = this.head;
		boolean temp = false;
		while(p.next != null) {
//			System.out.println("11111111111111");
			if(p.equals(fen)) {
				temp = true;
				return ;
			}
			if(p.getSeqNumber() <= fen.getSeqNumber())
					p = p.next;
		}
		if(!temp) {
			p.next = new FiveElementNode(fen.getIp_1(),fen.getIp_2() , fen.getPort_1(), fen.getPort_2(), fen.getProtocolType(), fen.getTimestamp(), fen.getBody(), p.next, fen.getAckNumber(), fen.getSeqNumber());
		}
	}	
	
	/**
	 * <p>Description:在head后增加节点</p>
	 * @param fen
	 * @author chai
	 * @date 2015-12-23 下午8:28:57
	 * @version 1.0
	 */
	public void append(FiveElementNode fen) {
		FiveElementNode p = this.head;
		p.next = fen;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (FiveElementNode f = this.head.next ; f!=null ; f=f.next) {
			sb.append(f.toString());
			if(f.next != null) {
				sb.append(",");
			}
		}
		
		sb.append(")");
		return sb.toString();
	}

	public FiveElementNode getHead() {
		return head;
	}

	public void setHead(FiveElementNode head) {
		this.head = head;
	}
	
	

}
