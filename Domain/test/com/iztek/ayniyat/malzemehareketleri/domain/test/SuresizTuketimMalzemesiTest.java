package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;

import com.iztek.ayniyat.malzemehareketleri.domain.SuresizTuketimMalzemesi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class SuresizTuketimMalzemesiTest extends MainTestCase {
    public static void main(String[] args) {
        TestRunner.run(SuresizTuketimMalzemesiTest.class);
    }
    
    public void testCreateSuresizTuketimMalzemesi(){
        SuresizTuketimMalzemesi tuketimMalzemesi = new SuresizTuketimMalzemesi();
        assertNotNull(tuketimMalzemesi.getMalzemeHareketleri());
    }
}
