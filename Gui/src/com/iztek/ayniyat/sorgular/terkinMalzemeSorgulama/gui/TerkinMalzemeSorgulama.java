package com.iztek.ayniyat.sorgular.terkinMalzemeSorgulama.gui;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.sorgular.gui.AbstractMalzemeSorguFrame;


public class TerkinMalzemeSorgulama extends AbstractMalzemeSorguFrame {

	public TerkinMalzemeSorgulama(String panelName, String titleName) {
		super(panelName, titleName);
		this.controller = AnaController.getInstance().getTerkinMalzemeSorguController();
		getController().registerSelf(this);
		this.setTitle("Terkin Malzeme Sorgulama");
		sorguTable.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Terkin Sorgulama", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
				new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
	
	}

	protected String[] getColumnNames() {
		if(columnNames == null)
			columnNames = new String[] {"Demirbaþ No","Malzeme Tanýmý","TutanakNo","TutanakTarihi","TerkinNedeni","TerkinEden","Açýklama"};
		return columnNames;
	}
}

