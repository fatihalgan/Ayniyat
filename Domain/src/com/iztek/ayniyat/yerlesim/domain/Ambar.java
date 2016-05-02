package com.iztek.ayniyat.yerlesim.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemehareketleri.domain.StateStokta;
/**
 * @author Umit Akyol
 *
 */
public class Ambar extends AbstractZimmetAlan implements IStokAlan{
    private Set kullanicilar = new HashSet();
     
    public Ambar(){
    	super(); 
     }
	
    public Ambar(String tanim){
    	super(tanim);
    }
     
    public Set getKullanicilar() {
        return kullanicilar;
    }
    public void setKullanicilar(Set kullanicilar) {
        this.kullanicilar = kullanicilar;
    }
    
    public void addKategori(IKategorilendirilebilir kategori){
        if(kategori == null)
            throw new IllegalStateException("Boþ Kategori Eklenemez.");
        if(kategori instanceof Personel || kategori instanceof Ambar)
            super.addKategori(kategori);
        else
            throw new IllegalStateException("Ambara yalnýz Ambar veya Personel alt kategorisi eklenebilir");
    }
    
    public Object copy() throws Exception{
        Ambar newInstance = null;
        if(!isKopyasiAlinabilir())
            throw new IllegalAccessException("Kendisi veya altkategorileri zimmetli malzeme iceren kategoriler kopyalanamaz");
        else
            newInstance = new Ambar(tanim);
        
		Iterator iterator = getAltKategoriler().iterator();
		while(iterator.hasNext()){
			IKategorilendirilebilir altKategori = (IKategorilendirilebilir)iterator.next();
			if(altKategori instanceof Personel)
			    continue;
			try {
                if(!altKategori.isKopyasiAlinabilir())
                    throw new IllegalAccessException("Kendisi veya altkategorileri zimmetli malzeme iceren kategoriler kopyalanamaz");
                else
                    newInstance.addKategori((IKategorilendirilebilir) altKategori.copy());
            } catch (Exception e) {
                System.out.println(e);
                throw new IllegalAccessException("Kendisi veya altkategorileri zimmetli malzeme iceren kategoriler kopyalanamaz");
            } 
		}
		return newInstance;
    }
    
    public Set getStokluMalzemeler(){
  
    	Set stokluDemirbaslar=new HashSet();
    	
    	Collection demirbaslar=getZimmetliMalzemeler();
    	
    	
    	
    	Iterator iterator=demirbaslar.iterator();
    	
    	 while(iterator.hasNext()){
    		 
    		
    		 AbstractMalzeme demirbasMalzeme=(AbstractMalzeme)iterator.next();
    		 
    		 if(demirbasMalzeme.getState().getType().equals(StateStokta.STOKTA)){
    			 
    			 stokluDemirbaslar.add(demirbasMalzeme);
    		 }
    	 }
    			 
    		return stokluDemirbaslar;	 
    			
    		}
    
    /**
	 * @author fusun
	 */
    
    public Collection getAmbaraZimmetliMalzemeler(){
    	
    	Set returnVal=new HashSet();
    	Iterator iterator=getZimmetliMalzemeler().iterator();
    
    	while(iterator.hasNext()){
    		
    		AbstractMalzeme zimmetliMalzeme=(AbstractMalzeme)iterator.next();
    		
    		if ((zimmetliMalzeme.getState().getType()).equals(IMalzemeState.ZIMMETLI)){
				returnVal.add(zimmetliMalzeme);
    		}
    	}
    	return returnVal;
    	}
    	
     public Collection getAltKategorilerindekiZimmetliMalzemeler(){
    	Collection returnVal=null;
    	returnVal=getAmbaraZimmetliMalzemeler();
		Iterator iter=getAltKategoriler().iterator();
			while(iter.hasNext()){
				AbstractZimmetAlan altKategori=(AbstractZimmetAlan)iter.next();
				returnVal.addAll(altKategori.getAltKategorilerindekiZimmetliMalzemeler());
			}
			return returnVal;
		}
    /**
     * @author Füsun Çetin
    */
    public boolean isDeletable() {
    	if(getStokluMalzemeler().size()!=0 || getZimmetliMalzemeler().size()!=0) return false;
    	return true;
    }
    	
}
    	
    

