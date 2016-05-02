package com.iztek.ayniyat.tanimlar.gui;

import javax.swing.JOptionPane;

import com.iztek.ayniyat.gui.service.InputDuzenleyici;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.yerlesim.domain.Birim;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */

public class BirimGirisPanel extends AbstractGirisPanel{

	private Birim birim = null;

	public BirimGirisPanel(String panelName) {
		super(panelName);
	}

	public void loadYourself(IKategorilendirilebilir bir,IKategorilendirilebilir parentNode){
        unLoadYourself(); // ekrani temizle 
        getLabel1().setText("Birim Adý :");
	    if (bir == null){ //yeni birim girisi
	        this.birim = new Birim("");
	        super.anaKategori = parentNode;
	    }else{ //birim duzeltme iþlemi
	        this.birim = (Birim) bir;
	        super.loadYourself(birim.getTanim());
	    }
	}
	
	public void kaydetAction(){
		 if (super.getTextField().getText().equals(""))
			    JOptionPane.showMessageDialog(BirimGirisPanel.this,"Birim tanýmý alaný doldurulmalýdýr!","Eksik bilgi",JOptionPane.ERROR_MESSAGE);
			else{
			    birim.setTanim(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(getTextField().getText()));
			    			    
			    if (birim.getId()==null){ //yeni malzeme
			        if(super.agacaYeniNodeGir(birim))
			        	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
			    }else{ // malzeme duzelt
			        if(super.nodeDuzelt(birim))
			        	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
			    }
			    
			}
	}
}  
