/*
 * Created on 03.Aðu.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.malzemehareketleri.persistence.test;

import java.util.Collection;

import org.hibernate.Session;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MalzemeHareketiDAOTest extends MainPersistedTestCase{

    
    public static void main(String[] args) {
 		junit.swingui.TestRunner.run(MalzemeHareketiDAOTest.class);
    }
    
    public void testGetAmbaraGirenMalzemeHareketleri(){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeHareketiDAO().getAmbaraGirenMalzemeHareketleri(muhendislikAmbar,null,null);
			assertTrue(returnVal.size()>0);
		} catch(Throwable t) {
			Debug.instance().println(t);
		} finally {
			HibernateUtil.closeSession();
		}
    }
    
    public void testGetAmbardanCikanMalzemeHareketleri(){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeHareketiDAO().getAmbardanCikanMalzemeHareketleri(muhendislikAmbar,null,null);
			assertTrue(returnVal.size()>0);
		} catch(Throwable t) {
			Debug.instance().println(t);
		} finally {
			HibernateUtil.closeSession();
		}
    }
    
    public void testGetMalzemeHareketleriOrderByBelgeNo(){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		Long no1,no2;
		try {
			returnVal = DAOFactory.getMalzemeHareketiDAO().getMalzemeHareketleriOrderByBelgeNo((AbstractMalzeme) masa0);
			assertTrue(returnVal.size()>0);
			no1= ((AbstractMalzemeHareketi)returnVal.toArray()[0]).getHareketFisi().getBelgeNo().getBelgeNo();
			no2= ((AbstractMalzemeHareketi)returnVal.toArray()[1]).getHareketFisi().getBelgeNo().getBelgeNo();
			assertTrue(no1.compareTo(no2) == 1);
		} catch(Throwable t) {
			Debug.instance().println(t);
		} finally {
			HibernateUtil.closeSession();
		}
    }
}
