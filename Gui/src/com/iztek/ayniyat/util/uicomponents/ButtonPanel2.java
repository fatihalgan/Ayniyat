package com.iztek.ayniyat.util.uicomponents;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.iztek.ayniyat.panel.IAbstractPanel;
import com.iztek.ayniyat.tanimlar.gui.AbstractGirisPanel;
import com.iztek.ayniyat.util.uiactions.IptalAction;
import com.iztek.ayniyat.util.uiactions.KaydetAction;

public class ButtonPanel2 extends JPanel {
/**
 * @author fusun
 */
	private JButton iptalButton = null;
	private JButton kaydetButton = null;
	private IAbstractPanel activePanel=null;

	public ButtonPanel2() {
		super();
		initialize();
	}
	public void registerPanel(AbstractGirisPanel panel){
		this.activePanel=panel;
	}
	private void initialize() {
		this.setLayout(null);
		this.setSize(450, 100);
		this.add(getIptalButton(), null);
		this.add(getKaydetButton(), null);
	}
	
	public JButton getIptalButton() {
		if (iptalButton == null) {
			iptalButton = new JButton();
			iptalButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/error.png")));
			iptalButton.setBounds(new java.awt.Rectangle(150,5,24,24));
			iptalButton.setToolTipText("\u0130ptal");
			iptalButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				new IptalAction().execute();
				}
			});
		}
		return iptalButton;
	}

	public JButton getKaydetButton() {
		if (kaydetButton == null) {
			kaydetButton = new JButton();
			kaydetButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/check.png")));
			kaydetButton.setBounds(new java.awt.Rectangle(275,5,24,24));
			kaydetButton.setToolTipText("Kaydet");
			kaydetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
			new KaydetAction(activePanel).execute1();
				}
			});
		}
		return kaydetButton;
	}

}
