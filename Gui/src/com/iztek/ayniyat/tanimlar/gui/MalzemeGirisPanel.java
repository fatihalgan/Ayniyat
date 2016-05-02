package com.iztek.ayniyat.tanimlar.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.iztek.ayniyat.gui.service.InputDuzenleyici;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.tanimlar.controller.MalzemeTanimlariController;

/**
 * @author Cagdas CIRIT
 */
public class MalzemeGirisPanel extends JPanel implements IAyniyatPanel{
	private JPanel buttonPanel = null;
	private JPanel malzemePanel = null; 
	private JPanel nitelikPanel = null;
    
	private JLabel malzemeTanimiLabel = null;
	private JLabel malzemeBirimiLabel = null;
	private JLabel nitelikSecLabel = null; 
	private JLabel nitelikEkleLabel = null;
	private JTextField tanimTextField = null;
	private JComboBox birimComboBox = null;
	private JComboBox nitelikComboBox = null;
	private JLabel malzemeKoduLabel = null;
	private JTextField  malzemeKoduTextField = null;
	
	private JButton iptalButton = null;
	private JButton kaydetButton = null;
	private JButton nitelikEkleButton = null;
	
	private String panelName = null;
	private JList nitelikList = null;
	private JScrollPane jScrollPane = null;
    private DefaultListModel listModel = null;
	private JPopupMenu jPopupMenu = null;
	private JMenuItem jMenuItem = null;
	private AbstractMalzemeTanimi malzeme = null;
	private IKategorilendirilebilir anaKategori = null;
	
	private JLabel nitelikBirimLabel = null;
	private JComboBox nitelikBirimComboBox = null;
	public MalzemeGirisPanel(String panelName) {
		super();
		this.panelName = panelName;
		initialize();
	}

	public void initialize() {
		listModel = new DefaultListModel();
		this.setLayout(new BorderLayout());
		this.setSize(450,350);		
		this.add(getMalzemePanel(), java.awt.BorderLayout.NORTH);
		this.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		this.add(getNitelikPanel(), java.awt.BorderLayout.CENTER);
	}
	
	private JPanel getMalzemePanel() {
		if (malzemePanel == null) {
			malzemePanel = new JPanel();
			malzemePanel.setLayout(null);
			malzemePanel.setPreferredSize(new java.awt.Dimension(1,150));
			malzemePanel.add(getMalzemeTanimiLabel(), null);
			malzemePanel.add(getMalzemeBirimiLabel(), null);
			malzemePanel.add(getTanimTextField(), null);
			malzemePanel.add(getBirimComboBox(), null);
			malzemePanel.add(getMalzemeKoduLabel(), null);
			malzemePanel.add(getMalzemeKoduTextField(), null);
		}
		return malzemePanel;
	}
	
