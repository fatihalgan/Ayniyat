package com.iztek.ayniyat.model;

import org.hibernate.LockMode;
import org.hibernate.Session;
import com.iztek.ayniyat.event.IKategoriEventListener;
import com.iztek.ayniyat.event.KategoriCutEvent;
import com.iztek.ayniyat.event.KategoriEvent;
import com.iztek.ayniyat.event.KategoriRemoveEvent;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.model.AbstractTreeModel;
import com.iztek.util.persistence.HibernateUtil;
/**
 * @author Cagdas CIRIT
 */
public class AyniyatTreeModel extends AbstractTreeModel implements IKategoriEventListener{

	IKategorilendirilebilir rootNode = null;

	public AyniyatTreeModel() {	}
	
	public AyniyatTreeModel(IKategorilendirilebilir root) {
		this.rootNode = root;
	}

	public Object getRoot() {
		return rootNode;
	}

	public Object getChild(Object parent, int index) {
		IKategorilendirilebilir newChild = (IKategorilendirilebilir)((IKategorilendirilebilir)parent).getAltKategoriler().toArray()[index];
        return newChild;
	}

	public int getChildCount(Object parent) {
		Session newSession = HibernateUtil.getSession();
        newSession.lock(parent,LockMode.READ);
        int size = ((IKategorilendirilebilir)parent).getAltKategoriler().size();
        HibernateUtil.closeSession();
        return size;
	}


	public boolean isLeaf(Object node) {
		if(getChildCount(node)==0) return true;
        return false;
	}

	public int getIndexOfChild(Object parent, Object child) {
		IKategorilendirilebilir anaKategori = (IKategorilendirilebilir)parent;
		IKategorilendirilebilir altKategori = (IKategorilendirilebilir)child;
		Object[] altKategoriler = anaKategori.getAltKategoriler().toArray();
		for(int i = 0; i < altKategoriler.length; i++) {
			IKategorilendirilebilir temp = (IKategorilendirilebilir)altKategoriler[i];
			if(temp.equals(child)) return i;
		}
		return -1;
	}

    protected Object getParent(Object child) {
        return ((IKategorilendirilebilir) child).getAnaKategori();
    }

	public void addKategori(KategoriEvent kategoriEvent) {
    	nodeInserted(kategoriEvent.getSourceNode(),kategoriEvent.getDestinationNode());		
	}

	public void removeKategori(KategoriRemoveEvent kategoriRemoveEvent) {
		nodeRemoved(kategoriRemoveEvent.getSourceNode(),kategoriRemoveEvent.getDestinationNode(),kategoriRemoveEvent.getIndexOfChildNode());
	}

	public void changeKategori(KategoriEvent kategoriEvent) {
		nodeChanged(kategoriEvent.getDestinationNode());
		if (kategoriEvent.getDestinationNode().getAnaKategori()==null)//change root
			reload(kategoriEvent.getDestinationNode());
	}

	public void copyKategori(KategoriEvent kategoriEvent) {
		reload(kategoriEvent.getDestinationNode());	
	}

	public void cutKategori(KategoriCutEvent kategoriCutEvent) {
		if (kategoriCutEvent.getCommitOperation()){
			nodeRemoved(kategoriCutEvent.getOldParentNode(),kategoriCutEvent.getSourceNode(),kategoriCutEvent.getIndexOfChildNode());
			reload(kategoriCutEvent.getDestinationNode());	
		}
	}
}
