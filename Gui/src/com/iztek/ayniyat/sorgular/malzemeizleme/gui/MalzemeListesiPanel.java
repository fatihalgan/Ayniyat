/*
 * Created on 18.Haz.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.sorgular.malzemeizleme.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.model.AyniyatTableModel;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MalzemeListesiPanel extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private javax.swing.JScrollPane malzemePanel = null;
	private javax.swing.JPanel buttonPanel = null;
	private javax.swing.JTable malzemeTable = null;
	private javax.swing.JButton tamamButton = null;
	private javax.swing.JButton cikisButton = null;
	/**
	 * This is the default constructor
	 */
	public MalzemeListesiPanel(JFrame parent) {
		super(parent ,true);
		initialize();		
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(554, 325);
		this.setContentPane(getJContentPane());
		this.setTitle("Malzeme Listesi");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getMalzemePanel(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	
	private javax.swing.JScrollPane getMalzemePanel() {
		if(malzemePanel == null) {
		    malzemePanel=new JScrollPane();
		    malzemePanel.setViewportView(getMalzemeTable());
		    malzemePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Malzemeler", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
		}
		return malzemePanel;
	}
	
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
	
	private javax.swing.JTable getMalzemeTable() {
		if (malzemeTable == null) {
		    AyniyatTableModel ayniyatTableModel;
		    String [] columnName = {"Seç","Demirbaþ Numarasý","Tanýmý","Sahibi"};
			ayniyatTableModel = new AyniyatTableModel(columnName){
				    public boolean isCellEditable(int row, int col) {
				        if (col < 1) 
				            return true;
				        else 
				            return false;
				    }
				};	    
		    
	    	malzemeTable = new JTable(ayniyatTableModel);
		}
		return malzemeTable;
	}
	
	private javax.swing.JButton getTamamButton() {
		if(tamamButton == null) {
			tamamButton = new javax.swing.JButton();
			tamamButton.setToolTipText("Tamam");
			tamamButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/check.png")));
			tamamButton.addActionListener(new java.awt.event.ActionListener() { 
					public void actionPerformed(java.awt.event.ActionEvent e) {   
					    dispose();
					    Vector secilenler = getSeciliRowsForDemirbaslar();
					    if (secilenler.size()>0)
					        AnaController.getInstance().sendDemirbaslar(secilenler);
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
	
	//TODO þimdilik herþey demirbaþ Malzeme senaryosuna göre hazýrlandý
	//sonra Abstract Malzeme olacak
	public void tabloyaMalzemeEkle(DemirbasMalzeme malzeme){
	    Vector demirbas=new Vector();
	    demirbas.add(Boolean.FALSE);
	    demirbas.add(malzeme);
	    demirbas.add(malzeme.getMalzemeTanimi().getNodeValue());
	    //Malzeme Bozuk yada Zayi olabilir!
	    if (malzeme.getZimmetSahibi()==null)
	        demirbas.add("Malzeme "+malzeme.getState().getType());
	    else
	        demirbas.add(malzeme.getZimmetSahibi());
	    ((AyniyatTableModel)getMalzemeTable().getModel()).addRow2LastRow(demirbas);
	}

    public Vector getSeciliRowsForDemirbaslar(){
        Vector rows = new Vector();            
        for(int i=0;i<getMalzemeTable().getModel().getRowCount();i++){
            if(((Boolean)getMalzemeTable().getModel().getValueAt(i,0)).booleanValue())
                rows.add(getMalzemeTable().getModel().getValueAt(i,1));                    
        }            
        return rows;
    }
	
}  //  @jve:decl-index=0:visual-constraint="10,97"