	private JPanel getNitelikPanel() {
	    if (nitelikPanel == null) {
			nitelikPanel = new JPanel();
			nitelikPanel.setLayout(null);
			nitelikPanel.setPreferredSize(new java.awt.Dimension(1,150));
			nitelikPanel.add(getNitelikComboBox(), null);
			nitelikPanel.add(getNitelikEkleButton(), null);
			nitelikPanel.add(getJScrollPane(), null);
			nitelikPanel.add(getNitelikSecLabel(), null);
			nitelikPanel.add(getNitelikEkleLabel(), null);
			nitelikPanel.add(getNitelikBirimLabel(), null);
			nitelikPanel.add(getNitelikBirimComboBox(), null);
		}
		return nitelikPanel;
	}
	
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(null);
			buttonPanel.setPreferredSize(new java.awt.Dimension(1,50));
			buttonPanel.add(getIptalButton());
			buttonPanel.add(getKaydetButton());
		}
		return buttonPanel;
	}
	
	private JLabel getMalzemeTanimiLabel() {
        if(malzemeTanimiLabel==null){
			malzemeTanimiLabel = new JLabel();
			malzemeTanimiLabel.setText("Tanýmý :");
			malzemeTanimiLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			malzemeTanimiLabel.setBounds(25, 70, 100, 25);
        }
        return malzemeTanimiLabel;
    }

	private JTextField getTanimTextField() {
		if (tanimTextField == null) {
			tanimTextField = new JTextField();
			tanimTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			tanimTextField.setText("");
			tanimTextField.setBounds(125, 70, 200, 25);
		}
		return tanimTextField;
	}
	
	private JLabel getMalzemeKoduLabel() {
        if(malzemeKoduLabel==null){
        	malzemeKoduLabel = new JLabel();
        	malzemeKoduLabel.setText("Kodu :");
        	malzemeKoduLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        	malzemeKoduLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        	malzemeKoduLabel.setBounds(25, 25, 100, 25);
        }
        return malzemeKoduLabel;
    }

	private JTextField getMalzemeKoduTextField() {
        if(malzemeKoduTextField==null){
        	malzemeKoduTextField = new JTextField();
        	malzemeKoduTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        	malzemeKoduTextField.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
        	malzemeKoduTextField.setBounds(125, 25, 200, 25);
        }
        return malzemeKoduTextField;
    }
	private JLabel getMalzemeBirimiLabel() {
        if(malzemeBirimiLabel==null){
            malzemeBirimiLabel = new JLabel();
    		malzemeBirimiLabel.setText("Ölçek :");
    		malzemeBirimiLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
    		malzemeBirimiLabel.setBounds(25, 110, 100, 25);
        }
        return malzemeBirimiLabel;
    }
	

	private JComboBox getBirimComboBox() {
		if (birimComboBox == null) {
			birimComboBox = new JComboBox();
			birimComboBox.setEditable(true);
			birimComboBox.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			birimComboBox.setBounds(125, 110, 200, 25);
		}
		return birimComboBox;
	}
    
	private JLabel getNitelikSecLabel() {
        if(nitelikSecLabel==null){
			nitelikSecLabel = new JLabel();
			nitelikSecLabel.setText("Nitelik Seç:");
			nitelikSecLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			nitelikSecLabel.setBounds(15, 30, 100, 25);
        }
        return nitelikSecLabel;
    }
	
	private JLabel getNitelikEkleLabel() {
        if(nitelikEkleLabel==null){
			nitelikEkleLabel = new JLabel();
			nitelikEkleLabel.setText("Seçilen Niteliði Listeye Ekle");
			nitelikEkleLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			nitelikEkleLabel.setBounds(15, 105, 200, 25);
        }
        return nitelikEkleLabel;
    }  
	private JLabel getNitelikBirimLabel() {
        if(nitelikBirimLabel==null){
        	nitelikBirimLabel = new JLabel();
        	nitelikBirimLabel.setText("Nitelik Birimi:");
        	nitelikBirimLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			nitelikBirimLabel.setBounds(15, 68, 100, 25);
        }
        return nitelikBirimLabel;
    }  
	  
	private JComboBox getNitelikBirimComboBox() {
		if (nitelikBirimComboBox == null) {
			nitelikBirimComboBox = new JComboBox();
			nitelikBirimComboBox.setEditable(true);
			nitelikBirimComboBox.setBounds(115, 68, 150, 25);
		}
		return nitelikBirimComboBox;
	}
	private JButton getNitelikEkleButton() {
		if (nitelikEkleButton == null) {
			nitelikEkleButton = new JButton();
			nitelikEkleButton.setText(">>");
			nitelikEkleButton.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			nitelikEkleButton.setBounds(215, 105, 50, 25);
			nitelikEkleButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
			            String nitelik = getNitelikComboBox().getSelectedItem().toString().toLowerCase();
			            String birim = getNitelikBirimComboBox().getSelectedItem().toString();
			            if(!nitelik.equals("")){
			                nitelik = InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(nitelik);
			                if(birim != "")
			                	nitelik=nitelik+"("+birim+")";
			                NitelikTanimi nitelikTanimi = new NitelikTanimi(nitelik);
			               
			                if (!alreadyInList(nitelik)) {
			                    int index = getNitelikList().getSelectedIndex(); //get selected index
			                
			                    if (index == -1)  //no selection, so insert at beginning
			                        index = 0; //getNitelikList().getModel().getSize();
			                    else            //add after the selected item
			                        index++;
			                
			                    listModel.insertElementAt(nitelikTanimi, index);
			                    getNitelikList().setSelectedIndex(index);
			                    getNitelikList().ensureIndexIsVisible(index);
			                }
		                    getNitelikComboBox().setSelectedItem("");
		                    getNitelikBirimComboBox().setSelectedItem("");
			            }
				}
			});
		}
		return nitelikEkleButton;
	}
  
	private JList getNitelikList() {
		if (nitelikList == null) {
			nitelikList = new JList(listModel);
			nitelikList.setLayoutOrientation(JList.VERTICAL);
			nitelikList.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
			nitelikList.addMouseListener(new java.awt.event.MouseAdapter() { 
				public void mouseReleased(java.awt.event.MouseEvent e) {    
					if (e.isPopupTrigger() && (getNitelikList().getModel().getSize()!=0)){
					    jPopupMenu = getJPopupMenu();
					    jPopupMenu.show(getNitelikList(),e.getX(),e.getY());
					}
					    	
				}
			});
		} 
		return nitelikList;
	}
  
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getNitelikList());
			jScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Nitelik Listesi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
			jScrollPane.setBounds(278, 6, 162, 131);
		}
		return jScrollPane;
	}
  
	private JComboBox getNitelikComboBox() {
		if (nitelikComboBox == null) {
			nitelikComboBox = new JComboBox();
			nitelikComboBox.setEditable(true);
			nitelikComboBox.setBounds(115, 30, 150, 25);
		}
		return nitelikComboBox;
	}
	
	private JPopupMenu getJPopupMenu() {
		if (jPopupMenu == null) {
			jPopupMenu = new JPopupMenu();
			jPopupMenu.setBounds(5, 5, 10, 10);
			jPopupMenu.add(getJMenuItem());
		}
		return jPopupMenu;
	}
	
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Çýkar");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
		            int index = getNitelikList().getSelectedIndex();
		            listModel.remove(index);

		            int size = listModel.getSize();

		            if (size != 0) {
		                if (index == size) //removed item in last position
		                    index--;

		                getNitelikList().setSelectedIndex(index);
		                getNitelikList().ensureIndexIsVisible(index);
		            }
				}
			});
		}
		return jMenuItem;
	}
	
	private JButton getIptalButton() {
		if (iptalButton == null) {
			iptalButton = new JButton();
			iptalButton.setToolTipText("Ýptal");
			iptalButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
				}
			});
			iptalButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/error.png")));
			iptalButton.setBounds(150, 13, 24, 24);
		}
		return iptalButton;
	}

	private JButton getKaydetButton() {
		if (kaydetButton == null) {
			kaydetButton = new JButton();
			kaydetButton.setToolTipText("Kaydet");
			kaydetButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (getTanimTextField().getText().equals("") || getBirimComboBox().getSelectedItem().equals("")
							|| getMalzemeKoduTextField().getText().equals("")){
					    JOptionPane.showMessageDialog(MalzemeGirisPanel.this,"Malzeme kodu,tanýmý ve birimi alanlarý doldurulmalýdýr!","Eksik bilgi",JOptionPane.ERROR_MESSAGE);
					}else{
						malzeme.setKod(getMalzemeKoduTextField().getText());
					    malzeme.setTanim(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(getTanimTextField().getText()));
					    malzeme.setBirim(InputDuzenleyici.kelimelerinBasHarfleriniDuzenle(getBirimComboBox().getSelectedItem().toString()));
					    			    
					    if (malzeme.getId()==null){ //yeni malzeme
					        if(agacaYeniMalzemeGir(malzeme))
					        	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
					    }else{ // malzeme duzelt
					        if(malzemeDuzelt(malzeme))
					        	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
					    }					    
					}
					    
				}
			});
			kaydetButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/check.png")));
			kaydetButton.setBounds(275, 13, 24, 24);
		}
		return kaydetButton;
	}
	
	private void loadBirim2BirimComboBox(){
		//TODO daha sonra birimler.txt dosyasýndan olasi tüm deðerlerle doldurulacak @author Cagdas CIRIT
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
	    comboBoxModel.addElement("");
	    comboBoxModel.addElement(AbstractMalzemeTanimi.ADET);
	    comboBoxModel.addElement(AbstractMalzemeTanimi.KG);
	    comboBoxModel.addElement(AbstractMalzemeTanimi.LT);
	    comboBoxModel.addElement(AbstractMalzemeTanimi.BIDON);
	    comboBoxModel.addElement(AbstractMalzemeTanimi.CIFT);
	    getBirimComboBox().setModel(comboBoxModel);
	}
	
	private void loadNitelik2NitelikComboBox(){
		//TODO daha sonra nitelikTanimlari.txt dosyasýndan olasi tüm deðerlerle doldurulacak @author Cagdas CIRIT
	    Object[] nitelikler = {"","Boyut","Renk","Model","Marka"};
	    DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(nitelikler);
	    getNitelikComboBox().setModel(defaultComboBoxModel);
	}

	private void loadNitelikBirimi2NitelikBirimComboBox(){
		//TODO daha sonra nitelikTanimlari.txt dosyasýndan olasi tüm deðerlerle doldurulacak @author Cagdas CIRIT
	    Object[] nitelikler = {"","GB","MB","Inch.","GHz"};
	    DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(nitelikler);
	    getNitelikBirimComboBox().setModel(defaultComboBoxModel);
	}
	
    protected boolean alreadyInList(String name) {
        for (int i = 0; i < listModel.getSize(); i++) {
            if (((NitelikTanimi)listModel.getElementAt(i)).getNitelikAdi().equals(name))
                return true;
        }
        
        return false;
    }
    
    public boolean agacaYeniMalzemeGir(IMalzemeTanimi malzeme){
    	NitelikTanimi nitelik;
    	
    	for (int i = 0; i < listModel.getSize(); i++) {
    		nitelik = (NitelikTanimi) listModel.elementAt(i);
    		malzeme.addNitelikTanimi(nitelik);
    	}
    	
    	return AnaController.getInstance().getCurrentTanimlarController().treePaneleNodeEkle(anaKategori,malzeme);
    }
    
    public boolean  malzemeDuzelt(IMalzemeTanimi malzeme){
    	NitelikTanimi nitelik;
    	Vector nitelikTanimlari = new Vector(listModel.getSize());
    	
	    for (int i = 0; i < listModel.getSize(); i++) {
            nitelik = (NitelikTanimi) listModel.elementAt(i);
            if (nitelik.getOwningMalzemeTanimi()==null) // sahip malzemesi bos, malzeme nitelik listesinde yok
                malzeme.addNitelikTanimi(nitelik);
            nitelikTanimlari.add(nitelik);
        }

	   
		return ((MalzemeTanimlariController)AnaController.getInstance().getCurrentTanimlarController()).malzemeTanimiDuzelt(malzeme,nitelikTanimlari);
    }
    
	public void loadYourself(IKategorilendirilebilir mal,IKategorilendirilebilir parentNode){
        unLoadYourself(); // ekrani temizle 
	    if (mal == null){ //yeni kategori girisi
	        malzeme = new DemirbasMalzemeTanimi();
	        anaKategori = parentNode;
	    }else{ //malzeme duzeltme iþlemi
	        this.malzeme = (AbstractMalzemeTanimi) mal;
	        loadYourself(malzeme.getKod(),malzeme.getTanim(),malzeme.getBirim(),malzeme.getNitelikTanimlari());
	    }
	}
	
	public void loadYourself(String kod,String tanim,String birim,Set nitelikler){
		getMalzemeKoduTextField().setText(kod);
		getTanimTextField().setText(tanim);
	    getBirimComboBox().setSelectedItem(birim);
	    
	    Iterator iter = nitelikler.iterator();
	    while (iter.hasNext()) {
            NitelikTanimi nitelik = (NitelikTanimi) iter.next();
            listModel.addElement(nitelik);
        }
	}
	
	public void unLoadYourself(){
	    getTanimTextField().setText("");
	    getMalzemeKoduTextField().setText("");
	    listModel.removeAllElements();
	    loadBirim2BirimComboBox();
	    loadNitelik2NitelikComboBox();
	    loadNitelikBirimi2NitelikBirimComboBox();
	}
	
	public String getPanelName() {
		return panelName;
	}
   }
