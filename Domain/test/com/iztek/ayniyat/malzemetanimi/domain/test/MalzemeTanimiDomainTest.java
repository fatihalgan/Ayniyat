/*
 * Created on 04.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.malzemetanimi.domain.test;

import java.util.StringTokenizer;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.test.KategoriDomainTest;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.test.util.ObjectFactory;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MalzemeTanimiDomainTest extends KategoriDomainTest{

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(MalzemeTanimiDomainTest.class);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        kategoriType="MalzemeTanimi";
        root = new Kategori("Malzemeler","01");
        child1 = new Kategori("Demirbas Malzemeler","01");
        child2 = new Kategori("Tüketim Malzemeleri","02");
        child11 = new Kategori("Bilgisayar","02");
        child111= new DemirbasMalzemeTanimi("Yazýcý","01",AbstractMalzemeTanimi.ADET);
        
        root.addKategori(child1);
        root.addKategori(child2);
        child1.addKategori(child11);
        child11.addKategori(child111);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public IKategorilendirilebilir getObjectFromProperties(String key) {
        Kategori instance = (Kategori) ObjectFactory
                .getMalzemeTanimiDomainObject(key);
        instance.setTanim(key);
        return instance;
    }
    public String getStringFromProperties(String key) {
        return ObjectFactory.getMalzemeTanimiString(key);
    }
    public IKategorilendirilebilir setNodeValue(IKategorilendirilebilir obj,String nodeValue) {
    	StringTokenizer tk= new StringTokenizer(nodeValue,"-");
    	String kod= tk.nextToken();
    	String tanim= tk.nextToken();
        Kategori instance = (Kategori) obj;
        instance.setKod(kod);
        instance.setTanim(tanim);
        return instance;
    }
}
