package com.iztek.ayniyat.util.uiservice;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.tanimlar.gui.AmbarGirisPanel;
import com.iztek.ayniyat.tanimlar.gui.AmbarGosterisPanel;
import com.iztek.ayniyat.tanimlar.gui.BinaGirisPanel;
import com.iztek.ayniyat.tanimlar.gui.BinaGosterisPanel;
import com.iztek.ayniyat.tanimlar.gui.BirimGirisPanel;
import com.iztek.ayniyat.tanimlar.gui.BirimGosterisPanel;
import com.iztek.ayniyat.tanimlar.gui.KategoriGirisPanel;
import com.iztek.ayniyat.tanimlar.gui.KategoriGosterisPanel;
import com.iztek.ayniyat.tanimlar.gui.MalzemeGirisPanel;
import com.iztek.ayniyat.tanimlar.gui.MalzemeGosterisPanel;
import com.iztek.ayniyat.tanimlar.gui.OdaGirisPanel;
import com.iztek.ayniyat.tanimlar.gui.OdaGosterisPanel;
import com.iztek.ayniyat.tanimlar.gui.PersonelGirisPanel;
import com.iztek.ayniyat.tanimlar.gui.PersonelGosterisPanel;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.Bina;
import com.iztek.ayniyat.yerlesim.domain.Birim;
import com.iztek.ayniyat.yerlesim.domain.Oda;
import com.iztek.ayniyat.yerlesim.domain.Personel;

/**
 * @author Cagdas CIRIT
 **/
public class PanelFactory {
	public static final String PERSONELGIRISPANEL = "PersonelGiris";
	public static final String PERSONELGOSTERISPANEL = "PersonelGosteris";
	public static final String ODAGIRISPANEL = "OdaGiris";
	public static final String ODAGOSTERISPANEL = "OdaGosteris";
	public static final String BINAGIRISPANEL = "BinaGiris";
	public static final String BINAGOSTERISPANEL = "BinaGosteris";
	public static final String BIRIMGIRISPANEL = "BirimGiris";
	public static final String BIRIMGOSTERISPANEL = "BirimGosteris";
	public static final String AMBARGIRISPANEL = "AmbarGiris";
	public static final String AMBARGOSTERISPANEL = "AmbarGosteris";

	public static final String KATEGORIGIRISPANEL = "KategoriGiris";
	public static final String KATEGORIGOSTERISPANEL = "KategoriGosteris";
	public static final String MALZEMEGIRISPANEL = "MalzemeGiris";
	public static final String MALZEMEGOSTERISPANEL = "MalzemeGosteris";

	private KategoriGirisPanel kategoriGirisPanel = null;
	private MalzemeGirisPanel malzemeGirisPanel = null;
	private MalzemeGosterisPanel malzemeGosterisPanel = null;
	private KategoriGosterisPanel kategoriGosterisPanel = null;
	private PersonelGirisPanel personelGirisPanel = null;
	private PersonelGosterisPanel personelGosterisPanel = null;
	private OdaGirisPanel odaGirisPanel = null;
	private OdaGosterisPanel odaGosterisPanel = null;
	private BinaGirisPanel binaGirisPanel = null;
	private BinaGosterisPanel binaGosterisPanel = null;
	private BirimGirisPanel birimGirisPanel = null;
	private BirimGosterisPanel birimGosterisPanel = null;
	private AmbarGirisPanel ambarGirisPanel = null;
	private AmbarGosterisPanel ambarGosterisPanel = null;

	private static PanelFactory service;
    
    private PanelFactory(){}  
    
	public static PanelFactory getService() {
		if (service == null)
			service = new PanelFactory();
		return service;
	}
	
    public IAyniyatPanel getGosterisPanelByNode(IKategorilendirilebilir node){
    	if (node instanceof Personel)
    		return personelGosterisPanel;
    	else if (node instanceof Oda)
    		return odaGosterisPanel;
    	else if (node instanceof Bina)
    		return binaGosterisPanel;
    	else if (node instanceof Birim)
    		return birimGosterisPanel;
    	else if (node instanceof Ambar)
    		return ambarGosterisPanel;
    	else{
    		if (node instanceof IMalzemeTanimi)
        		return malzemeGosterisPanel;
    		else 
        		return kategoriGosterisPanel;
        	}
    }
    
    public IAyniyatPanel getGirisPanelByNode(IKategorilendirilebilir node){
    	if (node instanceof Personel)
    		return personelGirisPanel;
    	else if (node instanceof Oda)
    		return odaGirisPanel;
    	else if (node instanceof Bina)
    		return binaGirisPanel;
    	else if (node instanceof Birim)
    		return birimGirisPanel;
    	else if (node instanceof Ambar)
    		return ambarGirisPanel;
    	else {
    		if (node instanceof IMalzemeTanimi)
        		return malzemeGirisPanel;
        	else 
        		return kategoriGirisPanel;
    		}
    		
    }
    
