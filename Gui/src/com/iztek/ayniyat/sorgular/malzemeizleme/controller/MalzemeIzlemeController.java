/*
 * Created on 12.Haz.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.sorgular.malzemeizleme.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.HareketYeri;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketiManager;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.sorgular.malzemeizleme.gui.MalzemeIzleme;
import com.iztek.ayniyat.sorgular.malzemeizleme.gui.MalzemeListesiPanel;
import com.iztek.util.persistence.HibernateUtil;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MalzemeIzlemeController {
    MalzemeIzleme malzemeIzleme;
    
	public void registerMalzemeIzleme(MalzemeIzleme malzemeIzleme) {
		this.malzemeIzleme=malzemeIzleme;
	}
	
	public void demirbasNumarasindanIzle(String demirbasNo){
		IDemirbasMalzeme demirbas=MalzemeManager.findDemirbasByDemirbasNo(demirbasNo);
		if(demirbas != null)
		    malzemeBilgileriniGoster((AbstractMalzeme) demirbas);
	}
	
	public void zimmetlerdenIzle(Vector zimmetler){
	    if(!zimmetler.isEmpty()){
	        malzemeBilgileriniGoster((AbstractMalzeme)zimmetler.get(0));
	    }
	}

	public void tanimindanIzle(AbstractMalzemeTanimi malzemeTanimi){
        Collection returnVal= MalzemeManager.getDemirbaslarByMalzemeTanimi(malzemeTanimi);
        if(!returnVal.isEmpty()){
    	    MalzemeListesiPanel liste=new MalzemeListesiPanel(AnaController.getInstance().getAnaPanel());
	        for (int j = 0; j < returnVal.size(); j++) {
	            liste.tabloyaMalzemeEkle((DemirbasMalzeme)returnVal.toArray()[j]);
	        }
	        liste.setVisible(true);
        }  
	}
	
	
	
	public void malzemeBilgileriniGoster(AbstractMalzeme malzeme){
	    malzemeIzleme.unLoadYourself();
		bilgileriPaneleAktar(malzeme);
		nitelikleriPaneleAktar(malzeme.getNitelikDegerleri());
		hareketleriPaneleAktar(malzeme);
	}
	
	public void bilgileriPaneleAktar(AbstractMalzeme malzeme){
	    malzemeIzleme.getTanimTextField().setText(malzeme.getMalzemeTanimi().getNodeValue());
	    malzemeIzleme.getBirimTextField().setText(malzeme.getMalzemeTanimi().getBirim());
	    if(malzeme.getState() != null)
	        malzemeIzleme.getStateTextField().setText(malzeme.getState().getType());
	    if(malzeme instanceof DemirbasMalzeme)
	        malzemeIzleme.getDemirbasNoTextField().setText(((DemirbasMalzeme)malzeme).getDemirbasNo().toString());
	}
	
	public void nitelikleriPaneleAktar(Set nitelikDegerleri){	    
	    Iterator iter = nitelikDegerleri.iterator();
	    while(iter.hasNext()){
	        NitelikDegeri nitelikDegeri=(NitelikDegeri)iter.next();
	        Vector row =new Vector();
	        row.add(nitelikDegeri.getNitelikAdi());
	        row.add(nitelikDegeri.getNitelikDegeri());
	        ((AyniyatTableModel)malzemeIzleme.getNiteliklerTable().getModel()).addRow2LastRow(row);
	    }	    
	}
	
    public void hareketleriPaneleAktar(AbstractMalzeme malzeme){

	    Collection col = MalzemeHareketiManager.getMalzemeHareketleriOrderByBelgeNo(malzeme);
	    Iterator iter=col.iterator();

	   while(iter.hasNext()){
	       AbstractMalzemeHareketi hareket= (AbstractMalzemeHareketi) iter.next();	       
	        Vector row =new Vector();
	        
	        row.add(hareket.getType());	        
	        //Bütün malzeme giriþleri normal giriþ senaryosuna göre yapýlmadýðý için null deðerler olabiliyor
	        //Ayrýca giriþ hareketinde kaynak olmadýðýndan null oluyor
            Session session = HibernateUtil.getSession();
        	session.lock(hareket,LockMode.READ);
	        try {
                row.add(((HareketYeri)hareket.getHareketKaynagi()).getHareketYeri().toString());
            } catch (Exception e) {
                System.out.println(e);
                row.add("");
            }
	        try {
                row.add(((HareketYeri)hareket.getHareketHedefi()).getHareketYeri().toString());
            } catch (Exception e) {
                System.out.println(e);
                row.add("");
            }
	        try {
                row.add(hareket.getHareketTarihi().toString());
            } catch (Exception e) {
                row.add("");
            }
	        try {
                row.add(((HareketYeri)hareket.getGeciciSahip()).getHareketYeri().toString());
            } catch (Exception e) {
                System.out.println(e);
                row.add("");
            }
	        try {
                row.add(hareket.getHareketFisi().getBelgeNo().getBelgeNo());
            } catch (Exception e) {
                row.add("");
            }

	        HibernateUtil.closeSession();  
            ((AyniyatTableModel)malzemeIzleme.getHareketlerTable().getModel()).addRow2LastRow(row);
	    }
     }
    
     public void tabloyaEkle(Vector row){
       ((AyniyatTableModel) malzemeIzleme.getHareketlerTable().getModel()).addRow2LastRow(row);
     }
    
}
