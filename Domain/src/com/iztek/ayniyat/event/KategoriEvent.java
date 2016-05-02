package com.iztek.ayniyat.event;

import java.util.EventObject;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

public class KategoriEvent extends EventObject{
	private IKategorilendirilebilir sourceNode;
	private IKategorilendirilebilir destinationNode;

	public KategoriEvent(Object sourceOfEvent,IKategorilendirilebilir source,IKategorilendirilebilir destination) {
		super(sourceOfEvent);
		this.sourceNode = source;
		this.destinationNode = destination;;
	}
		
	public IKategorilendirilebilir getDestinationNode() {
		return destinationNode;
	}
	
	public void setDestinationNode(IKategorilendirilebilir destinationNode) {
		this.destinationNode = destinationNode;
	}
	
	public IKategorilendirilebilir getSourceNode() {
		return sourceNode;
	}
	
	public void setSourceNode(IKategorilendirilebilir sourceNode) {
		this.sourceNode = sourceNode;
	}
}
