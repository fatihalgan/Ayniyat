package com.iztek.ayniyat.malzemehareketleri.malzemecikisi.controller;



import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.HareketYeri;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeCikisFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeCikisHareketi;
import com.iztek.ayniyat.malzemehareketleri.fis.HareketFisiParemeters;
import com.iztek.ayniyat.malzemehareketleri.fis.PrintHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.malzemecikisi.gui.CikisMalzemeleriSecimi;
import com.iztek.ayniyat.malzemehareketleri.malzemecikisi.gui.MalzemeCikisi;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketiManager;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.model.treetable.TreeTableNode;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;

/**
 * @author Cagdas CIRIT + Umit Akyol
 **/
public class MalzemeCikisiController {
	private MalzemeCikisi malzemeCikisi;
	
	public void findAmbarSorumlulari(){
		malzemeCikisi.setAmbarSorumlulari((Vector) YerlesimManager.getPersonelInAmbar(AnaController.getInstance().getIslemYapanAmbar().getTanim()));
	}
	
	public void registerMalzemeCikisi(MalzemeCikisi malzemeCikisi) {
		this.malzemeCikisi = malzemeCikisi;
	}
	
	public boolean malzemelerinCikisiniGerceklestir(){
	    MalzemeCikisHareketi cikisHareketi = new MalzemeCikisHareketi();
	    cikisHareketi.setHareketHedefi(new HareketYeri(malzemeCikisi.getHareketBilgileriPanelForCikis().getVerildigiYer()));
	    IZimmetAlan geciciSahip = malzemeCikisi.getHareketBilgileriPanelForCikis().getTeslimAlan();
	    if(geciciSahip != null){
	        cikisHareketi.setGeciciSahip(new HareketYeri(geciciSahip));
	        setCikisFisiBilgileri(cikisHareketi,MalzemeCikisFisi.TRANSFER);
	    }
	    else{
	        setCikisFisiBilgileri(cikisHareketi,MalzemeCikisFisi.ZIMMET);
	    }
	    cikisHareketi.setHareketKaynagi(new HareketYeri(AnaController.getInstance().getIslemYapanAmbar()));
	    cikisHareketi.setHareketTarihi(malzemeCikisi.getHareketBilgileriPanelForCikis().getBilgiPanel().getHareketTarihi().getDate());
	    
	    
	    MalzemeHareketiManager.createMalzemeHareketi(cikisHareketi,malzemeCikisi.getCikisiYapilacakMalzemeler());
	    malzemeCikisi.getHareketBilgileriPanelForCikis().getBilgiPanel().getBelgeNoTextField().setText(""+cikisHareketi.getHareketFisi().getBelgeNo().getBelgeNo());
	  
	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Malzemelerin çýkýþý "+cikisHareketi.getHareketFisi().getBelgeNo().getBelgeNo()+" nolu belge ile kaydedildi.\n" +
	   		"Çýkýþ fiþini görüntülemek ister misiniz?");
	  
	    if(onay){
	      showMalzemeCikisFisi(cikisHareketi);
	    }
	    return true;
	}
	
	//NOT : henuz eksik veya silinebilir
	private void setCikisFisiBilgileri(MalzemeCikisHareketi cikisHareketi,String cikisSekli){
	    MalzemeCikisFisi cikisFisi = (MalzemeCikisFisi) cikisHareketi.getHareketFisi();
	    cikisFisi.setAlinisNedeni(" ");//TODO nereden alýnacak bu bilgi......
	    cikisFisi.setCikisSekli(cikisSekli);
	    Calendar rightNow = Calendar.getInstance();
		Date tarih = rightNow.getTime();
	    cikisFisi.setDuzenlemeTarihi(tarih);
	    cikisFisi.setOnayVeren(KullaniciManager.getpersonel(AnaController.getInstance().getKullanici()));
	}
	
	private void showMalzemeCikisFisi(MalzemeCikisHareketi cikisHareketi){
	    MalzemeCikisFisi cikisFisi = (MalzemeCikisFisi) cikisHareketi.getHareketFisi();
	    try{
            PrintHareketFisi.prepareReport(cikisFisi,"HareketFisi",new HareketFisiParemeters());
        }catch(Exception e){
        	e.printStackTrace();
        }
        }
	
	public void sendSeciliNodeToMalzemeCikisi(AbstractMalzemeTanimi malzemeTanimi){
	    CikisMalzemeleriSecimi cikisMalzemeleriSecimi = new CikisMalzemeleriSecimi(AnaController.getInstance().getAnaPanel());
	   
	    cikisMalzemeleriSecimi.loadYourself((DemirbasMalzemeTanimi) malzemeTanimi);
	    cikisMalzemeleriSecimi.setVisible(true);
	}
	
	public HashMap getNiteliklerForMalzemeSecimleriTable(AbstractMalzemeTanimi malzemeTanimi){
	    HashMap nitelikHashMap = new HashMap(malzemeTanimi.getNitelikTanimlari().size());
	    
	    Iterator iter = malzemeTanimi.getNitelikTanimlari().iterator();
	    while (iter.hasNext()) {
            NitelikTanimi nitelikTanimi = (NitelikTanimi) iter.next();
           
            nitelikHashMap.put(nitelikTanimi.getNitelikAdi(),MalzemeManager.getNitelikTaniminaAitFarkliDegerler(malzemeTanimi,nitelikTanimi.getNitelikAdi()));
        }
	    
	    return nitelikHashMap;
	}
	
	public Vector getNitelikSuzgecindenGecmisDemirbaslar(AbstractMalzemeTanimi malzemeTanimi,AyniyatTableModel tableModel){
	    HashMap nitelikler = new HashMap();
    
	    for (int i = 0; i < tableModel.getRowCount(); i++){ 
            String nitelikAdi = (String) tableModel.getValueAt(i,0);
            String nitelikDegeri = (String) tableModel.getValueAt(i,1);
            nitelikler.put(nitelikAdi,nitelikDegeri);
	    }
	    
	    if (AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.MALZEME_CIKISI) || AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.STOK_SORGULAMA))
	        return new Vector(MalzemeManager.getDemirbaslarNitelikDegeriBelirlenmis(malzemeTanimi,nitelikler,AnaController.getInstance().getIslemYapanAmbar()));
	    else
	        return new Vector(MalzemeManager.getDemirbaslarNitelikDegeriBelirlenmis(malzemeTanimi,nitelikler,null));//sadece zimmet alan üzerindekileri deðil tüm demirbaþlarý döndür
	}
	
	public void secilenDemirbaslariMalzemeCikisiTablosunaGonder(TreeTableNode root){
	    Vector demirbaslar = new Vector(root.getChildren().size());
	    Iterator iter = root.getChildren().iterator();
	    while (iter.hasNext()) {
			TreeTableNode element = (TreeTableNode) iter.next();
			if (element.isSelected())
				demirbaslar.add(element.getUserObject());
		}
	    
        if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.MALZEME_CIKISI))
            malzemeCikisi.secilenMalzemeleriTabloyaKoy(demirbaslar);
        else if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.MALZEME_ZAYI)) 
            AnaController.getInstance().getMalzemeZayiatiController().secilenDemirbaslariTabloyaGonder(demirbaslar);  
        else if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.MALZEME_TERKIN)) 
            AnaController.getInstance().getMalzemeTerkinController().secilenDemirbaslariTabloyaGonder(demirbaslar);  
        else if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.MALZEME_BOZUK)) 
            AnaController.getInstance().getMalzemeBozulmasiController().secilenDemirbaslariBozukTablosunaGonder(demirbaslar);
	}
}
