package com.model;


public class DoubleFiveElementNodeLinkedList {

	public FiveElementNode head;
	private FiveElementNode current;  //上次插入点
	
	public DoubleFiveElementNodeLinkedList() {
		current = head;
	}
	
	/**
	 * 判断链表是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return head == null ? true : false;
	}
	
	/**
	 * 判断当前结点是否还有下个结点
	 * @param node
	 * @return false表示没有  true表示有
	 */
	public boolean hasNext(FiveElementNode node) {
		if(node.next == null)
			return false;     
		return true;
	}
	
	/**
	 * 当前结点是否有前结点
	 * @param node
	 * @return false表示没有 true表示有
	 */
	public boolean hasPrev(FiveElementNode node) {
		if(node.pred == null)
			return false;
		return true;
	}
	
	
	/**
	 * 获得头结点
	 * @return
	 */
	public FiveElementNode getHead() {
		return head;
	}
	
	/**
	 * 在链表头添加结点
	 */
	public void addHead(FiveElementNode node) {
		if(head==null){
			head=node;
			} else{
				node.next=head;
				head.pred=node;
				head=node;
			}
	}
	
	/**
	 * 获得最后一个结点
	 * @return
	 * @throws Exception
	 */
	public FiveElementNode getLast() throws Exception{
		FiveElementNode curNode=null;
		FiveElementNode next=null;
		FiveElementNode last=null;

		boolean flag=true;
		if(head==null){
			throw new Exception("链表为空");
		} else {
			curNode=head;
		while(flag) {
			if(hasNext(curNode)){
			next=curNode.next;
			curNode=next;
		    }	else {
				last=curNode;
				flag=false;
				}
			}
		}
			return last;
	}
	
	/**
	 * 尾插入
	 * @param node
	 * @throws Exception
	 */
	public void addLast(FiveElementNode node) throws Exception{

		if(head==null){
			head=node;
		}	else {
			FiveElementNode last=this.getLast();
			last.next=node;
			node.pred=last;
			}
		}
	/**
	 * 插入结点
	 * @param fen
	 */
	public void insert(FiveElementNode fen) {
		if(fen == null) 
			return ;
		
		if(isEmpty()) {
			try {
				addLast(fen);
				current = head;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ;
		}
		
		if(fen.getProtocolType().equals("UDP")) {
			FiveElementNode p = current;
			while(true) {
				if(p.getTimestamp() < fen.getTimestamp()) {
					if(!hasNext(p)) {   //如果下个结点为空
						p.next = fen;
						fen.pred = p;
						current = p.next;
						break;
					} else {
						p.next.pred = fen;    //结点插入
						fen.next = p.next;
						p.next = fen;
						fen.pred = p;
						current = p.next;
						break;
					}
					
				} else {
					
					if(!hasPrev(p)) {  //如果上个结点为空
						fen.next = p;
						p.pred = fen;
						current = fen;
						break;
					} else {
						p = p.pred;  //往前移位
//						current = p;
					}
					
				}
			}
			
		} else if(fen.getProtocolType().equals("TCP")) {
			FiveElementNode p = current;
			while(true) {
					if(fen.getSeqNumber() >= p.getSeqNumber()) { //要插入的序列号比当前结点大
						if(!hasNext(p)) {   //如果下个结点为空
							p.next = fen;
							fen.pred = p;
							current = p.next;
							break;
						} else if(fen.getSeqNumber() >= p.next.getSeqNumber()){
							p = p.next;
//							current = p;
						} else if(fen.getSeqNumber() < p.next.getSeqNumber()) {
							p.next.pred = fen;
							fen.next = p.next;
							p.next = fen;
							fen.pred = p;
							current = fen;
							break;
						}
						
					} else {
						
						if(!hasPrev(p)) {   //前面结点为空 执行插入
							fen.next = p;
							p.pred = fen;
							current = p.pred;
							break;
						} else {     //否则前移
							p = p.pred;
//							current = p;
						}
					}
					
				
			}
			
		}
		
	}
	
	
	
}
