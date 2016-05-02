package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.panel.IMalzemeHareketleriPanel;

public class TabloyuTemizleAction implements Command {

	private IMalzemeHareketleriPanel activePanel;
	
	public TabloyuTemizleAction(IMalzemeHareketleriPanel panel){
		this.activePanel=panel;
	}
	public void execute() {
		((AyniyatTableModel)activePanel.getMalzemelerTable().getModel()).removeAllRows();
	}

}
