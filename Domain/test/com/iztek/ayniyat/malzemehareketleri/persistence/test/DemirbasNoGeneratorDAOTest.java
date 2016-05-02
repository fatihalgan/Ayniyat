package com.iztek.ayniyat.malzemehareketleri.persistence.test;

import com.iztek.ayniyat.test.MainPersistedTestCase;
import com.iztek.util.persistence.DAOFactory;

/**
 * @author Cagdas CIRIT
 **/
public class DemirbasNoGeneratorDAOTest extends MainPersistedTestCase{
	public void testGetSiradakiDemirbasNumarasi(){
		assertTrue(DAOFactory.getDemirbasNoGenDAO().getSiradakiDemirbasNumarasi().getSiraNo().longValue()>0);
	}
}
