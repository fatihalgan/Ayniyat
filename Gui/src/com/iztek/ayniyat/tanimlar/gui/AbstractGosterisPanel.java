package com.iztek.ayniyat.tanimlar.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.util.uicomponents.ButtonPanel2;

public abstract class AbstractGosterisPanel extends JPanel implements IAyniyatPanel {
/**
 * @author fusun
 */
	
	private JPanel jPanel = null;
	private JLabel label1 = null;
	private JLabel label2 = null;
	private ButtonPanel2 buttonPanel=null;
	private String panelName;
	
	public AbstractGosterisPanel(String pName) {
		super();
		this.panelName=pName;
		initialize();
	}
	public void initialize() {
		this.setLayout(null);
		this.setSize(450, 300);
		this.add(getJPanel(), null);
		this.add(getButtonPanel(),null);
	}

	public JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new java.awt.Rectangle(0,1,450,199));
			jPanel.add(getLabel1(), null);
			jPanel.add(getLabel2(), null);
		}
		return jPanel;
	}
	public ButtonPanel2 getButtonPanel(){
		if(buttonPanel==null){
			buttonPanel=new ButtonPanel2();
			buttonPanel.setBounds(new java.awt.Rectangle(0,199,450,100));
			buttonPanel.getIptalButton().setBounds(180, 7, 24, 24);
			buttonPanel.getKaydetButton().setVisible(false);
		}
		return buttonPanel;
	}
	public JLabel getLabel1(){
		if(label1==null){
			label1 = new JLabel();
			label1.setBounds(new java.awt.Rectangle(50, 100, 100, 25));
			label1.setText("");
			label1.setFont(new Font("Dialog", Font.BOLD, 14));
			label1.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
		       
		}
		return label1;
	}

	public JLabel getLabel2() {
		if (label2 == null) {
			label2 = new JLabel();
			label2.setBounds(new java.awt.Rectangle(150, 100, 275, 25));
			label2.setFont(new Font("Dialog", Font.BOLD, 14));
			label2.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
		}
		return label2;
	}

	public abstract void loadYourself(IKategorilendirilebilir oda, IKategorilendirilebilir parentNode);
	public void unLoadYourself(){
		getLabel2().setText("");	
	}
	public String getPanelName() {
		return panelName;
	}
}
