package com.model;

/**
 * 将ip对和五元组进行关联
 * @author chai
 *
 */
public class KV {
	private IPpair ipp;
//	public FiveElementNodeLinkedList fenll;
	public DoubleFiveElementNodeLinkedList fenll;
	
	public KV(IPpair ipp, DoubleFiveElementNodeLinkedList fenll) {
		super();
		this.ipp = ipp;
		this.fenll = fenll;
	}
	
//	public KV(IPpair ipp, FiveElementNodeLinkedList fenll) {
//		super();
//		this.ipp = ipp;
//		this.fenll = fenll;
//	}
	public IPpair getIpp() {
		return ipp;
	}
	public void setIpp(IPpair ipp) {
		this.ipp = ipp;
	}
//	public FiveElementNodeLinkedList getFenll() {
//		return fenll;
//	}
//	public void setFenll(FiveElementNodeLinkedList fenll) {
//		this.fenll = fenll;
//	}

	public DoubleFiveElementNodeLinkedList getFenll() {
		return fenll;
	}

	public void setFenll(DoubleFiveElementNodeLinkedList fenll) {
		this.fenll = fenll;
	}
	
	
	
	
	
	

}
