package com.iztek.ayniyat.malzemehareketleri.malzemeGirisi.gui;

import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.jdnc.JNTable;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.StateBozuk;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.model.AyniyatTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
/**
 * @author Sevgi Uslu
 */
public class BozukMalzemeSecimi extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private javax.swing.JScrollPane demirbaslarPanel = null;
	private javax.swing.JPanel buttonPanel = null;
	private javax.swing.JButton tamamButton = null;
	private javax.swing.JButton cikisButton = null;
	private JNTable demirbaslarTable = null;
    private String errorMessage=null;

	public BozukMalzemeSecimi(JFrame parent) {
		super(parent ,true);
		initialize();		
	}

	public void initialize() {
		this.setBounds(0, 0, 458, 278);
		this.setContentPane(getJContentPane());
		this.setTitle("Bozuk Malzeme Seçimi");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		loadTable();
	}

	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getDemirbaslarTable(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	
/*	private javax.swing.JScrollPane getDemirbaslarPanel() {
		if(demirbaslarPanel == null) {
		    demirbaslarPanel=new JScrollPane();
		    demirbaslarPanel.setViewportView(getDemirbaslarTable());
		    demirbaslarPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Bozuk Malzemeler", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
		}
		return demirbaslarPanel;
	}	*/

	private javax.swing.JPanel getButtonPanel() {
		if(buttonPanel == null) {
		    GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		    GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		    buttonPanel=new JPanel();
		    buttonPanel.setLayout(new GridBagLayout());
		    buttonPanel.setPreferredSize(new java.awt.Dimension(1,35));
		    gridBagConstraints6.gridx = 1;
		    gridBagConstraints6.gridy = 0;
		    gridBagConstraints6.ipadx = -34;
		    gridBagConstraints6.ipady = -10;
		    gridBagConstraints6.insets = new java.awt.Insets(6,67,5,134);
		    gridBagConstraints7.gridx = 0;
		    gridBagConstraints7.gridy = 0;
		    gridBagConstraints7.ipadx = -34;
		    gridBagConstraints7.ipady = -10;
		    gridBagConstraints7.insets = new java.awt.Insets(6,134,5,67);
		    buttonPanel.add(getTamamButton(), gridBagConstraints6);
		    buttonPanel.add(getCikisButton(), gridBagConstraints7);
		}
		return buttonPanel;
	}
  
	private JNTable getDemirbaslarTable() {
		if (demirbaslarTable == null) { 
		//    String [] columnName = {"Seç","Demirbaþ Numarasý","Tanýmý"};
		    AyniyatTableModel model = new AyniyatTableModel(new String[]{"Seç","Demirbaþ Numarasý","Tanýmý"}){
			    public boolean isCellEditable(int row, int col) {
			        if (col < 1) 
			            return true;
				    else 
				        return false;
			    }
			};	
			
			demirbaslarTable = new JNTable(model);
			demirbaslarTable.getTable().setEditingColumn(0);
			demirbaslarTable.setHasColumnControl(true);
			demirbaslarTable.setHeaderBackground(new java.awt.Color(204, 255, 255));
			
		}
		return demirbaslarTable;
	}	

	private javax.swing.JButton getTamamButton() {
		if(tamamButton == null) {
			tamamButton = new javax.swing.JButton();
			tamamButton.setToolTipText("Tamam");
			tamamButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/check.png")));
			tamamButton.addActionListener(new java.awt.event.ActionListener() { 
					public void actionPerformed(java.awt.event.ActionEvent e) {   
					    dispose();
					    AnaController.getInstance().sendDemirbaslar(getSeciliRowsForDemirbaslar());
					}
			});
		}
		return tamamButton;
	}

	private javax.swing.JButton getCikisButton() {
		if(cikisButton == null) {
			cikisButton = new javax.swing.JButton();
			cikisButton.setToolTipText("Ýptal");
			cikisButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/error.png")));
			cikisButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					dispose();
				}
			});
		}
		return cikisButton;
	}
		
    public Vector getSeciliRowsForDemirbaslar(){
        Vector rows = new Vector();            
        for(int i=0;i<getDemirbaslarTable().getModel().getRowCount();i++){
            if(((Boolean)getDemirbaslarTable().getModel().getValueAt(i,0)).booleanValue())
                rows.add(getDemirbaslarTable().getModel().getValueAt(i,1));                    
        }            
        return rows;
    }

	public void loadTable(){
        Object[] bozukDemirbaslar = MalzemeManager.getDemirbaslarByState(new StateBozuk()).toArray();
        for (int i = 0; i < bozukDemirbaslar.length; i++) {
            DemirbasMalzeme bozukDemirbas=((DemirbasMalzeme)bozukDemirbaslar[i]);
            Vector row=new Vector();
            row.add(Boolean.FALSE);
            row.add(bozukDemirbas);
            row.add(bozukDemirbas.getMalzemeTanimi());
            ((AyniyatTableModel)demirbaslarTable.getModel()).addRow2LastRow(row);
        }
	}


  }