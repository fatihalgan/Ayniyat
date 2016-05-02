package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.panel.IAbstractPanel;
import com.iztek.ayniyat.panel.IMalzemeHareketleriPanel;

public class KaydetAction implements Command {

	private IMalzemeHareketleriPanel activePanel;
	private IAbstractPanel panel;
	
	public KaydetAction(IMalzemeHareketleriPanel panel){
		this.activePanel=panel;
	}
	public KaydetAction(IAbstractPanel panel){
		this.panel=panel;
	}
	public void execute() {
		activePanel.kaydetAction();
	}
	public void execute1(){
		panel.kaydetAction();
	}

}
