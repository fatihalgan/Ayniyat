/*
 * Created on 30.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.gui.service;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InputController {

    public static boolean isInteger(String input){
        try {
            if(Integer.parseInt(input) >= 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    public static boolean isDouble(String input){
        try {
            if(Double.parseDouble(input) >= 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
        
    }
    
}
