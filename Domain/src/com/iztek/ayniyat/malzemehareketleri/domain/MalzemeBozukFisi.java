package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Date;

/**
 * @author Cagdas CIRIT
 */
public class MalzemeBozukFisi extends AbstractMalzemeHareketFisi{
	private String bozulmaNedeni = null;
	private Date bozulusTarihi = null;
	
	public MalzemeBozukFisi() {
        super();
    }
    
    public MalzemeBozukFisi(MalzemeBozukHareketi malzemeHareketi) {
        setOwningMalzemeHareketi(malzemeHareketi);
    }
    
	public String getBozulmaNedeni() {
		return bozulmaNedeni;
	}

	public void setBozulmaNedeni(String bozulmaNedeni) {
		this.bozulmaNedeni = bozulmaNedeni;
	}	
	
    public Date getBozulusTarihi() {
        return bozulusTarihi;
    }
    
    public void setBozulusTarihi(Date bozulusTarihi) {
        this.bozulusTarihi = bozulusTarihi;
    }
}
