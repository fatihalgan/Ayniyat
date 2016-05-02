package com.iztek.ayniyat.kategori.service;

import java.util.Collection;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.persistence.IKategoriDAO;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

/**
 * @author Cagdas CIRIT
 **/
public abstract class AbstractKategoriManager {
	
	public static void createKategori(IKategorilendirilebilir parent, IKategorilendirilebilir child) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		try {
			if(parent == null){
			    session.save(child);
			}else {
				session.lock(parent,LockMode.UPGRADE);
				parent.addKategori(child);
				session.saveOrUpdate(parent);
			}
			HibernateUtil.commitTransaction();
		} catch(Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public static void deleteKategori(IKategorilendirilebilir kategori){
		Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    try {	
	    		session.lock(kategori, LockMode.UPGRADE);
	    		if(!kategori.isDeletable()) throw new IllegalStateException("Bu kategori altýnda tanýmlanmýþ giriþler olduðu için silinemez.!");
    		   	IKategorilendirilebilir parent = kategori.getAnaKategori();
    		   	if (parent!=null){
	        		session.lock(parent, LockMode.UPGRADE);
	        		parent.getAltKategoriler().remove(kategori);
	        	}	
    	        session.delete(kategori);
	    	    HibernateUtil.commitTransaction();
	    } catch(Throwable t) {
	        HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
	    } finally {
	        HibernateUtil.closeSession();
	    }
	}
	
	public static void updateKategori(IKategorilendirilebilir kategori) {
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		try {
			session.saveOrUpdate(kategori);
			HibernateUtil.commitTransaction();
		} catch(StaleObjectStateException se) {
			HibernateUtil.rollbackTransaction();
			throw new RuntimeException("Bu Kayýt Bir Baþka Kullanýcý Tarafýndan Deðiþtirilmiþ..");
		} catch(Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * @author Umit Akyol
	 */
	public static void copyKategori(IKategorilendirilebilir destination, IKategorilendirilebilir source) {
	    boolean success=true;
	    KategoriMergeStateManager stateManager = new KategoriMergeStateManager();
		Session session = HibernateUtil.getSession();
		HibernateUtil.beginTransaction();
		try {
		    session.lock(source,LockMode.UPGRADE);
			session.lock(destination, LockMode.UPGRADE);
			stateManager.putState(destination,destination.getMemento());
			destination.mergeKategori((IKategorilendirilebilir) source.copy(),stateManager);
			HibernateUtil.commitTransaction();
			success=true;
		}catch(IllegalAccessException e){
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(e.getLocalizedMessage());
		    throw new IllegalStateException(e.getLocalizedMessage());
		}catch(IllegalStateException e){
		    HibernateUtil.rollbackTransaction();
			Debug.instance().println(e);
			stateManager.undo(destination);
			success=false;
			throw new IllegalStateException(e.getLocalizedMessage());
		}catch(Throwable t) {
			HibernateUtil.rollbackTransaction();
			Debug.instance().println(t);
			throw new RuntimeException(t);
		}finally {
			HibernateUtil.closeSession();
			if(!success)
			    updateKategori(destination);
		}
	}
	
	/**
	 * @author Umit Akyol
	 */
	public static void cutKategori(IKategorilendirilebilir destination, IKategorilendirilebilir source) {
	    boolean success=false;
	    KategoriMergeStateManager stateManager = new KategoriMergeStateManager();
	    IKategorilendirilebilir parent = source.getAnaKategori();
	    Session session = HibernateUtil.getSession();
	    HibernateUtil.beginTransaction();
	    	    
		try {
		    session.lock(parent, LockMode.UPGRADE);
			session.lock(destination, LockMode.UPGRADE);
			session.lock(source, LockMode.UPGRADE);
			stateManager.putState(source,source.getMemento());
		    stateManager.putState(destination,destination.getMemento());
		    destination.mergeKategori(source,stateManager);
		    //source ve destination ayný olamaz eger merge islemi basarýyla gerceklestirilmisse 
		    //ve source un altKategorileri tasýnmýssa source silinmeli 
		    if(source.getAnaKategori() == parent){
		        parent.removeKategori(source);
		        session.delete(source);
		    }
			HibernateUtil.commitTransaction();
			success=true;
		}catch(IllegalStateException e){
		    HibernateUtil.rollbackTransaction();
		    Debug.instance().println(e);
		    stateManager.undo(destination);
			stateManager.undo(source);
			success=false;
			throw new IllegalStateException(e.getLocalizedMessage());
		}catch(Throwable t) {
		    HibernateUtil.rollbackTransaction();
		    Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
			if(!success){
			    updateKategori(source);
				updateKategori(destination);  
			}
		}
    }

	public static IKategorilendirilebilir findRootKategori(String kategoriType) {
	    IKategorilendirilebilir returnVal = null;
	    Session session = HibernateUtil.getSession();
	    try {
	        returnVal = getKategoriPersister(kategoriType).findRootKategori();
	    } catch(Throwable t) {
	        Debug.instance().println(t);
	        throw new RuntimeException(t);
	    } finally {
	        HibernateUtil.closeSession();
	    }
	    return returnVal;
	}
	
	public static IKategoriDAO getKategoriPersister(String persisterType) {
		if(persisterType.equals(DAOFactory.MALZEME_TANIMI)) {
			return DAOFactory.getMalzemeTanimiDAO();
		} else if(persisterType.equals(DAOFactory.YERLESIM)) {
			return DAOFactory.getYerlesimDAO();
		}
		return null;
	}

	public static Collection findKategoriByMatchingTanim(String tanim, String kategoriType) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = getKategoriPersister(kategoriType).findKategoriByMatchingTanim(tanim);
		} catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}
	/**
	 *  Sevgi Uslu
	 */
	public static Collection findKategoriByMatchingTanimIgnoreCase(String tanim, String kategoriType) {
		Session session = HibernateUtil.getSession();
		Collection returnVal = null;
		try {
			returnVal = getKategoriPersister(kategoriType).findKategoriByMatchingTanimIgnoreCase(tanim);
		} catch(Throwable t) {
			Debug.instance().println(t);
			throw new RuntimeException(t);
		} finally {
			HibernateUtil.closeSession();
		}
		return returnVal;
	}	
}
