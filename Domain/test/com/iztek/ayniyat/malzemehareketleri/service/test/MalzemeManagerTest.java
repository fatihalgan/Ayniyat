package com.iztek.ayniyat.malzemehareketleri.service.test;

import java.util.Collection;
import java.util.HashMap;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemePahasi;
import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;
import com.iztek.ayniyat.malzemehareketleri.domain.StateAskida;
import com.iztek.ayniyat.malzemehareketleri.domain.StateStokta;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.commons.money.Money;
import com.iztek.util.persistence.HibernateUtil;

/**
 * @author Umit Akyol + Cagdas CIRIT +Sevgi Uslu
 *
 */
public class MalzemeManagerTest extends MainPersistedTestCase{
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(MalzemeManagerTest.class);
	}
   
   	public void testFindDemirbasByDemirbasNo(){
   		assertNotNull(MalzemeManager.findDemirbasByDemirbasNo(masa0.getDemirbasNo().toString()));
   	}
    
   public void testCreateAndDeleteMalzeme(){
        IDemirbasMalzeme mudurMasasi = new DemirbasMalzeme();
        mudurMasasi.setMalzemeTanimi(masaTanimi);
        mudurMasasi.addNitelikDegeri(new NitelikDegeri("Boyut","150*120"));
		MalzemePahasi paha = new MalzemePahasi();
        paha.setKdvOrani(new Float(0.08));
        paha.setVergisizBirimFiyat(Money.yeniTurkLirasi((float)4500.4669));
		mudurMasasi.setPaha(paha);
		mudurMasasi.setState(new StateStokta());
        MalzemeManager.createMalzeme(mudurMasasi);
        Session session = HibernateUtil.getSession();
        session.lock(mudurMasasi,LockMode.READ);
        assertNotNull(mudurMasasi.getNitelikDegeri("Boyut"));
		//assertTrue(mudurMasasi.getDemirbasNo().getSiraNo().longValue()>0);
		assertTrue(new Long(mudurMasasi.getDemirbasNo().getSiraNo()).longValue()>0);
        HibernateUtil.closeSession();       
        MalzemeManager.deleteMalzeme(mudurMasasi);
        assertNull(MalzemeManager.findDemirbasByDemirbasNo(mudurMasasi.getDemirbasNo().toString()));
    }
   
   
   	public void testUpdateMalzeme(){
   		IDemirbasMalzeme d= MalzemeManager.findDemirbasByDemirbasNo(masa1.getDemirbasNo().toString());
  // 		d.getDemirbasNo().setAltSiraNo("test");
   		//Long oldNo=d.getDemirbasNo().getSiraNo();
   		Long oldNo=new Long(d.getDemirbasNo().getSiraNo());
   		assertFalse(d.getState().getType().equals(IMalzemeState.ASKIDA));
   		d.setState(new StateAskida());
   		MalzemeManager.updateMalzeme(d,null);
   		Session session = HibernateUtil.getSession();
   		session.lock(d,LockMode.UPGRADE);
   		//assertTrue(d.getDemirbasNo().toString().equals(oldNo+"-test"));
   		assertTrue(d.getDemirbasNo().toString().equals(oldNo.toString()));
   		assertTrue(d.getState().getType().equals(IMalzemeState.ASKIDA));
   		HibernateUtil.closeSession();
   	}
   	
   	public void testGetNitelikTaniminaAitFarkliDegerler(){
   	    Collection nitelikDegerleri = MalzemeManager.getNitelikTaniminaAitFarkliDegerler((AbstractMalzemeTanimi) masaTanimi,"Boyut");
   	    Object [] nitelikler = nitelikDegerleri.toArray();
   	    assertEquals(nitelikler[0],"150*120");
   	}
   	
   	public void testGetDemirbaslarNitelikDegeriBelirlenmis(){
   	    HashMap nitelikler = new HashMap(2);
   	    nitelikler.put("Boyut","150*120");
   	    Collection demirbaslar = MalzemeManager.getDemirbaslarNitelikDegeriBelirlenmis((AbstractMalzemeTanimi) masaTanimi,nitelikler,null);
   	    Object [] demirbasMalzemeler = demirbaslar.toArray();
   	    assertEquals(demirbasMalzemeler[0],masa0);
   	    assertTrue(demirbasMalzemeler.length<2);
   	    demirbaslar = MalzemeManager.getDemirbaslarNitelikDegeriBelirlenmis((AbstractMalzemeTanimi) masaTanimi,new HashMap(0),null);
   	    demirbasMalzemeler = demirbaslar.toArray();
   	    assertFalse(demirbasMalzemeler.length<2);
   	}
   	
   	public void testGetDemirbaslarByState(){
   	    DemirbasMalzeme malzeme = (DemirbasMalzeme) MalzemeManager.getDemirbaslarByState(new StateAskida()).toArray()[0];
   	    assertEquals(malzeme,dolap);
   	}
   	
   	public void testGetMalzemeTanimlariVisibleToKullanici(){
   	    assertTrue(MalzemeManager.getMalzemeTanimlariVisibleToKullanici("Sevgi").size()>0);
   	}

    public void testGetDemirbaslarByMalzemeTanimi(){
		assertTrue(MalzemeManager.getDemirbaslarByMalzemeTanimi((AbstractMalzemeTanimi) masaTanimi).size()>0);
    }
    
    public void testGetZimmetliDemirbaslarByMalzemeTanimi(){
		assertTrue(MalzemeManager.getZimmetliDemirbaslarByMalzemeTanimi((AbstractMalzemeTanimi) koltukTanimi).size()>0);
    }
    
    public void testGetDemirbaslarByZimmetAlan(){
    	assertTrue(MalzemeManager.getDemirbaslarByZimmetAlan(umit).size()>0);
		
    }
}
