package com.iztek.ayniyat.util.uicomponents;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.GridBagConstraints;

public class SatinAlmaBilgileriPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JPanel satinalmaBilgileriPanel = null;
	public JLabel faturaNoLabel = null;
	public JLabel faturaTarihiLabel = null;
	private JTextField faturaNoTextField = null;
	private DateTextField faturaTarihiTextField = null;

	/**
	 * This is the default constructor
	 */
	public SatinAlmaBilgileriPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(366, 200);
		this.setLayout(null);
		this.add(getSatinalmaBilgileriPanel(), null);
	}

	/**
	 * This method initializes satinalmaBilgileriPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getSatinalmaBilgileriPanel() {
		if (satinalmaBilgileriPanel == null) {
			satinalmaBilgileriPanel = new JPanel();
			faturaTarihiLabel = new JLabel();
			faturaTarihiLabel.setBounds(new Rectangle(30, 115, 120, 20));
			faturaTarihiLabel.setText("Fatura Tarihi :");
			faturaTarihiLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			faturaNoLabel = new JLabel();
			faturaNoLabel.setBounds(new Rectangle(31, 75, 114, 20));
			faturaNoLabel.setText("Fatura No :");
			faturaNoLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			satinalmaBilgileriPanel.setLayout(null);
			satinalmaBilgileriPanel.setSize(new Dimension(366, 200));
			satinalmaBilgileriPanel.setLocation(new Point(-1, -1));
			satinalmaBilgileriPanel.add(faturaNoLabel, null);
			satinalmaBilgileriPanel.add(faturaTarihiLabel, null);
			satinalmaBilgileriPanel.add(getFaturaNoTextField(), null);
			satinalmaBilgileriPanel.add(getFaturaTarihiTextField(), null);
		}
		return satinalmaBilgileriPanel;
	}

	/**
	 * This method initializes faturaNoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getFaturaNoTextField() {
		if (faturaNoTextField == null) {
			faturaNoTextField = new JTextField();
			faturaNoTextField.setBounds(new Rectangle(152, 75, 204, 20));
			faturaNoTextField.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return faturaNoTextField;
	}

	/**
	 * This method initializes faturaTarihiTextField	
	 * 	
	 * @return com.iztek.ayniyat.util.uicomponents.DateTextField	
	 */
	public DateTextField getFaturaTarihiTextField() {
		if (faturaTarihiTextField == null) {
			faturaTarihiTextField = new DateTextField();
			faturaTarihiTextField.setBounds(new Rectangle(152, 115, 131, 21));
		}
		return faturaTarihiTextField;
	}
	public void setSatinalmaBilgileriPanelEnabled(boolean value) {
		faturaNoLabel.setEnabled(value);
		faturaTarihiLabel.setEnabled(value);
		getFaturaNoTextField().setEnabled(value);
		getFaturaTarihiTextField().setEnabled(value);
		if (value)
			satinalmaBilgileriPanel
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									javax.swing.BorderFactory
											.createBevelBorder(javax.swing.border.BevelBorder.LOWERED),
									"Satýnalma Bilgileri",
									javax.swing.border.TitledBorder.LEFT,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									new java.awt.Color(113, 7, 113)));
		else
			satinalmaBilgileriPanel
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									javax.swing.BorderFactory
											.createBevelBorder(javax.swing.border.BevelBorder.LOWERED),
									"Satýnalma Bilgileri",
									javax.swing.border.TitledBorder.LEFT,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									new java.awt.Color(150, 150, 150)));

	}
	public void unLoadYourself() {
		getFaturaNoTextField().setText("");
	}

}
