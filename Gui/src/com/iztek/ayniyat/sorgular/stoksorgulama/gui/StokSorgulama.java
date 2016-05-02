package com.iztek.ayniyat.sorgular.stoksorgulama.gui;


import java.util.Collection;
import java.util.Vector;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.sorgular.gui.AbstractMalzemeSorguFrame;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
/**
 * @author Cagdas CIRIT
 **/
public class StokSorgulama extends AbstractMalzemeSorguFrame {

	public StokSorgulama(String panelName, String titleName) {
		super(panelName, titleName);
		this.controller = AnaController.getInstance().getStokSorguController();
		getController().registerSelf(this);
		setTitle("Ambar Sorgulama");
		sorguTable.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Stok Sorgulama", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, 
				new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
	}
 
	public void inceleAction(){
	    int selectedRow = getSorguTable().getTable().getSelectedRow();
        if(selectedRow!=-1) {
          Collection tanimlar=MalzemeManager.getMalzemeTanimi(((AyniyatTableModel) getSorguTable().getModel()).getTableData(selectedRow));
          AbstractMalzemeTanimi tanim = (AbstractMalzemeTanimi)tanimlar.toArray()[0];
          AnaController.getInstance().getStokSorguController().malzemeIncelemekIcinDialogCikar(tanim);
        }
	}

    public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
    	unLoadYourself();
    	Vector tableData = new Vector();
    	Collection ambarlar = AnaController.getInstance()
    		.getKullanici()
    		.getAmbarlar();      
        for (int i = 0; i < ambarlar.size(); i++) {
           	tableData.add(AnaController.getInstance().getStokSorguController().getDataForSorguTable((Ambar)ambarlar.toArray()[i]));
        }
       	((AyniyatTableModel) getSorguTable().getModel()).fillSorguTable(tableData);
        
    }



	protected String[] getColumnNames() {
		if(columnNames == null)
			columnNames = new String[] {"Ambar","Malzeme Kodu","Malzeme Tanýmý","Stok Miktarý","Birimi"};
		return columnNames;
	}
  }
