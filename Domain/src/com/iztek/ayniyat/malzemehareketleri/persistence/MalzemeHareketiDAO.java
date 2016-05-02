package com.iztek.ayniyat.malzemehareketleri.persistence;

import java.util.Collection;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeHareketi;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.persistence.InfrastructureException;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeHareketiDAO implements IMalzemeHareketiDAO {
    
    public IMalzemeHareketi getMalzemeHareketiById(Long id){
        IMalzemeHareketi malzemeHareketi = null;
        try {
            Session session = HibernateUtil.getSession();
            malzemeHareketi = (IMalzemeHareketi)session.get(IMalzemeHareketi.class, id, LockMode.UPGRADE);
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return malzemeHareketi;
    }

    public Collection getAmbaraGirenMalzemeHareketleri(IZimmetAlan ambar, Date baslangic, Date bitis) {
        Collection returnVal = null;
        String queryString = "from com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi hareket";

        if(ambar!=null)
           queryString = queryString+" where hareket.hareketHedefi.hareketYeri=:ambar";
        
        if(baslangic != null)
            queryString = queryString+" and hareket.hareketTarihi >= :baslangic";
        
        if(bitis != null)
            queryString = queryString+" and hareket.hareketTarihi <= :bitis";
        
            
        try {
            Session session = HibernateUtil.getSession();
            Query query = session.createQuery(queryString);
            if(ambar != null)
                query = query.setParameter("ambar",ambar);
            if(baslangic != null)
                query = query.setParameter("baslangic",baslangic);
            if(bitis != null)
                query = query.setParameter("bitis",bitis);
            
            returnVal =(Collection) query.list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }    
        return returnVal;
    }

    public Collection getAmbardanCikanMalzemeHareketleri(IZimmetAlan ambar, Date baslangic, Date bitis) {
        Collection returnVal = null;
        String queryString = "from com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi hareket";
        if(ambar!=null)
           queryString = queryString+" where hareket.hareketKaynagi.hareketYeri=:ambar";
       
        if(baslangic != null)
            queryString = queryString+" and hareket.hareketTarihi >= :baslangic";
        
        if(bitis != null)
            queryString = queryString+" and hareket.hareketTarihi <= :bitis";
        
        try {
            Session session = HibernateUtil.getSession();
            Query query = session.createQuery(queryString);
            if(ambar != null)
                query = query.setParameter("ambar",ambar);
            if(baslangic != null)
                query = query.setParameter("baslangic",baslangic);
            if(bitis != null)
                query = query.setParameter("bitis",bitis);
            
            returnVal =(Collection) query.list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }    
        return returnVal;
    }
    
    
    /**
     * Sevgi USLU
     */
       
    public Collection getMalzemeHareketleriOrderByBelgeNo(AbstractMalzeme malzeme){
        Collection returnVal = null;
    	String query = "select hareket from com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme malzeme " +
		"join malzeme.malzemeHareketleri hareket " +
		"where malzeme=:malzeme order by hareket.hareketFisi.belgeNo desc";
        try {
            Session session = HibernateUtil.getSession();
            returnVal = session.createQuery(query).setParameter("malzeme",malzeme).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
}
