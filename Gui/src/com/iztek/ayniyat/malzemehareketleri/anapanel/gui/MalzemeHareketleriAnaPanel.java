package com.iztek.ayniyat.malzemehareketleri.anapanel.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.event.TableModelEvent;

import com.iztek.ayniyat.login.gui.LoginGui;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.demirbasNo.gui.DemirbasNoUpdate;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeCikisFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisFisi;
import com.iztek.ayniyat.malzemehareketleri.malzemeGirisi.gui.MalzemeGirisi;
import com.iztek.ayniyat.malzemehareketleri.malzemebozuk.gui.BozukMalzemeAramaSecimi;
import com.iztek.ayniyat.malzemehareketleri.malzemebozuk.gui.MalzemeBozulmasi;
import com.iztek.ayniyat.malzemehareketleri.malzemecikisi.gui.MalzemeCikisi;
import com.iztek.ayniyat.malzemehareketleri.malzemedevri.gui.DemirbasMalzemeDevretme;
import com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.gui.MalzemeTerkini;
import com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.gui.MalzemeZayiati;
import com.iztek.ayniyat.malzemehareketleri.malzemeyonetimi.gui.NitelikDegistirme;
import com.iztek.ayniyat.sorgular.ambaraDevir.gui.AmbaraDevirCikanMalzemeler;
import com.iztek.ayniyat.sorgular.ambaraDevir.gui.AmbaraDevirGelenMalzemeler;
import com.iztek.ayniyat.sorgular.bozukMalzemeSorgulama.gui.BozukMalzemeSorgulama;
import com.iztek.ayniyat.sorgular.malzemeizleme.gui.MalzemeIzleme;
import com.iztek.ayniyat.sorgular.stoksorgulama.gui.StokSorgulama;
import com.iztek.ayniyat.sorgular.terkinMalzemeSorgulama.gui.TerkinMalzemeSorgulama;
import com.iztek.ayniyat.sorgular.yevmiyedefteri.gui.YevmiveDefteri;
import com.iztek.ayniyat.sorgular.zayiMalzemeSorgulama.gui.ZayiMalzemeSorgulama;
import com.iztek.ayniyat.sorgular.zimmetsorgulama.gui.ZimmetSorgulama;
import com.iztek.ayniyat.tanimlar.gui.MalzemeTanimlariAnaPanel;
import com.iztek.ayniyat.tanimlar.gui.YerlesimAnaPanel;


/**
 * @author Cagdas CIRIT
 */
public class MalzemeHareketleriAnaPanel extends JFrame {

	private javax.swing.JPanel jContentPane = null;
	private CardLayout malzemeCardLayout = null;
	private JPanel cardLayoutPanel = null;
	private JMenuBar menuBar = null;
	private JPanel bosPanel = null;
	private JLabel malzemeHareketleriLabel = null;	
	private JMenu malzemeHareketleriMenu  = null;
	private JMenu sorgularMenu = null;
	private JMenu tanimlarMenu = null;
	private JMenu transferDurumSorgulamaMenu = null;
	private JMenu defterlerMenu = null;
	private JMenuItem malzemeZayiMenuItem = null;
	private JMenuItem malzemeDevirMenuItem  = null;
	private JMenu malzemeStokCikisMenu  = null;
	private JMenu malzemeStokGirisMenu  = null;
	private JMenu malzemeDemirbasCikisMenu=null;
	private JMenuItem gelenMalzemelerMenuItem  = null;
	private JMenuItem zimmetSorgulamaMenuItem  = null;
	private JMenuItem cikanMalzemelerMenuItem  = null;
	private JMenuItem yevmiyeDefteriMenuItem  = null;
	private JMenuItem stokSorgulamaMenuItem = null;
	private JMenuItem bozukMalzemeSorgulamaMenuItem = null;
	private JMenuItem zayiMalzemeSorgulamaMenuItem = null;
	private JMenuItem terkinMalzemeSorgulamaMenuItem = null;
	private JMenuItem malzemeIzlemeMenuItem = null;
	private JMenuItem malzemeBozukMenuItem = null;
	private JMenuItem malzemeTanimlariMenuItem = null;
	private JMenuItem yerlesimMenuItem = null;
	private JMenuItem kullaniciMenuItem = null;
	private JMenuItem demirbasNoMenuItem = null;
	private JMenuItem nitelikDegisimiMenuItem=null;
	
	private JMenu demirbasMalzemeStokHareketleriMenu=null;
	private JMenu demirbasMalzemeZimmetHareketleriMenu=null;
	private JMenuItem satinAlmaMenuItem=null;
	private JMenuItem bagisMenuItem=null;
	private JMenuItem vakifMenuItem=null;
	
