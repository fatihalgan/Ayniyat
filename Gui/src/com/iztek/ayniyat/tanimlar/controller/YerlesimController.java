package com.iztek.ayniyat.tanimlar.controller;

import java.util.Vector;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.StateZimmetli;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.tanimlar.gui.YerlesimAnaPanel;
import com.iztek.ayniyat.util.uiservice.MenuItemNameFactory;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Personel;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class YerlesimController extends AbstractController{
	

	public YerlesimController() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void panelOneGetir(String panelAdi) {
		(((YerlesimAnaPanel)anaPanel).getCardLayout()).
		show(((YerlesimAnaPanel)anaPanel).getKategoriPanel(),panelAdi);
		if(panelAdi.equals("bos"))
			demirbasTablosunuTemizle();
	}
   public void zimmetliDemirbaslariTabloyaYukle(AbstractZimmetAlan zimmetAlan){
    	Session session = HibernateUtil.getSession();
       	session.lock(zimmetAlan, LockMode.NONE);
    	Object [] demirbaslar = zimmetAlan.getZimmetliMalzemeler().toArray();   	
       	Vector demirbasVeri = new Vector(demirbaslar.length);
    	for (int i = 0; i < demirbaslar.length; i++) {
			IDemirbasMalzeme demirbas = (IDemirbasMalzeme) demirbaslar[i];
			Vector temp = new Vector(3);
			if(AnaController.getInstance().getCurrentYerlesimPanelRole().equals(AnaController.YERLESIM_FOR_DIALOG_DEMIRBAS_SEC))
			    temp.add(Boolean.FALSE);
			if(demirbas.getState().getType().equals(new StateZimmetli().getType())){
			temp.add(demirbas);
			temp.add(((AbstractMalzemeTanimi)demirbas.getMalzemeTanimi()).getTanim());
			temp.add(zimmetAlan.getTanim());
			demirbasVeri.add(temp);
			}
    	}
    	HibernateUtil.closeSession();
    	((YerlesimAnaPanel)anaPanel).loadDemirbasTable(demirbasVeri);
    }
    public void personelUzerindekiTumDemirbaslariTabloyaYukle(Personel personel){
    	
    	Object [] personeller = YerlesimManager.findPersonelBySicilNo(personel.getSicilNo()).toArray();
    	
   	
    	for (int i = 0; i < personeller.length; i++) 
   		zimmetliDemirbaslariTabloyaYukle((AbstractZimmetAlan) personeller[i]);
  
    }
    
    
    
    
    public void zimmetAlanAltindakiTumDemirbaslariTabloyaYukle(AbstractZimmetAlan abstractZimmetAlan){

		zimmetliDemirbaslariTabloyaYukle(abstractZimmetAlan);
		
		Session session = HibernateUtil.getSession();
		session.lock(abstractZimmetAlan, LockMode.NONE);
		try {
			for (int i = 0; i < abstractZimmetAlan.getAltKategoriler().size(); i++) {
				zimmetAlanAltindakiTumDemirbaslariTabloyaYukle((AbstractZimmetAlan)abstractZimmetAlan.getAltKategoriler().toArray()[i]);
			}
		} catch(Throwable t) {
			Debug.instance().println(t);
		} finally {
			HibernateUtil.closeSession();
		}
    	
    }
    
    public void demirbasTablosunuTemizle(){
    	((YerlesimAnaPanel)anaPanel).demirbasTablonuTemizle();
    }
    
    public Vector demirbasTablosundakiSeciliDemirbaslariDondur(){
        return ((YerlesimAnaPanel)anaPanel).getSeciliRowsForDemirbaslar();
    }
    
    public String[] getMenuItemNamesForTree(IKategorilendirilebilir seciliNode){
    	if(AnaController.getInstance().getCurrentYerlesimPanelRole().equals(AnaController.YERLESIM_FOR_TANIM))
    		return MenuItemNameFactory.getMenuItems(seciliNode);
    	return new String[] {"Seç"};
    }
    
   
}