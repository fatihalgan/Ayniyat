package com.iztek.ayniyat.kategori.test;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.KategoriComparator;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.test.BaseTestCase;



/**
 * @author Cagdas CIRIT
 **/
public class KategoriComparatorTest extends BaseTestCase {
    protected IKategorilendirilebilir demirbasMalzemeler;
    protected IKategorilendirilebilir bilgisayar;
    protected IMalzemeTanimi monitor;
    protected IMalzemeTanimi masa;
    protected KategoriComparator kategoriComparator;
    
    protected void setUp() throws Exception {
        super.setUp();
        demirbasMalzemeler = new Kategori("Demirbaþ Malzemeler","01");
        bilgisayar = new Kategori("Bilgisayar","01");
        monitor = new DemirbasMalzemeTanimi("Monitör","02",AbstractMalzemeTanimi.ADET);
        masa = new DemirbasMalzemeTanimi("Masa","01",AbstractMalzemeTanimi.ADET);        
    	kategoriComparator = new KategoriComparator();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public static void main(String[] args) {
        junit.swingui.TestRunner.run(KategoriComparatorTest.class);
    }
    
    public void testCompareBothKategori(){
    	assertTrue(kategoriComparator.compare(demirbasMalzemeler,bilgisayar)>0);
    	assertTrue(kategoriComparator.compare(bilgisayar,demirbasMalzemeler)<0);
    	assertTrue(kategoriComparator.compare(demirbasMalzemeler,demirbasMalzemeler)==0);
    }
    
    public void testCompareBothMalzeme(){
    	assertTrue(kategoriComparator.compare(monitor,masa)>0);
    	assertTrue(kategoriComparator.compare(masa,monitor)<0);
    	assertTrue(kategoriComparator.compare(masa,masa)==0);
    }
    
    public void testCompareMix(){
    	assertTrue(kategoriComparator.compare(demirbasMalzemeler,masa)<0);
    	assertTrue(kategoriComparator.compare(masa,demirbasMalzemeler)>0);
    }
}
