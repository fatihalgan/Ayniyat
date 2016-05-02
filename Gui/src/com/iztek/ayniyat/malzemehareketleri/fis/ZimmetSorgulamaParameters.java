package com.iztek.ayniyat.malzemehareketleri.fis;

import java.util.Map;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

public class ZimmetSorgulamaParameters {
	
public static void commonParameters(Map parameters,String node,int toplam){
		
		parameters.put("demirbasSahibi",node);
		parameters.put("toplam",""+toplam);
}

}
