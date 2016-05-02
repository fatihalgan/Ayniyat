package com.iztek.ayniyat.tanimlar.gui;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class AmbarGosterisPanel extends AbstractGosterisPanel{


	public AmbarGosterisPanel(String panelName) {
		super(panelName);
	}
 	
	public void loadYourself(IKategorilendirilebilir kategori, IKategorilendirilebilir parentNode){
	    unLoadYourself();	
	    getLabel1().setText("Ambar Adý :");
	    getLabel2().setText(kategori.getTanim());
	 }
	
}
