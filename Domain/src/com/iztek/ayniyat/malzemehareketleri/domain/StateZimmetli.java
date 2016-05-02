package com.iztek.ayniyat.malzemehareketleri.domain;
import com.iztek.ayniyat.yerlesim.domain.Ambar;

/**
 * @author Cagdas CIRIT
 **/
public class StateZimmetli implements IMalzemeState{

	public String getType() {
		return IMalzemeState.ZIMMETLI;
	}

	public IMalzemeState getNextState(IMalzemeHareketi hareket) {
		if (hareket instanceof MalzemeZayiHareketi) 
			return new StateZayi();
		if (hareket instanceof MalzemeTerkinHareketi) 
			return new StateTerkin();
		if (hareket instanceof MalzemeBozukHareketi) 
			return new StateBozuk();
		if (hareket instanceof MalzemeDevirHareketi) {
			if (hareket.getHareketHedefi().getHareketYeri() instanceof Ambar)
				return new StateStokta();
			else
				return new StateZimmetli();
		}
	
		
		return null;
	}
}
