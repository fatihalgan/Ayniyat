package com.iztek.ayniyat.event.test;

import com.iztek.ayniyat.data.dbunit.DeHibernatizedTestCase;

/**
 * @author Cagdas CIRIT
 **/
public class Sample extends DeHibernatizedTestCase{

    public Sample(String name) {
        super(name);
    }
    
    public void testSample(){
        //Nesneleri burda hibernate kullanarak bellege �ekip i�lemler yap�l�r
        assertTrue(true);
    }

}
