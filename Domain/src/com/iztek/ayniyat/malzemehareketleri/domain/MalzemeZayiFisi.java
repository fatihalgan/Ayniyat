package com.iztek.ayniyat.malzemehareketleri.domain;

/**
 * @author Umit Akyol
 */
public class MalzemeZayiFisi extends AbstractMalzemeHareketFisi {
    
	private HareketBilgileri hareketBilgileri;
    
    public MalzemeZayiFisi() {
        super();
    }
    
    public MalzemeZayiFisi(MalzemeZayiHareketi malzemeHareketi) {
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
