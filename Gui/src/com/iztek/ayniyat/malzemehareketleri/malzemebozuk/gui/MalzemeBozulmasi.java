package com.iztek.ayniyat.malzemehareketleri.malzemebozuk.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdesktop.jdnc.JNTable;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.controller.ButtonController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.panel.IMalzemeHareketleriPanel;
import com.iztek.ayniyat.util.uicomponents.ButtonPanel1;
import com.iztek.ayniyat.util.uicomponents.HareketBilgileriPanel;
import com.iztek.ayniyat.util.uicomponents.MalzemeTipiSecimiPanel;
import com.iztek.ayniyat.util.uicomponents.SatinAlmaBilgileriPanel;
/**
 * @author Cagdas CIRIT
 */
public class MalzemeBozulmasi extends JFrame implements IAyniyatPanel,IMalzemeHareketleriPanel{

	private JPanel jContentPane = null;
	private JPanel anaPanel = null;
	private JPanel bozulusBilgileriPanel = null;
	private MalzemeTipiSecimiPanel malzemeTipiSecimiPanel=null;
	private ButtonPanel1 buttonPanel=null;
	private JNTable malzemelerTable = null;
	private JPanel tablePanel = null;
	private ButtonController buttonController = null;
	private String panelName = null;
	private Vector ambarSorumlulari = null;
	private String belgeNo="";
	private HareketBilgileriPanel bozulusBilgileriLeftPanel=null;
	private SatinAlmaBilgileriPanel bozulusBilgileriRightPanel=null;
	
	public MalzemeBozulmasi(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}

