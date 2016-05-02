package com.iztek.ayniyat.malzemehareketleri.domain.test;

import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeCikisFisi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeCikisFisiTest extends MainTestCase {
    
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(MalzemeCikisFisiTest.class);
    }
    
    public void testCreateMalzemeCikisFisi(){
        MalzemeCikisFisi cikisFisi = new MalzemeCikisFisi();
        assertNull(cikisFisi.getAlinisNedeni());
        assertNull(cikisFisi.getCikisSekli());
        assertNull(cikisFisi.getOwningMalzemeHareketi());
    }
    
    public void testGetAlinisNedeni(){
        assertEquals(malzemeCikisFisi1.getAlinisNedeni(),"Ýhtiyac");
    }
    
    public void testGetCikisSekli(){
        assertEquals(malzemeCikisFisi1.getCikisSekli(),MalzemeCikisFisi.ZIMMET);
    }
}
