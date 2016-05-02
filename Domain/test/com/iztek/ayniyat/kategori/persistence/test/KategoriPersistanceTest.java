/*
 * Created on 07.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.kategori.persistence.test;

import java.util.Collection;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.persistence.IKategoriDAO;
import com.iztek.ayniyat.kategori.test.KategoriTestCase;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KategoriPersistanceTest extends KategoriTestCase{

    public void testFindRootKategori() {
        IKategorilendirilebilir temp = getObjectFromProperties().findRootKategori();
        assertEquals(temp.getTanim(),root.getTanim());
    }
    
    public void testFindKategoriByMatchingTanim() {
        Collection col = getObjectFromProperties().findKategoriByMatchingTanim(getStringFromProperties("searchByTanim"));
        assertTrue(col.size() > 0);
    }    
    
    public void testFindKategoriByMatchingTanimIgnoreCase() {
        Collection col = getObjectFromProperties().findKategoriByMatchingTanimIgnoreCase(getStringFromProperties("searchByTanimIgnoreCase"));
        assertTrue(col.size() > 0);
    } 
    
    public void testFindKategoriById(){
        IKategorilendirilebilir root = getObjectFromProperties().findRootKategori();
        assertEquals(root,getObjectFromProperties().findKategoriById(root.getId()));
    }
    
    public IKategoriDAO getObjectFromProperties(){        
        return null;
    }
    
    public String getStringFromProperties(String keyForObject){        
        return null;
    }
    
}
