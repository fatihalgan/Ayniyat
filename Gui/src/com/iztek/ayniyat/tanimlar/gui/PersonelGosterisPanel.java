package com.iztek.ayniyat.tanimlar.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.yerlesim.domain.Personel;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class PersonelGosterisPanel extends AbstractGosterisPanel{
	
	private JLabel sicilNoLabel1 = null;
	private JLabel sicilNoLabel = null;
	
	public PersonelGosterisPanel(String panelName) {
		super(panelName);
	}
	private JLabel getSicilNoLabel1() {
        if(sicilNoLabel1==null){
            sicilNoLabel1 = new JLabel();
			sicilNoLabel1.setBounds(75, 50, 100, 25);
			sicilNoLabel1.setText("Sicil No:");
			sicilNoLabel1.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			sicilNoLabel1.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        }
        return sicilNoLabel1;
    }

    private JLabel getSicilNoLabel() {
        if(sicilNoLabel==null){
            sicilNoLabel = new JLabel();
			sicilNoLabel.setBounds(175, 50, 200, 25);
			sicilNoLabel.setText("");
			sicilNoLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			sicilNoLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        }
        return sicilNoLabel;
    }

 	public void loadYourself(IKategorilendirilebilir kategori, IKategorilendirilebilir parentNode){
	    getJPanel().add(getSicilNoLabel(),null);
	    getJPanel().add(getSicilNoLabel1(),null);
	    getLabel1().setText("Adý Soyadý :");
 		unLoadYourself();	
	    sicilNoLabel.setText(((Personel) kategori).getSicilNo());
	    getLabel2().setText(kategori.getTanim());
	    getLabel2().setBounds(175, 95, 200, 25);
		getLabel1().setBounds(75, 95, 100, 25);
	}
	
	public void unLoadYourself(){
	    sicilNoLabel.setText("");
	    getLabel2().setText("");
	}
}
