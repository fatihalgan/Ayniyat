package com.iztek.ayniyat.malzemehareketleri.fis;

import java.util.Map;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisFisi;

public class TesellumFisiParameters implements FisParameters{

public void commonParameters(Map parameters,AbstractMalzemeHareketFisi hareketFisi){
		
		parameters.put("BelgeNo",hareketFisi.getBelgeNo().getBelgeNo().toString());
		parameters.put("Tarih",hareketFisi.getDuzenlemeTarihi());
		parameters.put("VerenYer",((MalzemeGirisFisi)hareketFisi).getAlindigiKurulus());
		parameters.put("AlanYer",AnaController.getInstance().getIslemYapanAmbar().getTanim());
		if(((MalzemeGirisFisi)hareketFisi).getGirisSekli().equals(MalzemeGirisFisi.SATINALMA))
		{
			parameters.put("FaturaNo",((MalzemeGirisFisi)hareketFisi).getFaturaNo().toString());
			
		}
		else{
			parameters.put("FaturaNo","");
		}
		parameters.put("ToplamTutar",hareketFisi.getPaha().getToplamTutar().toString());
		parameters.put("IskontoTutari",hareketFisi.getPaha().getIskontoTutari().toString());
		parameters.put("KDVTutari",hareketFisi.getPaha().getKdvTutari().toString());
		parameters.put("OTVTutari",hareketFisi.getPaha().getOtvTutari().toString());
		parameters.put("GenelToplam",hareketFisi.getPaha().getGenelToplamTutari().toString());
		parameters.put("Aciklama","DemirBaþ Malzeme");
		parameters.put("AlinisNedeni",((MalzemeGirisFisi)hareketFisi).getGirisSekli().toString());
		//	parameters.put("AlinisNedeni",((MalzemeCikisFisi)hareketFisi).getAlinisNedeni());
		
}
	
}
		