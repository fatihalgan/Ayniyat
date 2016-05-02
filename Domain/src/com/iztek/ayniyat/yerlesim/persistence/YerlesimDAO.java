package com.iztek.ayniyat.yerlesim.persistence;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.persistence.InfrastructureException;

/**
 * @author Cagdas CIRIT
 */
public class YerlesimDAO implements IYerlesimDAO {

    public IKategorilendirilebilir findRootKategori() {
        IKategorilendirilebilir abstractZimmetAlan = null;
        try {
            Session session = HibernateUtil.getSession();
            abstractZimmetAlan = (AbstractZimmetAlan) session.createQuery("from com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan as abstractZimmetAlan " +
            		"where abstractZimmetAlan.anaKategori is null").uniqueResult();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return abstractZimmetAlan;
    }

    public IKategorilendirilebilir findKategoriById(Long abstractZimmetAlanId) {
        IKategorilendirilebilir abstractZimmetAlan = null;
        try {
            Session session = HibernateUtil.getSession();
            abstractZimmetAlan = (AbstractZimmetAlan)session.get(AbstractZimmetAlan.class, abstractZimmetAlanId, LockMode.UPGRADE);
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return abstractZimmetAlan;
    }

    public Collection findKategoriByMatchingTanim(String tanimString) {
        Collection returnVal = null;
        try {
            Session session = HibernateUtil.getSession();
            returnVal = session.createCriteria(AbstractZimmetAlan.class).add(Expression.like("tanim", tanimString, MatchMode.ANYWHERE)).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    } 
    
    public Collection findKategoriByMatchingTanimIgnoreCase(String tanimString) {
        Collection returnVal = null;
        try {
            Session session = HibernateUtil.getSession();
            returnVal = session.createCriteria(AbstractZimmetAlan.class).add(Expression.like("tanim", tanimString, MatchMode.ANYWHERE).ignoreCase()).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
    /**
     * @author Cagdas CIRIT
     */
    public Collection findPersonelBySicilNo(String sicilNo){
    	Collection returnVal = null;
    	String query = "from com.iztek.ayniyat.yerlesim.domain.Personel as personel " +
    			"where personel.sicilNo=:sicilNo";    	
        try {
            Session session = HibernateUtil.getSession();
            returnVal = session.createQuery(query).setParameter("sicilNo",sicilNo).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
    /**
     * @author Sevgi USLU
     */
    public AbstractZimmetAlan findAmbarByTanim(String ambarTanim){
        AbstractZimmetAlan returnVal = null;
    	String query = "from com.iztek.ayniyat.yerlesim.domain.Ambar as ambar " +
    			"where ambar.tanim=:tanim";    	
        try {
            Session session = HibernateUtil.getSession();
            returnVal =(AbstractZimmetAlan) session.createQuery(query).setParameter("tanim",ambarTanim).uniqueResult();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
    /**
     * @author Sevgi USLU
     */
    public Collection getAmbarlar(){
        Collection returnVal = null;
    	String query = "from com.iztek.ayniyat.yerlesim.domain.Ambar";    	
        try {
            Session session = HibernateUtil.getSession();
            returnVal = session.createQuery(query).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }

}
