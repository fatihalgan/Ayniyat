package com.iztek.ayniyat.tanimlar.controller;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.tanimlar.gui.AbstractTanimlarPanel;
import com.iztek.ayniyat.util.uicomponents.TreePanel;


public abstract class AbstractController implements ITanimlarController {

/**
 * @author fusun
 */
	public AbstractTanimlarPanel anaPanel = null;

	public TreePanel treePanel=null;

	private IKategorilendirilebilir clipBoard = null;

	public AbstractController() {
		super();
		
	}

	public abstract void panelOneGetir(String panelAdi);

	public void registerTreePanel(TreePanel treePanel) {
		this.treePanel = treePanel;
	}

	public void registerAnaPanel(AbstractTanimlarPanel anaPanel) {
		this.anaPanel = anaPanel;
	}

	public void removeAllRegisteredElements() {
		this.anaPanel = null;
		this.treePanel = null;
	}

	public void disposeAnaPanel() {
		anaPanel.dispose();
	}

	public void kaynagiHedefeTasi(IKategorilendirilebilir hedefNode) {
		if (showConfirmationDialogBox(anaPanel, "Seçilen node '" + hedefNode
				+ "' üzerine taþýmak istiyor musunuz?")) {
			treePanel.cutKategori(hedefNode, getClipBoard());
			clipBoard = null;
		}
	}

	public void kaynagiHedefeKopyala(IKategorilendirilebilir hedefNode) {
		treePanel.copyKategori(hedefNode, getClipBoard());
		clipBoard = null;
	}

	public boolean treePaneleNodeEkle(IKategorilendirilebilir parent,
			IKategorilendirilebilir node) {
		if (!parent.getNodeValue().trim().equalsIgnoreCase(
				node.getNodeValue().trim())) { // eklenecek çocuk parenti ile ayni ada sahip olmamalý   		
			return treePanel.addKategori(parent, node, true);
		} else {
			JOptionPane.showMessageDialog(null,
					"Ana Kategoriyle ayni kod ve ismi veremezsiniz!", "Uyarý",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	public boolean kategoriTanimiDuzelt(IKategorilendirilebilir node) {
		if (node.getAnaKategori() != null) {
			if (!node.getAnaKategori().getNodeValue().trim().equalsIgnoreCase(
					node.getNodeValue().trim())) { // eklenecek çocuk parenti ile ayni ada sahip olmamalý
				treePanel.updateKategori(node, null);
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Ana Kategoriyle ayni ismi veremezsiniz!", "Uyarý",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} else {
			treePanel.updateKategori(node, null);
			return true;
		}
	}

	/**
	 * @author Cagdas CIRIT
	 */
	public boolean showConfirmationDialogBox(Component parentComponent,
			String message) {
		Object[] options = { "Evet", "Hayýr" };
		if (JOptionPane.showOptionDialog(parentComponent, message, "Onay",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[1]) == JOptionPane.YES_OPTION)
			return true;
		return false;
	}

	public void showErrorDialogBox(Component parentComponent, String message) {
		JOptionPane.showMessageDialog(parentComponent, message, "Hata",
				JOptionPane.ERROR_MESSAGE);
	}

	public void setClipBoard(IKategorilendirilebilir seciliNode) {
		clipBoard = seciliNode;
	}

	public IKategorilendirilebilir getClipBoard() {
		return clipBoard;
	}

	public boolean isClipboardEmpty() {
		return clipBoard == null;
	}

	public abstract String[] getMenuItemNamesForTree(
			IKategorilendirilebilir seciliNode);

}
