package com.iztek.ayniyat.malzemetanimi.domain;

import java.util.Collection;
import java.util.Set;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IMalzemeTanimi extends IKategorilendirilebilir {
    public void addNitelikTanimi(NitelikTanimi nitelik);
    public Set getNitelikTanimlari();
    public boolean hasNitelikTanimi(String nitelik);
    public NitelikTanimi getNitelikTanimi(String nitelik);
    public void removeNitelikTanimi(String nitelik);
    public void removeNitelikTanimi(NitelikTanimi nitelik);
    public boolean equals(final Object other);
	public int hashCode();
	public void retainNitelikTanimlari(Collection tutulacakNitelikler);
    public String getBirim();
}
