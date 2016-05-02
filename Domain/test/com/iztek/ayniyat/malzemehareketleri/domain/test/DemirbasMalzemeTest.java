package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;

import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.test.MainTestCaseWithGeneratedNumbers;

/**
 * @author Umit Akyol
 *
 */
public class DemirbasMalzemeTest extends MainTestCaseWithGeneratedNumbers {
    public static void main(String []args){
        TestRunner.run(DemirbasMalzemeTest.class);
    }
    
    public void testCreateDemirbasMalzeme(){
    	DemirbasMalzeme demirbasMalzeme = new DemirbasMalzeme();
    	assertNull(demirbasMalzeme.getZimmetSahibi());
	}
	
	public void testGetDemirbasNo() {
		assertNotNull(monitor.getDemirbasNo());
		//assertEquals(monitor.getDemirbasNo().getSiraNo().intValue(),6);
		assertEquals(monitor.getDemirbasNo().getSiraNo(),"6");
		
	}
	
	public void testGetZimmetSahibi() {
		assertEquals(masa0.getZimmetSahibi(),cagdas);
	}
	
	public void testCopy()  {
		//TODO copy  methodu implement edildikten sonra yazýlacak
	}
	
	public void testEquals() {
		assertTrue(monitor.equals(monitor));
		DemirbasMalzeme newMasa = new DemirbasMalzeme();
		assertFalse(masa0.equals(newMasa));
	}
	
}
