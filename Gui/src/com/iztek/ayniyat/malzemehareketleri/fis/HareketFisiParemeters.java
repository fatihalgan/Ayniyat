package com.iztek.ayniyat.malzemehareketleri.fis;

import java.util.Map;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeCikisFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeDevirFisi;

public class HareketFisiParemeters implements FisParameters{
	
	public  void commonParameters(Map parameters,AbstractMalzemeHareketFisi hareketFisi){
		
		parameters.put("BelgeNo",hareketFisi.getBelgeNo().getBelgeNo().toString());
		parameters.put("Tarih",hareketFisi.getDuzenlemeTarihi());
		if (hareketFisi instanceof MalzemeCikisFisi) {
			parameters.put("VerenYer",hareketFisi.getOwningMalzemeHareketi().getHareketKaynagi().getHareketYeri().getTanim());
			parameters.put("AlanYer",hareketFisi.getOwningMalzemeHareketi().getHareketHedefi().getHareketYeri().getTanim());
			parameters.put("GenelToplam",hareketFisi.getPaha().getGenelToplamTutari().toString());
			
		}
		else if (hareketFisi instanceof MalzemeDevirFisi) {
			parameters.put("VerenYer",hareketFisi.getOwningMalzemeHareketi().getHareketKaynagi().getHareketYeri().getTanim());
			parameters.put("AlanYer",hareketFisi.getOwningMalzemeHareketi().getHareketHedefi().getHareketYeri().getTanim());
			
		}
		
	}
	
}
		
	
	
