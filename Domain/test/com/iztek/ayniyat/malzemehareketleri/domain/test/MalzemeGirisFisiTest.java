package com.iztek.ayniyat.malzemehareketleri.domain.test;

import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisFisi;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeGirisFisiTest extends MainTestCase {
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(MalzemeGirisFisiTest.class);
	}
    
    public void testCreateMalzemeGirisFisi(){
        MalzemeGirisFisi girisFisi = new MalzemeGirisFisi();
        assertNull(girisFisi.getAlindigiKurulus());
        assertNull(girisFisi.getOwningMalzemeHareketi());
        assertNull(girisFisi.getOnayVeren());
    }
    
    public void testGetAlindigiKurulus() {
        assertEquals(malzemeGirisFisi.getAlindigiKurulus(),"Dekor Mobilya A.S.");
    }
       
    public void testGetFaturaNo() {
        assertEquals(malzemeGirisFisi.getFaturaNo(),"12563250");
    }
   
    public void testGetFaturaTarihi() {
        assertEquals(malzemeGirisFisi.getFaturaTarihi(),tarih);
    }
    
    public void testGetGirisSekli() {
        assertEquals(malzemeGirisFisi.getGirisSekli(),MalzemeGirisFisi.SATINALMA);
    }
    
    public void testGetPaha(){
        assertNotNull(malzemeGirisFisi.getPaha());
    }
}
