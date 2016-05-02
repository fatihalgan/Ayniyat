package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;
import com.iztek.ayniyat.malzemehareketleri.domain.HareketYeri;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeBozukHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeCikisHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeZayiHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.StateStokta;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class StateStoktaTest extends MainTestCase {

    public static void main(String[] args) {
        TestRunner.run(StateStoktaTest.class);
    }
    
    public void testCreateStateAmbarda(){
        StateStokta stateTransferred = new StateStokta();
        assertNotNull(stateTransferred.getType());
        assertEquals(stateTransferred.getType(),IMalzemeState.STOKTA);
    }
	
	public void testGetNextState(){
        StateStokta stateTransferred = new StateStokta();
		IMalzemeHareketi hareket = new MalzemeCikisHareketi();
		assertEquals(stateTransferred.getNextState(hareket).getType(),IMalzemeState.ZIMMETLI);
		hareket.setGeciciSahip(new HareketYeri());
		assertEquals(stateTransferred.getNextState(hareket).getType(),IMalzemeState.ASKIDA);
		assertEquals(stateTransferred.getNextState(new MalzemeBozukHareketi()).getType(),IMalzemeState.BOZUK);
		assertEquals(stateTransferred.getNextState(new MalzemeZayiHareketi()).getType(),IMalzemeState.ZAYI);
	}
}
