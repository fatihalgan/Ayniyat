package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;

import com.iztek.ayniyat.malzemehareketleri.domain.TuketimMalzemesi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class TuketimMalzemesiTest extends MainTestCase {
    public static void main(String []args){
        TestRunner.run(TuketimMalzemesiTest.class);
    }
    
    public void testCreateTuketimMalzemesi(){
        TuketimMalzemesi tuketimMalzemesi = new TuketimMalzemesi();
        assertNotNull(tuketimMalzemesi.getMalzemeHareketleri());
    }
}
