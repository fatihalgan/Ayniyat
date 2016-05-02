package com.iztek.ayniyat.malzemehareketleri.service;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeHareketi;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeHareketiManager {
   
    public static void createMalzemeHareketi(IMalzemeHareketi malzemeHareketi) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		try {
		    session.save(malzemeHareketi);
		    malzemeHareketi.hareketiGerceklestir();
		    HibernateUtil.commitTransaction();
		} catch(Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}
    
    public static void createMalzemeHareketi(IMalzemeHareketi malzemeHareketi,Vector malzemeler) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		try {
		    Iterator iterator = malzemeler.iterator();
		    while(iterator.hasNext()){
		        IMalzeme malzeme = (IMalzeme) iterator.next();
		        session.update(malzeme);
		        malzemeHareketi.addMalzeme(malzeme);
		    }
		    malzemeHareketi.hareketiGerceklestir();
			session.save(malzemeHareketi);
			HibernateUtil.commitTransaction();
		} catch(Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}
    
    public static void deleteMalzemeHareketi(IMalzemeHareketi malzemeHareketi){
        AbstractMalzemeHareketFisi hareketFisi = malzemeHareketi.getHareketFisi();
		Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	
	        session.lock(malzemeHareketi, LockMode.UPGRADE);
	        session.lock(hareketFisi, LockMode.UPGRADE);
	        hareketFisi.setOwningMalzemeHareketi(null);
	        session.delete(malzemeHareketi);
	        HibernateUtil.commitTransaction();
	    } catch(Throwable t) {
	        HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
	    } finally {
	        HibernateUtil.closeSession();
	    }
	}
	
	public static void updateMalzemeHareketi(IMalzemeHareketi malzemeHareketi) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		try {
			session.saveOrUpdate(malzemeHareketi);
			HibernateUtil.commitTransaction();
		} catch(StaleObjectStateException se) {
			HibernateUtil.rollbackTransaction();
			throw new RuntimeException("Bu Kayýt Bir Baþka Kullanýcý Tarafýndan Deðiþtirilmiþ..");
		} catch(Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public static Collection getAmbaraGirenMalzemeHareketleri(IZimmetAlan ambar,Date baslangic,Date bitis){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeHareketiDAO().getAmbaraGirenMalzemeHareketleri(ambar,baslangic,bitis);
		} catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
    }
	
	public static Collection getAmbardanCikanMalzemeHareketleri(IZimmetAlan ambar,Date baslangic,Date bitis){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeHareketiDAO().getAmbardanCikanMalzemeHareketleri(ambar,baslangic,bitis);
		} catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
    }

    
    /**
     * Sevgi USLU
     */
	
    public static Collection getMalzemeHareketleriOrderByBelgeNo(AbstractMalzeme malzeme){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		
		try {
			returnVal = DAOFactory.getMalzemeHareketiDAO().getMalzemeHareketleriOrderByBelgeNo(malzeme);
		} catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
    }
}
