package com.iztek.ayniyat.malzemehareketleri.service.test;

import java.util.Date;

import org.hibernate.Session;

import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisHareketi;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketiManager;
import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;


/**
 * @author Umit Akyol
 *
*/
public class MalzemeHareketiManagerTest extends MainPersistedTestCase {
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(MalzemeHareketiManagerTest.class);
	}

    
    public void testCreateMalzemeHareketi(){
        MalzemeGirisHareketi malzemeHareketi = new MalzemeGirisHareketi();
        malzemeHareketi.setHareketHedefi(rektorlukHareketYeri);
        malzemeHareketi.setHareketTarihi(new Date(rightNow.getTimeInMillis()));
        malzemeHareketi.addMalzeme(new DemirbasMalzeme());
		Session session = HibernateUtil.getSession();
		try {
			MalzemeHareketiManager.createMalzemeHareketi(malzemeHareketi);
		} catch(Throwable t) {
			Debug.instance().println(t);
		} finally {
			HibernateUtil.closeSession();
		}
    }
    
}
