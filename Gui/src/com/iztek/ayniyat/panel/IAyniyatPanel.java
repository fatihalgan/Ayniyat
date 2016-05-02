package com.iztek.ayniyat.panel;

import javax.swing.JTable;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Cagdas CIRIT
 **/
public interface IAyniyatPanel {
	public String getPanelName();
	public void initialize();
	public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode);
	public void unLoadYourself();
}
