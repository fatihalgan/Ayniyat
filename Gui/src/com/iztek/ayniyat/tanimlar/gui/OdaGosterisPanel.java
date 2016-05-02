package com.iztek.ayniyat.tanimlar.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.yerlesim.domain.Oda;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class OdaGosterisPanel extends AbstractGosterisPanel{

	private JLabel odaNoLabel = null;
	private JLabel odaLabel = null;
	private JLabel tipLabel = null;
	private JLabel tipLabel1 = null;

	public OdaGosterisPanel(String panelName) {
		super(panelName);
	}
	
    private JLabel getOdaNoLabel() {
        if(odaNoLabel==null){
			odaNoLabel = new JLabel();
			odaNoLabel.setBounds(75, 50, 100, 25);
			odaNoLabel.setText("Oda No:");
			odaNoLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			odaNoLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        }
        return odaNoLabel;
    }
    
    private JLabel getOdaLabel() {
        if(odaLabel==null){
            odaLabel = new JLabel();
			odaLabel.setBounds(175, 50, 200, 25);
			odaLabel.setText("");
			odaLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			odaLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        }
        return odaLabel;
    }
    
    private JLabel getTipLabel() {
        if(tipLabel==null){
            tipLabel = new JLabel();
			tipLabel.setBounds(75, 140, 100, 25);
			tipLabel.setText("Tipi:");
			tipLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			tipLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        }
        return tipLabel;
    }

    private JLabel getTipLabel1() {
		if (tipLabel1 == null) {
			tipLabel1 = new JLabel();
			tipLabel1.setBounds(175, 140, 200, 25);
			tipLabel1.setText("");
			tipLabel1.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD,
					14));
			tipLabel1.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
		}
		return tipLabel1;
	}

	public void loadYourself(IKategorilendirilebilir oda,
			IKategorilendirilebilir parentNode) {
		getJPanel().add(getOdaNoLabel(), null);
		getJPanel().add(getOdaLabel(), null);
		getJPanel().add(getTipLabel(), null);
		getJPanel().add(getTipLabel1(), null);
		getLabel2().setBounds(175, 95, 200, 25);
		getLabel1().setBounds(75, 95, 100, 25);
		getLabel1().setText("Kat :");
		unLoadYourself();
		odaLabel.setText(oda.getTanim());
		tipLabel1.setText(((Oda) oda).getTip());
		getLabel2().setText("" + ((Oda) oda).getKat().intValue());
	}

	public void unLoadYourself() {
		odaLabel.setText("");
		tipLabel1.setText("");
		getLabel2().setText("");
	}
	
}
