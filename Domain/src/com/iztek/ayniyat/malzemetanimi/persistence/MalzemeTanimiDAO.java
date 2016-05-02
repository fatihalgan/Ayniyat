/*
 * Created on 11.Nis.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.malzemetanimi.persistence;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.persistence.InfrastructureException;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MalzemeTanimiDAO implements IMalzemeTanimiDAO {

    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.persistence.IMalzemeTanimiDAO#findRootKategori()
     */
    public IKategorilendirilebilir findRootKategori() {
        IKategorilendirilebilir kategori = null;
        try {
            Session session = HibernateUtil.getSession();
            kategori = (Kategori)session.createQuery("from com.iztek.ayniyat.malzemetanimi.domain.Kategori as kategori " +
            		"where kategori.anaKategori is null").uniqueResult();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return kategori;
    }

    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.persistence.IMalzemeTanimiDAO#findKategoriById(java.lang.Long)
     */
    public IKategorilendirilebilir findKategoriById(Long kategoriId) {
        IKategorilendirilebilir kategori = null;
        try {
            Session session = HibernateUtil.getSession();
            kategori = (Kategori)session.get(Kategori.class, kategoriId, LockMode.UPGRADE);
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return kategori;
    }

    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.persistence.IMalzemeTanimiDAO#findKategoriByMatchingTanim(java.lang.String)
     */
    public Collection findKategoriByMatchingTanim(String tanimString) {
        Collection returnVal = null;
        try {
            Session session = HibernateUtil.getSession();
            returnVal = session.createCriteria(Kategori.class).add(Expression.like("tanim", tanimString, MatchMode.ANYWHERE)).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
   /**
    * Sevgi USLU
    */
    
    public Collection findKategoriByMatchingTanimIgnoreCase(String tanimString) {
        Collection returnVal = null;
        try {
            Session session = HibernateUtil.getSession();
            returnVal = session.createCriteria(Kategori.class).add(Expression.like("tanim", tanimString, MatchMode.ANYWHERE).ignoreCase()).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
	public boolean isMalzemeKodExist(String kod) {
		AbstractMalzemeTanimi malzemeTanimi = null;
        try {
        	Session session = HibernateUtil.getSession();
            if(session.createCriteria(AbstractMalzemeTanimi.class).add(Expression.like("kod", kod, MatchMode.EXACT)).uniqueResult() == null){
        		return false;
        	}else{
        		return true;
        	}
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
	}
    
}
