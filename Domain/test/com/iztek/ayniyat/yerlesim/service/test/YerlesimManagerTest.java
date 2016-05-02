/*
 * Created on 01.Haz.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.yerlesim.service.test;

import java.util.Collection;

import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class YerlesimManagerTest extends MainPersistedTestCase{
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(YerlesimManagerTest.class);
	}
    
    public void testFindPersonelBySicilNo(){
    	assertTrue(YerlesimManager.findPersonelBySicilNo("23222").size()!=0);
    }
    
    public void testFindAmbarByTanim(){
        AbstractZimmetAlan ambar=YerlesimManager.findAmbarByTanim("Rektörlük Ambari");
        assertNotNull(ambar);
    }
    
    public void testGetPersonelInAmbar(){
        Collection personel=YerlesimManager.getPersonelInAmbar("Rektörlük Ambari");
        assertTrue(personel.size()!=0);
        assertEquals(personel.toArray()[0],rektorlukAmbarGorevlisi1);
        
    }
}
