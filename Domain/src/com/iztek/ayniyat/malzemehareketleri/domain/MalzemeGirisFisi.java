package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Date;

/**
 * @author Umit Akyol
 */
public class MalzemeGirisFisi extends AbstractMalzemeHareketFisi {
    public final static String SATINALMA = "Satýnalma";
    public final static String BAGIS ="Baðýþ";
    public final static String DEVIR ="Baþka Ambardan Devir";
    public final static String VAKIF ="Vakýf";
    public final static String TAMIREDILDI ="Tamir Edildi";
    public final static String DIGER ="Diðer";
    
    private String girisSekli;
    private String alindigiKurulus;
    private String faturaNo;
    private Date faturaTarihi; 
   
	public MalzemeGirisFisi() {
        super();
    }
    
    public MalzemeGirisFisi(MalzemeGirisHareketi malzemeHareketi) {
        super();
        setOwningMalzemeHareketi(malzemeHareketi);
    }
    
    public String getAlindigiKurulus() {
        return alindigiKurulus;
    }
    
    public void setAlindigiKurulus(String alindigiKurulus) {
        this.alindigiKurulus = alindigiKurulus;
    }
    
    public String getFaturaNo() {
        return faturaNo;
    }
    
    public void setFaturaNo(String faturaNo) {
        this.faturaNo = faturaNo;
    }
    
    public Date getFaturaTarihi() {
        return faturaTarihi;
    }
    
    public void setFaturaTarihi(Date faturaTarihi) {
        this.faturaTarihi = faturaTarihi;
    }
    
    public String getGirisSekli() {
        return girisSekli;
    }
    
    public void setGirisSekli(String girisSekli) {
        this.girisSekli = girisSekli;
    }
}