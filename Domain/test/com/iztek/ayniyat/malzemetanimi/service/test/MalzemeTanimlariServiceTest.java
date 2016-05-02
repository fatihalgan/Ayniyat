package com.iztek.ayniyat.malzemetanimi.service.test;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.test.KategoriServiceTest;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.ayniyat.test.util.ObjectFactory;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Fatih Algan
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MalzemeTanimlariServiceTest extends KategoriServiceTest {

    protected IMalzemeTanimi monitor;

    protected NitelikTanimi boyut;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(MalzemeTanimlariServiceTest.class);
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

        monitor = new DemirbasMalzemeTanimi("Monitör","02",AbstractMalzemeTanimi.ADET);
        boyut = new NitelikTanimi("Boyut");
        monitor.addNitelikTanimi(boyut);
        child11.addKategori(monitor);

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
        Kategori instance = (Kategori) ObjectFactory
                .getMalzemeTanimiDomainObject(key);
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
    

    public void testGetAnaKategori() {
        IKategorilendirilebilir monitor = (IKategorilendirilebilir) MalzemeTanimlariManager
                .findKategoriByMatchingTanim("Monitör",
                        DAOFactory.MALZEME_TANIMI).toArray()[0];
        Session session = HibernateUtil.getSession();
        session.lock(monitor, LockMode.READ);
        IKategorilendirilebilir parent = (IKategorilendirilebilir) monitor.getAnaKategori();
        assertNotNull(parent);
        assertEquals(parent.getTanim(), "Bilgisayar");
        assertEquals(monitor.getAnaKategori(), parent);
        HibernateUtil.closeSession();
    }
    public void testUpdateKategoriByNitelikler() {
        NitelikTanimi renk = new NitelikTanimi("Renk");
        monitor.addNitelikTanimi(renk);
        monitor.removeNitelikTanimi("Boyut");
        MalzemeTanimlariManager.updateKategori(monitor);
        assertNull(monitor.getNitelikTanimi("Boyut"));
        assertNotNull(monitor.getNitelikTanimi("Renk"));
    }
}
