package com.iztek.ayniyat.malzemehareketleri.service.test;


import com.iztek.ayniyat.test.MainPersistedTestCase;

/**
 * @author Umit Akyol
 *
 */
public class MalzemeHareketFisiTest extends MainPersistedTestCase {
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(MalzemeHareketFisiTest.class);
	}
    
    public void testCreateMalzemeGirisFisi(){
        /*MalzemeHareketi malzemeHareketi = new MalzemeHareketi();
        malzemeHareketi.addMalzeme(masa0);
        MalzemeGirisFisi girisFisi = new MalzemeGirisFisi();
        girisFisi.setOwningMalzemeHareketi(malzemeHareketi);
        girisFisi.setAlindigiKurulus("Gorke Tic.");
        girisFisi.setBelgeNo(new Integer(111));
        girisFisi.setDuzenlemeTarihi(new Date());
        
        MalzemeHareketFisiManager.createMalzemeHareketFisi(girisFisi);
        Session session = HibernateUtil.getSession();
        session.lock(girisFisi,LockMode.READ);
        assertNotNull(girisFisi.getOwningMalzemeHareketi());
        assertTrue(girisFisi.getOwningMalzemeHareketi().getMalzemeler().size()==1);
        HibernateUtil.closeSession();*/
    }
    
}
