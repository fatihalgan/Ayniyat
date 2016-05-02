package com.iztek.ayniyat.malzemetanimi.persistence.test;

import org.hibernate.Session;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.persistence.IKategoriDAO;
import com.iztek.ayniyat.kategori.persistence.test.KategoriPersistanceTest;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.ayniyat.test.util.ObjectFactory;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MalzemeTanimiPersistenceTest extends KategoriPersistanceTest{
	
    public static void main(String[] args) {
        junit.swingui.TestRunner.run(MalzemeTanimiPersistenceTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        
        kategoriType="MalzemeTanimi";
        
        root = new Kategori("Malzemeler","01");
        child1 = new Kategori("Demirbas Malzemeler","01");
        child2 = new Kategori("Tüketim Malzemeleri","02");
        child11 = new Kategori("Bilgisayar","01");
        child111= new DemirbasMalzemeTanimi("Yazýcý","01",AbstractMalzemeTanimi.ADET);
        
        root.addKategori(child1);
        root.addKategori(child2);
        child1.addKategori(child11);
        child11.addKategori(child111);
        
        MalzemeTanimlariManager.createKategori(null, root); 
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	
	        session.delete(DAOFactory.getMalzemeTanimiDAO().findRootKategori());
	        HibernateUtil.commitTransaction();
	    } catch(Throwable t) {
	        HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
	    } finally {
	        HibernateUtil.closeSession();
	    }
    }
    
    public IKategoriDAO getObjectFromProperties(){        
        return DAOFactory.getMalzemeTanimiDAO();
    }
    
    public String getStringFromProperties(String key){   
        return ObjectFactory.getMalzemeTanimiString(key);
    }
    
    public IKategorilendirilebilir setTanim(IKategorilendirilebilir obj,String key) {
        Kategori instance = (Kategori) obj;
        instance.setTanim(key);
        return instance;
    }
}
