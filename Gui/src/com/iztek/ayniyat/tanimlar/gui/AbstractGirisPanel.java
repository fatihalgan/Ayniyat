package com.iztek.ayniyat.tanimlar.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.panel.IAbstractPanel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.util.uicomponents.ButtonPanel2;

public abstract class AbstractGirisPanel extends JPanel implements IAyniyatPanel,IAbstractPanel{
/**
 * @author fusun
 */
	private JPanel jPanel = null;
	private JLabel label1 = null;
	private JTextField textField = null;
	public IKategorilendirilebilir anaKategori=null;
	private ButtonPanel2 buttonPanel=null;
	private String panelName;

	public AbstractGirisPanel(String pname) {
		super();
		this.panelName=pname;
		initialize();
	}

	public void initialize() {
		this.setLayout(null);
		this.setSize(450, 300);
		this.add(getJPanel(), null);
		this.add(getButtonPanel(), null);
	}

	public JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new java.awt.Rectangle(0,2,449,199));
			jPanel.add(getTextField(), null);
			jPanel.add(getLabel1(), null);
		}
		return jPanel;
	}
	private ButtonPanel2 getButtonPanel(){
		if(buttonPanel==null){
			buttonPanel=new ButtonPanel2();
			buttonPanel.setBounds(new java.awt.Rectangle(1,201,450,100));
			buttonPanel.registerPanel(this);
			
		}
		return buttonPanel;
	}
	public JLabel getLabel1(){
		if(label1==null){
			label1 = new JLabel();
			label1.setText("");
			label1.setBounds(new java.awt.Rectangle(75,95,100,25));
			
			label1.setFont(new Font("Dialog", Font.BOLD, 14));
			label1.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
		       
		}
		return label1;
	}

	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(new java.awt.Rectangle(175,95,200,25));
			textField.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return textField;
	}

	public abstract void loadYourself(IKategorilendirilebilir bir,IKategorilendirilebilir parentNode);
	
	public void loadYourself(String tanim){
	    getTextField().setText(tanim);
	}	
	public void unLoadYourself(){
		getTextField().setText("");	
	}
	public boolean agacaYeniNodeGir(IKategorilendirilebilir node){    	
    	return AnaController.getInstance().getCurrentTanimlarController().treePaneleNodeEkle(anaKategori,node);
    }
	public boolean nodeDuzelt(IKategorilendirilebilir node) {
	    return AnaController.getInstance().getCurrentTanimlarController().kategoriTanimiDuzelt(node);
    }
	public abstract void kaydetAction();
	
	public String getPanelName() {
		return panelName;
	}

}
