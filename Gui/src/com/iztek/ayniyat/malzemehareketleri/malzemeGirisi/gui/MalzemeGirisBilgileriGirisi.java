/**
 * @author Sevgi Uslu
 */

package com.iztek.ayniyat.malzemehareketleri.malzemeGirisi.gui;

import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import com.iztek.ayniyat.gui.service.InputController;
import com.iztek.ayniyat.gui.service.IntegerInputListener;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemePahasi;
import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.util.uicomponents.NiteliklerTablePanel;
import com.iztek.commons.money.Money;

public class MalzemeGirisBilgileriGirisi extends JDialog implements IAyniyatPanel{

	private javax.swing.JPanel jContentPane = null;
	private javax.swing.JPanel girisBilgileriPanel = null;
	private NiteliklerTablePanel niteliklerTable=null;
	private javax.swing.JPanel buttonPanel = null;
	private JLabel malzemeLabel = null;
	private JLabel malzemeLabel1 = null;
	private javax.swing.JLabel miktarLabel = null;
	private javax.swing.JLabel vergiliFiyatLabel = null;
	private javax.swing.JLabel vergisizFiyatLabel = null;
	private javax.swing.JLabel kdvOraniLabel = null;
	private javax.swing.JLabel otvOraniLabel = null;
	private javax.swing.JLabel iskontoOraniLabel = null;
	private javax.swing.JTextField miktarTextField = null;
	private javax.swing.JTextField vergisizFiyatTextField = null;
	private javax.swing.JTextField vergiliFiyatTextField = null;
	private javax.swing.JTextField iskontoOraniTextField = null;
	private JComboBox kdvOraniComboBox = null;
	private JComboBox otvOraniComboBox = null;
	private javax.swing.JButton tamamButton = null;
	private javax.swing.JButton cikisButton = null;
   private AbstractMalzemeTanimi malzemeTanimi=null;
    private String panelName=null;
    private String errorMessage=null;
	
