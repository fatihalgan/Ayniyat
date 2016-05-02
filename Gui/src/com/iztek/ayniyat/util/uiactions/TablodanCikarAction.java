package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.panel.IMalzemeHareketleriPanel;

public class TablodanCikarAction implements Command {

	private IMalzemeHareketleriPanel activePanel;
	
	public TablodanCikarAction(IMalzemeHareketleriPanel panel){
		this.activePanel=panel;
	}
	public void execute() {
		int row =activePanel.getMalzemelerTable().getTable().getSelectedRow();
		    if (row!=-1)
		    	((AyniyatTableModel)activePanel.getMalzemelerTable().getModel()).removeRow(row);
	}
}
