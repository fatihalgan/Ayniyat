package com.iztek.ayniyat.event.test;

import com.iztek.ayniyat.event.KategoriEvent;
import com.iztek.ayniyat.test.MainTestCase;


/**
 * @author Cagdas CIRIT
 **/
public class KategoriEventTest extends MainTestCase{
    public static void main(String[] args) {
        junit.swingui.TestRunner.run(KategoriEventTest.class);
    }
    
    public void testCreateKategoriEvent(){
    	KategoriEvent event = new KategoriEvent(this,ampulTanimi,elektronikMalzemeler);
    	assertEquals(event.getSource(),this);
    	assertEquals(event.getSourceNode(),ampulTanimi);
    	assertEquals(event.getDestinationNode(),elektronikMalzemeler);
    }
}
