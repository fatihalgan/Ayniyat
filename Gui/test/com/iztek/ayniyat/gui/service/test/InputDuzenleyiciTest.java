package com.iztek.ayniyat.gui.service.test;

import junit.framework.TestCase;
import junit.swingui.TestRunner;
import com.iztek.ayniyat.gui.service.InputDuzenleyici;
/**
 * @author Umit Akyol
 *
*/
public class InputDuzenleyiciTest extends TestCase {

    public static void main(String[] args) {
        TestRunner.run(InputDuzenleyiciTest.class);
    }
    
    public void testKelimeninBasHarfleriniDuzenle(){
        assertEquals(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle("muhendislik"),"Muhendislik");
        assertEquals(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle("muHenDisLik"),"Muhendislik");
    }
    
    public void testKelimelerinBasHarfleriniDuzenle(){
        assertEquals(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle("muhendislik fakultesi"),"Muhendislik Fakultesi");
        assertEquals(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle("muHendiSlik faKUltEsi"),"Muhendislik Fakultesi");
    }
}
