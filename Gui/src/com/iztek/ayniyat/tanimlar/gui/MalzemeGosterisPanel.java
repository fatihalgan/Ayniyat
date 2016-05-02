package com.iztek.ayniyat.tanimlar.gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.panel.IAyniyatPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Cagdas CIRIT
 */
public class MalzemeGosterisPanel extends JPanel implements IAyniyatPanel{
	private String panelName = null;

	private JPanel buttonPanel = null;
	private JPanel malzemePanel = null; 
	
	private JLabel malzemeTanimiLabel = null;
	private JLabel malzemeBirimiLabel = null;
	private JLabel malzemeNitelikleriLabel = null;
	private JLabel tanimLabel = null;
	private JLabel birimLabel = null;
	private JLabel nitelikLabel = null;
	private JButton kapatButton = null;
	private JLabel malzemeKoduLabel = null;
	private JLabel kodLabel = null;

	public MalzemeGosterisPanel(String panelName) {
		super();
		this.panelName = panelName;
		initialize();
	}

	public void initialize() {
		this.setLayout(new BorderLayout());
		this.setSize(450,350);	
		this.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);	
		this.add(getMalzemePanel(), java.awt.BorderLayout.CENTER);
	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(null);
			buttonPanel.setPreferredSize(new java.awt.Dimension(1,50));
			buttonPanel.add(getKapatButton(), null);
		}
		return buttonPanel;
	}
	
	private JPanel getMalzemePanel() {
		if (malzemePanel == null) {
			malzemePanel = new JPanel();
			malzemePanel.setLayout(null);
			malzemePanel.add(getMalzemeTanimiLabel(), null);
			malzemePanel.add(getMalzemeBirimiLabel(), null);
			malzemePanel.add(getMalzemeNitelikleriLabel(), null);
			malzemePanel.add(getTanimLabel(), null);
			malzemePanel.add(getBirimLabel(), null);
			malzemePanel.add(getNitelikLabel(), null);
			malzemePanel.add(getMalzemeKoduLabel(), null);
			malzemePanel.add(getKodLabel(), null);
		}
		return malzemePanel;
	}
	
	private JLabel getMalzemeTanimiLabel() {
        if(malzemeTanimiLabel==null){
			malzemeTanimiLabel = new JLabel();
			malzemeTanimiLabel.setText("Tanýmý :");
			malzemeTanimiLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			malzemeTanimiLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			malzemeTanimiLabel.setBounds(25, 120, 100, 25);
        }
        return malzemeTanimiLabel;
    }
    
	private JLabel getTanimLabel() {
        if(tanimLabel==null){
			tanimLabel = new JLabel();
			tanimLabel.setText("");
			tanimLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			tanimLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			tanimLabel.setBounds(125, 120, 300, 25);
        }
        return tanimLabel;
    }
    
	private JLabel getMalzemeKoduLabel() {
        if(malzemeKoduLabel==null){
        	malzemeKoduLabel = new JLabel();
        	malzemeKoduLabel.setText("Kodu :");
        	malzemeKoduLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        	malzemeKoduLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        	malzemeKoduLabel.setBounds(25, 70, 100, 25);
        }
        return malzemeKoduLabel;
    }
	
	private JLabel getKodLabel() {
        if(kodLabel==null){
        	kodLabel = new JLabel();
        	kodLabel.setText("");
        	kodLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        	kodLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        	kodLabel.setBounds(125, 70, 300, 25);
        }
        return kodLabel;
    }
	private JLabel getMalzemeBirimiLabel() {
        if(malzemeBirimiLabel==null){
            malzemeBirimiLabel = new JLabel();
    		malzemeBirimiLabel.setText("Ölçek :");
    		malzemeBirimiLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
    		malzemeBirimiLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
    		malzemeBirimiLabel.setBounds(25, 170, 100, 25);
        }
        return malzemeBirimiLabel;
    }
    
	private JLabel getBirimLabel() {
        if(birimLabel==null){
			birimLabel = new JLabel();
			birimLabel.setText("");
			birimLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			birimLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			birimLabel.setBounds(125, 170, 300, 25);
        }
        return birimLabel;
    }
    
	private JLabel getMalzemeNitelikleriLabel() {
        if(malzemeNitelikleriLabel==null){
			malzemeNitelikleriLabel = new JLabel();
			malzemeNitelikleriLabel.setText("Nitelikleri :");
			malzemeNitelikleriLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			malzemeNitelikleriLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			malzemeNitelikleriLabel.setBounds(25, 220, 100, 25);
        }
        return malzemeNitelikleriLabel;
    }
    
	private JLabel getNitelikLabel() {
        if(nitelikLabel==null){
			nitelikLabel = new JLabel();
			nitelikLabel.setText("");
			nitelikLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			nitelikLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			nitelikLabel.setBounds(125, 220, 300, 25);
        }
        return nitelikLabel;
    }

	private JButton getKapatButton() {
		if (kapatButton == null) {
			kapatButton = new JButton();
			kapatButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/error.png")));
			kapatButton.setToolTipText("Kapat");
			kapatButton.setBounds(213, 13, 24, 24);
			kapatButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
				}
			});
		}
		return kapatButton;
	}
	
	public void loadYourself(IKategorilendirilebilir malzeme, IKategorilendirilebilir parentNode){
	    unLoadYourself();
	    if (malzeme!=null)
	        loadYourself(((AbstractMalzemeTanimi)malzeme).getKod(),
	        		((AbstractMalzemeTanimi)malzeme).getTanim(),
	        		((DemirbasMalzemeTanimi)malzeme).getBirim(),
	        		((AbstractMalzemeTanimi) malzeme).getNitelikTanimlari());
	} 
	
	public void loadYourself(String kod,String tanim,String birim,Set nitelikler){
	    String nitelikAdlari = "";
	    NitelikTanimi nitelik;
	    Iterator iter = nitelikler.iterator();
	    
	    if (nitelikler.size()>0){
	        for (int i = 0; i < nitelikler.size()-1; i++) {
	            nitelik = (NitelikTanimi) iter.next();
	            nitelikAdlari = nitelikAdlari.concat(nitelik+", ");
	        }
	        nitelik = (NitelikTanimi) iter.next();
	        nitelikAdlari = nitelikAdlari.concat(nitelik.toString());
	    }
	    kodLabel.setText(kod);
	    tanimLabel.setText(tanim);
	    birimLabel.setText(birim);
	    nitelikLabel.setText(nitelikAdlari);
	}
	
	public void unLoadYourself(){
	    tanimLabel.setText("");
	    birimLabel.setText("");
	    nitelikLabel.setText("");
	    kodLabel.setText("");
	}
	
	public String getPanelName() {
		return panelName;
	}
 } 