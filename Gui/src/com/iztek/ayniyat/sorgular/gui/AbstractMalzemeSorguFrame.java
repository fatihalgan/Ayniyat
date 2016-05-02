/*
 * Created on 24.Eki.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.sorgular.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import org.jdesktop.jdnc.JNTable;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.panel.ISorgularPanel;
import com.iztek.ayniyat.sorgular.controller.MalzemeSorguController;
import com.iztek.ayniyat.sorgular.stoksorgulama.gui.PrintStokSorgu;
import com.iztek.ayniyat.util.uicomponents.SorguButtonPanel;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class AbstractMalzemeSorguFrame extends JFrame implements IAyniyatPanel,ISorgularPanel {

    protected JPanel jContentPane = null;
	protected JScrollPane sorguScrollPane = null;
	protected JNTable sorguTable = null;
	protected JPopupMenu jPopupMenu = null;
	protected JMenuItem inceleMenuItem = null;
	protected String panelName = null;
	protected MalzemeSorguController controller = null;
	protected SorguButtonPanel buttonPanel=null;
	protected String[] columnNames = null;
	protected String titleName = null;
	
	public AbstractMalzemeSorguFrame(String panelName, String titleName) {
		super();
		this.panelName = panelName;
		this.titleName = titleName;
		initialize();
	}
	
	
	public void initialize() {
	    this.setSize(800, 600);
		this.setContentPane(getJContentPane());
	}
	
    /**
	 * This is the default constructor
	 */
	public AbstractMalzemeSorguFrame() {
		super();
		initialize();
	}
	
	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setPreferredSize(new java.awt.Dimension(462,483));
			jContentPane.add(getSorguTable(), java.awt.BorderLayout.CENTER);
//					jContentPane.add(getSorguScrollPane(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getButtonPanel(),BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	protected SorguButtonPanel getButtonPanel(){
		if(buttonPanel==null){
			buttonPanel=new SorguButtonPanel();
			buttonPanel.registerPanel(this);
		}
		return buttonPanel;
	}
	
	/**
	 * This method initializes jPopupMenu	
	 * 	
	 * @return javax.swing.JPopupMenu	
	 */
	protected JPopupMenu getJPopupMenu() {
		if (jPopupMenu == null) {
			jPopupMenu = new JPopupMenu();
			jPopupMenu.add(getInceleMenuItem());
		}
		return jPopupMenu;
	}
	
	/**
	 * This method initializes secMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	protected JMenuItem getInceleMenuItem() {
		if (inceleMenuItem == null) {
			inceleMenuItem = new JMenuItem();
			inceleMenuItem.setActionCommand("Ýncele");
			inceleMenuItem.setText("Ýncele");
			inceleMenuItem.setPreferredSize(new java.awt.Dimension(63,21));
			inceleMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					inceleAction();
				}
			});
		}
		return inceleMenuItem;
	}
	
	protected boolean isRowSelected() {
		if(sorguTable.getTable().getSelectedRow()==-1)
	        return true;
	    return false;
	}


	public void unLoadYourself() {
		((AyniyatTableModel)getSorguTable().getModel()).removeAllRows();
	}


	public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
		unLoadYourself();
		Collection result =getController().getDataForSorguTable();
				
		for (int i = 0; i < result.size(); i++) {
			Vector temp = new Vector();
			Object[] obj = (Object[]) result.toArray()[i];
			for(int j=0;j<obj.length;j++){
				temp.add(obj[j]);
			}
			((AyniyatTableModel) getSorguTable().getModel()).
			addRow2LastRow(temp);
		}
	
	}

	public String getPanelName() {
		return panelName;
	}
	
	public MalzemeSorguController getController() {
		return controller;
	}


	public void inceleAction() {
		int selectedRow = getSorguTable().getTable().getSelectedRow();
		if (selectedRow != -1)
			getController()
					.malzemeIncelemekIcinDialogCikar(
						(AbstractMalzemeTanimi)((AyniyatTableModel) getSorguTable().getModel()).getValueAt(selectedRow,1));
	}
	public void printAction() {
		 try{
			 PrintStokSorgu.prepareReport(((AyniyatTableModel)getSorguTable().getModel()).getTableData());
		 }catch(Exception e){
		    	e.printStackTrace();
		    }
	}
	protected abstract String[] getColumnNames();


	/**
	 * This method initializes sorguTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	protected JNTable getSorguTable() {
		if (sorguTable == null) {
		    AyniyatTableModel atm = new AyniyatTableModel(getColumnNames());
			sorguTable=new JNTable(atm);
			sorguTable.setPopupMenu(getJPopupMenu());
	        sorguTable.setHasColumnControl(true);
	        sorguTable.setHeaderBackground(new java.awt.Color(204,255,255));
	        sorguTable.setBounds(new java.awt.Rectangle(0,62,794,406));
	        sorguTable.setHeaderFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
		}
		return sorguTable;
	}

	public String getTitleName() {
		return titleName;
	}
}
