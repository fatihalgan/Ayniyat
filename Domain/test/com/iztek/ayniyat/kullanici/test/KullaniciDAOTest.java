/*
 * Created on 08.Tem.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.kullanici.test;

import java.util.Collection;

import com.iztek.ayniyat.kullanici.Kullanici;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.util.persistence.DAOFactory;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KullaniciDAOTest extends MainPersistedTestCase {
    
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(KullaniciDAOTest.class);
	}
    
    public void testGetKullaniciByUserNameAndPassword(){
		Kullanici returnVal = null;
		returnVal =DAOFactory.getKullaniciDAO().getKullaniciByUserNameAndPassword("Sevgi","123");
		assertNotNull(returnVal);
    }     
    
    public void testGetMalzemelerVisibleToKullanici(){
        Kullanici kullanici=new Kullanici("Sevgi","123");
        Collection returnVal = null;
		returnVal =DAOFactory.getKullaniciDAO().getMalzemelerVisibleToKullanici(kullanici);
		assertEquals(monitor1,(IDemirbasMalzeme)returnVal.toArray()[0]);
    }   
    
}

