/*
 * Created on 02.May.2005
 */
package com.iztek.ayniyat.kategori.persistence;

import java.util.Collection;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Fatih Algan
 */
public interface IKategoriDAO {
	public IKategorilendirilebilir findRootKategori();
	public IKategorilendirilebilir findKategoriById(Long kategoriId);
	public Collection findKategoriByMatchingTanim(String tanimString);
	public Collection findKategoriByMatchingTanimIgnoreCase(String tanimString);
}
