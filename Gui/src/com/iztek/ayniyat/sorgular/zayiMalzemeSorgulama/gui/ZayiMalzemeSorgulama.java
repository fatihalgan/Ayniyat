package com.iztek.ayniyat.sorgular.zayiMalzemeSorgulama.gui;



import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.sorgular.gui.AbstractMalzemeSorguFrame;


public class ZayiMalzemeSorgulama extends AbstractMalzemeSorguFrame {

	public ZayiMalzemeSorgulama(String panelName, String titleName) {
		super(panelName, titleName);
		this.controller = AnaController.getInstance().getZayiMalzemeSorguController();
		getController().registerSelf(this);
		this.setTitle("Zayi Malzeme Sorgulama");
		sorguTable.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Zayi Sorgulama", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
				new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
	
	}

	protected String[] getColumnNames() {
		if(columnNames == null)
			columnNames = new String[] {"Demirbaþ No","Malzeme Tanýmý","TutanakNo","TutanakTarihi","ZayiNedeni","ZayiEden","Açýklama"};
		return columnNames;
	}
}