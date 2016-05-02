package com.iztek.ayniyat.malzemehareketleri.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;

/**
 * @author Cagdas CIRIT
 **/
public class DemirbasMalzeme extends AbstractMalzeme implements IDemirbasMalzeme{
	private DemirbasNo demirbasNo;
	private IZimmetAlan zimmetSahibi;
	
	public DemirbasMalzeme(){
		super();
		demirbasNo = new DemirbasNo();
	}
	
	public void setDemirbasNo(DemirbasNo demirbasNo) {
		this.demirbasNo = demirbasNo;
	}

	public DemirbasNo getDemirbasNo() {
		return demirbasNo;
	}

	public IZimmetAlan getZimmetSahibi() {
		return zimmetSahibi;
	}
	
	public void setZimmetSahibi(IZimmetAlan zimmetSahibi) {
		this.zimmetSahibi = zimmetSahibi;
	}
	
/*	public boolean equals(final Object other) {
		if (this == other)
			return true;
		if (!(other instanceof DemirbasMalzeme))
			return false;		
		DemirbasMalzeme castOther =  (DemirbasMalzeme) other;
		return new EqualsBuilder().appendSuper(super.equals(other))
								  .append(getDemirbasNo().getSiraNo(),castOther.getDemirbasNo().getSiraNo())
								  .append(getDemirbasNo().getAltSiraNo(),castOther.getDemirbasNo().getAltSiraNo()).isEquals();
	}*/
	public boolean equals(final Object other) {
		if (this == other)
			return true;
		if (!(other instanceof DemirbasMalzeme))
			return false;		
		DemirbasMalzeme castOther =  (DemirbasMalzeme) other;
		return new EqualsBuilder().appendSuper(super.equals(other))
								  .append(getDemirbasNo().getSiraNo(),castOther.getDemirbasNo().getSiraNo()).isEquals();
	}
/*	public int hashCode() {
		return new HashCodeBuilder(985654123, -858585219).append(super.hashCode())
														  .append(getDemirbasNo().getSiraNo()).append(getDemirbasNo().getAltSiraNo()).toHashCode();
	}*/
	public int hashCode() {
		return new HashCodeBuilder(985654123, -858585219).append(super.hashCode())
														  .append(getDemirbasNo().getSiraNo()).toHashCode();
	}
	public String toString(){
		if(getDemirbasNo().getSiraNo()==null)
			return super.toString();
		return getDemirbasNo().toString();
	}
	public Object copy() throws Exception {
		return null; //TODO? 
	}
}
