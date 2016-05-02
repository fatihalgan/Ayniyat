package com.iztek.ayniyat.sorgular.zayiMalzemeSorgulama.controller;

import java.util.Collection;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.sorgular.controller.MalzemeSorguController;
import com.iztek.ayniyat.sorgular.gui.AbstractMalzemeSorguFrame;
import com.iztek.ayniyat.yerlesim.domain.Ambar;



public class ZayiMalzemeSorgulamaController implements MalzemeSorguController {
	
	AbstractMalzemeSorguFrame zayiMalzemeSorgulama;
	
	public void registerSelf(AbstractMalzemeSorguFrame zayiMalzemeSorgulama) {
		this.zayiMalzemeSorgulama=zayiMalzemeSorgulama;
	}
	
	public Collection getDataForSorguTable(){
		return (MalzemeManager.zayiMalzemeSorgulama());
	}
	
	public void malzemeIncelemekIcinDialogCikar(AbstractMalzemeTanimi malzemeTanimi){
	      AnaController.getInstance().getMalzemeCikisiController().sendSeciliNodeToMalzemeCikisi(malzemeTanimi);
	}

	public Collection getDataForSorguTable(Ambar ambar) {
		return null;
	}
}
