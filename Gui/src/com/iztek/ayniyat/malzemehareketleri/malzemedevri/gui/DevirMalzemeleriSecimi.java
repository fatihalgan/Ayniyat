package com.iztek.ayniyat.malzemehareketleri.malzemedevri.gui;

import java.awt.CheckboxGroup;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.tanimlar.gui.YerlesimAnaPanel;
import java.awt.Rectangle;
import java.awt.BorderLayout;
/**
 * @author Umit Akyol
 */
public class DevirMalzemeleriSecimi extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private JPanel jPanel1 = null;
	private JTextField demirbasNoTextField = null;
	private JButton kisiUzerindenButton = null;
	private CheckboxGroup group = null;
	
	private JButton demirbasNoButton = null;
	public DevirMalzemeleriSecimi(JFrame parent) {
		super(parent,true);
		initialize();
	}
	
	private void initialize() {
		this.setTitle("Demirbaþ Ara");
		this.setSize(277, 155);
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(AnaController.getInstance().getAnaPanel());
	}
	
	public javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel1(), null);
		}
		return jContentPane;
	}
	  
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(new Rectangle(0, 0, 269, 120));
			jPanel1.add(getDemirbasNoTextField(), null);
			jPanel1.add(getKisiUzerindenButton(), null);
			jPanel1.add(getDemirbasNoButton(), null);
		}
		return jPanel1;
	}
	
	private JTextField getDemirbasNoTextField() {
		if (demirbasNoTextField == null) {
			demirbasNoTextField = new JTextField();
			demirbasNoTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			demirbasNoTextField.setLocation(150, 23);
			demirbasNoTextField.setSize(100, 26);
			demirbasNoTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			demirbasNoTextField.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					demirbasNoDondurAction();
				}
			});
			demirbasNoTextField.addKeyListener(new java.awt.event.KeyAdapter() { 
				public void keyTyped(java.awt.event.KeyEvent e) {    
					getDemirbasNoButton().setEnabled(true);
				}
			});
		}
		return demirbasNoTextField;
	}

	private JButton getKisiUzerindenButton() {
		if (kisiUzerindenButton == null) {
			kisiUzerindenButton = new JButton();
			kisiUzerindenButton.setText("Kiþi Üzerinden    ");
			kisiUzerindenButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/navigate_right2.png")));
			kisiUzerindenButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
			kisiUzerindenButton.setFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 13));
			kisiUzerindenButton.setSize(229, 26);
			kisiUzerindenButton.setLocation(21, 72);
			kisiUzerindenButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					zimmetAlanDondurAction();
					dispose();
				}
			});
		}
		return kisiUzerindenButton;
	}
	
	public CheckboxGroup getGroup() {
	    if(group == null)
	        group = new CheckboxGroup();
        return group;
    }
  
	private JButton getDemirbasNoButton() {
		if (demirbasNoButton == null) {
			demirbasNoButton = new JButton();
			demirbasNoButton.setFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 13));
			demirbasNoButton.setText("Demirbaþ No :");
			demirbasNoButton.setLocation(21, 23);
			demirbasNoButton.setSize(129, 26);
			demirbasNoButton.setEnabled(false);
			demirbasNoButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				   demirbasNoDondurAction();
				}
			});
		}
		return demirbasNoButton;
	}
	private void demirbasNoDondurAction(){
		dispose();
		if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.ZIMMET_SORGULAMA))
		    AnaController.getInstance().getZimmetSorgulamaController().demirbasNumarasindanSorgula(getDemirbasNoTextField().getText());
		else if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.MALZEME_IZLEME))
		    AnaController.getInstance().getMalzemeIzlemeController().demirbasNumarasindanIzle(getDemirbasNoTextField().getText());
		else if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.MALZEME_TERKIN))
			AnaController.getInstance().getMalzemeTerkinController().demirbasNoyaGoreSonucDondur(getDemirbasNoTextField().getText());
		else if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.MALZEME_ZAYI))
			AnaController.getInstance().getMalzemeZayiatiController().demirbasNoyaGoreSonucDondur(getDemirbasNoTextField().getText());
		else if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.MALZEME_DEVIR))
			AnaController.getInstance().getMalzemeDevriController().getDemirbasByDemirbasNo(getDemirbasNoTextField().getText());
		else
		    AnaController.getInstance().getMalzemeBozulmasiController().demirbasNoyaGoreSonucDondur(getDemirbasNoTextField().getText());
	}
	
	private void zimmetAlanDondurAction(){
		dispose();
		if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.ZIMMET_SORGULAMA))
		    AnaController.getInstance().setCurrentYerlesimPanelRole(AnaController.YERLESIM_FOR_DIALOG_ZIMMETALAN_SEC);
	    else
	        AnaController.getInstance().setCurrentYerlesimPanelRole(AnaController.YERLESIM_FOR_DIALOG_DEMIRBAS_SEC);
		AnaController.getInstance().setCurrentTanimlarController(AnaController.getInstance().getYerlesimController());
		new YerlesimAnaPanel(AnaController.getInstance().getAnaPanel(),"yerlesim").setVisible(true);	   
	}
	
	private void demirbasNoAction(){
	    String demirbasNo = getDemirbasNoTextField().getText();
	    boolean gecerliNumara = AnaController.getInstance().getMalzemeDevriController().getDemirbasByDemirbasNo(demirbasNo);
	    if(gecerliNumara){
	        dispose();
	    }
	    else{
	        AnaController.getInstance().showWarningDialogBox(DevirMalzemeleriSecimi.this,demirbasNo+" numaralý bir demirbaþ bulunamadý veya henüz zimmetli deðil!");
	        return;
	    }
	}
}
