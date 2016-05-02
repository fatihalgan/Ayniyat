package com.iztek.ayniyat.malzemehareketleri.domain;

/**
 * @author Umit Akyol
 */
public class MalzemeTerkinFisi extends AbstractMalzemeHareketFisi {
    
	private HareketBilgileri hareketBilgileri;
    
    public MalzemeTerkinFisi() {
        super();
    }
    
    public MalzemeTerkinFisi(MalzemeTerkinHareketi malzemeHareketi) {
        super();
        setOwningMalzemeHareketi(malzemeHareketi);
    }
    
    public HareketBilgileri getHareketBilgileri() {
        return hareketBilgileri;
    }
    
    public void setHareketBilgileri(HareketBilgileri hareketBilgileri) {
        this.hareketBilgileri = hareketBilgileri;
    }
}
