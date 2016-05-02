/*
 * Created on 20.Eki.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.malzemehareketleri.demirbasNo.gui;

import java.awt.BorderLayout;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.util.persistence.DAOFactory;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.tracer.debug.Debug;

import javax.swing.JList;
import javax.swing.border.BevelBorder;

import org.hibernate.LockMode;
import org.hibernate.Session;
/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DemirbasNoUpdate extends JFrame{
	private JPanel jContentPane = null;
	private JPanel bilgiPanel= null;
	private JPanel buttonPanel = null;

	private JLabel demirbasNoLabel = null;
	private JLabel siraNoLabel = null;
	private JTextField altSiraNoTextField = null;	
	private JTextField demirbasNoTextField = null;
	private JButton bulButton = null;
	private JButton degistirButton = null;
	private JButton iptalButton = null;
	private String panelName;
	private DemirbasMalzeme demirbasMalzeme=null;
	
	private JLabel yeniNoLabel = null;
	private JList oncekilerList = null;
	public DemirbasNoUpdate() {
		super();
		initialize();
	}
	
	public void initialize() {
	//	AnaController.getInstance().getMalzemeBozulmasiController().registerMalzemeBozulmasi(MalzemeBozulmasi.this);
		this.setBounds(0, 0, 450, 414);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
        this.setLocationRelativeTo(null);
		this.setTitle("Demirbaþ Numarasý Deðiþimi");
		this.setResizable(false);	
	}

	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getButtonPanel(),BorderLayout.SOUTH);
			jContentPane.add(getAnaPanel(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
	private JPanel getAnaPanel() {
		if (bilgiPanel == null) {
			bilgiPanel = new JPanel();
			bilgiPanel.setLayout(null);
			bilgiPanel.add(getDemirbasNoLabel(), null);
			bilgiPanel.add(getSiraNoLabel(), null);
			bilgiPanel.add(getAltSiraNoTextField(), null);
			bilgiPanel.add(getDemirbasNoTextField(), null);
			bilgiPanel.add(getBulButton(), null);
			bilgiPanel.add(getYeniNoLabel(), null);
			bilgiPanel.add(getOncekilerList(), null);
			
		}
		return bilgiPanel;
	}
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(null);
			buttonPanel.setPreferredSize(new java.awt.Dimension(1,75));
			buttonPanel.add(getIptalButton(), null);
			buttonPanel.add(getdegistirButton(), null);
		}
		return buttonPanel;
	}	
    private JLabel getSiraNoLabel() {
        if(siraNoLabel==null){
			siraNoLabel = new JLabel();
			siraNoLabel.setBounds(122, 267, 71, 25);
			siraNoLabel.setText("");
			siraNoLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			siraNoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
			siraNoLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			siraNoLabel.setVisible(false);
        }
        return siraNoLabel;
    }
    private JLabel getDemirbasNoLabel() {
        if(demirbasNoLabel==null){
        	demirbasNoLabel = new JLabel();
			demirbasNoLabel.setBounds(10, 60, 176, 25);
			demirbasNoLabel.setText("Demirbas Numarasý");
			demirbasNoLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        }
        return demirbasNoLabel;
    }
    private JLabel getYeniNoLabel() {
        if(yeniNoLabel==null){
        	yeniNoLabel = new JLabel();
			yeniNoLabel.setBounds(13, 267, 110, 25);
			yeniNoLabel.setText("Yeni Numara");
			yeniNoLabel.setVisible(false);
			yeniNoLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        }
        return yeniNoLabel;
    }     
    private JTextField getDemirbasNoTextField() {
		if (demirbasNoTextField == null) {
			demirbasNoTextField = new JTextField();
			demirbasNoTextField.setBounds(187, 60, 114, 25);
		}
		return demirbasNoTextField;
	}   
	private JButton getBulButton() {
		if (bulButton == null) {
			bulButton = new JButton();
			bulButton.setBounds(320, 59, 100, 25);
			bulButton.setText("Bul");
			bulButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {
					demirbasMalzeme = (DemirbasMalzeme) MalzemeManager.findDemirbasByDemirbasNo(getDemirbasNoTextField().getText());
					if(demirbasMalzeme!=null){
						loadYourself();
					}else{
					    Object[] options = {"Tamam"};
					  	JOptionPane.showOptionDialog(DemirbasNoUpdate.this,
					   	        getDemirbasNoTextField().getText()+" numaralý demirbaþ bulunamadý. ",
								"Uyarý",JOptionPane.YES_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
						unLoadYourself();
					}					
				}
			});
		}
		return bulButton;
	}
	private JTextField getAltSiraNoTextField() {
		if (altSiraNoTextField == null) {
			altSiraNoTextField = new JTextField();
			altSiraNoTextField.setBounds(199, 267, 130, 25);
			altSiraNoTextField.setVisible(false);
		}
		return altSiraNoTextField;
	}
	private JButton getIptalButton() {
		if (iptalButton == null) {
			iptalButton = new JButton();
			iptalButton.setToolTipText("Ýptal");
			iptalButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    DemirbasNoUpdate.this.dispose();
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir("bosPanel");
				}
			});
			iptalButton.setBounds(270, 25, 90, 25);
			iptalButton.setText("Ýptal");
		}
		return iptalButton;
	}
   
	private JButton getdegistirButton() {
		if (degistirButton == null) {
			degistirButton = new JButton();
			degistirButton.setToolTipText("Deðiþtir");
			degistirButton.setBounds(90, 25, 90, 25);
			degistirButton.setText("Deðiþtir");
			degistirButton.setEnabled(false);
			degistirButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				//	demirbasMalzeme.getDemirbasNo().setAltSiraNo(getAltSiraNoTextField().getText());
					demirbasMalzeme.getDemirbasNo().setSiraNo(getAltSiraNoTextField().getText());
				
					try{
						MalzemeManager.updateMalzemeDemirbasNo(demirbasMalzeme, getAltSiraNoTextField().getText());
					MalzemeManager.updateMalzeme(demirbasMalzeme,null);
					} catch (Throwable t) {
						showError("Girdiðiniz DemirbaþNo Sistemde Mevcut");
						throw new RuntimeException(t);
						
					}
					DemirbasNoUpdate.this.dispose();					
			}
			});
		}
		return degistirButton;
	}
	public void showError(String Message){
		JOptionPane.showMessageDialog(this, Message, "Hata",
				JOptionPane.ERROR_MESSAGE);
	}
	public void loadYourself() {
		degistirButton.setEnabled(true);
		yeniNoLabel.setVisible(true);
		siraNoLabel.setVisible(true);
		altSiraNoTextField.setVisible(true);
		getSiraNoLabel().setText(demirbasMalzeme.getId().toString());
		Session session = HibernateUtil.getSession();
		session.lock(demirbasMalzeme,LockMode.READ);
		Iterator iter=demirbasMalzeme.getDemirbasNolari().iterator();
	
		while(iter.hasNext()){
			((DefaultListModel)getOncekilerList().getModel()).addElement(iter.next());
		}
		HibernateUtil.closeSession();
	}
	
	public void unLoadYourself() {
		degistirButton.setEnabled(false);
		yeniNoLabel.setVisible(false);
		siraNoLabel.setVisible(false);
		altSiraNoTextField.setVisible(false);
		getDemirbasNoTextField().setText("");
		getSiraNoLabel().setText("");
		getAltSiraNoTextField().setText("");
	}
	 
	
	public String getPanelName() {
		return panelName;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getOncekilerList() {
		if (oncekilerList == null) {
			DefaultListModel model=new DefaultListModel();
			oncekilerList = new JList(model);
			oncekilerList.setBounds(new java.awt.Rectangle(17,104,172,153));
			oncekilerList.setBackground(new java.awt.Color(238,238,238));
			oncekilerList.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			oncekilerList.setVisibleRowCount(10);
			oncekilerList.setBorder(javax.swing.BorderFactory
					.createTitledBorder(
							javax.swing.BorderFactory
									.createBevelBorder(javax.swing.border.BevelBorder.LOWERED),
							"Önceki DemirbaþNolarý",
							javax.swing.border.TitledBorder.LEFT,
							javax.swing.border.TitledBorder.DEFAULT_POSITION,
							new java.awt.Font("Dialog",
									java.awt.Font.BOLD, 12),
							new java.awt.Color(113, 7, 113)));
		}
		return oncekilerList;
	}   

   }  //  @jve:decl-index=0:visual-constraint="10,10"
