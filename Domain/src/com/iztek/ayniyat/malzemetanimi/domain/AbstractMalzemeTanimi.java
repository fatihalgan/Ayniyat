package com.iztek.ayniyat.malzemetanimi.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.NitelikComparator;
import com.iztek.ayniyat.kategori.service.KategoriMergeStateManager;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstractMalzemeTanimi extends Kategori implements IMalzemeTanimi {

    public static final String ADET = "Adet";
    public static final String KG = "Kilogram";
    public static final String LT = "Litre";
    public static final String BIDON = "Bidon";
    public static final String CIFT = "Çift";
    
    private String birim;
    private Set malzemeler = new HashSet();
    private Set nitelikTanimlari = new TreeSet(new NitelikComparator());
    
    protected AbstractMalzemeTanimi() {
    	super();
    }
    
    protected AbstractMalzemeTanimi( String tanim, String kod, String birim) {
        super(tanim,kod);
        this.birim = birim;
    }
    
    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#addKategori(com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir)
     */
    public void addKategori(IKategorilendirilebilir kategori) {
        throw new IllegalStateException("Malzeme Tanýmýna Kategori Eklenemez..");
    }

    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#removeKategori(com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir)
     */
    public void removeKategori(IKategorilendirilebilir kategori) {
        throw new IllegalStateException("Malzeme Tanýmlarý Alt Kategoriler Ýçeremez..");

    }

    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#getAnaKategori()
     */
    public IKategorilendirilebilir getAnaKategori() {
        return anaKategori;
    }

    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#setAnaKategori(com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir)
     */
    public void setAnaKategori(IKategorilendirilebilir kategori) {
        this.anaKategori = kategori;
    }

      
    /**
     * @return Returns the birim.
     */
    public String getBirim() {
        return birim;
    }
    /**
     * @param birim The birim to set.
     */
    public void setBirim(String birim) {
        this.birim = birim;
    }
    
        
    public boolean hasInChildren(IKategorilendirilebilir kategori) {
        //Malzeme tipinin alt kategorisi olamaz.
        return false;
    }
    
    public IKategorilendirilebilir getChildByTanim(String tanim) {
        //Malzeme tipinin alt kategorisi olamaz.
        return null;
    }
    
    public IKategorilendirilebilir getChildByKod(String kod) {
        //Malzeme tipinin alt kategorisi olamaz.
        return null;
    }
    
    public String toString(){
        return getNodeValue();
    }

    /**
     * @return Returns the nitelikTanimlari.
     */
    public Set getNitelikTanimlari() {
        return nitelikTanimlari;
    }
    /**
     * @param nitelikTanimlari The nitelikTanimlari to set.
     */
    public void setNitelikTanimlari(Set nitelikTanimlari) {
        this.nitelikTanimlari = nitelikTanimlari;
    }
    
    public void addNitelikTanimi(NitelikTanimi nitelikTanimi) {
        if(nitelikTanimi == null) 
			throw new IllegalStateException("Boþ Nitelik Tanýmý Eklenemez.");
		nitelikTanimi.setOwningMalzemeTanimi(this);
		getNitelikTanimlari().add(nitelikTanimi);
    }

    public void removeNitelikTanimi(String nitelikTanimi) {
        Iterator it = getNitelikTanimlari().iterator();
        while(it.hasNext()) {
            NitelikTanimi nt = (NitelikTanimi)it.next();
            if(nitelikTanimi.trim().equals(nt.getNitelikAdi().trim())) {
                it.remove();
                nt.setOwningMalzemeTanimi(null);
                break;
            }
       }
    }
    
    public void removeNitelikTanimi(NitelikTanimi nitelikTanimi) {
        if (nitelikTanimi!=null){
            getNitelikTanimlari().remove(nitelikTanimi);
            nitelikTanimi.setOwningMalzemeTanimi(null);
        }
    }
    
    public NitelikTanimi getNitelikTanimi(String nitelikTanimi) {
        Iterator it = getNitelikTanimlari().iterator();
        while(it.hasNext()) {
            NitelikTanimi nt = (NitelikTanimi)it.next();
            if(nitelikTanimi.trim().equals(nt.getNitelikAdi().trim())) return nt;
        }
        return null;
    }
    
    public boolean hasNitelikTanimi(String nitelikTanimi) {
        Iterator it = getNitelikTanimlari().iterator();
        while(it.hasNext()) {
            NitelikTanimi nt = (NitelikTanimi)it.next();
            if(nitelikTanimi.trim().equals(nt.getNitelikAdi().trim())) return true;
        }
        return false;
    }

	public boolean equals(final Object other) {
		if (this == other)
			return true;
		if (!(other instanceof AbstractMalzemeTanimi))
			return false;
		AbstractMalzemeTanimi castOther = (AbstractMalzemeTanimi) other;
		return new EqualsBuilder().appendSuper(super.equals(other))
			.append(getBirim(), castOther.getBirim()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(793645183, -698430245).appendSuper(
				super.hashCode()).append(getBirim()).toHashCode();
	}
	
	/**
	 * @author Cagdas CIRIT
	 */
	public void retainNitelikTanimlari(Collection tutulacakNitelikler){
	    Iterator dolasilacakNitelikler = getNitelikTanimlari().iterator();

	    while (dolasilacakNitelikler.hasNext()) {
            NitelikTanimi nitelik = (NitelikTanimi) dolasilacakNitelikler.next();
            if (!tutulacakNitelikler.contains(nitelik)){ //do not retain
            	dolasilacakNitelikler.remove();               
            }
        }
	}
	
	/**
	 * @author Umit Akyol
	 */
    public void mergeKategori(IKategorilendirilebilir kategori,KategoriMergeStateManager stateManager) {
        throw new IllegalStateException("Hedef "+getNodeValue()+" ile ayný tanýma ve koda sahip bir malzeme tanýmý içeriyor!");
    }
    /**
     * @return Returns the malzemeler.
     */
    public Set getMalzemeler() {
        return malzemeler;
    }
    /**
     * @param malzemeler The malzemeler to set.
     */
    private void setMalzemeler(Set malzemeler) {
        this.malzemeler = malzemeler;
    }
    
    public boolean isDeletable() {
        if(getMalzemeler().size() != 0) return false;
        return true;
    }
    
    
}
