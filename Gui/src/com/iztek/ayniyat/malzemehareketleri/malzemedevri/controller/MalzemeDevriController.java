package com.iztek.ayniyat.malzemehareketleri.malzemedevri.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.HareketYeri;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeDevirFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeDevirHareketi;
import com.iztek.ayniyat.malzemehareketleri.fis.HareketFisiParemeters;
import com.iztek.ayniyat.malzemehareketleri.fis.PrintHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.malzemedevri.gui.DemirbasMalzemeDevretme;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketiManager;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;

/**
 * @author Umit Akyol
 **/
public class MalzemeDevriController {
	private DemirbasMalzemeDevretme malzemeDevirGui;
	
	public void registerDemirbasMalzemeDevri(DemirbasMalzemeDevretme devretme) {
	    malzemeDevirGui = devretme;
	}
	
	public DemirbasMalzemeDevretme getMalzemeDevriGui() {
	       return malzemeDevirGui;
	}
	
	public void secilenDemirbaslariTabloyaKoy(Vector demirbaslar){
	    malzemeDevirGui.tabloyuDoldur(demirbaslar);
	}

	public void findAmbarSorumlulari() {
	    //Simdilik rektorluk ambari gorevlilerini donduruyor!
	    malzemeDevirGui.setAmbarSorumlulari((Vector) YerlesimManager.getPersonelInAmbar(AnaController.getInstance().getIslemYapanAmbar().getTanim()));
    }

    public boolean getDemirbasByDemirbasNo(String demirbasNo) {
        Vector demirbas = new Vector(1);
        DemirbasMalzeme demirbasMalzeme = (DemirbasMalzeme) MalzemeManager.findDemirbasByDemirbasNo(demirbasNo);
        if(demirbasMalzeme!=null){
            if(!isMalzemeZimmetli(demirbasMalzeme))
                return false;
            demirbas.add(demirbasMalzeme);
            secilenDemirbaslariTabloyaKoy(demirbas);
            return true;
        }
        return false;
    }
    
    private boolean isMalzemeZimmetli(IDemirbasMalzeme malzeme){
        String state = malzeme.getState().getType();
        if(state.equals(IMalzemeState.ZIMMETLI))
            return true;
        else
            return false;
    }

    public boolean malzemelerinDevriniGerceklestir() {
        Vector malzemeler = malzemeDevirGui.getDevriYapilacakMalzemeler();
        MalzemeDevirHareketi devirHareketi = new MalzemeDevirHareketi();
        devirHareketi.setHareketHedefi(new HareketYeri(malzemeDevirGui.getDevirBilgileriPanel().getVerildigiYer()));
        devirHareketi.setHareketKaynagi(new HareketYeri(malzemeDevirGui.getDevirBilgileriPanel().getHareketKaynagi()));
        devirHareketi.setHareketTarihi(malzemeDevirGui.getIslemTarihi());
        setDevirFisiBilgileri(devirHareketi);
        
        MalzemeHareketiManager.createMalzemeHareketi(devirHareketi,malzemeDevirGui.getDevriYapilacakMalzemeler());
        malzemeDevirGui.getDevirBilgileriPanel().getBilgiPanel().getBelgeNoTextField().setText(""+devirHareketi.getHareketFisi().getBelgeNo().getBelgeNo());
  	  					
  	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Malzemelerin devri "+devirHareketi.getHareketFisi().getBelgeNo().getBelgeNo()+" nolu belge ile kaydedildi.\n" +
  	   		"Devir fiþini görüntülemek ister misiniz?");
  	  
  	    if(onay){
  	      showMalzemeCikisFisi(devirHareketi);
  	    }
        return true;
    }
  
    private void setDevirFisiBilgileri(MalzemeDevirHareketi devirHareketi){
	    MalzemeDevirFisi devirFisi = (MalzemeDevirFisi) devirHareketi.getHareketFisi();
	    Calendar rightNow = Calendar.getInstance();
		Date tarih = rightNow.getTime();
	    devirFisi.setDuzenlemeTarihi(tarih);
	    devirFisi.setOnayVeren(KullaniciManager.getpersonel(AnaController.getInstance().getKullanici()));
	}
    
    private void showMalzemeCikisFisi(MalzemeDevirHareketi devirHareketi) {
        MalzemeDevirFisi devirFisi = (MalzemeDevirFisi) devirHareketi.getHareketFisi();
        try{
            PrintHareketFisi.prepareReport(devirFisi,"HareketFisi",new HareketFisiParemeters());
        }catch(Exception e){
        	e.printStackTrace();
        }
    }

     

    
}
