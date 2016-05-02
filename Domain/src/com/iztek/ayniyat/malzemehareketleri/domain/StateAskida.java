package com.iztek.ayniyat.malzemehareketleri.domain;
/**
 * @author Cagdas CIRIT
 **/
public class StateAskida implements IMalzemeState {

	public String getType() {
		return IMalzemeState.ASKIDA;
	}

	public IMalzemeState getNextState(IMalzemeHareketi hareket) {
		if (hareket instanceof MalzemeZayiHareketi) 
			return new StateZayi();
		if (hareket instanceof MalzemeTerkinHareketi) 
			return new StateTerkin();
		if (hareket instanceof MalzemeBozukHareketi) 
			return new StateBozuk();
		if (hareket instanceof MalzemeGirisHareketi) 
			return new StateStokta();
		return null;
	}

}
