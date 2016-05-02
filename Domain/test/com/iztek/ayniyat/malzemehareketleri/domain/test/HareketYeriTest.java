package com.iztek.ayniyat.malzemehareketleri.domain.test;

import com.iztek.ayniyat.malzemehareketleri.domain.HareketYeri;
import com.iztek.ayniyat.test.MainTestCase;

/**
 * @author Umit Akyol
 *
 */
public class HareketYeriTest extends MainTestCase {
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(HareketYeriTest.class);
	}
    public void testCreateHareketYeri(){
        HareketYeri hareketYeri = new HareketYeri(rektorlukAmbar);
        assertEquals(hareketYeri.getHareketYeri(),rektorlukAmbar);
        hareketYeri.setHareketYeri(muhendislik);
        assertEquals(hareketYeri.getHareketYeri(),muhendislik);
    }
}
