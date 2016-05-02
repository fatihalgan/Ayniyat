package com.iztek.ayniyat.malzemehareketleri.domain.test;

import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeDevirFisi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeDevirFisiTest extends MainTestCase {
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(MalzemeDevirFisiTest.class);
	}
    
    public void testCreateMalzemeDevirFisi(){
        MalzemeDevirFisi devirFisi = new MalzemeDevirFisi();
        assertNull(devirFisi.getOwningMalzemeHareketi());
    }
}
