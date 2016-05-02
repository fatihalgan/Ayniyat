package com.iztek.ayniyat.tanimlar.gui;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class BirimGosterisPanel extends AbstractGosterisPanel{


	public BirimGosterisPanel(String panelName) {
		super(panelName);
	}
	
	public void loadYourself(IKategorilendirilebilir kategori, IKategorilendirilebilir parentNode){
	    unLoadYourself();	
	    getLabel1().setText("Birim Adý :");
	    getLabel2().setText(kategori.getTanim());
	}

}