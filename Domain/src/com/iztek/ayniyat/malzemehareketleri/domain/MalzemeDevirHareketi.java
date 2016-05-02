package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Iterator;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeDevirHareketi extends AbstractMalzemeHareketi {

	public MalzemeDevirHareketi(){
		setHareketFisi(new MalzemeDevirFisi(this));
	}
	
	public HareketYeri getGeciciSahip() {
		return null;
	}
	
	public void hareketiGerceklestir() {
	    //malzemeler hedefe zimmetlenmeli
	    Iterator iterator = getMalzemeler().iterator();
	    while(iterator.hasNext()){
	        IMalzeme malzeme = (IMalzeme) iterator.next();
	        if(malzeme instanceof IDemirbasMalzeme){
	            getHareketHedefi().getHareketYeri().addDemirbasMalzeme((IDemirbasMalzeme)malzeme);
	        }
	    }
    }
	public String getType(){
	    return "Malzeme Devir Hareketi";
	}
}
