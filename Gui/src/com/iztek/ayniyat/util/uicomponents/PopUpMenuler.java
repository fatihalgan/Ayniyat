package com.iztek.ayniyat.util.uicomponents;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.util.uiservice.MenuItemNameFactory;
import com.iztek.ayniyat.util.uiservice.PanelFactory;

public abstract class PopUpMenuler extends JPopupMenu {
	/**
	 * @author fusun
	 */

	private JMenuItem silMenuItem = null;
	private JMenuItem duzenleMenuItem = null;
	private JMenuItem kesMenuItem = null;
	private JMenuItem kopyalaMenuItem = null;
	private JMenuItem yapistirMenuItem = null;
	public JMenuItem secMenuItem = null;
	public ArrayList menuItems = null;
	private boolean isSecilenMalzemeTasinacak = false;
	public TreePanel treePanel;
	public JPopupMenu pop;
   

	public PopUpMenuler() {
		super();
		menuItems = new ArrayList(12);

		setBorder(BorderFactory.createRaisedBevelBorder());
		getSilMenuItem();
		getDuzenleMenuItem();
		getKesMenuItem();
		getKopyalaMenuItem();
		getYapistirMenuItem();
		getSecMenuItem();
	}

	public abstract JMenuItem getSecMenuItem();

	public void registerTreePanel(TreePanel panel) {
		treePanel = panel;
	}

	private JMenuItem getSilMenuItem() {
		if (silMenuItem == null) {
			silMenuItem = new JMenuItem();
			silMenuItem.setText(MenuItemNameFactory.SIL);
			menuItems.add(silMenuItem);
			silMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					treePanel.removeKategori();
				}
			});
		}
		return silMenuItem;
	}

	private JMenuItem getDuzenleMenuItem() {
		if (duzenleMenuItem == null) {
			duzenleMenuItem = new JMenuItem();
			duzenleMenuItem.setText(MenuItemNameFactory.DUZENLE);
			menuItems.add(duzenleMenuItem);
			duzenleMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							IKategorilendirilebilir updateNode = treePanel
									.getSeciliNode();
							IAyniyatPanel panel = PanelFactory.getService()
									.getGirisPanelByNode(updateNode);
							panel.loadYourself(updateNode, updateNode
									.getAnaKategori());
							AnaController.getInstance()
									.getCurrentTanimlarController()
									.panelOneGetir(panel.getPanelName());
						}
					});
		}
		return duzenleMenuItem;
	}

	private JMenuItem getKesMenuItem() {
		if (kesMenuItem == null) {
			kesMenuItem = new JMenuItem();
			kesMenuItem.setText(MenuItemNameFactory.KES);
			menuItems.add(kesMenuItem);
			kesMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AnaController.getInstance().getCurrentTanimlarController()
							.setClipBoard(treePanel.getSeciliNode());
					isSecilenMalzemeTasinacak = true;
				}
			});
		}
		return kesMenuItem;
	}

	private JMenuItem getKopyalaMenuItem() {
		if (kopyalaMenuItem == null) {
			kopyalaMenuItem = new JMenuItem();
			kopyalaMenuItem.setText(MenuItemNameFactory.KOPYALA);
			menuItems.add(kopyalaMenuItem);
			kopyalaMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							AnaController.getInstance()
									.getCurrentTanimlarController()
									.setClipBoard(treePanel.getSeciliNode());
							isSecilenMalzemeTasinacak = false;
						}
					});
		}
		return kopyalaMenuItem;
	}

	public JMenuItem getYapistirMenuItem() {
		if (yapistirMenuItem == null) {
			yapistirMenuItem = new JMenuItem();
			yapistirMenuItem.setText(MenuItemNameFactory.YAPISTIR);
			menuItems.add(yapistirMenuItem);
			yapistirMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (isSecilenMalzemeTasinacak)
								AnaController.getInstance()
										.getCurrentTanimlarController()
										.kaynagiHedefeTasi(
												treePanel.getSeciliNode());
							else
								AnaController.getInstance()
										.getCurrentTanimlarController()
										.kaynagiHedefeKopyala(
												treePanel.getSeciliNode());

							isSecilenMalzemeTasinacak = false;
						}
					});
		}
		return yapistirMenuItem;
	}

	public void unLoadPopUpMenu() {
		removeAll();
	}

	public JMenuItem getMenuItemByName(String name) {
		Iterator iter = menuItems.iterator();

		while (iter.hasNext()) {
			JMenuItem element = (JMenuItem) iter.next();
			if (element.getText().equals(name))
				return element;
		}
		return null;
	}

	private void loadPopUpMenu(String[] items) {
		unLoadPopUpMenu();//clear popUp

		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(MenuItemNameFactory.SEPERATOR))
					pop.addSeparator();
				else
					pop.add(getMenuItemByName(items[i]));
			}
			// yapýþtýrý kontrol et
			getYapistirMenuItem().setEnabled(
					!AnaController.getInstance().getCurrentTanimlarController()
							.isClipboardEmpty());
		}
	}
}
