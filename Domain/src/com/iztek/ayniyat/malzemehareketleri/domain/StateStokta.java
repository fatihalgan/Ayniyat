package com.iztek.ayniyat.malzemehareketleri.domain;


/**
 * @author Cagdas CIRIT
 **/
public class StateStokta implements IMalzemeState {

	public String getType() {
		return IMalzemeState.STOKTA;
	}

	public IMalzemeState getNextState(IMalzemeHareketi hareket) {
		if (hareket instanceof MalzemeZayiHareketi) 
			return new StateZayi();
		else if (hareket instanceof MalzemeTerkinHareketi) 
			return new StateTerkin();
		else if (hareket instanceof MalzemeBozukHareketi) 
			return new StateBozuk();
		else if (hareket instanceof MalzemeCikisHareketi) {
	
			if (hareket.getGeciciSahip()==null)
				return new StateZimmetli();
			else
				return new StateAskida();
		
		}
		
		return null;
	}
	
}
