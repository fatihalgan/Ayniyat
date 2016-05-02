package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.iztek.ayniyat.kategori.NitelikDegeriComparator;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;

/**
 * @author Cagdas CIRIT 
 **/
public abstract class AbstractMalzeme implements IMalzeme{
    private Long id;    
    private int version;
    private IMalzemeState state;
    private IMalzemeTanimi malzemeTanimi;
    private Set nitelikDegerleri = new TreeSet(new NitelikDegeriComparator());
    private Set malzemeHareketleri = new HashSet();
	private MalzemePahasi paha;
	private Set demirbasNolari=new HashSet();
     
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
	
	public IMalzemeTanimi getMalzemeTanimi() {
		return malzemeTanimi;
	}
	
	public void setMalzemeTanimi(IMalzemeTanimi malzemeTanimi) {
		this.malzemeTanimi = malzemeTanimi;
	}
	
	public Set getNitelikDegerleri() {
		return nitelikDegerleri;
	}
	
	public void setNitelikDegerleri(Set nitelikDegerleri) {
		this.nitelikDegerleri = nitelikDegerleri;
	}
	
    public void addNitelikDegeri(NitelikDegeri nitelikDegeri) {
        if(nitelikDegeri == null) 
			throw new IllegalStateException("Boþ Nitelik Tanýmý Eklenemez.");
		nitelikDegeri.setOwningMalzeme(this);
		getNitelikDegerleri().add(nitelikDegeri);
    }
    
    public void removeNitelikDegeri(String nitelikDegeri) {
        Iterator it = getNitelikDegerleri().iterator();
        while(it.hasNext()) {
            NitelikDegeri nd = (NitelikDegeri)it.next();
            if(nitelikDegeri.trim().equals(nd.getNitelikAdi().trim())) {
                it.remove();
                nd.setOwningMalzeme(null);
                break;
            }
       }
    }
    
    public void removeNitelikDegeri(NitelikDegeri nitelikDegeri) {
        if (nitelikDegeri!=null){
            getNitelikDegerleri().remove(nitelikDegeri);
            nitelikDegeri.setOwningMalzeme(null);
        }
    }
    
    public NitelikDegeri getNitelikDegeri(String nitelikDegeri) {
        Iterator it = getNitelikDegerleri().iterator();
        while(it.hasNext()) {
            NitelikDegeri nd = (NitelikDegeri)it.next();
            if(nitelikDegeri.trim().equals(nd.getNitelikAdi().trim())) return nd;
        }
        return null;
    }
    
    public boolean hasNitelikDegeri(String nitelikDegeri) {
        Iterator it = getNitelikDegerleri().iterator();
        while(it.hasNext()) {
            NitelikDegeri nd = (NitelikDegeri)it.next();
            if(nitelikDegeri.trim().equals(nd.getNitelikAdi().trim())) return true;
        }
        return false;
    }
	
	public IMalzemeState getState() {
		return state;
	}
	
	public void setState(IMalzemeState state) {
		this.state = state;
	}
	
	public Set getMalzemeHareketleri() {
		return malzemeHareketleri;
	}
			
	public void setMalzemeHareketleri(Set malzemeHareketleri) {
		this.malzemeHareketleri = malzemeHareketleri;
	}
	
	public MalzemePahasi getPaha() {
		return paha;
	}

	public void setPaha(MalzemePahasi paha) {
		this.paha = paha;
	}

	public void addMalzemeHareketi(IMalzemeHareketi malzemeHareketi){
		if(malzemeHareketi == null) throw new IllegalStateException("Boþ Malzeme Eklenemez.");
		getMalzemeHareketleri().add(malzemeHareketi);
		setNextState(malzemeHareketi);
	}
	
	public void removeMalzemeHareketi(IMalzemeHareketi malzemeHareketi){
		if (malzemeHareketi!=null){
			getMalzemeHareketleri().remove(malzemeHareketi);
		}
	}
	
	private void setNextState(IMalzemeHareketi hareket){
		if(getState()==null)
			setState(new StateStokta());//ilk state her zaman  StateStokta olmalýdýr!!
		else
			setState(getState().getNextState(hareket));
	}
	
	public boolean equals(final Object other) {
		if (this == other)
			return true;
		if (!(other instanceof AbstractMalzeme))
			return false;
		AbstractMalzeme castOther = (AbstractMalzeme) other;
		
		if(this.malzemeTanimi==null || castOther.getMalzemeTanimi()==null)
			return false;	
		
		if (new EqualsBuilder().append(this.malzemeTanimi.getNodeValue(),castOther.getMalzemeTanimi().getNodeValue()).isEquals()){
			if(getNitelikDegerleri().equals(castOther.getNitelikDegerleri())){//nitelik degerleri treeSetleri ayný boyutta ve nitelik Adlarý ayný, nitelik degerleride aynýmý kontrol edilmesi gerek (sette)
				Iterator thisIter = getNitelikDegerleri().iterator();
				Iterator castIter = castOther.getNitelikDegerleri().iterator();
				while (thisIter.hasNext()) {
					NitelikDegeri element0 = (NitelikDegeri) thisIter.next();
					NitelikDegeri element1 = (NitelikDegeri) castIter.next();
					if(!element0.getNitelikDegeri().equals(element1.getNitelikDegeri()))
						return false;	
				}
				return true;
			}				
		}			
		return false;
	}
	
    public int hashCode() {
        String malzemeTanimi = null;
        if (getMalzemeTanimi()!=null)
        	malzemeTanimi = getMalzemeTanimi().getNodeValue();
        HashCodeBuilder hasher = new HashCodeBuilder(1598786547, -16976425).append(malzemeTanimi);
        Iterator iter = getNitelikDegerleri().iterator();
        while (iter.hasNext()) {
			NitelikDegeri element = (NitelikDegeri) iter.next();
			hasher.append(element.getNitelikAdi());
			hasher.append(element.getNitelikDegeri());
		}
        return hasher.toHashCode();
    }
    
    public boolean hasInMalzemeHareketleri(IMalzemeHareketi malzemeHareketi){
        return getMalzemeHareketleri().contains(malzemeHareketi);
    }
    
    public boolean hasInNitelikDegerleri(NitelikDegeri nitelikDegeri){
        return getNitelikDegerleri().contains(nitelikDegeri);
    }
    
    public String toString(){
    	return getMalzemeTanimi().getNodeValue();
    }

	public Set getDemirbasNolari() {
		return demirbasNolari;
	}

	public void setDemirbasNolari(Set demirbasNolari) {
		this.demirbasNolari = demirbasNolari;
	}
	public void addDemirbasNo(String demirbasNo){
		getDemirbasNolari().add(demirbasNo);
	}
}
