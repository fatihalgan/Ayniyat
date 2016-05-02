package com.iztek.ayniyat.malzemehareketleri.service;

import java.util.Collection;
import java.util.Date;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;

import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketFisi;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeHareketFisiManager {
    
    public static void createMalzemeHareketFisi(AbstractMalzemeHareketFisi malzemeHareketFisi) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		try {
			session.save(malzemeHareketFisi);
			HibernateUtil.commitTransaction();
		} catch(Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}
    
    //FIXME delete methodu olacak mý??? 
    //olacaksa tekrar implementasyonuna bakýlacak!!!
    public static void deleteMalzemeHareketi(AbstractMalzemeHareketFisi malzemeHareketFisi){
        IMalzemeHareketi malzemeHareketi = malzemeHareketFisi.getOwningMalzemeHareketi();
		Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	
	        session.lock(malzemeHareketi, LockMode.UPGRADE);
	        session.lock(malzemeHareketFisi, LockMode.UPGRADE);
	        malzemeHareketi.setHareketFisi(null);
	        session.delete(malzemeHareketFisi);
	        HibernateUtil.commitTransaction();
	    } catch(Throwable t) {
	        HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
	    } finally {
	        HibernateUtil.closeSession();
	    }
	}
	
	public static void updateMalzemeHareketi(AbstractMalzemeHareketFisi malzemeHareketFisi) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		try {
			session.saveOrUpdate(malzemeHareketFisi);
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
	
	public static Collection getAlindigiKurulusInHareketFisleri(){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeHareketFisiDAO().getAlindigiKurulusInHareketFisleri();
		} catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
    }
}
