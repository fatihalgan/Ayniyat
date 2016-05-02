package com.iztek.ayniyat.malzemehareketleri.malzemedevri.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Date;
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
import com.iztek.ayniyat.util.uicomponents.ButtonPanel1;
import com.iztek.ayniyat.util.uicomponents.HareketBilgileriPanelForCikis;
import com.iztek.ayniyat.util.uicomponents.MalzemeTipiSecimiPanel;
/**
 * @author Umit Akyol
 */
public class DemirbasMalzemeDevretme extends JFrame implements IAyniyatPanel,IMalzemeHareketleriPanel{

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
	public HareketBilgileriPanelForCikis devirbilgileriPanel=null;
		
    
	public DemirbasMalzemeDevretme(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}

	public void initialize() {
		buttonController = new ButtonController(getMalzemelerTable().getModel(),
				new JButton[] {getButtonPanel().getCikarButton(),getButtonPanel().getDevretButton(),
			getButtonPanel().getTemizleButton()},new JMenuItem[] {
			getButtonPanel().getCikarMenuItem(),getButtonPanel().getDevretMenuItem(),
			getButtonPanel().getTemizleMenuItem()});
		getMalzemelerTable().getModel().addTableModelListener(buttonController);
		AnaController.getInstance().getMalzemeDevriController().registerDemirbasMalzemeDevri(DemirbasMalzemeDevretme.this);
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Malzeme Devir Hareketi");
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
			anaPanel.setLayout(new java.awt.BorderLayout());
			anaPanel.add(getDevirBilgileriPanel(),BorderLayout.NORTH);
			anaPanel.add(getTablePanel(), java.awt.BorderLayout.CENTER);
			anaPanel.add(getButtonPanel(),BorderLayout.SOUTH);
		}
		return anaPanel;
	}
	public HareketBilgileriPanelForCikis getDevirBilgileriPanel(){
		if(devirbilgileriPanel==null){
			devirbilgileriPanel=new HareketBilgileriPanelForCikis();
			devirbilgileriPanel.setPreferredSize(new Dimension(795, 171));
			devirbilgileriPanel.getBilgiPanel().hareketiOnaylayanLabel.setText("Devri Onaylayan :");
			devirbilgileriPanel.getBilgiPanel().hareketTarihiLabel.setText("Devir Tarihi :");
			devirbilgileriPanel.getBilgiPanel().hareketSekliLabel.setVisible(false);
			devirbilgileriPanel.getBilgiPanel().getHareketSekliTextField().setVisible(false);
			devirbilgileriPanel.getTeslimAlanButton().setVisible(false);
			devirbilgileriPanel.getTeslimAlanTextField().setVisible(false);
		}
		return devirbilgileriPanel;
	}
	private ButtonPanel1 getButtonPanel(){
		if(buttonPanel==null){
			buttonPanel=new ButtonPanel1();
			buttonPanel.registerPanel(this);
			buttonPanel.KaydetButton.setVisible(false);
			buttonPanel.DevretButton.setVisible(true);
			buttonPanel.getKaydetMenuItem().setVisible(false);
		}
		return buttonPanel;
	}

	public JNTable getMalzemelerTable() {
		if(malzemelerTable == null) {
			AyniyatTableModel model = new AyniyatTableModel(new String[]{"Demirbaþ No","Cinsi","Ölçek","Fiyat(KDVsiz)","KDV (%)","OTV (%)"});
			malzemelerTable = new JNTable(model);
			malzemelerTable.setHasColumnControl(true);
			malzemelerTable.setHeaderBackground(new java.awt.Color(204, 255, 255));
			malzemelerTable.setBounds(new Rectangle(6, 54, 788, 441));
			malzemelerTable.setHeaderFont(new java.awt.Font("Dialog",
			 java.awt.Font.BOLD, 12));
			malzemelerTable.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Devri Yapýlacak Malzemeler", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
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
			AnaController.getInstance().getMalzemeDevriController().findAmbarSorumlulari();
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
	    getDevirBilgileriPanel().unLoadYourself();
		getDevirBilgileriPanel().setVerildigiYer(null);
	    getDevirBilgileriPanel().setHareketKaynagi(null);
	    ((AyniyatTableModel)malzemelerTable.getModel()).removeAllRows();
	}
	
	public Date getIslemTarihi(){
	   
        return getDevirBilgileriPanel().getBilgiPanel().getHareketTarihi().getDate();
	}
	
	public Vector getDevriYapilacakMalzemeler(){
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
	    if(!getDevirBilgileriPanel().getBilgiPanel().getHareketTarihi().getDate().equals("")&& getDevirBilgileriPanel().getVerildigiYer()!=null)
	        return true;
	    return false;
	}
		
	public String getBelgeNo() {
        return belgeNo;
    }
    
    public void setBelgeNo(String belgeNo) {
        this.belgeNo = belgeNo;
    }
    
    public void tabloyuDoldur(Vector demirbaslar){
        Iterator iterator = demirbaslar.iterator();
        while(iterator.hasNext()){
            IDemirbasMalzeme malzeme = (IDemirbasMalzeme) iterator.next();
            if(isTablodaMevcut(malzeme))
                continue;
            if(!isMalzemeEklenebilir(malzeme)){
                return;
            }
            Vector row = new Vector(6);
            row.add(malzeme);
            row.add(malzeme.getMalzemeTanimi());
            row.add(malzeme.getMalzemeTanimi().getBirim());
            row.add(malzeme.getPaha().getVergisizBirimFiyat());
            row.add(malzeme.getPaha().getKdvOrani());
            row.add(malzeme.getPaha().getOtvOrani());
            ((AyniyatTableModel)getMalzemelerTable().getModel()).addRow2LastRow(row);
        }
    }
    
	/*public static void main(String[] args) {
		MainPersistedTestCase mptc = new MainPersistedTestCase();
		try {
			mptc.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DemirbasMalzemeDevretme malzemeDevri = new DemirbasMalzemeDevretme("Malzeme Devretme");
		malzemeDevri.setVisible(true);
	}
*/    
    private boolean isMalzemeEklenebilir(IDemirbasMalzeme malzeme){
        if(isTableEmpty()){
           getDevirBilgileriPanel().setHareketKaynagi(malzeme.getZimmetSahibi());
            return true;
        }
        else{
            if(!getDevirBilgileriPanel().getHareketKaynagi().equals(malzeme.getZimmetSahibi())){
                AnaController.getInstance().showWarningDialogBox(AnaController.getInstance().getAnaPanel(),"Tabloya eklenen tüm malzemeler ayný kiþi üzerine zimmetli olmak zorunda!");
                return false;
            }
            else
                return true;
        }
    }
    
    private boolean isTablodaMevcut(IDemirbasMalzeme malzeme){
        for (int i=0;i<getMalzemelerTable().getTable().getRowCount();i++){
            if(getMalzemelerTable().getTable().getValueAt(i,0).equals(malzeme))
                return true;
        }
        return false; 
    }
    
    public void tabloyaEkleAction(){
        DevirMalzemeleriSecimi malzemeSecimi = new DevirMalzemeleriSecimi(DemirbasMalzemeDevretme.this);
	    malzemeSecimi.setVisible(true);
    }
    
    public void kaydetAction(){
        //Gerekli bilgiler mevcut mu
	    if(!gererkliBilgilerMevcutOnayiAl()){
	        AnaController.getInstance().showWarningDialogBox(AnaController.getInstance().getAnaPanel(),"Lütfen gerekli bilgileri giriniz!");
	        return;
	    }
	    if(getDevirBilgileriPanel().getVerildigiYer().equals(getDevirBilgileriPanel().getHareketKaynagi())){
	        AnaController.getInstance().showWarningDialogBox(AnaController.getInstance().getAnaPanel(),"Devreden ile devredilen kiþi ayný olamaz!");
	        return;
	    }
	    //kayýt yapmak icin onay al
	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Malzemelerin devrini onaylýyor musunuz?");
	    if(!onay){
	        return;
	    }
	    //islemi gerceklestir
	    boolean success = false;
	    success=AnaController.getInstance().getMalzemeDevriController().malzemelerinDevriniGerceklestir();    
	    
	    //islem basarýyla gerceklestirilmis mi kontrol et ve tum ekraný temizle
	   if(success)
	      unLoadYourself();
    }
  
 }
