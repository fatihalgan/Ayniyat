package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisHareketi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeGirisHareketiTest extends MainTestCase {

    public static void main(String[] args) {
        TestRunner.run(MalzemeGirisHareketiTest.class);
    }
    
    public void testCreateMalzemeGirisHareketi(){
        MalzemeGirisHareketi girisHareketi = new MalzemeGirisHareketi();
		assertNotNull(girisHareketi.getHareketFisi());
	}
	
	public void testGetGeciciSahip() {
		assertNull(malzemeGirisHareketi.getGeciciSahip());
	}
}
