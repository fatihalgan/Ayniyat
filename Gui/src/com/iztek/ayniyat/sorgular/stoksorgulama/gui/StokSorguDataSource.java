package com.iztek.ayniyat.sorgular.stoksorgulama.gui;

import java.util.Collection;
import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import com.iztek.util.persistence.AyniyatTableModelData;

public class StokSorguDataSource implements JRDataSource{
	Collection collection=null;
	private int index = -1;
	private Object[] obj;
	
	public StokSorguDataSource(Collection coll){
		
		this.collection=coll;
		initFields();
	}
	
	public boolean next() throws JRException {
		index++;
		return (index < obj.length);
	}

	public Object getFieldValue(JRField field) {
		Object value = null;
		
		String fieldName = field.getName();
		Vector row =(Vector) obj[index];
	 
		 if("Ambar".equals(fieldName)) value=row.elementAt(0);
		 else if("MalzemeTanimi".equals(fieldName)) value=row.elementAt(2);
		 else if("MalzemeKodu".equals(fieldName)) value=row.elementAt(1);
		 else if("Miktar".equals(fieldName)) value=(row.elementAt(3)).toString();
		 else if("Birim".equals(fieldName)) value=row.elementAt(4);
		return value;
	}
	
	private void initFields() {
		obj = collection.toArray();		
		
	}
	

}
