package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.test.MainTestCaseWithGeneratedNumbers;

/**
 * @author Umit Akyol
 *
 */
public class AbstractMalzemeHareketiTest extends MainTestCaseWithGeneratedNumbers {

    public static void main(String[] args) {
        TestRunner.run(AbstractMalzemeHareketiTest.class);
    }
    
    public void testGetMalzemeler(){
        assertNotNull(malzemeGirisHareketi.getMalzemeler());
        assertTrue(malzemeGirisHareketi.getMalzemeler().size()==7);
        assertTrue(malzemeGirisHareketi.getMalzemeler().contains(dolap));
    }
    
    public void testAddNullMalzeme(){
        try{
            DemirbasMalzeme malzeme = null;
            malzemeCikisHareketi1.addMalzeme(malzeme);
            fail("Malzeme hareketine null malzeme IllegalStateException fýrlatýr..");
        }catch(IllegalStateException e){}
    }
    
    public void testAddMalzeme(){
        malzemeCikisHareketi1.addMalzeme(dolap);
        assertTrue(malzemeCikisHareketi1.hasInMalzemeler(dolap));
        assertTrue(malzemeCikisHareketi1.hasInMalzemeler(masa0));
    }
    
    public void testRemoveMalzeme(){
        malzemeGirisHareketi.removeMalzeme(masa0);
        assertFalse(malzemeGirisHareketi.hasInMalzemeler(masa0));
        assertFalse(masa0.hasInMalzemeHareketleri(malzemeGirisHareketi));
    }
    
    public void testGetHareketFisi(){
        assertNotNull(malzemeGirisHareketi.getHareketFisi());
        assertEquals(malzemeGirisHareketi.getHareketFisi(),malzemeGirisFisi);
    }
        
	public void testHasInMalzemeler(){
	    assertTrue(malzemeGirisHareketi.hasInMalzemeler(dolap));
	    assertFalse(malzemeDevirHareketi0.hasInMalzemeler(dolap));
    }
    
    public void testGetGeciciSahip() {
    	assertEquals(malzemeCikisHareketi3.getGeciciSahip(),geciciSahipHareketYeri);
    }
    
    public void testGetHareketHedefi() {
       assertEquals(malzemeCikisHareketi2.getHareketHedefi(),umitHareketYeri);
       assertEquals(malzemeGirisHareketi.getHareketHedefi(),rektorlukHareketYeri);
    }
    
    public void testGetHareketKaynagi() {
       assertNull(malzemeGirisHareketi.getHareketKaynagi());
       assertEquals(malzemeCikisHareketi1.getHareketKaynagi(),rektorlukHareketYeri);
    }
    
    public void testGetHareketTarihi() {
        assertEquals(malzemeGirisHareketi.getHareketTarihi(),tarih);
    }
    
}
