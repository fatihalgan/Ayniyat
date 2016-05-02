package com.iztek.ayniyat.util.uicomponents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.iztek.ayniyat.panel.IMalzemeHareketleriPanel;
import com.iztek.ayniyat.util.uiactions.CikisAction;
import com.iztek.ayniyat.util.uiactions.KaydetAction;
import com.iztek.ayniyat.util.uiactions.TablodanCikarAction;
import com.iztek.ayniyat.util.uiactions.TabloyaEkleAction;
import com.iztek.ayniyat.util.uiactions.TabloyuTemizleAction;

public class ButtonPanel1 extends JPanel {
	private static final long serialVersionUID = 1L;
	public JButton EkleButton = null;
	public JButton CikarButton = null;
	public JButton TemizleButton = null;
	public JButton KaydetButton = null;
	public JButton CikisButton = null;
	public JButton DevretButton=null;
	public IMalzemeHareketleriPanel activePanel=null;
	public JPopupMenu Popup = null;  
	public JMenuItem ekleMenuItem = null;
	public JMenuItem cikarMenuItem = null;
	public JMenuItem temizleMenuItem = null;
	public JMenuItem kaydetMenuItem = null;
	public JMenuItem devretMenuItem=null;

	public ButtonPanel1(){
		super();
		initialize();
	}
	public void registerPanel(IMalzemeHareketleriPanel panel){
		this.activePanel=panel;
	}
	
	private void initialize() {
		 GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        gridBagConstraints11.gridx = 3;
        gridBagConstraints11.insets = new java.awt.Insets(9,46,7,46);
        gridBagConstraints11.ipadx = -34;
        gridBagConstraints11.ipady = -10;
        gridBagConstraints11.gridy = 0;
        GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.insets = new java.awt.Insets(9,46,7,46);
        gridBagConstraints4.gridy = 0;
        gridBagConstraints4.ipadx = -34;
        gridBagConstraints4.ipady = -10;
        gridBagConstraints4.gridx = 1;
        GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.insets = new java.awt.Insets(9,92,7,46);
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.ipadx = -34;
        gridBagConstraints3.ipady = -10;
        gridBagConstraints3.gridx = 0;
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(9,46,7,46);
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.ipadx = -34;
        gridBagConstraints2.ipady = -10;
        gridBagConstraints2.gridx = 2;
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.insets = new java.awt.Insets(9,46,7,46);
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.ipadx = -34;
        gridBagConstraints1.ipady = -10;
        gridBagConstraints1.gridx = 3;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(9,46,7,46);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -34;
        gridBagConstraints.ipady = -10;
        gridBagConstraints.gridx = 4;
        this.setLayout(new GridBagLayout());
        this.setBounds(new java.awt.Rectangle(0,0,800,41));
        this.add(getCikisButton(), gridBagConstraints);
        this.add(getKaydetButton(), gridBagConstraints1);
        this.add(getCikarButton(), gridBagConstraints2);
        this.add(getEkleButton(), gridBagConstraints3);
        this.add(getTemizleButton(), gridBagConstraints4);
        this.add(getDevretButton(), gridBagConstraints11);
       }

	public JButton getEkleButton() {
		if (EkleButton == null) {
			EkleButton = new JButton();
			EkleButton.setToolTipText("Ekle");
			EkleButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/row_add.png")));
			EkleButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new TabloyaEkleAction(activePanel).execute();
				}
			});
		}
		return EkleButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getCikarButton() {
		if (CikarButton == null) {
			CikarButton = new JButton();
			CikarButton.setEnabled(false);
			CikarButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/row_delete.png")));
			CikarButton.setToolTipText("Çýkar");
			CikarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new TablodanCikarAction(activePanel).execute();
				}
			});
		}
		return CikarButton;
	}

	public JButton getTemizleButton() {
		if (TemizleButton == null) {
			TemizleButton = new JButton();
			TemizleButton.setEnabled(false);
			TemizleButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/table_refresh.png")));
			TemizleButton.setToolTipText("Temizle");
			TemizleButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new TabloyuTemizleAction(activePanel).execute();
				}
			});
		}
		return TemizleButton;
	}

	public JButton getKaydetButton() {
		if (KaydetButton == null) {
			KaydetButton = new JButton();
			KaydetButton.setEnabled(false);
			KaydetButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/disk_blue.png")));
			KaydetButton.setToolTipText("Kaydet");
			KaydetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new KaydetAction(activePanel).execute();
				}
			});
		}
		return KaydetButton;
	}

	public JButton getCikisButton() {
		if (CikisButton == null) {
			CikisButton = new JButton();
			CikisButton.setToolTipText("Çýkýþ");
			CikisButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/home.png")));
			CikisButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new CikisAction().execute();
				}
			});
		}
		return CikisButton;
	}

	public JButton getDevretButton() {
		if (DevretButton == null) {
			DevretButton = new JButton();
			DevretButton.setEnabled(false);
			DevretButton.setVisible(false);
			DevretButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/businessman_add.png")));
			DevretButton.setToolTipText("Devret");
			DevretButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new KaydetAction(activePanel).execute();
				}
			});
		}
		return DevretButton;
	}
	public JPopupMenu getJPopupMenu() {
		if (Popup == null) {
			Popup = new JPopupMenu();
			Popup.add(getEkleMenuItem());
			Popup.add(getCikarMenuItem());
			Popup.add(getTemizleMenuItem());
			Popup.add(getKaydetMenuItem());
			Popup.add(getDevretMenuItem());
			
		}
		return Popup;
	}
	public JMenuItem getEkleMenuItem() {
		if (ekleMenuItem == null) {
			ekleMenuItem = new JMenuItem();
			ekleMenuItem.setText("Ekle");
			ekleMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new TabloyaEkleAction(activePanel).execute();
				}
			});
		}
		return ekleMenuItem;
	}
	public JMenuItem getCikarMenuItem() {
		if (cikarMenuItem == null) {
			cikarMenuItem = new JMenuItem();
			cikarMenuItem.setText("Çýkar");
			cikarMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new TablodanCikarAction(activePanel).execute();}
			});
		}
		return cikarMenuItem;
	}
	public JMenuItem getTemizleMenuItem() {
		if (temizleMenuItem == null) {
			temizleMenuItem = new JMenuItem();
			temizleMenuItem.setText("Temizle");
			temizleMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new TabloyuTemizleAction(activePanel).execute();
					}
			});
		}
		return temizleMenuItem;
	}
	public JMenuItem getKaydetMenuItem() {
		if (kaydetMenuItem == null) {
			kaydetMenuItem = new JMenuItem();
			kaydetMenuItem.setText("Kaydet");
			kaydetMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new KaydetAction(activePanel).execute();
					}
			});
		}
		return kaydetMenuItem;
	}
	public JMenuItem getDevretMenuItem() {
		if (devretMenuItem == null) {
			devretMenuItem = new JMenuItem();
			devretMenuItem.setText("Devret");
			devretMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new KaydetAction(activePanel).execute();
					}
			});
		}
		return devretMenuItem;
	}

}  


