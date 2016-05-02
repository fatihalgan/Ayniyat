package com.iztek.ayniyat.model;

import java.util.Vector;

public class NiteliklerTableModel extends AyniyatTableModel {

	
	public NiteliklerTableModel(String[] columnNames) {
		super(columnNames);
		}

	public NiteliklerTableModel(String[] columnNames, Vector data) {
		super(columnNames, data);
		}
	   public boolean isCellEditable(int row, int col)
	    {
	    return col == 1;
	    }

}
