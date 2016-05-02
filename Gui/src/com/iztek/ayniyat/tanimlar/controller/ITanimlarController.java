package com.iztek.ayniyat.tanimlar.controller;

import java.awt.Component;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.tanimlar.gui.AbstractTanimlarPanel;

public interface ITanimlarController {
	
	public boolean treePaneleNodeEkle(IKategorilendirilebilir parent,
			IKategorilendirilebilir node);
	public boolean kategoriTanimiDuzelt(IKategorilendirilebilir node);
	public void registerTreePanel(com.iztek.ayniyat.util.uicomponents.TreePanel treePanel);
	public void registerAnaPanel(AbstractTanimlarPanel anaPanel);
	public void removeAllRegisteredElements();
	public abstract void panelOneGetir(String panelAdi);
	public boolean showConfirmationDialogBox(Component parentComponent,
			String message);
	public void showErrorDialogBox(Component parentComponent, String message);
	public abstract String[] getMenuItemNamesForTree(
			IKategorilendirilebilir seciliNode);
	public void setClipBoard(IKategorilendirilebilir seciliNode);
	public void kaynagiHedefeTasi(IKategorilendirilebilir hedefNode);
	public void kaynagiHedefeKopyala(IKategorilendirilebilir hedefNode);
	public void disposeAnaPanel() ;
	public boolean isClipboardEmpty();
}
