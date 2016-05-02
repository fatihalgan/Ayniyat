package com.iztek.ayniyat.malzemehareketleri.util;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Cagdas CIRIT
 */
public class BelgeNoGenerator {
	private Long id;
	private Long belgeNo;
	
	public BelgeNoGenerator(){}
	
	public BelgeNoGenerator(Long belgeNo){
		this.belgeNo = belgeNo;
	}

	public Long getBelgeNo() {
		return belgeNo;
	}

	public void setBelgeNo(Long belgeNo) {
		this.belgeNo = belgeNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean equals(final Object other){
		if (this == other)
			return true;
		if (!(other instanceof BelgeNoGenerator))
			return false;
		BelgeNoGenerator castOther = (BelgeNoGenerator) other;
		return getBelgeNo().longValue() == castOther.getBelgeNo().longValue();
	}
	
	public int hashCode() {
		return new HashCodeBuilder(-11241015, -1392753393).append(getBelgeNo()).toHashCode();
    }
}
