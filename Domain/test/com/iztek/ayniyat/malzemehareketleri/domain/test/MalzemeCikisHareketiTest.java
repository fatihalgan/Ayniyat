package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;

import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeCikisHareketi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeCikisHareketiTest extends MainTestCase {

    public static void main(String[] args) {
        TestRunner.run(MalzemeCikisHareketiTest.class);
    }
    
    public void testCreateMalzemeGirisHareketi(){
        MalzemeCikisHareketi cikisHareketi = new MalzemeCikisHareketi();
		assertNotNull(cikisHareketi.getHareketFisi());
	}
	
}
