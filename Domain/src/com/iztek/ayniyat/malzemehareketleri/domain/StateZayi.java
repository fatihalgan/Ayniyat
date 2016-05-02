package com.iztek.ayniyat.malzemehareketleri.domain;
/**
 * @author Cagdas CIRIT
 **/
public class StateZayi implements IMalzemeState{

	public String getType() {
		return IMalzemeState.ZAYI;
	}

	public IMalzemeState getNextState(IMalzemeHareketi hareket) {
		return new StateZayi();
	}
}
