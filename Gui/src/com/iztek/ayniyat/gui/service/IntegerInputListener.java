/*
 * Created on 29.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.gui.service;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IntegerInputListener extends KeyAdapter{
  
    private JTextField jTextField = null;
    
    public void keyPressed(KeyEvent arg0) {
        checker(arg0);
    }
    
    public void keyReleased(KeyEvent arg0) {
    }
    
    public void keyTyped(KeyEvent arg0) {
        arg0.consume();
    }

    
    public void checker(KeyEvent arg0){  
        String keyString;     
        jTextField=(JTextField) arg0.getSource();        
        char key = arg0.getKeyChar();
        switch (key) {
        		case '0':
        		case '1':
        		case '2':
        		case '3':
        		case '4':
        		case '5':
        		case '6':
        		case '7':
        		case '8':
        		case '9':
        		case '.':	
        		    keyString=jTextField.getText()+Character.toString(key);
        		    break;
        		default:
        		    keyString=jTextField.getText();
        		    break;
        }
	        jTextField.setText(keyString);
        
    }

}
