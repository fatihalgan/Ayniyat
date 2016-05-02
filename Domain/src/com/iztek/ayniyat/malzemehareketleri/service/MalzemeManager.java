package com.iztek.ayniyat.malzemehareketleri.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.util.persistence.AyniyatTableModelData;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class MalzemeManager {

	public static void createMalzeme(IMalzeme malzeme) {
		Session session = HibernateUtil.getSession();		
		HibernateUtil.beginTransaction();		
		try {
			session.save(malzeme);
			HibernateUtil.commitTransaction();
		} catch (StaleObjectStateException se) {
			HibernateUtil.rollbackTransaction();
			throw new RuntimeException(
					"Bu Kayýt Bir Baþka Kullanýcý Tarafýndan Deðiþtirilmiþ..");
		} catch (Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	// FIXME delete methodu olacak mý???
	// olacaksa tekrar implementasyonuna bakýlacak!!!
	public static void deleteMalzeme(IMalzeme malzeme) {
		Set malzemeHareketleri = malzeme.getMalzemeHareketleri();
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		try {
			session.lock(malzeme, LockMode.UPGRADE);
			Iterator iterator = malzemeHareketleri.iterator();
			while (iterator.hasNext()) {
				AbstractMalzemeHareketi malzemeHareketi = (AbstractMalzemeHareketi) iterator
						.next();
				session.load(malzemeHareketi, LockMode.UPGRADE);
				malzemeHareketi.removeMalzeme(malzeme);
			}
			session.delete(malzeme);
			HibernateUtil.commitTransaction();
		} catch (Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}
	public static void updateMalzemeDemirbasNo(AbstractMalzeme malzeme,String demirbasNo){
		Session session = HibernateUtil.getSession();		
		HibernateUtil.beginTransaction();		
		try {
			session.lock(malzeme, LockMode.UPGRADE);
			malzeme.getDemirbasNolari().add(demirbasNo);
			session.saveOrUpdate(malzeme);
			HibernateUtil.commitTransaction();
		} catch (StaleObjectStateException se) {
			HibernateUtil.rollbackTransaction();
			throw new RuntimeException(
					"Bu Kayýt Bir Baþka Kullanýcý Tarafýndan Deðiþtirilmiþ..");
		} catch (Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}

		
	public static void updateMalzeme(IMalzeme malzeme, Set nitelikDegerleri) {
		Session session = HibernateUtil.getSession();		
		HibernateUtil.beginTransaction();		
		try {
			session.lock(malzeme, LockMode.UPGRADE);
			if(nitelikDegerleri != null) {
				malzeme.getNitelikDegerleri().clear();
				malzeme.getNitelikDegerleri().addAll(nitelikDegerleri);
			}
			
			session.saveOrUpdate(malzeme);
			HibernateUtil.commitTransaction();
		} catch (StaleObjectStateException se) {
			HibernateUtil.rollbackTransaction();
			throw new RuntimeException(
					"Bu Kayýt Bir Baþka Kullanýcý Tarafýndan Deðiþtirilmiþ..");
		} catch (Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public static IDemirbasMalzeme findDemirbasByDemirbasNo(String demirbasNo) {
		Session session = HibernateUtil.getSession();
		IDemirbasMalzeme returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO().findDemirbasByDemirbasNo(
					demirbasNo);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection getNitelikTaniminaAitFarkliDegerler(
			AbstractMalzemeTanimi malzemeTanimi, String nitelikAdi) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO()
					.getNitelikTaniminaAitFarkliDegerler(malzemeTanimi,
							nitelikAdi);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection getDemirbaslarNitelikDegeriBelirlenmis(
			AbstractMalzemeTanimi malzemeTanimi, HashMap nitelikler,
			IZimmetAlan zimmetAlan) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO()
					.getDemirbaslarNitelikDegeriBelirlenmis(malzemeTanimi,
							nitelikler, zimmetAlan);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection getDemirbaslarByState(IMalzemeState state) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO().getDemirbaslarByState(state);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection getMalzemeTanimlariVisibleToKullanici(
			String kullanici) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO()
					.getMalzemeTanimlariVisibleToKullanici(kullanici);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	/**
	 * 
	 * Füsun Çetin
	 */

	public static Collection bozukMalzemeSorgulamabyState() {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO().bozukMalzemeSorgulama();
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection zayiMalzemeSorgulama() {
		Session session = HibernateUtil.getSession();

		Collection returnVal = null;

		try {
			returnVal = DAOFactory.getMalzemeDAO().zayiMalzemeSorgulama();
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection terkinMalzemeSorgulama() {
		Session session = HibernateUtil.getSession();

		Collection returnVal = null;

		try {
			returnVal = DAOFactory.getMalzemeDAO().terkinMalzemeSorgulama();
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection AmbarStokSorgulama(Ambar ambar) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO().ambarStokSorgulama(ambar);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}
	
	/**
	 * Sevgi USLU
	 */
	public static Collection getDemirbaslarByMalzemeTanimi(
			AbstractMalzemeTanimi malzemeTanimi) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO()
					.getDemirbaslarByMalzemeTanimi(malzemeTanimi);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection getZimmetliDemirbaslarByMalzemeTanimi(
			AbstractMalzemeTanimi malzemeTanimi) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO()
					.getZimmetliDemirbaslarByMalzemeTanimi(malzemeTanimi);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}
	
	public static Collection getDemirbaslarByZimmetAlan(IZimmetAlan zimmetAlan) {
		Session session = HibernateUtil.getSession();
		Vector returnVal = new Vector();
		try {
			session.lock(zimmetAlan,LockMode.NONE);
			Collection temp = zimmetAlan.getZimmetliMalzemeler();
			for (int i = 0; i < temp.size(); i++) {
				IMalzemeState state=((AbstractMalzeme)(temp.toArray()[i])).getState();
				if (state.getType().equals(IMalzemeState.ZIMMETLI)){
					returnVal.add(temp.toArray()[i]);
				}
			}
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection getTransferMalzemeHareketleri(IZimmetAlan hedefZimmetAlan, IZimmetAlan kaynakZimmetAlan) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = DAOFactory.getMalzemeDAO()
					.getTransferMalzemeHareketleri(hedefZimmetAlan,
							kaynakZimmetAlan);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}

	public static Collection getMalzemeTanimi(AyniyatTableModelData tableData) {
		Session session = HibernateUtil.getSession();
		Collection malzemeTanimi = null;
		try {
			malzemeTanimi = DAOFactory.getMalzemeDAO().getMalzemeTanimi(
					tableData);
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return malzemeTanimi;
	}
	public static Collection getAltKategorilerindekiZimmetliMalzemeler(AbstractZimmetAlan zimmetAlan){
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try{
			session.lock(zimmetAlan,LockMode.READ);
			returnVal=zimmetAlan.getAltKategorilerindekiZimmetliMalzemeler();
		} catch (Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}
}
