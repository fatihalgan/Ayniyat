package com.iztek.ayniyat.yerlesim.service.test;

import org.hibernate.Session;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.test.KategoriServiceTest;
import com.iztek.ayniyat.test.util.ObjectFactory;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Bina;
import com.iztek.ayniyat.yerlesim.domain.Birim;
import com.iztek.ayniyat.yerlesim.domain.Oda;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Cagdas CIRIT
 */
public class YerlesimServiceTest extends KategoriServiceTest{

	public static void main(String[] args) {
		junit.swingui.TestRunner.run(YerlesimServiceTest.class);
	}
	
    protected void setUp() throws Exception {
        super.setUp();
        
        kategoriType="Yerlesim";
        
        root = new Birim("IYTE Kampus");
        child1 = new Birim("Muhendislik Fakültesi");
        child2 = new Birim("Rektörlük");
        child11 = new Bina("Dekanlik");
        child111 = new Oda("301",Oda.PERSONEL,new Integer(3));
        
        root.addKategori(child1);
        root.addKategori(child2);
        child1.addKategori(child11);
        child11.addKategori(child111);

        YerlesimManager.createKategori(null, root); 
    }

    protected void tearDown() throws Exception {
        super.tearDown();		
        Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	
	        session.delete(DAOFactory.getYerlesimDAO().findRootKategori());
	        HibernateUtil.commitTransaction();
	    } catch(Throwable t) {
	        HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
	    } finally {
	        HibernateUtil.closeSession();
	    }
    }
    
    public IKategorilendirilebilir getObjectFromProperties(String key){     
        AbstractZimmetAlan instance = (AbstractZimmetAlan) ObjectFactory.getYerlesimDomainObject(key);
        instance.setTanim(key);
        return instance;
    }
	
    public String getStringFromProperties(String key){        
        return ObjectFactory.getYerlesimString(key);
    }
    
    public IKategorilendirilebilir setTanim(IKategorilendirilebilir obj,String key){
        AbstractZimmetAlan instance = (AbstractZimmetAlan)obj;
        instance.setTanim(key);
        return instance;    
    }
    
	
}
