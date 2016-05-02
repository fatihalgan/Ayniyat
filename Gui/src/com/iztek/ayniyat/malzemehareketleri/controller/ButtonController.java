package com.iztek.ayniyat.malzemehareketleri.controller;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * @author Cagdas CIRIT
 **/
public class ButtonController implements TableModelListener {
	private TableModel tableModel = null;
	private JButton[] buttons = null;
	private JMenuItem[] menuItems = null;
	
	public ButtonController(TableModel tableModel, JButton[] buttons){
		setTableModel(tableModel);
		this.buttons = buttons;
	}
	
	public ButtonController(TableModel tableModel, JButton[] buttons, JMenuItem[] menuItems){
		setTableModel(tableModel);
		this.buttons = buttons;
		this.menuItems = menuItems;
	}
	
	public void tableChanged(TableModelEvent e) {
		if(tableModel.getRowCount()==0){
			for (int i = 0; i < buttons.length; i++) {
				buttons[i].setEnabled(false);
				if (menuItems!=null)
				    menuItems[i].setEnabled(false);
			}
		}else{
			for (int i = 0; i < buttons.length; i++){ 
				buttons[i].setEnabled(true);
				if (menuItems!=null)
				    menuItems[i].setEnabled(true);
			}
		}
	}

    public void setTableModel(TableModel tableModel) {
        TableModel oldModel = this.tableModel;
        this.tableModel = tableModel;
        
        if (oldModel!=null)
            oldModel.removeTableModelListener(this);
        
        tableModel.addTableModelListener(this);
    }
}
