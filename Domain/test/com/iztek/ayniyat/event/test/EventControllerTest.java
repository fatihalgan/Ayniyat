package com.iztek.ayniyat.event.test;

import com.iztek.ayniyat.event.KategoriCutEvent;
import com.iztek.ayniyat.event.KategoriEvent;
import com.iztek.ayniyat.event.KategoriRemoveEvent;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.util.persistence.DAOFactory;

/**
 * @author Cagdas CIRIT
 **/
public class EventControllerTest extends MainPersistedTestCase{
    public static void main(String[] args) {
        junit.swingui.TestRunner.run(EventControllerTest.class);
    }
    
    public void testAddKategori(){
    	Kategori child = new Kategori("Spor Malzemeleri","01");
    	Kategori parent = (Kategori) MalzemeTanimlariManager.findKategoriByMatchingTanim("Malzemeler", DAOFactory.MALZEME_TANIMI).toArray()[0];
    	eventController.addKategori(new KategoriEvent(this,parent,child));
    	assertEquals(child.getAnaKategori(),parent);
    	assertTrue(MalzemeTanimlariManager.findKategoriByMatchingTanim("Spor Malzemeleri", DAOFactory.MALZEME_TANIMI).size()!=0);
    }
    
    public void testRemoveKategori(){
    	Kategori child = (Kategori) MalzemeTanimlariManager.findKategoriByMatchingTanim("Ampul", DAOFactory.MALZEME_TANIMI).toArray()[0];
    	eventController.removeKategori(new KategoriRemoveEvent(this,null,child,0));
    	assertTrue(MalzemeTanimlariManager.findKategoriByMatchingTanim("Ampul", DAOFactory.MALZEME_TANIMI).size()==0);
    }
    
    public void testChangeKategori(){
    	Kategori child = (Kategori) MalzemeTanimlariManager.findKategoriByMatchingTanim("Dosya",DAOFactory.MALZEME_TANIMI).toArray()[0];
    	child.setTanim("Klasor");
    	assertTrue(MalzemeTanimlariManager.findKategoriByMatchingTanim("Klasor", DAOFactory.MALZEME_TANIMI).size()==0);
    	eventController.changeKategori(new KategoriEvent(this,null,child));
    	assertTrue(MalzemeTanimlariManager.findKategoriByMatchingTanim("Dosya", DAOFactory.MALZEME_TANIMI).size()==0);
    	assertTrue(MalzemeTanimlariManager.findKategoriByMatchingTanim("Klasor", DAOFactory.MALZEME_TANIMI).size()!=0);
    }
    
    public void testCopyKategori(){
		assertTrue(MalzemeTanimlariManager.findKategoriByMatchingTanim("Ampul", DAOFactory.MALZEME_TANIMI).size()==1);
		IKategorilendirilebilir destination = (IKategorilendirilebilir) MalzemeTanimlariManager.findKategoriByMatchingTanim("Bilgisayar", DAOFactory.MALZEME_TANIMI).toArray()[0];
    	IKategorilendirilebilir source = (IKategorilendirilebilir) MalzemeTanimlariManager.findKategoriByMatchingTanim("Ampul", DAOFactory.MALZEME_TANIMI).toArray()[0];
    	eventController.copyKategori(new KategoriEvent(this,source,destination));
    	assertTrue(MalzemeTanimlariManager.findKategoriByMatchingTanim("Ampul", DAOFactory.MALZEME_TANIMI).size()==2);
    }
    
    public void testCutKategori(){
    	IKategorilendirilebilir destination = (Kategori) DAOFactory.getMalzemeTanimiDAO().findKategoriByMatchingTanim("Demirbas Malzemeler").toArray()[0];
    	IKategorilendirilebilir source = (Kategori) DAOFactory.getMalzemeTanimiDAO().findKategoriByMatchingTanim("Ampul").toArray()[0];
    	IKategorilendirilebilir elektirikMalzemeleri = (Kategori) DAOFactory.getMalzemeTanimiDAO().findKategoriByMatchingTanim("Elektronik Malzemeler").toArray()[0];
    	
    	assertTrue(MalzemeTanimlariManager.findKategoriByMatchingTanim("Ampul", DAOFactory.MALZEME_TANIMI).size()==1);
    	eventController.cutKategori(new KategoriCutEvent(this,source,destination,0,elektirikMalzemeleri));
    	assertTrue(MalzemeTanimlariManager.findKategoriByMatchingTanim("Ampul", DAOFactory.MALZEME_TANIMI).size()==1);
    	assertNotNull(((Kategori) destination).getFirstLevelChildByNodeValue(source.getNodeValue()));
    }
}
