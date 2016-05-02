package com.iztek.ayniyat.malzemehareketleri.persistence.test;

import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.util.persistence.DAOFactory;

/**
 * @author Cagdas CIRIT
 **/
public class BelgeNoGeneratorDAOTest extends MainPersistedTestCase{
	public void testGetSiradakiBelgeNumarasi(){
		assertTrue(DAOFactory.getBelgeNoGenDAO().getSiradakiBelgeNumarasi().getBelgeNo().longValue()>0);
	}
}
