/*
 * Created on 04.May.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.kategori.test;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.service.KategoriMergeStateManager;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KategoriDomainTest extends KategoriTestCase{
 
    public void testAddKategori() {
        IKategorilendirilebilir newFirstLevel = getObjectFromProperties("newFirstLevel");
        root.addKategori(newFirstLevel);
        assertTrue(root.hasInChildren(newFirstLevel));
    }
    
    public void testAddNullKategori() {
        try {
            IKategorilendirilebilir altKategori = null;
            root.addKategori(altKategori);
            fail("Adding a null Kategori should throw an IllegalStateException");
        } catch(IllegalStateException e) {
            
        }
    }
    
    public void testRemoveKategori() {
    	assertTrue(child1.hasInChildren(child11));
    	child1.removeKategori(child11);
    	assertFalse(child1.hasInChildren(child11));
    	assertNull(child11.getAnaKategori());
    }
    
    public void testMergeKategori(){
        IKategorilendirilebilir newFirstLevel = getObjectFromProperties("newFirstLevel");
        IKategorilendirilebilir newSecondLevel = getObjectFromProperties("newSecondLevel");
        setNodeValue(newFirstLevel,child1.getNodeValue());
        setNodeValue(newSecondLevel,"00-newTanim");
        newFirstLevel.addKategori(newSecondLevel);
    	child1.mergeKategori(newFirstLevel,new KategoriMergeStateManager());
    	assertNull(child1.getChildByNodeValue(newFirstLevel.getNodeValue()));
    	assertNotNull(child1.getChildByNodeValue(newSecondLevel.getNodeValue()));
    }
    
    public void testMergeNullKategori() {
        try {
            IKategorilendirilebilir altKategori = null;
            root.mergeKategori(altKategori,new KategoriMergeStateManager());
            fail("Adding a null Kategori should throw an IllegalStateException");
        } catch(IllegalStateException e) {
            
        }
    }
    
    public void testGetAltKategoriler() {
    //	assertTrue(child1111.getAltKategoriler().isEmpty());
    	assertNotNull(child1.getAltKategoriler());
    }
    
    public void testGetAnaKategori() {
    	assertEquals(child1.getAnaKategori(),root);
    }
    
    public void testHasInChildren(){
    	assertTrue(root.hasInChildren(child111));
    	assertFalse(child2.hasInChildren(child111));
    }
    
    public void testGetChildByTanim() {
    	assertNotNull(root.getChildByNodeValue(child1.getNodeValue()));
    }    
    
    public void testGetIndexOfChild(){
    	assertEquals(root.getIndexOfChild(child1),0);
    	assertEquals(child2.getIndexOfChild(child111),-1);
    }
    
    public void testGetMemento(){
        Object memento = child1.getMemento();
        assertNotNull(memento);
    }
    public void testSetMemento(){
        Object memento = root.getMemento();
        root.removeKategori(child1);
        root.setMemento(memento);
        assertNotNull(root.getChildByNodeValue(child1.getNodeValue()));
        
    }
    
    public void testEquals(){
        IKategorilendirilebilir newFirstLevel = getObjectFromProperties("newFirstLevel");
        setNodeValue(newFirstLevel,child1.getNodeValue());
        assertTrue(child1.equals(child1));
    	assertFalse(child1.equals(newFirstLevel));
    	newFirstLevel.setAnaKategori(root);
    	assertTrue(child1.equals(newFirstLevel));
    }
    
    public IKategorilendirilebilir getObjectFromProperties(String key){        
        return null;
    }
    
    public String getStringFromProperties(String key){        
        return null;
    }
    
    public IKategorilendirilebilir setNodeValue(IKategorilendirilebilir obj,String nodeValue){
        return null;    
    }
    /**
     * @author Umit Akyol
     */
    public void testCopy(){
        IKategorilendirilebilir copy = null;
        try {
            copy = (IKategorilendirilebilir)child11.copy();
        } catch (Exception e) {}
        assertTrue(copy.getTanim().equals(child11.getTanim()));
    	assertNotNull(copy.getAltKategoriler());
    	assertEquals(copy.getAltKategoriler().size(),child11.getAltKategoriler().size());
    	
    }
    public void testIsKopyalanabilir(){
        assertTrue(child11.isKopyasiAlinabilir());
    }
    
}
