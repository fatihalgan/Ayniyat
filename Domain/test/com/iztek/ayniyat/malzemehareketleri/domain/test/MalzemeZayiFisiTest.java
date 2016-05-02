package com.iztek.ayniyat.malzemehareketleri.domain.test;

import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeZayiFisi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeZayiFisiTest extends MainTestCase {
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(MalzemeZayiFisiTest.class);
	}
    
    public void testCreateMalzemeZayiFisi(){
        MalzemeZayiFisi zayiFisi = new MalzemeZayiFisi();
        assertNull(zayiFisi.getOwningMalzemeHareketi());
        assertNull(zayiFisi.getHareketBilgileri());
    }
    
    public void testGetZayiNedeni(){
        assertEquals(malzemeZayiFisi.getHareketBilgileri().getZayi_terkinNedeni(),"Tüpü patlamis");
    }
}
