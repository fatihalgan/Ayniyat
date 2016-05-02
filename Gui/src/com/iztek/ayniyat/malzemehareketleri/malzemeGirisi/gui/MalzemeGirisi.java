package com.iztek.ayniyat.malzemehareketleri.malzemeGirisi.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;

import org.jdesktop.jdnc.JNTable;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kullanici.KullaniciManager;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.controller.ButtonController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeGirisFisi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemePahasi;
import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.panel.IMalzemeHareketleriPanel;
import com.iztek.ayniyat.tanimlar.gui.MalzemeTanimlariAnaPanel;
import com.iztek.ayniyat.util.uicomponents.ButtonPanel1;
import com.iztek.ayniyat.util.uicomponents.HareketBilgileriPanel1;
import com.iztek.ayniyat.util.uicomponents.MalzemeTipiSecimiPanel;
import com.iztek.ayniyat.util.uicomponents.SatinAlmaBilgileriPanel;

/**
 * @author Sevgi USLU
 */
public class MalzemeGirisi extends JFrame implements IAyniyatPanel,IMalzemeHareketleriPanel {

	private javax.swing.JPanel jContentPane = null;
	private javax.swing.JPanel anaPanel = null;
	private javax.swing.JPanel bilgiPanel = null;
	private javax.swing.JPanel tablePanel = null;
	private HareketBilgileriPanel1 girisBilgileriPanel=null;
	private SatinAlmaBilgileriPanel satinalmaBilgileriPanel = null;
	private ButtonPanel1 buttonPanel=null;
	private MalzemeTipiSecimiPanel malzemeTipiSecimiPanel=null;
	public ButtonController buttonController;
	private Vector ambarSorumlulari = null;
	private String panelName = null;
	private String errorMessage = null;
	private JNTable malzemelerTable = null;
	private AyniyatTableModel tableForYeniGiris = null;
	private AyniyatTableModel tableForKayitliGiris = null;
	private Integer transferNo = null;
	public JTextField girisSekliTextField = null;

	public MalzemeGirisi(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}

	public void initialize() {
		AnaController.getInstance().getMalzemeGirisiController()
				.registerMalzemeGirisi(MalzemeGirisi.this);
		buttonController = new ButtonController(
				getMalzemelerTable().getModel(),
				new JButton[] { getButtonPanel().getCikarButton(),getButtonPanel().getTemizleButton(),
					getButtonPanel().getKaydetButton() }, new JMenuItem[] {
					getButtonPanel().getCikarMenuItem(), getButtonPanel().getKaydetMenuItem(),
					getButtonPanel().getTemizleMenuItem() });
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Malzeme Giriþ Hareketi");
		this.setResizable(false);
	}

