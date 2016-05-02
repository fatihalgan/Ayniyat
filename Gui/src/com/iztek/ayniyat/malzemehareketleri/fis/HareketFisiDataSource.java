/*
 * Created on 01.Kas.2005
 *
 */
package com.iztek.ayniyat.malzemehareketleri.fis;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;

/**
 * @author Fatih Algan
 *
 */
public class HareketFisiDataSource implements JRDataSource{
	
	AbstractMalzemeHareketFisi hareketFisi;
	private int index = -1;
	private Object[] obj;
	
	public HareketFisiDataSource(AbstractMalzemeHareketFisi hFisi){
		
		this.hareketFisi=hFisi;
		initFields();
	}
	
	public boolean next() throws JRException {
		index++;
		return (index < obj.length);
	}

	public Object getFieldValue(JRField field) {
		Object value = null;
		
		String fieldName = field.getName();
		DemirbasMalzeme row = (DemirbasMalzeme)obj[index];
	
		 if("DemirbasNo".equals(fieldName)) value=row.getDemirbasNo().toString();
		 else if("Cinsi".equals(fieldName)) value=row.getMalzemeTanimi().getNodeValue();
		 else if("Fiyat".equals(fieldName)) value=row.getPaha().getVergisizBirimFiyat().toString();
		 else if("Birim".equals(fieldName)) value=row.getMalzemeTanimi().getBirim().toString();
		 else if("Tutar".equals(fieldName)) value=row.getPaha().getVergisizBirimFiyat().toString();

		return value;
	}
	
	private void initFields() {
		obj = hareketFisi.getOwningMalzemeHareketi().getMalzemeler().toArray();		
		
	}
	

}
