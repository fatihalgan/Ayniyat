/*
 * Created on 11.Nis.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.util.persistence.test;

import com.iztek.ayniyat.malzemehareketleri.persistence.IDemirbasNoGeneratorDAO;
import com.iztek.ayniyat.malzemetanimi.persistence.IMalzemeTanimiDAO;
import com.iztek.ayniyat.malzemetanimi.persistence.MalzemeTanimiDAO;
import com.iztek.ayniyat.yerlesim.persistence.IYerlesimDAO;
import com.iztek.util.persistence.DAOFactory;

import junit.framework.TestCase;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DAOFactoryTest extends TestCase {

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(DAOFactoryTest.class);
    }
    
    public void testLoadIMalzemeTanimiDAO() {
        IMalzemeTanimiDAO dao = DAOFactory.getMalzemeTanimiDAO();
        assertTrue(dao instanceof MalzemeTanimiDAO);
    }
    
    public void testLoadIYerlesimDAO() {
    	IYerlesimDAO dao = DAOFactory.getYerlesimDAO();
    	assertTrue(dao instanceof IYerlesimDAO);
    }
	
	public void testLoadIDemirbasNoGenDAO() {
		IDemirbasNoGeneratorDAO dao = DAOFactory.getDemirbasNoGenDAO();
		assertTrue(dao instanceof IDemirbasNoGeneratorDAO);
	}

}
