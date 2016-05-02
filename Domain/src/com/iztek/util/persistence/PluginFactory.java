/*
 * Created on 11.Nis.2005
 */
package com.iztek.util.persistence;

import java.util.ResourceBundle;

/**
 * @author Fatih Algan
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PluginFactory {

    public static final String BUNDLE_NAME = "props.persistence";//$NON-NLS-1$
    public static final String BUNDLE_NAME1= "props.fisler";
   
    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME);

    public static final ResourceBundle RESOURCE_BUNDLE1 = ResourceBundle
    .getBundle(BUNDLE_NAME1);
    
    public static Object getPlugin(Class iFace) {
        String implName = RESOURCE_BUNDLE.getString(iFace.getName());
        if (implName == null) {
            throw new RuntimeException("Implementation not supplied for "
                    + iFace.getName() + " in PluginFactory properties.");
        }
        try {
            return Class.forName(implName).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Factory unable to construct instance of "
                            + iFace.getName());
        }
    }

    public static String getParam(String paramName) {
        String paramVal = RESOURCE_BUNDLE.getString(paramName);
        if (paramVal == null) {
            throw new RuntimeException("Value For Parameter for " + paramName
                    + " not found in PluginFactory properties.");
        }
        return paramVal;
    }
    public static String getParamforFisler(String paramName) {
        String paramVal = RESOURCE_BUNDLE1.getString(paramName);
        if (paramVal == null) {
            throw new RuntimeException("Value For Parameter for " + paramName
                    + " not found in PluginFactory properties.");
        }
        return paramVal;
    }
}

