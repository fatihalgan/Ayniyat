package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Iterator;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeCikisHareketi extends AbstractMalzemeHareketi {
	public MalzemeCikisHareketi(){
		setHareketFisi(new MalzemeCikisFisi(this));
	}	
	public void addMalzeme(IMalzeme malzeme){
		super.addMalzeme(malzeme);
		//malzeme eklendiginde fisin pahasinin degerleri degisecegi icin yeni paha yaratýlýp kendi deðerlerini hesaplasina izin verilir.
		//pahanin value type olmasinada uyulmuþ olur, value type üstünde oynama yapilmaz direkt yenisi yaratýlýr.
		((MalzemeCikisFisi)getHareketFisi()).setPaha(new MalzemeFisPahasi());
	}
	
	public void hareketiGerceklestir() {
	    //TODO gerekli islemler karsýlasýldýkca eklenecek
	    
	    //malzemeler varsa gecici sahibine yoksa hedefe zimmetlenmeli
	    Iterator iterator = getMalzemeler().iterator();
	    while(iterator.hasNext()){
	        IMalzeme malzeme = (IMalzeme) iterator.next();
	          if(malzeme instanceof IDemirbasMalzeme){
	            if(getGeciciSahip()!= null){
	                ((IDemirbasMalzeme)malzeme).setZimmetSahibi(getGeciciSahip().getHareketYeri());
	            }
	            else{
	            	((IDemirbasMalzeme)malzeme).setZimmetSahibi(getHareketHedefi().getHareketYeri());
	            }
	        }
	    }
    }
	
	public String getType(){
	    return "Malzeme Çýkýþ Hareketi";
	}
}
