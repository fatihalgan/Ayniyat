package com.iztek.ayniyat.yerlesim.domain;

import java.util.Set;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;

/**
 * @author Cagdas CIRIT
 **/
public interface IZimmetAlan {
    public void addDemirbasMalzeme(IDemirbasMalzeme demirbas);
    public void removeDemirbasMalzeme(IDemirbasMalzeme demirbas);
    public boolean hasDemirbas(IDemirbasMalzeme demirbas);
    public IDemirbasMalzeme getZimmetliDemirbasByNodeValue(String nodeValue);
    public Set getZimmetliMalzemeler();
    public String getTanim();
}
