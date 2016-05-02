package com.iztek.ayniyat.malzemehareketleri.domain;

import java.math.BigDecimal;

import com.iztek.commons.money.Money;
/**
 * @author Cagdas CIRIT
 */
public class MalzemePahasi {
	private Money vergisizBirimFiyat;
    private Money vergiliBirimFiyat;
	private Money iskontoTutari;
    private Float kdvOrani;
    private Float otvOrani;
    
	public MalzemePahasi(){
	    kdvOrani = new Float(0);
	    otvOrani = new Float(0);
	    iskontoTutari = Money.yeniTurkLirasi(0);
	}
	
	public BigDecimal getVergisizBirimFiyatMiktar(){
		if (getVergisizBirimFiyat()!=null)
			return vergisizBirimFiyat.getAmount();
		return null;
	}
	
	public void setVergisizBirimFiyatMiktar(BigDecimal amount){
		if (vergisizBirimFiyat!=null)
			vergisizBirimFiyat.setAmount(amount);
	}
	
	public String getVergisizBirimFiyatKur(){
		if (getVergisizBirimFiyat()!=null)
			return vergisizBirimFiyat.getCurrency();
		return null;
	}
	
	public void setVergisizBirimFiyatKur(String currency){
		if (vergisizBirimFiyat!=null)
			vergisizBirimFiyat.setCurrency(currency);
	}
	
	public BigDecimal getVergiliBirimFiyatMiktar(){
		if (getVergiliBirimFiyat()!=null)
			return vergiliBirimFiyat.getAmount();
		return null;
	}
	
	public void setVergiliBirimFiyatMiktar(BigDecimal amount){
		if (vergiliBirimFiyat==null)
			vergiliBirimFiyat = new Money();
		vergiliBirimFiyat.setAmount(amount);
	}
	
	public String getVergiliBirimFiyatKur(){
		if (getVergiliBirimFiyat()!=null)
			return vergiliBirimFiyat.getCurrency();
		return null;
	}
	
	public void setVergiliBirimFiyatKur(String currency){
		if (vergiliBirimFiyat==null)
			vergiliBirimFiyat = new Money();
		vergiliBirimFiyat.setCurrency(currency);
	}
	
	public Money getIskontoTutari() {
		return iskontoTutari;
	}

	public void setIskontoTutari(Money iskontoTutari) {
		this.iskontoTutari = iskontoTutari;
	}

	public Float getKdvOrani() {
		return kdvOrani;
	}
	
	public void setKdvOrani(Float kdvOrani) {
		this.kdvOrani = kdvOrani;
	}
	
	public Float getOtvOrani() {
		return otvOrani;
	}
	
	public void setOtvOrani(Float otvOrani) {
		this.otvOrani = otvOrani;
	}
	 
	public Money getVergiliBirimFiyat() {
		if (vergiliBirimFiyat==null && vergisizBirimFiyat!=null)
			setVergiliBirimFiyat((Money) vergisizBirimFiyat.times(1+getAsilOran()));
		return vergiliBirimFiyat;
	}

	public void setVergiliBirimFiyat(Money vergiliBirimFiyat) {
		this.vergiliBirimFiyat = vergiliBirimFiyat;
	}

	public Money getVergisizBirimFiyat() {
		if (vergisizBirimFiyat==null && vergiliBirimFiyat!=null)
			setVergisizBirimFiyat((Money) vergiliBirimFiyat.times(1/(1+getAsilOran())));
		return vergisizBirimFiyat;			
	}

	public void setVergisizBirimFiyat(Money vergisizBirimFiyat) {
		this.vergisizBirimFiyat = vergisizBirimFiyat;
	}
	
	public BigDecimal getIskontoTutariMiktar() {
		if (getIskontoTutari()!=null)
			return iskontoTutari.getAmount();
		return null;
	}
	
	public void setIskontoTutariMiktar(BigDecimal amount) {
		if(iskontoTutari==null)
			iskontoTutari = new Money();
		iskontoTutari.setAmount(amount);
	}
	
	public String getIskontoTutariKur() {
		if (getIskontoTutari()!=null)
			return iskontoTutari.getCurrency();
		return null;
	}
	
	public void setIskontoTutariKur(String currency) {
		if(iskontoTutari==null)
			iskontoTutari = new Money();
		iskontoTutari.setCurrency(currency);
	}
	
	//oncelikli oran OTV dir otv set edilmediyse kdv set edildiyse KDV orani döndürülür 
	public float getAsilOran(){
		if (getOtvOrani().longValue()!=0)
			return getOtvOrani().floatValue()/100;
		if (getKdvOrani().longValue()!=0)
			return getKdvOrani().floatValue()/100;
		return 0;//2 oranda set edilmediyse vergili = vergisizdir
	}
}
