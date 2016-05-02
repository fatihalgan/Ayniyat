package com.iztek.ayniyat.yerlesim.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import org.hibernate.LockMode;
import org.hibernate.Session;
import com.iztek.ayniyat.kategori.service.AbstractKategoriManager;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.Personel;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Cagdas CIRIT
 */
public class YerlesimManager extends AbstractKategoriManager{
	
	public static Collection findPersonelBySicilNo(String sicilNo){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getYerlesimDAO().findPersonelBySicilNo(sicilNo);
		} catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}
	
    /**
     * @author Sevgi USLU
     */
	public static AbstractZimmetAlan findAmbarByTanim(String ambarTanim){
		Session session = HibernateUtil.getSession();
		AbstractZimmetAlan returnVal = null;
		try {
			returnVal = DAOFactory.getYerlesimDAO().findAmbarByTanim(ambarTanim);
		} catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}
	
    /**
     * @author Sevgi USLU
     */
	public static Collection getAmbarlar(){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getYerlesimDAO().getAmbarlar();
		} catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}
	
    /**
     * @author Sevgi USLU
     */
	public static Collection getPersonelInAmbar(String ambarTanim){
		Collection returnVal = new Vector();
		Collection ambardakier=null;
		
	    Ambar ambar= (Ambar) findAmbarByTanim(ambarTanim);
	    
		Session session = HibernateUtil.getSession();
	    session.lock(ambar, LockMode.NONE);
	    
		ambardakier=ambar.getAltKategoriler();
		Iterator iter=ambardakier.iterator();	
		while(iter.hasNext()){
		    AbstractZimmetAlan temp=(AbstractZimmetAlan)iter.next();			
		    if(temp instanceof Personel){
		        returnVal.add(temp);
		    }
		}	
		HibernateUtil.closeSession();
		
		return returnVal;
	}
	
	
}
