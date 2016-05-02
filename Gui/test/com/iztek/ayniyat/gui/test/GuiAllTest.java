package com.iztek.ayniyat.gui.test;

import com.iztek.ayniyat.gui.service.test.InputControllerTest;
import com.iztek.ayniyat.gui.service.test.InputDuzenleyiciTest;
import com.iztek.ayniyat.gui.service.test.SearchTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Umit Akyol
 *
 */
public class GuiAllTest {

    public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.iztek.ayniyat.test");
		//service tests
		suite.addTestSuite(InputControllerTest.class);
		suite.addTestSuite(InputDuzenleyiciTest.class);
		suite.addTestSuite(SearchTest.class);
        return suite;
    }
}
