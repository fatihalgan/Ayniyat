package com.iztek.ayniyat.malzemehareketleri.domain;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

import com.iztek.commons.money.Bank;
import com.iztek.commons.money.Money;

/**
 * @author Cagdas CIRIT
 */
public class MalzemeFisPahasi {
	private Money toplamTutar;
    private Money genelToplamTutari;
    private Money kdvTutari;
    private Money otvTutari;
    private Money iskontoTutari;
	private Set malzemeler; 
   
	public MalzemeFisPahasi() {
	    iskontoTutari = new Money(0,Money.YTL);
	}
	
	public Money getIskontoTutari() {
		setIskontoTutari(calculateIskontoTutari());
		return iskontoTutari;
	}

	public void setIskontoTutari(Money iskontoTutari) {
		this.iskontoTutari = iskontoTutari;
	}

	public Money getKdvTutari() {
		if(kdvTutari==null)
			setKdvTutari(calculateKdvTutari());
		return kdvTutari;
	}

	public void setKdvTutari(Money kdvTutari) {
		this.kdvTutari = kdvTutari;
	}

	public Money getOtvTutari() {
		if (otvTutari==null)
			setOtvTutari(calculateOtvTutari());
		return otvTutari;
	}

	public void setOtvTutari(Money otvTutari) {
		this.otvTutari = otvTutari;
	}
	
	public Money getGenelToplamTutari() {
		if(genelToplamTutari==null)
			setGenelToplamTutari(calculateGenelToplamTutari());
		return genelToplamTutari;
	}

	public void setGenelToplamTutari(Money genelToplamTutari) {
		this.genelToplamTutari = genelToplamTutari;
	}

	public Money getToplamTutar() {
		if (toplamTutar==null)
			setToplamTutar(calculateToplamTutar());
		return toplamTutar;
	}

	public void setToplamTutar(Money toplamTutar) {
		this.toplamTutar = toplamTutar;
	}

	public BigDecimal getGenelToplamTutariMiktar() {
		if (getGenelToplamTutari()!=null)
			return genelToplamTutari.getAmount();
		return null;
	}
	
	public void setGenelToplamTutariMiktar(BigDecimal amount) {
		if(genelToplamTutari==null)
			genelToplamTutari = new Money();
		genelToplamTutari.setAmount(amount);
	}
	
	public String getGenelToplamTutariKur() {
		if (getGenelToplamTutari()!=null)
			return genelToplamTutari.getCurrency();
		return null;
	}
	
	public void setGenelToplamTutariKur(String currency) {
		if(genelToplamTutari==null)
			genelToplamTutari = new Money();
		genelToplamTutari.setCurrency(currency);
	}
	
	public BigDecimal getToplamTutarMiktar() {
		if (getToplamTutar()!=null)
			return toplamTutar.getAmount();
		return null;
	}
	
	public void setToplamTutarMiktar(BigDecimal amount) {
		if(toplamTutar==null)
			toplamTutar = new Money();
		toplamTutar.setAmount(amount);
	}
	
	public String getToplamTutarKur() {
		if (getToplamTutar()!=null)
			return toplamTutar.getCurrency();
		return null;
	}
	
	public void setToplamTutarKur(String currency) {
		if(toplamTutar==null)
			toplamTutar = new Money();
		toplamTutar.setCurrency(currency);
	}
	
	public BigDecimal getKdvTutariMiktar() {
		if (getKdvTutari()!=null)
			return kdvTutari.getAmount();
		return null;
	}
	
	public void setKdvTutariMiktar(BigDecimal amount) {
		if(kdvTutari==null)
			kdvTutari = new Money();
		kdvTutari.setAmount(amount);
	}
	
	public String getKdvTutariKur() {
		if (getKdvTutari()!=null)
			return kdvTutari.getCurrency();
		return null;
	}
	
	public void setKdvTutariKur(String currency) {
		if(kdvTutari==null)
			kdvTutari = new Money();
		kdvTutari.setCurrency(currency);
	}
	
	public BigDecimal getOtvTutariMiktar() {
		if (getOtvTutari()!=null)
			return otvTutari.getAmount();
		return null;
	}
	
	public void setOtvTutariMiktar(BigDecimal amount) {
		if(otvTutari==null)
			otvTutari = new Money();
		otvTutari.setAmount(amount);
	}
	
