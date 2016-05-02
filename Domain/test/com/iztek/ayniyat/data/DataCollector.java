 package com.iztek.ayniyat.data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemePahasi;
import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;
import com.iztek.ayniyat.malzemehareketleri.domain.StateZimmetli;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.yerlesim.domain.Bina;
import com.iztek.ayniyat.yerlesim.domain.Birim;
import com.iztek.ayniyat.yerlesim.domain.Oda;
import com.iztek.ayniyat.yerlesim.domain.Personel;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;
import com.iztek.commons.money.Money;
import com.iztek.util.persistence.DAOFactory;

/**
 * @author Umit Akyol
 *
 */
public class DataCollector {
    private static HashMap nitelikTanimlari = new HashMap();
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        getMalzemeTanimlari(null);
        System.out.println("------------------------------");
        getOdalar(null,null);
        System.out.println("------------------------------");
        getPersoneller(null,null);
        System.out.println("------------------------------");
        getDemirbaslar(null,null,null);
        
    } 
    
    public static void getMalzemeTanimlari(IKategorilendirilebilir root) throws FileNotFoundException, IOException{
       IKategorilendirilebilir anaKategori = null;
        POIFSFileSystem fs      =
            new POIFSFileSystem(new FileInputStream(getPath()+"MalzemeTanimlari.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
            Vector nitelikleri = new Vector();
            HSSFRow row = sheet.getRow(i);
            String anaMalzemeKod= row.getCell((short)0).getStringCellValue().trim();
            String anaMalzeme 	= row.getCell((short)1).getStringCellValue().trim(); 
            String tanimi     	= row.getCell((short)2).getStringCellValue().trim(); 
            String birimi	 	= row.getCell((short)3).getStringCellValue().trim();
            String nitelikler	= row.getCell((short)4).getStringCellValue().trim();
            String kod       	= row.getCell((short)5).getStringCellValue().trim();
            if(!anaMalzeme.equals("Bilgisayar"))
                anaKategori = root.getChildByNodeValue(anaMalzemeKod+"-"+anaMalzeme);
            else
                anaKategori = root;
            
            if(anaKategori == null){
                anaKategori = new Kategori(anaMalzeme,anaMalzemeKod);
                root.addKategori(anaKategori);
            }
            //Demirbas malzeme tanimini yarat
            DemirbasMalzemeTanimi yeniMalzemeTanimi = new DemirbasMalzemeTanimi(tanimi,kod,DemirbasMalzemeTanimi.ADET);
            //Nitelik tanimlarini yarat ve malzemetanimina ekle
            StringTokenizer tok = new StringTokenizer(nitelikler,",");
            while(tok.hasMoreTokens()){
                String nitelik = tok.nextToken().trim();
                nitelikleri.add(nitelik);
                NitelikTanimi yeniNitelik = new NitelikTanimi(nitelik);
                yeniMalzemeTanimi.addNitelikTanimi(yeniNitelik);
                
                //malzemeyi ekle
                anaKategori.addKategori(yeniMalzemeTanimi);
                
            }
            nitelikTanimlari.put(tanimi,nitelikleri);
        }
    }        
    
    public static void getOdalar(Birim buam,Bina bina) throws FileNotFoundException, IOException{
        Oda yeniOda = null;
        POIFSFileSystem fs      =
            new POIFSFileSystem(new FileInputStream(getPath()+"OdaTanimlari.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
            HSSFRow row = sheet.getRow(i);
            String tanimi = row.getCell((short) 0).getStringCellValue().trim(); 
            Integer kat   = new Integer((int)row.getCell((short)1).getNumericCellValue());
            String tipi   = row.getCell((short) 2).getStringCellValue().trim(); 
            
            tanimi = InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(tanimi);
            
            StringTokenizer tok = new StringTokenizer(tanimi,"-");
            String temp=tok.nextToken().trim();
            if(temp.equals("Buam")){
                String odaTanimi = tok.nextToken().trim();
                yeniOda = new Oda(odaTanimi,tipi,kat);
                if(buam != null){
                    buam.addKategori(yeniOda);
                }
            }
            else{
                yeniOda = new Oda(tanimi,tipi,kat);
                if(bina != null){
                    bina.addKategori(yeniOda);
                }  
            }
        }
    }
    
    public static void getPersoneller(Bina bina,Birim birim) throws FileNotFoundException, IOException{
       Oda oda = null;
        POIFSFileSystem fs      =
            new POIFSFileSystem(new FileInputStream(getPath()+"PersonelTanimlari.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
            HSSFRow row = sheet.getRow(i);
            String odaTanimi = row.getCell((short)0).getStringCellValue().trim();
            String sicilNo   = row.getCell((short)1).getStringCellValue().trim();
            String ad        = row.getCell((short)2).getStringCellValue().trim();
            
            odaTanimi = InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(odaTanimi);
            ad        = InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(ad);
            
            //yeni personel yarat
            Personel yeniPersonel = new Personel();
            yeniPersonel.setTanim(ad);
            yeniPersonel.setSicilNo(sicilNo);
            StringTokenizer tokenizer = new StringTokenizer(odaTanimi,"-");
            String temp = tokenizer.nextToken().trim();
            
            //zimmetlenecegi yada zimmetsahibinin bulundugu odayý bul
            if(temp.equals("Buam")){
                odaTanimi = tokenizer.nextToken().trim();
                if(birim != null){
                    oda = (Oda) birim.getChildByNodeValue(odaTanimi);
                    oda.addKategori(yeniPersonel);
                }
            }
            else{
                odaTanimi = temp;
                if(bina != null){
                    oda = (Oda) bina.getChildByNodeValue(odaTanimi);
                    oda.addKategori(yeniPersonel);
                }
            }
        }
    }
    
    public static void getPersonel(Bina bina,Birim birim) throws FileNotFoundException, IOException{
        Oda oda = null;
         POIFSFileSystem fs      =
             new POIFSFileSystem(new FileInputStream(getPath()+"personel.xls"));
         HSSFWorkbook wb = new HSSFWorkbook(fs);
         HSSFSheet sheet = wb.getSheetAt(0);
         for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
             HSSFRow row = sheet.getRow(i);
             String sicilNo   = row.getCell((short)0).getStringCellValue().trim();
             String ad        = row.getCell((short)1).getStringCellValue().trim();
             
             ad        = InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(ad);
             
             //yeni personel yarat
             Personel yeniPersonel = new Personel();
             yeniPersonel.setTanim(ad);
             yeniPersonel.setSicilNo(sicilNo);
             
             Birim kampus = (Birim) YerlesimManager.findRootKategori(DAOFactory.YERLESIM);
             kampus.addKategori(yeniPersonel);
       
         }
     }
    
    public static void getDemirbaslar(Kategori root,Bina bina,Birim birim) throws FileNotFoundException, IOException {
        Oda oda = null;
        Personel personel=null;
        DemirbasMalzeme yeniDemirbas ;
        POIFSFileSystem fs      =
            new POIFSFileSystem(new FileInputStream(getPath()+"ZimmetTanimlari.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
            HSSFRow row = sheet.getRow(i);
            String malzemeTanimi    = row.getCell((short)2).getStringCellValue().trim();
            String nitelikler   = row.getCell((short)3).getStringCellValue().trim();
            String sahipSicilNo = row.getCell((short)1).getStringCellValue().trim();
            String odaTanimi    = row.getCell((short)0).getStringCellValue().trim();
            odaTanimi = InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(odaTanimi);

            StringTokenizer tk = new StringTokenizer(malzemeTanimi,"-");
            String kod = tk.nextToken();
            String tanim	 = tk.nextToken();
            //yeniDemirbas yarat
           yeniDemirbas = new DemirbasMalzeme();
           MalzemePahasi malzemePahasi = new MalzemePahasi();
           malzemePahasi.setKdvOrani(new Float(0));
           malzemePahasi.setVergisizBirimFiyat(Money.turkLirasi(0));
           malzemePahasi.setIskontoTutari(Money.yeniTurkLirasi(0));
           yeniDemirbas.setPaha(malzemePahasi); 
           yeniDemirbas.setState(new StateZimmetli());
                                           
            //tanimini bul
            if(root != null){
                DemirbasMalzemeTanimi demirbasMalzemeTanimi = (DemirbasMalzemeTanimi) root.getChildByNodeValue(malzemeTanimi);
                if(demirbasMalzemeTanimi == null){
                	System.out.println(malzemeTanimi);
                }
                yeniDemirbas.setMalzemeTanimi(demirbasMalzemeTanimi);
            }
            
            //nitelik degerlerini gir
            StringTokenizer tok = new StringTokenizer(nitelikler,",");
            Iterator iterator = ((Vector)nitelikTanimlari.get(tanim)).iterator();//getMalzemeTanimlari2(tanimKodu).iterator();
            while(iterator.hasNext()){
                String nitelikDegeri = tok.nextToken().trim();
                String nitelikAdi    = iterator.next().toString(); 
                if(!nitelikDegeri.equals("null"))
                    yeniDemirbas.addNitelikDegeri(new NitelikDegeri(nitelikAdi,nitelikDegeri));
            }
            StringTokenizer tokenizer = new StringTokenizer(odaTanimi,"-");
            String temp = tokenizer.nextToken().trim();
            
            //zimmetlenecegi yada zimmetsahibinin bulundugu odayý bul
            if(temp.equals("Buam")){
                odaTanimi = tokenizer.nextToken().trim();
                if(birim != null){
                    oda = (Oda) birim.getChildByNodeValue(odaTanimi);
                }
            }
            else{
                odaTanimi = temp;
                if(bina != null){
                    oda = (Oda) bina.getChildByNodeValue(odaTanimi);
                }
            }
            //sahibi belirle
            if(sahipSicilNo.equals("0")){
                oda.addDemirbasMalzeme(yeniDemirbas);
            }
            else{
                personel = oda.getPersonelBySicilNo(sahipSicilNo);
            	personel.addDemirbasMalzeme(yeniDemirbas);
            }
        }
    }
    
    private static String getPath(){
        return new DataCollector().getClass().getResource("/com/iztek/ayniyat/data/").getPath();
    }
   
 }
