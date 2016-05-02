package com.iztek.ayniyat.malzemehareketleri.persistence;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketFisi;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.persistence.InfrastructureException;

/**
 * @author Umit Akyol
 *
*/
public class MalzemeHareketFisiDAO implements IMalzemeHareketFisiDAO {
    public AbstractMalzemeHareketFisi getMalzemeHareketFisiById(Long id){
        AbstractMalzemeHareketFisi hareketFisi = null;
        try {
            Session session = HibernateUtil.getSession();
            hareketFisi = (AbstractMalzemeHareketFisi)session.get(AbstractMalzemeHareketFisi.class,id, LockMode.UPGRADE);
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
    return hareketFisi;
    }
    public  Collection getAlindigiKurulusInHareketFisleri(){
    	Collection returnVal=null;
    	String queryString="select distinct h.alindigiKurulus from com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisFisi h"; 
    	try{
    		Session session=HibernateUtil.getSession();
    		returnVal=session.createQuery(queryString).list();
    	 } catch(HibernateException he) {
             throw new InfrastructureException(he.getMessage());
         }
    	 
    	 return returnVal;
    }	
    
}
