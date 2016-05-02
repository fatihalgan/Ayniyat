package com.iztek.ayniyat.malzemehareketleri.domain;
/**
 * @author Cagdas CIRIT
 **/
public class StateTerkin implements IMalzemeState{

	public String getType() {
		return IMalzemeState.TERK�N;
	}

	public IMalzemeState getNextState(IMalzemeHareketi hareket) {
		return new StateTerkin();
	}
}
