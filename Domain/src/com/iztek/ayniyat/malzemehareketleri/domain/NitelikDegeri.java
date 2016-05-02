package com.iztek.ayniyat.malzemehareketleri.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Cagdas CIRIT
 **/
public class NitelikDegeri {
	private String nitelikAdi;
	private String nitelikDegeri;
	private AbstractMalzeme owningMalzeme;
	
	public NitelikDegeri(){	}
	
	public NitelikDegeri(String ad, String deger){	
		nitelikAdi = ad;
		nitelikDegeri = deger;
	}
	
    public boolean equals(final Object other) {
		if (this == other)
			return true;
		if (!(other instanceof NitelikDegeri))
			return false;
		NitelikDegeri castOther = (NitelikDegeri) other;
	    
		return new EqualsBuilder().append(getNitelikAdi(), castOther.getNitelikAdi()).append(getNitelikDegeri(), castOther.getNitelikDegeri()).isEquals();
    }
    
    public int hashCode() {  
    	String owningMalzemeTanimi = null;   	
    	if (getOwningMalzeme()!=null)
    		owningMalzemeTanimi = getOwningMalzeme().getMalzemeTanimi().getNodeValue();
    	
        return new HashCodeBuilder(-248401167, 1984878581).append(getNitelikAdi()).append(owningMalzemeTanimi).toHashCode();
    }
	
    public String toString() {
        return getNitelikAdi();
    }
	
	public String getNitelikAdi() {
		return nitelikAdi;
	}
	
	public void setNitelikAdi(String nitelikAdi) {
		this.nitelikAdi = nitelikAdi;
	}
	
	public String getNitelikDegeri() {
		return nitelikDegeri;
	}
	public void setNitelikDegeri(String nitelikDegeri) {
		this.nitelikDegeri = nitelikDegeri;
	}
	
	public AbstractMalzeme getOwningMalzeme() {
		return owningMalzeme;
	}
	
	public void setOwningMalzeme(AbstractMalzeme owningMalzeme) {
		this.owningMalzeme = owningMalzeme;
	}
}
