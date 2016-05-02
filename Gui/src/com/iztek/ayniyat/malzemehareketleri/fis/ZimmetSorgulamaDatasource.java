package com.iztek.ayniyat.malzemehareketleri.fis;

import java.util.Collection;
import java.util.Vector;

import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ZimmetSorgulamaDatasource implements JRDataSource{
	
	Collection demirbaslar;
	private int index = -1;
	private Object[] obj;
	
	public ZimmetSorgulamaDatasource(Collection demirbaslar){
		
		this.demirbaslar=demirbaslar;
		initFields();
	}
	
	public boolean next() throws JRException {
		index++;
		return (index < obj.length);
	}

	public Object getFieldValue(JRField field) {
		Object value = null;
		
		String fieldName = field.getName();
		 Vector row= (Vector)obj[index];
	
		 if("demirbasNo".equals(fieldName)) value=((DemirbasMalzeme)row.elementAt(0)).getDemirbasNo().toString();
		 else if("Cinsi".equals(fieldName)) value=row.elementAt(1);
		 else if("Olcek".equals(fieldName)) value=row.elementAt(2);
		 else if("zimmetSahibi".equals(fieldName)) value=row.elementAt(3);

		return value;
	}
	
	private void initFields() {
		obj =demirbaslar.toArray();
		
	}
	

}

