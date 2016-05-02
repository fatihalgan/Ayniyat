package com.iztek.ayniyat.kategori;

import java.util.Comparator;
import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;

/**
 * @author Cagdas CIRIT
 **/
public class NitelikDegeriComparator implements Comparator{
	public int compare(Object nitelikA, Object nitelikB) {
	    int result = ((NitelikDegeri)nitelikA).getNitelikAdi().compareToIgnoreCase(((NitelikDegeri)nitelikB).getNitelikAdi());
		if (result==0)
		    return ((NitelikDegeri)nitelikA).getNitelikDegeri().compareToIgnoreCase(((NitelikDegeri)nitelikB).getNitelikDegeri());
		return result;	    
	}
}
