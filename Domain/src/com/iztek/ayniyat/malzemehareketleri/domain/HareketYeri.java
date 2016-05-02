package com.iztek.ayniyat.malzemehareketleri.domain;

import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;

/**
 * @author Umit Akyol
 */
public class HareketYeri {
    private Long id = null;
    private int version;
    private IZimmetAlan hareketYeri = null;
    
    public HareketYeri(){}
    
    public HareketYeri(IZimmetAlan hareketYeri){
    	this.hareketYeri = hareketYeri;
    }
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
    public IZimmetAlan getHareketYeri() {
        return hareketYeri;
    }

    public void setHareketYeri(IZimmetAlan hareketYeri) {
        this.hareketYeri = hareketYeri;
    }
}
