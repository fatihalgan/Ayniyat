package com.iztek.ayniyat.tanimlar.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.iztek.ayniyat.gui.service.InputController;
import com.iztek.ayniyat.gui.service.InputDuzenleyici;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.yerlesim.domain.Oda;
/**
 * @author Umit Akyol + Cagdas CIRIT
 *
 */
public class OdaGirisPanel extends AbstractGirisPanel{

	private JLabel odaNoLabel = null;
	private Oda oda = null;
	private JTextField odaNoTextField = null;  
	private JLabel tipLabel = null;
	private JComboBox tipComboBox = null;

	public OdaGirisPanel(String panelName) {
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
   
    private JLabel getTipLabel() {
        if(tipLabel==null){
			tipLabel = new JLabel();
			tipLabel.setBounds(75, 140, 100, 25);
			tipLabel.setText("Tip:");
			tipLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			tipLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
       }
        return tipLabel;
    }
   
    public void kaydetAction() {
		if (getOdaNoTextField().getText().equals("")
				|| getTextField().getText().equals("")
				|| getTipComboBox().getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(OdaGirisPanel.this,
					"Oda no,kat,tip alanlarý doldurulmalýdýr!", "Eksik bilgi",
					JOptionPane.ERROR_MESSAGE);
			// }else
			// if(!InputController.isInteger(getOdaNoTextField().getText())){
			// JOptionPane.showMessageDialog(OdaGirisPanel.this,"Oda no bilgisi
			// sadece rakamlardan oluþabilir","Hatalý
			// girdi",JOptionPane.ERROR_MESSAGE);
		} else if (!InputController.isInteger(getTextField().getText())) {
			JOptionPane.showMessageDialog(OdaGirisPanel.this,
					"Kat bilgisi sadece rakamlardan oluþabilir",
					"Hatalý girdi", JOptionPane.ERROR_MESSAGE);
		} else {
			oda.setTanim(InputDuzenleyici
					.kelimelerinBasHarfleriniDuzenle(getOdaNoTextField()
							.getText()));
			oda.setKat(Integer.valueOf(getTextField().getText()));
			oda.setTip(InputDuzenleyici
					.kelimelerinBasHarfleriniDuzenle((String) getTipComboBox()
							.getSelectedItem()));

			if (oda.getId() == null) { // yeni oda
				if (agacaYeniNodeGir(oda))
					AnaController.getInstance().getCurrentTanimlarController()
							.panelOneGetir("bos");
			} else { // oda duzelt
				if (nodeDuzelt(oda))
					AnaController.getInstance().getCurrentTanimlarController()
							.panelOneGetir("bos");
			}
		}
	}
		
	private JTextField getOdaNoTextField() {
		if (odaNoTextField == null) {
			odaNoTextField = new JTextField();
			odaNoTextField.setBounds(175, 50, 200, 25);
			odaNoTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
		//	odaNoTextField.addKeyListener(new IntegerInputListener());
		}
		return odaNoTextField;
	}
  
	private JComboBox getTipComboBox() {
		if (tipComboBox == null) {
			tipComboBox = new JComboBox();
			tipComboBox.setBounds(175, 140, 200, 25);
			tipComboBox.setEditable(true);
			tipComboBox.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			loadTip2TipComboBox();
		}
		return tipComboBox;
	}
	
	public void loadYourself(IKategorilendirilebilir od,IKategorilendirilebilir parentNode){
		
		getJPanel().add(getOdaNoLabel(),null);
		getJPanel().add(getOdaNoTextField(),null);
		getJPanel().add(getTipLabel(),null);
		getJPanel().add(getTipComboBox(),null);
		getTextField().setBounds(175, 95, 200, 25);
		getLabel1().setBounds(75, 95, 100, 25);
		getLabel1().setText("Kat :");
		unLoadYourself(); // ekrani temizle 
	    if (od == null){ //yeni oda girisi
	        this.oda = new Oda();
	        super.anaKategori = parentNode;
	    }else{ //oda duzeltme iþlemi
	        this.oda = (Oda) od;
	        loadYourself(oda.getTanim(),oda.getKat(),oda.getTip());
	    }
	}
	
	public void loadYourself(String tanim,Integer kat,String tip ){
	    getOdaNoTextField().setText(tanim);
		getTextField().setText(""+kat.intValue());
		getTipComboBox().setSelectedItem(tip);   
	}
	
	public void unLoadYourself(){
	   getOdaNoTextField().setText("");
	   getTextField().setText("");
	   getTipComboBox().setSelectedItem("");	
	}
	
	private void loadTip2TipComboBox(){
	    //TODO muhtemelen odalar.txt diye bir dosyadan kendini yükleyecek.
	    getTipComboBox().addItem("");
	    getTipComboBox().addItem(Oda.SINIF);
	    getTipComboBox().addItem(Oda.LAB);
	    getTipComboBox().addItem(Oda.PERSONEL);
	    getTipComboBox().addItem(Oda.KANTIN);
	    getTipComboBox().addItem(Oda.KILER);
	    getTipComboBox().addItem(Oda.KONFERANS);
	}
}  