	public javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new javax.swing.BoxLayout(jContentPane,
					javax.swing.BoxLayout.Y_AXIS));
			jContentPane.add(getAnaPanel(), null);
		}
		return jContentPane;
	}

	public javax.swing.JPanel getAnaPanel() {
		if (anaPanel == null) {
			anaPanel = new javax.swing.JPanel();
			anaPanel.setLayout(new BorderLayout());
			anaPanel.setPreferredSize(new java.awt.Dimension(462,674));
			anaPanel.add(getBilgiPanel(), java.awt.BorderLayout.NORTH);
			anaPanel.add(getTablePanel(), java.awt.BorderLayout.CENTER);
			anaPanel.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		}
		return anaPanel;
	}
	
	private ButtonPanel1 getButtonPanel(){
		if(buttonPanel==null){
			buttonPanel=new ButtonPanel1();
			buttonPanel.registerPanel(this);
			buttonPanel.getDevretMenuItem().setVisible(false);
			buttonPanel.getKaydetMenuItem().setVisible(true);
			buttonPanel.getKaydetButton().setEnabled(true);
		}
		return buttonPanel;
	}
	
	private javax.swing.JPanel getBilgiPanel() {
		if (bilgiPanel == null) {
			bilgiPanel = new javax.swing.JPanel();
			bilgiPanel.setLayout(new javax.swing.BoxLayout(bilgiPanel,
					javax.swing.BoxLayout.X_AXIS));
			bilgiPanel.add(getGirisBilgileriPanel(), null);
			bilgiPanel.add(getSatinalmaBilgileriPanel(), null);
			bilgiPanel.setPreferredSize(new java.awt.Dimension(62, 200));
		}
		return bilgiPanel;
	}
	public HareketBilgileriPanel1 getGirisBilgileriPanel(){
		if (girisBilgileriPanel == null) {
			girisBilgileriPanel=new HareketBilgileriPanel1();
			girisBilgileriPanel.getHareketBilgileriPanel().setBorder
			(javax.swing.BorderFactory.createTitledBorder(
					BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Giri\u015f Bilgileri", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(113, 7, 113)));
		}
		return girisBilgileriPanel;
	}
	public SatinAlmaBilgileriPanel getSatinalmaBilgileriPanel(){
		if (satinalmaBilgileriPanel == null) {
			satinalmaBilgileriPanel=new SatinAlmaBilgileriPanel();
			satinalmaBilgileriPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Sat\u0131nalma Bilgileri", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(113, 7, 113)));
			
		}
		return satinalmaBilgileriPanel;
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

	public void disposeThis() {
		this.dispose();
	}

	public JNTable getMalzemelerTable() {
		if (malzemelerTable == null) {
			malzemelerTable = new JNTable(getTableForYeniGiris());
			malzemelerTable.setBounds(new java.awt.Rectangle(0, 0, 780, 80));
			malzemelerTable.setHasColumnControl(true);
			malzemelerTable.setHeaderBackground(new java.awt.Color(204, 255,
					255));
			malzemelerTable.setHeaderFont(new java.awt.Font("Dialog",
					java.awt.Font.BOLD, 12));
			malzemelerTable
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(
									javax.swing.BorderFactory
											.createBevelBorder(javax.swing.border.BevelBorder.LOWERED),
									"Giriþi Yapýlacak Malzemeler",
									javax.swing.border.TitledBorder.LEFT,
									javax.swing.border.TitledBorder.DEFAULT_POSITION,
									new java.awt.Font("Dialog",
											java.awt.Font.BOLD, 12),
									new java.awt.Color(113, 7, 113)));
			malzemelerTable.setRowMargin(1);
			malzemelerTable.setFont(new Font("Dialog", Font.PLAIN, 12));
			malzemelerTable.setPopupMenu(getButtonPanel().getJPopupMenu());
		}
		return malzemelerTable;
	}

	public Vector getAmbarSorumlulari() {
		if (ambarSorumlulari == null)
			AnaController.getInstance().getMalzemeGirisiController()
					.findAmbarSorumlulari();
		return ambarSorumlulari;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setAmbarSorumlulari(Vector ambarSorumlulari) {
		this.ambarSorumlulari = ambarSorumlulari;
	}

	private boolean isTableEmpty() {
		if (malzemelerTable.getTable().getRowCount() > 0) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isRowSelected() {
		if (malzemelerTable.getTable().getSelectedRow() == -1) {
			return false;
		} else {
			return true;
		}
	}

	public void loadYourself(IKategorilendirilebilir node,
			IKategorilendirilebilir parentNode) {
	}

	public void unLoadYourself() {
		
		getGirisBilgileriPanel().unLoadYourself();
		getGirisBilgileriPanel().getHareketiOnaylayanTextField().setText(KullaniciManager.getpersonel(
				AnaController.getInstance().getKullanici()).toString());
		getSatinalmaBilgileriPanel().getFaturaNoTextField().setText("");
		getTableForYeniGiris().removeAllRows();
		getTableForKayitliGiris().removeAllRows();
		getButtonPanel().getEkleMenuItem().setEnabled(true);
	}
	
	public void setSatinalmaBilgileriPanelEnabled(boolean value) {
		getSatinalmaBilgileriPanel().setSatinalmaBilgileriPanelEnabled(value);

	}

	public Integer getTransferNo() {
		return this.transferNo;
	}

	public void setTransferNo(Integer no) {
		this.transferNo = no;
	}

	private boolean checkInputFields() {
		if (getGirisBilgileriPanel().getHareketSekliTextField().getText().equals("")
				|| getGirisBilgileriPanel().getAlindigiKurulusComboBox().getSelectedItem().toString().equals("")) {
			errorMessage = "Lütfen bütün giriþ bilgilerinin tamamýný doldurunuz.";
			return false;
		} else if (getGirisBilgileriPanel().getHareketSekliTextField().getText().equals("Satýnalma")
				&& (getSatinalmaBilgileriPanel().getFaturaNoTextField().getText().equals("") )) {
			errorMessage = "Lütfen fatura bilgilerini giriniz";
			return false;
		
		}
		return true;
	}
	public Vector getGirisiYapilacakMalzemeler() {
		Vector malzemeler = new Vector();
		if (getGirisBilgileriPanel().getHareketSekliTextField().getText().equals(
				MalzemeGirisFisi.TAMIREDILDI)
				|| getGirisBilgileriPanel().getHareketSekliTextField().getText().equals(
						MalzemeGirisFisi.DEVIR)) {
			for (int i = 0; i < ((AyniyatTableModel) getMalzemelerTable()
					.getModel()).getRowCount(); i++) {
				malzemeler.add(((AyniyatTableModel) malzemelerTable.getModel())
						.getValueAt(i, 0));
			}
		} else {
			HashMap malzemeTanimlari = new HashMap();
			for (int i = 0; i < ((AyniyatTableModel) getMalzemelerTable()
					.getModel()).getRowCount(); i++) {
				int miktar = Integer
						.parseInt(((AyniyatTableModel) getMalzemelerTable()
								.getModel()).getValueAt(i, 1).toString());
				AbstractMalzeme tabloSatiri = (AbstractMalzeme) ((AyniyatTableModel) getMalzemelerTable()
						.getModel()).getValueAt(i, 0);
				for (int j = 0; j < miktar; j++) {
					// TODO þimdilik sadece demirbaþ malzeme giriþi yapýlýyor
					AbstractMalzeme malzeme = new DemirbasMalzeme();

					// ayný malzeme tanimina sahip birden fazla malzeme tabloya
					// eklendiginde database e yansýtýlmasý olayýnda
					// ayný id degerine sahip fakat farklý nesneler olan
					// malzemeTanimlari ayný sessionda bulunamaz. Bu problemi
					// asmak amaciyla malzemeTanimlarini hashMapde tutuyoruz.
					IMalzemeTanimi tanim = (IMalzemeTanimi) malzemeTanimlari
							.get(tabloSatiri.getMalzemeTanimi().getId());
					if (tanim == null) {
						tanim = tabloSatiri.getMalzemeTanimi();
						malzemeTanimlari.put(tanim.getId(), tanim);
					}
					malzeme.setMalzemeTanimi(tanim);

					for (int k = 0; k < tabloSatiri.getNitelikDegerleri()
							.size(); k++)
						malzeme.addNitelikDegeri(new NitelikDegeri(
								((NitelikDegeri) (tabloSatiri
										.getNitelikDegerleri().toArray())[k])
										.getNitelikAdi(),
								((NitelikDegeri) (tabloSatiri
										.getNitelikDegerleri().toArray())[k])
										.getNitelikDegeri()));

					MalzemePahasi malzemePahasi = new MalzemePahasi();
					malzemePahasi.setKdvOrani(tabloSatiri.getPaha()
							.getKdvOrani());
					malzemePahasi.setOtvOrani(tabloSatiri.getPaha()
							.getOtvOrani());
					malzemePahasi.setIskontoTutari(tabloSatiri.getPaha()
							.getIskontoTutari());
					malzemePahasi.setVergisizBirimFiyat(tabloSatiri.getPaha()
							.getVergisizBirimFiyat());
					malzeme.setPaha(malzemePahasi);
					malzemeler.add(malzeme);
				}
			}
		}
		return malzemeler;
	}

	public AyniyatTableModel getTableForYeniGiris() {
		if (tableForYeniGiris == null) {
			tableForYeniGiris = new AyniyatTableModel(new String[]{ "Cinsi", "Miktar", "Ölçek",
					"Fiyat(KDV/ÖTV'siz) YTL.", "Tutarý YTL", "KDV(%)",
					"ÖTV(%)", "Ýskonto(%)", "KDV/ÖTV'li Tutar" });
		}
		return tableForYeniGiris;
	}

	public AyniyatTableModel getTableForKayitliGiris() {
		if (tableForKayitliGiris == null) {
			tableForKayitliGiris = new AyniyatTableModel(new String[]{ "Demirbaþ Numarasý", "Cinsi", "Ölçek",
					"Fiyat(Vergisiz)", "Vergi(%)" });
		}
		return tableForKayitliGiris;
	}

	public void tabloyaEkleAction() {
		if (getGirisBilgileriPanel().getHareketSekliTextField().getText().equals(
				MalzemeGirisFisi.TAMIREDILDI)) {
			new BozukMalzemeSecimi(AnaController.getInstance().getAnaPanel())
					.setVisible(true);
		} else if (getGirisBilgileriPanel().getHareketSekliTextField().getText().equals(
				MalzemeGirisFisi.DEVIR)) {
			AnaController.getInstance()
					.ambardanGelenleriOneGetirMalzemeGirisiIcin();
		} else {
			AnaController.getInstance().setCurrentMalzemeTanimlariPanelRole(
					AnaController.MALZEMETANIMLARI_FOR_DIALOG);
			AnaController.getInstance().setCurrentTanimlarController(AnaController.getInstance().getMalzemeTanimlariController());
			
			new MalzemeTanimlariAnaPanel(AnaController.getInstance()
					.getAnaPanel(), "malzemeTanimlariPanel").setVisible(true);
		}
	}

	public void kaydetAction() {
		boolean success = false;
		// giris bilgilerini kontrol et
		if (!checkInputFields()) {
			AnaController.getInstance().showWarningDialogBox(
					MalzemeGirisi.this, errorMessage);
			return;
		}
		// giris yapmak icin onay al
		boolean onay = AnaController.getInstance().showConfirmationDialogBox(
				MalzemeGirisi.this, "Malzemelerin giriþini onaylýyor musunuz?");
		if (!onay)
			return;
		// islemi gerceklestir
		success = AnaController.getInstance().getMalzemeGirisiController()
				.malzemeGirisHareketiOlustur();

		// islem basarýyla gerceklestirilmis mi kontrol et ve tum ekraný temizle
		if (success)
			unLoadYourself();
	}

	public void menuDegisikligiEtkisiniGercekle() {
		getTableForYeniGiris().removeAllRows();
		getTableForKayitliGiris().removeAllRows();
		getButtonPanel().getEkleButton().setEnabled(true);
		getButtonPanel().getEkleMenuItem().setEnabled(true);
	}

	public void loadYourselfForKarsiAmbardanMalzemeGirisi(
			String karsiAmbarIsmi, String teslimEdenPersonelAdi) {
		unLoadYourself();
		getGirisBilgileriPanel().getHareketSekliTextField().setText(MalzemeGirisFisi.DEVIR);
	//	getGirisBilgileriPanel().getAlindigiKurulusTextField().setText(karsiAmbarIsmi);
		getGirisBilgileriPanel().getAlindigiKurulusComboBox().setSelectedItem(karsiAmbarIsmi);
		getGirisBilgileriPanel().getTeslimEdenTextField().setText(teslimEdenPersonelAdi);
		setSatinalmaBilgileriPanelEnabled(false);
		getMalzemelerTable().setModel(getTableForKayitliGiris());
		buttonController.setTableModel(getMalzemelerTable().getModel());
		buttonController.tableChanged(new TableModelEvent(getMalzemelerTable()
				.getModel()));
		menuDegisikligiEtkisiniGercekle();
	}
		
}
