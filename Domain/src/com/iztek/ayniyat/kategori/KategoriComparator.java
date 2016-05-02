package com.iztek.ayniyat.kategori;

import java.util.Comparator;

import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
/**
 * @author Cagdas CIRIT
 **/
public class KategoriComparator implements Comparator {
	public int compare(Object kategoriA, Object kategoriB) {		
		if ((kategoriA instanceof IMalzemeTanimi) && (kategoriB instanceof IMalzemeTanimi))
			return ((IKategorilendirilebilir)kategoriA).getNodeValue().compareToIgnoreCase(((IKategorilendirilebilir)kategoriB).getNodeValue());
		else if (!(kategoriA instanceof IMalzemeTanimi) && !(kategoriB instanceof IMalzemeTanimi))
			return ((IKategorilendirilebilir)kategoriA).getNodeValue().compareToIgnoreCase(((IKategorilendirilebilir)kategoriB).getNodeValue());
		else {
			if (kategoriA instanceof IMalzemeTanimi)
				return 1;
			else
				return -1;	
		}
	}
}
