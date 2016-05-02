package com.iztek.ayniyat.util.uicomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.tanimlar.gui.YerlesimAnaPanel;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;

public class HareketBilgileriPanelForCikis extends JPanel {

/**
 * @author fusun
 */	
	private static final long serialVersionUID = 1L;
	private HareketBilgileriPanel bilgiPanel=null;
	private JPanel cikisBilgileriPanel = null;
	private JButton verildigiYerKisiButton = null;
	private JTextField verildigiYerKisiTextField = null;
	private JTextField teslimAlanTextField = null;
	private JButton teslimAlanButton = null;
	private IZimmetAlan verildigiYer = null;  //  @jve:decl-index=0:
	private IZimmetAlan teslimAlan = null;  //  @jve:decl-index=0:
	private IZimmetAlan temporary = null;
	private IZimmetAlan hareketKaynagi=null;
	/**
	 * This is the default constructor
	 */
	public HareketBilgileriPanelForCikis() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(792, 175);
		this.setLayout(null);
		this.add(getCikisBilgileriPanel(), null);
	}

	/**
	 * This method initializes cikisBilgileriPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCikisBilgileriPanel() {
		if (cikisBilgileriPanel == null) {
			cikisBilgileriPanel = new JPanel();
			cikisBilgileriPanel.setLayout(null);
			cikisBilgileriPanel.setBounds(new Rectangle(2, 0, 785, 171));
			cikisBilgileriPanel.setPreferredSize(new Dimension(1, 150));
			cikisBilgileriPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Ç\u0131k\u0131\u015f Bilgileri", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(113, 7, 113)));
			cikisBilgileriPanel.add(getBilgiPanel(),null);
			cikisBilgileriPanel.add(getVerildigiYerKisiButton(), null);
			cikisBilgileriPanel.add(getVerildigiYerKisiTextField(), null);
			cikisBilgileriPanel.add(getTeslimAlanTextField(), null);
			cikisBilgileriPanel.add(getTeslimAlanButton(), null);
		}
		return cikisBilgileriPanel;
	}
	
	public HareketBilgileriPanel getBilgiPanel(){
		if(bilgiPanel==null){
			bilgiPanel=new HareketBilgileriPanel();
			bilgiPanel.setSize(new Dimension(384, 141));
			bilgiPanel.setLocation(new Point(8, 19));
			bilgiPanel.hareketSekliLabel.setText("Çýkýþ Þekli :");
			bilgiPanel.hareketTarihiLabel.setText("Çýkýþ Tarihi :");
			bilgiPanel.hareketiOnaylayanLabel.setText("Çýkýþý Onaylayan :");
		}
		return bilgiPanel;
	}

	/**
	 * This method initializes verildigiYerKisiButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getVerildigiYerKisiButton() {
		if (verildigiYerKisiButton == null) {
			verildigiYerKisiButton = new JButton();
			verildigiYerKisiButton.setLocation(new java.awt.Point(405,63));
			verildigiYerKisiButton.setFont(new Font("Dialog", Font.BOLD, 10));
			verildigiYerKisiButton.setToolTipText("Verildi\u011fi Yer / Ki\u015fi");
			verildigiYerKisiButton.setHorizontalAlignment(SwingConstants.LEFT);
			verildigiYerKisiButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/branch.png")));
			verildigiYerKisiButton.setText("Yer/Ki\u015fi :");
			verildigiYerKisiButton.setSize(new java.awt.Dimension(129,20));
			verildigiYerKisiButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setTemporary(null);
					AnaController.getInstance().setCurrentYerlesimPanelRole(AnaController.YERLESIM_FOR_DIALOG_ZIMMETALAN_SEC);
					AnaController.getInstance().setCurrentTanimlarController(AnaController.getInstance().getYerlesimController());
					new YerlesimAnaPanel(AnaController.getInstance().getAnaPanel(),"yerlesimPanel").setVisible(true);
					if (getTemporary()!=null){
						setVerildigiYer(getTemporary());
						verildigiYerKisiTextField.setText(getVerildigiYer().getTanim());
					}
				}
			});
			
		}
		return verildigiYerKisiButton;
	}

	/**
	 * This method initializes verildigiYerKisiTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getVerildigiYerKisiTextField() {
		if (verildigiYerKisiTextField == null) {
			verildigiYerKisiTextField = new JTextField();
			verildigiYerKisiTextField.setBounds(new java.awt.Rectangle(534,63,230,20));
			verildigiYerKisiTextField.setEditable(false);
			verildigiYerKisiTextField.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return verildigiYerKisiTextField;
	}

	/**
	 * This method initializes teslimAlanTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTeslimAlanTextField() {
		if (teslimAlanTextField == null) {
			teslimAlanTextField = new JTextField();
			teslimAlanTextField.setLocation(new java.awt.Point(534,105));
			teslimAlanTextField.setFont(new Font("Dialog", Font.BOLD, 13));
			teslimAlanTextField.setEditable(false);
			teslimAlanTextField.setSize(new java.awt.Dimension(230,20));
		}
		return teslimAlanTextField;
	}

	/**
	 * This method initializes teslimAlanButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getTeslimAlanButton() {
		if (teslimAlanButton == null) {
			teslimAlanButton = new JButton();
			teslimAlanButton.setBounds(new java.awt.Rectangle(405,105,129,20));
			teslimAlanButton.setToolTipText("Teslim Alan Ki\u015fi");
			teslimAlanButton.setHorizontalAlignment(SwingConstants.LEFT);
			teslimAlanButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/branch.png")));
			teslimAlanButton.setText("Teslim Alan :");
			teslimAlanButton.setFont(new Font("Dialog", Font.BOLD, 10));
			teslimAlanButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setTemporary(null);
					AnaController.getInstance().setCurrentYerlesimPanelRole(AnaController.YERLESIM_FOR_DIALOG_ZIMMETALAN_SEC);
					new YerlesimAnaPanel(AnaController.getInstance().getAnaPanel(),"yerlesimPanel").setVisible(true);
					if (getTemporary()!=null){
						setTeslimAlan(getTemporary());
						teslimAlanTextField.setText(getTeslimAlan().getTanim());
					}
				}
			});
		}
		return teslimAlanButton;
	}
	
	public IZimmetAlan getTemporary() {
		return temporary;
	}
	
	public void setTemporary(IZimmetAlan temporary) {
		this.temporary = temporary;
	}
	
	public IZimmetAlan getTeslimAlan() {
		return teslimAlan;
	}
	
	public void setTeslimAlan(IZimmetAlan teslimAlan) {
		this.teslimAlan = teslimAlan;
	}
	
	public IZimmetAlan getVerildigiYer() {
		return verildigiYer;
	}
	
	public void setVerildigiYer(IZimmetAlan verildigiYer) {
		this.verildigiYer = verildigiYer;
	}
	public IZimmetAlan getHareketKaynagi() {
        return hareketKaynagi;
    }
   
    public void setHareketKaynagi(IZimmetAlan hareketKaynagi) {
        this.hareketKaynagi = hareketKaynagi;
    }
	
	public void unLoadYourself() {
		getBilgiPanel().unLoadYourself();
		getVerildigiYerKisiTextField().setText("");
		getTeslimAlanTextField().setText("");
		setTeslimAlan(null);
		setVerildigiYer(null);
		setHareketKaynagi(null);
	}

}  //  @jve:decl-index=0:visual-constraint="9,16"
