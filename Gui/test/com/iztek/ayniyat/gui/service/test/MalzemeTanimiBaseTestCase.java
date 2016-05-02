/*
 * Created on 11.Nis.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.gui.service.test;

import org.hibernate.Session;

import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.ayniyat.test.BaseTestCase;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;


/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MalzemeTanimiBaseTestCase extends BaseTestCase {

	protected IKategorilendirilebilir malzemeler;
   	IKategorilendirilebilir demirbasMalzemeler;
   	IKategorilendirilebilir elektirikMalzemeler;
   	IKategorilendirilebilir kýrtasiyeMalzemeler;        	
   	IKategorilendirilebilir bilgisayar;
   	IKategorilendirilebilir mobilya;
   	IKategorilendirilebilir bahce;          	
	IMalzemeTanimi monitor;
	IMalzemeTanimi yazici ;
	IMalzemeTanimi kasa;
	IMalzemeTanimi masa; 
	IMalzemeTanimi dolap; 
	IMalzemeTanimi koltuk; 
	IMalzemeTanimi makas; 
	IMalzemeTanimi kazma; 
	IMalzemeTanimi kurek; 
	IMalzemeTanimi ampul;
	IMalzemeTanimi piriz;
	IMalzemeTanimi kablo;
	IMalzemeTanimi kagit;
	IMalzemeTanimi kalem;
	IMalzemeTanimi zimba;
	IMalzemeTanimi dosya;
    
    protected void setUp() throws Exception {
        super.setUp();
      	malzemeler = new Kategori("Malzemeler","");        	
       	demirbasMalzemeler = new Kategori("Demirbas Malzemeler","");
       	elektirikMalzemeler = new Kategori("Elektirik Malzemeleri","");
       	kýrtasiyeMalzemeler = new Kategori("Kýrtasiye Malzemeleri","");        	
       	bilgisayar = new Kategori("Bilgisayar","");
       	mobilya = new Kategori("Büro Mobilyalari","");
       	bahce = new Kategori("Bað-Bahçe Demirbaþý","");          	
    	monitor = new DemirbasMalzemeTanimi("Monitör","",AbstractMalzemeTanimi.ADET);
    	yazici = new DemirbasMalzemeTanimi("Yazýcý","",AbstractMalzemeTanimi.ADET);
    	kasa = new DemirbasMalzemeTanimi("Kasa","",AbstractMalzemeTanimi.ADET);
    	masa = new DemirbasMalzemeTanimi("Masa","",AbstractMalzemeTanimi.ADET); 
    	dolap = new DemirbasMalzemeTanimi("Dolap","",AbstractMalzemeTanimi.ADET); 
    	koltuk = new DemirbasMalzemeTanimi("Koltuk","",AbstractMalzemeTanimi.ADET); 
    	makas = new DemirbasMalzemeTanimi("Makas","",AbstractMalzemeTanimi.ADET); 
    	kazma = new DemirbasMalzemeTanimi("Kazma","",AbstractMalzemeTanimi.ADET); 
    	kurek =  new DemirbasMalzemeTanimi("Kürek","",AbstractMalzemeTanimi.ADET); 
    	ampul = new DemirbasMalzemeTanimi("Ampul","",AbstractMalzemeTanimi.ADET);
    	piriz = new DemirbasMalzemeTanimi("Piriz","",AbstractMalzemeTanimi.ADET);
    	kablo = new DemirbasMalzemeTanimi("Kablo","",AbstractMalzemeTanimi.ADET);
    	kagit = new DemirbasMalzemeTanimi("Kaðýt","",AbstractMalzemeTanimi.ADET);
    	kalem = new DemirbasMalzemeTanimi("Kalem","",AbstractMalzemeTanimi.ADET);
    	zimba = new DemirbasMalzemeTanimi("Zýmba","",AbstractMalzemeTanimi.ADET);
    	dosya = new DemirbasMalzemeTanimi("Dosya","",AbstractMalzemeTanimi.ADET);

    	monitor.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	monitor.addNitelikTanimi(new NitelikTanimi("Marka"));
    	yazici.addNitelikTanimi(new NitelikTanimi("Model"));
    	yazici.addNitelikTanimi(new NitelikTanimi("Marka"));
    	kasa.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	kasa.addNitelikTanimi(new NitelikTanimi("Marka"));
    	masa.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	dolap.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	koltuk.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	makas.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	kazma.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	kurek.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	ampul.addNitelikTanimi(new NitelikTanimi("Marka"));
    	piriz.addNitelikTanimi(new NitelikTanimi("Marka"));
    	kablo.addNitelikTanimi(new NitelikTanimi("Marka"));
    	kagit.addNitelikTanimi(new NitelikTanimi("Marka"));
    	kalem.addNitelikTanimi(new NitelikTanimi("Marka"));
    	zimba.addNitelikTanimi(new NitelikTanimi("Marka"));
    	dosya.addNitelikTanimi(new NitelikTanimi("Marka"));
    	

    	demirbasMalzemeler.addKategori(bilgisayar);
    	demirbasMalzemeler.addKategori(mobilya);
    	demirbasMalzemeler.addKategori(bahce);
    	bilgisayar.addKategori(monitor);
    	bilgisayar.addKategori(kasa);
    	bilgisayar.addKategori(yazici);
    	mobilya.addKategori(masa);
    	mobilya.addKategori(dolap);
    	mobilya.addKategori(koltuk);
    	bahce.addKategori(makas);
    	bahce.addKategori(kazma);
    	bahce.addKategori(kurek);
    	elektirikMalzemeler.addKategori(ampul);
    	elektirikMalzemeler.addKategori(piriz);
    	elektirikMalzemeler.addKategori(kablo);
    	kýrtasiyeMalzemeler.addKategori(kagit);
    	kýrtasiyeMalzemeler.addKategori(kalem);
    	kýrtasiyeMalzemeler.addKategori(zimba);
    	kýrtasiyeMalzemeler.addKategori(dosya);
    	

    	malzemeler.addKategori(demirbasMalzemeler);
    	malzemeler.addKategori(elektirikMalzemeler);
    	malzemeler.addKategori(kýrtasiyeMalzemeler);
        MalzemeTanimlariManager.createKategori(null, malzemeler);
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
}
