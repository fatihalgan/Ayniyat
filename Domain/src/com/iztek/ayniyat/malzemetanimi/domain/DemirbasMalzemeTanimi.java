package com.iztek.ayniyat.malzemetanimi.domain;

import java.util.Iterator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DemirbasMalzemeTanimi extends AbstractMalzemeTanimi {

    public DemirbasMalzemeTanimi( String tanim,String kod, String birim) {
        super(tanim,kod, birim);
    }
     
    public DemirbasMalzemeTanimi() {
    	super();
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(final Object other) {
        if (this == other)
            return true;
        if (!(other instanceof DemirbasMalzemeTanimi))
            return false;
        DemirbasMalzemeTanimi castOther = (DemirbasMalzemeTanimi) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).isEquals();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-752116893, 1639478737).appendSuper(
                super.hashCode()).toHashCode();
    }

    /**
     * @author Umit Akyol
     *
     */
	public Object copy() {
		IMalzemeTanimi newInstance = new DemirbasMalzemeTanimi(getTanim(),getKod(),getBirim());
		Iterator iterator = getNitelikTanimlari().iterator();
		while(iterator.hasNext()){
			NitelikTanimi nitelikTanimi = (NitelikTanimi)iterator.next();
			newInstance.addNitelikTanimi(new NitelikTanimi(nitelikTanimi.getNitelikAdi()));
		}
		return newInstance;
	}
	
	/**
     * @author Umit Akyol
    */
    public boolean isKopyasiAlinabilir() {
        return true; 
    }
    
}
