package com.iztek.ayniyat.malzemehareketleri.malzemecikisi.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.jdesktop.jdnc.JNTable;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.controller.ButtonController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.panel.IMalzemeHareketleriPanel;
import com.iztek.ayniyat.tanimlar.gui.MalzemeTanimlariAnaPanel;
import com.iztek.ayniyat.util.uicomponents.ButtonPanel1;
import com.iztek.ayniyat.util.uicomponents.HareketBilgileriPanelForCikis;
import com.iztek.ayniyat.util.uicomponents.MalzemeTipiSecimiPanel;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
/**
 * @author Cagdas CIRIT + Umit Akyol
 */
public class MalzemeCikisi extends JFrame implements IAyniyatPanel,IMalzemeHareketleriPanel{

	private javax.swing.JPanel jContentPane = null;
	private javax.swing.JPanel anaPanel = null;
	private MalzemeTipiSecimiPanel malzemeTipiSecimiPanel=null;
	private ButtonPanel1 buttonPanel=null;
	private JNTable malzemelerTable = null;
	private JPanel tablePanel = null;
	private ButtonController buttonController = null;
	private String panelName = null;
	private Vector ambarSorumlulari = null;
	private String belgeNo="";
	private HareketBilgileriPanelForCikis hareketBilgileriPanelForCikis=null;	
    
	public MalzemeCikisi(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}

	public void initialize() {
		buttonController = new ButtonController(getMalzemelerTable().getModel(),new JButton[] {
			getButtonPanel().getCikarButton(),getButtonPanel().getKaydetButton(),getButtonPanel().getTemizleButton()},
			new JMenuItem[] {getButtonPanel().getCikarMenuItem(),getButtonPanel().getKaydetMenuItem(),
			getButtonPanel().getTemizleMenuItem()});
		AnaController.getInstance().getMalzemeCikisiController().registerMalzemeCikisi(MalzemeCikisi.this);
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Malzeme Çýkýþ Hareketi");
		this.setResizable(true);	
	}