	private JLabel jBirimFiyatLabel = null;
	private JRadioButton vergisizRadioButton = null;
	private JRadioButton vergiliRadioButton = null;
	/**
	 * This is the default constructor
	 */
	public MalzemeGirisBilgileriGirisi(JFrame parent,String panelName,AbstractMalzemeTanimi malzemeTanimi) {
		super(parent ,true);
		this.malzemeTanimi=malzemeTanimi;
		initialize();	
	    getNiteliklerTable().loadNiteliklerTable(AnaController.getInstance().getMalzemeCikisiController().getNiteliklerForMalzemeSecimleriTable(malzemeTanimi),malzemeTanimi.getNitelikTanimlari().iterator());

	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
		this.setBounds(0, 0, 350, 500);
		this.setContentPane(getJContentPane());
		this.setTitle("Malzeme Giris Bilgileri Girisi");
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getGirisBilgileriPanel(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getNiteliklerTable(),java.awt.BorderLayout.CENTER);
			//		jContentPane.add(getNitelikBilgileriPanel(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	
	private NiteliklerTablePanel getNiteliklerTable(){
		if(niteliklerTable==null){
			niteliklerTable=new NiteliklerTablePanel();
			niteliklerTable.getNiteliklerTable().setEditingColumn(1);
			niteliklerTable.getJScrollPane().setSize(350,130);
			niteliklerTable.getNiteliklerTable().setSize(300,150);
		}
		return niteliklerTable;
	}
	
	/**
	 * This method initializes getGirisBilgileriPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	
	private javax.swing.JPanel getGirisBilgileriPanel() {
		if(girisBilgileriPanel == null) {
			girisBilgileriPanel = new javax.swing.JPanel();
			girisBilgileriPanel.setPreferredSize(new java.awt.Dimension(1,300));
			girisBilgileriPanel.setLayout(null);
			girisBilgileriPanel.add(getMalzemeLabel(), null);
			girisBilgileriPanel.add(getMalzemeLabel1(), null);
			girisBilgileriPanel.add(getMiktarLabel(), null);
			girisBilgileriPanel.add(getVergiliFiyatLabel(), null);
			girisBilgileriPanel.add(getVergisizFiyatLabel(), null);
			girisBilgileriPanel.add(getKdvOraniLabel(), null);
			girisBilgileriPanel.add(getOtvOraniLabel(), null);			
			girisBilgileriPanel.add(getIskontoOraniLabel(), null);
			girisBilgileriPanel.add(getJBirimFiyatLabel(), null);		
			girisBilgileriPanel.add(getMiktarTextField(), null);
			girisBilgileriPanel.add(getVergisizFiyatTextField(), null);
			girisBilgileriPanel.add(getVergiliFiyatTextField(), null);
			girisBilgileriPanel.add(getKdvOraniComboBox(), null);
			girisBilgileriPanel.add(getOtvOraniComboBox(), null);
			girisBilgileriPanel.add(getIskontoOraniTextField(), null);
			girisBilgileriPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Fatura Bilgileri", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));

			girisBilgileriPanel.add(getVergisizRadioButton(), null);
			girisBilgileriPanel.add(getVergiliRadioButton(), null);
			
			vergiliFiyatLabel.setEnabled(false);
			vergiliFiyatTextField.setEnabled(false);
		}
		return girisBilgileriPanel;
	}

	private javax.swing.JPanel getButtonPanel() {
		if(buttonPanel == null) {
		    buttonPanel=new JPanel();
		    buttonPanel.setLayout(null);
		    buttonPanel.add(getTamamButton(), null);
		    buttonPanel.add(getCikisButton(), null);
		    buttonPanel.setPreferredSize(new java.awt.Dimension(1,35));
		}
		return buttonPanel;
	}
	/*
	 * Füsun Çetin
	 */	

	/**
	 * This method initializes malzemeLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getMalzemeLabel() {
		if(malzemeLabel == null) {
			malzemeLabel =new javax.swing.JLabel();
			malzemeLabel.setBounds(25, 25, 125, 20);
			malzemeLabel.setText("Malzeme :");
		}
		return malzemeLabel;
	}
	
	/**
	 * This method initializes malzemeLabel1
	 * 
	 * @return javax.swing.JLabel
	 */
	private JLabel getMalzemeLabel1() {
		if(malzemeLabel1 == null) {
			malzemeLabel1 =new javax.swing.JLabel();
			malzemeLabel1.setBounds(150, 25, 150, 20);
			malzemeLabel1.setText(malzemeTanimi.getTanim());
		}
        return malzemeLabel1;
    }
    
	/**
	 * This method initializes miktarLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getMiktarLabel() {
		if(miktarLabel == null) {
			miktarLabel = new javax.swing.JLabel();
			miktarLabel.setBounds(25, 55, 125, 20);
			miktarLabel.setText("Miktar (Adet) :");
		}
		return miktarLabel;
	}
	/**
	 * This method initializes vergiliFiyatLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJBirimFiyatLabel() {
		if(jBirimFiyatLabel == null) {
		    jBirimFiyatLabel = new javax.swing.JLabel();
			jBirimFiyatLabel.setBounds(25, 85, 100, 20);
			jBirimFiyatLabel.setText("Birim Fiyat");
		}
		return jBirimFiyatLabel;
	}
	/**
	 * This method initializes vergiliFiyatLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getVergiliFiyatLabel() {
		if(vergiliFiyatLabel == null) {
			vergiliFiyatLabel = new javax.swing.JLabel();
			vergiliFiyatLabel.setBounds(50, 145, 100, 20);
			vergiliFiyatLabel.setText("Vergili:");
		}
		return vergiliFiyatLabel;
	}
	/**
	 * This method initializes vergisizFiyatLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getVergisizFiyatLabel() {
		if(vergisizFiyatLabel == null) {
			vergisizFiyatLabel = new javax.swing.JLabel();
			vergisizFiyatLabel.setBounds(50, 115, 100, 20);
			vergisizFiyatLabel.setText("Vergisiz:");
		}
		return vergisizFiyatLabel;
	}
	/**
	 * This method initializes kdvOraniLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getKdvOraniLabel() {
		if(kdvOraniLabel == null) {
			kdvOraniLabel = new javax.swing.JLabel();
			kdvOraniLabel.setBounds(25, 175, 125, 20);
			kdvOraniLabel.setText("KDV Oraný (%) :");
		}
		return kdvOraniLabel;
	}
	
	/**
	 * This method initializes otvOraniLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getOtvOraniLabel() {
		if(otvOraniLabel == null) {
			otvOraniLabel = new javax.swing.JLabel();
			otvOraniLabel.setBounds(25, 205, 125, 20);
			otvOraniLabel.setText("ÖTV Oraný(%):");
		}
		return otvOraniLabel;
	}

    
	/**
	 * This method initializes iskontoOraniLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getIskontoOraniLabel() {
		if(iskontoOraniLabel == null) {
			iskontoOraniLabel = new javax.swing.JLabel();
			iskontoOraniLabel.setBounds(25, 235, 125, 20);
			iskontoOraniLabel.setText("Ýskonto Oraný (%) :");
		}
		return iskontoOraniLabel;
	}


	/**
	 * This method initializes miktarTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public javax.swing.JTextField getMiktarTextField() {
		if(miktarTextField == null) {
			miktarTextField = new javax.swing.JTextField();
			miktarTextField.setBounds(150, 55, 150, 20);
			miktarTextField.addKeyListener(new IntegerInputListener());
		}
		return miktarTextField;
	}
	/**
	 * This method initializes vergisizFiyatTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public javax.swing.JTextField getVergisizFiyatTextField() {
		if(vergisizFiyatTextField == null) {
			vergisizFiyatTextField = new javax.swing.JTextField();
			vergisizFiyatTextField.setBounds(150, 115, 150, 20);
			vergisizFiyatTextField.addKeyListener(new IntegerInputListener());
		}
		return vergisizFiyatTextField;
	}
	/**
	 * This method initializes vergiliFiyatTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public javax.swing.JTextField getVergiliFiyatTextField() {
		if(vergiliFiyatTextField == null) {
			vergiliFiyatTextField = new javax.swing.JTextField();
			vergiliFiyatTextField.setBounds(150, 145, 150, 20);
			vergiliFiyatTextField.addKeyListener(new IntegerInputListener());
		}
		return vergiliFiyatTextField;
	}
	/**
	 * This method initializes iskontoOraniTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	public javax.swing.JTextField getIskontoOraniTextField() {
		if(iskontoOraniTextField == null) {
			iskontoOraniTextField = new javax.swing.JTextField();
			iskontoOraniTextField.setBounds(150, 235, 150, 20);
			iskontoOraniTextField.addKeyListener(new IntegerInputListener());
		}
		return iskontoOraniTextField;
	}
 

	/**
	 * This method initializes kdvOraniComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	public JComboBox getKdvOraniComboBox() {
		if (kdvOraniComboBox == null) {
			kdvOraniComboBox = new JComboBox();
			kdvOraniComboBox.setBounds(150, 175, 50, 20);			
			for (int i = 0; i < 51; i++) {
                kdvOraniComboBox.addItem(new Integer(i));
            }
		}
		return kdvOraniComboBox;
	}
	/**
	 * This method initializes otvOraniComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */    
	public JComboBox getOtvOraniComboBox() {
		if (otvOraniComboBox == null) {
			otvOraniComboBox = new JComboBox();
			otvOraniComboBox.setBounds(150, 205, 50, 20);
			
			for (int i = 0; i < 51; i++) {
                otvOraniComboBox.addItem(new Integer(i));
            }
		}
		return otvOraniComboBox;
	}

	
	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getTamamButton() {
		if(tamamButton == null) {
			tamamButton = new javax.swing.JButton();
			tamamButton.setBounds(220, 5, 24, 24);
			tamamButton.setToolTipText("Tamam");
			tamamButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/check.png")));
			tamamButton.addActionListener(new java.awt.event.ActionListener() { 
					public void actionPerformed(java.awt.event.ActionEvent e) {   
					    if (getNiteliklerTable().getNiteliklerTable().isEditing()) {
					    	getNiteliklerTable().getNiteliklerTable().getCellEditor().stopCellEditing();
					    }
					    if(!checkInputFields()){
					        JOptionPane.showMessageDialog( null,errorMessage,"Uyarý",JOptionPane.WARNING_MESSAGE );
					    }else{
					        AnaController.getInstance().getMalzemeGirisiController().tabloyaEkle(getAllInputFields());
					        dispose();
					    }
					}
			});
		}
		return tamamButton;
	}
	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getCikisButton() {
		if(cikisButton == null) {
			cikisButton = new javax.swing.JButton();
			cikisButton.setBounds(98, 5, 24, 24);
			cikisButton.setToolTipText("Ýptal");
			cikisButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/error.png")));
			cikisButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					dispose();
				}
			});
		}
		return cikisButton;
	}

	public boolean checkInputFields(){
	    if( !InputController.isInteger(getMiktarTextField().getText()) || (Integer.parseInt(getMiktarTextField().getText())==0)){
	        errorMessage="Lütfen miktar için pozitif bir tamsayý giriniz.";
	        return false;
	    }/*else if(!InputController.isDouble(getVergisizFiyatTextField().getText()) && 
	            !InputController.isDouble(getVergiliFiyatTextField().getText())){
	        errorMessage="Lütfen birim fiyat alanlarýndan biri için bir sayý giriniz.";
	        return false;
	    }else if(!getVergiliFiyatTextField().getText().equals("")  && !InputController.isDouble(getVergiliFiyatTextField().getText())){
	        errorMessage="Lütfen vergili birim fiyat için bir sayý giriniz.";
	        return false;
	    }*/else if(!getIskontoOraniTextField().getText().equals("")  && !InputController.isInteger(getIskontoOraniTextField().getText())){
	        errorMessage="Lütfen iskonto oraný için bir tamsayý giriniz.";
	        return false;
	    /*}else if(((Integer)getKdvOraniComboBox().getSelectedItem()).intValue()==0 && ((Integer)getOtvOraniComboBox().getSelectedItem()).intValue()==0){
	        errorMessage="Lütfen bütün giriþ bilgilerini doldurunuz";
	        return false;*/
	    }else{
	        for (int i = 0; i < getNiteliklerTable().getNiteliklerTable().getRowCount(); i++) {
          //      if(((String)getJTable().getValueAt(i,1)).equals("")){
	        	 if(((String)getNiteliklerTable().getNiteliklerTable().getValueAt(i,1)).equals("")){
                    errorMessage="Lütfen bütün nitelik bilgilerini doldurunuz";
                	return false;
                }
            }	        
	    }	  
	    return true;
	}
	
	public Vector getAllInputFields(){
	    Vector inputs=new Vector();	    
	    double vergisizTutar= 0;
	    double vergiliTutar= 0;
	    int miktar = 0;	    
	    //TODO daha sonra malzeme tanýmýnýn tuketim malzemesi olmasýna göre ayarlanacak
	    AbstractMalzeme malzeme=new DemirbasMalzeme();
		MalzemePahasi malzemePahasi = new MalzemePahasi();	
		
		miktar = Integer.parseInt(miktarTextField.getText());		
		if(!getKdvOraniComboBox().getSelectedItem().equals(""))
			malzemePahasi.setKdvOrani(new Float(getKdvOraniComboBox().getSelectedItem().toString()));
		if(!getOtvOraniComboBox().getSelectedItem().equals(""))
		    malzemePahasi.setOtvOrani(new Float(getOtvOraniComboBox().getSelectedItem().toString()));
		if(!vergisizFiyatTextField.getText().equals("")){
		    malzemePahasi.setVergisizBirimFiyat(Money.yeniTurkLirasi(new BigDecimal(vergisizFiyatTextField.getText())));
		}else if(!vergiliFiyatTextField.getText().equals("")){
		    malzemePahasi.setVergiliBirimFiyat(Money.yeniTurkLirasi(new BigDecimal(vergiliFiyatTextField.getText())));
		}else {
		    malzemePahasi.setVergiliBirimFiyat(Money.yeniTurkLirasi(new BigDecimal(0)));
		}
		if(!getIskontoOraniTextField().getText().equals("")){
		    BigDecimal birimFiyat=malzemePahasi.getVergisizBirimFiyatMiktar();
		    double oran=Double.parseDouble(iskontoOraniTextField.getText())/100;
		    malzemePahasi.setIskontoTutari(Money.yeniTurkLirasi(new BigDecimal(birimFiyat.doubleValue()*oran*miktar)));
		}
	    vergiliTutar=malzemePahasi.getVergiliBirimFiyatMiktar().doubleValue()* miktar;
	    vergisizTutar=malzemePahasi.getVergisizBirimFiyatMiktar().doubleValue() * miktar;
		malzeme.setMalzemeTanimi(malzemeTanimi);
		malzeme.setPaha(malzemePahasi);
	    
		for (int i = 0; i < getNiteliklerTable().getNiteliklerTable().getRowCount(); i++) 
		    malzeme.addNitelikDegeri(new NitelikDegeri(getNiteliklerTable().getNiteliklerTable().getValueAt(i,0).toString(),getNiteliklerTable().getNiteliklerTable().getValueAt(i,1).toString()));
	    inputs.add(malzeme);
	    inputs.add(miktarTextField.getText());
	    inputs.add(malzemeTanimi.getBirim());
	    inputs.add(malzemePahasi.getVergisizBirimFiyatMiktar());
	    inputs.add(Double.toString(vergisizTutar));
	    inputs.add(malzemePahasi.getKdvOrani());
	    inputs.add(malzemePahasi.getOtvOrani());
	    inputs.add(getIskontoOraniTextField().getText());
	    inputs.add(new Double(vergiliTutar).toString());	   
	    return inputs;
	}
	
    public String getPanelName() {
        return panelName;
    }

    public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {

    }

    public void unLoadYourself() {
        
    }
	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */    
	private JRadioButton getVergisizRadioButton() {
		if (vergisizRadioButton == null) {
			vergisizRadioButton = new JRadioButton();
			vergisizRadioButton.setBounds(25, 115, 21, 21);
			vergisizRadioButton.setSelected(true);
			vergisizRadioButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					vergiliRadioButton.setSelected(false);
				    vergiliFiyatLabel.setEnabled(false);
				    vergiliFiyatTextField.setText("");
				    vergiliFiyatTextField.setEnabled(false);
				    vergisizFiyatLabel.setEnabled(true);
				    vergisizFiyatTextField.setEnabled(true);
				}
			});
		}
		return vergisizRadioButton;
	}
	/**
	 * This method initializes jRadioButton1	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */    
	private JRadioButton getVergiliRadioButton() {
		if (vergiliRadioButton == null) {
			vergiliRadioButton = new JRadioButton();
			vergiliRadioButton.setBounds(25, 145, 21, 21);
			vergiliRadioButton.setSelected(false);
			vergiliRadioButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					vergisizRadioButton.setSelected(false);
				    vergisizFiyatLabel.setEnabled(false);
				    vergisizFiyatTextField.setText("");
				    vergisizFiyatTextField.setEnabled(false);
				    vergiliFiyatLabel.setEnabled(true);
				    vergiliFiyatTextField.setEnabled(true);
				}
			});
		}
		return vergiliRadioButton;
	}
  }  //  @jve:decl-index=0:visual-constraint="10,10"