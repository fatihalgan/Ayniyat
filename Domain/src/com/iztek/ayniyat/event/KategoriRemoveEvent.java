package com.iztek.ayniyat.event;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Cagdas CIRIT
 **/
public class KategoriRemoveEvent extends KategoriEvent{
	private int indexOfChildNode;
	
	public KategoriRemoveEvent(Object sourceOfEvent, IKategorilendirilebilir source, IKategorilendirilebilir destination, int indexOfChildNode) {
		super(sourceOfEvent, source, destination);
		this.indexOfChildNode = indexOfChildNode;
	}
	
	public int getIndexOfChildNode() {
		return indexOfChildNode;
	}
	
	public void setIndexOfChildNode(int indexOfChildNode) {
		this.indexOfChildNode = indexOfChildNode;
	}
}
