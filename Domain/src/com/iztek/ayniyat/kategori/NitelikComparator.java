package com.iztek.ayniyat.kategori;

import java.util.Comparator;
import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
/**
 * @author Cagdas CIRIT
 **/
public class NitelikComparator implements Comparator{
	public int compare(Object nitelikA, Object nitelikB) {
		return ((NitelikTanimi)nitelikA).getNitelikAdi().compareToIgnoreCase(((NitelikTanimi)nitelikB).getNitelikAdi());
	}
}
