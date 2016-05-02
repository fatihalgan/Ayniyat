/*
 * Created on 13.Nis.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.gui.service.test;

import java.util.ArrayList;
import java.util.Collection;
import com.iztek.ayniyat.gui.service.Search;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.util.persistence.DAOFactory;

/**
 * @author Sevgi
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchTest extends MalzemeTanimiBaseTestCase{
	
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(SearchTest.class);
	}
    
    public void testFindPath(){
    	Collection col=MalzemeTanimlariManager.findKategoriByMatchingTanim("Demirbas Malzemeler", DAOFactory.MALZEME_TANIMI);
		ArrayList path=new ArrayList();
		Search.findPath(path,(IKategorilendirilebilir)col.toArray()[0]);
		assertEquals(((IKategorilendirilebilir)path.get(1)).getTanim(),"Malzemeler");
		assertEquals(((IKategorilendirilebilir)path.get(0)).getTanim(),"Demirbas Malzemeler");
    }
    
    public void testGetTreePath(){
    	Collection col=MalzemeTanimlariManager.findKategoriByMatchingTanim("Piriz", DAOFactory.MALZEME_TANIMI);
        ArrayList list=new ArrayList();
        Search.findPath(list,(IKategorilendirilebilir)col.toArray()[0]);
        Object[] path=Search.getTreePath(list).getPath();
		assertEquals(path.length,3);
		assertEquals(((IKategorilendirilebilir)path[0]).getTanim(),"Malzemeler");
		assertEquals(((IKategorilendirilebilir)path[1]).getTanim(),"Elektirik Malzemeleri");
		assertEquals(((IKategorilendirilebilir)path[2]).getTanim(),"Piriz");
    }
    
    public void testFindNext(){
    	Collection col=MalzemeTanimlariManager.findKategoriByMatchingTanim("Monitör", DAOFactory.MALZEME_TANIMI);
    	Object[] path=Search.findNext(col).getPath();
		assertEquals(path.length,4);
		assertEquals(((IKategorilendirilebilir)path[0]).getTanim(),"Malzemeler");
		assertEquals(((IKategorilendirilebilir)path[1]).getTanim(),"Demirbas Malzemeler");
		assertEquals(((IKategorilendirilebilir)path[2]).getTanim(),"Bilgisayar");
		assertEquals(((IKategorilendirilebilir)path[3]).getTanim(),"Monitör");
    }


}