	private JMenuItem tamirEdilenGirisiMenuItem=null;
	private JMenuItem baskaAmbaraTransferMenuItem=null;
	private JMenuItem baskaAmbardanTransferMenuItem=null;
	private JMenuItem zimmetMenuItem=null;
	private JMenuItem malzemeTerkinMenuItem=null;
	
	private MalzemeGirisi malzemeGirisi = null;
	private MalzemeCikisi malzemeCikisi = null;
	private DemirbasMalzemeDevretme malzemeDevri = null;
	private MalzemeZayiati malzemeZayiati = null;
	private MalzemeTerkini malzemeTerkini=null;
	private MalzemeBozulmasi malzemeBozulmasi = null;
	private ZimmetSorgulama zimmetSorgulama = null;
	private MalzemeIzleme malzemeIzleme = null;
	private StokSorgulama stokSorgulama = null;
	private BozukMalzemeSorgulama bozukMalzemeSorgulama=null;
	private ZayiMalzemeSorgulama zayiMalzemeSorgulama=null;
	private TerkinMalzemeSorgulama terkinMalzemeSorgulama=null;
	private YevmiveDefteri yevmiveDefteri = null;
	private AmbaraDevirCikanMalzemeler ambaraDevirCikanMalzemeler = null;
	private AmbaraDevirGelenMalzemeler ambaraDevirGelenMalzemeler = null;
	
	public MalzemeHareketleriAnaPanel() {
		super();
		initialize();
	}

