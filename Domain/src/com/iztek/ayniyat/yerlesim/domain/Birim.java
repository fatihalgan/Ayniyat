package com.iztek.ayniyat.yerlesim.domain;

import java.util.Collection;
import java.util.Iterator;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Umit Akyol
 */
public class Birim extends AbstractYapi{
    public Birim() {
        super();  
    }
	
    public Birim(String ad) {
        super(ad);  
    }
    
    public void addKategori(IKategorilendirilebilir kategori) {
        if(kategori == null)
            throw new IllegalStateException("Boþ Kategori Eklenemez.");
        if(kategori instanceof Birim || kategori instanceof Bina || kategori instanceof Oda || kategori instanceof Ambar)
            super.addKategori(kategori);
        else
            throw new IllegalStateException("Birime yalnýz Birim, Bina, Oda veya Ambar alt kategorisi eklenebilir");
    }    
    
    public Object copy() throws IllegalAccessException {
        Birim newInstance = null;
        if(!isKopyasiAlinabilir())
            throw new IllegalAccessException("Kendisi veya altkategorileri zimmetli malzeme içeren kategoriler kopyalanamaz!");
        else
            newInstance = new Birim(tanim);
        
		Iterator iterator = getAltKategoriler().iterator();
		while(iterator.hasNext()){
			IKategorilendirilebilir altKategori = (IKategorilendirilebilir)iterator.next();
			try {
                if(!altKategori.isKopyasiAlinabilir())
                    throw new IllegalAccessException("Kendisi veya altkategorileri zimmetli malzeme içeren kategoriler kopyalanamaz!");
                else
                    newInstance.addKategori((IKategorilendirilebilir) altKategori.copy());
            } catch (Exception e) {
                System.out.println(e);
                throw new IllegalAccessException("Kendisi veya altkategorileri zimmetli malzeme içeren kategoriler kopyalanamaz!");
            } 
		}
		return newInstance;
    }
    
	/**
	 * @author fusun
	 */

    public Collection getAltKategorilerindekiZimmetliMalzemeler(){
        
 	Collection zimmetliDemirbaslar=getZimmetliMalzemeler();
   	Iterator iter=getAltKategoriler().iterator();
   		while(iter.hasNext()){
   			AbstractZimmetAlan altKategori=(AbstractZimmetAlan)iter.next();
   			Collection demirbaslar=(altKategori.getAltKategorilerindekiZimmetliMalzemeler());
   			zimmetliDemirbaslar.addAll(demirbaslar);
   		}
   	return zimmetliDemirbaslar;
    }
}
