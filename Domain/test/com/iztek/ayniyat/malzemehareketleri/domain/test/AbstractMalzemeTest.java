package com.iztek.ayniyat.malzemehareketleri.domain.test;

import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.test.MainTestCaseWithGeneratedNumbers;

/**
 * @author Umit Akyol
 *
 */
public class AbstractMalzemeTest extends MainTestCaseWithGeneratedNumbers {
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(AbstractMalzemeTest.class);
	}
    
    public void testAddNitelikDegeri() {
        assertTrue(masa0.hasInNitelikDegerleri(masaBoyut));
        assertTrue(masa1.hasInNitelikDegerleri(masaMarka));
    }
    
    public void testRemoveNitelikDegeri() {
        masa0.removeNitelikDegeri(masaBoyut);
        assertFalse(masa0.hasInNitelikDegerleri(masaBoyut));
    }
    
    public void testRemoveNitelikDegeriByNitelikTanimi() {
        dolap.removeNitelikDegeri("Dekor");
        assertFalse(dolap.hasNitelikDegeri("Dekor"));
    }
    public void testGetMalzemeTanimi() {
		assertEquals(masa0.getMalzemeTanimi(),masaTanimi);
	}
	
    public void testGetNitelikDegerleri() {
		assertNotNull(masa0.getNitelikDegerleri());
		assertEquals(masa0.getNitelikDegerleri().size(),1);
	}
	
    public void testHasNitelikDegeri() {
        assertTrue(dolap.hasNitelikDegeri("Marka"));
        assertFalse(masa0.hasNitelikDegeri("Marka"));
    }
    
    public void testAddMalzemeHareketi(){
		assertTrue(dolap.hasInMalzemeHareketleri(malzemeGirisHareketi));
		assertTrue(malzemeGirisHareketi.hasInMalzemeler(dolap));
	}
	
	public void testRemoveMalzemeHareketi(){
		masa0.removeMalzemeHareketi(malzemeGirisHareketi);
		assertFalse(masa0.hasInMalzemeHareketleri(malzemeGirisHareketi));
	}
        
    public void testGetNitelikDegeri() {
        assertNull(masa0.getNitelikDegeri("Marka"));
        assertNotNull(masa0.getNitelikDegeri("Boyut"));
    }
    
    public void testGetState() {
		assertEquals(masa0.getState().getType(),IMalzemeState.STOKTA);
	}
			
	public void testGetMalzemeHareketleri() {
		assertNotNull(masa0.getMalzemeHareketleri());
		assertEquals(masa0.getMalzemeHareketleri().size(),3);
	}
	
}
