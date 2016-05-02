/*
 * Created on 05.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.test.util;

import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
/**
 * @author Sevgi Uslu
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ObjectFactory {
    
    public static final String MALZEME_TANIMI = "malzemeTanimi";
    public static final String YERLESIM = "yerlesim";

    public static Kategori getMalzemeTanimiDomainObject(String key) {
        Kategori instance = (Kategori) PluginFactory.getObject(key,MALZEME_TANIMI);
        return instance;
    }

    public static String getMalzemeTanimiString(String key) {
        return PluginFactory.getParam(key, MALZEME_TANIMI);
    }

    public static AbstractZimmetAlan getYerlesimDomainObject(String key) {
        AbstractZimmetAlan instance = (AbstractZimmetAlan) PluginFactory
                .getObject(key, YERLESIM);
        return instance;
    }
    public static String getYerlesimString(String key) {
        return PluginFactory.getParam(key, YERLESIM);
    }
}
