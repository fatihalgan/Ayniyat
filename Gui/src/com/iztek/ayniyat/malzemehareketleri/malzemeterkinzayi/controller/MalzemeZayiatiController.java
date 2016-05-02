package com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.controller;

import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeZayiFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeZayiHareketi;
import com.iztek.ayniyat.malzemehareketleri.fis.HareketFisiParemeters;
import com.iztek.ayniyat.malzemehareketleri.fis.PrintHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.controller.MalzemeTerkinZayiController;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketiManager;

/**
 * @author Cagdas CIRIT
 **/
public class MalzemeZayiatiController extends MalzemeTerkinZayiController {
    
  
    
    public void malzemelerinHareketiniGerceklestir(){
        MalzemeZayiHareketi malzemeZayiHareketi = new MalzemeZayiHareketi();
        MalzemeZayiFisi malzemeZayiFisi = (MalzemeZayiFisi)malzemeZayiHareketi.getHareketFisi();
        malzemeZayiFisi.setHareketBilgileri(super.malzemeTerkinZayi.getBilgiler());
        malzemeZayiFisi.setDuzenlemeTarihi(super.malzemeTerkinZayi.getDuzenlemeTarihi());
        malzemeZayiHareketi.setHareketTarihi(super.malzemeTerkinZayi.getDuzenlemeTarihi());
        malzemeZayiFisi.setOnayVeren(KullaniciManager.getpersonel(AnaController.getInstance().getKullanici()));

        MalzemeHareketiManager.createMalzemeHareketi(malzemeZayiHareketi,super.malzemeTerkinZayi.getCikisiYapilacakMalzemeler());
        super.malzemeTerkinZayi.getBelgeNoTextField().setText(malzemeZayiFisi.getBelgeNo().getBelgeNo().toString());
    
	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Malzemelerin zayi hareketi "+malzemeZayiFisi.getBelgeNo().getBelgeNo()+" nolu belge ile kaydedildi.\n" +
   		"Çýkýþ fiþini görüntülemek ister misiniz?");
	    
	    if(onay)
	        fisiGoruntule(malzemeZayiHareketi);
    }
    
   public void fisiGoruntule(AbstractMalzemeHareketi hareket){
        MalzemeZayiFisi zayiFisi = (MalzemeZayiFisi) hareket.getHareketFisi();
        try{
            PrintHareketFisi.prepareReport(zayiFisi,"HareketFisi",new HareketFisiParemeters());
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}
