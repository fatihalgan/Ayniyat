/*
 * Created on 11.Tem.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.kullanici.test;

import java.util.Collection;

import com.iztek.ayniyat.kullanici.Kullanici;
import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.util.persistence.DAOFactory;


/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KullaniciManagerTest extends MainPersistedTestCase {

    public static void main(String[] args) {
		junit.swingui.TestRunner.run(KullaniciManagerTest.class);
	}
    
    public void testGetKullaniciByUserNameAndPassword(){
        assertNotNull(KullaniciManager.getKullaniciByUserNameAndPassword("Sevgi","123"));
    }
    
    public void testIsAmbarInKullaniciAmbarList(){
        Kullanici kullanici=DAOFactory.getKullaniciDAO().getKullaniciByUserNameAndPassword("Umit","000");
        assertTrue(KullaniciManager.isAmbarInKullaniciAmbarList(kullanici,(Ambar)muhendislikAmbar));
        assertTrue(KullaniciManager.isAmbarInKullaniciAmbarList(kullanici,(Ambar)rektorlukAmbar));     
    }
    
    public void testGetMalzemelerVisibleToKullanici(){
        Kullanici kullanici=new Kullanici("Sevgi","123");
        Collection returnVal = null;
		returnVal =KullaniciManager.getMalzemelerVisibleToKullanici(kullanici);
		assertEquals(monitor1,(IDemirbasMalzeme)returnVal.toArray()[0]);
    }
}
