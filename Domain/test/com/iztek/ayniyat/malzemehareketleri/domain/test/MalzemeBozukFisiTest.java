package com.iztek.ayniyat.malzemehareketleri.domain.test;

import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeBozukFisi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeBozukFisiTest extends MainTestCase {
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(MalzemeBozukFisiTest.class);
	}
    
    public void testCreateMalzemeBozukFisi(){
        MalzemeBozukFisi bozukFisi = new MalzemeBozukFisi();
        assertNull(bozukFisi.getOwningMalzemeHareketi());
        assertNull(bozukFisi.getBozulmaNedeni());
    }
    
    public void testGetBozulmaNedeni(){
        assertEquals(malzemeBozukFisi.getBozulmaNedeni(),"Masadan düstü");
    }
}
