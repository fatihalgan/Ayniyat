package com.iztek.ayniyat.malzemehareketleri.domain;

import com.iztek.ayniyat.kategori.IKopyalanabilir;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;

/**
 * @author Cagdas CIRIT
 **/
public interface IDemirbasMalzeme extends IKopyalanabilir, IMalzeme{
    public DemirbasNo getDemirbasNo();
	public IZimmetAlan getZimmetSahibi();
	public void setZimmetSahibi(IZimmetAlan zimmetSahibi);  
	public void addDemirbasNo(String demirbasNo);
	
}
