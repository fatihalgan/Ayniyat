package com.iztek.ayniyat.yerlesim.domain;

import java.util.Collection;
import java.util.StringTokenizer;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.KategoriMergeStateManager;

/**
 * @author Umit Akyol
 *
*/
public class Personel extends AbstractZimmetAlan{

   private String sicilNo = null;
   private String ad =null;
   private String soyad = null;
   
   public Personel() {
   		super();    
   }
   
   public Personel(String sicilNo, String ad, String soyad) {
    	super();
        this.sicilNo = sicilNo;
        this.ad = ad;
        this.soyad = soyad;
    }
    
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSicilNo() {
        return sicilNo;
    }

    public void setSicilNo(String sicilNo) {
        this.sicilNo = sicilNo;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
    
    public String getTanim(){
        return ad+" "+soyad;
    }
    
    public void setTanim(String tanim){
       if(tanim!=null){
       		String ad="";
       		String soyad="";
       		StringTokenizer st = new StringTokenizer(tanim);
       		int numOfTokens = st.countTokens();
        
       		ad = st.nextToken();
        
       		if (numOfTokens>1){
       			for(int i=0;i<numOfTokens-2;i++)
       				ad = ad+" "+st.nextToken();
       			soyad = st.nextToken();
       		}
        
       		setAd(ad);
       		setSoyad(soyad);
       		super.tanim = tanim;
       }
    }
    
    
    public void addKategori(IKategorilendirilebilir kategori) {
        throw new IllegalStateException("Personele Alt Kategori Eklenemez..");
    }
    
    public void removeKategori(IKategorilendirilebilir kategori) {
        throw new IllegalStateException("Personel Alt Kategoriler Ýçeremez..");

    }
    public boolean hasInChildren(IKategorilendirilebilir kategori) {
        //Personel tipinin alt kategorisi olamaz.
        return false;
    }
    
    public IKategorilendirilebilir getChildByTanim(String tanim) {
        //Personel tipinin alt kategorisi olamaz.
        return null;
    }
    
    
    public String toString(){
        return getTanim();
    }
	
    public Object copy() {
        Personel newInstance = new Personel(sicilNo,ad,soyad);
        return newInstance;
    }
    
    public void mergeKategori(IKategorilendirilebilir kategori,KategoriMergeStateManager stateManager) {
        throw new IllegalStateException("Hedef "+this.tanim+" ile ayný isimli baþka bir personel içeriyor!");
    }
    
    public boolean isKopyasiAlinabilir() {
        return false;
    }
	public Collection getAltKategorilerindekiZimmetliMalzemeler(){
		return getZimmetliMalzemeler();
	}
	 
    
}
