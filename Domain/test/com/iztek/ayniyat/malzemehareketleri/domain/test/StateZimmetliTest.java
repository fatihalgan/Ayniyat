package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;
import com.iztek.ayniyat.malzemehareketleri.domain.*;
import com.iztek.ayniyat.test.MainTestCase;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.Oda;

/**
 * @author Umit Akyol
 *
*/
public class StateZimmetliTest extends MainTestCase {
    public static void main(String[] args) {
        TestRunner.run(StateZimmetliTest.class);
    }
    
    public void testCreateStateZimmetli(){
        StateZimmetli stateDebited = new StateZimmetli();
        assertNotNull(stateDebited.getType());
        assertEquals(stateDebited.getType(),IMalzemeState.ZIMMETLI);
    }
	
	public void testGetNextState(){
        StateZimmetli stateDebited = new StateZimmetli();
		IMalzemeHareketi hareket = new MalzemeDevirHareketi();
		hareket.setHareketHedefi(new HareketYeri(new Oda()));
		assertEquals(stateDebited.getNextState(hareket).getType(),IMalzemeState.ZIMMETLI);
		hareket.setHareketHedefi(new HareketYeri(new Ambar()));
		assertEquals(stateDebited.getNextState(hareket).getType(),IMalzemeState.STOKTA);
		assertEquals(stateDebited.getNextState(new MalzemeBozukHareketi()).getType(),IMalzemeState.BOZUK);
		assertEquals(stateDebited.getNextState(new MalzemeZayiHareketi()).getType(),IMalzemeState.ZAYI);
	}
}