    public IAyniyatPanel getPanel(String panelName){
    	if(panelName.equals(PERSONELGIRISPANEL))
    	   return personelGirisPanel;
    	else if(panelName.equals(PERSONELGOSTERISPANEL))
    	   return personelGosterisPanel;
    	else if(panelName.equals(ODAGIRISPANEL))
    		return odaGirisPanel;
    	else if(panelName.equals(ODAGOSTERISPANEL))
    		return odaGosterisPanel;
    	else if(panelName.equals(BINAGIRISPANEL))
    		return binaGirisPanel;
    	else if(panelName.equals(BINAGOSTERISPANEL))
    		return binaGosterisPanel;
    	else if(panelName.equals(BIRIMGIRISPANEL))
    		return birimGirisPanel;
    	else if(panelName.equals(BIRIMGOSTERISPANEL))
    		return birimGosterisPanel;
    	else if(panelName.equals(AMBARGIRISPANEL))
    		return ambarGirisPanel;
    	else if(panelName.equals(AMBARGOSTERISPANEL))
    		return ambarGosterisPanel;
    	else if(panelName.equals(KATEGORIGIRISPANEL))
    		return kategoriGirisPanel;
    	else if(panelName.equals(MALZEMEGIRISPANEL))
    		return malzemeGirisPanel;
    	else if(panelName.equals(MALZEMEGOSTERISPANEL))
    		return malzemeGosterisPanel;
    	else if(panelName.equals(KATEGORIGOSTERISPANEL))
    		return kategoriGosterisPanel;
    	else return null;
    }
	 
	public void registerPersonelGirisPanel(PersonelGirisPanel personelGirisPanel) {
		this.personelGirisPanel = personelGirisPanel;
	}
	
	public void registerPersonelGosterisPanel(PersonelGosterisPanel personelGosterisPanel) {
		this.personelGosterisPanel = personelGosterisPanel;
	}
	
	public void registerOdaGirisPanel(OdaGirisPanel odaGirisPanel) {
		this.odaGirisPanel = odaGirisPanel;
	}
	
	public void registerOdaGosterisPanel(OdaGosterisPanel odaGosterisPanel) {
		this.odaGosterisPanel = odaGosterisPanel;
	}
	
	public void registerBinaGirisPanel(BinaGirisPanel binaGirisPanel) {
		this.binaGirisPanel = binaGirisPanel;
	}

	public void registerAmbarGirisPanel(AmbarGirisPanel ambarGirisPanel) {
		this.ambarGirisPanel = ambarGirisPanel;
	}
	
	public void registerAmbarGosterisPanel(AmbarGosterisPanel ambarGosterisPanel) {
		this.ambarGosterisPanel = ambarGosterisPanel;
	}
	
	public void registerBinaGosterisPanel(BinaGosterisPanel binaGosterisPanel) {
		this.binaGosterisPanel = binaGosterisPanel;
	}

	public void registerBirimGirisPanel(BirimGirisPanel birimGirisPanel) {
		this.birimGirisPanel = birimGirisPanel;
	}
	
	public void registerBirimGosterisPanel(BirimGosterisPanel birimGosterisPanel) {
		this.birimGosterisPanel = birimGosterisPanel;
	}
	public void registerKategoriGirisPanel(KategoriGirisPanel kategoriPanel) {
        this.kategoriGirisPanel = kategoriPanel; 
    }
    
    public void registerMalzemeGirisPanel(MalzemeGirisPanel malzemeGirisPanel) {
    	this.malzemeGirisPanel = malzemeGirisPanel;
     }  
    
    public void registerMalzemeGosterisPanel(MalzemeGosterisPanel malzemeGosterisPanel) {
    	this.malzemeGosterisPanel = malzemeGosterisPanel;
    }
    
    public void registerKategoriGosterisPanel(KategoriGosterisPanel kategoriGosterisPanel) {
    	this.kategoriGosterisPanel = kategoriGosterisPanel;
    }
	
    public void removeAllRegisteredElements(){
    	personelGirisPanel = null;
    	personelGosterisPanel = null;
    	odaGirisPanel = null;
    	odaGosterisPanel = null;
    	binaGirisPanel = null;
    	binaGosterisPanel = null;
    	birimGirisPanel = null;
    	birimGosterisPanel = null;
    	ambarGirisPanel = null;
    	ambarGosterisPanel = null;
    	malzemeGirisPanel = null;
    	malzemeGosterisPanel = null;
    	kategoriGosterisPanel = null;
        kategoriGirisPanel = null;
    }

}
