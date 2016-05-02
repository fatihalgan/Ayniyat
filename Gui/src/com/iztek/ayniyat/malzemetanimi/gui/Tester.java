package com.iztek.ayniyat.malzemetanimi.gui;

import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;

public class Tester {

    public static void setUp(){
      	IKategorilendirilebilir malzemeler = new Kategori("Malzemeler","01");        	
       	IKategorilendirilebilir demirbasMalzemeler = new Kategori("Demirbas Malzemeler","01");
       	IKategorilendirilebilir elektirikMalzemeler = new Kategori("Elektirik Malzemeleri","02");
       	IKategorilendirilebilir kýrtasiyeMalzemeler = new Kategori("Kirtasiye ","03");        	
       	IKategorilendirilebilir bilgisayar = new Kategori("Bilgisayar","01");
       	IKategorilendirilebilir mobilya = new Kategori("Büro Malzemeleri","02");
       	IKategorilendirilebilir bahce = new Kategori("Bag-Bahce Demirbasi","03");          	
    	IMalzemeTanimi monitor = new DemirbasMalzemeTanimi("Monitör","01",AbstractMalzemeTanimi.ADET);
    	IMalzemeTanimi yazici = new DemirbasMalzemeTanimi("Yazici","02",AbstractMalzemeTanimi.ADET);
    	IMalzemeTanimi kasa = new DemirbasMalzemeTanimi("Kasa","03",AbstractMalzemeTanimi.ADET);
    	IMalzemeTanimi masa = new DemirbasMalzemeTanimi("Masa","04",AbstractMalzemeTanimi.ADET); 
    	IMalzemeTanimi dolap = new DemirbasMalzemeTanimi("Dolap","05",AbstractMalzemeTanimi.ADET); 
    	IMalzemeTanimi koltuk = new DemirbasMalzemeTanimi("Koltuk","06",AbstractMalzemeTanimi.ADET); 
    	IMalzemeTanimi makas = new DemirbasMalzemeTanimi("Makas","07",AbstractMalzemeTanimi.ADET); 
    	IMalzemeTanimi kazma = new DemirbasMalzemeTanimi("Kazma","08",AbstractMalzemeTanimi.ADET); 
    	IMalzemeTanimi kurek =  new DemirbasMalzemeTanimi("Kürek","09",AbstractMalzemeTanimi.ADET); 
    	IMalzemeTanimi ampul = new DemirbasMalzemeTanimi("Ampul","10",AbstractMalzemeTanimi.ADET);
    	IMalzemeTanimi piriz = new DemirbasMalzemeTanimi("Piriz","11",AbstractMalzemeTanimi.ADET);
    	IMalzemeTanimi kablo = new DemirbasMalzemeTanimi("Kablo","12",AbstractMalzemeTanimi.ADET);
    	IMalzemeTanimi kagit = new DemirbasMalzemeTanimi("Kagit","13",AbstractMalzemeTanimi.ADET);
    	IMalzemeTanimi kalem = new DemirbasMalzemeTanimi("Kalem","14",AbstractMalzemeTanimi.ADET);
    	IMalzemeTanimi zimba = new DemirbasMalzemeTanimi("Zimba","15",AbstractMalzemeTanimi.ADET);
    	IMalzemeTanimi dosya = new DemirbasMalzemeTanimi("Dosya","16",AbstractMalzemeTanimi.ADET);

    	monitor.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	monitor.addNitelikTanimi(new NitelikTanimi("Marka"));
    	yazici.addNitelikTanimi(new NitelikTanimi("Model"));
    	yazici.addNitelikTanimi(new NitelikTanimi("Marka"));
    	kasa.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	kasa.addNitelikTanimi(new NitelikTanimi("Marka"));
    	masa.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	dolap.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	koltuk.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	makas.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	kazma.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	kurek.addNitelikTanimi(new NitelikTanimi("Boyut"));
    	ampul.addNitelikTanimi(new NitelikTanimi("Marka"));
    	piriz.addNitelikTanimi(new NitelikTanimi("Marka"));
    	kablo.addNitelikTanimi(new NitelikTanimi("Marka"));
    	kagit.addNitelikTanimi(new NitelikTanimi("Marka"));
    	kalem.addNitelikTanimi(new NitelikTanimi("Marka"));
    	zimba.addNitelikTanimi(new NitelikTanimi("Marka"));
    	dosya.addNitelikTanimi(new NitelikTanimi("Marka"));
    	

    	demirbasMalzemeler.addKategori(bilgisayar);
    	demirbasMalzemeler.addKategori(mobilya);
    	demirbasMalzemeler.addKategori(bahce);
    	bilgisayar.addKategori(monitor);
    	bilgisayar.addKategori(kasa);
    	bilgisayar.addKategori(yazici);
    	mobilya.addKategori(masa);
    	mobilya.addKategori(dolap);
    	mobilya.addKategori(koltuk);
    	bahce.addKategori(makas);
    	bahce.addKategori(kazma);
    	bahce.addKategori(kurek);
    	elektirikMalzemeler.addKategori(ampul);
    	elektirikMalzemeler.addKategori(piriz);
    	elektirikMalzemeler.addKategori(kablo);
    	kýrtasiyeMalzemeler.addKategori(kagit);
    	kýrtasiyeMalzemeler.addKategori(kalem);
    	kýrtasiyeMalzemeler.addKategori(zimba);
    	kýrtasiyeMalzemeler.addKategori(dosya);
    	

    	malzemeler.addKategori(demirbasMalzemeler);
    	malzemeler.addKategori(elektirikMalzemeler);
    	malzemeler.addKategori(kýrtasiyeMalzemeler);

    	MalzemeTanimlariManager.createKategori(null, malzemeler);
    }
}
