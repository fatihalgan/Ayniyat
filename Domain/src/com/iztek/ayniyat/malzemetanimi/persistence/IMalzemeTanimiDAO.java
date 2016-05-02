/*
 * Created on 11.Nis.2005
 */
package com.iztek.ayniyat.malzemetanimi.persistence;

import com.iztek.ayniyat.kategori.persistence.IKategoriDAO;

/**
 * @author Fatih Algan
 */
public interface IMalzemeTanimiDAO extends IKategoriDAO {
	public boolean isMalzemeKodExist(String kod);
}
