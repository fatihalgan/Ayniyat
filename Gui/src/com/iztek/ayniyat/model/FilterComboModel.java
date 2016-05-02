package com.iztek.ayniyat.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class FilterComboModel implements ComboBoxModel {

	private List data;
	
	private int selected ;
	
	public FilterComboModel(){
		data=new ArrayList();
		selected=0;
	}
	public void setModelData(Collection collection,int column){
		Set set = new HashSet();
		Iterator iter=collection.iterator();
		while(iter.hasNext()){
			Vector	row = (Vector)iter.next();
			String tanim=(String)row.elementAt(column);	
			set.add(tanim);
		}
		Iterator it = set.iterator();
		data.add("All");
	    while (it.hasNext()) {
	        data.add(it.next());
	    }
	}
	
	public void setSelectedItem(Object o) {
		selected = data.indexOf(o);
	}

	public Object getSelectedItem() {
		if(!data.isEmpty()){
		return data.get(selected);
		}
		else return null;
	}

	public int getSize() {
		return data.size();
	}

	public Object getElementAt(int i) {
		return data.get(i);
	}
	public void addListDataListener(ListDataListener listener){
		
	}
	public void removeListDataListener(ListDataListener listener){
		
	}
	public void removeAll() {
		if(data.size()>0){
			Vector removed=new Vector(data.size());
			for(int i=0;i<data.size();i++){
				removed.add(data.get(i));
			}
			data.removeAll(removed);
			selected=0;
		}
	}
}
