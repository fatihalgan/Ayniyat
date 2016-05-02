package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.panel.IMalzemeHareketleriPanel;

public class TabloyaEkleAction implements Command {

	private IMalzemeHareketleriPanel activePanel;
	
	public TabloyaEkleAction(IMalzemeHareketleriPanel panel){
		this.activePanel=panel;
	}
	public void execute() {
		activePanel.tabloyaEkleAction();
	}

}
