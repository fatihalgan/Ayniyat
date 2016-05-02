package com.iztek.ayniyat.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.iztek.ayniyat.data.DataCollector;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.AbstractKategoriManager;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Bina;
import com.iztek.ayniyat.yerlesim.domain.Birim;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;


/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class DataPersister extends DataComposer{
    public void setUp() throws Exception {
        super.setUp();  
        
        //Exel Files Data
        try {
            DataCollector.getOdalar((Birim) buam,(Bina)bina); 
            DataCollector.getPersoneller((Bina) bina,(Birim) buam);
            DataCollector.getMalzemeTanimlari(bilgisayar);
            DataCollector.getDemirbaslar((Kategori) bilgisayar,(Bina) bina,(Birim) buam);
          
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		//persist MalzemeTanimlari
        AbstractKategoriManager.createKategori(null,malzemeler);
		//persist Yerlesim
		YerlesimManager.createKategori(null,kampus);
		
		
		//persist MalzemeHareketleri
        Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	
	       session.saveOrUpdate(malzemeGirisHareketi);
	       session.saveOrUpdate(malzemeCikisHareketi1);
	       session.saveOrUpdate(malzemeCikisHareketi2);
	       session.saveOrUpdate(malzemeCikisHareketi3);
	       session.saveOrUpdate(malzemeCikisHareketi4);
	       session.saveOrUpdate(malzemeCikisHareketi5);
	       session.saveOrUpdate(malzemeDevirHareketi0);
	       session.saveOrUpdate(malzemeDevirHareketi1);
	       session.saveOrUpdate(malzemeBozukHareketi);
	       session.saveOrUpdate(malzemeZayiHareketi);
	       session.saveOrUpdate(kullanici);
	       session.saveOrUpdate(kullanici1);
	       session.saveOrUpdate(kullanici2);
	       HibernateUtil.commitTransaction();
	    } catch(Throwable exception) {
	        HibernateUtil.rollbackTransaction();
			Debug.instance().println(exception);
	    } finally {
	        HibernateUtil.closeSession();
	    }
    }
   
    public void tearDown() throws Exception {
        super.tearDown();
		
		//delete malzemeHareketleri
		Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	
	       session.delete(malzemeGirisHareketi);
		   session.delete(malzemeCikisHareketi1);
		   session.delete(malzemeCikisHareketi2);
		   session.delete(malzemeCikisHareketi3);
		   session.delete(malzemeCikisHareketi4);
		   session.delete(malzemeCikisHareketi5);
		   session.delete(malzemeDevirHareketi0);
		   session.delete(malzemeDevirHareketi1);
		   session.delete(malzemeBozukHareketi);	   
		   session.delete(malzemeZayiHareketi);
		   session.delete(monitor);//monitor ve sahipsiz kimseye zimmetli degil dolayýsýyla cascadele dbden dusmezler.
		   session.delete(sahipsizMalzeme);
	       HibernateUtil.commitTransaction();
	    } catch(Throwable t) {
	        HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
	    } finally {
	        HibernateUtil.closeSession();
	    }
	    
	    //	  delete kullanýcýlar
		HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	 
		   session.delete(kullanici);
		   session.delete(kullanici1);
		   session.delete(kullanici2);
	       HibernateUtil.commitTransaction();
	    } catch(Throwable t) {
	        HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
	    } finally {
	        HibernateUtil.closeSession();
	    }
	    
        //delete yerlesim
        IKategorilendirilebilir root = DAOFactory.getYerlesimDAO().findRootKategori();		
		HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {
			clearZimmetler((IZimmetAlan) root);
			findAndClear(root);
		    HibernateUtil.commitTransaction();
	    } catch(Throwable t) {
	        HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
	    } finally {
	        HibernateUtil.closeSession();
	    }		
        YerlesimManager.deleteKategori(root);
		
		//delete malzemeTanimlari
		MalzemeTanimlariManager.deleteKategori(DAOFactory.getMalzemeTanimiDAO().findRootKategori());
    }
    
    public static void findAndClear(IKategorilendirilebilir zimmetAlan){
    	Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	
	    	zimmetAlan = (IKategorilendirilebilir) session.get(AbstractZimmetAlan.class,zimmetAlan.getId());
	    	Iterator iter = zimmetAlan.getAltKategoriler().iterator();
	    	while (iter.hasNext()) {
	    		IKategorilendirilebilir element = (IKategorilendirilebilir) iter.next();
				clearZimmetler((IZimmetAlan) element);
				findAndClear(element);
			}
	    } catch(Throwable he) {
			throw new HibernateException(he);
	    }
    }
    
    public static void clearZimmetler(IZimmetAlan zimmetAlan){
    	Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	
	    	session.update(zimmetAlan);
	    	Iterator iteration = zimmetAlan.getZimmetliMalzemeler().iterator();
	    	while (iteration.hasNext()) {
				IDemirbasMalzeme element = (IDemirbasMalzeme) iteration.next();
				iteration.remove();
				element.setZimmetSahibi(null);
				session.delete(element.getMalzemeTanimi());
				session.delete(element);
			}
	    } catch(Throwable he) {
			throw new HibernateException(he);
	    }
    }
}
