package com.iztek.ayniyat.sorgular.stoksorgulama.controller;



import java.util.Collection;
import java.util.Vector;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.sorgular.controller.MalzemeSorguController;
import com.iztek.ayniyat.sorgular.gui.AbstractMalzemeSorguFrame;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
/**
 * @author Cagdas CIRIT
 **/
public class StokSorguController implements MalzemeSorguController {
    private AbstractMalzemeSorguFrame ambarSorgulama;

    public void registerSelf(AbstractMalzemeSorguFrame ambarSorgulama) {
        this.ambarSorgulama = ambarSorgulama;
    }
    
    public Collection getDataForSorguTable() { 
    	return null;
    }
     
    public Collection getDataForSorguTable(Ambar ambar){
       	return new Vector(MalzemeManager.AmbarStokSorgulama(ambar));
    }
    
    public void malzemeIncelemekIcinDialogCikar(AbstractMalzemeTanimi tanim){
	   AnaController.getInstance().getMalzemeCikisiController().sendSeciliNodeToMalzemeCikisi(tanim);
    }
}
