package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Date;
import java.util.Set;

/**
 * @author Umit Akyol
 *
 */
public interface IMalzemeHareketi {
	public void addMalzeme(IMalzeme malzeme);
	public void removeMalzeme(IMalzeme malzeme);
	public Set getMalzemeler();
	public void setMalzemeler(Set malzemeler);	
    public AbstractMalzemeHareketFisi getHareketFisi();
    public void setHareketFisi(AbstractMalzemeHareketFisi malzemeHareketFisi) ;
    public boolean hasInMalzemeler(IMalzeme malzeme);
    public HareketYeri getGeciciSahip();
    public void setGeciciSahip(HareketYeri geciciSahip);
    public HareketYeri getHareketHedefi();
    public void setHareketHedefi(HareketYeri hareketHedefi);
    public HareketYeri getHareketKaynagi();
    public void setHareketKaynagi(HareketYeri hareketKaynagi);    
    public Date getHareketTarihi();
    public void setHareketTarihi(Date hareketTarihi);
    public void hareketiGerceklestir();
    
}
