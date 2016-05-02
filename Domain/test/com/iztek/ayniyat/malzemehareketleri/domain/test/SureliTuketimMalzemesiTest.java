package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;

import com.iztek.ayniyat.malzemehareketleri.domain.SureliTuketimMalzemesi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class SureliTuketimMalzemesiTest extends MainTestCase {
    public static void main(String[] args) {
        TestRunner.run(SureliTuketimMalzemesiTest.class);
    }
    
    public void testCreateSureliTuketimMalzemesi(){
        SureliTuketimMalzemesi sureliTuketimMalzemesi = new SureliTuketimMalzemesi();
        assertNotNull(sureliTuketimMalzemesi.getMalzemeHareketleri());
    }
}
