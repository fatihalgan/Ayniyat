package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeZayiHareketi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeZayiHareketiTest extends MainTestCase {

    public static void main(String[] args) {
        TestRunner.run(MalzemeZayiHareketiTest.class);
    }
    
    public void testCreateMalzemeZayiHareketi(){
        MalzemeZayiHareketi zayiHareketi = new MalzemeZayiHareketi();
		assertNotNull(zayiHareketi.getHareketFisi());
	}
	
	public void testGetGeciciSahip() {
		assertNull(malzemeZayiHareketi.getGeciciSahip());
	}
}
