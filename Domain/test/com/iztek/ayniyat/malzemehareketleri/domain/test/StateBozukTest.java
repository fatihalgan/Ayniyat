package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.swingui.TestRunner;
import com.iztek.ayniyat.malzemehareketleri.domain.*;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 **/
public class StateBozukTest extends MainTestCase {

    public static void main(String[] args) {
        TestRunner.run(StateBozukTest.class);
    }
    
    public void testCreateStateBozuk(){
        StateBozuk stateDown = new StateBozuk();
        assertNotNull(stateDown.getType());
        assertEquals(stateDown.getType(),IMalzemeState.BOZUK);
    }
	
	public void testGetNextState(){
        StateBozuk stateDown = new StateBozuk();
		assertEquals(stateDown.getNextState(new MalzemeGirisHareketi()).getType(),IMalzemeState.STOKTA);
		assertEquals(stateDown.getNextState(new MalzemeZayiHareketi()).getType(),IMalzemeState.ZAYI);
	}
}
