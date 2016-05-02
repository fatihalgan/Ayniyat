package com.iztek.ayniyat.malzemehareketleri.persistence;

import java.util.Collection;
import java.util.HashMap;

import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.util.persistence.AyniyatTableModelData;

/**
 * @author Umit Akyol
*/
public interface IMalzemeDAO {
	public IMalzeme getMalzemeById(Long id);
	public IDemirbasMalzeme findDemirbasByDemirbasNo(String demirbasNo);
	public Collection getNitelikTaniminaAitFarkliDegerler(AbstractMalzemeTanimi malzemeTanimi, String nitelikAdi);
	public Collection getDemirbaslarNitelikDegeriBelirlenmis(AbstractMalzemeTanimi malzemeTanimi, HashMap nitelikler, IZimmetAlan currentAmbar);
	public Collection getDemirbaslarByState(IMalzemeState state);
	public Collection getDemirbaslarByMalzemeTanimi(AbstractMalzemeTanimi malzemeTanimi);
	public Collection getZimmetliDemirbaslarByMalzemeTanimi(AbstractMalzemeTanimi malzemeTanimi);
    public Collection getMalzemeTanimlariVisibleToKullanici(String kullanici);
    public Collection ambarStokSorgulama(Ambar ambar);
    public Collection bozukMalzemeSorgulama();
    public Collection zayiMalzemeSorgulama();
    public Collection terkinMalzemeSorgulama();
    public Collection getTransferMalzemeHareketleri(IZimmetAlan hedefZimmetAlan,IZimmetAlan kaynakZimmetAlan);
    public Collection getMalzemeTanimi(AyniyatTableModelData tableData);
    
}
