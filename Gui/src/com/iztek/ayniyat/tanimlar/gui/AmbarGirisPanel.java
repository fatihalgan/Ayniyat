package com.iztek.ayniyat.tanimlar.gui;

import javax.swing.JOptionPane;

import com.iztek.ayniyat.gui.service.InputDuzenleyici;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.yerlesim.domain.Ambar;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class AmbarGirisPanel extends AbstractGirisPanel{

	private Ambar ambar = null;
	
	public AmbarGirisPanel(String panelName) {
		super(panelName);
		}

	public void loadYourself(IKategorilendirilebilir amb,IKategorilendirilebilir parentNode){
        unLoadYourself(); // ekrani temizle 
        getLabel1().setText("Ambar Adý :");
        if (amb == null){ //yeni ambar girisi
	        this.ambar = new Ambar("");
	        super.anaKategori = parentNode;
	    }else{ //ambar duzeltme iþlemi
	        this.ambar = (Ambar) amb;
	        loadYourself(ambar.getTanim());
	    }
	}
	
	
	public void kaydetAction(){
		if (getTextField().getText().equals(""))
		    JOptionPane.showMessageDialog(AmbarGirisPanel.this,"Ambar tanýmý alaný doldurulmalýdýr!","Eksik bilgi",JOptionPane.ERROR_MESSAGE);
		else{
		    ambar.setTanim(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(getTextField().getText()));
		    			    
		    if (ambar.getId()==null){ //yeni ambar
		        if(agacaYeniNodeGir(ambar))
		        	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
		    }else{ // ambar duzelt
		        if(nodeDuzelt(ambar))
		        	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
		    }
		    
		}
	}
}  
