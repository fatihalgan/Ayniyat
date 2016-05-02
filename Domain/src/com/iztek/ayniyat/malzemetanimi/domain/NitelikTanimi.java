package com.iztek.ayniyat.malzemetanimi.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.iztek.ayniyat.kategori.IKopyalanabilir;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NitelikTanimi implements IKopyalanabilir{
    
    private String nitelikAdi;
    private IMalzemeTanimi owningMalzemeTanimi;
        
    public NitelikTanimi() {
    	super();
    }
    
    
    public NitelikTanimi(String nitelikAdi) {
        this.nitelikAdi = nitelikAdi;
    }
    
    
       /**
     * @param nitelikAdi The nitelikAdi to set.
     */
    public void setNitelikAdi(String nitelikAdi) {
        this.nitelikAdi = nitelikAdi;
    }
    
    /**
     * @return Returns the nitelikAdi.
     */
    public String getNitelikAdi() {
        return nitelikAdi;
    }
    
    /**
     * @return Returns the owningMalzemeTanimi.
     */
    public IMalzemeTanimi getOwningMalzemeTanimi() {
        return owningMalzemeTanimi;
    }
    /**
     * @param owningMalzemeTanimi The owningMalzemeTanimi to set.
     */
    public void setOwningMalzemeTanimi(IMalzemeTanimi owningMalzemeTanimi) {
        this.owningMalzemeTanimi = owningMalzemeTanimi;
    }
    
	/**
	 * @author Cagdas CIRIT
	 */
    public boolean equals(final Object other) {

		if (this == other)
			return true;
		if (!(other instanceof NitelikTanimi))
			return false;
		NitelikTanimi castOther = (NitelikTanimi) other;
	    
		return new EqualsBuilder().append(getNitelikAdi(), castOther.getNitelikAdi()).isEquals();
    }

	/**
	 * @author Cagdas CIRIT
	 */
    public int hashCode() {     	
        return new HashCodeBuilder(-248401167, 1984878581).append(getNitelikAdi()).toHashCode();
    }

    public String toString() {
        return getNitelikAdi();
    }

	/**
     * @author Umit Akyol
     *
     */
	public Object copy() {
		return new NitelikTanimi(nitelikAdi);
	}
}
