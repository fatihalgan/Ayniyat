package com.iztek.ayniyat.sorgular.ambaraDevir.controller;

import java.util.Iterator;
import java.util.Vector;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.model.treetable.TreeTableNode;
import com.iztek.ayniyat.sorgular.ambaraDevir.gui.AmbaraDevirCikanMalzemeler;
import com.iztek.ayniyat.sorgular.ambaraDevir.gui.AmbaraDevirGelenMalzemeler;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Cagdas CIRIT
 **/
public class AmbaraDevirSorguController {
    private AmbaraDevirCikanMalzemeler ambaraDevirCikanMalzemeler;
    private AmbaraDevirGelenMalzemeler ambaraDevirGelenMalzemeler;
    
    public void registerAmbaraDevirCikanMalzemeler(AmbaraDevirCikanMalzemeler ambaraDevirCikanMalzemeler) {
        this.ambaraDevirCikanMalzemeler = ambaraDevirCikanMalzemeler;
    }
    
    public void registerAmbaraDevirGelenMalzemeler(AmbaraDevirGelenMalzemeler ambaraDevirGelenMalzemeler) {
        this.ambaraDevirGelenMalzemeler = ambaraDevirGelenMalzemeler;
    }
    
    public Vector baskaAmbaraDevirCikisHareketleriniCek(){
        return new Vector(MalzemeManager.getTransferMalzemeHareketleri(null,AnaController.getInstance().getIslemYapanAmbar()));
    }
    
    public Vector baskaAmbaraDevirGirisHareketleriniCek(){
        return new Vector(MalzemeManager.getTransferMalzemeHareketleri(AnaController.getInstance().getIslemYapanAmbar(),null));
    }
    
	public void hareketeMalzemeleriniEkle(TreeTableNode hareketNode){
	    AbstractMalzemeHareketi hareket = ((AbstractMalzemeHareketi) hareketNode.getUserObject());
		Session session = HibernateUtil.getSession();		
		try{
		    session.lock(hareket,LockMode.READ);
		    Iterator iter = hareket.getMalzemeler().iterator();
		    while (iter.hasNext()) {
	            DemirbasMalzeme malzeme = (DemirbasMalzeme) iter.next();
	            if (malzeme.getState().getType().equals(IMalzemeState.ASKIDA)){
	                TreeTableNode malzemeNode = new TreeTableNode(malzeme);
	                hareketNode.add(malzemeNode);
	            }
	        }
		}catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void gelenFisMalzemeleriniMalzemeGirisEkraninaGonder(TreeTableNode hareketNode){
	    Vector malzemeler = new Vector(hareketNode.getChildren().size());
	    Iterator iter = hareketNode.getChildren().iterator();
	    while (iter.hasNext()) {
            AbstractMalzeme malzeme = (AbstractMalzeme) ((TreeTableNode) iter.next()).getUserObject();
            malzemeler.add(malzeme);
        }
	    
	    AnaController.getInstance().getMalzemeGirisiController().malzemeGirisiniBaskaAmbardanGelenMalzemeleriKabulIcinDoldur((AbstractMalzemeHareketi) hareketNode.getUserObject(),malzemeler);
	    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(AnaController.MALZEME_GIRISI);
	}
}
