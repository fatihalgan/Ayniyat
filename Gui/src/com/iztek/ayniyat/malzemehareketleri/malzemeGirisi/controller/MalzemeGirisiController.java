package com.iztek.ayniyat.malzemehareketleri.malzemeGirisi.controller;

import java.util.Iterator;
import java.util.Vector;

import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.HareketYeri;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisHareketi;
import com.iztek.ayniyat.malzemehareketleri.fis.PrintHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.fis.TesellumFisiParameters;
import com.iztek.ayniyat.malzemehareketleri.malzemeGirisi.gui.MalzemeGirisBilgileriGirisi;
import com.iztek.ayniyat.malzemehareketleri.malzemeGirisi.gui.MalzemeGirisi;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketiManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;

/**
 * @author Sevgi USLU
 **/
public class MalzemeGirisiController{
	private MalzemeGirisi malzemeGirisi;
	
	public void registerMalzemeGirisi(MalzemeGirisi malzemeGirisi) {
		this.malzemeGirisi = malzemeGirisi;
	}
	
	public void findAmbarSorumlulari(){
	    malzemeGirisi.setAmbarSorumlulari((Vector)YerlesimManager.getPersonelInAmbar(AnaController.getInstance().getIslemYapanAmbar().toString()));
	}
	
    public void sendSeciliNodeToMalzemeGirisi(AbstractMalzemeTanimi malzeme){
        new MalzemeGirisBilgileriGirisi(AnaController.getInstance().getAnaPanel()
                ,"girisBilgileri",malzeme).setVisible(true);    
    } 
    
    public void sendDemirbaslarToMalzemeGirisi(Vector demirbaslar){
        for (int i = 0; i < demirbaslar.size(); i++) {
           DemirbasMalzeme demirbas =(DemirbasMalzeme) demirbaslar.get(i);
           if(!isDemirbasInTable(demirbas)){
	            Vector row=new Vector();
	            row.add(demirbas);
	            row.add(demirbas.getMalzemeTanimi());
	            row.add(demirbas.getMalzemeTanimi().getBirim());
	            row.add(demirbas.getPaha().getVergisizBirimFiyatMiktar());
	            row.add(Float.toString(demirbas.getPaha().getAsilOran()*100));
	            tabloyaEkle(row);
          }
        }
    }
    
    public void tabloyaEkle(Vector row){
       ((AyniyatTableModel) malzemeGirisi.getMalzemelerTable().getModel()).addRow2LastRow(row);
    }
    
	private boolean isDemirbasInTable(DemirbasMalzeme demirbas){
	    for (int i = 0; i < malzemeGirisi.getMalzemelerTable().getModel().getRowCount(); i++) {
	        DemirbasMalzeme lineItem=(DemirbasMalzeme) 
	        ((AyniyatTableModel)malzemeGirisi.getMalzemelerTable().getModel()).getValueAt(i,0);
            if(demirbas.getDemirbasNo().equals(lineItem.getDemirbasNo()))
                return true;
        }
	    	return false;
	}
    
