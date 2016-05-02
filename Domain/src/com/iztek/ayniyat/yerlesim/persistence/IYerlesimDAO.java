package com.iztek.ayniyat.yerlesim.persistence;

import java.util.Collection;

import com.iztek.ayniyat.kategori.persistence.IKategoriDAO;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;

/**
 * @author Cagdas CIRIT
 */
public interface IYerlesimDAO extends IKategoriDAO {
    public Collection findPersonelBySicilNo(String sicilNo);
	public AbstractZimmetAlan findAmbarByTanim(String ambarTanim);
	public Collection getAmbarlar();
}
