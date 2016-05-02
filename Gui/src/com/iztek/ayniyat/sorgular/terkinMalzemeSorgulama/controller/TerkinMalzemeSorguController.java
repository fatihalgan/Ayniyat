package com.iztek.ayniyat.sorgular.terkinMalzemeSorgulama.controller;

import java.util.Collection;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.sorgular.controller.MalzemeSorguController;
import com.iztek.ayniyat.sorgular.gui.AbstractMalzemeSorguFrame;
import com.iztek.ayniyat.yerlesim.domain.Ambar;


public class TerkinMalzemeSorguController implements MalzemeSorguController  {
	AbstractMalzemeSorguFrame terkinMalzemeSorgulama;
	public void registerSelf(AbstractMalzemeSorguFrame terkinMalzemeSorgulama) {
		this.terkinMalzemeSorgulama= terkinMalzemeSorgulama;
	}
	public Collection getDataForSorguTable(){
	   	return (MalzemeManager.terkinMalzemeSorgulama());
	}
	public void malzemeIncelemekIcinDialogCikar(AbstractMalzemeTanimi malzemeTanimi){
		AnaController.getInstance().getMalzemeCikisiController().sendSeciliNodeToMalzemeCikisi(malzemeTanimi);
	}
	public Collection getDataForSorguTable(Ambar ambar) {
		return null;
	}
}

