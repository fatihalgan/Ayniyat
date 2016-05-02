package com.iztek.ayniyat.kategori.service.test;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.AbstractKategoriManager;
import com.iztek.ayniyat.kategori.test.KategoriTestCase;
import com.iztek.ayniyat.test.util.TestUtils;
import com.iztek.util.persistence.HibernateUtil;

/**
 * @author Sevgi Uslu
 **/
public class KategoriCopyCutTest extends KategoriTestCase{

    public void testCopyWithSameTanim() {
        IKategorilendirilebilir temp1 = TestUtils.findKategoriByNodeValue(child1.getTanim(), child11.getNodeValue(),kategoriType);
	    IKategorilendirilebilir temp2 = TestUtils.findKategoriByNodeValue(child2.getTanim(), child21.getNodeValue(),kategoriType);
	    //child11 ve child21 tanýmlarý ayný oldugu icin altkategorileri
	    //child11 altýnda toplanýlacak
	    AbstractKategoriManager.copyKategori(temp1,temp2);  
	    temp1 = TestUtils.findKategoriByNodeValue(child1.getTanim(), child11.getNodeValue(),kategoriType);
	    IKategorilendirilebilir temp3 = TestUtils.findKategoriByTanim(child2.getTanim(),kategoriType);
	    Session session = HibernateUtil.getSession();
	    session.lock(temp1, LockMode.READ);
	    assertNull(temp1.getChildByNodeValue(child21.getNodeValue()));
	    assertNotNull(temp1.getChildByNodeValue(child211.getNodeValue()));
	    //child21 nin anaKategorisi olan child2 kategorisinde
	    //child21 kategorisi halen mevcut
	    session.lock(temp3, LockMode.READ);
	    assertNotNull(temp3.getChildByNodeValue(child21.getNodeValue()));
	    HibernateUtil.closeSession();
	}
    
    public void testCutWithSameTanim() {
        IKategorilendirilebilir temp1 = TestUtils.findKategoriByNodeValue(child1.getTanim(), child11.getNodeValue(),kategoriType);
	    IKategorilendirilebilir temp2 = TestUtils.findKategoriByNodeValue(child2.getTanim(), child21.getNodeValue(),kategoriType);
	            
	    //bilgisayar ve otherBilgisayarýn tanýmlarý ayný oldugu icin altkategorileri
	    //bilgisayar altýnda toplanýlacak
	    AbstractKategoriManager.cutKategori(temp1,temp2);  
	    temp1 = TestUtils.findKategoriByNodeValue(child1.getTanim(), child11.getNodeValue(),kategoriType);
	    IKategorilendirilebilir temp3 = TestUtils.findKategoriByTanim(child2.getTanim(),kategoriType);
	    Session session = HibernateUtil.getSession();
	    session.lock(temp1, LockMode.READ);
	    assertNull(temp1.getChildByNodeValue(child21.getNodeValue()));
	    assertNotNull(temp1.getChildByNodeValue(child211.getNodeValue()));
	    //otherBilgisayar nin anaKategorisi olan elektronik malzemeler kategorisinden
	    //otherBilgisayar kategorisi tasýnmýs olmalý
	    session.lock(temp3, LockMode.READ);
	    assertNull(temp3.getChildByNodeValue(child21.getNodeValue()));
	    HibernateUtil.closeSession();
	}
    
    public void testCopyInDeeperKategories(){
    	IKategorilendirilebilir temp1  = TestUtils.findKategoriByNodeValue(child2.getTanim(),child21.getNodeValue(),kategoriType);
    	IKategorilendirilebilir temp2 = TestUtils.findKategoriByNodeValue(child1.getTanim(),child11.getNodeValue(),kategoriType);
    	//child1 kategorisi child21 kategorisiyle ayný tanýma sahip bir alt kategori icerdigi icin 
	    //child1 in alt kategorisi ile child21 merge islemine tabi tutulacak 
    	int count = TestUtils.getChildKategoriCountByTanim(child21.getTanim());
    	AbstractKategoriManager.copyKategori(temp2,temp1);
	    temp2 = TestUtils.findKategoriByTanim(child1.getTanim(),kategoriType);
	    IKategorilendirilebilir temp3 = TestUtils.findKategoriByNodeValue(child1.getTanim(),child11.getNodeValue(),kategoriType);
	    IKategorilendirilebilir temp4 = TestUtils.findKategoriByTanim(child2.getTanim(),kategoriType);
	    assertEquals(TestUtils.getChildKategoriCountByTanim(child21.getTanim()),count);
	    Session session = HibernateUtil.getSession();
	    //child21 altýnda bulunan child211 child1 altýna kopyalandý
	    session.lock(temp3, LockMode.READ);
	    assertNotNull(temp3.getChildByNodeValue(child211.getNodeValue()));
	    //"temp3" tanýmlý bir kategori halen  temp4 altýnda bulunuyor
	    session.lock(temp4,LockMode.READ);
	    assertNotNull(temp4.getChildByNodeValue(child21.getNodeValue()));
	    HibernateUtil.closeSession();
	}
    
    public void testCutInDeeperKategories(){
	    IKategorilendirilebilir temp1  = TestUtils.findKategoriByNodeValue(child2.getTanim(),child21.getNodeValue(),kategoriType);
	    //IKategorilendirilebilir temp2 = TestUtils.findKategoriByTanim(child1.getTanim(),kategoriType);
	    IKategorilendirilebilir temp2 = TestUtils.findKategoriByNodeValue(child1.getTanim(),child11.getNodeValue(),kategoriType);
	     
	    //child1 kategorisi child21 kategorisiyle ayný tanýma sahip bir alt kategori icerdigi icin 
	    //child1 in alt kategorisi ile child21 merge islemine tabi tutulacak 
	    AbstractKategoriManager.cutKategori(temp2,temp1);
	    temp2 = TestUtils.findKategoriByTanim(child1.getTanim(),kategoriType);
	    IKategorilendirilebilir temp3 = TestUtils.findKategoriByNodeValue(child1.getTanim(),child11.getNodeValue(),kategoriType);
	    IKategorilendirilebilir temp4 = TestUtils.findKategoriByTanim(child2.getTanim(),kategoriType);
	    Session session = HibernateUtil.getSession();
	    //child21 altýnda bulunan child211 child1 altýna kopyalandý
	    session.lock(temp3, LockMode.READ);
	    assertNotNull(temp3.getChildByNodeValue(child211.getNodeValue()));
	    //"temp3" tanýmlý bir kategori halen  temp4 altýnda bulunuyor
	    session.lock(temp4,LockMode.READ);
	    assertNull(temp4.getChildByNodeValue(child21.getNodeValue()));
	    HibernateUtil.closeSession();
	}

    
}
