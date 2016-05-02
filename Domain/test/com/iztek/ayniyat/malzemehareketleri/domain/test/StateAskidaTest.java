package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeBozukHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeZayiHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.StateAskida;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class StateAskidaTest extends MainTestCase {

    public static void main(String[] args) {
        TestRunner.run(StateAskidaTest.class);
    }
    public void testCreateStateAskida(){
        StateAskida statePending = new StateAskida();
        assertNotNull(statePending.getType());
        assertEquals(statePending.getType(),IMalzemeState.ASKIDA);
    }
	
	public void testGetNextState(){
        StateAskida statePending = new StateAskida();
		assertEquals(statePending.getNextState(new MalzemeGirisHareketi()).getType(),IMalzemeState.STOKTA);
		assertEquals(statePending.getNextState(new MalzemeBozukHareketi()).getType(),IMalzemeState.BOZUK);
		assertEquals(statePending.getNextState(new MalzemeZayiHareketi()).getType(),IMalzemeState.ZAYI);
	}
}
