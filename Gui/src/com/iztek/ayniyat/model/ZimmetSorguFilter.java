package com.iztek.ayniyat.model;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
/**
 * @author fusun
 *  */
public class ZimmetSorguFilter extends AbstractTableModel
	implements TableModelListener {

	protected ArrayList indexList = new ArrayList(0);

	protected String filter = "All";
	protected TableModel model;

	public TableModel getModel() {
		return model;
	}

	public void setModel(TableModel model) {
		this.model = model;
		model.addTableModelListener(this);
	}

	public ZimmetSorguFilter(TableModel model) {
		setModel(model);
	}

	public synchronized void setFilter(String filter,int ColIndex) {

  		if (model instanceof ZimmetSorguTableModel)
			 ((ZimmetSorguTableModel) model).setFilter(filter);
             
		this.filter = filter;
		int rowCount = model.getRowCount();

		indexList.clear();

		// Iterate through the model
		for (int z = 0; z < rowCount; z++) {
			String row = (String) model.getValueAt(z, ColIndex);
			if (filter.equals("All")) {
				indexList.add(new Integer(z));
			} else if (filter.equals(row))
				indexList.add(new Integer(z));
		}

		// Tell the component that the data have changed
		this.fireTableDataChanged();
	}

	public String getFilter() {
		return filter;
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return indexList.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return model.getColumnCount();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		int newIndex = ((Integer) indexList.get(rowIndex)).intValue();
		return model.getValueAt(newIndex, columnIndex);
	}
	public String getColumnName(int aColumn) {
		return model.getColumnName(aColumn);
	}

	public Class getColumnClass(int aColumn) {
		return model.getColumnClass(aColumn);
	}

	public boolean isCellEditable(int row, int column) {
		return model.isCellEditable(row, column);
	}

	public synchronized void reallocateIndexes() {

		int rowCount = model.getRowCount();

		indexList.clear();

		// Iterate through the model

		for (int z = 0; z < rowCount; z++) {
			String planet = (String) model.getValueAt(z, 1);
			if (filter.equals("All")) {
				indexList.add(new Integer(z));
			} else if (filter.equals(planet))
				indexList.add(new Integer(z));
		}
	}

	public void tableChanged(TableModelEvent e) {
		reallocateIndexes();
		fireTableChanged(e);
	}
	
}

