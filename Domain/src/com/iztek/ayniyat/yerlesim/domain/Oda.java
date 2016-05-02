package com.iztek.ayniyat.yerlesim.domain;
import java.util.Collection;
import java.util.Iterator;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Umit Akyol
 *
 */
public class Oda extends AbstractYapi {
	public static final String LAB = "Laboratuvar";
	public static final String SINIF = "Sýnýf";
	public static final String PERSONEL = "Personel odasý";
	public static final String KILER = "Kiler";
	public static final String KANTIN = "Kantin";
	public static final String KONFERANS = "Konferans salonu";
	
    private String tip = null;
    private Integer kat = null;
    
    public Oda() {
        super();    
    }
    
    public Oda(String ad, String tip ,Integer kat) {
        super(ad);
        this.tip = tip;
        this.kat = kat;
    }
    

    public Integer getKat() {
        return kat;
    }
    
    public void setKat(Integer kat) {
        this.kat = kat;
    }
    
    public String getTip() {
        return tip;
    }
        
    public void setTip(String tip) {
        this.tip = tip;
    }
    
    public void addKategori(IKategorilendirilebilir kategori) {
        if(kategori == null)
            throw new IllegalStateException("Boþ Kategori Eklenemez.");
        if(kategori instanceof Personel)
            super.addKategori(kategori);
        else
            throw new IllegalStateException("Odaya yalnýz Personel alt kategorisi eklenebilir");
    }
    
    public Object copy() throws IllegalAccessException {
        Oda newInstance = null;
        if(!isKopyasiAlinabilir())
            throw new IllegalAccessException("Kendisi veya altkategorileri zimmetli malzeme içeren kategoriler kopyalanamaz!");
        else
            newInstance = new Oda(tanim,tip,kat);
		return newInstance;
    } 
    
    public Personel getPersonelBySicilNo(String sicilNo){
        if(getAltKategoriler() == null) return null;
        Iterator it = getAltKategoriler().iterator();
        while(it.hasNext()) {
             Personel altKategori = (Personel) it.next();
            if(altKategori.getSicilNo().trim().equals(sicilNo.trim())) return altKategori;
            
        }
        return null;
    }
    /**
	 * @author fusun
	 */

    
    public Collection getAltKategorilerindekiZimmetliMalzemeler(){
    	   
		Collection zimmetliDemirbaslar=getZimmetliMalzemeler();
		
		Iterator iter=getAltKategoriler().iterator();
		while(iter.hasNext()){
			
			AbstractZimmetAlan altKategori=(AbstractZimmetAlan)iter.next();
			
			Iterator demirbaslar=(altKategori.getAltKategorilerindekiZimmetliMalzemeler()).iterator();
				
			while(demirbaslar.hasNext()){
			
			zimmetliDemirbaslar.add(demirbaslar.next());
			}
		}
		return zimmetliDemirbaslar;
	}
}
