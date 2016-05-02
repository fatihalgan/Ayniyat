package com.iztek.ayniyat.yerlesim.domain.test;

import com.iztek.ayniyat.test.MainTestCaseWithGeneratedNumbers;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.Bina;
import com.iztek.ayniyat.yerlesim.domain.Birim;
import com.iztek.ayniyat.yerlesim.domain.Oda;
import com.iztek.ayniyat.yerlesim.domain.Personel;

/**
 * @author Cagdas CIRIT
 **/
public class AbstractZimmetAlanTest extends MainTestCaseWithGeneratedNumbers{
    
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(AbstractZimmetAlanTest.class);
	}
	
    public void testCreatePersonel() {
        Personel personel = new Personel("545","Ali Osman","Y�lmaz");
        assertEquals(personel.getTanim(), "Ali Osman Y�lmaz");
        assertEquals(personel.getSicilNo(), "545");
        assertEquals(personel.getAd(), "Ali Osman");
        assertEquals(personel.getSoyad(), "Y�lmaz");
        personel.setTanim("Ali Y�lmaz");
        assertEquals(personel.getTanim(), "Ali Y�lmaz");
        assertEquals(personel.getAd(), "Ali");
        assertEquals(personel.getSoyad(), "Y�lmaz");
    }
    
    public void testCreateOda() {
    	Oda oda = new Oda("L-204",Oda.LAB,new Integer(2));
    	assertEquals(oda.getTanim(), "L-204");
    	assertEquals(oda.getTip(), Oda.LAB);
    	assertEquals(oda.getKat(), new Integer(2));
    }
    
    public void testCreateAmbar() {
    	Ambar ambar = new Ambar("M�hendislik Ambar�");
    	assertEquals(ambar.getTanim(), "M�hendislik Ambar�");
    }
    
    public void testCreateBina() {
    	Bina bina = new Bina("BUAM");
    	assertEquals(bina.getTanim(), "BUAM");
    }
    
    public void testCreateBirim() {
    	Birim birim = new Birim("M�hendislik Fak�ltesi");
    	assertEquals(birim.getTanim(), "M�hendislik Fak�ltesi");
    }
    
    public void testCreateYapi() {
    	Birim yapi = new Birim("Kamp�s");
    	assertEquals(yapi.getTanim(), "Kamp�s");
    } 
    
    public void testGetZimmetliMalzemeler(){
    	assertTrue(cagdas.getZimmetliMalzemeler().size()!=0);
    	assertTrue(cagdasOda.getZimmetliMalzemeler().size()==0);
    }
    
    public void testAddDemirbasMalzeme(){
        umit.addDemirbasMalzeme(sahipsizMalzeme);
    	assertEquals(umit.getZimmetliMalzemeler().toArray()[1],sahipsizMalzeme);
    	assertEquals(sahipsizMalzeme.getZimmetSahibi(),umit);
    }
    
    public void testRemoveDemirbasMalzeme(){
    	assertNotNull(cagdas.getZimmetliDemirbasByNodeValue("08-Masa"));
    	assertEquals(masa0.getZimmetSahibi(),cagdas);
    	cagdas.removeDemirbasMalzeme(masa0);
    	assertNull(cagdas.getZimmetliDemirbasByNodeValue("08-Masa"));
    	assertNull(masa0.getZimmetSahibi());
    }
    
    public void testHasDemirbas(){
    	assertFalse(umit.hasDemirbas(masa0));
    	assertTrue(cagdas.hasDemirbas(masa0));
    }
    
    public void testGetZimmetliDemirbasByTanim(){
    	assertEquals(cagdas.getZimmetliDemirbasByNodeValue("08-Masa"),masa0);
    	assertNull(cagdas.getZimmetliDemirbasByNodeValue("10-Koltuk"));
    }
    
    public void testAddKategori2Ambar(){
    	Ambar ambar = new Ambar ("Alt ambar");
    	rektorlukAmbar.addKategori(cagdas);
    	rektorlukAmbar.addKategori(ambar);
    	assertTrue(rektorlukAmbar.hasInChildren(cagdas));
    	assertTrue(rektorlukAmbar.hasInChildren(ambar));
    	
    	try {
    		rektorlukAmbar.addKategori(umitOda);
    		fail("Adding an Oda should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
    		rektorlukAmbar.addKategori(buam);
    		fail("Adding a Bina should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
    		rektorlukAmbar.addKategori(fenFak�ltesi);
    		fail("Adding a Birim should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    }
    
    public void testAddKategori2Personel(){
    	try {
			umit.addKategori(umitOda);
			fail("Adding an Oda should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
			umit.addKategori(buam);
			fail("Adding a Bina should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
			umit.addKategori(fenFak�ltesi);
			fail("Adding a Birim should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
			umit.addKategori(rektorlukAmbar);
			fail("Adding an Ambar should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
			umit.addKategori(cagdas);
			fail("Adding a Personel should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    }
    
    public void testAddKategori2Oda(){
    	try {
			umitOda.addKategori(cagdasOda);
			fail("Adding an Oda should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
    	    umitOda.addKategori(rektorlukAmbar);
			fail("Adding an Ambar should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
    	    umitOda.addKategori(buam);
			fail("Adding an Bina should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
    	    umitOda.addKategori(fenFak�ltesi);
			fail("Adding an Birim should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    }
    
    public void testAddKategori2Bina(){
    	try {
			buam.addKategori(umit);
			fail("Adding an Personel should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
			buam.addKategori(rektorlukAmbar);
			fail("Adding an Ambar should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
			buam.addKategori(muhDekanlik);
			fail("Adding a Bina should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    	try {
			buam.addKategori(fenFak�ltesi);
			fail("Adding a Birim should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    }
    
    public void testAddKategori2Birim(){
    	try {
			fenFak�ltesi.addKategori(umit);
			fail("Adding a Personel should throw an IllegalStateException");
		} catch (IllegalStateException e) {}
    }
    
    /** 
     * @author Umit Akyol
     */
    public void testCopy() {
        //fen fakultesi ve alt kategorileri hic bir  zimmetli malzeme
        //icermedikleri icin kopyalanabilir
        AbstractZimmetAlan copy = null;
        try {
            copy = (AbstractZimmetAlan)fenFak�ltesi.copy();
        } catch (Exception e) {}
        assertEquals(copy.getTanim(),fenFak�ltesi.getTanim());
        assertEquals(copy.getAltKategoriler().size(),fenFak�ltesi.getAltKategoriler().size());
        assertNotNull(copy.getAltKategoriler());
    }
    /**
     * @author Umit Akyol
     */
    public void testCopyWithZimmetliMalzeme() {
        //muhendislik fakultesi zimmetli malzeme "kasa" sahibi oldugu icin
        //kopyalanamaz
        try{
            AbstractZimmetAlan copy = (AbstractZimmetAlan) muhendislik.copy();
            fail("Zimmet sahibi ZimmetAlanlar copyalanamaz");
        }catch (Exception e) {}
    }
    
    /**
     * @author Umit Akyol
     */
    public void testPersonelCopy(){
        //personel tekbas�na kopyalan�rsa zimmet sahibi olsada copyalama yap�l�r
        //fakat zimmetli malzemeler kopyalanmaz
        AbstractZimmetAlan copy = null;
        try {
            copy = (AbstractZimmetAlan) cagdas.copy();
        } catch (Exception e) {}
        assertEquals(copy.getTanim(),cagdas.getTanim());
        assertFalse(copy.getZimmetliMalzemeler().size()== cagdas.getZimmetliMalzemeler().size());
    }
    
    /** 
     * @author Umit Akyol
     */
    public void testIsKopyalanabilir() {
        //fen fakultesi ve alt kategorileri hic bir  zimmetli malzeme
        //icermedikleri icin kopyalanabilir
        assertTrue(fenFak�ltesi.isKopyasiAlinabilir());
    }
    
    /** 
     * @author Umit Akyol
     */
    public void testIsKopyalanabilirWithZimmetliMalzeme() {
        //zimmetli malzeme iceren zimmetalanlar kopyalanamaz
        assertFalse(cagdas.isKopyasiAlinabilir());
    }
    
    public void testEquals(){
        assertTrue(sevgi0.equals(sevgi0));
        assertFalse(sevgi0.equals(sevgi1));
    }
}
