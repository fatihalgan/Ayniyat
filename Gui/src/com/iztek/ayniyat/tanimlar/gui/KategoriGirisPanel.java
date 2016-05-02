package com.iztek.ayniyat.tanimlar.gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.iztek.ayniyat.gui.service.InputDuzenleyici;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.panel.IAyniyatPanel;

import java.awt.BorderLayout;
import java.awt.Color;

public class KategoriGirisPanel extends JPanel implements IAyniyatPanel{
	private String panelName = null;
	private JPanel buttonPanel = null;
	private JPanel kategoriPanel = null;
	private JButton iptalButton = null;
	private JButton kaydetButton = null;
	private JLabel kategoriTanimiLabel = null;
	private JTextField kategoriTanimiTextField = null;
	private IKategorilendirilebilir kategori =null;
	private IKategorilendirilebilir parent =null;
	private JLabel kategoriKoduLabel = null;
	private JTextField kategoriKoduTextField = null;
	
	public KategoriGirisPanel(String panelName) {
		super();
		this.panelName = panelName;
		initialize();
	}

	public  void initialize() {
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 450, 350);
		this.setPreferredSize(new java.awt.Dimension(450,350));
		this.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		this.add(getKategoriPanel(), java.awt.BorderLayout.CENTER);
	}
  
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(null);
			buttonPanel.setPreferredSize(new java.awt.Dimension(1,150));
			buttonPanel.add(getIptalButton(), null);
			buttonPanel.add(getKaydetButton(), null);
		}
		return buttonPanel;
	}
   
	private JPanel getKategoriPanel() {
		if (kategoriPanel == null) {
			kategoriPanel = new JPanel();
			kategoriPanel.setLayout(null);
			kategoriPanel.add(getKategoriTanimiLabel(), null);
			kategoriPanel.add(getKategoriTanimiTextField(), null);
			kategoriPanel.add(getKategoriKoduLabel(), null);
			kategoriPanel.add(getKategoriKoduTextField(), null);
		}
		return kategoriPanel;
	}
	
	private JButton getIptalButton() {
		if (iptalButton == null) {
			iptalButton = new JButton();
			iptalButton.setToolTipText("Ýptal");
			iptalButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/error.png")));
			iptalButton.setBounds(140, 10, 24, 24);
			iptalButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
				}
			});
		}
		return iptalButton;
	}
   
	private JButton getKaydetButton() {
		if (kaydetButton == null) {
			kaydetButton = new JButton();
			kaydetButton.setToolTipText("Kaydet");
			kaydetButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if(kategoriKoduTextField.getText().equals(""))
					    JOptionPane.showMessageDialog(KategoriGirisPanel.this,"Lütfen grup kodunu giriniz!","Eksik bilgi",JOptionPane.WARNING_MESSAGE);
					else if(kategoriTanimiTextField.getText().equals(""))
					    JOptionPane.showMessageDialog(KategoriGirisPanel.this,"Lütfen grup tanýmýný giriniz!","Eksik bilgi",JOptionPane.WARNING_MESSAGE);
					else{
					    ((Kategori)kategori).setTanim(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(kategoriTanimiTextField.getText()));
					    ((Kategori)kategori).setKod(kategoriKoduTextField.getText());
					    if(kategori.getId() != null){ // kategori duzeltme
					       if( AnaController.getInstance().getCurrentTanimlarController().kategoriTanimiDuzelt(kategori))// parent ayný kod-ad kontrolu
					       	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
					    }else{ // yeni kategori
					        if(AnaController.getInstance().getCurrentTanimlarController().treePaneleNodeEkle(parent,kategori))// parent ayný ad kontrolu
					        	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
					    }
					}
				}
			});
			kaydetButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/check.png")));
			kaydetButton.setBounds(250, 10, 24, 24);
		}
		return kaydetButton;
	}

    private JLabel getKategoriTanimiLabel() {
        if(kategoriTanimiLabel==null){
			kategoriTanimiLabel = new JLabel();
			kategoriTanimiLabel.setText("Grup Tanýmý:");
			kategoriTanimiLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			kategoriTanimiLabel.setBounds(75, 135, 100, 25);
			kategoriTanimiLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        }
        return kategoriTanimiLabel;
    }
    
    private JLabel getKategoriKoduLabel() {
        if(kategoriKoduLabel==null){
			kategoriKoduLabel = new JLabel();
			kategoriKoduLabel.setText("Grup Kodu");
			kategoriKoduLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			kategoriKoduLabel.setBounds(75, 80, 100, 25);
			kategoriKoduLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
		      
        }
        return kategoriKoduLabel;
    }
    
	private JTextField getKategoriTanimiTextField() {
		if (kategoriTanimiTextField == null) {
			kategoriTanimiTextField = new JTextField();
			kategoriTanimiTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			kategoriTanimiTextField.setBounds(175, 135, 200, 25);
		}
		return kategoriTanimiTextField;
	}
	private JTextField getKategoriKoduTextField() {
		if (kategoriKoduTextField == null) {
			kategoriKoduTextField = new JTextField();
			kategoriKoduTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			kategoriKoduTextField.setBounds(175, 80, 200, 25);
		}
		return kategoriKoduTextField;
	}
	public void loadYourself(IKategorilendirilebilir kategori,IKategorilendirilebilir parent) {
	    unLoadYourself();
	    
	    if(kategori == null){
	        this.kategori = new Kategori();
	        this.parent = parent;	        
	    }
	    else{
	         getKategoriTanimiTextField().setText(((Kategori)kategori).getTanim());  
	         getKategoriKoduTextField().setText(((Kategori)kategori).getKod());
	         this.kategori = kategori;
	   } 
	}
	
	public void unLoadYourself(){
	    this.getKategoriTanimiTextField().setText("");
	    this.getKategoriKoduTextField().setText("");
	}
	
	public String getPanelName() {
		return panelName;
	}
} 
