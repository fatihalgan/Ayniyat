package com.iztek.ayniyat.tanimlar.gui;

import javax.swing.JOptionPane;

import com.iztek.ayniyat.gui.service.InputDuzenleyici;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.yerlesim.domain.Bina;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class BinaGirisPanel extends AbstractGirisPanel{

	private Bina bina = null;
	
	public BinaGirisPanel(String panelName) {
		super(panelName);
	}

	public void loadYourself(IKategorilendirilebilir bin,IKategorilendirilebilir parentNode){
        unLoadYourself(); // ekrani temizle 
        getLabel1().setText("Bina Adý :");
        if (bin == null){ //yeni bina girisi
	        this.bina = new Bina("");
	        super.anaKategori = parentNode;
	    }else{ //bina duzeltme iþlemi
	        this.bina = (Bina) bin;
	        loadYourself(bina.getTanim());
	    }
	}
	
	public void kaydetAction(){
		if (getTextField().getText().equals(""))
		    JOptionPane.showMessageDialog(BinaGirisPanel.this,"Bina tanýmý alaný doldurulmalýdýr!","Eksik bilgi",JOptionPane.ERROR_MESSAGE);
		else{
		    bina.setTanim(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(getTextField().getText()));
		    			    
		    if (bina.getId()==null){ //yeni malzeme
		        if(agacaYeniNodeGir(bina))
		        	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
		    }else{ // malzeme duzelt
		        if(super.nodeDuzelt(bina))
		        	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
		    }
		    
		}
	}
}  
