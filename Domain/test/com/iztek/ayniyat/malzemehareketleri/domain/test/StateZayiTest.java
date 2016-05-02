package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;
import com.iztek.ayniyat.malzemehareketleri.domain.*;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class StateZayiTest extends MainTestCase{

    public static void main(String[] args) {
        TestRunner.run(StateZayiTest.class);
    }
    
    public void testCreateStateZayi(){
        StateZayi stateDisposed = new StateZayi();
        assertNotNull(stateDisposed.getType());
        assertEquals(stateDisposed.getType(),IMalzemeState.ZAYI);
    }
	
	public void testGetNextState(){
        StateZayi stateDisposed = new StateZayi();
		assertEquals(stateDisposed.getNextState(new MalzemeGirisHareketi()).getType(),IMalzemeState.ZAYI);
		assertEquals(stateDisposed.getNextState(new MalzemeCikisHareketi()).getType(),IMalzemeState.ZAYI);
		assertEquals(stateDisposed.getNextState(new MalzemeZayiHareketi()).getType(),IMalzemeState.ZAYI);
		assertEquals(stateDisposed.getNextState(new MalzemeDevirHareketi()).getType(),IMalzemeState.ZAYI);
		assertEquals(stateDisposed.getNextState(new MalzemeBozukHareketi()).getType(),IMalzemeState.ZAYI);
	}
}
