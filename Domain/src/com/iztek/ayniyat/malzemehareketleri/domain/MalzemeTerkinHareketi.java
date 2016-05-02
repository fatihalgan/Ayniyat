package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Iterator;

import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeTerkinHareketi extends AbstractMalzemeHareketi {
	
	public MalzemeTerkinHareketi(){
		setHareketFisi(new MalzemeTerkinFisi(this));
	}
	
	public HareketYeri getGeciciSahip() {
		return null;
	}
	
	public void hareketiGerceklestir() {
        // TODO gerekli islemler karsýlasýldýkca eklenecek
	    
	    //terkin malzemelerin zimmet sahibi olmaz
	    Iterator iterator = getMalzemeler().iterator();
	    while(iterator.hasNext()){
	        IMalzeme malzeme = (IMalzeme) iterator.next();
	        if(malzeme instanceof IDemirbasMalzeme){
	            IZimmetAlan zimmetSahibi = ((IDemirbasMalzeme)malzeme).getZimmetSahibi();
	            if (zimmetSahibi!=null) //Malzeme Bozuk olabilir
	            	((IDemirbasMalzeme)malzeme).setZimmetSahibi(null);
	        }
	    }
    }
	
	public String getType(){
	    return "Malzeme Terkin Hareketi";
	}
}
