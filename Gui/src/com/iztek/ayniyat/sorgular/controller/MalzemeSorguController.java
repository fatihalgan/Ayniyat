package com.iztek.ayniyat.sorgular.controller;

import java.util.Collection;

import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.sorgular.gui.AbstractMalzemeSorguFrame;
import com.iztek.ayniyat.yerlesim.domain.Ambar;

public interface MalzemeSorguController {
	public void registerSelf(AbstractMalzemeSorguFrame frame);
	public Collection getDataForSorguTable();
	public Collection getDataForSorguTable(Ambar ambar);
	public void malzemeIncelemekIcinDialogCikar(AbstractMalzemeTanimi malzemeTanimi);
}
