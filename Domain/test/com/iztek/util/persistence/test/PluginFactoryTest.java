/*
 * Created on 11.Nis.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.util.persistence.test;

import com.iztek.ayniyat.malzemehareketleri.persistence.DemirbasNoGeneratorDAO;
import com.iztek.ayniyat.malzemehareketleri.persistence.IDemirbasNoGeneratorDAO;
import com.iztek.ayniyat.malzemetanimi.persistence.IMalzemeTanimiDAO;
import com.iztek.ayniyat.malzemetanimi.persistence.MalzemeTanimiDAO;
import com.iztek.ayniyat.yerlesim.persistence.IYerlesimDAO;
import com.iztek.ayniyat.yerlesim.persistence.YerlesimDAO;
import com.iztek.util.persistence.PluginFactory;

import junit.framework.TestCase;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PluginFactoryTest extends TestCase {

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(PluginFactoryTest.class);
    }
    
    public void testLoadResourceBundle() {
        assertNotNull(PluginFactory.RESOURCE_BUNDLE);
    }

    public void testLoadIMalzemeTanimiDAO() {
        IMalzemeTanimiDAO dao = (IMalzemeTanimiDAO)PluginFactory.getPlugin(IMalzemeTanimiDAO.class);
        assertTrue(dao instanceof MalzemeTanimiDAO);
    }
	
	public void testLoadIYerlesimDAO() {
		IYerlesimDAO dao = (IYerlesimDAO)PluginFactory.getPlugin(IYerlesimDAO.class);
		assertTrue(dao instanceof YerlesimDAO);
	}
	
	public void testLoadDemirbasNoGenDAO() {
		IDemirbasNoGeneratorDAO dao = (IDemirbasNoGeneratorDAO)PluginFactory.getPlugin(IDemirbasNoGeneratorDAO.class);
		assertTrue(dao instanceof DemirbasNoGeneratorDAO);
	}

}
