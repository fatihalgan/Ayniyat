/*
 * Created on 05.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.test.util;

import java.util.ResourceBundle;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PluginFactory {

      public static Object getObject(String key,String fileName) {          
        ResourceBundle resourceBundle = ResourceBundle.getBundle("props."+ fileName);
        String objName = resourceBundle.getString(key);
        
        if (objName == null) {
            throw new RuntimeException("Implementation not supplied for"
                    + key+ " in"+ fileName+".properties.");
        }
        try {
            return Class.forName(objName).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Factory unable to construct instance of "
                            + objName );
        }
    }
      
      public static String getParam(String key,String fileName) {
          ResourceBundle resourceBundle = ResourceBundle .getBundle("props."+ fileName);
          String paramVal = resourceBundle.getString(key);
          
          if (paramVal == null) {
              throw new RuntimeException("Value For Parameter for " + key
                      + " not found in "+ fileName +" properties.");
          }
          return paramVal;
      }

}
