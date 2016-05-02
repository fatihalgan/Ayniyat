package com.iztek.ayniyat.util.uicomponents;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;

public class MalzemeTipiSecimiPanel extends JPanel {

	public JCheckBox demirbasCheckBox = null;
	public JCheckBox tuketimCheckBox = null;
	public JCheckBox sureliCheckBox = null;
	/**
	 * This is the default constructor
	 */
	public MalzemeTipiSecimiPanel() {
		super();
		initialize();
	}

	private void initialize() {
		GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
		gridBagConstraints15.insets = new java.awt.Insets(0,39,4,73);
		gridBagConstraints15.gridy = 0;
		gridBagConstraints15.ipadx = 9;
		gridBagConstraints15.ipady = -4;
		gridBagConstraints15.gridx = 2;
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.insets = new java.awt.Insets(0,39,4,39);
		gridBagConstraints14.gridy = 0;
		gridBagConstraints14.ipadx = 1;
		gridBagConstraints14.ipady = -4;
		gridBagConstraints14.gridx = 1;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.insets = new java.awt.Insets(0,72,4,39);
		gridBagConstraints13.gridy = 0;
		gridBagConstraints13.ipadx = 82;
		gridBagConstraints13.ipady = -4;
		gridBagConstraints13.gridx = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(null, "MalzemeTipi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51));
		titledBorder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(113,7,113),2));
		titledBorder.setTitleJustification(2);
		titledBorder.setTitleColor(new java.awt.Color(113,7,113));
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new java.awt.Dimension(10,40));
		this.setBounds(new java.awt.Rectangle(0,0,794,49));
		this.setBorder(titledBorder);
		this.add(getDemirbasCheckBox(), gridBagConstraints13);
		this.add(getTuketimCheckBox(), gridBagConstraints14);
		this.add(getSureliCheckBox(), gridBagConstraints15);
	}
	private JCheckBox getDemirbasCheckBox() {
		if (demirbasCheckBox == null) {
			demirbasCheckBox = new JCheckBox();
			demirbasCheckBox.setPreferredSize(new java.awt.Dimension(80,24));
			demirbasCheckBox.setText("Demirbaþ");
			demirbasCheckBox.setSelected(true);
			demirbasCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					demirbasCheckBox.setSelected(true);
				}
			});
		}
		return demirbasCheckBox;
	}
	private JCheckBox getTuketimCheckBox() {
		if (tuketimCheckBox == null) {
			tuketimCheckBox = new JCheckBox();
			tuketimCheckBox.setText("Tüketim Malzemesi");
			tuketimCheckBox.setPreferredSize(new java.awt.Dimension(80,24));
			tuketimCheckBox.setEnabled(false);
		}
		return tuketimCheckBox;
	}
	private JCheckBox getSureliCheckBox() {
		if (sureliCheckBox == null) {
			sureliCheckBox = new JCheckBox();
			sureliCheckBox.setText("Süreli Tüketim Malzemesi");
			sureliCheckBox.setEnabled(false);
		}
		return sureliCheckBox;
	}
}  //  @jve:decl-index=0:visual-constraint="-28,10"
