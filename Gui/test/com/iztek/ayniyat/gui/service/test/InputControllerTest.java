/*
 * Created on 30.May.2005
 *
 */
package com.iztek.ayniyat.gui.service.test;


import com.iztek.ayniyat.gui.service.InputController;

import junit.framework.TestCase;

/**
 * @author Sevgi Uslu
 *
 */
public class InputControllerTest extends TestCase{

	public static void main(String[] args) {
		junit.swingui.TestRunner.run(InputControllerTest.class);
	}
	
    public void testIsInteger(){
        assertFalse(InputController.isInteger("þlcxþl"));
        assertTrue(InputController.isInteger("123"));
    
    }
}
