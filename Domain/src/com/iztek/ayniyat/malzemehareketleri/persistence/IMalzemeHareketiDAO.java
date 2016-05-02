package com.iztek.ayniyat.malzemehareketleri.persistence;

import java.util.Collection;
import java.util.Date;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;

/**
 * @author Umit Akyol
 *
 */
public interface IMalzemeHareketiDAO {
    public Collection getAmbaraGirenMalzemeHareketleri(IZimmetAlan ambar, Date baslangic, Date bitis);
    public Collection getAmbardanCikanMalzemeHareketleri(IZimmetAlan ambar, Date baslangic, Date bitis);
    public Collection getMalzemeHareketleriOrderByBelgeNo(AbstractMalzeme malzeme);
}
