package com.iztek.ayniyat.malzemehareketleri.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Cagdas CIRIT
 */
public class DemirbasNo {
	private String siraNo; 
	
	public DemirbasNo(){}
	
	
	public DemirbasNo(String siraNo){
		this.siraNo = siraNo;
	}

	public String getSiraNo() {
		return siraNo;
	}

	public void setSiraNo(String siraNo) {
		this.siraNo = siraNo;
	}

	
	public boolean equals(final Object other){
		if (this == other)
			return true;
		if (!(other instanceof DemirbasNo))
			return false;
		DemirbasNo castOther = (DemirbasNo) other;
		return (getSiraNo().equals(castOther.getSiraNo()));
	}
	
    public int hashCode() {
		return new HashCodeBuilder(-11241015, -1392753393).append(getSiraNo()).toHashCode();
    }
    
    public String toString(){
    	return getSiraNo().toString();
    }
}
