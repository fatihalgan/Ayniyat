package com.iztek.ayniyat.sorgular.bozukMalzemeSorgulama.controller;

import java.util.Collection;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.sorgular.controller.MalzemeSorguController;
import com.iztek.ayniyat.sorgular.gui.AbstractMalzemeSorguFrame;
import com.iztek.ayniyat.yerlesim.domain.Ambar;

public class BozukMalzemeSorguController implements MalzemeSorguController {

	AbstractMalzemeSorguFrame bozukMalzemeSorgulama;
	
	public void registerSelf(AbstractMalzemeSorguFrame bozukMalzemeSorgulama) {
		this.bozukMalzemeSorgulama=bozukMalzemeSorgulama;
	}

	public Collection getDataForSorguTable() {
		return (MalzemeManager.bozukMalzemeSorgulamabyState());
	}

	public void malzemeIncelemekIcinDialogCikar(
			AbstractMalzemeTanimi malzemeTanimi) {
		AnaController.getInstance().getMalzemeCikisiController()
				.sendSeciliNodeToMalzemeCikisi(malzemeTanimi);
	}
	public Collection getDataForSorguTable(Ambar ambar) {
		return null;
	}
}
