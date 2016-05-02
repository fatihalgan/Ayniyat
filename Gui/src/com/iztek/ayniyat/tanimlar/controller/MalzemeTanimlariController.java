package com.iztek.ayniyat.tanimlar.controller;

import java.util.Collection;

import javax.swing.JOptionPane;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.tanimlar.gui.MalzemeTanimlariAnaPanel;
import com.iztek.ayniyat.util.uiservice.MenuItemNameFactory;

public class MalzemeTanimlariController extends AbstractController{
    
    
    public MalzemeTanimlariController() {
		super();
		
	}

	public void panelOneGetir(String panelAdi){
		((MalzemeTanimlariAnaPanel)anaPanel).getCardLayout().
		show(anaPanel.getRightPanel(),panelAdi);
    }
 
    public boolean malzemeTanimiDuzelt(IKategorilendirilebilir node, Collection retains){
    	if (!node.getAnaKategori().getNodeValue().trim().equalsIgnoreCase(node.getNodeValue().trim())){ // eklenecek çocuk parenti ile ayni koda ve ada sahip olmamalý
    		treePanel.updateKategori(node,retains);
    		return true;
    	}else{
    		JOptionPane.showMessageDialog(null,"Ana Kategoriyle ayni kod ve ismi veremezsiniz!","Uyarý",JOptionPane.WARNING_MESSAGE);
    		return false;
    	}
    }  
 	
	public void setAnaPanelVisible(boolean value){
		anaPanel.setVisible(value);
	}
   
    public String[] getMenuItemNamesForTree(IKategorilendirilebilir seciliNode){
    	if(AnaController.getInstance().getCurrentMalzemeTanimlariPanelRole().equals(AnaController.MALZEMETANIMLARI_FOR_TANIM))
    		return MenuItemNameFactory.getMenuItems(seciliNode);
    	else if (seciliNode instanceof IMalzemeTanimi)
    		return new String[] {"Seç"};
		return null;
    }
}
