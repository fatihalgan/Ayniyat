package com.iztek.ayniyat.yerlesim.service.test;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class YerlesimCopyCutTest extends MainPersistedTestCase{
    public static void main(String[] args) {
		junit.swingui.TestRunner.run(YerlesimCopyCutTest.class);
	}
    
    public void testCopyZimmetAlanWithZimmetliDemirbas(){     
        try{
            YerlesimManager.copyKategori(buam,umitOda);
            fail("Zimmetli malzemeye sahip zimmet alanlar kopyalanamaz!!"); 
        }catch(Exception e){
        	assertNull(buam.getChildByNodeValue("301"));
        }           
    }
    
    public void testCopyZimmetAlanWithZimmetliChild(){      
        try{
        	YerlesimManager.copyKategori(rektorluk,muhDekanlik);
        	fail("Zimmetli malzemeye sahip alt kategori iceren zimmet alanlar kopyalanamaz!!"); 
        }catch(Exception e){
        	assertNull(rektorluk.getChildByNodeValue("M�hendislik Fak�ltesi Dekanlik"));
        }              
    }
    
    public void testCopyZimmetAlanWithPersonelChild(){
        //kopyalanan zimmet alanlar alt�ndaki personeller hedefe kopyalanmazlar      
        YerlesimManager.copyKategori(buam,cagdasOda);
        assertNotNull(buam.getChildByNodeValue("205"));
        IKategorilendirilebilir copyCagdasOda = buam.getChildByNodeValue("205");
        //odada bulunan personellerin kopyalanmad�g�n� test et
        assertTrue(copyCagdasOda.getAltKategoriler().size()==0);     
    }
    
    public void testCopyPersonelWithZimmetliDemirbas(){
        //personel tek bas�na kopyalanabilir fakat zimmetli mallar� kopyas�na dahil edilmez       
        YerlesimManager.copyKategori(sevgiOda0,umit);
        assertNotNull(sevgiOda0.getChildByNodeValue("Umit Akyol"));
        IKategorilendirilebilir copyPersonel = sevgiOda0.getChildByNodeValue("Umit Akyol");
        //kopyalanan personel zimmetli demirbas malzeme icermez
        assertTrue(((IZimmetAlan)copyPersonel).getZimmetliMalzemeler().size()==0);         
    }
    
    public void testCutZimmetAlanWithPersonelChild(){
        //zimmet alanlar alt�ndaki personelle birlikte tas�n�rlar    
        YerlesimManager.cutKategori(muhDekanlik,sevgiOda0);
        //odan�n tas�nd�g�n� test et
        assertNotNull(muhDekanlik.getChildByNodeValue("106"));
        assertNull(buam.getChildByNodeValue("106"));
        //odan�n personeliyle birlikte tas�nd�g�n� test et
        assertTrue(sevgiOda0.getAltKategoriler().size()!=0);       
    }
    
    public void testCutZimmetAlanWithZimmetliDemirbas(){
       	assertNotNull(muhDekanlik.getChildByNodeValue("301"));
        YerlesimManager.cutKategori(buam,umitOda);
        assertNull(muhDekanlik.getChildByNodeValue("301"));
        assertNotNull(buam.getChildByNodeValue("301"));
        assertNotNull(((IZimmetAlan) umitOda).getZimmetliDemirbasByNodeValue("08-Masa"));
    }
    
    public void testCutZimmetAlanWithSamePersonelChild(){
        //tas�ma esnas�nda ayn� tanima sahip zimmet alanlar eger ayn� isimli 
        //personel iceriyorsa islem iptal edilir       
        try{
        	YerlesimManager.cutKategori(sevgiOda0,sevgiOda1);
            fail("Ayn� isimli personeller birlestirilemez!!");
        }catch(Exception e){
        	 //IKategorilendirilebilir oldParent = TestUtils.findKategoriByNodeValue("406",DAOFactory.YERLESIM);
             //personel kayd� hala eski yerinde duruyor
        	 assertNotNull(sevgiOda1.getChildByNodeValue("Sevgi Uslu"));
        }
    } 
}
