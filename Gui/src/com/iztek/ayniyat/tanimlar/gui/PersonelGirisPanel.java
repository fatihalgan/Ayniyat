package com.iztek.ayniyat.tanimlar.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.iztek.ayniyat.gui.service.InputController;
import com.iztek.ayniyat.gui.service.InputDuzenleyici;
import com.iztek.ayniyat.gui.service.IntegerInputListener;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.yerlesim.domain.Personel;

/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class PersonelGirisPanel extends AbstractGirisPanel{

	private JLabel sicilNoLabel = null;
	private JTextField sicilNoTextField = null;
	private JLabel soyadLabel = null;
	private JTextField soyadiTextField = null;
	private Personel personel = null;

	public PersonelGirisPanel(String panelName) {
		super(panelName);
	}

	private JLabel getSicilNoLabel() {
		if (sicilNoLabel == null) {
			sicilNoLabel = new JLabel();
			sicilNoLabel.setText("Sicil No:");
			sicilNoLabel.setFont(new java.awt.Font("Dialog",
					java.awt.Font.BOLD, 14));
			sicilNoLabel.setBounds(new java.awt.Rectangle(75, 50, 100, 25));
			sicilNoLabel.setBorder(BorderFactory.createLineBorder(new Color(
					204, 204, 204)));
		}
		return sicilNoLabel;
	}

	private JLabel getSoyadLabel() {
		if (soyadLabel == null) {
			soyadLabel = new JLabel();
			soyadLabel.setText("Soyadý:");
			soyadLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD,
					14));
			soyadLabel.setBounds(new java.awt.Rectangle(75, 140, 100, 25));
			soyadLabel.setBorder(BorderFactory.createLineBorder(new Color(204,
					204, 204)));
		}
		return soyadLabel;
	}

	public void kaydetAction() {
		if (getSicilNoTextField().getText().equals("")
				|| getTextField().getText().equals("")
				|| getSoyadiTextField().getText().equals("")) {
			JOptionPane
					.showMessageDialog(
							PersonelGirisPanel.this,
							"Personele ait Sicil No,Ad ve Soyad alanlarý doldurulmalýdýr!",
							"Eksik bilgi", JOptionPane.ERROR_MESSAGE);
		} else if (!InputController.isInteger(getSicilNoTextField().getText())) {
			JOptionPane.showMessageDialog(PersonelGirisPanel.this,
					"Personel sicil no bilgisi sadece rakamlardan oluþabilir",
					"Hatalý girdi", JOptionPane.ERROR_MESSAGE);
		} else {
			personel.setSicilNo(InputDuzenleyici
					.kelimelerinBasHarfleriniDuzenle(getSicilNoTextField()
							.getText()));
			personel.setAd(InputDuzenleyici
					.kelimelerinBasHarfleriniDuzenle(getTextField().getText()));
			personel.setSoyad(InputDuzenleyici
					.kelimelerinBasHarfleriniDuzenle(getSoyadiTextField()
							.getText()));

			if (personel.getId() == null) { //yeni malzeme
				if (agacaYeniNodeGir(personel))
					AnaController.getInstance().getCurrentTanimlarController()
							.panelOneGetir("bos");

			} else { // malzeme duzelt
				if (nodeDuzelt(personel))
					AnaController.getInstance().getCurrentTanimlarController()
							.panelOneGetir("bos");
			}
		}
	}

	private JTextField getSicilNoTextField() {
		if (sicilNoTextField == null) {
			sicilNoTextField = new JTextField();
			sicilNoTextField.setFont(new java.awt.Font("Dialog",
					java.awt.Font.BOLD, 14));
			sicilNoTextField
					.setBounds(new java.awt.Rectangle(175, 50, 200, 25));
			sicilNoTextField.addKeyListener(new IntegerInputListener());
		}
		return sicilNoTextField;
	}

	private JTextField getSoyadiTextField() {
		if (soyadiTextField == null) {
			soyadiTextField = new JTextField();
			soyadiTextField.setFont(new java.awt.Font("Dialog",
					java.awt.Font.BOLD, 14));
			soyadiTextField
					.setBounds(new java.awt.Rectangle(175, 140, 200, 25));
		}
		return soyadiTextField;
	}

	public void loadYourself(IKategorilendirilebilir pers,
			IKategorilendirilebilir parentNode) {
		getJPanel().add(getSicilNoLabel(), null);
		getJPanel().add(getSicilNoTextField(), null);
		getJPanel().add(getSoyadLabel(), null);
		getJPanel().add(getSoyadiTextField(), null);
		getTextField().setBounds(175, 95, 200, 25);
		getLabel1().setBounds(75, 95, 100, 25);

		unLoadYourself(); // ekrani temizle 
		if (pers == null) { //yeni personel girisi
			this.personel = new Personel();
			super.anaKategori = parentNode;
			getLabel1().setText("Adý :");
		} else { //personel duzeltme iþlemi
			this.personel = (Personel) pers;
			getLabel1().setText("Adý :");
			loadYourself(personel.getSicilNo(), personel.getAd(), personel
					.getSoyad());
		}
	}

	public void loadYourself(String sicilNo, String ad, String soyad) {
		getSicilNoTextField().setText(sicilNo);
		getTextField().setText(ad);
		getSoyadiTextField().setText(soyad);
	}

	public void unLoadYourself() {
		getSicilNoTextField().setText("");
		getTextField().setText("");
		getSoyadiTextField().setText("");
	}

} 
