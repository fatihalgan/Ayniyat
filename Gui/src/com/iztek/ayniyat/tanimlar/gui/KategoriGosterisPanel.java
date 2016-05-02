package com.iztek.ayniyat.tanimlar.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.panel.IAyniyatPanel;

/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KategoriGosterisPanel extends JPanel implements IAyniyatPanel{
    
	private String panelName = null;
	private JPanel buttonPanel = null;
	private JPanel kategoriPanel = null;
	
	private JButton kapatButton = null;

	private JLabel tanimLabel = null;
	private JLabel kategoriTanimiLabel = null;
	private JLabel kodLabel = null;
	private JLabel kategoriKoduLabel = null;
	
	public KategoriGosterisPanel(String panelName) {
		super();
		this.panelName = panelName;
		initialize();
	}
	
	public  void initialize() {
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 450, 350);
		this.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		this.add(getKategoriPanel(), java.awt.BorderLayout.CENTER);
	}
	
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(null);
			buttonPanel.setPreferredSize(new java.awt.Dimension(1,150));
			buttonPanel.add(getKapatButton(), null);
		}
		return buttonPanel;
	}
   
	private JPanel getKategoriPanel() {
		if (kategoriPanel == null) {
			kategoriPanel = new JPanel();
			kategoriPanel.setLayout(null);
			kategoriPanel.add(getKategoriTanimiLabel(), null);
			kategoriPanel.add(getTanimLabel(), null);
			kategoriPanel.add(getKategoriKoduLabel(), null);
			kategoriPanel.add(getKodLabel(), null);
		}
		return kategoriPanel;
	}
	
    
    private JLabel getKategoriTanimiLabel() {
        if(kategoriTanimiLabel==null){
			kategoriTanimiLabel = new JLabel();
			kategoriTanimiLabel.setText("Grup Tanýmý :");
			kategoriTanimiLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			kategoriTanimiLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			kategoriTanimiLabel.setBounds(75, 135, 100, 25);
        }
        return kategoriTanimiLabel;
    }
    

    private JLabel getTanimLabel() {
        if(tanimLabel==null){
			tanimLabel = new JLabel();
			tanimLabel.setText("");
			tanimLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			tanimLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			tanimLabel.setBounds(175, 135, 200, 25);
        }
        return tanimLabel;
    }
    private JLabel getKategoriKoduLabel() {
        if(kategoriKoduLabel==null){
			kategoriKoduLabel = new JLabel();
			kategoriKoduLabel.setText("Grup Kodu");
			kategoriKoduLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			kategoriKoduLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			kategoriKoduLabel.setBounds(75, 80, 100, 25);
        }
        return kategoriKoduLabel;
    }
    
	private JLabel getKodLabel() {
		if (kodLabel == null) {
			kodLabel = new JLabel();
			kodLabel.setText("");
			kodLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			kodLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			kodLabel.setBounds(175, 80, 200, 25);
		}
		return kodLabel;
	}
	private JButton getKapatButton() {
		if (kapatButton == null) {
			kapatButton = new JButton();
			kapatButton.setToolTipText("Ýptal");
			kapatButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/error.png")));
			kapatButton.setBounds(213, 10, 24, 24);
			kapatButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
				}
			});
		}
		return kapatButton;
	}
	
	public void loadYourself(IKategorilendirilebilir kategori,IKategorilendirilebilir parentKategori){
	    unLoadYourself();	
	    tanimLabel.setText(((Kategori)kategori).getTanim());
	    kodLabel.setText(((Kategori)kategori).getKod());
	}
	
	public void unLoadYourself(){
	    tanimLabel.setText("");
	    kodLabel.setText("");
	}
	
	public String getPanelName() {
		return panelName;
	}
}
