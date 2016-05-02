package com.iztek.ayniyat.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.iztek.ayniyat.sorgular.zimmetsorgulama.gui.ZimmetSorgulamaFilterData;
/**
 * @author fusun
 *  */
public class ZimmetSorguTableModel extends DefaultTableModel{
	ZimmetSorgulamaFilterData filterData;
	private  String[] columnNames;
	private String filter = "All";
	
	public ZimmetSorguTableModel(String[] columnNames){
		this.columnNames = columnNames;
	}
	public void setModelData(Collection Data) {
		Iterator iter = Data.iterator();
		while(iter.hasNext()){
				Vector row = new Vector();
				filterData = (ZimmetSorgulamaFilterData)iter.next();
	//			row.add(filterData.getDemirbasNo());
				row.add(filterData.getTanim());
				row.add(filterData.getBirim());
				row.add(filterData.getZimmetSahibi());
				addRow(row);
			}
		fireTableDataChanged();
	
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int c) {
		return columnNames[c];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public synchronized void setFilter(String filter) {
		if (!filter.equals("All")) {
			this.filter = filter;
			fireTableDataChanged();
		}
	}

	public String getFilter() {
		return filter;
	}
	public String[] getColumnNames() {
		return columnNames;
	}
	public void removeAllRows(){
    	if (getRowCount()>0)
    		removeRows(0,getRowCount()-1);
    		
    }
 public Vector removeRows(int firstRow, int lastRow){
    	Vector removedRows = new Vector(lastRow-firstRow+1);
		
    	for (int i = firstRow; i < lastRow+1; i++) 
			removedRows.add(dataVector.get(i));
    	
    	dataVector.removeAll(removedRows);
    	fireTableRowsDeleted(firstRow,lastRow);  	
    	return removedRows;	
    }
 public Object getValueAt(int row, int column) {
	 
		return ((Vector)dataVector.get(row)).get(column);
	
	}

}

