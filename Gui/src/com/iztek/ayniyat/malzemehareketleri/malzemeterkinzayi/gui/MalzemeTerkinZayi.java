package com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.jdnc.JNTable;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.controller.ButtonController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.HareketBilgileri;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.panel.IMalzemeHareketleriPanel;
import com.iztek.ayniyat.util.uicomponents.ButtonPanel1;
import com.iztek.ayniyat.util.uicomponents.DateTextField;
import com.iztek.ayniyat.util.uicomponents.MalzemeTipiSecimiPanel;

public abstract class MalzemeTerkinZayi extends JFrame implements IAyniyatPanel,IMalzemeHareketleriPanel{
	
	private JPanel jContentPane = null;
	private JPanel anaPanel = null;
	private JPanel terkinBilgileriPanel = null;
	private JLabel duzenlemeTarihiLabel = null;
	private JLabel cikisiOnaylayanLabel = null;
	private DateTextField duzenlemeTarihiTextField = null;
	private JTextField cikisiOnaylayanTextField = null;
	private JNTable malzemelerTable = null;
	private JLabel belgeNoLabel = null;
	private JTextField belgeNoTextField = null;
	private JPanel tablePanel = null;
	private ButtonController buttonController = null;
	private String panelName = null;
	private Vector ambarSorumlulari = null;  //  @jve:decl-index=0:
	private String belgeNo="";
	private DateTextField terkinTarihiTextField = null;
	private MalzemeTipiSecimiPanel malzemeTipiSecimiPanel=null;
	private ButtonPanel1 buttonPanel=null;
	public JLabel terkinTarihiLabel = null;
	public JLabel terkinNedeniLabel = null;
	private JLabel tutanakNoLabel = null;
	private JLabel tutanakTarihiLabel = null;
	public JLabel terkinEdenLabel = null;
	private JLabel aciklamaLabel = null;
	private JTextField tutanakNoTextField = null;
	private DateTextField tutanakTarihiTextField = null;
	private JTextField terkinEdenTextField = null;
	private JTextField aciklamaTextField = null;
	private JTextField terkinNedeniTextField = null;
	
	public MalzemeTerkinZayi(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}

