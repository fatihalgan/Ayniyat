package com.iztek.ayniyat.malzemehareketleri.domain;

import java.util.Date;

import com.iztek.ayniyat.malzemehareketleri.util.BelgeNoGenerator;
import com.iztek.ayniyat.yerlesim.domain.Personel;

/**
 * @author Umit Akyol
 *  
 */
public abstract class AbstractMalzemeHareketFisi {
    private Long id = null;
    private int version;
    private BelgeNoGenerator belgeNo = null;
    private Personel onayVeren = null;
    private String teslimEden = null;
    private IMalzemeHareketi owningMalzemeHareketi = null;
    private Date duzenlemeTarihi = null;
    private MalzemeFisPahasi paha;
	
	public AbstractMalzemeHareketFisi(){
		belgeNo = new BelgeNoGenerator();
		paha = new MalzemeFisPahasi();
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

    public BelgeNoGenerator getBelgeNo() {
		return belgeNo;
	}

	public void setBelgeNo(BelgeNoGenerator belgeNo) {
		this.belgeNo = belgeNo;
	}

	public Date getDuzenlemeTarihi() {
        return duzenlemeTarihi;
    }

    public void setDuzenlemeTarihi(Date duzenlemeTarihi) {
        this.duzenlemeTarihi = duzenlemeTarihi;
    }

    public Personel getOnayVeren() {
        return onayVeren;
    }

    public void setOnayVeren(Personel onayVeren) {
        this.onayVeren = onayVeren;
    }

    public String getTeslimEden() {
        return teslimEden;
    }

    public void setTeslimEden(String teslimEden) {
        this.teslimEden = teslimEden;
    }

    public IMalzemeHareketi getOwningMalzemeHareketi() {
        return owningMalzemeHareketi;
    }

    public void setOwningMalzemeHareketi(IMalzemeHareketi malzemeHareketi) {
        this.owningMalzemeHareketi = malzemeHareketi;
    }
    
    public MalzemeFisPahasi getPaha() {
		return paha;
	}

	public void setPaha(MalzemeFisPahasi paha) {
		this.paha = paha;
		paha.setMalzemeler(getOwningMalzemeHareketi().getMalzemeler());
	}
}