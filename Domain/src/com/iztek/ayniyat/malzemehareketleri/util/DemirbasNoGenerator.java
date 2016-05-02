package com.iztek.ayniyat.malzemehareketleri.util;
/**
 * @author Cagdas CIRIT
 */
public class DemirbasNoGenerator {
	private Long id;
	private Long siraNo;
	public DemirbasNoGenerator(){}
	
	
	public DemirbasNoGenerator(Long siraNo){
		this.siraNo = siraNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	public Long getSiraNo() {
		return siraNo;
	}
	
	public void setSiraNo(Long siraNo) {
		this.siraNo = siraNo;
	}
}
