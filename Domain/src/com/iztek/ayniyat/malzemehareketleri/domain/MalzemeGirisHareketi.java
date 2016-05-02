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
		//malzeme eklendiginde fisin pahasinin degerleri degisecegi icin yeni paha yaratýlýp kendi deðerlerini hesaplasina izin verilir.
		//pahanin value type olmasinada uyulmuþ olur, value type üstünde oynama yapilmaz direkt yenisi yaratýlýr.
		((MalzemeGirisFisi)getHareketFisi()).setPaha(new MalzemeFisPahasi());
	}
	
	public HareketYeri getGeciciSahip() {
		return null;
	}
	
	public void hareketiGerceklestir() {
        // TODO gerekli islemler karsýlasýldýkca eklenecek
	    //assume: malzemeler ambara girecegi icin sahibi olmamalý
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
	    return "Malzeme Giriþ Hareketi";
	}
}
