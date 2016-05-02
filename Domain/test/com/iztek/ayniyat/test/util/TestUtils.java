package com.iztek.ayniyat.test.util;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.AbstractKategoriManager;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestUtils {
    
    public static IKategorilendirilebilir findKategoriByTanim(String tanim, String kategoriType) {
        Collection col = AbstractKategoriManager.findKategoriByMatchingTanim(tanim,kategoriType);
        Iterator it = col.iterator();
        if(it.hasNext() ) 
        	return (IKategorilendirilebilir)it.next();

        return null;
    }
    /**
     * @author Umit Akyol
     * */
    public static IKategorilendirilebilir findKategoriByNodeValue(String parent,String nodeValue,String kategoriType) {
    	IKategorilendirilebilir child=null;
    	IKategorilendirilebilir parentKategori = findKategoriByTanim(parent,kategoriType);
     	Session session = HibernateUtil.getSession();
     	session.lock(parentKategori, LockMode.READ);
        child= parentKategori.getChildByNodeValue(nodeValue);
        HibernateUtil.closeSession();
        return child;
    }
    /**
     * @author Umit Akyol
     * */
    public static int getChildKategoriCountByTanim(String tanim){
        Collection col = MalzemeTanimlariManager.findKategoriByMatchingTanim(tanim,DAOFactory.MALZEME_TANIMI);
        return col.size();
    }
}
