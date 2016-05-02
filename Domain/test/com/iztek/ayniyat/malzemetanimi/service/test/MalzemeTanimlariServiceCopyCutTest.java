/*
 * Created on 10.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.malzemetanimi.service.test;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.test.KategoriCopyCutTest;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.ayniyat.test.util.ObjectFactory;
import com.iztek.ayniyat.test.util.TestUtils;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class MalzemeTanimlariServiceCopyCutTest extends KategoriCopyCutTest{
    
    public static void main(String[] args) {
        junit.swingui.TestRunner.run(MalzemeTanimlariServiceCopyCutTest.class);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        kategoriType="MalzemeTanimi";
        
        root = new Kategori("Malzemeler","01");
        child1 = new Kategori("Demirbas Malzemeler","01");
        child2 = new Kategori("Elektronik Malzemeler","02");
        child3 = new Kategori("Kirtasiye Malzemeleri","03");
        child4 = new Kategori("Bilgisayar","01");
        
        child11 = new Kategori("Bilgisayar","01");
        child111= new DemirbasMalzemeTanimi("Monitör","01",AbstractMalzemeTanimi.ADET);
        child112= new DemirbasMalzemeTanimi("Kasa","02",AbstractMalzemeTanimi.ADET);
        
        child21 = new Kategori("Bilgisayar","01");
        child211= new DemirbasMalzemeTanimi("Modem","03",AbstractMalzemeTanimi.ADET);
        
        child31 = new DemirbasMalzemeTanimi("Kagit","04",AbstractMalzemeTanimi.ADET);
        
        child41= new DemirbasMalzemeTanimi("Modem","05",AbstractMalzemeTanimi.ADET);
        child42= new DemirbasMalzemeTanimi("Kasa","06",AbstractMalzemeTanimi.ADET);
       
    
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
        
        MalzemeTanimlariManager.createKategori(null, root);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        Session session = HibernateUtil.getSession();
        HibernateUtil.beginTransaction();
        try {
            session.delete(DAOFactory.getMalzemeTanimiDAO().findRootKategori());
            HibernateUtil.commitTransaction();
        } catch (Throwable t) {
            HibernateUtil.rollbackTransaction();
            Debug.instance().println(t);
        } finally {
            HibernateUtil.closeSession();
        }
    }
    
    public IKategorilendirilebilir getObjectFromProperties(String key) {
        Kategori instance = (Kategori) ObjectFactory.getMalzemeTanimiDomainObject(key);
        instance.setTanim(key);
        return instance;
    }
    public String getStringFromProperties(String key) {
        return ObjectFactory.getMalzemeTanimiString(key);
    }
    public IKategorilendirilebilir setTanim(IKategorilendirilebilir obj,String key) {
        Kategori instance = (Kategori) obj;
        instance.setTanim(key);
        return instance;
    }
    
    
    public void testCopyKategoriWithDifferentTanim() {
        IKategorilendirilebilir temp1 = TestUtils.findKategoriByTanim(child11.getTanim(),kategoriType);
        IKategorilendirilebilir temp2 = TestUtils.findKategoriByTanim(child3.getTanim(),kategoriType);
        	    
	    //child11 ve child3 ün tanýmlarý farklý oldugu icin
	    //child3 kategorisi child1 altýna kopyalanacak
        MalzemeTanimlariManager.copyKategori(temp1,temp2);
	    Session session = HibernateUtil.getSession();
	    temp1 = TestUtils.findKategoriByTanim(child11.getTanim(),kategoriType);
	    IKategorilendirilebilir temp = TestUtils.findKategoriByTanim(root.getTanim(),kategoriType);
	    session = HibernateUtil.getSession();
	    session.lock(temp1, LockMode.READ);
	    assertNotNull(temp1.getChildByNodeValue(child3.getNodeValue()));
	    
	    //child3 kategorisi altýndan tasýnmadýgý icin
	    //agacta 2 adet child oldu
	    session.lock(temp,LockMode.READ);
	    assertTrue(TestUtils.getChildKategoriCountByTanim(child3.getTanim())==2);
	    HibernateUtil.closeSession();
	}
    
    public void testCutKategoriWithDifferentTanim() {
        IKategorilendirilebilir temp1 = TestUtils.findKategoriByTanim(child11.getTanim(),kategoriType);
        IKategorilendirilebilir temp2 = TestUtils.findKategoriByTanim(child3.getTanim(),kategoriType);
        	    
	    //child11 ve child3 ün tanýmlarý farklý oldugu icin
	    //child3 kategorisi child1 altýna kopyalanacak
        MalzemeTanimlariManager.cutKategori(temp1,temp2);
	    Session session = HibernateUtil.getSession();
	    temp1 = TestUtils.findKategoriByTanim(child11.getTanim(),kategoriType);
	    IKategorilendirilebilir temp = TestUtils.findKategoriByTanim(root.getTanim(),kategoriType);
	    session = HibernateUtil.getSession();
	    session.lock(temp1, LockMode.READ);
	    assertNotNull(temp1.getChildByNodeValue(child3.getNodeValue()));
	    
	    //child3 kategorisi altýndan tasýndýgý icin
	    //agacta 1 adet child oldu
	    session.lock(temp,LockMode.READ);
	    assertTrue(TestUtils.getChildKategoriCountByTanim(child3.getTanim())==1);
	    HibernateUtil.closeSession();
	}
    
    public void testCopyKategoriWithSameTanimAndSameChild(){        
        IKategorilendirilebilir temp1 = TestUtils.findKategoriByNodeValue(child1.getTanim(),child11.getNodeValue(),kategoriType);
 	    IKategorilendirilebilir temp2 = TestUtils.findKategoriByNodeValue(root.getTanim(),child4.getNodeValue(),kategoriType);
 	   	    
 	    //child11 ve child4 kategorileri ayný tanýmlý malzeme icerdigi icin islem sonlanacak
 	    //child4 de bulunan diðer malzemeler de child11 e eklenmeyecek
 	    try {
 	       MalzemeTanimlariManager.copyKategori(temp1,temp2);
        } catch (Exception e) {
            System.out.println(e);
        }
        temp1 = TestUtils.findKategoriByNodeValue(child1.getTanim(),child11.getNodeValue(),kategoriType);
 	    IKategorilendirilebilir temp = TestUtils.findKategoriByTanim(root.getTanim(),kategoriType);
	    Session session = HibernateUtil.getSession();
	    session.lock(temp1, LockMode.READ);
	    assertNull(temp1.getChildByNodeValue(child41.getNodeValue()));
	    
	    //Kopyalama islemi iptal edildigi ve child4 malzemesi halen
	    //eski anaKategorisi olan root kategorisi altýnda
	    session.lock(temp,LockMode.READ);
	    assertNotNull(TestUtils.findKategoriByNodeValue(root.getTanim(),child4.getNodeValue(),kategoriType));
 	    HibernateUtil.closeSession();
 	}
    public void testCutKategoriWithSameTanimAndSameChild(){
        IKategorilendirilebilir temp1 = TestUtils.findKategoriByNodeValue(child1.getTanim(),child11.getNodeValue(),kategoriType);
 	    IKategorilendirilebilir temp2 = TestUtils.findKategoriByNodeValue(root.getTanim(),child4.getNodeValue(),kategoriType);
 	   	    
 	    //child11 ve child4 kategorileri ayný tanýmlý malzeme icerdigi icin islem sonlanacak
 	    //child4 de bulunan diðer malzemeler de child11 e eklenmeyecek
 	    try {
 	       MalzemeTanimlariManager.cutKategori(temp1,temp2);
        } catch (Exception e) {
            System.out.println(e);
        }
        temp1 = TestUtils.findKategoriByNodeValue(child1.getTanim(),child11.getNodeValue(),kategoriType);
 	    IKategorilendirilebilir temp = TestUtils.findKategoriByTanim(root.getTanim(),kategoriType);
	    Session session = HibernateUtil.getSession();
	    session.lock(temp1, LockMode.READ);
	    assertNull(temp1.getChildByNodeValue(child41.getNodeValue()));
	    
	    //Kopyalama islemi iptal edildigi ve child4 malzemesi halen
	    //eski anaKategorisi olan root kategorisi altýnda
	    session.lock(temp,LockMode.READ);
	    assertNotNull(TestUtils.findKategoriByNodeValue(root.getTanim(),child4.getNodeValue(),kategoriType));
 	    HibernateUtil.closeSession();
 	}
}