	public String getOtvTutariKur() {
		if (getOtvTutari()!=null)
			return otvTutari.getCurrency();
		return null;
	}
	
	public void setOtvTutariKur(String currency) {
		if(otvTutari==null)
			otvTutari = new Money();
		otvTutari.setCurrency(currency);
	}
	
	public BigDecimal getIskontoTutariMiktar() {
		return iskontoTutari.getAmount();
		
	}
	
	public void setIskontoTutariMiktar(BigDecimal amount) {
	    iskontoTutari.setAmount(amount);
	}
	
	public String getIskontoTutariKur() {
		return iskontoTutari.getCurrency();
	}
	
	public void setIskontoTutariKur(String currency) {
	    iskontoTutari.setCurrency(currency);
	}

	public void setMalzemeler(Set malzemeler) {
		this.malzemeler = malzemeler;
	}
	
	private Money calculateToplamTutar(){
		if (malzemeler!=null){
			Money toplamTutar = Money.yeniTurkLirasi(0);
			Bank bank = new Bank();
			Iterator iter = malzemeler.iterator();
			while (iter.hasNext()) {
				MalzemePahasi paha = ((IMalzeme)iter.next()).getPaha();
				toplamTutar = bank.reduce(toplamTutar.plus(paha.getVergisizBirimFiyat()), Money.YTL);
			}
			return toplamTutar;
		}
		
		return null;	
	}
	
	private Money calculateIskontoTutari(){	
	    Money iskontoTutari = Money.yeniTurkLirasi(0);
		if (malzemeler!=null){
			Bank bank = new Bank();
			Iterator iter = malzemeler.iterator();
			while (iter.hasNext()) {
				MalzemePahasi paha = ((IMalzeme)iter.next()).getPaha();
				iskontoTutari = bank.reduce(iskontoTutari.plus(paha.getIskontoTutari()), Money.YTL);
			}
		}
		return iskontoTutari;	
	}
	
	private Money calculateKdvTutari(){
		if (malzemeler!=null){
			Money kdvTutari = Money.yeniTurkLirasi(0);
			Bank bank = new Bank();
			Iterator iter = malzemeler.iterator();
			while (iter.hasNext()) {
				MalzemePahasi paha = ((IMalzeme)iter.next()).getPaha();
				if (paha.getOtvOrani().floatValue()==0){
					Money malzemeKdvTutari = (Money) paha.getVergisizBirimFiyat().times(paha.getAsilOran());
					kdvTutari = bank.reduce(kdvTutari.plus(malzemeKdvTutari), Money.YTL);
				}
			}
			return kdvTutari;
		}
		
		return null;	
	}
	
	private Money calculateOtvTutari(){
		if (malzemeler!=null){
			Money otvTutari = Money.yeniTurkLirasi(0);
			Bank bank = new Bank();
			Iterator iter = malzemeler.iterator();
			while (iter.hasNext()) {
				MalzemePahasi paha = ((IMalzeme)iter.next()).getPaha();
				if (paha.getOtvOrani().floatValue()!=0){
					Money malzemeOtvTutari = (Money) paha.getVergisizBirimFiyat().times(paha.getAsilOran());
					otvTutari = bank.reduce(otvTutari.plus(malzemeOtvTutari), Money.YTL);
				}
			}
			return otvTutari;
		}
		
		return null;	
	}
	
	private Money calculateGenelToplamTutari(){
		Money genelToplamTutari = Money.yeniTurkLirasi(0);
		Bank bank = new Bank();
		
		if (getToplamTutar()!=null) //toplam tutarý ekle
			genelToplamTutari = bank.reduce(genelToplamTutari.plus(getToplamTutar()), Money.YTL);
		if (getKdvTutari()!=null) //kdv tutarýný ekle
			genelToplamTutari = bank.reduce(genelToplamTutari.plus(getKdvTutari()), Money.YTL);
		if (getOtvTutari()!=null) //otv tutarini ekle
			genelToplamTutari = bank.reduce(genelToplamTutari.plus(getOtvTutari()), Money.YTL);
		if (iskontoTutari.getAmount().doubleValue()!=0.0) // iskonto tutarýný cikar
			genelToplamTutari = bank.reduce(genelToplamTutari.plus(getIskontoTutari().times(-1)), Money.YTL);
		
		return genelToplamTutari;
	}
}
