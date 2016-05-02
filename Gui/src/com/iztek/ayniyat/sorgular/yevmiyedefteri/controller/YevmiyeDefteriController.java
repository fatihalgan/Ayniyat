package com.iztek.ayniyat.sorgular.yevmiyedefteri.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketiManager;
import com.iztek.ayniyat.model.treetable.TreeTableNode;
import com.iztek.ayniyat.sorgular.yevmiyedefteri.gui.YevmiveDefteri;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;


/**
 * @author Umit Akyol
 *
 */
public class YevmiyeDefteriController {
    private YevmiveDefteri yevmiveDefteri=null;
    
    public void registerYevmiyeDefteri(YevmiveDefteri yevmiyeDefteri){
        this.yevmiveDefteri = yevmiyeDefteri;
    }

    public Vector yevmiyeDefteriGirisBilgileriniSorgula(Date baslangic,Date bitis) {
       Ambar ambar = (Ambar)AnaController.getInstance().getIslemYapanAmbar();
       Vector hareketler = new Vector();
       hareketler.addAll(MalzemeHareketiManager.getAmbaraGirenMalzemeHareketleri(ambar,baslangic,bitis));
       return hareketler;
    }
    
    public Vector yevmiyeDefteriCikisBilgileriniSorgula(Date baslangic,Date bitis) {
        Ambar ambar = (Ambar)AnaController.getInstance().getIslemYapanAmbar();
        Vector hareketler = new Vector();
        hareketler.addAll(MalzemeHareketiManager.getAmbardanCikanMalzemeHareketleri(ambar,baslangic,bitis));
        return hareketler;
     }
    
    public void hareketeMalzemeleriniEkle(TreeTableNode hareketNode){
	    AbstractMalzemeHareketi hareket = ((AbstractMalzemeHareketi) hareketNode.getUserObject());
		Session session = HibernateUtil.getSession();		
		try{
		    session.lock(hareket,LockMode.READ);
		    Iterator iter = hareket.getMalzemeler().iterator();
		    while (iter.hasNext()) {
	            DemirbasMalzeme malzeme = (DemirbasMalzeme) iter.next();
	            TreeTableNode malzemeNode = new TreeTableNode(malzeme);
	            hareketNode.add(malzemeNode);
	        }
		}catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}
    
	
    
}