	public void initialize() {
		buttonController = new ButtonController(getMalzemelerTable().getModel(),
				new JButton[] {getButtonPanel().getCikarButton(),getButtonPanel().getKaydetButton(),
			getButtonPanel().getTemizleButton()},
			new JMenuItem[] {getButtonPanel().getCikarMenuItem(),getButtonPanel().getKaydetMenuItem(),getButtonPanel().getTemizleMenuItem()});
		AnaController.getInstance().getMalzemeBozulmasiController().registerMalzemeBozulmasi(MalzemeBozulmasi.this);
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Malzeme Bozuk Hareketi");
		this.setResizable(true);	
	}

	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(jContentPane, BoxLayout.Y_AXIS));
			jContentPane.add(getAnaPanel(), null);
		}
		return jContentPane;
	}

	private JPanel getAnaPanel() {
		if(anaPanel == null) {
			anaPanel = new JPanel();
			anaPanel.setLayout(new BorderLayout());
			anaPanel.add(getBozulusBilgileriPanel(), BorderLayout.NORTH);
			anaPanel.add(getTablePanel(), null);
			anaPanel.add(getButtonPanel(), BorderLayout.SOUTH);
		}
		return anaPanel;
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
	private JPanel getBozulusBilgileriPanel() {
		if(bozulusBilgileriPanel == null) {
			bozulusBilgileriPanel = new JPanel();
			bozulusBilgileriPanel.setLayout(new BorderLayout());
			bozulusBilgileriPanel.add(getBozulusBilgileriLeftPanel(), BorderLayout.WEST);
			bozulusBilgileriPanel.add(getBozulusBilgileriRightPanel(), BorderLayout.EAST);
			bozulusBilgileriPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Bozulma Bilgileri", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
		}
		return bozulusBilgileriPanel;
	}
	public HareketBilgileriPanel getBozulusBilgileriLeftPanel(){
		if(bozulusBilgileriLeftPanel==null){
			bozulusBilgileriLeftPanel=new HareketBilgileriPanel();
			bozulusBilgileriLeftPanel.setPreferredSize(new Dimension(400, 160));
			bozulusBilgileriLeftPanel.hareketSekliLabel.setVisible(false);
			bozulusBilgileriLeftPanel.getHareketSekliTextField().setVisible(false);
			bozulusBilgileriLeftPanel.hareketTarihiLabel.setText("Düzenlenme Tarihi :");
			bozulusBilgileriLeftPanel.hareketiOnaylayanLabel.setText("Çýkýþý Onaylayan :");
		}
		return bozulusBilgileriLeftPanel;
	}
	public SatinAlmaBilgileriPanel getBozulusBilgileriRightPanel(){
		if (bozulusBilgileriRightPanel==null){
			bozulusBilgileriRightPanel=new SatinAlmaBilgileriPanel();
			bozulusBilgileriRightPanel.setPreferredSize(new Dimension(380, 160));
			bozulusBilgileriRightPanel.faturaNoLabel.setText("Bozulma Nedeni :");
			bozulusBilgileriRightPanel.faturaTarihiLabel.setText("Bozuluþ Tarihi :");
		}
		return bozulusBilgileriRightPanel;
	}

	public JNTable getMalzemelerTable() {
		if(malzemelerTable == null) {
			AyniyatTableModel ayniyatTableModel = new AyniyatTableModel(new String[]{"Demirbaþ No","Cinsi","Ölçek","Fiyat(Vergisiz)","Vergi (%)"});
			malzemelerTable = new JNTable(ayniyatTableModel);
			malzemelerTable.setHasColumnControl(true);
			malzemelerTable.setHeaderBackground(new java.awt.Color(204, 255, 255));
			malzemelerTable.setHeaderFont(new java.awt.Font("Dialog",
			 java.awt.Font.BOLD, 12));
			malzemelerTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Bozuk Malzemeler", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
			malzemelerTable.setPopupMenu(getButtonPanel().getJPopupMenu());
		}
		return malzemelerTable;
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
			AnaController.getInstance().getMalzemeBozulmasiController().findAmbarSorumlulari();
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
	    getBozulusBilgileriLeftPanel().unLoadYourself();
	    getBozulusBilgileriRightPanel().unLoadYourself();
	   ((AyniyatTableModel)malzemelerTable.getModel()).removeAllRows();
	}
	
	public Date getDuzenlemeTarihi(){
	   return getBozulusBilgileriLeftPanel().getHareketTarihi().getDate();
	}
	public Date getBozulusTarihi(){
		return getBozulusBilgileriRightPanel().getFaturaTarihiTextField().getDate();
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
	
	public void tablonuDoldur(Vector malzemeler){
	    Iterator iterator = malzemeler.iterator();
	    while(iterator.hasNext()){
	        IDemirbasMalzeme malzeme = (IDemirbasMalzeme) iterator.next();
	        
	        if(!isMalzemeZayi(malzeme) && !isMalzemeBozuk(malzeme)){
		        if(!isTablodaMevcut(malzeme)){
			        Vector row = new Vector(5);
			        //row yapýsýný olustur 
			        row.add(malzeme);
				    row.add(malzeme.getMalzemeTanimi().getNodeValue());
				    row.add(malzeme.getMalzemeTanimi().getBirim());
				    row.add(malzeme.getPaha().getVergisizBirimFiyat());
				    row.add(new Float(malzeme.getPaha().getAsilOran()*100));
				    
				    //malzeme kaydýný tabloya ekle
				    ((AyniyatTableModel)getMalzemelerTable().getModel()).addRow2LastRow(row);
		        }
	        }      
	    }
	}
	
	private boolean isMalzemeZayi(IDemirbasMalzeme demirbas){
	    if(demirbas.getState().getType().equals(IMalzemeState.ZAYI)){
	        JOptionPane.showMessageDialog(AnaController.getInstance().getAnaPanel(),demirbas+" Nolu demirbaþ Zayidir!","Zayi Malzeme Uyarýsý",JOptionPane.WARNING_MESSAGE);
	        return true;
	    }
	    return false;
	}
	
	private boolean isMalzemeBozuk(IDemirbasMalzeme demirbas){
	    if(demirbas.getState().getType().equals(IMalzemeState.BOZUK)){
	        JOptionPane.showMessageDialog(AnaController.getInstance().getAnaPanel(),demirbas+" Nolu demirbaþ halihazýrda Bozuktur!","Bozuk Malzeme Uyarýsý",JOptionPane.WARNING_MESSAGE);
	        return true;
	    }
	    return false;
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
	    return AnaController.getInstance().showConfirmationDialogBox(this,"Bozuk iþlemini onaylýyor musunuz?");   
	}
	
	public String getBelgeNo() {
        return belgeNo;
    }
    
    public void setBelgeNo(String belgeNo) {
        this.belgeNo = belgeNo;
    }
  
	public String getBozulmaNedeni(){
	    return getBozulusBilgileriRightPanel().getFaturaNoTextField().getText();
	}
	
    private boolean gererkliBilgilerMevcutOnayiAl(){
        if (getBozulusBilgileriLeftPanel().getHareketTarihi().getDate().equals("") || getBozulusBilgileriRightPanel().getFaturaTarihiTextField().getDate().equals("") || getBozulusBilgileriRightPanel().getFaturaNoTextField().getText().equals(""))
            return false;
        return true;
    }
    
    public void tabloyaEkleAction(){
        new BozukMalzemeAramaSecimi(AnaController.getInstance().getAnaPanel()).setVisible(true);
    }
  public void kaydetAction(){
	    //Gerekli bilgiler mevcut mu
	    if(!gererkliBilgilerMevcutOnayiAl()){
	        AnaController.getInstance().showWarningDialogBox(AnaController.getInstance().getAnaPanel(),"Lütfen gerekli bilgileri giriniz!");
	        return;
	    }
	    //kayýt yapmak icin onay al
	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Bozulma iþlemini onaylýyor musunuz?");
	    if(!onay){
	        return;
	    }
	    
	    AnaController.getInstance().getMalzemeBozulmasiController().malzemelerinBozukHareketiniGerceklestir();
		unLoadYourself();
    }
    
    private boolean isTablodaMevcut(IDemirbasMalzeme malzeme){
        for (int i=0;i<getMalzemelerTable().getTable().getRowCount();i++){
            if(getMalzemelerTable().getTable().getValueAt(i,0).equals(malzeme))
                return true;
        }
        return false; 
    }
  /*  public static void main(String[] args) {
		MainPersistedTestCase mptc = new MainPersistedTestCase();
		try {
			mptc.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MalzemeBozulmasi malzemeZayiatý = new MalzemeBozulmasi("Malzeme Zayiatý");
		malzemeZayiatý.setVisible(true);
	}*/
}
