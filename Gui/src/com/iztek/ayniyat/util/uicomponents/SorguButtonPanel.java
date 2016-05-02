package com.iztek.ayniyat.util.uicomponents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.iztek.ayniyat.panel.ISorgularPanel;
import com.iztek.ayniyat.util.uiactions.CikisAction;
import com.iztek.ayniyat.util.uiactions.InceleAction;
import com.iztek.ayniyat.util.uiactions.PrintAction;

public class SorguButtonPanel extends JPanel {
/**
 * @author fusun
 */
	ISorgularPanel activePanel;
	protected JButton printButton = null;
	protected JButton cikisButton = null;
	protected JButton inceleButton = null;

	public SorguButtonPanel() {
		super();
		initialize();
	}

	public void registerPanel(ISorgularPanel panel){
		this.activePanel=panel;
	}

	private void initialize() {
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new java.awt.Dimension(10,35));
		this.setBounds(new java.awt.Rectangle(0,0,800,41));
		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.gridy = 0;
		gridBagConstraints1.ipadx = -34;
		gridBagConstraints1.ipady = -10;
		gridBagConstraints1.insets = new java.awt.Insets(5,90,6,90);
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 0;
		gridBagConstraints2.ipadx = -34;
		gridBagConstraints2.ipady = -10;
		gridBagConstraints2.insets = new java.awt.Insets(5,180,6,90);
		gridBagConstraints3.gridx = 2;
		gridBagConstraints3.gridy = 0;
		gridBagConstraints3.ipadx = -34;
		gridBagConstraints3.ipady = -10;
		gridBagConstraints3.insets = new java.awt.Insets(5,90,6,180);
		this.add(getPrintButton(), gridBagConstraints1);
		this.add(getCikisButton(), gridBagConstraints2);
		this.add(getInceleButton(), gridBagConstraints3);
	}

	public JButton getPrintButton() {
		if (printButton == null) {
			printButton = new JButton();
			printButton.setToolTipText("Yazd\u0131r");
			printButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/printer2.png")));
			printButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new PrintAction(activePanel).execute();	
				}
			});
		}
		return printButton;
	}

	private JButton getCikisButton() {
		if (cikisButton == null) {
			cikisButton = new JButton();
			cikisButton.setToolTipText("Ç\u0131k\u0131\u015f");
			cikisButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/home.png")));
			cikisButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new CikisAction().execute();
					}
			});
		}
		return cikisButton;
	}

	public JButton getInceleButton() {
		if (inceleButton == null) {
			inceleButton = new JButton();
			inceleButton.setToolTipText("\u0130ncele");
			inceleButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/view.png")));
			inceleButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				new InceleAction(activePanel).execute();	
				}
			});
		}
		return inceleButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,2"
