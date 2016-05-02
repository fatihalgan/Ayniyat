package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.iztek.ayniyat.kategori.DemirbasMalzemeComparator;

/**
 * @author Umit Akyol
 *
*/
public class AbstractMalzemeHareketi implements IMalzemeHareketi{

	private Long id;    
    private int version;
    private AbstractMalzemeHareketFisi hareketFisi=null;
    private Set malzemeler = new TreeSet(new DemirbasMalzemeComparator());
    private HareketYeri hareketKaynagi=null;
    private HareketYeri hareketHedefi=null;
    private HareketYeri geciciSahip=null;
    private Date hareketTarihi = null;
     
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
    public void addMalzeme(IMalzeme malzeme){
    	if(malzeme == null) throw new IllegalStateException("Boþ Malzeme Eklenemez.");
    	malzeme.addMalzemeHareketi(this);
    	getMalzemeler().add(malzeme);
    }
    
    public void removeMalzeme(IMalzeme malzeme){
    	if(malzeme!=null){
    		getMalzemeler().remove(malzeme);
    		malzeme.removeMalzemeHareketi(this);
    	}
    }
    
    public Set getMalzemeler() {
		return malzemeler;
	}
	
	public void setMalzemeler(Set malzemeler) {
		this.malzemeler = malzemeler;
	}
	
    public AbstractMalzemeHareketFisi getHareketFisi() {
        return hareketFisi;
    }

    public void setHareketFisi(AbstractMalzemeHareketFisi malzemeHareketFisi) {
        this.hareketFisi = malzemeHareketFisi;
    }

    public boolean hasInMalzemeler(IMalzeme malzeme){
        return getMalzemeler().contains(malzeme);
    }
    
    public HareketYeri getGeciciSahip() {
        return geciciSahip;
    }
    
    public void setGeciciSahip(HareketYeri geciciSahip) {
        this.geciciSahip = geciciSahip;
    }
    
    public HareketYeri getHareketHedefi() {
        return hareketHedefi;
    }
    
    public void setHareketHedefi(HareketYeri hareketHedefi) {
        this.hareketHedefi = hareketHedefi;
    }
    
    public HareketYeri getHareketKaynagi() {
        return hareketKaynagi;
    }
    
    public void setHareketKaynagi(HareketYeri hareketKaynagi) {
        this.hareketKaynagi = hareketKaynagi;
    }
    
    public Date getHareketTarihi() {
        return hareketTarihi;
    }
    
    public void setHareketTarihi(Date hareketTarihi) {
        this.hareketTarihi = hareketTarihi;
    }

    public void hareketiGerceklestir() {}
    
    public String getType(){return "AbstractMalzemeHareketi";}
    
    public String toString(){
        return getHareketFisi().getBelgeNo().getBelgeNo().toString();
    }
}
