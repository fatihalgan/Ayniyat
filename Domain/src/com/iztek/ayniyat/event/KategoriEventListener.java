package com.iztek.ayniyat.event;

import javax.swing.JOptionPane;
import com.iztek.ayniyat.kategori.service.AbstractKategoriManager;

public class KategoriEventListener implements IKategoriEventListener{
	
	public void addKategori(KategoriEvent kategoriEvent) {
    	AbstractKategoriManager.createKategori(kategoriEvent.getSourceNode(),kategoriEvent.getDestinationNode());
	}

	public void removeKategori(KategoriRemoveEvent kategoriRemoveEvent) {
        AbstractKategoriManager.deleteKategori(kategoriRemoveEvent.getDestinationNode());       
	}

	public void changeKategori(KategoriEvent kategoriEvent) {
		AbstractKategoriManager.updateKategori(kategoriEvent.getDestinationNode());
	}

	public void copyKategori(KategoriEvent kategoriEvent) {
	   try{
	       AbstractKategoriManager.copyKategori(kategoriEvent.getDestinationNode(),kategoriEvent.getSourceNode());
	    }
	    catch(IllegalStateException e){
	        JOptionPane.showMessageDialog(null,e.getLocalizedMessage(),"Uyarý",JOptionPane.WARNING_MESSAGE);
	    }	    
	}

	public void cutKategori(KategoriCutEvent kategoriCutEvent) {
	   try{
	    AbstractKategoriManager.cutKategori(kategoriCutEvent.getDestinationNode(), kategoriCutEvent.getSourceNode());
	    }catch(IllegalStateException e){
	        JOptionPane.showMessageDialog(null,e.getLocalizedMessage(),"Uyarý",JOptionPane.WARNING_MESSAGE);
	        kategoriCutEvent.setCommitOperation(false);
	        return;
	    }
	    kategoriCutEvent.setCommitOperation(true);
	      
	}
}
