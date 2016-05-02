/*
 * Created on 12.Haz.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.sorgular.malzemeizleme.gui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.jdesktop.jdnc.JNTable;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.malzemebozuk.gui.BozukMalzemeAramaSecimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.panel.ISorgularPanel;
import com.iztek.ayniyat.util.uicomponents.SorguButtonPanel;
import java.awt.Rectangle;
/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MalzemeIzleme extends JDialog implements IAyniyatPanel,ISorgularPanel{

	private javax.swing.JPanel jContentPane = null;
	private javax.swing.JPanel bilgiPanel = null;
	private javax.swing.JPanel malzemeBilgileriPanel = null;
	private JNTable hareketlerTable = null; 
	private JNTable niteliklerTable = null; 
	private javax.swing.JLabel tanimLabel = null;
	private javax.swing.JLabel birimLabel = null;
	private javax.swing.JLabel stateLabel = null;
	private javax.swing.JLabel demirbasNoLabel = null;
	private javax.swing.JTextField tanimTextField = null;
	private javax.swing.JTextField demirbasNoTextField = null;
	private javax.swing.JTextField birimTextField = null;
	private javax.swing.JTextField stateTextField = null;
	private String panelName=null;
	private SorguButtonPanel buttonPanel=null;
	/**
	 * This is the default constructor
	 */
	public MalzemeIzleme(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}
	public MalzemeIzleme(JFrame parent,String panelName){
		super(parent ,true);
		this.panelName=panelName;
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
	    AnaController.getInstance().getMalzemeIzlemeController().registerMalzemeIzleme(this);
	    this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Malzeme Ýzleme");
		this.setResizable(false);	
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getBilgiPanel(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getHareketlerTable(), java.awt.BorderLayout.CENTER);	
			jContentPane.add(getButtonPanel(),BorderLayout.SOUTH);
			
		}
		return jContentPane;
	}	
	public SorguButtonPanel getButtonPanel(){
		if(buttonPanel==null){
			buttonPanel=new SorguButtonPanel();
			buttonPanel.registerPanel(this);
			buttonPanel.getPrintButton().setVisible(false);
		}
		return buttonPanel;
	}
	private javax.swing.JPanel getBilgiPanel() {
		if(bilgiPanel == null) {
			bilgiPanel = new javax.swing.JPanel();
			bilgiPanel.setLayout(null);
			bilgiPanel.setPreferredSize(new java.awt.Dimension(0,200));
			bilgiPanel.add(getMalzemeBilgileriPanel(), null);
			bilgiPanel.add(getNiteliklerTable(), null);
		}
		return bilgiPanel;
	}
	
	private javax.swing.JPanel getMalzemeBilgileriPanel() {
		if(malzemeBilgileriPanel == null) {
			malzemeBilgileriPanel = new javax.swing.JPanel();
			malzemeBilgileriPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Malzeme Bilgileri", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
			malzemeBilgileriPanel.setLayout(null);
			malzemeBilgileriPanel.setBounds(new Rectangle(0, 0, 446, 200));
			malzemeBilgileriPanel.add(getTanimLabel(), null);
			malzemeBilgileriPanel.add(getBirimLabel(), null);
			malzemeBilgileriPanel.add(getStateLabel(), null);
			malzemeBilgileriPanel.add(getDemirbasNoLabel(), null);
			malzemeBilgileriPanel.add(getTanimTextField(), null);
			malzemeBilgileriPanel.add(getBirimTextField(), null);
			malzemeBilgileriPanel.add(getStateTextField(), null);
			malzemeBilgileriPanel.add(getDemirbasNoTextField(), null);	
			malzemeBilgileriPanel.setPreferredSize(new java.awt.Dimension(600,1));
		}
		return malzemeBilgileriPanel;
	}
	
	public JNTable getHareketlerTable() {
		if(hareketlerTable == null) {
 		AyniyatTableModel model= new AyniyatTableModel
				    (new String[]{"Hareket Tipi","Hareketin Kaynaðý",
				    		"Hareketin Hedefi","Hareket Tarihi","Geçici Sahibi","Belge No"});
            hareketlerTable =new JNTable(model); 
            hareketlerTable.setHasColumnControl(true);
            hareketlerTable.setHeaderBackground(new java.awt.Color(204, 255, 255));
		}
		return hareketlerTable;
	}
	
	public JNTable getNiteliklerTable() {
		if(niteliklerTable == null) {
           AyniyatTableModel model= new AyniyatTableModel
		    (new String[]{"Nitelik Tanýmý","Nitelik Deðeri"});
            niteliklerTable =new JNTable(model);  
            niteliklerTable.setHasColumnControl(true);
            niteliklerTable.setBounds(new Rectangle(447, 0, 346, 200));
            niteliklerTable.setHeaderBackground(new java.awt.Color(204, 255, 255));
		}
		return niteliklerTable;
	}
	
	private javax.swing.JLabel getTanimLabel() {
		if(tanimLabel == null) {
			tanimLabel = new javax.swing.JLabel();
			tanimLabel.setBounds(50, 50, 140, 20);
			tanimLabel.setText("Cinsi :");
			tanimLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
		}
		return tanimLabel;
	}

	private javax.swing.JLabel getBirimLabel() {
		if(birimLabel == null) {
			birimLabel = new javax.swing.JLabel();
			birimLabel.setBounds(50, 80, 140, 20);
			birimLabel.setText("Ölçek :");
			birimLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
		}
		return birimLabel;
	}


	private javax.swing.JLabel getStateLabel() {
		if(stateLabel == null) {
			stateLabel = new javax.swing.JLabel();
			stateLabel.setBounds(50, 110, 140, 20);
			stateLabel.setText("Durumu :");
			stateLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
		}
		return stateLabel;
	}

	private javax.swing.JLabel getDemirbasNoLabel() {
		if(demirbasNoLabel == null) {
		    demirbasNoLabel = new javax.swing.JLabel();
			demirbasNoLabel.setBounds(50, 140, 140, 20);
			demirbasNoLabel.setText("Demirbaþ Numarasý :");
			demirbasNoLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
		}
		return demirbasNoLabel;
	}

	public javax.swing.JTextField getTanimTextField() {
		if(tanimTextField == null) {
			tanimTextField = new javax.swing.JTextField();
			tanimTextField.setBounds(190, 50, 160, 20);
			tanimTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			tanimTextField.setEditable(false);
		}
		return tanimTextField;
	}

	public javax.swing.JTextField getBirimTextField() {
		if(birimTextField == null) {
			birimTextField = new javax.swing.JTextField();
			birimTextField.setBounds(190, 80, 160, 20);
			birimTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			birimTextField.setEditable(false);
		}
		return birimTextField;
	}

	public javax.swing.JTextField getStateTextField() {
		if(stateTextField == null) {
			stateTextField = new javax.swing.JTextField();
			stateTextField.setBounds(190, 110, 160, 20);
			stateTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			stateTextField.setEditable(false);
		}
		return stateTextField;
	}

	public javax.swing.JTextField getDemirbasNoTextField() {
		if(demirbasNoTextField == null) {
			demirbasNoTextField = new javax.swing.JTextField();
			demirbasNoTextField.setBounds(190, 140, 160, 20);
			demirbasNoTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			demirbasNoTextField.setEditable(false);
		}
		return demirbasNoTextField;
	}
	
	public String getPanelName() {
        return panelName;
    }

    public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
    }
    
    public void unLoadYourself() {
        getTanimTextField().setText("");
        getBirimTextField().setText("");
        getStateTextField().setText("");
        getDemirbasNoTextField().setText("");
        ((AyniyatTableModel) getNiteliklerTable().getModel()).removeAllRows(); 
        ((AyniyatTableModel) getHareketlerTable().getModel()).removeAllRows();     
    }
    
	public void inceleAction(){
	    unLoadYourself();
	    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(getPanelName());				
	    new BozukMalzemeAramaSecimi(AnaController.getInstance().getAnaPanel()).setVisible(true);
	}
	public void printAction(){
		
	}
 }
