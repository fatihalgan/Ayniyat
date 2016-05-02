package com.iztek.ayniyat.malzemehareketleri.domain.test;

import java.math.BigDecimal;

import junit.swingui.TestRunner;

import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeFisPahasi;
import com.iztek.ayniyat.test.MainTestCase;
import com.iztek.commons.money.Money;

/**
 * @author Umit Akyol + Cagdas CIRIT
 *
 */
public class MalzemeGirisFisiPahasiTest extends MainTestCase {

    public static void main(String[] args) {
        TestRunner.run(MalzemeGirisFisiPahasiTest.class);
    }
    
    public void testCreateMalzemeGirisFisiPahasiTest(){
        MalzemeFisPahasi giriFisiPahasi = new MalzemeFisPahasi();
        assertEquals(giriFisiPahasi.getIskontoTutari(),Money.yeniTurkLirasi(0));
        assertNull(giriFisiPahasi.getKdvTutari());
        assertNull(giriFisiPahasi.getOtvTutari());
        assertNull(giriFisiPahasi.getToplamTutar());
    }
    
    public void testGetIskontoTutari() {
		assertEquals(malzemeGirisFisi.getPaha().getIskontoTutari(),Money.yeniTurkLirasi(36.72));
	}

	public void testGetKdvTutari() {
	    assertEquals(malzemeGirisFisi.getPaha().getKdvTutari(),Money.yeniTurkLirasi(105.425));
	}

	public void testGetOtvTutari() {
	    assertEquals(malzemeGirisFisi.getPaha().getOtvTutari(),Money.yeniTurkLirasi(43.5722));
	}

	public void testGetGenelToplamTutari() {
	    assertEquals(malzemeGirisFisi.getPaha().getGenelToplamTutari(),Money.yeniTurkLirasi(1249.29));
	}

	public void testGetToplamTutar() {
	    assertEquals(malzemeGirisFisi.getPaha().getToplamTutar(),Money.yeniTurkLirasi(1100.3));
	}
	
	public void testGetGenelToplamTutariMiktar() {
	    assertEquals(malzemeGirisFisi.getPaha().getGenelToplamTutariMiktar(),new BigDecimal(1249.29).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN));
	}
	
	public void testGetGenelToplamTutariKur() {
	    assertEquals(malzemeGirisFisi.getPaha().getGenelToplamTutariKur(),Money.YTL);
	}
	
	public void testGetToplamTutarMiktar() {
		assertEquals(malzemeGirisFisi.getPaha().getToplamTutarMiktar(),new BigDecimal(1100.3).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN));
	}
	
	public void testGetToplamTutarKur() {
	    assertEquals(malzemeGirisFisi.getPaha().getToplamTutarKur(),Money.YTL);
	}
	
	public void testGetKdvTutariMiktar() {
		assertEquals(malzemeGirisFisi.getPaha().getKdvTutariMiktar(),new BigDecimal(105.425).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN));
	}
	
	public void testGetKdvTutariKur() {
	    assertEquals(malzemeGirisFisi.getPaha().getKdvTutariKur(),Money.YTL);
	}
	
	public void testGetOtvTutariMiktar() {
		assertEquals(malzemeGirisFisi.getPaha().getOtvTutariMiktar(),new BigDecimal(43.5722).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN));
	}
	
	public void testGetOtvTutariKur() {
	    assertEquals(malzemeGirisFisi.getPaha().getOtvTutariKur(),Money.YTL);
	}
	
	public void testGetIskontoTutariMiktar() {
		assertEquals(malzemeGirisFisi.getPaha().getIskontoTutariMiktar(),new BigDecimal(0).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN));
	}
	
	public void testGetIskontoTutariKur() {
	    assertEquals(malzemeGirisFisi.getPaha().getIskontoTutariKur(),Money.YTL);
	}
}
