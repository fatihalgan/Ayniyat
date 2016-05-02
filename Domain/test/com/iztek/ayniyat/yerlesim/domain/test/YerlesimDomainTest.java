/*
 * Created on 04.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.yerlesim.domain.test;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.test.KategoriDomainTest;
import com.iztek.ayniyat.test.util.ObjectFactory;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Bina;
import com.iztek.ayniyat.yerlesim.domain.Birim;
import com.iztek.ayniyat.yerlesim.domain.Oda;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class YerlesimDomainTest extends KategoriDomainTest{

	
    protected void setUp() throws Exception {
        super.setUp();
        
        kategoriType="Yerlesim";
        root = new Birim("IYTE Kampus");
        child1 = new Birim("Muhendislik Fakültesi");
        child2 = new Birim("Rektörlük");
        child11 = new Bina("Dekanlik");
        child111 = new Oda("301",Oda.PERSONEL,new Integer(3));
        
        root.addKategori(child1);
        root.addKategori(child2);
        child1.addKategori(child11);
        child11.addKategori(child111);

    }

    protected void tearDown() throws Exception {
        super.tearDown();		
    }

    public IKategorilendirilebilir getObjectFromProperties(String key){     
        AbstractZimmetAlan instance = (AbstractZimmetAlan) ObjectFactory.getYerlesimDomainObject(key);
        instance.setTanim(key);
        return instance;
    }
	
    public String getStringFromProperties(String key){        
        return ObjectFactory.getYerlesimString(key);
    }
    
    public IKategorilendirilebilir setNodeValue(IKategorilendirilebilir obj,String nodeValue){
        AbstractZimmetAlan instance = (AbstractZimmetAlan)obj;
        instance.setTanim(nodeValue);
        return instance;    
    }

}
