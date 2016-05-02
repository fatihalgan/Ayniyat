package com.iztek.ayniyat.util.uicomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;

public class HareketBilgileriPanel extends JPanel {
/**
 * @author fusun
 */
	public JPanel hareketBilgileriPanel = null;
	public JLabel hareketSekliLabel = null;
	public JLabel hareketiOnaylayanLabel = null;
	public JLabel belgeNoLabel = null;
	public JLabel hareketTarihiLabel = null;
	public DateTextField hareketTarihi = null;
	public JTextField belgeNoTextField = null;
	public JTextField hareketiOnaylayanTextField = null;
	public JTextField hareketSekliTextField = null;

	/**
	 * This is the default constructor
	 */
	public HareketBilgileriPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setLayout(null);
		this.setSize(427, 200);
		this.setPreferredSize(new java.awt.Dimension(62,200));
		this.add(getHareketBilgileriPanel(), null);
	}

	/**
	 * This method initializes hareketBilgileriPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getHareketBilgileriPanel() {
		if (hareketBilgileriPanel == null) {
			hareketTarihiLabel = new JLabel();
			hareketTarihiLabel.setBounds(new Rectangle(15, 73, 130, 20));
			hareketTarihiLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			belgeNoLabel = new JLabel();
			belgeNoLabel.setBounds(new Rectangle(200, 16, 69, 20));
			belgeNoLabel.setText("Belge No :");
			belgeNoLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			hareketiOnaylayanLabel = new JLabel();
			hareketiOnaylayanLabel.setBounds(new Rectangle(16, 101, 127, 20));
			hareketiOnaylayanLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			hareketSekliLabel = new JLabel();
			hareketSekliLabel.setBounds(new Rectangle(16, 44, 127, 20));
			hareketSekliLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			hareketBilgileriPanel = new JPanel();
			hareketBilgileriPanel.setLayout(null);
			hareketBilgileriPanel.setPreferredSize(new Dimension(1, 1));
			hareketBilgileriPanel.setLocation(new Point(0, 0));
			hareketBilgileriPanel.setSize(new Dimension(427, 200));
			hareketBilgileriPanel.add(hareketSekliLabel, null);
			hareketBilgileriPanel.add(hareketiOnaylayanLabel, null);
			hareketBilgileriPanel.add(belgeNoLabel, null);
			hareketBilgileriPanel.add(hareketTarihiLabel, null);
			hareketBilgileriPanel.add(getHareketTarihi(), null);
			hareketBilgileriPanel.add(getBelgeNoTextField(), null);
			hareketBilgileriPanel.add(getHareketiOnaylayanTextField(), null);
			hareketBilgileriPanel.add(getHareketSekliTextField(), null);
		}
		return hareketBilgileriPanel;
	}

	/**
	 * This method initializes hareketTarihi	
	 * 	
	 * @return com.iztek.ayniyat.util.uicomponents.DateTextField	
	 */
	public DateTextField getHareketTarihi() {
		if (hareketTarihi == null) {
			hareketTarihi = new DateTextField();
			hareketTarihi.setBounds(new Rectangle(143, 72, 148, 24));
			hareketTarihi.setBackground(new Color(238, 238, 238));
		}
		return hareketTarihi;
	}

	/**
	 * This method initializes belgeNoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getBelgeNoTextField() {
		if (belgeNoTextField == null) {
			belgeNoTextField = new JTextField();
			belgeNoTextField.setBounds(new java.awt.Rectangle(293,17,62,19));
			belgeNoTextField.setEditable(false);
			belgeNoTextField.setHorizontalAlignment(JTextField.CENTER);
			belgeNoTextField.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return belgeNoTextField;
	}

	/**
	 * This method initializes hareketiOnaylayanTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getHareketiOnaylayanTextField() {
		if (hareketiOnaylayanTextField == null) {
			hareketiOnaylayanTextField = new JTextField();
			hareketiOnaylayanTextField.setBounds(new Rectangle(143, 101, 230, 20));
			hareketiOnaylayanTextField.setEditable(false);
			hareketiOnaylayanTextField.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return hareketiOnaylayanTextField;
	}

	/**
	 * This method initializes hareketSekliTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getHareketSekliTextField() {
		if (hareketSekliTextField == null) {
			hareketSekliTextField = new JTextField();
			hareketSekliTextField.setBounds(new Rectangle(143, 44, 230, 20));
			hareketSekliTextField.setEditable(false);
			hareketSekliTextField.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return hareketSekliTextField;
	}
	public void unLoadYourself() {
		getHareketiOnaylayanTextField().setText(KullaniciManager.getpersonel(
	            AnaController.getInstance().getKullanici()).toString());
		getBelgeNoTextField().setText("");
	//	getHareketSekliTextField().setText("");
	}

}  //  @jve:decl-index=0:visual-constraint="40,49"
