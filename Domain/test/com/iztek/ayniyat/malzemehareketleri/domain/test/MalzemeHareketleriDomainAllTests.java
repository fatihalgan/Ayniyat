package com.iztek.ayniyat.malzemehareketleri.domain.test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeHareketleriDomainAllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.iztek.ayniyat.malzemehareketleri.domain.test");
		//$JUnit-BEGIN$
	
		//malzeme
		suite.addTestSuite(AbstractMalzemeTest.class);
		suite.addTestSuite(DemirbasMalzemeTest.class);
		suite.addTestSuite(NitelikDegeriTest.class);
		suite.addTestSuite(SureliTuketimMalzemesiTest.class);
		suite.addTestSuite(SuresizTuketimMalzemesiTest.class);
		suite.addTestSuite(TuketimMalzemesiTest.class);
		
		//hareket yeri
		suite.addTestSuite(HareketYeriTest.class);
		
		//MalzemeHareketFisi
		suite.addTestSuite(MalzemeBozukFisiTest.class);
		suite.addTestSuite(MalzemeCikisFisiTest.class);
		suite.addTestSuite(MalzemeDevirFisiTest.class);
		suite.addTestSuite(MalzemeGirisFisiTest.class);
		suite.addTestSuite(AbstractMalzemeHareketFisiTest.class);
		suite.addTestSuite(MalzemeZayiFisiTest.class);
		
		//MalzemeHareketi
		suite.addTestSuite(AbstractMalzemeHareketiTest.class);
		suite.addTestSuite(MalzemeGirisHareketiTest.class);
		suite.addTestSuite(MalzemeCikisHareketiTest.class);
		suite.addTestSuite(MalzemeDevirHareketiTest.class);
		suite.addTestSuite(MalzemeZayiHareketiTest.class);
		suite.addTestSuite(MalzemeBozukHareketiTest.class);
		
		//Malzeme State
		suite.addTestSuite(StateZimmetliTest.class);
		suite.addTestSuite(StateZayiTest.class);
		suite.addTestSuite(StateBozukTest.class);
		suite.addTestSuite(StateAskidaTest.class);
		suite.addTestSuite(StateStoktaTest.class);
		
		//paha
		suite.addTestSuite(MalzemePahasiTest.class);
		suite.addTestSuite(MalzemeGirisFisiPahasiTest.class);
		
		//$JUnit-END$
		return suite; 
	}
}
