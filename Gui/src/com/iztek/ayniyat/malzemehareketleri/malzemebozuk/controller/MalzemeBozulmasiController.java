package com.iztek.ayniyat.malzemehareketleri.malzemebozuk.controller;

import java.util.Vector;

import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeBozukFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeBozukHareketi;
import com.iztek.ayniyat.malzemehareketleri.fis.HareketFisiParemeters;
import com.iztek.ayniyat.malzemehareketleri.fis.PrintHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.malzemebozuk.gui.MalzemeBozulmasi;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketiManager;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;

/**
 * @author Cagdas CIRIT
 **/
public class MalzemeBozulmasiController {
    private MalzemeBozulmasi malzemeBozulmasi;
    
	public void findAmbarSorumlulari(){
		//Simdilik rektorluk ambari gorevlilerini donduruyor!
	    malzemeBozulmasi.setAmbarSorumlulari((Vector) YerlesimManager.getPersonelInAmbar(AnaController.getInstance().getIslemYapanAmbar().getTanim()));
	}

    public void registerMalzemeBozulmasi(MalzemeBozulmasi malzemeBozulmasi) {
        this.malzemeBozulmasi = malzemeBozulmasi;
    }
    
    public void demirbasNoyaGoreSonucDondur(String demirbasNo){
        Vector demirbas = new Vector(1);
        DemirbasMalzeme demirbasMalzeme = (DemirbasMalzeme) MalzemeManager.findDemirbasByDemirbasNo(demirbasNo);
        if(demirbasMalzeme!=null){
            demirbas.add(demirbasMalzeme);
            secilenDemirbaslariBozukTablosunaGonder(demirbas);
        }
    }
    
    public void secilenDemirbaslariBozukTablosunaGonder(Vector demirbaslar){
        malzemeBozulmasi.tablonuDoldur(demirbaslar);
    }
    
    public void malzemelerinBozukHareketiniGerceklestir(){
        MalzemeBozukHareketi malzemeBozukHareketi = new MalzemeBozukHareketi();
        MalzemeBozukFisi malzemeBozukFisi = (MalzemeBozukFisi) malzemeBozukHareketi.getHareketFisi();
        malzemeBozukFisi.setDuzenlemeTarihi(malzemeBozulmasi.getDuzenlemeTarihi());
        malzemeBozukHareketi.setHareketTarihi(malzemeBozulmasi.getDuzenlemeTarihi());
        malzemeBozukFisi.setOnayVeren(KullaniciManager.getpersonel(AnaController.getInstance().getKullanici()));
        malzemeBozukFisi.setBozulusTarihi(malzemeBozulmasi.getBozulusTarihi());
        malzemeBozukFisi.setBozulmaNedeni(malzemeBozulmasi.getBozulmaNedeni());
        
        MalzemeHareketiManager.createMalzemeHareketi(malzemeBozukHareketi,malzemeBozulmasi.getCikisiYapilacakMalzemeler());
        malzemeBozulmasi.getBozulusBilgileriLeftPanel().getBelgeNoTextField().setText(malzemeBozukFisi.getBelgeNo().getBelgeNo().toString());
        
	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Malzemelerin bozuk hareketi "+malzemeBozukFisi.getBelgeNo().getBelgeNo()+" nolu belge ile kaydedildi.\n" +
   		"Çýkýþ fiþini görüntülemek ister misiniz?");
	    
	    if(onay)
	        fisiGoruntule(malzemeBozukHareketi);
    }
    
    private void fisiGoruntule(MalzemeBozukHareketi hareket){
        MalzemeBozukFisi bozukFisi = (MalzemeBozukFisi) hareket.getHareketFisi();
        try{
            PrintHareketFisi.prepareReport(bozukFisi,"HareketFisi",new HareketFisiParemeters());
        }catch(Exception e){
        	e.printStackTrace();
        }
}
}
