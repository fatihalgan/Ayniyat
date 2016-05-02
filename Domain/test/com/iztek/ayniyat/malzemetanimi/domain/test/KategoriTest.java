package com.iztek.ayniyat.malzemetanimi.domain.test;

import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KategoriTest extends MainTestCase {

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(KategoriTest.class);
    }

    public void testCreateKategori() {
        Kategori kategoriA = new Kategori("Kategori","01");
        assertEquals(kategoriA.getTanim(), "Kategori");
    }
    
    public void testCreateDemirbasMalzemeTanimi() {
        DemirbasMalzemeTanimi tanim = (DemirbasMalzemeTanimi)monitorTanimi;
        assertEquals(tanim.getTanim(), "Monitör");
        assertEquals(tanim.getBirim(), AbstractMalzemeTanimi.ADET);
    }
    
    public void testEquals(){
        assertTrue(bilgisayar2.equals(bilgisayar2));
        assertFalse(bilgisayar2.equals(bilgisayar3));
    }
    
}
