package com.iztek.ayniyat.kategori.service.test;

import com.iztek.ayniyat.kategori.service.KategoriMergeStateManager;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class KategoriMergeStateManagerTest extends MainTestCase{
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(KategoriMergeStateManagerTest.class);
	}
    public void testPutState(){
        KategoriMergeStateManager stateManager = new KategoriMergeStateManager();
        stateManager.putState(bilgisayar,bilgisayar.getMemento());
        assertNotNull(stateManager.getState(bilgisayar));
    }
    public void testUndo(){
        KategoriMergeStateManager stateManager = new KategoriMergeStateManager();
        stateManager.putState(bilgisayar,bilgisayar.getMemento());
        bilgisayar.removeKategori(monitorTanimi);
        stateManager.undo(bilgisayar);
        assertNotNull(bilgisayar.getChildByNodeValue("05-Monitör"));
    }
}
