package com.iztek.ayniyat.event;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Cagdas CIRIT
 **/
public class KategoriCutEvent extends KategoriEvent {
	private IKategorilendirilebilir oldParentNode;
	private int indexOfChildNode;
	private boolean commitOperation = false;
	
	public KategoriCutEvent(Object sourceOfEvent, IKategorilendirilebilir source, IKategorilendirilebilir destination, int indexOfChildNode, IKategorilendirilebilir oldParentNode) {
		super(sourceOfEvent, source, destination);
		this.oldParentNode = oldParentNode;
		this.indexOfChildNode = indexOfChildNode;
	}
	
	public IKategorilendirilebilir getOldParentNode() {
		return oldParentNode;
	}
	
	public void setOldParentNode(IKategorilendirilebilir oldParentNode) {
		this.oldParentNode = oldParentNode;
	}
	
	public int getIndexOfChildNode() {
		return indexOfChildNode;
	}
	
	public void setIndexOfChildNode(int indexOfChildNode) {
		this.indexOfChildNode = indexOfChildNode;
	}
	
	public boolean getCommitOperation() {
		return commitOperation;
	}
	
	public void setCommitOperation(boolean commitOperation) {
		this.commitOperation = commitOperation;
	}
}
