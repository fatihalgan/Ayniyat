package com.iztek.ayniyat.malzemehareketleri.domain;

/**
 * @author Umit Akyol
 */
public class MalzemeDevirFisi extends AbstractMalzemeHareketFisi {
   
    public MalzemeDevirFisi() {
        super();
    }
    
    public MalzemeDevirFisi(MalzemeDevirHareketi malzemeHareketi) {
        super();
        setOwningMalzemeHareketi(malzemeHareketi);
    }
}
