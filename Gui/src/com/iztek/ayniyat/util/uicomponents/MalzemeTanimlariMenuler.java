package com.iztek.ayniyat.util.uicomponents;

import javax.swing.JMenuItem;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.util.uiservice.MenuItemNameFactory;
import com.iztek.ayniyat.util.uiservice.PanelFactory;

public class MalzemeTanimlariMenuler extends PopUpMenuler{

	private JMenuItem grupEkleMenuItem = null;
    private JMenuItem malzemeEkleMenuItem = null;
       
    public MalzemeTanimlariMenuler() {
		super();
	    getGrupEkleMenuItem();
	    getMalzemeEkleMenuItem();
	}

    private JMenuItem getGrupEkleMenuItem() {
	        if (grupEkleMenuItem == null) {
	            grupEkleMenuItem = new JMenuItem();
	            grupEkleMenuItem.setText(MenuItemNameFactory.GRUP_EKLE);
	            menuItems.add(grupEkleMenuItem);
	            grupEkleMenuItem.addActionListener(new java.awt.event.ActionListener() { 
	            	public void actionPerformed(java.awt.event.ActionEvent e) { 
	            		IAyniyatPanel panel = PanelFactory.getService().getPanel(PanelFactory.KATEGORIGIRISPANEL);
	            		panel.loadYourself(null,treePanel.getSeciliNode());
	            		AnaController.getInstance().getCurrentTanimlarController().panelOneGetir(panel.getPanelName());
	            	}
	            });
	        }
	        return grupEkleMenuItem;
	    }
    
    private JMenuItem getMalzemeEkleMenuItem() {
        if (malzemeEkleMenuItem == null) {
            malzemeEkleMenuItem = new JMenuItem();
            malzemeEkleMenuItem.setText(MenuItemNameFactory.MALZEME_EKLE);
            menuItems.add(malzemeEkleMenuItem);
            malzemeEkleMenuItem.addActionListener(new java.awt.event.ActionListener() { 
            	public void actionPerformed(java.awt.event.ActionEvent e) {   
                    IAyniyatPanel panel = PanelFactory.getService().getPanel(PanelFactory.MALZEMEGIRISPANEL);
            		panel.loadYourself(null,treePanel.getSeciliNode());
                    AnaController.getInstance().getCurrentTanimlarController().panelOneGetir(panel.getPanelName());
            	}
            });
        }
        return malzemeEkleMenuItem;
    }

    public JMenuItem getSecMenuItem() {
    	if (secMenuItem == null) {
    		secMenuItem = new JMenuItem();
    		secMenuItem.setText("Seç");
    		menuItems.add(secMenuItem);
    		secMenuItem.addActionListener(new java.awt.event.ActionListener() { 
    			public void actionPerformed(java.awt.event.ActionEvent e) {   
    				AnaController.getInstance().getCurrentTanimlarController().disposeAnaPanel();
    				AnaController.getInstance().sendSeciliNode(treePanel.getSeciliNode());            		
    			}
    		});
    	}
    	return secMenuItem;
    }
}
