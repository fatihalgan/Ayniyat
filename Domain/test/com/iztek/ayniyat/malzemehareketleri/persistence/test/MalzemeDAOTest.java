package com.iztek.ayniyat.malzemehareketleri.persistence.test;

import java.util.Collection;

import org.hibernate.Session;

import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.StateStokta;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Sevgi Uslu
 */
public class MalzemeDAOTest extends MainPersistedTestCase{
    
    public static void main(String[] args) {
 		junit.swingui.TestRunner.run(MalzemeDAOTest.class);
    }
    
    public void testFindDemirbasByDemirbasNo(){
		IDemirbasMalzeme returnVal = null;
		returnVal = DAOFactory.getMalzemeDAO().findDemirbasByDemirbasNo(masa0.getDemirbasNo().toString());
		assertNotNull(returnVal);
    }

    public void testgetDemirbaslarByMalzemeTanimi(){
		Collection returnVal = null;
		returnVal = DAOFactory.getMalzemeDAO().getDemirbaslarByMalzemeTanimi((AbstractMalzemeTanimi) masaTanimi);
		assertTrue(returnVal.size()>0);
        
    }  
    public void testGetDemirbaslarByState(){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO().getDemirbaslarByState(new StateStokta());
			assertTrue(returnVal.size()>0);
		} catch(Throwable t) {
			Debug.instance().println(t);
		} finally {
			HibernateUtil.closeSession();
		}
    }
    
    public void testGetMalzemeTanimlariVisibleToKullanici(){
    	Collection returnVal = null;
		returnVal = DAOFactory.getMalzemeDAO().getMalzemeTanimlariVisibleToKullanici("Sevgi");
		assertTrue(returnVal.size()>0);       
    }
     
    public void testGetNitelikTaniminaAitFarkliDegerler(){
        Collection returnVal = null;
		returnVal = DAOFactory.getMalzemeDAO().getNitelikTaniminaAitFarkliDegerler(
		        (AbstractMalzemeTanimi) monitorTanimi,"Marka");
		assertTrue(returnVal.size()>0);  
    }
}