    public boolean malzemeGirisHareketiOlustur(){
	    MalzemeGirisHareketi girisHareketi = new MalzemeGirisHareketi();    
	    
		girisHareketi.setHareketHedefi(new HareketYeri(AnaController.getInstance().getIslemYapanAmbar()));
		girisHareketi.setHareketTarihi(malzemeGirisi.getGirisBilgileriPanel().getHareketTarihi().getDate());
		if(malzemeGirisi.getGirisBilgileriPanel().getHareketSekliTextField().getText().equals(MalzemeGirisFisi.DEVIR)){
		 //   girisHareketi.setHareketKaynagi(new HareketYeri(YerlesimManager.findAmbarByTanim(malzemeGirisi.getGirisBilgileriPanel().getAlindigiKurulusTextField().getText())));
			   girisHareketi.setHareketKaynagi(new HareketYeri(YerlesimManager.findAmbarByTanim(malzemeGirisi.getGirisBilgileriPanel().getAlindigiKurulusComboBox().getSelectedItem().toString())));
		}
	    setGirisFisiBilgileri(girisHareketi);     
	    
	    if(((MalzemeGirisFisi)girisHareketi.getHareketFisi()).getGirisSekli().equals(MalzemeGirisFisi.TAMIREDILDI) ||
	            ((MalzemeGirisFisi)girisHareketi.getHareketFisi()).getGirisSekli().equals(MalzemeGirisFisi.DEVIR))
	        MalzemeHareketiManager.createMalzemeHareketi(girisHareketi,malzemeGirisi.getGirisiYapilacakMalzemeler());
	    else{
		    Iterator iterator = malzemeGirisi.getGirisiYapilacakMalzemeler().iterator();
		    while(iterator.hasNext()){
		        IMalzeme malzeme = (IMalzeme) iterator.next();
		        girisHareketi.addMalzeme(malzeme);
		    }
	        MalzemeHareketiManager.createMalzemeHareketi(girisHareketi);
	    }
	    
	    malzemeGirisi.getGirisBilgileriPanel().getBelgeNoTextField().setText(""+girisHareketi.getHareketFisi().getBelgeNo().getBelgeNo());
	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(
	            malzemeGirisi,"Malzemelerin girisi "+girisHareketi.getHareketFisi().getBelgeNo().getBelgeNo()+
	            " nolu belge ile kaydedildi.\n Giriþ fiþini görüntülemek ister misiniz?");
	    if(onay){
	        showMalzemeGirisFisi(girisHareketi);
	    }
	    return true;
	}
    
    public void malzemeGirisiniBaskaAmbardanGelenMalzemeleriKabulIcinDoldur(AbstractMalzemeHareketi hareket,Vector malzemeler){
        malzemeGirisi.loadYourselfForKarsiAmbardanMalzemeGirisi(hareket.getHareketKaynagi().getHareketYeri().getTanim(),hareket.getGeciciSahip().getHareketYeri().getTanim());
        sendDemirbaslarToMalzemeGirisi(malzemeler);
    }
    
	private void setGirisFisiBilgileri(MalzemeGirisHareketi girisHareketi){
	    MalzemeGirisFisi girisFisi=(MalzemeGirisFisi) girisHareketi.getHareketFisi();

	    girisFisi.setGirisSekli(malzemeGirisi.getGirisBilgileriPanel().getHareketSekliTextField().getText());
	    girisFisi.setOnayVeren(KullaniciManager.getpersonel(AnaController.getInstance().getKullanici()));
	//    girisFisi.setAlindigiKurulus(malzemeGirisi.getGirisBilgileriPanel().getAlindigiKurulusTextField().getText());
	    girisFisi.setAlindigiKurulus(malzemeGirisi.getGirisBilgileriPanel().getAlindigiKurulusComboBox().getSelectedItem().toString());
	    girisFisi.setTeslimEden(malzemeGirisi.getGirisBilgileriPanel().getTeslimEdenTextField().getText());
	    girisFisi.setDuzenlemeTarihi(malzemeGirisi.getGirisBilgileriPanel().getHareketTarihi().getDate());
	    girisFisi.setOwningMalzemeHareketi(girisHareketi);
	    if(girisFisi.getGirisSekli().equals(MalzemeGirisFisi.SATINALMA)){
	        girisFisi.setFaturaNo(malzemeGirisi.getSatinalmaBilgileriPanel().getFaturaNoTextField().getText());
	        girisFisi.setFaturaTarihi(malzemeGirisi.getSatinalmaBilgileriPanel().getFaturaTarihiTextField().getDate());
	    }
	    girisHareketi.setHareketFisi(girisFisi);
	}
	
	private void showMalzemeGirisFisi(MalzemeGirisHareketi girisHareketi){
	   MalzemeGirisFisi girisFisi = (MalzemeGirisFisi) girisHareketi.getHareketFisi();
		    // TODO  yetkililer doldurulacak
	   try{
        PrintHareketFisi.prepareReport(girisFisi,"ayniyat_tesellum",new TesellumFisiParameters());
    }catch(Exception e){
    	e.printStackTrace();
    }
		
	}
}
