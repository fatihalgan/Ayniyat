package com.iztek.ayniyat.malzemehareketleri.domain.test;

import com.iztek.ayniyat.test.MainTestCaseWithGeneratedNumbers;

/**
 * @author Umit Akyol
 *
 */
public class AbstractMalzemeHareketFisiTest extends MainTestCaseWithGeneratedNumbers {
    
   public static void main(String[] args) {
		junit.swingui.TestRunner.run(AbstractMalzemeHareketFisiTest.class);
   }
    
   public void testGetBelgeNo(){
       assertEquals(malzemeGirisFisi.getBelgeNo().getBelgeNo().intValue(),1);
   }
   
   public void testGetDuzenlemeTarihi(){
       assertEquals(malzemeGirisFisi.getDuzenlemeTarihi(),tarih);
   }
   
   public void testGetOnayVeren(){
       assertEquals(malzemeGirisFisi.getOnayVeren(),rektorlukAmbarGorevlisi0);
   }
   
   public void testGetTeslimEden(){
       assertEquals(malzemeGirisFisi.getTeslimEden(),"Mehmet Altintas");
   }
}
