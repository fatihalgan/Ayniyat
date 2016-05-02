package com.iztek.ayniyat.malzemehareketleri.domain.test;

import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class NitelikDegeriTest extends MainTestCase {
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(NitelikDegeriTest.class);
	}
    public void testCreateNitelikDegeri(){
        NitelikDegeri nitelikDegeri = new NitelikDegeri("Marka","Philips");
        assertEquals(nitelikDegeri.getNitelikAdi(),"Marka");
        assertEquals(nitelikDegeri.getNitelikDegeri(),"Philips");
    }
    
    public void testGetNitelikAdi() {
		assertEquals(masaBoyut.getNitelikAdi(),"Boyut");
	}
	
	public void testGetNitelikDegeri() {
		assertEquals(mobilyaMarka.getNitelikDegeri(),"Dekor");
	}
	
	public void testGetOwningMalzeme() {
		assertEquals(monitorBoyut.getOwningMalzeme(),monitor);
	}
	
}
