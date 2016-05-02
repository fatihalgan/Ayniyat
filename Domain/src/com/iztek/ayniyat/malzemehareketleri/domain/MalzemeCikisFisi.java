package com.iztek.ayniyat.malzemehareketleri.domain;

/**
 * @author Umit Akyol
 */
public class MalzemeCikisFisi extends AbstractMalzemeHareketFisi {
	public static final String ZIMMET = "Zimmet";
	public static final String TRANSFER = "Transfer";
	public static final String DIGER = "Diger";
    private String cikisSekli = null;
    private String alinisNedeni = null;
    
    public MalzemeCikisFisi() {
        super();
    }
    
    public MalzemeCikisFisi(MalzemeCikisHareketi malzemeHareketi) {
        super();
        setOwningMalzemeHareketi(malzemeHareketi);
    }

    public String getAlinisNedeni() {
        return alinisNedeni;
    }
    
    public void setAlinisNedeni(String alinisNedeni) {
        this.alinisNedeni = alinisNedeni;
    }
    
    public String getCikisSekli() {
        return cikisSekli;
    }
    
    public void setCikisSekli(String cikisSekli) {
        this.cikisSekli = cikisSekli;
    }
}
