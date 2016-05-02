package com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.controller;

import java.util.Vector;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.gui.MalzemeTerkinZayi;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;

public abstract class MalzemeTerkinZayiController {

	public MalzemeTerkinZayi malzemeTerkinZayi;
    
	public void findAmbarSorumlulari(){
		//Simdilik rektorluk ambari gorevlilerini donduruyor!
		malzemeTerkinZayi.setAmbarSorumlulari((Vector) YerlesimManager.getPersonelInAmbar(AnaController.getInstance().getIslemYapanAmbar().getTanim()));
	}
		
    public void registerMalzemeTerkinZayi(MalzemeTerkinZayi malzemeTerkinZayi) {
        this.malzemeTerkinZayi = malzemeTerkinZayi;
    }
    
    public void demirbasNoyaGoreSonucDondur(String demirbasNo){
        Vector demirbas = new Vector(1);
        DemirbasMalzeme demirbasMalzeme = (DemirbasMalzeme) MalzemeManager.findDemirbasByDemirbasNo(demirbasNo);
        if(demirbasMalzeme!=null){
            demirbas.add(demirbasMalzeme);
            secilenDemirbaslariTabloyaGonder(demirbas);
        }
    }
    
    public void secilenDemirbaslariTabloyaGonder(Vector demirbaslar){
    	malzemeTerkinZayi.tablonuDoldur(demirbaslar);
    }
    
   
    public abstract void malzemelerinHareketiniGerceklestir();
    
    public abstract void fisiGoruntule(AbstractMalzemeHareketi hareket);
}

