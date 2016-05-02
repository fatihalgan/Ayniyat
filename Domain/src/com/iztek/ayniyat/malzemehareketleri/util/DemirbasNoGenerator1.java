package com.iztek.ayniyat.malzemehareketleri.util;
/**
 * @author Cagdas CIRIT
 */
public class DemirbasNoGenerator1 {
	private Long id;
	private Long siraNo;
	private String altSiraNo;
	
	public DemirbasNoGenerator1(){}
	
	public DemirbasNoGenerator1(Long siraNo, String altSiraNo){
		this.siraNo = siraNo;
		this.altSiraNo = altSiraNo;
	}
	
	public DemirbasNoGenerator1(Long siraNo){
		this.siraNo = siraNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAltSiraNo() {
		return altSiraNo;
	}
	
	public void setAltSiraNo(String altSiraNo) {
		this.altSiraNo = altSiraNo;
	}
	
	public Long getSiraNo() {
		return siraNo;
	}
	
	public void setSiraNo(Long siraNo) {
		this.siraNo = siraNo;
	}
}
