package com.iztek.ayniyat.malzemetanimi.service;

import org.hibernate.Session;

import com.iztek.ayniyat.kategori.service.AbstractKategoriManager;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MalzemeTanimlariManager extends AbstractKategoriManager {
	
	/** 
	 * Sevgi USLU 
	 */
	public static boolean isMalzemeKodExist(String kod) {
        try {
            Session session = HibernateUtil.getSession();
			return DAOFactory.getMalzemeTanimiDAO().isMalzemeKodExist(kod);
		} catch(Throwable t) {
			Debug.instance().println(t);
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
}
