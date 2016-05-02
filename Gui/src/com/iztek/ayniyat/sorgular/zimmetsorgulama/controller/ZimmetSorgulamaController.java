/*
 * Created on 12.Haz.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.sorgular.zimmetsorgulama.controller;

import java.util.Collection;
import java.util.Vector;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.model.FilterComboModel;
import com.iztek.ayniyat.sorgular.zimmetsorgulama.gui.ZimmetSorgulama;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.util.persistence.HibernateUtil;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZimmetSorgulamaController {
    ZimmetSorgulama zimmetSorgulama;
   public  String zSahibi=null;
    
	public void registerZimmetSorgulama(ZimmetSorgulama zimmetSorgulama) {
		this.zimmetSorgulama=zimmetSorgulama;
	}
	
	public void demirbasNumarasindanSorgula(String demirbasNo){
        Vector demirbaslar=new Vector();
		IDemirbasMalzeme demirbas=MalzemeManager.findDemirbasByDemirbasNo(demirbasNo);
		 if(demirbas != null) 
		     demirbaslar.add(demirbas);
        sendDemirbaslarToTable(demirbaslar);
	}
	/**
	 * @author fusun
	 *  */

	public void zimmetSahibindenSorgula(AbstractZimmetAlan zimmetSahibi) {
		this.zSahibi=zimmetSahibi.getTanim().toString();
		Vector demirbaslar = new Vector();
		Collection returnVal = MalzemeManager
				.getAltKategorilerindekiZimmetliMalzemeler(zimmetSahibi);
		for (int j = 0; j < returnVal.size(); j++) {
			demirbaslar.add(returnVal.toArray()[j]);
		}
		sendDemirbaslarToTable(demirbaslar);
	}
	
	public void tanimindanSorgula(AbstractMalzemeTanimi malzemeTanimi) {
		Vector demirbaslar = new Vector();
		Collection returnVal = MalzemeManager
				.getZimmetliDemirbaslarByMalzemeTanimi(malzemeTanimi);
		for (int j = 0; j < returnVal.size(); j++) {
			demirbaslar.add(returnVal.toArray()[j]);
		}
		sendDemirbaslarToTable(demirbaslar);
	}
	
 	 public void sendDemirbaslarToTable(Vector demirbaslar) {
		Vector coll = new Vector();
		for (int i = 0; i < demirbaslar.size(); i++) {
			DemirbasMalzeme demirbas = (DemirbasMalzeme) demirbaslar.get(i);
			Vector row = new Vector();
			row.add(demirbas);
			row.add(demirbas.getMalzemeTanimi().toString());
			row.add(demirbas.getMalzemeTanimi().getBirim());
			Session session = HibernateUtil.getSession();
			session.lock(demirbas, LockMode.UPGRADE);
			row.add(demirbas.getZimmetSahibi().toString());
			HibernateUtil.closeSession();
			coll.add(row);
			((AyniyatTableModel) zimmetSorgulama.getSorguTable().getModel())
					.addRow2LastRow(row);
		}
		((FilterComboModel) zimmetSorgulama.getCriteriaCmbBox().getModel())
				.setModelData(coll,1);
		((FilterComboModel) zimmetSorgulama.getCriteria1CmbBox().getModel())
				.setModelData(coll,3);
		zimmetSorgulama.gettLabel().setText(
				"" + zimmetSorgulama.getSorguTable().getTable().getRowCount());
	}
}
