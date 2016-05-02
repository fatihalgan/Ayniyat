package com.iztek.ayniyat.yerlesim.persistence.test;

import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.util.persistence.DAOFactory;

/**
 * @author Sevgi Uslu
 */
public class YerlesimDAOTest extends MainPersistedTestCase {

	public static void main(String[] args) {
		junit.swingui.TestRunner.run(YerlesimDAOTest.class);
	}
	
    public void testFindPersonelBySicilNo(){
    	assertTrue(DAOFactory.getYerlesimDAO().findPersonelBySicilNo("23222").size()!=0);
    	}
    
    public void testFindAmbarByTanim(){
        AbstractZimmetAlan ambar=DAOFactory.getYerlesimDAO().findAmbarByTanim("Rektörlük Ambari");
    	assertNotNull(ambar);
    }
}
