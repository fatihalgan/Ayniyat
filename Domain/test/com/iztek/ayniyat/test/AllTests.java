package com.iztek.ayniyat.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.iztek.ayniyat.event.test.EventControllerTest;
import com.iztek.ayniyat.event.test.KategoriEventTest;
import com.iztek.ayniyat.kategori.service.test.KategoriMergeStateManagerTest;
import com.iztek.ayniyat.kategori.test.KategoriComparatorTest;
import com.iztek.ayniyat.kategori.test.NitelikComparatorTest;
import com.iztek.ayniyat.kullanici.test.KullaniciDAOTest;
import com.iztek.ayniyat.kullanici.test.KullaniciManagerTest;
import com.iztek.ayniyat.malzemehareketleri.domain.test.MalzemeHareketleriDomainAllTests;
import com.iztek.ayniyat.malzemehareketleri.persistence.test.BelgeNoGeneratorDAOTest;
import com.iztek.ayniyat.malzemehareketleri.persistence.test.DemirbasNoGeneratorDAOTest;
import com.iztek.ayniyat.malzemehareketleri.persistence.test.MalzemeDAOTest;
import com.iztek.ayniyat.malzemehareketleri.persistence.test.MalzemeHareketiDAOTest;
import com.iztek.ayniyat.malzemehareketleri.service.test.MalzemeManagerTest;
import com.iztek.ayniyat.malzemetanimi.domain.test.KategoriTest;
import com.iztek.ayniyat.malzemetanimi.domain.test.MalzemeTanimiDomainTest;
import com.iztek.ayniyat.malzemetanimi.domain.test.NitelikTanimiTest;
import com.iztek.ayniyat.malzemetanimi.persistence.test.MalzemeTanimiPersistenceTest;
import com.iztek.ayniyat.malzemetanimi.service.test.MalzemeTanimlariServiceCopyCutTest;
import com.iztek.ayniyat.malzemetanimi.service.test.MalzemeTanimlariServiceTest;
import com.iztek.ayniyat.yerlesim.domain.test.AbstractZimmetAlanTest;
import com.iztek.ayniyat.yerlesim.domain.test.YerlesimDomainTest;
import com.iztek.ayniyat.yerlesim.persistence.test.YerlesimDAOTest;
import com.iztek.ayniyat.yerlesim.persistence.test.YerlesimPersistenceTest;
import com.iztek.ayniyat.yerlesim.service.test.YerlesimCopyCutTest;
import com.iztek.ayniyat.yerlesim.service.test.YerlesimManagerTest;
import com.iztek.ayniyat.yerlesim.service.test.YerlesimServiceCopyCutTest;
import com.iztek.ayniyat.yerlesim.service.test.YerlesimServiceTest;
import com.iztek.commons.money.test.MoneyTest;
import com.iztek.util.persistence.test.DAOFactoryTest;
import com.iztek.util.persistence.test.HibernateUtilTest;
import com.iztek.util.persistence.test.PluginFactoryTest;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.iztek.ayniyat.test");
		//$JUnit-BEGIN$
		
		//event tests
		suite.addTestSuite(EventControllerTest.class);
		suite.addTestSuite(KategoriEventTest.class);		
		//kategori tests
		suite.addTestSuite(KategoriMergeStateManagerTest.class);
		suite.addTestSuite(KategoriComparatorTest.class);
		suite.addTestSuite(NitelikComparatorTest.class);		
		//malzeme domain tests
		suite.addTestSuite(KategoriTest.class);
        suite.addTestSuite(NitelikTanimiTest.class);
        suite.addTestSuite(MalzemeTanimiDomainTest.class);        
        //malzeme persistence tests
		suite.addTestSuite(MalzemeTanimiPersistenceTest.class);		
		//malzeme service tests
		suite.addTestSuite(MalzemeTanimlariServiceTest.class);
		suite.addTestSuite(MalzemeTanimlariServiceCopyCutTest.class);
		//yerlesim domain tests
		suite.addTestSuite(YerlesimDomainTest.class);
        suite.addTestSuite(AbstractZimmetAlanTest.class);
        //yerlesim persistence tests
        suite.addTestSuite(YerlesimDAOTest.class);
        suite.addTestSuite(YerlesimPersistenceTest.class);
        //yerlesim service tests
        suite.addTestSuite(YerlesimServiceTest.class);
		suite.addTestSuite(YerlesimServiceCopyCutTest.class); 
		suite.addTestSuite(YerlesimManagerTest.class); 
		suite.addTestSuite(YerlesimCopyCutTest.class); 	
		//malzemeHareketleri Domain Tests
		suite.addTest(MalzemeHareketleriDomainAllTests.suite());
        //malzemeHareketleri persistence tests
		suite.addTestSuite(DemirbasNoGeneratorDAOTest.class);
		suite.addTestSuite(BelgeNoGeneratorDAOTest.class);
		suite.addTestSuite(MalzemeDAOTest.class);
		suite.addTestSuite(MalzemeHareketiDAOTest.class);
        //malzemeHareketleri service tests
		suite.addTestSuite(MalzemeManagerTest.class);
		// kullanici tests
		suite.addTestSuite(KullaniciDAOTest.class);
		suite.addTestSuite(KullaniciManagerTest.class);
		

		//others
        suite.addTestSuite(DAOFactoryTest.class);
		suite.addTestSuite(PluginFactoryTest.class);
		suite.addTestSuite(MoneyTest.class);
		suite.addTestSuite(HibernateUtilTest.class);
		//$JUnit-END$
		return suite; 
	}
}
