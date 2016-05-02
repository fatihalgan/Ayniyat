package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;

import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeDevirHareketi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeDevirHareketiTest extends MainTestCase {

    public static void main(String[] args) {
        TestRunner.run(MalzemeDevirHareketiTest.class);
    }
    
    public void testCreateMalzemeDevirHareketi(){
        MalzemeDevirHareketi devirHareketi = new MalzemeDevirHareketi();
		assertNotNull(devirHareketi.getHareketFisi());
	}
	
	public void testGetGeciciSahip() {
		assertNull(malzemeDevirHareketi0.getGeciciSahip());
	}
}
