/*
 * Created on 05.Tem.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.login.gui;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.iztek.ayniyat.event.KategoriEventListener;
import com.iztek.ayniyat.event.KategoriEventServiceImpl;
import com.iztek.ayniyat.kullanici.Kullanici;
import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.anapanel.gui.MalzemeHareketleriAnaPanel;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.event.EventController;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;
/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoginGui extends JFrame {

	private javax.swing.JPanel jContentPane = null;
	
	private JLabel ambarLabel = null;
	private JLabel userLabel = null;
	private JLabel passwordLabel = null;
	private JComboBox ambarComboBox = null;
	private JTextField userTextField = null;
	private JPasswordField jPasswordField = null;
	private JButton tamamButton = null;  //  @jve:decl-index=0:
	private JButton cikisButton = null;
	private Collection ambarlar = null;
	
	public LoginGui() {
		super();
		initialize();		
	}

	public void initialize() {
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setBounds(0, 0, 360, 250);
		this.setContentPane(getJContentPane());
        this.setLocationRelativeTo(null);
        this.setTitle("Kullanýcý Giriþi");
	}
	
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {;
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getAmbarLabel(), null);
			jContentPane.add(getUserLabel(), null);
			jContentPane.add(getPasswordLabel(), null);
			jContentPane.add(getAmbarComboBox(), null);
			jContentPane.add(getUserTextField(), null);
			jContentPane.add(getJPasswordField(), null);
			jContentPane.add(getTamamButton(), null);
			jContentPane.add(getCikisButton(), null);
		}
		return jContentPane;
	}
	
	private JComboBox getAmbarComboBox() {
		if (ambarComboBox == null) {
			ambarComboBox = new JComboBox();
			ambarComboBox.setBounds(150, 50, 150, 20);
			Iterator iter = getAmbarlar().iterator();
			while (iter.hasNext()) 
				ambarComboBox.addItem(iter.next());
		}
		return ambarComboBox;
	}
	
	/**
	 * This method initializes ambarLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */    
	private JLabel getAmbarLabel() {
		if (ambarLabel == null) {
			ambarLabel = new JLabel();
			ambarLabel.setBounds(50, 50, 100, 20);
			ambarLabel.setText("Ambar");
		}
		return ambarLabel;
	}	
	
	/**
	 * This method initializes ambarLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */    
	private JLabel getUserLabel() {
		if (userLabel == null) {
		    userLabel = new JLabel();
			userLabel.setBounds(50, 80, 100, 20);
			userLabel.setText("Kullanýcý Adý");
		}
		return userLabel;
	}
	
	/**
	 * This method initializes ambarLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */    
	private JLabel getPasswordLabel() {
		if (passwordLabel == null) {
		    passwordLabel = new JLabel();
			passwordLabel.setBounds(50, 110, 100, 20);
			passwordLabel.setText("Þifre");
		}
		return passwordLabel;
	}
	
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getUserTextField() {
		if (userTextField == null) {
			userTextField = new JTextField();
			userTextField.setBounds(150, 80, 150, 20);
		}
		return userTextField;
	}
	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */    
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(150, 110, 150, 20);
			jPasswordField.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					girisIslemi();
				}
			});
		}
		return jPasswordField;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getTamamButton() {
		if (tamamButton == null) {
			tamamButton = new JButton();
			tamamButton.setBounds(80, 160, 70, 25);
			tamamButton.setText("Giriþ");
			tamamButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {
				    girisIslemi();
				}
			});
		}
		return tamamButton;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getCikisButton() {
		if (cikisButton == null) {
			cikisButton = new JButton();
			cikisButton.setBounds(200, 160, 70, 25);
			cikisButton.setText("Çýkýþ");
			cikisButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				//    AnaController.getInstance().getAnaPanel().dispose();
				    LoginGui.this.dispose();
				}
			});
		}
		return cikisButton;
	}
	
	private Collection getAmbarlar() {
		try{
		ambarlar = YerlesimManager.getAmbarlar();
		}catch(Exception ex){
			showerror(ex.toString());
			System.exit(0);
		}
		return ambarlar;
    }
	
	private void girisIslemi(){
		Ambar ambar = null;
		Kullanici kullanici = null;
		boolean girisIzni=false;
			    
	    ambar= (Ambar) ambarComboBox.getSelectedItem();
	    kullanici=KullaniciManager.getKullaniciByUserNameAndPassword(userTextField.getText(),
	            String.valueOf(jPasswordField.getPassword()));
	    if(kullanici != null){
	      girisIzni=KullaniciManager.isAmbarInKullaniciAmbarList(kullanici,ambar);
	    }
	    if(girisIzni){
	        LoginGui.this.dispose();
	        AnaController.getInstance().setIslemYapanAmbar((Ambar)ambarComboBox.getSelectedItem());
	        AnaController.getInstance().setKullanici(kullanici);
	        if( AnaController.getInstance().getAnaPanel() == null){
	            MalzemeHareketleriAnaPanel malzemeHareketleriAnaPanel = new MalzemeHareketleriAnaPanel();
	            malzemeHareketleriAnaPanel.setVisible(true);
	        }
	        AnaController.getInstance().getAnaPanel().refreshMalzemeHareketleriAnaPanel(ambarComboBox.getSelectedItem().toString());
	    }else{
	        Object[] options = {"Tamam"};
	    	JOptionPane.showOptionDialog(LoginGui.this,
	    	        "Giriþ bilgileriniz geçersiz. Lütfen kontrol edip tekrar deneyin. ","Uyarý",JOptionPane.YES_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
		}
	}
	
	public void showerror(String message){
		AnaController.getInstance().showWarningDialogBox(LoginGui.this,message);
	}
	
    
	public static void main(String[] args) {
		LoginGui loginGui=null;
//		DataPersister dataPersister = new DataPersister();		
		KategoriEventListener tanimlarEventController=new EventController();
		try {
	//		dataPersister.setUp();
			KategoriEventServiceImpl.getTanimlarEventService().addKategoriEventListener(tanimlarEventController);
			loginGui=new LoginGui();
			loginGui.setVisible(true);
		} catch (Exception exception) {
			loginGui.showerror(exception.toString());
			System.err.println(exception);
		}
	}
}
