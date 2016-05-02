package com.iztek.ayniyat.test;

import java.util.Calendar;
import java.util.Date;
import com.iztek.ayniyat.event.KategoriEventListener;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kullanici.Kullanici;
import com.iztek.ayniyat.malzemehareketleri.domain.*;
import com.iztek.ayniyat.malzemetanimi.domain.*;
import com.iztek.ayniyat.yerlesim.domain.*;
import com.iztek.commons.money.Money;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class MainTestCase extends BaseTestCase{
    //////////////////////////////////////////////
    /////////////KULLANICI
    //////////////////////////////////////////////
    protected Kullanici kullanici; 
    protected Kullanici kullanici1; 
    protected Kullanici kullanici2; 
    
    
    ///////////////////////////////////////////////
    /////////////MALZEME
    ///////////////////////////////////////////////	
	protected IKategorilendirilebilir malzemeler;
	protected IKategorilendirilebilir demirbasMalzemeler;
	protected IKategorilendirilebilir elektronikMalzemeler;
	protected IKategorilendirilebilir kirtasiyeMalzemeler;
	protected IKategorilendirilebilir bilgisayar;
	protected IKategorilendirilebilir bilgisayar2;
	protected IKategorilendirilebilir bilgisayar3;
	protected IKategorilendirilebilir mobilya;
	protected IKategorilendirilebilir bahce;
	protected IMalzemeTanimi modemTanimi0;
	protected IMalzemeTanimi modemTanimi1;
	protected IMalzemeTanimi modemTanimi3;
	protected IMalzemeTanimi kasaTanimi0;
	protected IMalzemeTanimi kasaTanimi1;
	protected IMalzemeTanimi monitorTanimi;
	protected IMalzemeTanimi yaziciTanimi;
	protected IMalzemeTanimi dolapTanimi;
	protected IMalzemeTanimi masaTanimi;
	protected IMalzemeTanimi koltukTanimi;
	protected IMalzemeTanimi makasTanimi;
	protected IMalzemeTanimi kazmaTanimi;
	protected IMalzemeTanimi kurekTanimi;
	protected IMalzemeTanimi ampulTanimi;
	protected IMalzemeTanimi pirizTanimi;
	protected IMalzemeTanimi kabloTanimi;
	protected IMalzemeTanimi kagitTanimi;
	protected IMalzemeTanimi kalemTanimi;
	protected IMalzemeTanimi zimbaTanimi;
	protected IMalzemeTanimi dosyaTanimi;
   	protected KategoriEventListener eventController;   	 
    ///////////////////////////////////////////////
    /////////////YERLEÞIM 
    ///////////////////////////////////////////////  
    protected AbstractZimmetAlan cagdas ;
    protected AbstractZimmetAlan umit;
    protected AbstractZimmetAlan sevgi0;
    protected AbstractZimmetAlan sevgi1;  
	protected AbstractZimmetAlan rektorlukAmbarGorevlisi0;
	protected AbstractZimmetAlan rektorlukAmbarGorevlisi1;
	protected AbstractZimmetAlan muhendislikAmbarGorevlisi;
    protected IDemirbasMalzeme masa1;
    protected IDemirbasMalzeme yazici;
    protected IDemirbasMalzeme sahipsizMalzeme;  
    protected AbstractZimmetAlan cagdasOda;
    protected AbstractZimmetAlan umitOda;
    protected AbstractZimmetAlan sevgiOda0;
    protected AbstractZimmetAlan sevgiOda1;
    protected AbstractZimmetAlan lab;   
    protected AbstractZimmetAlan buam;
    protected AbstractZimmetAlan muhDekanlik;    
    protected AbstractZimmetAlan muhendislik;
    protected AbstractZimmetAlan rektorluk;   
	protected AbstractZimmetAlan rektorlukAmbar;
	protected AbstractZimmetAlan muhendislikAmbar;
	protected AbstractZimmetAlan fenFakültesi;	
    protected AbstractZimmetAlan kampus;
    ///////////////////////////////////////////////
    /////////////MALZEME HAREKETLERI
    ///////////////////////////////////////////////  
	protected IDemirbasMalzeme masa0;
	protected IDemirbasMalzeme koltuk;
	protected IDemirbasMalzeme dolap;
	protected IDemirbasMalzeme monitor;
	protected IDemirbasMalzeme monitor1;
	protected HareketYeri rektorlukHareketYeri;
	protected HareketYeri cagdasHareketYeri;
	protected HareketYeri umitHareketYeri;
	protected HareketYeri sevgiHareketYeri;
	protected HareketYeri muhendislikHareketYeri;
	protected HareketYeri geciciSahipHareketYeri;
    protected MalzemeGirisFisi malzemeGirisFisi;
    protected MalzemeGirisFisi malzemeGirisFisi1;
    protected MalzemeCikisFisi malzemeCikisFisi1;
    protected MalzemeCikisFisi malzemeCikisFisi2;
    protected MalzemeCikisFisi malzemeCikisFisi3;
    protected MalzemeDevirFisi malzemeDevirFisi0;
    protected MalzemeDevirFisi malzemeDevirFisi1;
    protected MalzemeBozukFisi malzemeBozukFisi;
    protected MalzemeZayiFisi malzemeZayiFisi;
    protected IMalzemeHareketi malzemeGirisHareketi;
    protected IMalzemeHareketi malzemeGirisHareketi1;
    protected IMalzemeHareketi malzemeCikisHareketi1;
    protected IMalzemeHareketi malzemeCikisHareketi2;
    protected IMalzemeHareketi malzemeCikisHareketi3;
    protected IMalzemeHareketi malzemeDevirHareketi0;
    protected IMalzemeHareketi malzemeDevirHareketi1;
    protected IMalzemeHareketi malzemeZayiHareketi;
    protected IMalzemeHareketi malzemeBozukHareketi;
    protected NitelikDegeri monitorMarka;
    protected NitelikDegeri mobilyaMarka;
    protected NitelikDegeri monitorBoyut;
    protected NitelikDegeri masaBoyut;
    protected NitelikDegeri koltukMarka;
    protected NitelikDegeri dolapMarka;
    protected NitelikDegeri masaMarka;
    protected NitelikDegeri yaziciMarka;
    protected Date tarih;
    protected Calendar rightNow;
    
    protected void setUp() throws Exception {
        super.setUp();
        
    	///////////////////////////////////////////////
        /////////////MALZEME DATA SETÝ
        ///////////////////////////////////////////////
        
      	malzemeler = new Kategori("Malzemeler","00");        	
       	demirbasMalzemeler = new Kategori("Demirbas Malzemeler","01");
       	elektronikMalzemeler = new Kategori("Elektronik Malzemeler","02");
       	kirtasiyeMalzemeler = new Kategori("Kirtasiye Malzemeleri","03");        	
       	bilgisayar = new Kategori("Bilgisayar","01");
       	bilgisayar2 = new Kategori("Bilgisayar","02");
       	bilgisayar3 = new Kategori("Bilgisayar","03");
       	mobilya = new Kategori("Büro Mobilyalari","02");
       	bahce = new Kategori("Bag-Bahce Demirbasi","03");         
       	modemTanimi0= new DemirbasMalzemeTanimi("Modem","01",AbstractMalzemeTanimi.ADET);
       	modemTanimi1= new DemirbasMalzemeTanimi("Modem","02",AbstractMalzemeTanimi.ADET);
       	modemTanimi3= new DemirbasMalzemeTanimi("Modem","03",AbstractMalzemeTanimi.ADET);
       	kasaTanimi1 = new DemirbasMalzemeTanimi("Kasa1","04",AbstractMalzemeTanimi.ADET);
    	monitorTanimi = new DemirbasMalzemeTanimi("Monitör","05",AbstractMalzemeTanimi.ADET);
    	yaziciTanimi = new DemirbasMalzemeTanimi("Yazýcý","06",AbstractMalzemeTanimi.ADET);
    	kasaTanimi0 = new DemirbasMalzemeTanimi("Kasa","07",AbstractMalzemeTanimi.ADET);
    	masaTanimi = new DemirbasMalzemeTanimi("Masa","08",AbstractMalzemeTanimi.ADET); 
    	dolapTanimi = new DemirbasMalzemeTanimi("Dolap","09",AbstractMalzemeTanimi.ADET); 
    	koltukTanimi = new DemirbasMalzemeTanimi("Koltuk","10",AbstractMalzemeTanimi.ADET); 
    	makasTanimi = new DemirbasMalzemeTanimi("Makas","11",AbstractMalzemeTanimi.ADET); 
    	kazmaTanimi = new DemirbasMalzemeTanimi("Kazma","12",AbstractMalzemeTanimi.ADET); 
    	kurekTanimi =  new DemirbasMalzemeTanimi("Kürek","13",AbstractMalzemeTanimi.ADET); 
    	ampulTanimi = new DemirbasMalzemeTanimi("Ampul","14",AbstractMalzemeTanimi.ADET);
    	pirizTanimi = new DemirbasMalzemeTanimi("Piriz","15",AbstractMalzemeTanimi.ADET);
    	kabloTanimi = new DemirbasMalzemeTanimi("Kablo" ,"16",AbstractMalzemeTanimi.ADET);
    	kagitTanimi = new DemirbasMalzemeTanimi("Kagit","17",AbstractMalzemeTanimi.ADET);
    	kalemTanimi = new DemirbasMalzemeTanimi("Kalem","18",AbstractMalzemeTanimi.ADET);
    	zimbaTanimi = new DemirbasMalzemeTanimi("Zimba","19",AbstractMalzemeTanimi.ADET);
    	dosyaTanimi = new DemirbasMalzemeTanimi("Dosya","20",AbstractMalzemeTanimi.ADET);

           	modemTanimi0.addNitelikTanimi(new NitelikTanimi("Marka"));
        	kasaTanimi1.addNitelikTanimi(new NitelikTanimi("Marka"));
        	modemTanimi1.addNitelikTanimi(new NitelikTanimi("Marka"));
        	modemTanimi3.addNitelikTanimi(new NitelikTanimi("Marka"));
        	monitorTanimi.addNitelikTanimi(new NitelikTanimi("Boyut"));
        	monitorTanimi.addNitelikTanimi(new NitelikTanimi("Marka"));
        	yaziciTanimi.addNitelikTanimi(new NitelikTanimi("Model"));
        	yaziciTanimi.addNitelikTanimi(new NitelikTanimi("Marka"));
        	kasaTanimi0.addNitelikTanimi(new NitelikTanimi("Boyut"));
        	kasaTanimi0.addNitelikTanimi(new NitelikTanimi("Marka"));
        	masaTanimi.addNitelikTanimi(new NitelikTanimi("Boyut"));
        	dolapTanimi.addNitelikTanimi(new NitelikTanimi("Boyut"));
        	koltukTanimi.addNitelikTanimi(new NitelikTanimi("Boyut"));
        	makasTanimi.addNitelikTanimi(new NitelikTanimi("Boyut"));
        	kazmaTanimi.addNitelikTanimi(new NitelikTanimi("Boyut"));
        	kurekTanimi.addNitelikTanimi(new NitelikTanimi("Boyut"));
        	ampulTanimi.addNitelikTanimi(new NitelikTanimi("Marka"));
        	pirizTanimi.addNitelikTanimi(new NitelikTanimi("Marka"));
        	kabloTanimi.addNitelikTanimi(new NitelikTanimi("Marka"));
        	kagitTanimi.addNitelikTanimi(new NitelikTanimi("Marka"));
        	kalemTanimi.addNitelikTanimi(new NitelikTanimi("Marka"));
        	zimbaTanimi.addNitelikTanimi(new NitelikTanimi("Marka"));
        	dosyaTanimi.addNitelikTanimi(new NitelikTanimi("Marka"));
        	

        	demirbasMalzemeler.addKategori(bilgisayar);
        	demirbasMalzemeler.addKategori(mobilya);
        	demirbasMalzemeler.addKategori(bahce);
        	bilgisayar.addKategori(monitorTanimi);
        	bilgisayar.addKategori(kasaTanimi0);
        	bilgisayar.addKategori(yaziciTanimi);
        	bilgisayar2.addKategori(modemTanimi1);
        	bilgisayar3.addKategori(modemTanimi3);
        	bilgisayar3.addKategori(kasaTanimi1);
        	mobilya.addKategori(masaTanimi);
        	mobilya.addKategori(dolapTanimi);
        	mobilya.addKategori(koltukTanimi);
        	bahce.addKategori(makasTanimi);
        	bahce.addKategori(kazmaTanimi);
        	bahce.addKategori(kurekTanimi);
        	elektronikMalzemeler.addKategori(ampulTanimi);
        	elektronikMalzemeler.addKategori(pirizTanimi);
        	elektronikMalzemeler.addKategori(kabloTanimi);
        	elektronikMalzemeler.addKategori(bilgisayar2);
        	kirtasiyeMalzemeler.addKategori(kagitTanimi);
        	kirtasiyeMalzemeler.addKategori(kalemTanimi);
        	kirtasiyeMalzemeler.addKategori(zimbaTanimi);
        	kirtasiyeMalzemeler.addKategori(dosyaTanimi);
        	

        	malzemeler.addKategori(elektronikMalzemeler);
        	malzemeler.addKategori(kirtasiyeMalzemeler);
        	malzemeler.addKategori(demirbasMalzemeler);
        	malzemeler.addKategori(bilgisayar3);
        	
        	eventController = new KategoriEventListener();
       	
        	///////////////////////////////////////////////
            /////////////YERLEÞIM DATA SETÝ
            ///////////////////////////////////////////////
        	/************** yerlesim & hareketler ortak kullanacak *******/
        	masaBoyut = new NitelikDegeri("Boyut","150*120");
        	koltukMarka = new NitelikDegeri("Marka","Dekor");
        	dolapMarka = new NitelikDegeri("Marka","Dekor");
        	monitorMarka = new NitelikDegeri("Marka","LG");
        	monitorBoyut = new NitelikDegeri("Boyut","17'");
        	masaMarka = new NitelikDegeri("Marka","Dekor");
        	yaziciMarka = new NitelikDegeri("Marka","HP") ;
			mobilyaMarka = new NitelikDegeri("Marka","Dekor");
			
			MalzemePahasi masa0Pahasi = new MalzemePahasi();
			MalzemePahasi masa1Pahasi = new MalzemePahasi();
			MalzemePahasi koltukPahasi = new MalzemePahasi();
			MalzemePahasi dolapPahasi = new MalzemePahasi();
			MalzemePahasi yaziciPahasi = new MalzemePahasi();
			MalzemePahasi monitorPahasi = new MalzemePahasi();
			MalzemePahasi monitorPahasi1 = new MalzemePahasi();
			MalzemePahasi sahipsizMalzemePahasi = new MalzemePahasi();
            
        	masa0 = new DemirbasMalzeme();
			masa0.setMalzemeTanimi(masaTanimi);
			masa0.addNitelikDegeri(masaBoyut);
			masa0Pahasi.setKdvOrani(new Float(18));
			masa0Pahasi.setVergisizBirimFiyat(Money.turkLirasi(150000000));
			masa0Pahasi.setIskontoTutari(Money.yeniTurkLirasi(12.5));
			masa0.setPaha(masa0Pahasi); 
        	
        	koltuk = new DemirbasMalzeme();
			koltuk.setMalzemeTanimi(koltukTanimi);
			koltuk.addNitelikDegeri(koltukMarka);
			koltukPahasi.setOtvOrani(new Float(14.5));
			koltukPahasi.setVergiliBirimFiyat(Money.yeniTurkLirasi(108.693));
			koltuk.setPaha(koltukPahasi);
        	
        	dolap = new DemirbasMalzeme();
			dolap.setMalzemeTanimi(dolapTanimi);
			dolap.addNitelikDegeri(dolapMarka);
			dolapPahasi.setKdvOrani(new Float(18));
			dolapPahasi.setVergisizBirimFiyat(Money.yeniTurkLirasi(120));
			dolap.setPaha(dolapPahasi);
			
        	monitor = new DemirbasMalzeme();
			monitor.setMalzemeTanimi(monitorTanimi);
			monitor.addNitelikDegeri(monitorMarka);
			monitor.addNitelikDegeri(monitorBoyut);
			monitorPahasi.setOtvOrani(new Float(18));
			monitorPahasi.setVergisizBirimFiyat(Money.turkLirasi(165600000));
			monitorPahasi.setIskontoTutari(Money.yeniTurkLirasi(24.22));
			monitor.setPaha(monitorPahasi);
			
        	monitor1 = new DemirbasMalzeme();
			monitor1.setMalzemeTanimi(monitorTanimi);
			monitor1.addNitelikDegeri(monitorBoyut);
			monitor1.addNitelikDegeri(monitorMarka);
			monitorPahasi1.setOtvOrani(new Float(18));
			monitorPahasi1.setVergisizBirimFiyat(Money.turkLirasi(165600000));
			monitorPahasi1.setIskontoTutari(Money.yeniTurkLirasi(24.22));
			monitor1.setPaha(monitorPahasi1);
			
			masa1 = new DemirbasMalzeme();
        	masa1.setMalzemeTanimi(masaTanimi);
        	masa1.addNitelikDegeri(masaMarka);
        	masa1Pahasi.setKdvOrani(new Float(8));
        	masa1Pahasi.setVergisizBirimFiyat(Money.yeniTurkLirasi(150));
        	masa1.setPaha(masa1Pahasi);
			
        	yazici = new DemirbasMalzeme();
        	yazici.setMalzemeTanimi(yaziciTanimi);
        	yazici.addNitelikDegeri(yaziciMarka);
        	yaziciPahasi.setKdvOrani(new Float(8));
        	yaziciPahasi.setVergisizBirimFiyat(Money.yeniTurkLirasi(45));
        	yazici.setPaha(yaziciPahasi);
			
        	sahipsizMalzeme = new DemirbasMalzeme();
        	sahipsizMalzeme.setMalzemeTanimi(zimbaTanimi);
        	sahipsizMalzeme.addNitelikDegeri(mobilyaMarka);
        	sahipsizMalzemePahasi.setKdvOrani(new Float(11));
        	sahipsizMalzemePahasi.setVergiliBirimFiyat(Money.yeniTurkLirasi(416));
			sahipsizMalzeme.setPaha(sahipsizMalzemePahasi);			
			
        	cagdas = new Personel("65544","Cagdas","Cirit");
        	umit = new Personel("211212","Umit","Akyol");
        	sevgi0 = new Personel("666854","Sevgi","Uslu");
        	sevgi1 = new Personel("666854","Sevgi","Uslu");
			rektorlukAmbarGorevlisi0 = new Personel("23222","Salih","Yilmaz");
			rektorlukAmbarGorevlisi1 = new Personel("23654","Ibrahim","Celik");
			muhendislikAmbarGorevlisi = new Personel("26598","Dursun","Kara");
            
			            
            cagdasOda = new Oda("205",Oda.PERSONEL,new Integer(2));
            umitOda = new Oda("301",Oda.PERSONEL,new Integer(3));
            sevgiOda0 = new Oda("106",Oda.PERSONEL,new Integer(1));
            sevgiOda1 = new Oda("406",Oda.PERSONEL,new Integer(4));
            lab = new Oda("L-109",Oda.LAB,new Integer(1));
            
            buam = new Bina("Bilgisayar Uygulama ve Arastirma Merkezi");
            muhDekanlik = new Bina("Mühendislik Fakültesi Dekanlik");
            
            muhendislik = new Birim("Muhendislik Fakültesi");
            rektorluk = new Birim("Rektörlük");
            kampus = new Birim("IYTE Kampus");
            
            rektorlukAmbar = new Ambar("Rektörlük Ambari");
			muhendislikAmbar = new Ambar("Mühendislik Ambari");
            fenFakültesi = new Birim("Fen Fakültesi");
            
            cagdas.addDemirbasMalzeme(masa0);
            cagdasOda.addKategori(cagdas);
            muhDekanlik.addKategori(cagdasOda);
            umit.addDemirbasMalzeme(koltuk);
            umitOda.addDemirbasMalzeme(masa1);
            umitOda.addKategori(umit);
            muhDekanlik.addKategori(umitOda);      
            sevgi0.addDemirbasMalzeme(dolap);
            sevgiOda0.addKategori(sevgi0);
            buam.addKategori(sevgiOda0);
            sevgi1.addDemirbasMalzeme(yazici);
            sevgiOda1.addKategori(sevgi1);
			rektorlukAmbar.addKategori(rektorlukAmbarGorevlisi0);
			rektorlukAmbar.addKategori(rektorlukAmbarGorevlisi1);
			muhendislikAmbar.addKategori(muhendislikAmbarGorevlisi);
            muhDekanlik.addKategori(sevgiOda1);
            muhendislik.addKategori(muhDekanlik);
			muhendislik.addKategori(muhendislikAmbar);
            muhendislik.addKategori(buam); 
			rektorluk.addKategori(rektorlukAmbar);
            kampus.addKategori(muhendislik);
            kampus.addKategori(rektorluk);
			
	    	///////////////////////////////////////////////
	        /////////////MALZEME HAREKETLERI DATA SETÝ
	        /////////////////////////////////////////////
        	rektorlukHareketYeri = new HareketYeri(rektorlukAmbar);
			cagdasHareketYeri = new HareketYeri(cagdas);
			umitHareketYeri = new HareketYeri(umit);
			sevgiHareketYeri = new HareketYeri(sevgi0);
			muhendislikHareketYeri = new HareketYeri(muhendislikAmbar);
			geciciSahipHareketYeri = new HareketYeri(sevgi1); 
			//umitOdaHareketYeri = new HareketYeri(umitOda);
			
			rightNow = Calendar.getInstance();
			tarih = new Date(rightNow.getTimeInMillis());
			
	        malzemeGirisHareketi = new MalzemeGirisHareketi();
	        malzemeGirisHareketi.setHareketHedefi(rektorlukHareketYeri);
	        malzemeGirisHareketi.setHareketTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeGirisFisi = (MalzemeGirisFisi) malzemeGirisHareketi.getHareketFisi();
	        malzemeGirisFisi.setAlindigiKurulus("Dekor Mobilya A.S.");
	        malzemeGirisFisi.setTeslimEden("Mehmet Altintas");
	        malzemeGirisFisi.setFaturaNo("12563250");
	        malzemeGirisFisi.setFaturaTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeGirisFisi.setDuzenlemeTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeGirisFisi.setOwningMalzemeHareketi(malzemeGirisHareketi);
	        malzemeGirisFisi.setOnayVeren((Personel) rektorlukAmbarGorevlisi0);
	        malzemeGirisFisi.setGirisSekli(MalzemeGirisFisi.SATINALMA);
			malzemeGirisHareketi.setHareketFisi(malzemeGirisFisi);
	        malzemeGirisHareketi.addMalzeme(masa0);
	        malzemeGirisHareketi.addMalzeme(masa1);
	        malzemeGirisHareketi.addMalzeme(koltuk);
	        malzemeGirisHareketi.addMalzeme(dolap);
	        malzemeGirisHareketi.addMalzeme(monitor);
	        malzemeGirisHareketi.addMalzeme(yazici);
	        malzemeGirisHareketi.addMalzeme(sahipsizMalzeme);
	        
	        malzemeGirisHareketi1 = new MalzemeGirisHareketi();
	        malzemeGirisHareketi1.setHareketHedefi(muhendislikHareketYeri);
	        malzemeGirisHareketi1.setHareketTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeGirisFisi1 = (MalzemeGirisFisi) malzemeGirisHareketi1.getHareketFisi();
	        malzemeGirisFisi1.setAlindigiKurulus("Düzey Bilgisayar A.S.");
	        malzemeGirisFisi1.setTeslimEden("Derya Kurtuluþ");
	        malzemeGirisFisi1.setFaturaNo("658264");
	        malzemeGirisFisi1.setFaturaTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeGirisFisi1.setDuzenlemeTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeGirisFisi1.setOwningMalzemeHareketi(malzemeGirisHareketi1);
	        malzemeGirisFisi1.setOnayVeren((Personel) muhendislikAmbarGorevlisi);
	        malzemeGirisFisi1.setGirisSekli(MalzemeGirisFisi.SATINALMA);
			malzemeGirisHareketi1.setHareketFisi(malzemeGirisFisi1);
	        malzemeGirisHareketi1.addMalzeme(monitor1);
	        malzemeGirisHareketi1.hareketiGerceklestir();
	        
	        malzemeCikisHareketi1 = new MalzemeCikisHareketi();
	        malzemeCikisHareketi1.setHareketHedefi(cagdasHareketYeri);
	        malzemeCikisHareketi1.setHareketKaynagi(rektorlukHareketYeri);
	        malzemeCikisHareketi1.setHareketTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeCikisHareketi1.addMalzeme(masa0);
			malzemeCikisFisi1 =  (MalzemeCikisFisi) malzemeCikisHareketi1.getHareketFisi();
			malzemeCikisFisi1.setCikisSekli(MalzemeCikisFisi.ZIMMET);
	        malzemeCikisFisi1.setAlinisNedeni("Ýhtiyac");
	        malzemeCikisFisi1.setDuzenlemeTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeCikisFisi1.setOwningMalzemeHareketi(malzemeCikisHareketi1);
	        malzemeCikisHareketi1.setHareketFisi(malzemeCikisFisi1);
			
	        malzemeCikisHareketi2 = new MalzemeCikisHareketi();
	        malzemeCikisHareketi2.setHareketHedefi(umitHareketYeri);
	        malzemeCikisHareketi2.setHareketKaynagi(rektorlukHareketYeri);
	        malzemeCikisHareketi2.setHareketTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeCikisHareketi2.addMalzeme(koltuk);
	        malzemeCikisHareketi2.addMalzeme(masa1);
	        malzemeCikisFisi2 = (MalzemeCikisFisi) malzemeCikisHareketi2.getHareketFisi();
			malzemeCikisFisi2.setCikisSekli(MalzemeCikisFisi.ZIMMET);
	        malzemeCikisFisi2.setAlinisNedeni("Ýhtiyac");
	        malzemeCikisFisi2.setDuzenlemeTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeCikisFisi2.setOwningMalzemeHareketi(malzemeCikisHareketi2);
			malzemeCikisHareketi2.setHareketFisi(malzemeCikisFisi2);
	        
	        malzemeCikisHareketi3 = new MalzemeCikisHareketi();
	        malzemeCikisHareketi3.setHareketHedefi(muhendislikHareketYeri);
	        malzemeCikisHareketi3.setHareketKaynagi(rektorlukHareketYeri);
			malzemeCikisHareketi3.setGeciciSahip(geciciSahipHareketYeri);
	        malzemeCikisHareketi3.addMalzeme(dolap);
			malzemeCikisHareketi3.setHareketTarihi(new Date(rightNow.getTimeInMillis()));
			malzemeCikisFisi3 = (MalzemeCikisFisi) malzemeCikisHareketi3.getHareketFisi();
			malzemeCikisFisi3.setCikisSekli(MalzemeCikisFisi.TRANSFER);
	        malzemeCikisFisi3.setDuzenlemeTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeCikisFisi3.setOwningMalzemeHareketi(malzemeCikisHareketi3);
			malzemeCikisHareketi3.setHareketFisi(malzemeCikisFisi3);
	        
	        malzemeDevirHareketi0 = new MalzemeDevirHareketi();
	        malzemeDevirHareketi0.setHareketHedefi(sevgiHareketYeri);
	        malzemeDevirHareketi0.setHareketKaynagi(umitHareketYeri);
	        malzemeDevirHareketi0.setHareketTarihi(new Date(rightNow.getTimeInMillis()));
	        //malzemeDevirHareketi0.addMalzeme(koltuk);
	        malzemeDevirHareketi0.addMalzeme(masa1);
			malzemeDevirFisi0 = (MalzemeDevirFisi) malzemeDevirHareketi0.getHareketFisi();
	        malzemeDevirFisi0.setDuzenlemeTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeDevirFisi0.setOwningMalzemeHareketi(malzemeDevirHareketi0);
			malzemeDevirHareketi0.setHareketFisi(malzemeDevirFisi0);

	        malzemeDevirHareketi1 = new MalzemeDevirHareketi();
	        malzemeDevirHareketi1.setHareketHedefi(rektorlukHareketYeri);
	        malzemeDevirHareketi1.setHareketKaynagi(cagdasHareketYeri);
	        malzemeDevirHareketi1.setHareketTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeDevirHareketi1.addMalzeme(masa0);
			malzemeDevirFisi1 = (MalzemeDevirFisi) malzemeDevirHareketi1.getHareketFisi();
	        malzemeDevirFisi1.setDuzenlemeTarihi(new Date(rightNow.getTimeInMillis()));
	        malzemeDevirFisi1.setOwningMalzemeHareketi(malzemeDevirHareketi1);
			malzemeDevirHareketi1.setHareketFisi(malzemeDevirFisi1);
	        
			malzemeBozukHareketi = new MalzemeBozukHareketi();
			malzemeBozukHareketi.addMalzeme(monitor);
			malzemeBozukFisi = (MalzemeBozukFisi) malzemeBozukHareketi.getHareketFisi();
			malzemeBozukFisi.setDuzenlemeTarihi(new Date(rightNow.getTimeInMillis()));
			malzemeBozukFisi.setOnayVeren((Personel) rektorlukAmbarGorevlisi0);
			malzemeBozukFisi.setBozulmaNedeni("Masadan düstü");
			malzemeBozukFisi.setOwningMalzemeHareketi(malzemeBozukHareketi);
			malzemeBozukHareketi.setHareketFisi(malzemeBozukFisi);
					
	        malzemeZayiHareketi = new MalzemeZayiHareketi();
	        malzemeZayiHareketi.setHareketKaynagi(rektorlukHareketYeri);
	        malzemeZayiHareketi.addMalzeme(monitor);
			malzemeZayiFisi = (MalzemeZayiFisi) malzemeZayiHareketi.getHareketFisi();
	        malzemeZayiFisi.setDuzenlemeTarihi(new Date(rightNow.getTimeInMillis()));
			malzemeZayiFisi.setOnayVeren((Personel) rektorlukAmbarGorevlisi0);
			HareketBilgileri hareketBilgileri = new HareketBilgileri();
			hareketBilgileri.setZayi_terkinNedeni("Tüpü patlamis");
	        malzemeZayiFisi.setHareketBilgileri(hareketBilgileri);
	        malzemeZayiFisi.setOwningMalzemeHareketi(malzemeZayiHareketi);
			malzemeZayiHareketi.setHareketFisi(malzemeZayiFisi);
			
	       kullanici=new Kullanici(sevgi0,"Sevgi","123");
	       kullanici1=new Kullanici(cagdas,"Caðdas","123");
	       kullanici2=new Kullanici(umit,"Umit","000");
	       kullanici.addAmbar((Ambar)muhendislikAmbar);
	       kullanici1.addAmbar((Ambar)rektorlukAmbar);
	       kullanici2.addAmbar((Ambar)muhendislikAmbar);
	       kullanici2.addAmbar((Ambar)rektorlukAmbar);
   }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
