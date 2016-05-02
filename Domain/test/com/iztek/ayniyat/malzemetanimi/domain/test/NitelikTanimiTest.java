/*
 * Created on 10.Nis.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.malzemetanimi.domain.test;


import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NitelikTanimiTest extends MainTestCase{

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(NitelikTanimiTest.class);
    }
	
    public void testCreateNitelikTanimi() {
        NitelikTanimi nitelikTanimi = new NitelikTanimi("Boyut");
        assertEquals(nitelikTanimi.getNitelikAdi(), "Boyut");
    }
    
    public void testHasNitelikTanimi() {
        assertTrue(monitorTanimi.hasNitelikTanimi("Boyut"));
    }
    
    public void testAddNitelikTanimi() {
        monitorTanimi.addNitelikTanimi(new NitelikTanimi("Çözünürlük"));
        assertTrue(monitorTanimi.hasNitelikTanimi("Çözünürlük"));
    }
    
    public void testGetNitelikTanimi() {
        NitelikTanimi nt = monitorTanimi.getNitelikTanimi("Boyut");
        assertNotNull(nt);
    }
        
    public void testRemoveNitelikTanimi() {
        monitorTanimi.removeNitelikTanimi("Boyut");
		assertFalse(monitorTanimi.hasNitelikTanimi("Boyut"));       
    }
    
}
