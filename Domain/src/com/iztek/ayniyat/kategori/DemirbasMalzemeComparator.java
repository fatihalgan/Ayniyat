package com.iztek.ayniyat.kategori;

import java.util.Comparator;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;


/**
 * @author Cagdas CIRIT
 **/
public class DemirbasMalzemeComparator implements Comparator{
/*	public int compare(Object demirbasA, Object demirbasB) {
		int compare = ((IDemirbasMalzeme)demirbasA).getMalzemeTanimi().getNodeValue().compareToIgnoreCase(((IDemirbasMalzeme)demirbasB).getMalzemeTanimi().getNodeValue());
		if(compare!=0)
			return compare;
		if( ((IDemirbasMalzeme)demirbasA).getDemirbasNo().getSiraNo()!=null && ((IDemirbasMalzeme)demirbasB).getDemirbasNo().getSiraNo()!=null ){
			if(((IDemirbasMalzeme)demirbasA).getDemirbasNo().equals(((IDemirbasMalzeme)demirbasB).getDemirbasNo()))
				return 0;
			else
				return 1;
		}
		return 1;		
	}*/
	public int compare(Object demirbasA, Object demirbasB) {
		int compare = ((IDemirbasMalzeme)demirbasA).getMalzemeTanimi().getNodeValue().compareToIgnoreCase(((IDemirbasMalzeme)demirbasB).getMalzemeTanimi().getNodeValue());
		if(compare!=0)
			return compare;
		if( ((IDemirbasMalzeme)demirbasA).getDemirbasNo().getSiraNo()!=null && ((IDemirbasMalzeme)demirbasB).getDemirbasNo().getSiraNo()!=null ){
			if(((IDemirbasMalzeme)demirbasA).getDemirbasNo().equals(((IDemirbasMalzeme)demirbasB).getDemirbasNo()))
				return 0;
			else
				return 1;
		}
		return 1;		
	}
}