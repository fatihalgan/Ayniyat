package com.iztek.ayniyat.sorgular.zimmetsorgulama.gui;

import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasNo;
/**
 * @author fusun
 *  */
public class ZimmetSorgulamaFilterData {

	private DemirbasMalzeme demirbas;
	private String tanim;
	private String zimmetSahibi;
	private String birim;
	public String getBirim() {
		return birim;
	}
	public void setBirim(String birim) {
		this.birim = birim;
	}
	public  DemirbasMalzeme getDemirbas() {
		return demirbas;
	}
	public void setDemirbas(DemirbasMalzeme demirbas) {
		this.demirbas = demirbas;
	}
	public String getTanim() {
		return tanim;
	}
	public void setTanim(String tanim) {
		this.tanim = tanim;
	}
	public String getZimmetSahibi() {
		return zimmetSahibi;
	}
	public void setZimmetSahibi(String zimmetSahibi) {
		this.zimmetSahibi = zimmetSahibi;
	}
	
}
