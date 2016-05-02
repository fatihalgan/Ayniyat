package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;

import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeBozukHareketi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeBozukHareketiTest extends MainTestCase {

    public static void main(String[] args) {
        TestRunner.run(MalzemeBozukHareketiTest.class);
    }
    
    public void testCreateMalzemeBozukHareketi(){
        MalzemeBozukHareketi bozukHareketi = new MalzemeBozukHareketi();
		assertNotNull(bozukHareketi.getHareketFisi());
	}
	
	public void testGetGeciciSahip() {
		assertNull(malzemeBozukHareketi.getGeciciSahip());
	}
}
