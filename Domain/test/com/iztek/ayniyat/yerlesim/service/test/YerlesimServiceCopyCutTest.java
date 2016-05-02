/*
 * Created on 10.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.yerlesim.service.test;

import org.hibernate.Session;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.test.KategoriCopyCutTest;
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
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class YerlesimServiceCopyCutTest  extends KategoriCopyCutTest{
	
	public static void main(String[] args) {
        junit.swingui.TestRunner.run(YerlesimServiceCopyCutTest.class);
    }
    
    
    protected void setUp() throws Exception {
        super.setUp();
        
        kategoriType="Yerlesim";
        
        root = new Birim("IYTE Kampus");
        child1 = new Birim("Muhendislik Fakultesi");
        child2 = new Birim("Mimarlik Fakultesi");
        child3 = new Birim("Rektorluk");
        child4 = new Bina("Kantin");
        
        child11 = new Bina("Kantin");
        child111= new Oda("208",Oda.PERSONEL,new Integer(3));
        child112= new Oda("205",Oda.PERSONEL,new Integer(2));
        
        child21 = new Bina("Kantin");
        child211= new Oda("301",Oda.PERSONEL,new Integer(3));
        
        child31 = new Oda("306",Oda.PERSONEL,new Integer(4));
        
        child41= new Oda("301",Oda.PERSONEL,new Integer(3));
        child42= new Oda("205",Oda.PERSONEL,new Integer(2));
        
     
        child11.addKategori(child111);
        child11.addKategori(child112);
        child21.addKategori(child211);   
        child4.addKategori(child41);
        child4.addKategori(child42);
        child3.addKategori(child31);
        child2.addKategori(child21);
        child1.addKategori(child11);
        root.addKategori(child1);
        root.addKategori(child2);
        root.addKategori(child3);
        
        YerlesimManager.createKategori(null, root);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        Session session = HibernateUtil.getSession();
        HibernateUtil.beginTransaction();
        try {
            session.delete(DAOFactory.getYerlesimDAO().findRootKategori());
            HibernateUtil.commitTransaction();
        } catch (Throwable t) {
            HibernateUtil.rollbackTransaction();
            Debug.instance().println(t);
        } finally {
            HibernateUtil.closeSession();
        }
    }
    
    public IKategorilendirilebilir getObjectFromProperties(String key) {
       AbstractZimmetAlan instance = (AbstractZimmetAlan) ObjectFactory.getYerlesimDomainObject(key);
       instance.setTanim(key);
       return instance;
    }
    public String getStringFromProperties(String key) {
        return ObjectFactory.getYerlesimString(key);
    }
    public IKategorilendirilebilir setTanim(IKategorilendirilebilir obj,String key) {
    	AbstractZimmetAlan instance = (AbstractZimmetAlan) obj;
        instance.setTanim(key);
        return instance;
    }
}