	private void initialize() {
		this.setResizable(true);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setTitle("Ayniyat");
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		AnaController.getInstance().registerMalzemeHareketleriAnaPanel(MalzemeHareketleriAnaPanel.this);
		ToolTipManager.sharedInstance().setInitialDelay(50);
	}

	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getMainMenuBar(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getCardLayoutPanel(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
   
	public JPanel getCardLayoutPanel() {
		if (cardLayoutPanel == null) {
			malzemeHareketleriLabel = new JLabel();
			cardLayoutPanel = new JPanel();
			cardLayoutPanel.setLayout(getMalzemeCardLayout());
			malzemeHareketleriLabel.setText("Ayniyat Bilgi Sistemi");
			malzemeHareketleriLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			malzemeHareketleriLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			malzemeHareketleriLabel.setFont(new java.awt.Font("Monotype Corsiva", java.awt.Font.BOLD, 50));
			malzemeHareketleriLabel.setForeground(new java.awt.Color(93,7,112));
			malzemeHareketleriLabel.setName("jLabel");
			
			//CardLayout Panels
			cardLayoutPanel.add(getBosPanel(), "bosPanel");
			cardLayoutPanel.add(getMalzemeGirisi().getJContentPane(), malzemeGirisi.getPanelName());
			cardLayoutPanel.add(getMalzemeCikisi().getJContentPane(), malzemeCikisi.getPanelName());
			cardLayoutPanel.add(getMalzemeDevri().getJContentPane(), malzemeDevri.getPanelName());
			cardLayoutPanel.add(getMalzemeZayiati().getJContentPane(), malzemeZayiati.getPanelName());
			cardLayoutPanel.add(getMalzemeTerkini().getJContentPane(),malzemeTerkini.getPanelName());
			cardLayoutPanel.add(getMalzemeBozulmasi().getJContentPane(), malzemeBozulmasi.getPanelName());
			cardLayoutPanel.add(getZimmetSorgulama().getJContentPane(), zimmetSorgulama.getPanelName());
			cardLayoutPanel.add(getMalzemeIzleme().getJContentPane(), malzemeIzleme.getPanelName());
			cardLayoutPanel.add(getStokSorgulama().getJContentPane(), stokSorgulama.getPanelName());
			cardLayoutPanel.add(getBozukMalzemeSorgulama().getJContentPane(),bozukMalzemeSorgulama.getPanelName());
			cardLayoutPanel.add(getZayiMalzemeSorgulama().getJContentPane(),zayiMalzemeSorgulama.getPanelName());
			cardLayoutPanel.add(getTerkinMalzemeSorgulama().getJContentPane(),terkinMalzemeSorgulama.getPanelName());
			cardLayoutPanel.add(getYevmiyeDefteri().getJContentPane(),yevmiveDefteri.getPanelName());
			cardLayoutPanel.add(getAmbaraDevirCikanMalzemeler().getJContentPane(),ambaraDevirCikanMalzemeler.getPanelName());
			cardLayoutPanel.add(getAmbaraDevirGelenMalzemeler().getJContentPane(),ambaraDevirGelenMalzemeler.getPanelName());
		}
		return cardLayoutPanel;
	}
   
    private JMenuBar getMainMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMalzemeHareketleriMenu());
			menuBar.add(getSorgularMenu());
			menuBar.add(getTanimlarMenu());
		}
		return menuBar;
	}
   
	protected JMenu getMalzemeHareketleriMenu() {
		if (malzemeHareketleriMenu == null) {
			malzemeHareketleriMenu = new JMenu();
			malzemeHareketleriMenu.setText("Malzeme Hareketleri");
			malzemeHareketleriMenu.add(getDemirbasMalzemeStokHareketleriMenu());
			malzemeHareketleriMenu.addSeparator();
			malzemeHareketleriMenu.add(getDemirbasMalzemeZimmetHareketleriMenu());
		}
		return malzemeHareketleriMenu;
	}
	
	private JMenu getDemirbasMalzemeStokHareketleriMenu(){
		if(demirbasMalzemeStokHareketleriMenu==null){
			demirbasMalzemeStokHareketleriMenu=new JMenu();
			demirbasMalzemeStokHareketleriMenu.setText("DemirbasMalzemeStokHareketleri");
			demirbasMalzemeStokHareketleriMenu.add(getMalzemeStokGirisMenu());
			demirbasMalzemeStokHareketleriMenu.add(getMalzemeStokCikisMenu());
		}
		return demirbasMalzemeStokHareketleriMenu;
	}
	
	private JMenu getDemirbasMalzemeZimmetHareketleriMenu(){
		if(demirbasMalzemeZimmetHareketleriMenu==null){
			demirbasMalzemeZimmetHareketleriMenu=new JMenu();
			demirbasMalzemeZimmetHareketleriMenu.setText("DemirbasMalzemeZimmetHareketleri");
			demirbasMalzemeZimmetHareketleriMenu.add(getMalzemeDemirbasCikisMenu());
			demirbasMalzemeZimmetHareketleriMenu.add(getMalzemeDevirMenuItem());
		}
		return demirbasMalzemeZimmetHareketleriMenu;
	}
	
	private JMenu getMalzemeDemirbasCikisMenu(){
		if(malzemeDemirbasCikisMenu==null){
			malzemeDemirbasCikisMenu=new JMenu();
			malzemeDemirbasCikisMenu.setText("Malzeme Çýkýþ");
			malzemeDemirbasCikisMenu.add(getZimmetMenuItem());
			malzemeDemirbasCikisMenu.add(getMalzemeBozukMenuItem());
			malzemeDemirbasCikisMenu.add(getMalzemeZayiMenuItem());
			malzemeDemirbasCikisMenu.add(getMalzemeTerkinMenuItem());
			
		}
		return malzemeDemirbasCikisMenu;
	}
    
	private JMenu getSorgularMenu() {
		if (sorgularMenu == null) {
			sorgularMenu = new JMenu();
			sorgularMenu.setText("Sorgular");
			sorgularMenu.add(getTransferDurumSorgulamaMenu());
			sorgularMenu.add(getDefterlerMenu());
			sorgularMenu.add(getZimmetSorgulamaMenuItem());
			sorgularMenu.add(getStokSorgulamaMenuItem());
			sorgularMenu.add(getBozukMalzemeSorgulamaMenuItem());
			sorgularMenu.add(getZayiMalzemeSorgulamaMenuItem());
			sorgularMenu.add(getTerkinMalzemeSorgulamaMenuItem());
			sorgularMenu.add(getMalzemeIzlemeMenuItem());
		}
		return sorgularMenu;
	}
	
	private JMenu getDefterlerMenu() {
		if (defterlerMenu == null) {
			defterlerMenu = new JMenu();
			defterlerMenu.setText("Defterler");
			defterlerMenu.add(getYevmiyeDefteriMenuItem());
		}
		return defterlerMenu;
	}
	
	private JMenu getTransferDurumSorgulamaMenu() {
		if (transferDurumSorgulamaMenu == null) {
			transferDurumSorgulamaMenu = new JMenu();
			transferDurumSorgulamaMenu.setText("Ambarlar Arasý Devir Sorgulama");
			transferDurumSorgulamaMenu.add(getGelenMalzemelerMenuItem());
			transferDurumSorgulamaMenu.add(getCikanMalzemelerMenuItem());
		}
		return transferDurumSorgulamaMenu;
	}
  
	private JMenuItem getMalzemeDevirMenuItem() {
		if (malzemeDevirMenuItem == null) {
			malzemeDevirMenuItem = new JMenuItem();
			malzemeDevirMenuItem.setText("Malzeme Devir");
			malzemeDevirMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    malzemeDevri.unLoadYourself();
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeDevri.getPanelName());				
				}
			});
		}
		return malzemeDevirMenuItem;
	}

	private JMenuItem getYevmiyeDefteriMenuItem() {
		if (yevmiyeDefteriMenuItem == null) {
			yevmiyeDefteriMenuItem = new JMenuItem();
			yevmiyeDefteriMenuItem.setText("Yevmiye Defteri");
			yevmiyeDefteriMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    			
				    yevmiveDefteri.unLoadYourself();
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(yevmiveDefteri.getPanelName());						
				}
			});
		}
		return yevmiyeDefteriMenuItem;
	}
	
	private JMenuItem getMalzemeIzlemeMenuItem() {
		if (malzemeIzlemeMenuItem == null) {
			malzemeIzlemeMenuItem = new JMenuItem();
			malzemeIzlemeMenuItem.setText("Malzeme Ýzleme");
			malzemeIzlemeMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    			
				    malzemeIzleme.unLoadYourself();
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeIzleme.getPanelName());				
				    new BozukMalzemeAramaSecimi(AnaController.getInstance().getAnaPanel()).setVisible(true);
				}
			}); 
		}
		return malzemeIzlemeMenuItem;
	}
	
	private JMenuItem getStokSorgulamaMenuItem() {
		if (stokSorgulamaMenuItem == null) {
			stokSorgulamaMenuItem = new JMenuItem();
			stokSorgulamaMenuItem.setText("Stok Sorgulama");
			stokSorgulamaMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					stokSorgulama.loadYourself(null,null);
					
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(stokSorgulama.getPanelName());	   
				}
			}); 
		}
		return stokSorgulamaMenuItem;
	}
	
	private JMenuItem getBozukMalzemeSorgulamaMenuItem() {
		if (bozukMalzemeSorgulamaMenuItem == null) {
			bozukMalzemeSorgulamaMenuItem = new JMenuItem();
			bozukMalzemeSorgulamaMenuItem.setText("Bozuk Malzeme Sorgulama");
			bozukMalzemeSorgulamaMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					bozukMalzemeSorgulama.loadYourself(null,null);
					
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(bozukMalzemeSorgulama.getPanelName());	   
				}
			}); 
		}
		return bozukMalzemeSorgulamaMenuItem;
	}
	public JMenuItem getZayiMalzemeSorgulamaMenuItem() {
		if (zayiMalzemeSorgulamaMenuItem== null) {
			zayiMalzemeSorgulamaMenuItem = new JMenuItem();
			zayiMalzemeSorgulamaMenuItem.setText("Zayi Sorgulama");
			zayiMalzemeSorgulamaMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					zayiMalzemeSorgulama.loadYourself(null,null);
					
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(zayiMalzemeSorgulama.getPanelName());	   
				}
			}); 
		}
		return zayiMalzemeSorgulamaMenuItem;
	}
	public JMenuItem getTerkinMalzemeSorgulamaMenuItem() {
		if (terkinMalzemeSorgulamaMenuItem== null) {
			terkinMalzemeSorgulamaMenuItem = new JMenuItem();
			terkinMalzemeSorgulamaMenuItem.setText("Terkin Sorgulama");
			terkinMalzemeSorgulamaMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					terkinMalzemeSorgulama.loadYourself(null,null);
					
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(terkinMalzemeSorgulama.getPanelName());	   
				}
			}); 
		}
		return terkinMalzemeSorgulamaMenuItem;
	}
	private JMenuItem getZimmetSorgulamaMenuItem() {
		if (zimmetSorgulamaMenuItem == null) {
			zimmetSorgulamaMenuItem = new JMenuItem();
			zimmetSorgulamaMenuItem.setText("Zimmet Sorgulama");
			zimmetSorgulamaMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {
				    zimmetSorgulama.unLoadYourself();
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(zimmetSorgulama.getPanelName());				
				    new BozukMalzemeAramaSecimi(AnaController.getInstance().getAnaPanel()).setVisible(true);
				}
			});
		}
		return zimmetSorgulamaMenuItem;
	}
	private JMenu getMalzemeStokGirisMenu() {
		if (malzemeStokGirisMenu == null) {
			malzemeStokGirisMenu = new JMenu();
			malzemeStokGirisMenu.setText("Malzeme Giriþi");
			malzemeStokGirisMenu.add(getSatinalmaMenuItem());
			malzemeStokGirisMenu.add(getBagisMenuItem());
			malzemeStokGirisMenu.add(getVakifMenuItem());
			malzemeStokGirisMenu.add(getBaskaAmbardanTransferMenuItem());
			malzemeStokGirisMenu.add(getTamirEdilenGirisi());
		}
			
		return malzemeStokGirisMenu;	
	}
	private JMenuItem getSatinalmaMenuItem() {
		if (satinAlmaMenuItem == null) {
			satinAlmaMenuItem = new JMenuItem();
			satinAlmaMenuItem.setText("Satýn Alma");
			satinAlmaMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					
					malzemeGirisi.unLoadYourself();
					malzemeGirisi.getGirisBilgileriPanel().getHareketSekliTextField().setText(MalzemeGirisFisi.SATINALMA);
					malzemeGirisi.setSatinalmaBilgileriPanelEnabled(true);
					malzemeGirisi.getMalzemelerTable().setModel(malzemeGirisi.getTableForYeniGiris());
					malzemeGirisi.buttonController.tableChanged(new TableModelEvent(malzemeGirisi.getMalzemelerTable().getModel()));
					malzemeGirisi.menuDegisikligiEtkisiniGercekle();
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeGirisi.getPanelName());
				}
			});
		}
		return satinAlmaMenuItem;
	}	
	protected JMenuItem getBagisMenuItem() {
		if (bagisMenuItem == null) {
			bagisMenuItem = new JMenuItem();
			bagisMenuItem.setText("Baðýþ");
			bagisMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					malzemeGirisi.unLoadYourself();
					malzemeGirisi.getGirisBilgileriPanel().getHareketSekliTextField().setText(MalzemeGirisFisi.BAGIS);
					malzemeGirisi.setSatinalmaBilgileriPanelEnabled(false);
					malzemeGirisi.getMalzemelerTable().setModel(malzemeGirisi.getTableForYeniGiris());
					malzemeGirisi.buttonController.tableChanged(new TableModelEvent(malzemeGirisi.getMalzemelerTable().getModel()));
					malzemeGirisi.menuDegisikligiEtkisiniGercekle();
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeGirisi.getPanelName());
				}
			});
		}
		return bagisMenuItem;
	}	
	protected JMenuItem getVakifMenuItem() {
		if (vakifMenuItem == null) {
			vakifMenuItem = new JMenuItem();
			vakifMenuItem.setText("Vakif");
			vakifMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					
					malzemeGirisi.unLoadYourself();
					malzemeGirisi.getGirisBilgileriPanel().getHareketSekliTextField().setText(MalzemeGirisFisi.VAKIF);
					malzemeGirisi.setSatinalmaBilgileriPanelEnabled(false);
					malzemeGirisi.getMalzemelerTable().setModel(malzemeGirisi.getTableForYeniGiris());
					malzemeGirisi.buttonController.tableChanged(new TableModelEvent(malzemeGirisi.getMalzemelerTable().getModel()));
					malzemeGirisi.menuDegisikligiEtkisiniGercekle();
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeGirisi.getPanelName());
				}
			});
		}
		return vakifMenuItem;
	}	
	protected JMenuItem getBaskaAmbardanTransferMenuItem() {
		if (baskaAmbardanTransferMenuItem == null) {
			baskaAmbardanTransferMenuItem = new JMenuItem();
			baskaAmbardanTransferMenuItem.setText("Baþka Ambardan Transfer");
			baskaAmbardanTransferMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
		
					malzemeGirisi.unLoadYourself();
					malzemeGirisi.getGirisBilgileriPanel().getHareketSekliTextField().setText(MalzemeGirisFisi.DEVIR);
					malzemeGirisi.setSatinalmaBilgileriPanelEnabled(false);
					malzemeGirisi.getMalzemelerTable().setModel(malzemeGirisi.getTableForKayitliGiris());
					malzemeGirisi.buttonController.setTableModel(malzemeGirisi.getMalzemelerTable().getModel());
					malzemeGirisi.buttonController.tableChanged(new TableModelEvent(malzemeGirisi.getMalzemelerTable().getModel()));
					malzemeGirisi.menuDegisikligiEtkisiniGercekle();
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeGirisi.getPanelName());
				}
			});
		}
		return baskaAmbardanTransferMenuItem;
	}	
	
	protected JMenuItem getTamirEdilenGirisi() {
		if (tamirEdilenGirisiMenuItem == null) {
			tamirEdilenGirisiMenuItem = new JMenuItem();
			tamirEdilenGirisiMenuItem.setText("Tamir Edilen Malzeme Giriþi");
			tamirEdilenGirisiMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					malzemeGirisi.unLoadYourself();
					malzemeGirisi.getGirisBilgileriPanel().getHareketSekliTextField().setText(MalzemeGirisFisi.TAMIREDILDI);
					malzemeGirisi.setSatinalmaBilgileriPanelEnabled(false);
					malzemeGirisi.getMalzemelerTable().setModel(malzemeGirisi.getTableForKayitliGiris());
					malzemeGirisi.buttonController.setTableModel(malzemeGirisi.getMalzemelerTable().getModel());
					malzemeGirisi.buttonController.tableChanged(new TableModelEvent(malzemeGirisi.getMalzemelerTable().getModel()));
					malzemeGirisi.menuDegisikligiEtkisiniGercekle();
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeGirisi.getPanelName());
				}
			});
		}
		return tamirEdilenGirisiMenuItem;
	}	
	
	private JMenuItem getGelenMalzemelerMenuItem() {
		if (gelenMalzemelerMenuItem == null) {
			gelenMalzemelerMenuItem = new JMenuItem();
			gelenMalzemelerMenuItem.setText("Gelen Malzemeler");
			gelenMalzemelerMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
				    ambaraDevirGelenMalzemeler.loadYourself(null,null);
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(ambaraDevirGelenMalzemeler.getPanelName());
				}
			});
		}
		return gelenMalzemelerMenuItem;
	}
	
	private JMenu getMalzemeStokCikisMenu() {
		if (malzemeStokCikisMenu == null) {
			malzemeStokCikisMenu = new JMenu();
			malzemeStokCikisMenu.setText("Malzeme Çýkýþý");
			
			malzemeStokCikisMenu.add(getBaskaAmbaraTransferMenuItem());
			
		}
		return malzemeStokCikisMenu;
	}
	
	private JMenuItem getBaskaAmbaraTransferMenuItem(){
		
		if (baskaAmbaraTransferMenuItem == null) {
			baskaAmbaraTransferMenuItem = new JMenuItem();
			baskaAmbaraTransferMenuItem.setText("Baska Ambara Transfer");
			baskaAmbaraTransferMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					malzemeCikisi.getHareketBilgileriPanelForCikis().getTeslimAlanButton().setEnabled(true);
					malzemeCikisi.unLoadYourself();
					malzemeCikisi.setCikisSekliTextField("Baþka Ambara Devir");
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeCikisi.getPanelName());
				}
			});
		}
		return baskaAmbaraTransferMenuItem;
	}
	

	private JMenuItem getZimmetMenuItem(){
		
		if (zimmetMenuItem == null) {
			zimmetMenuItem = new JMenuItem();
			zimmetMenuItem.setText("Zimmet");
			zimmetMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					malzemeCikisi.getHareketBilgileriPanelForCikis().getTeslimAlanButton().setEnabled(false);
					malzemeCikisi.unLoadYourself();
					malzemeCikisi.setCikisSekliTextField(MalzemeCikisFisi.ZIMMET);
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeCikisi.getPanelName());
				}
			});
		}
		return zimmetMenuItem;
	}
	
	private JMenuItem getMalzemeBozukMenuItem() {
		if (malzemeBozukMenuItem == null) {
			malzemeBozukMenuItem = new JMenuItem();
			malzemeBozukMenuItem.setText("Malzeme Bozuk");
			malzemeBozukMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    malzemeBozulmasi.unLoadYourself();
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeBozulmasi.getPanelName());
				}
			});
		}
		return malzemeBozukMenuItem;
	}
  
	private JMenuItem getMalzemeZayiMenuItem() {
		if (malzemeZayiMenuItem == null) {
			malzemeZayiMenuItem = new JMenuItem();
			malzemeZayiMenuItem.setText("Malzeme Zayi");
			malzemeZayiMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    malzemeZayiati.unLoadYourself();
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeZayiati.getPanelName());
				}
			});
		}
		return malzemeZayiMenuItem;
	}
	private JMenuItem getMalzemeTerkinMenuItem(){
		if (malzemeTerkinMenuItem == null) {
			malzemeTerkinMenuItem = new JMenuItem();
			malzemeTerkinMenuItem.setText("Malzeme Terkin");
			malzemeTerkinMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    malzemeTerkini.unLoadYourself();
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(malzemeTerkini.getPanelName());
				}
			});
		}
		return malzemeTerkinMenuItem;
	}
	
	private JMenuItem getCikanMalzemelerMenuItem() {
		if (cikanMalzemelerMenuItem == null) {
			cikanMalzemelerMenuItem = new JMenuItem();
			cikanMalzemelerMenuItem.setText("Çýkan Malzemeler");
			cikanMalzemelerMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    ambaraDevirCikanMalzemeler.loadYourself(null,null);
				    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(ambaraDevirCikanMalzemeler.getPanelName());
				}
			});
		}
		return cikanMalzemelerMenuItem;
	}
	  
	public JPanel getBosPanel() {
		if (bosPanel == null) {
			bosPanel = new JPanel();
			bosPanel.setLayout(new BorderLayout());
			bosPanel.setName("jPanel1");
			bosPanel.add(malzemeHareketleriLabel, java.awt.BorderLayout.CENTER);
		}
		return bosPanel;
	}
	
    public CardLayout getMalzemeCardLayout() {
        if (malzemeCardLayout==null){
            malzemeCardLayout = new CardLayout();
        }      
        return malzemeCardLayout;
    }
   
	private JMenu getTanimlarMenu() {
		if (tanimlarMenu == null) {
			tanimlarMenu = new JMenu();
			tanimlarMenu.setText("Tanýmlar");
			tanimlarMenu.add(getMalzemeTanimlariMenuItem());
			tanimlarMenu.add(getYerlesimMenuItem());
			tanimlarMenu.add(getKullaniciMenuItem());
			tanimlarMenu.add(getDemirbasNoMenuItem());
			tanimlarMenu.add(getNitelikDegistirmeMenuItem());
		}
		return tanimlarMenu;
	}
   
	private JMenuItem getMalzemeTanimlariMenuItem() {
		if (malzemeTanimlariMenuItem == null) {
			malzemeTanimlariMenuItem = new JMenuItem();
			malzemeTanimlariMenuItem.setText("Malzeme Tanýmlarý");
			malzemeTanimlariMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {   
					AnaController.getInstance().setCurrentMalzemeTanimlariPanelRole(AnaController.MALZEMETANIMLARI_FOR_TANIM);
					AnaController.getInstance().setCurrentTanimlarController(AnaController.getInstance().getMalzemeTanimlariController());
					new MalzemeTanimlariAnaPanel(AnaController.getInstance().getAnaPanel(),"malzemeTanimiPanel").setVisible(true);
					
				}
			});
		}
		return malzemeTanimlariMenuItem;
	}
 
	private JMenuItem getYerlesimMenuItem() {
		if (yerlesimMenuItem == null) {
			yerlesimMenuItem = new JMenuItem();
			yerlesimMenuItem.setText("Yerleþim ve Organizasyon");
			yerlesimMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					AnaController.getInstance().setCurrentYerlesimPanelRole(AnaController.YERLESIM_FOR_TANIM);
					AnaController.getInstance().setCurrentTanimlarController(AnaController.getInstance().getYerlesimController());
					new YerlesimAnaPanel(AnaController.getInstance().getAnaPanel(),"yerlesimPanel").setVisible(true);
				}
			});
		}
		return yerlesimMenuItem;
	}
	
	 
	private JMenuItem getKullaniciMenuItem() {
		if (kullaniciMenuItem == null) {
		    kullaniciMenuItem = new JMenuItem();
		    kullaniciMenuItem.setText("Kullanici Deðiþtir");
		    kullaniciMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					new LoginGui().setVisible(true);
				}
			});
		}
		return kullaniciMenuItem;
	}
	
	private JMenuItem getDemirbasNoMenuItem() {
		if (demirbasNoMenuItem == null) {
		    demirbasNoMenuItem = new JMenuItem();
		    demirbasNoMenuItem.setText("Demirbaþ Numarasý Deðiþtir");
		    demirbasNoMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					new DemirbasNoUpdate().setVisible(true);
				}
			});
		}
		return demirbasNoMenuItem;
	}
	private JMenuItem getNitelikDegistirmeMenuItem(){
		if(nitelikDegisimiMenuItem==null){
			nitelikDegisimiMenuItem=new JMenuItem();
			nitelikDegisimiMenuItem.setText("Malzeme Nitelik Deðeri Deðiþtir");
			nitelikDegisimiMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e) {  
					new NitelikDegistirme().setVisible(true);
				}
			});
		}
		return nitelikDegisimiMenuItem;
	}
	
	public MalzemeCikisi getMalzemeCikisi() {
		if (malzemeCikisi == null)
			malzemeCikisi = new MalzemeCikisi(AnaController.MALZEME_CIKISI);

			malzemeCikisi.setTitle("malzemeCikisi");
		return malzemeCikisi;
	}
	
	public MalzemeGirisi getMalzemeGirisi() {
		if (malzemeGirisi == null)
			malzemeGirisi = new MalzemeGirisi(AnaController.MALZEME_GIRISI);
			malzemeGirisi.setTitle("malzemeGirisi");
	
		return malzemeGirisi;
	}
	
	public DemirbasMalzemeDevretme getMalzemeDevri() {
		if (malzemeDevri == null)
			malzemeDevri = new DemirbasMalzemeDevretme(AnaController.MALZEME_DEVIR);
			malzemeDevri.setTitle("malzemeDevri");
	
		return malzemeDevri;
	}
    
    public MalzemeZayiati getMalzemeZayiati() {
        if (malzemeZayiati == null)
            malzemeZayiati = new MalzemeZayiati(AnaController.MALZEME_ZAYI);
            malzemeZayiati.setTitle("malzemeZayiati");
        return malzemeZayiati;
    }
    
    public MalzemeTerkini getMalzemeTerkini(){
    	if(malzemeTerkini==null)
    		malzemeTerkini=new MalzemeTerkini(AnaController.MALZEME_TERKIN);
    		malzemeTerkini.setTitle("malzemeTerkini");
    	return malzemeTerkini;
    }

    public MalzemeBozulmasi getMalzemeBozulmasi() {
        if(malzemeBozulmasi == null)
            malzemeBozulmasi = new MalzemeBozulmasi(AnaController.MALZEME_BOZUK);
            malzemeBozulmasi.setTitle("malzemeBozulmasi");
        return malzemeBozulmasi;
    }
    
	public ZimmetSorgulama getZimmetSorgulama() {
		if (zimmetSorgulama == null)
		    zimmetSorgulama = new ZimmetSorgulama(AnaController.ZIMMET_SORGULAMA);	
		    zimmetSorgulama.setTitle("zimmetSorgulama");
		return zimmetSorgulama;
	}
	
	public MalzemeIzleme getMalzemeIzleme() {
		if ( malzemeIzleme == null)
		    malzemeIzleme = new MalzemeIzleme(AnaController.MALZEME_IZLEME);	
		    malzemeIzleme.setTitle("malzemeIzleme");
		return malzemeIzleme;
	}
	
	private YevmiveDefteri getYevmiyeDefteri() {
	    if(yevmiveDefteri == null)
	        yevmiveDefteri = new YevmiveDefteri(AnaController.YEVMIYE_DEFTERI);
	        yevmiveDefteri.setTitle("yevmiveDefteri");
	    return yevmiveDefteri; 
    }
    
    public StokSorgulama getStokSorgulama() {
        if(stokSorgulama == null)
            stokSorgulama = new StokSorgulama(AnaController.STOK_SORGULAMA, "Stoktaki Malzemeler");
            stokSorgulama.setTitle("stokSorgulama");
        return stokSorgulama;
    }
    
    public BozukMalzemeSorgulama getBozukMalzemeSorgulama() {
		if (bozukMalzemeSorgulama == null)
			bozukMalzemeSorgulama = new BozukMalzemeSorgulama(AnaController.BOZUKMALZEME_SORGULAMA,"Bozuk Malzeme Tanýmlarý");
			bozukMalzemeSorgulama.setTitle("bozukMalzemeSorgulama");
		return bozukMalzemeSorgulama;
	}
    public ZayiMalzemeSorgulama getZayiMalzemeSorgulama() {
    	if (zayiMalzemeSorgulama == null)
			zayiMalzemeSorgulama = new ZayiMalzemeSorgulama(
					AnaController.ZAYIMALZEME_SORGULAMA, "Zayi Olan Malzemeler");
		zayiMalzemeSorgulama.setTitle("zayiMalzemeSorgulama");
    	return zayiMalzemeSorgulama;
	}
    public TerkinMalzemeSorgulama getTerkinMalzemeSorgulama() {
    	if (terkinMalzemeSorgulama == null)
			terkinMalzemeSorgulama = new TerkinMalzemeSorgulama(
					AnaController.TERKINMALZEME_SORGULAMA, "Terkin Edilen Malzemeler");
		terkinMalzemeSorgulama.setTitle("terkinMalzemeSorgulama");
    	return terkinMalzemeSorgulama;
	}
    
    public AmbaraDevirCikanMalzemeler getAmbaraDevirCikanMalzemeler() {
        if(ambaraDevirCikanMalzemeler == null)
            ambaraDevirCikanMalzemeler = new AmbaraDevirCikanMalzemeler(AnaController.AMBARA_DEVIR_CIKANLAR);
            ambaraDevirCikanMalzemeler.setTitle("ambaraDevirCikanMalzemeler");
        return ambaraDevirCikanMalzemeler;
    }
    
    public AmbaraDevirGelenMalzemeler getAmbaraDevirGelenMalzemeler() {
        if (ambaraDevirGelenMalzemeler == null)
            ambaraDevirGelenMalzemeler = new AmbaraDevirGelenMalzemeler(AnaController.AMBARA_DEVIR_GELENLER);
            ambaraDevirGelenMalzemeler.setTitle("ambaraDevirGelenMalzemeler");
        return ambaraDevirGelenMalzemeler;
    }
 	public void refreshMalzemeHareketleriAnaPanel(String title){
        this.setTitle(title);
        AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir("bosPanel");
    }

}
