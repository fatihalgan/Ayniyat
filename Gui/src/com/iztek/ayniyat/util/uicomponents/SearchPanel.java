package com.iztek.ayniyat.util.uicomponents;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.iztek.ayniyat.panel.ITanimlarPanel;
import com.iztek.ayniyat.util.uiactions.AraAction;
import com.iztek.ayniyat.util.uiactions.SonrakiAction;

public class SearchPanel extends JPanel {

/**
 * @author fusun
 */
	private JTextField araTextField = null;
	private JButton araButton = null;
	private JButton sonrakiButton = null;
	private ITanimlarPanel activePanel=null;
	
	public SearchPanel() {
		super();
		initialize();
	}
	public void registerActivePanel(ITanimlarPanel panel){
		this.activePanel=panel;
	}

	public void initialize() {
		TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(null, "Arama", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null);
		titledBorder.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		titledBorder.setTitleColor(new java.awt.Color(113,7,113));
		this.setLayout(null);
		this.setSize(250, 120);
		this.setBorder(titledBorder);
		this.setPreferredSize(new java.awt.Dimension(1,120));
		this.add(getAraButton(), null);
		this.add(getAraTextField(), null);
		this.add(getSonrakiButton(), null);
	}

	public JTextField getAraTextField() {
		if (araTextField == null) {
			araTextField = new JTextField();
			araTextField.setBounds(new java.awt.Rectangle(25,25,200,25));
			araTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					  if(e.getKeyCode() == KeyEvent.VK_ENTER && !araTextField.getText().equals("")){
						  new AraAction(activePanel).execute();
					  }
					  else{
					       switchButtons(true); 
					   }
					  }
			});
		}
		return araTextField;
	}

	private JButton getAraButton() {
		if (araButton == null) {
			araButton = new JButton();
			araButton.setToolTipText("Ara");
			araButton.setBounds(new java.awt.Rectangle(69,72,24,24));
			araButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/element_find.png")));
			araButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new AraAction(activePanel).execute();
				}
			});
		}
		return araButton;
	}
	
	private JButton getSonrakiButton() {
		if (sonrakiButton == null) {
			sonrakiButton = new JButton();
			sonrakiButton.setEnabled(false);
			sonrakiButton.setToolTipText("Sonrakini Bul");
			sonrakiButton.setBounds(new java.awt.Rectangle(158,72,24,24));
			sonrakiButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/element_next.png")));
			sonrakiButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new SonrakiAction(activePanel).execute();
					}
			});
		}
		return sonrakiButton;
	}
	public void switchButtons(boolean b) {
		araButton.setEnabled(b);
		sonrakiButton.setEnabled(!b);
}
}
