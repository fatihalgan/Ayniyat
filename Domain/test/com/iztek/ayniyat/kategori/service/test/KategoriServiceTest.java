/*
 * Created on 07.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.kategori.service.test;

import org.hibernate.LockMode;
import org.hibernate.Session;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.AbstractKategoriManager;
import com.iztek.ayniyat.kategori.test.KategoriTestCase;
import com.iztek.util.persistence.HibernateUtil;

/**
 * @author Sevgi Uslu
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class KategoriServiceTest extends KategoriTestCase {

    public void testFindRootKategori() {
        IKategorilendirilebilir rootTemp = AbstractKategoriManager
                .findRootKategori(kategoriType);
        assertEquals(rootTemp, root);
    }

    public void testCreateKategori() {
        IKategorilendirilebilir third = getObjectFromProperties("newThirdLevel");
        IKategorilendirilebilir second = getObjectFromProperties("newSecondLevel");
        IKategorilendirilebilir first = (IKategorilendirilebilir) AbstractKategoriManager
                .findKategoriByMatchingTanim(child1.getTanim(),kategoriType).toArray()[0];
        second.addKategori(third);
        AbstractKategoriManager.createKategori(first, second);
        Session session = HibernateUtil.getSession();
        session.lock(first, LockMode.READ);
        assertTrue(first.hasInChildren(second));
        HibernateUtil.closeSession();
    }

    public void testUpdateKategori() {
        IKategorilendirilebilir rootTemp = AbstractKategoriManager.findRootKategori(kategoriType);
        setTanim(rootTemp,getStringFromProperties("update"));
        AbstractKategoriManager.updateKategori(rootTemp);
        assertTrue(AbstractKategoriManager.findKategoriByMatchingTanim(
                getStringFromProperties("update"),kategoriType).size() != 0);
    }

    public void testDeleteKategori() {
        AbstractKategoriManager.deleteKategori(child1);
        assertTrue(AbstractKategoriManager.findKategoriByMatchingTanim(
                child1.getTanim(),kategoriType).size() == 0);
    }

    public void testFindKategoriByMatchingTanim() {
        assertNotNull(AbstractKategoriManager.findKategoriByMatchingTanim(
                child1.getTanim(),kategoriType));
    }
    
    public void testFindKategoriByMatchingTanimIgnoreCase() {
        assertNotNull(AbstractKategoriManager.findKategoriByMatchingTanim(
                child1.getTanim().toLowerCase(),kategoriType));
        assertNotNull(AbstractKategoriManager.findKategoriByMatchingTanim(
                child1.getTanim().toUpperCase(),kategoriType));
    }
    
    public IKategorilendirilebilir getObjectFromProperties(String key) {
        return null;
    }

    public String getStringFromProperties(String key) {
        return null;
    }

    public IKategorilendirilebilir setTanim(IKategorilendirilebilir obj,String tanim) {
        return null;
    }

}
