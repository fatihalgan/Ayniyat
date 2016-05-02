package com.iztek.ayniyat.util.uiservice;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.Bina;
import com.iztek.ayniyat.yerlesim.domain.Birim;
import com.iztek.ayniyat.yerlesim.domain.Oda;
import com.iztek.ayniyat.yerlesim.domain.Personel;

public class MenuItemNameFactory {
	/**
	 * @author fusun
	 */

	public static final String BIRIM_EKLE = "Birim Ekle";
	public static final String AMBAR_EKLE = "Ambar Ekle";
	public static final String BINA_EKLE = "Bina Ekle";
	public static final String ODA_EKLE = "Oda Ekle";
	public static final String PERSONEL_EKLE = "Personel Ekle";
	public static final String SIL = "Sil";
	public static final String DUZENLE = "Düzenle";
	public static final String KES = "Kes";
	public static final String KOPYALA = "Kopyala";
	public static final String YAPISTIR = "Yapýþtýr";
	public static final String DEMIRBAS_GORUNTULE = "Demirbaþ Görüntüle";
	public static final String SEPERATOR = "Seperator";
	public static final String GRUP_EKLE = "Grup Ekle";
	public static final String MALZEME_EKLE = "Malzeme Ekle";
	
	public static String[] getMenuItems(IKategorilendirilebilir kategori){
		if (kategori instanceof Birim)
			return new String[] {BIRIM_EKLE,BINA_EKLE,ODA_EKLE,AMBAR_EKLE,SEPERATOR,SIL,DUZENLE,SEPERATOR,KES,KOPYALA,YAPISTIR,SEPERATOR,DEMIRBAS_GORUNTULE};
		else if (kategori instanceof Bina)
			return new String[] {ODA_EKLE,SEPERATOR,SIL,DUZENLE,SEPERATOR,KES,KOPYALA,YAPISTIR,SEPERATOR,DEMIRBAS_GORUNTULE};
		else if (kategori instanceof Oda)
			return new String[] {PERSONEL_EKLE,SEPERATOR,SIL,DUZENLE,SEPERATOR,KES,KOPYALA,YAPISTIR,SEPERATOR,DEMIRBAS_GORUNTULE};
		else if (kategori instanceof Ambar)
			return new String[] {AMBAR_EKLE,PERSONEL_EKLE,SEPERATOR,SIL,DUZENLE,SEPERATOR,KES,KOPYALA,YAPISTIR,SEPERATOR,DEMIRBAS_GORUNTULE};
		else if (kategori instanceof Personel)
			return new String[] {SIL,DUZENLE,SEPERATOR,KES,KOPYALA,SEPERATOR,DEMIRBAS_GORUNTULE};
		else if (kategori instanceof IMalzemeTanimi)
			return new String[] {SIL,DUZENLE,SEPERATOR,KES,KOPYALA};
		else 
			return new String[] {GRUP_EKLE,MALZEME_EKLE,SEPERATOR,SIL,DUZENLE,SEPERATOR,KES,KOPYALA,YAPISTIR};
		}
}