	public void initialize() {
		buttonController = new ButtonController(getMalzemelerTable().getModel(),new JButton[] 
		   {getButtonPanel().getCikarButton(),getButtonPanel().getKaydetButton(),
			getButtonPanel().getTemizleButton()},new JMenuItem[] {getButtonPanel().getCikarMenuItem(),
			getButtonPanel().getKaydetMenuItem(),getButtonPanel().getTemizleMenuItem()});
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Malzeme Terkin Hareketi");
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
			anaPanel.setLayout(new java.awt.BorderLayout());
			anaPanel.add(getTerkinBilgileriPanel(), java.awt.BorderLayout.NORTH);
			anaPanel.add(getTablePanel(), java.awt.BorderLayout.CENTER);
			anaPanel.add(getButtonPanel(),BorderLayout.SOUTH);
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

	public JPanel getTerkinBilgileriPanel() {
		if(terkinBilgileriPanel == null) {
			aciklamaLabel = new JLabel();
			aciklamaLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			terkinEdenLabel = new JLabel();
			terkinEdenLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			tutanakTarihiLabel = new JLabel();
			tutanakTarihiLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			tutanakNoLabel = new JLabel();
			tutanakNoLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			terkinNedeniLabel = new JLabel();
			terkinNedeniLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			terkinTarihiLabel = new JLabel();
			terkinTarihiLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			belgeNoLabel = new JLabel();
			terkinBilgileriPanel = new JPanel();
			terkinBilgileriPanel.setLayout(null);
			terkinBilgileriPanel.add(getDuzenlemeTarihiLabel(), null);
			terkinBilgileriPanel.add(getCikisiOnaylayanLabel(), null);
			terkinBilgileriPanel.add(getDuzenlemeTarihiTextField(), null);
			terkinBilgileriPanel.add(getCikisiOnaylayanTextField(), null);
			terkinBilgileriPanel.setPreferredSize(new java.awt.Dimension(1,200));
			terkinBilgileriPanel.add(belgeNoLabel, null);
			terkinBilgileriPanel.add(getBelgeNoTextField(), null);
			belgeNoLabel.setText("Belge No :");
			belgeNoLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			belgeNoLabel.setSize(69, 20);
			belgeNoLabel.setLocation(320, 19);
			terkinTarihiLabel.setSize(129, 20);
			terkinTarihiLabel.setLocation(16, 85);
			terkinTarihiLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			terkinNedeniLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			terkinNedeniLabel.setSize(129, 20);
			terkinNedeniLabel.setLocation(16, 119);
			tutanakNoLabel.setText("Tutanak No :");
			tutanakNoLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			tutanakNoLabel.setLocation(419, 51);
			tutanakNoLabel.setSize(129, 20);
			tutanakTarihiLabel.setBounds(419, 85, 129, 20);
			tutanakTarihiLabel.setText("Tutanak Tarihi :");
			tutanakTarihiLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			terkinEdenLabel.setBounds(419, 119, 129, 20);
			
			terkinEdenLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			aciklamaLabel.setBounds(419, 153, 129, 20);
			aciklamaLabel.setText("Açýklama :");
			aciklamaLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			terkinBilgileriPanel.add(getTerkinTarihiTextField(), null);
			terkinBilgileriPanel.add(terkinTarihiLabel, null);
			terkinBilgileriPanel.add(terkinNedeniLabel, null);
			terkinBilgileriPanel.add(tutanakNoLabel, null);
			terkinBilgileriPanel.add(tutanakTarihiLabel, null);
			terkinBilgileriPanel.add(terkinEdenLabel, null);
			terkinBilgileriPanel.add(aciklamaLabel, null);
			terkinBilgileriPanel.add(getTutanakNoTextField(), null);
			terkinBilgileriPanel.add(getTutanakTarihiTextField(), null);
			terkinBilgileriPanel.add(getTerkinEdenTextField(), null);
			terkinBilgileriPanel.add(getAciklamaTextField(), null);
			terkinBilgileriPanel.add(getTerkinNedeniTextField(), null);
		}
		return terkinBilgileriPanel;
	}

	private JLabel getDuzenlemeTarihiLabel() {
		if(duzenlemeTarihiLabel == null) {
			duzenlemeTarihiLabel = new JLabel();
			duzenlemeTarihiLabel.setBounds(16, 51, 129, 20);
			duzenlemeTarihiLabel.setText("Düzenleme Tarihi :");
			duzenlemeTarihiLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			duzenlemeTarihiLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
		}
		return duzenlemeTarihiLabel;
	}

	private JLabel getCikisiOnaylayanLabel() {
		if(cikisiOnaylayanLabel == null) {
			cikisiOnaylayanLabel = new JLabel();
			cikisiOnaylayanLabel.setText("Çýkýþý Onaylayan :");
			cikisiOnaylayanLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			cikisiOnaylayanLabel.setBorder(BorderFactory.createLineBorder(new Color(204,204,204)));
			cikisiOnaylayanLabel.setSize(129, 20);
			cikisiOnaylayanLabel.setLocation(16, 153);
		}
		return cikisiOnaylayanLabel;
	}

	private DateTextField getDuzenlemeTarihiTextField() {
		if(duzenlemeTarihiTextField == null) {
			duzenlemeTarihiTextField = new DateTextField();
			duzenlemeTarihiTextField.setBounds(145, 51, 230, 24);
			duzenlemeTarihiTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			}
		return duzenlemeTarihiTextField;
	}

	private JTextField getCikisiOnaylayanTextField() {
		if(cikisiOnaylayanTextField == null) {
			cikisiOnaylayanTextField = new JTextField();
			cikisiOnaylayanTextField.setBounds(145, 153, 230, 19);
			cikisiOnaylayanTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			cikisiOnaylayanTextField.setEditable(false);
		}
		return cikisiOnaylayanTextField;
	}

	public JNTable getMalzemelerTable() {
		if(malzemelerTable == null) {
			AyniyatTableModel model = new AyniyatTableModel(new String[]{"Demirbaþ No","Cinsi","Ölçek","Fiyat(Vergisiz)","Vergi (%)"});
			malzemelerTable = new JNTable(model);
			malzemelerTable.setHasColumnControl(true);
			malzemelerTable.setHeaderBackground(new java.awt.Color(204, 255, 255));
			malzemelerTable.setBounds(new Rectangle(6, 54, 788, 441));
			malzemelerTable.setHeaderFont(new java.awt.Font("Dialog",
			 java.awt.Font.BOLD, 12));
			malzemelerTable.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Terkin Malzemeler", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
			malzemelerTable.setPopupMenu(getButtonPanel().getJPopupMenu());
		}
		return malzemelerTable;
	}

	public JTextField getBelgeNoTextField() {
		if (belgeNoTextField == null) {
			belgeNoTextField = new JTextField();
			belgeNoTextField.setEditable(false);
			belgeNoTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			belgeNoTextField.setHorizontalAlignment(JTextField.CENTER);
			belgeNoTextField.setSize(89, 19);
			belgeNoTextField.setLocation(389, 19);
		}
		return belgeNoTextField;
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
 
	private DateTextField getTerkinTarihiTextField() {
		if (terkinTarihiTextField == null) {
			terkinTarihiTextField = new DateTextField();
			terkinTarihiTextField.setBounds(145, 85, 230, 24);
			terkinTarihiTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
		
		}
		return terkinTarihiTextField;
	}
  
	private JTextField getTutanakNoTextField() {
		if (tutanakNoTextField == null) {
			tutanakNoTextField = new JTextField();
			tutanakNoTextField.setBounds(548, 51, 230, 20);
			tutanakNoTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			tutanakNoTextField.setText("");
		}
		return tutanakNoTextField;
	}
  
	private DateTextField getTutanakTarihiTextField() {
		if (tutanakTarihiTextField == null) {
			tutanakTarihiTextField = new DateTextField();
			tutanakTarihiTextField.setBounds(548, 85, 230, 23);
			tutanakTarihiTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
		}
		return tutanakTarihiTextField;
	}
  
	private JTextField getTerkinEdenTextField() {
		if (terkinEdenTextField == null) {
			terkinEdenTextField = new JTextField();
			terkinEdenTextField.setBounds(548, 119, 230, 20);
			terkinEdenTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			terkinEdenTextField.setText("");
		}
		return terkinEdenTextField;
	}
 
	private JTextField getAciklamaTextField() {
		if (aciklamaTextField == null) {
			aciklamaTextField = new JTextField();
			aciklamaTextField.setBounds(548, 153, 230, 20);
			aciklamaTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			aciklamaTextField.setText("");
		}
		return aciklamaTextField;
	}
 
	public Vector getAmbarSorumlulari() {
		if (ambarSorumlulari == null)
			AnaController.getInstance().getMalzemeTerkinController().findAmbarSorumlulari();
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
	    cikisiOnaylayanTextField.setText(KullaniciManager.getpersonel(AnaController.getInstance().getKullanici()).toString());
	    belgeNoTextField.setText("");
	    tutanakNoTextField.setText("");
	    terkinEdenTextField.setText("");
	    aciklamaTextField.setText("");
	    ((AyniyatTableModel)malzemelerTable.getModel()).removeAllRows();
	}
	
	public Date getDuzenlemeTarihi(){
	   
        return duzenlemeTarihiTextField.getDate();
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
	        
	        if(!isMalzemeTerkinZayi(malzeme)){
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
	
	public abstract boolean isMalzemeTerkinZayi(IDemirbasMalzeme demirbas);
	
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
	
	public abstract boolean cikisIslemiOnayiAl();
	
	public String getBelgeNo() {
        return belgeNo;
    }
    
    public void setBelgeNo(String belgeNo) {
        this.belgeNo = belgeNo;
    }
    
    public HareketBilgileri getBilgiler() {
		HareketBilgileri terkinBilgileri = new HareketBilgileri();
		terkinBilgileri.setAciklama(getAciklamaTextField().getText());
		terkinBilgileri.setTutanakNo(getTutanakNoTextField().getText());
		terkinBilgileri.setTutanakTarihi(getTutanakTarihiTextField().getDate());
		terkinBilgileri.setZayi_terkinNedeni(getTerkinNedeniTextField().getText());
		if (!getTerkinTarihiTextField().getDate().equals(""))
			terkinBilgileri.setZayi_terkinTarihi(getTerkinTarihiTextField()
					.getDate());
		terkinBilgileri.setZayi_terkinEden(getTerkinEdenTextField().getText());

		return terkinBilgileri;
	}
    
    public void tabloyaEkleAction(){
        new TerkinZayiAramaSecimi(AnaController.getInstance().getAnaPanel()).setVisible(true);
    }
   
    
    public boolean gerekliBilgilerMevcutOnayiAl(){
        if (getDuzenlemeTarihiTextField().getDate().equals("") || getTerkinNedeniTextField().getText().equals("") || 
            getTutanakNoTextField().getText().equals("") || getTutanakTarihiTextField().getDate().equals(""))
            return false;
        return true;
    }
    
    private boolean isTablodaMevcut(IDemirbasMalzeme malzeme){
        for (int i=0;i<getMalzemelerTable().getTable().getRowCount();i++){
            if(getMalzemelerTable().getTable().getValueAt(i,0).equals(malzeme))
                return true;
        }
        return false; 
    }

    private JTextField getTerkinNedeniTextField() {
		if (terkinNedeniTextField == null) {
			terkinNedeniTextField = new JTextField();
			terkinNedeniTextField.setLocation(new java.awt.Point(145,120));
			terkinNedeniTextField.setPreferredSize(new java.awt.Dimension(4,22));
			terkinNedeniTextField.setSize(new java.awt.Dimension(230,19));
		}
		return terkinNedeniTextField;
	}
    public abstract void kaydetAction();

	/*public static void main(String[] args) {
		MainPersistedTestCase mptc = new MainPersistedTestCase();
		try {
			mptc.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//MalzemeTerkini malzemeZayiatý = new MalzemeTerkini("Malzeme Terkini");
		//malzemeZayiatý.setVisible(true);
	}*/
}
