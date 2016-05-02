package com.iztek.ayniyat.yerlesim.gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.iztek.ayniyat.data.DataCollector;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.ayniyat.yerlesim.domain.*;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;

public class Tester {
	
    public static void setUp(){
        
    	AbstractZimmetAlan  cagdas = new Personel("65544","Cagdas","Cirit");
    	AbstractZimmetAlan umit = new Personel("211212","Umit","Akyol");
    	AbstractZimmetAlan sevgi0 = new Personel("666854","Sevgi","Uslu");
    	AbstractZimmetAlan sevgi1 = new Personel("666854","Sevgi","Uslu");
        
    	IMalzemeTanimi masaTanim = new DemirbasMalzemeTanimi("Masa","01",DemirbasMalzemeTanimi.ADET);
    	IMalzemeTanimi masa2Tanim = new DemirbasMalzemeTanimi("Masa2","02",DemirbasMalzemeTanimi.ADET);
    	IMalzemeTanimi koltukTanim = new DemirbasMalzemeTanimi("Koltuk","03",DemirbasMalzemeTanimi.ADET);
    	IMalzemeTanimi dolapTanim = new DemirbasMalzemeTanimi("Dolap","04",DemirbasMalzemeTanimi.ADET);
    	IMalzemeTanimi sandalyeTanim = new DemirbasMalzemeTanimi("Sandalye","05",DemirbasMalzemeTanimi.ADET);    	
    	
    	IDemirbasMalzeme masa2 = new DemirbasMalzeme();
    	masa2.setMalzemeTanimi(masa2Tanim);
    	IDemirbasMalzeme masa = new DemirbasMalzeme();
    	masa.setMalzemeTanimi(masaTanim);
    	IDemirbasMalzeme koltuk = new DemirbasMalzeme();
    	koltuk.setMalzemeTanimi(koltukTanim);
    	IDemirbasMalzeme dolap = new DemirbasMalzeme();
    	dolap.setMalzemeTanimi(dolapTanim);
    	IDemirbasMalzeme sandalye = new DemirbasMalzeme();
    	sandalye.setMalzemeTanimi(sandalyeTanim);
        
        AbstractZimmetAlan cagdasOda = new Oda("205",Oda.PERSONEL,new Integer(2));
        AbstractZimmetAlan umitOda = new Oda("301",Oda.PERSONEL,new Integer(3));
        AbstractZimmetAlan sevgiOda0 = new Oda("106",Oda.PERSONEL,new Integer(1));
        AbstractZimmetAlan sevgiOda1 = new Oda("406",Oda.PERSONEL,new Integer(4));
        AbstractZimmetAlan lab = new Oda("L-109",Oda.LAB,new Integer(1));
        
        AbstractZimmetAlan buam = new Birim("Bilgisayar Uygulama ve Arastirma Merkezi");
        AbstractZimmetAlan muhDekanlýk = new Bina("Mühendislik Fakültesi Dekanlik");
        
        AbstractZimmetAlan muhendislik = new Birim("Muhendislik Fakültesi");
        AbstractZimmetAlan rektorluk = new Birim("Rektörlük");
        AbstractZimmetAlan fen = new Birim("Fen Fakültesi");
        
        AbstractZimmetAlan rektorlukAmbar = new Ambar("Rektörlük Ambari");
        AbstractZimmetAlan kampus = new Birim("IYTE Kampus");
        /*--------------------------------------------------------------*/
        Kategori bilgisayarTanimi =  new Kategori("Bilgisayar","01");
        AbstractZimmetAlan bBlok = new Bina("27/4");
        try {
            DataCollector.getOdalar((Birim) buam,(Bina)bBlok); 
            DataCollector.getPersoneller((Bina) bBlok,(Birim) buam);
            DataCollector.getMalzemeTanimlari(bilgisayarTanimi);
            DataCollector.getDemirbaslar(bilgisayarTanimi,(Bina) bBlok,(Birim) buam);
            muhendislik.addKategori(bBlok);
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /*--------------------------------------------------------------*/
        cagdas.addDemirbasMalzeme(masa);
        cagdasOda.addKategori(cagdas);
        muhDekanlýk.addKategori(cagdasOda);
        umit.addDemirbasMalzeme(koltuk);
        umitOda.addDemirbasMalzeme(masa2);
        umitOda.addKategori(umit);
        muhDekanlýk.addKategori(umitOda);      
        sevgi0.addDemirbasMalzeme(dolap);
        sevgiOda0.addKategori(sevgi0);
        //buam.addKategori(sevgiOda0);
        sevgi1.addDemirbasMalzeme(sandalye);
        sevgiOda1.addKategori(sevgi1);
        muhDekanlýk.addKategori(sevgiOda1);
        muhendislik.addKategori(muhDekanlýk);
        muhendislik.addKategori(buam);
        rektorluk.addKategori(rektorlukAmbar);
        fen.addKategori(lab);
        kampus.addKategori(muhendislik);
        kampus.addKategori(rektorluk);
        kampus.addKategori(fen);
        MalzemeTanimlariManager.createKategori(null,bilgisayarTanimi);
        YerlesimManager.createKategori(null, kampus); 
    }
}
