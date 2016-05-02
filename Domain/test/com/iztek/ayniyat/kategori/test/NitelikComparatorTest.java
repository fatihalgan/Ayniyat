package com.iztek.ayniyat.kategori.test;

import com.iztek.ayniyat.kategori.NitelikComparator;
import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.test.BaseTestCase;



/**
 * @author Cagdas CIRIT
 **/
public class NitelikComparatorTest extends BaseTestCase {

    protected NitelikTanimi boyut;
    protected NitelikTanimi marka;
    protected NitelikComparator nitelikComparator;
    
    public static void main(String[] args) {
        junit.swingui.TestRunner.run(NitelikComparatorTest.class);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
		boyut = new NitelikTanimi("Boyut");
		marka = new NitelikTanimi("Marka");
    	nitelikComparator = new NitelikComparator();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCompare(){
    	assertTrue(nitelikComparator.compare(boyut,marka)<0);
    	assertTrue(nitelikComparator.compare(marka,boyut)>0);
    	assertTrue(nitelikComparator.compare(marka,marka)==0);
    }
}
