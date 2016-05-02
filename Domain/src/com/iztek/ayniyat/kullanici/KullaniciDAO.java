/*
 * Created on 05.Tem.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.kullanici;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.persistence.InfrastructureException;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KullaniciDAO implements IKullaniciDAO{
    
    public Kullanici getKullaniciByUserNameAndPassword(String userName,String password){
    	Kullanici kullanici = null;
    	/**
    	 * modified by Füsun Çetin
    	 */
        String query="from com.iztek.ayniyat.kullanici.Kullanici kullanici " +
        		"where kullanici.userName=:userName and kullanici.password=:password";
        try {
            Session session = HibernateUtil.getSession();
 	        kullanici=(Kullanici)session.createQuery(query).setParameter("userName",userName).setParameter("password",password).uniqueResult();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return kullanici;
    }  
    
    public Collection getMalzemelerVisibleToKullanici(Kullanici kullanici){
        Collection returnVal = null;
    	String query = "from com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme demirbas " +
			"where demirbas.zimmetSahibi in (select ambar from com.iztek.ayniyat.kullanici.Kullanici kullanici "+
    		"join kullanici.ambarlar ambar where kullanici.userName =:userName)"; 

        try {
            Session session = HibernateUtil.getSession();
            returnVal= session.createQuery(query).setParameter("userName",kullanici.getUserName()).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        
        return returnVal;
    } 
}