	public javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new javax.swing.BoxLayout(jContentPane, javax.swing.BoxLayout.Y_AXIS));
			jContentPane.add(getAnaPanel(), null);
		}
		return jContentPane;
	}

	private javax.swing.JPanel getAnaPanel() {
		if(anaPanel == null) {
			anaPanel = new javax.swing.JPanel();
			anaPanel.setLayout(new BorderLayout());
			anaPanel.add(getHareketBilgileriPanelForCikis(), BorderLayout.NORTH);
			anaPanel.add(getTablePanel(), BorderLayout.CENTER);
			anaPanel.add(getButtonPanel(), BorderLayout.SOUTH);
		}
		return anaPanel;
	}
	public HareketBilgileriPanelForCikis getHareketBilgileriPanelForCikis(){
		if(hareketBilgileriPanelForCikis==null){
			hareketBilgileriPanelForCikis=new HareketBilgileriPanelForCikis();
			hareketBilgileriPanelForCikis.setPreferredSize(new Dimension(795, 171));
		}
		return hareketBilgileriPanelForCikis;
	}
	private ButtonPanel1 getButtonPanel(){
		if(buttonPanel==null){
			buttonPanel=new ButtonPanel1();
			buttonPanel.registerPanel(this);
			buttonPanel.getKaydetMenuItem().setVisible(true);
			buttonPanel.getDevretMenuItem().setVisible(false);
		}
		return buttonPanel;
	}

	public void setCikisSekliTextField(String cikisSekli){
		getHareketBilgileriPanelForCikis().getBilgiPanel().getHareketSekliTextField().setText(cikisSekli);
	}

	public JNTable getMalzemelerTable() {
		if(malzemelerTable == null) {
			AyniyatTableModel ayniyatTableModel = new AyniyatTableModel(new String[]{"Demirbaþ No","Cinsi","Ölçek","Fiyat(KDVsiz)","KDV (%)","OTV (%)"});
			malzemelerTable = new JNTable(ayniyatTableModel);
			malzemelerTable.setHasColumnControl(true);
			malzemelerTable.setHeaderBackground(new java.awt.Color(204, 255, 255));
			malzemelerTable.setBounds(new Rectangle(6, 54, 788, 441));
			malzemelerTable.setHeaderFont(new java.awt.Font("Dialog",
			 java.awt.Font.BOLD, 12));
			malzemelerTable.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Çýkýþý Yapýlacak Malzemeler", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
			malzemelerTable.setPopupMenu(getButtonPanel().getJPopupMenu());
		}
		return malzemelerTable;
	}

	private boolean isTableEmpty(){
		if(malzemelerTable.getTable().getRowCount()>0){
			return false;
		}
		else{
			return true;
		}
	}
	
	private boolean isRowSelected(){
		if(malzemelerTable.getTable().getSelectedRow()==-1){
			return false;
		}
		else{
			return true;
		}
	}
 
	private JPanel getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JPanel();
			tablePanel.setLayout(new BorderLayout());
			tablePanel.add(getMalzemeTipiSecimiPanel(), java.awt.BorderLayout.NORTH);
			tablePanel.add(getMalzemelerTable(), java.awt.BorderLayout.CENTER);
		}
		return tablePanel;
	}
	private MalzemeTipiSecimiPanel getMalzemeTipiSecimiPanel(){
		if(malzemeTipiSecimiPanel==null){
			malzemeTipiSecimiPanel=new MalzemeTipiSecimiPanel();
		}
		return malzemeTipiSecimiPanel;
	}
 
	public Vector getAmbarSorumlulari() {
		if (ambarSorumlulari == null)
			AnaController.getInstance().getMalzemeCikisiController().findAmbarSorumlulari();
		return ambarSorumlulari;
	}
	
	public void setAmbarSorumlulari(Vector ambarSorumlulari) {
		this.ambarSorumlulari = ambarSorumlulari;
	}
		
	public String getPanelName() {
		return panelName;
	}

	public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
	}

	public void unLoadYourself() {
 	    getHareketBilgileriPanelForCikis().unLoadYourself();
		((AyniyatTableModel)malzemelerTable.getModel()).removeAllRows();
	}
	
	public void secilenMalzemeleriTabloyaKoy(Vector malzemeler){
	    Iterator iterator = malzemeler.iterator();
	    while(iterator.hasNext()){
	        IDemirbasMalzeme malzeme = (IDemirbasMalzeme) iterator.next();
	        if(isTablodaMevcut(malzeme))
	            continue;
	        Vector row = new Vector(5);
	        //row yapýsýný olustur 
	        row.add(malzeme);
		    row.add(malzeme.getMalzemeTanimi().getNodeValue());
		    row.add(malzeme.getMalzemeTanimi().getBirim());
		    row.add(malzeme.getPaha().getVergisizBirimFiyat());
		    row.add(malzeme.getPaha().getKdvOrani());
		    row.add(malzeme.getPaha().getOtvOrani());
		    
		    //malzeme kaydýný tabloya ekle
		    ((AyniyatTableModel)getMalzemelerTable().getModel()).addRow2LastRow(row);
	    }
	}
	
	public Vector getCikisiYapilacakMalzemeler(){
	    Vector malzemeler = new Vector();
	    HashMap malzemeTanimlari = new HashMap();
	    for(int i=0;i<((AyniyatTableModel)getMalzemelerTable().getModel()).getRowCount();i++){
	        AbstractMalzeme malzeme = (AbstractMalzeme) ((AyniyatTableModel)getMalzemelerTable().getModel()).getValueAt(i,0);
	        IMalzemeTanimi tanim = (IMalzemeTanimi) malzemeTanimlari.get(malzeme.getMalzemeTanimi().getId());
            if(tanim == null){
                tanim = malzeme.getMalzemeTanimi();
                malzemeTanimlari.put(tanim.getId(),tanim);
    	        
            }else
                malzeme.setMalzemeTanimi(tanim);
            
	        malzemeler.add(malzeme);
	    }
	    return malzemeler;
	}
	
	private boolean cikisIslemiOnayiAl(){
	    return AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Malzemelerin çýkýþýný onaylýyor musunuz?");
	    
	}
	
	private boolean gererkliBilgilerMevcutOnayiAl(){
	    if(hareketBilgileriPanelForCikis.getBilgiPanel().getHareketTarihi().getDate().equals("")||
	            hareketBilgileriPanelForCikis.getVerildigiYer()==null){
	        return false;
	    }
	    if(hareketBilgileriPanelForCikis.getBilgiPanel().getHareketSekliTextField().getText().equals("Baþka Ambara Devir")){
	        if(hareketBilgileriPanelForCikis.getTeslimAlan()== null){
	            return false;
	        }
	    }
	    return true;
	}
	
	private boolean hareketHedefiUygunOnayiAl(){
	    String cikisSekli = (String) hareketBilgileriPanelForCikis.getBilgiPanel().getHareketSekliTextField().getText();
	    if(cikisSekli.equals("Baþka Ambara Devir")){
	        if(!(hareketBilgileriPanelForCikis.getVerildigiYer() instanceof Ambar)){
	            return false;
	        }
	    }
	    return true;
	}
	
	public String getBelgeNo() {
        return belgeNo;
    }
    
    public void setBelgeNo(String belgeNo) {
        this.belgeNo = belgeNo;
    }
    
    public void tabloyaEkleAction(){
	    AnaController.getInstance().setCurrentMalzemeTanimlariPanelRole(AnaController.MALZEMETANIMLARI_FOR_DIALOG);
		AnaController.getInstance().setCurrentTanimlarController(AnaController.getInstance().getMalzemeTanimlariController());
	    new MalzemeTanimlariAnaPanel(AnaController.getInstance().getAnaPanel(),"malzemePanel").setVisible(true);
    }
    
     public void kaydetAction(){
	    //Gerekli bilgiler mevcut mu
	    if(!gererkliBilgilerMevcutOnayiAl()){
	        AnaController.getInstance().showWarningDialogBox(AnaController.getInstance().getAnaPanel(),"Lütfen gerekli bilgileri giriniz!");
	        return;
	    }
	    // verilen yer uygun secilmis mi
	    if(!hareketHedefiUygunOnayiAl()){
	        AnaController.getInstance().showWarningDialogBox(AnaController.getInstance().getAnaPanel(),"Lütfen Verildigi Yer/Kiþi yi \n" +
	        		"çýkýþ þekline uygun seçiniz!");
	        return;
	    }
	    //kayýt yapmak icin onay al
	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Malzemelerin çýkýþýný onaylýyor musunuz?");
	    if(!onay){
	        return;
	    }
	    //islemi gerceklestir
	    boolean success = false;
	    success=AnaController.getInstance().getMalzemeCikisiController().malzemelerinCikisiniGerceklestir();    
	    
	    //islem basarýyla gerceklestirilmis mi kontrol et ve tum ekraný temizle
	   if(success)
	      unLoadYourself(); 
    }
    
    private boolean isTablodaMevcut(IDemirbasMalzeme malzeme){
        for (int i=0;i<getMalzemelerTable().getTable().getRowCount();i++){
            if(getMalzemelerTable().getTable().getValueAt(i,0).equals(malzeme))
                return true;
        }
        return false; 
    }

 }
