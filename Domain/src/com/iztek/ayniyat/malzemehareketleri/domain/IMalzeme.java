package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Set;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;

/**
 * @author Cagdas CIRIT
 **/
public interface IMalzeme{
	public IMalzemeTanimi getMalzemeTanimi();
	public void setMalzemeTanimi(IMalzemeTanimi tanim);
	public Set getMalzemeHareketleri();
	public void addMalzemeHareketi(IMalzemeHareketi malzemeHareketi);
	public void removeMalzemeHareketi(IMalzemeHareketi malzemeHareketi);
	public Set getNitelikDegerleri();
	public void setNitelikDegerleri(Set nitelikDegerleri);
	public void addNitelikDegeri(NitelikDegeri nitelikDegeri);
	public void removeNitelikDegeri(String nitelikDegeri);
	public void removeNitelikDegeri(NitelikDegeri nitelikDegeri);
	public NitelikDegeri getNitelikDegeri(String nitelikDegeri);
	public boolean hasNitelikDegeri(String nitelikDegeri);
	public IMalzemeState getState();
	public void setState(IMalzemeState state);
	public void setMalzemeHareketleri(Set malzemeHareketleri); 
	public boolean hasInMalzemeHareketleri(IMalzemeHareketi malzemeHareketi);
	public boolean hasInNitelikDegerleri(NitelikDegeri nitelikDegeri);
	public MalzemePahasi getPaha();
	public void setPaha(MalzemePahasi paha);
}
