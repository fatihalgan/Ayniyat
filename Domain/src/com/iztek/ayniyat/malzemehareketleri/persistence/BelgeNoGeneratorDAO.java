package com.iztek.ayniyat.malzemehareketleri.persistence;

import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.iztek.ayniyat.malzemehareketleri.util.BelgeNoGenerator;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.persistence.InfrastructureException;

/**
 * @author Cagdas CIRIT
 */
public class BelgeNoGeneratorDAO implements IBelgeNoGeneratorDAO{
	public BelgeNoGenerator getSiradakiBelgeNumarasi(){
		BelgeNoGenerator siradakiBelgeNumarasi = null;
        Session session = HibernateUtil.getSession();
		session.setFlushMode(FlushMode.COMMIT);
	    try {
	        List list = session.createQuery("from com.iztek.ayniyat.malzemehareketleri.util.BelgeNoGenerator as belgeNoGenerator").list(); 
	        if (list.size()>0)
				siradakiBelgeNumarasi = (BelgeNoGenerator) list.toArray()[0];
	    } catch(HibernateException he) {
	        throw new InfrastructureException(he.getMessage());
	    }
	    return siradakiBelgeNumarasi;
	}
}
