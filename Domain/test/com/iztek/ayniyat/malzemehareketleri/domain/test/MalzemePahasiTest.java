package com.iztek.ayniyat.malzemehareketleri.domain.test;

import java.math.BigDecimal;
import junit.swingui.TestRunner;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemePahasi;
import com.iztek.ayniyat.test.MainTestCase;
import com.iztek.commons.money.Money;

/**
 * @author Umit Akyol + Cagdas CIRIT 
 *
 */
public class MalzemePahasiTest extends MainTestCase{

    public static void main(String[] args) {
        TestRunner.run(MalzemePahasiTest.class);
    }
     
    public void testCreateMalzemePahasi(){
        MalzemePahasi malzemePahasi = new MalzemePahasi();
        assertEquals(malzemePahasi.getIskontoTutari(),Money.yeniTurkLirasi(0));
        assertEquals(malzemePahasi.getKdvOrani(),new Float(0));
        assertEquals(malzemePahasi.getOtvOrani(),new Float(0));
        assertNull(malzemePahasi.getVergiliBirimFiyat());
        assertNull(malzemePahasi.getVergisizBirimFiyat());
    }
    
    public void testGetVergisizBirimFiyatMiktar(){
		assertEquals(masa0.getPaha().getVergisizBirimFiyatMiktar().compareTo(new BigDecimal(150000000).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN)),0);
		assertEquals(koltuk.getPaha().getVergisizBirimFiyatMiktar().compareTo(new BigDecimal(94.93).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN)),0);
	}
	
	public void testGetVergisizBirimFiyatKur(){
		assertEquals(masa0.getPaha().getVergisizBirimFiyatKur(),Money.TL);
		assertEquals(koltuk.getPaha().getVergisizBirimFiyatKur(),Money.YTL);
	}
	
	public void testGetVergiliBirimFiyatMiktar(){
		assertEquals(masa0.getPaha().getVergiliBirimFiyat().getAmount().compareTo(new BigDecimal(177000010.01).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN)),0);
		assertEquals(koltuk.getPaha().getVergiliBirimFiyat().getAmount().compareTo(new BigDecimal(108.693).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN)),0);
	}
	
	public void testGetVergiliBirimFiyatKur(){
	    assertEquals(masa0.getPaha().getVergiliBirimFiyatKur(),Money.TL);
		assertEquals(koltuk.getPaha().getVergiliBirimFiyatKur(),Money.YTL);
	}
	
	public void testGetIskontoTutarýMiktar(){
		assertEquals(masa0.getPaha().getIskontoTutariMiktar().compareTo(new BigDecimal(12.5).setScale(Money.SCALE,BigDecimal.ROUND_HALF_EVEN)),0);
	}
	
	public void testGetIskontoTutarýKur(){
	    assertEquals(masa0.getPaha().getIskontoTutariKur(),Money.YTL);
	}
	
	public void testGetIskontoTutari() {
		assertEquals(masa0.getPaha().getIskontoTutari(),Money.yeniTurkLirasi(12.5));
		assertEquals(koltuk.getPaha().getIskontoTutari(),Money.yeniTurkLirasi(0));
	}	
	
	public void testGetKdvOrani() {
	    assertEquals(masa0.getPaha().getKdvOrani(),new Float(18));
		assertEquals(koltuk.getPaha().getKdvOrani(),new Float(0));
	}
	
	public void testGetOtvOrani() {
	    assertEquals(masa0.getPaha().getOtvOrani(),new Float(0));
	    assertEquals(koltuk.getPaha().getOtvOrani(),new Float(14.5));
	}
	
	public void testGetVergiliBirimFiyat() {
		assertEquals(masa0.getPaha().getVergiliBirimFiyat(),Money.turkLirasi(177000010.01));
		assertEquals(koltuk.getPaha().getVergiliBirimFiyat(),Money.yeniTurkLirasi(108.693));
	}

	public void testGetVergisizBirimFiyat() {
	    assertEquals(masa0.getPaha().getVergisizBirimFiyat(),Money.turkLirasi(150000000));
		assertEquals(koltuk.getPaha().getVergisizBirimFiyat(),Money.yeniTurkLirasi(94.93));
	}

}
