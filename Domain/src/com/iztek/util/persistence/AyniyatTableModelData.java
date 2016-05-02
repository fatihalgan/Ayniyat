package com.iztek.util.persistence;


public class AyniyatTableModelData {
	
	
	private String tanim;
	private String kod;
	private String ambarTanimi;
	private Integer adet;
	private String birim;
	
	public AyniyatTableModelData(){
		
	}
	
	public AyniyatTableModelData(String ambarTanimi,String kod,String tanim,Integer adet,String birim ) {
		
		this.adet = adet;
		this.ambarTanimi = ambarTanimi;
		this.birim=birim;
		this.tanim = tanim;
		this.kod=kod;
	}


	public Integer getAdet() {
		return adet;
	}


	public void setAdet(Integer adet) {
		this.adet = adet;
	}


	public String getAmbarTanimi() {
		return ambarTanimi;
	}


	public void setAmbarTanimi(String ambarTanimi) {
		this.ambarTanimi = ambarTanimi;
	}


	
	public String getTanim() {
		return tanim;
	}


	public void setTanim(String tanim) {
		this.tanim = tanim;
	}

	public String getBirim() {
		return birim;
	}

	public void setBirim(String birim) {
		this.birim = birim;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}
	
}