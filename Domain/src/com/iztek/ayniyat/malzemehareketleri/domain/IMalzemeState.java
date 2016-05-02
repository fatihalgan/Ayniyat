package com.iztek.ayniyat.malzemehareketleri.domain;

/**
 * @author Cagdas CIRIT
 */
public interface IMalzemeState {
	public static final String ZAYI = "Zayi";
	public static final String ASKIDA = "Askida";
	public static final String STOKTA = "Stokta";
	public static final String ZIMMETLI = "Zimmetli";
	public static final String BOZUK = "Bozuk";
	public static final String TERKÝN="Terkin";

	public String getType();
	public IMalzemeState getNextState(IMalzemeHareketi hareket);
}
