package com.iztek.ayniyat.gui.service;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.tree.TreePath;
import org.hibernate.LockMode;
import org.hibernate.Session;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.util.persistence.HibernateUtil;
/**
 * @author Sevgi ayniyat
 */
public class Search {
	
	/*
	 * Collection'ýn ilk elemaný için TreePath oluþturur
	 */
	public static TreePath findNext(Collection result){
		TreePath treePath=null;
		ArrayList path=new ArrayList();
		if(result.size()>0){
			IKategorilendirilebilir child=(IKategorilendirilebilir) result.toArray()[0];
			result.remove(child);
			findPath(path,child);
			treePath=getTreePath(path);
		}
		return treePath;
	}
	
	/*
	 * AnaKategorileri gezerek ArrayList bir path oluþturur
	 * liste child'dan root'a doðru sýralýdýr
	 */
	public static void findPath(ArrayList path,IKategorilendirilebilir child){
		IKategorilendirilebilir parent=null;
		if(child !=null){
		    Session session = HibernateUtil.getSession();
		    session.lock(child, LockMode.NONE);
			path.add(child);
			parent=child.getAnaKategori();
			HibernateUtil.closeSession();
			findPath(path,parent);		
		}
		
	}
	
	/*
	 * child'dan root'a sýralý
	 * ArrayList olarak verilen path'i TreePath'a çevirir 
	 */
	public static TreePath getTreePath(ArrayList path){
		IKategorilendirilebilir root=(IKategorilendirilebilir)path.get(path.size()-1);
		TreePath treePath=new TreePath(root);
		Session session = HibernateUtil.getSession();
		for (int i = path.size()-2; i >= 0; i--) {
			IKategorilendirilebilir child=(IKategorilendirilebilir) path.get(i);
			session.lock(root, LockMode.NONE);
			for (int j = 0; j < root.getAltKategoriler().size(); j++) {
			    if(((IKategorilendirilebilir) root.getAltKategoriler().toArray()[j]).equals(child)){
					root=(IKategorilendirilebilir) root.getAltKategoriler().toArray()[j];
					treePath=treePath.pathByAddingChild(root);
					j=root.getAltKategoriler().size();
				}
			}
		}	
		HibernateUtil.closeSession();
		return treePath;
	}

}
