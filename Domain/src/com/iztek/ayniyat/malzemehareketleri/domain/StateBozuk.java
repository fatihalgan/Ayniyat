package com.iztek.ayniyat.malzemehareketleri.domain;
/**
 * @author Cagdas CIRIT
 **/
public class StateBozuk implements IMalzemeState{

	public String getType() {
		return IMalzemeState.BOZUK;
	}

	public IMalzemeState getNextState(IMalzemeHareketi hareket) {
		if (hareket instanceof MalzemeZayiHareketi) 
			return new StateZayi();
		else if (hareket instanceof MalzemeTerkinHareketi) 
			return new StateTerkin();
		else if (hareket instanceof MalzemeGirisHareketi) 
			return new StateStokta();
		return null;
	}

}
