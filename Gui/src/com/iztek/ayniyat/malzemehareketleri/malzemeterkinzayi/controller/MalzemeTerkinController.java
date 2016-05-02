package com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.controller;

import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeTerkinFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeTerkinHareketi;
import com.iztek.ayniyat.malzemehareketleri.fis.HareketFisiParemeters;
import com.iztek.ayniyat.malzemehareketleri.fis.PrintHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.controller.MalzemeTerkinZayiController;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketiManager;

/**
 * @author Cagdas CIRIT
 **/
public class MalzemeTerkinController extends MalzemeTerkinZayiController {
    
   /**
    * @modified by fusun
    */
    public void malzemelerinHareketiniGerceklestir(){
        MalzemeTerkinHareketi malzemeTerkinHareketi = new MalzemeTerkinHareketi();
        MalzemeTerkinFisi malzemeTerkinFisi = (MalzemeTerkinFisi)malzemeTerkinHareketi.getHareketFisi();
        malzemeTerkinFisi.setHareketBilgileri(malzemeTerkinZayi.getBilgiler());
        malzemeTerkinFisi.setDuzenlemeTarihi(malzemeTerkinZayi.getDuzenlemeTarihi());
        malzemeTerkinHareketi.setHareketTarihi(malzemeTerkinZayi.getDuzenlemeTarihi());
        malzemeTerkinFisi.setOnayVeren(KullaniciManager.getpersonel(AnaController.getInstance().getKullanici()));

        MalzemeHareketiManager.createMalzemeHareketi(malzemeTerkinHareketi,malzemeTerkinZayi.getCikisiYapilacakMalzemeler());
        malzemeTerkinZayi.getBelgeNoTextField().setText(malzemeTerkinFisi.getBelgeNo().getBelgeNo().toString());
    
	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Malzemelerin terkin hareketi "+malzemeTerkinFisi.getBelgeNo().getBelgeNo()+" nolu belge ile kaydedildi.\n" +
   		"Çýkýþ fiþini görüntülemek ister misiniz?");
	    
	    if(onay)
	        fisiGoruntule(malzemeTerkinHareketi);
    }
    
    public void fisiGoruntule(AbstractMalzemeHareketi hareket){
         MalzemeTerkinFisi terkinFisi = (MalzemeTerkinFisi) hareket.getHareketFisi();
         try{
             PrintHareketFisi.prepareReport(terkinFisi,"HareketFisi",new HareketFisiParemeters());
         }catch(Exception e){
         	e.printStackTrace();
         }
    }
}
