package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Date;

/**
 * @author Cagdas CIRIT
 **/
public class HareketBilgileri {
    private String tutanakNo;
    private Date tutanakTarihi;
    private Date zayi_terkinTarihi;
    private String zayi_terkinNedeni;
    private String zayi_terkinEden;
    private String aciklama;
	
    public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public String getTutanakNo() {
		return tutanakNo;
	}
	public void setTutanakNo(String tutanakNo) {
		this.tutanakNo = tutanakNo;
	}
	public Date getTutanakTarihi() {
		return tutanakTarihi;
	}
	public void setTutanakTarihi(Date tutanakTarihi) {
		this.tutanakTarihi = tutanakTarihi;
	}
	public String getZayi_terkinEden() {
		return zayi_terkinEden;
	}
	public void setZayi_terkinEden(String zayi_terkinEden) {
		this.zayi_terkinEden = zayi_terkinEden;
	}
	public String getZayi_terkinNedeni() {
		return zayi_terkinNedeni;
	}
	public void setZayi_terkinNedeni(String zayi_terkinNedeni) {
		this.zayi_terkinNedeni = zayi_terkinNedeni;
	}
	public Date getZayi_terkinTarihi() {
		return zayi_terkinTarihi;
	}
	public void setZayi_terkinTarihi(Date zayi_terkinTarihi) {
		this.zayi_terkinTarihi = zayi_terkinTarihi;
	}

   
}
