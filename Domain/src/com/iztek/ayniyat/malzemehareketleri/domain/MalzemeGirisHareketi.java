package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Iterator;

/**
 * @author Umit Akyol
 */
public class MalzemeGirisHareketi extends AbstractMalzemeHareketi {
   
    public MalzemeGirisHareketi(){
		setHareketFisi(new MalzemeGirisFisi(this));
	}
	
	public void addMalzeme(IMalzeme malzeme){
		super.addMalzeme(malzeme);
		//malzeme eklendiginde fisin pahasinin degerleri degisecegi icin yeni paha yarat�l�p kendi de�erlerini hesaplasina izin verilir.
		//pahanin value type olmasinada uyulmu� olur, value type �st�nde oynama yapilmaz direkt yenisi yarat�l�r.
		((MalzemeGirisFisi)getHareketFisi()).setPaha(new MalzemeFisPahasi());
	}
	
	public HareketYeri getGeciciSahip() {
		return null;
	}
	
	public void hareketiGerceklestir() {
        // TODO gerekli islemler kars�las�ld�kca eklenecek
	    //assume: malzemeler ambara girecegi icin sahibi olmamal�
	    Iterator iterator = getMalzemeler().iterator();
	    while(iterator.hasNext()){
	        IMalzeme malzeme = (IMalzeme) iterator.next();
	        if(malzeme instanceof IDemirbasMalzeme){
	        	((IDemirbasMalzeme)malzeme).addDemirbasNo(((DemirbasMalzeme)malzeme).getDemirbasNo().getSiraNo());
	            ((IDemirbasMalzeme)malzeme).setZimmetSahibi(getHareketHedefi().getHareketYeri());
	        }
	    }
    }
	
	
	
	public String getType(){
	    return "Malzeme Giri� Hareketi";
	}
}
