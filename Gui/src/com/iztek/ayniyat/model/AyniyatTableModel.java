package com.iztek.ayniyat.model;


import java.util.List;
import java.util.Vector;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.table.AbstractTableModel;
import com.iztek.util.persistence.AyniyatTableModelData;
/**
 * @author Cagdas CIRIT
 **/
public class AyniyatTableModel extends AbstractTableModel {
	private String[] columnNames;
	private Vector data;//contains vectors as rows 
	AyniyatTableModelData tableData;
	
	
	public AyniyatTableModel(String[] columnNames){
		this.columnNames = columnNames;
		data = new Vector();
	}
	
	public AyniyatTableModel(String[] columnNames,Vector data){
		this.columnNames = columnNames;
		this.data = data;
	}
	public Collection getTableData(){
		return data;
	}
	public void fillSorguTable(Collection Data){
		List obj= new Vector();
		Iterator iter = Data.iterator();
		while(iter.hasNext()){
			obj = (List) (iter.next());
			for(int i = 0; i < obj.size(); i++){
				Vector row = new Vector();
				tableData = (AyniyatTableModelData)obj.get(i);
				row.add(tableData.getAmbarTanimi());
				row.add(tableData.getKod());
				row.add(tableData.getTanim());
				row.add(tableData.getAdet());
				row.add(tableData.getBirim());
				addRow2LastRow(row);
			}
		}
	}
		
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public int getRowCount() {
		return data.size();
	}
	
    public String getColumnName(int col) {
        return columnNames[col];
    }

	public Object getValueAt(int row, int column) {
	 
		return ((Vector)data.get(row)).get(column);
	
	}
	
	public AyniyatTableModelData getTableData(int row){
		
		Vector obj=new Vector();
		for(int i=0;i<data.size();i++){
			
		obj.add(new AyniyatTableModelData(((String)((Vector)data.get(i)).get(0)),
								((String)((Vector)data.get(i)).get(1)),
								((String)((Vector)data.get(i)).get(2)),
								((Integer)((Vector)data.get(i)).get(3)),
								((String)((Vector)data.get(i)).get(4))));
		
		}
		return (AyniyatTableModelData) obj.get(row);
		
		
	}
	
	
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    	
    }
    
    public Object removeRow(int row){
    	return removeRows(row,row).firstElement();
    }
    
    public Object removeLastRow(){
    	int lastIndex = getRowCount()-1;
    	if (lastIndex>=0)
    		return removeRows(lastIndex,lastIndex).firstElement();
		return null;
    }
    
    public void removeAllRows(){
    	if (getRowCount()>0)
    		removeRows(0,getRowCount()-1);
    }
    
    //firstRow siliniyor, lastRow siliniyor, arasýndakilerde siliniyor
    public Vector removeRows(int firstRow, int lastRow){
    	Vector removedRows = new Vector(lastRow-firstRow+1);
		
    	for (int i = firstRow; i < lastRow+1; i++) 
			removedRows.add(data.get(i));

    	data.removeAll(removedRows);
    	fireTableRowsDeleted(firstRow,lastRow);  	
    	return removedRows;	
    }
    
    public void addRow(int insertIndex,Vector insertSingleRow){
    	Vector insertData = new Vector(1);
    	insertData.add(insertSingleRow);
    	addRows(insertIndex,insertData);
    }
    
    public void addRow2LastRow(Vector insertSingleRow){
    	int lastIndex = getRowCount();
    	addRow(lastIndex,insertSingleRow);
    }
    
    public void addRows2LastRow(Vector insertData){
    	int lastIndex = getRowCount();
    	addRows(lastIndex,insertData);
    }
    
    public void addRows(int insertIndex,Vector insertData){
    	for (int i = 0; i < insertData.size(); i++) 
    		data.add(insertIndex+i,insertData.get(i));
    	
    	fireTableRowsInserted(insertIndex,insertIndex);
    	
    }
    
    public void setValueAt(Object value, int row, int col) {
        ((Vector)data.get(row)).setElementAt(value, col);
        fireTableCellUpdated(row, col);
    }
 
}
