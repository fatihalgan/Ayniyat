/*
 * Created on 05.Tem.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.kullanici;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.Personel;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.persistence.InfrastructureException;
import com.iztek.util.tracer.debug.Debug;


/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KullaniciManager {

    public static Kullanici getKullaniciByUserNameAndPassword(String userName,String password) {
    	Kullanici returnVal = null;
        try {
		    Session session = HibernateUtil.getSession();
		    returnVal = DAOFactory.getKullaniciDAO().getKullaniciByUserNameAndPassword(
		        userName,password);
		 } catch(Throwable t) {
            Debug.instance().println(t.getMessage());
            throw new RuntimeException(t.getMessage());
        } finally {
            HibernateUtil.closeSession();
        }
		return returnVal;
	}
    
    
    public static boolean isAmbarInKullaniciAmbarList(Kullanici kullanici,Ambar ambar){
        Session session = HibernateUtil.getSession();
        session.lock(kullanici, LockMode.NONE);
		for (int j = 0; j < kullanici.getAmbarlar().size(); j++) {
		    if((kullanici.getAmbarlar().toArray()[j]).equals(ambar)){
		        return true;
			}
		}
		HibernateUtil.closeSession();
        return false;
    }
    
    public static Collection getMalzemelerVisibleToKullanici(Kullanici kullanici){       
        Collection returnVal = null;
		try {
			returnVal = DAOFactory.getKullaniciDAO().getMalzemelerVisibleToKullanici(kullanici);
		} catch(Throwable t) {
			Debug.instance().println(t);
		} finally {
			HibernateUtil.closeSession();
		}
        return returnVal;
    } 
    
    public static Personel getpersonel(Kullanici kullanici){
        Personel returnVal = null;
        try {
            Session session = HibernateUtil.getSession();
            session.lock(kullanici,LockMode.READ);
            returnVal= (Personel) kullanici.getPersonel();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }finally {
            HibernateUtil.closeSession();
        }
        
        return returnVal;
    }
}
