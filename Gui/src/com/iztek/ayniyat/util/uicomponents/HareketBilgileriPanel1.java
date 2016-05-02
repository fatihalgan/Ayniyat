package com.iztek.ayniyat.util.uicomponents;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.iztek.ayniyat.malzemehareketleri.service.MalzemeHareketFisiManager;

public class HareketBilgileriPanel1 extends HareketBilgileriPanel {

/**
 * @author fusun
 */
	private static final long serialVersionUID = 1L;
	private JLabel teslimEdenLabel = null;
	//private JTextField alindigiKurulusTextField = null;
	private JTextField teslimEdenTextField = null;
	private JLabel alindigiKuruluslabel = null;
	private JComboBox alindigiKurulusComboBox=null;  //  @jve:decl-index=0:visual-constraint="89,242"
	/**
	 * This is the default constructor
	 */
	public HareketBilgileriPanel1() {
		super();
		
		alindigiKuruluslabel = new JLabel();
		alindigiKuruluslabel.setBounds(new java.awt.Rectangle(16,131,120,20));
		alindigiKuruluslabel.setText("Al\u0131nd\u0131\u011f\u0131 Kurulu\u015f:");
		alindigiKuruluslabel.setFont(new Font("Dialog", Font.BOLD, 13));
		teslimEdenLabel = new JLabel();
		teslimEdenLabel.setBounds(new java.awt.Rectangle(16,160,120,20));
		teslimEdenLabel.setText("Teslim Eden :");
		teslimEdenLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		super.getHareketBilgileriPanel().add(alindigiKuruluslabel,null);
		super.getHareketBilgileriPanel().add(teslimEdenLabel,null);
	//	super.getHareketBilgileriPanel().add(getAlindigiKurulusTextField(),null);
		super.getHareketBilgileriPanel().add(getAlindigiKurulusComboBox(),null);
		super.getHareketBilgileriPanel().add(getTeslimEdenTextField(),null);
		hareketSekliLabel.setText("Giri\u015f \u015eekli :");
		hareketTarihiLabel.setText("Giri\u015f Tarihi :");
		hareketiOnaylayanLabel.setText("Giri\u015fi Onaylayan :");
		
	}

	public JComboBox getAlindigiKurulusComboBox(){
		if(alindigiKurulusComboBox==null){
			Collection alindigiKurulus=MalzemeHareketFisiManager.getAlindigiKurulusInHareketFisleri();
			alindigiKurulusComboBox=new JComboBox(alindigiKurulus.toArray());
			alindigiKurulusComboBox.setBounds(new Rectangle(143, 131, 230, 20));
			alindigiKurulusComboBox.setEditable(true);
		}
		return alindigiKurulusComboBox;
	}
/*	public JTextField getAlindigiKurulusTextField() {
		if (alindigiKurulusTextField == null) {
			alindigiKurulusTextField = new JTextField();
			alindigiKurulusTextField.setBounds(new Rectangle(143, 131, 230, 20));
			alindigiKurulusTextField.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return alindigiKurulusTextField;
	}*/

	public JTextField getTeslimEdenTextField() {
		if (teslimEdenTextField == null) {
			teslimEdenTextField = new JTextField();
			teslimEdenTextField.setBounds(new Rectangle(143, 160, 230, 20));
			teslimEdenTextField.setFont(new Font("Dialog", Font.BOLD, 13));
		}
		return teslimEdenTextField;
	}

	public void unLoadYourself(){
	//	getHareketSekliTextField().setText("");
	//	getAlindigiKurulusTextField().setText("");
		getAlindigiKurulusComboBox().setSelectedIndex(0);
		getBelgeNoTextField().setText("");
		getTeslimEdenTextField().setText("");
	}

}
