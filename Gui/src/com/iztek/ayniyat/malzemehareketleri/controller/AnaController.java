package com.iztek.ayniyat.malzemehareketleri.controller;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kullanici.Kullanici;
import com.iztek.ayniyat.malzemehareketleri.anapanel.gui.MalzemeHareketleriAnaPanel;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.malzemeGirisi.controller.MalzemeGirisiController;
import com.iztek.ayniyat.malzemehareketleri.malzemebozuk.controller.MalzemeBozulmasiController;
import com.iztek.ayniyat.malzemehareketleri.malzemecikisi.controller.MalzemeCikisiController;
import com.iztek.ayniyat.malzemehareketleri.malzemedevri.controller.MalzemeDevriController;
import com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.controller.MalzemeTerkinController;
import com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.controller.MalzemeZayiatiController;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.sorgular.ambaraDevir.controller.AmbaraDevirSorguController;
import com.iztek.ayniyat.sorgular.bozukMalzemeSorgulama.controller.BozukMalzemeSorguController;
import com.iztek.ayniyat.sorgular.controller.MalzemeSorguController;
import com.iztek.ayniyat.sorgular.malzemeizleme.controller.MalzemeIzlemeController;
import com.iztek.ayniyat.sorgular.stoksorgulama.controller.StokSorguController;
import com.iztek.ayniyat.sorgular.terkinMalzemeSorgulama.controller.TerkinMalzemeSorguController;
import com.iztek.ayniyat.sorgular.yevmiyedefteri.controller.YevmiyeDefteriController;
import com.iztek.ayniyat.sorgular.zayiMalzemeSorgulama.controller.ZayiMalzemeSorgulamaController;
import com.iztek.ayniyat.sorgular.zimmetsorgulama.controller.ZimmetSorgulamaController;
import com.iztek.ayniyat.tanimlar.controller.ITanimlarController;
import com.iztek.ayniyat.tanimlar.controller.MalzemeTanimlariController;
import com.iztek.ayniyat.tanimlar.controller.YerlesimController;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;

/**
 * @author Cagdas CIRIT + Sevgi USLU + Umit Akyol
 */
public class AnaController {
    private static AnaController instance = new AnaController();
    public static String YERLESIM_FOR_TANIM = "YerlesimForTanim";
    public static String YERLESIM_FOR_DIALOG_ZIMMETALAN_SEC = "YerlesimForDialogZimmetalanSec";
    public static String YERLESIM_FOR_DIALOG_DEMIRBAS_SEC = "YerlesimForDialogDemirbasSec";
    public static String MALZEMETANIMLARI_FOR_TANIM = "MalzemeTanimlariForTanim";
    public static String MALZEMETANIMLARI_FOR_DIALOG = "MalzemeTanimlariForDialog";
    public static String MALZEME_GIRISI="malzemeGirisi";
    public static String MALZEME_CIKISI="malzemeCikisi";
    public static String MALZEME_DEVIR="malzemeDevir";
    public static String MALZEME_ZAYI="malzemeZayi";
    public static String MALZEME_TERKIN="malzemeTerkin";
    public static String MALZEME_BOZUK="malzemeBozuk";
    public static String STOK_SORGULAMA="stokSorgulama";
    public static String ZIMMET_SORGULAMA="zimmetSorgulama";
    public static String BOZUKMALZEME_SORGULAMA="bozukMalzemeSorgulama";
    public static String ZAYIMALZEME_SORGULAMA="zayiMalzemeSorgulama";
    public static String TERKINMALZEME_SORGULAMA="terkinMalzemeSorgulama";
    public static String MALZEME_IZLEME="malzemeIzleme";
    public static String YEVMIYE_DEFTERI = "yevmiyeDefteri";
    public static String AMBARA_DEVIR_CIKANLAR = "ambaraDevirCikanlar";
    public static String AMBARA_DEVIR_GELENLER = "ambaraDevirGelenler";
    private MalzemeHareketleriAnaPanel malzemeHareketleriAnaPanel;
    private MalzemeTanimlariController malzemeTanimlariController;
    private YerlesimController yerlesimController;
    private ITanimlarController tanimlarController=null;
    private MalzemeGirisiController malzemeGirisiController;
    private MalzemeCikisiController malzemeCikisiController;
    private MalzemeDevriController malzemeDevriController;
    private MalzemeTerkinController malzemeTerkinController;
    private MalzemeZayiatiController malzemeZayiatiController;
    private MalzemeBozulmasiController malzemeBozulmasiController;
    private ZimmetSorgulamaController zimmetSorgulamaController;
    private BozukMalzemeSorguController bozukMalzemeSorguController;
    private TerkinMalzemeSorguController terkinMalzemeSorguController;
    private ZayiMalzemeSorgulamaController zayiMalzemeSorguController;
    private MalzemeIzlemeController malzemeIzlemeController;
    private AmbaraDevirSorguController ambaraDevirSorguController;
    private StokSorguController stokSorguController;
    private YevmiyeDefteriController yevmiyeDefteriController = null;
    private String currentYerlesimPanelRole = YERLESIM_FOR_TANIM;
    private String currentMalzemeTanimlariPanelRole = MALZEMETANIMLARI_FOR_TANIM;
    private String currentVisiblePanel=null;
    private IZimmetAlan islemYapanAmbar = null;
    private Kullanici kullanici = null;

    private AnaController() {
        malzemeTanimlariController = new MalzemeTanimlariController();
        yerlesimController = new YerlesimController();
        malzemeGirisiController = new MalzemeGirisiController();
        malzemeCikisiController = new MalzemeCikisiController();
        malzemeDevriController = new MalzemeDevriController();
        malzemeTerkinController=new MalzemeTerkinController();
        malzemeZayiatiController = new MalzemeZayiatiController();
        malzemeBozulmasiController = new MalzemeBozulmasiController();
        zimmetSorgulamaController = new ZimmetSorgulamaController(); 
        malzemeIzlemeController = new MalzemeIzlemeController();
        stokSorguController = new StokSorguController();
        bozukMalzemeSorguController=new BozukMalzemeSorguController();
        zayiMalzemeSorguController=new ZayiMalzemeSorgulamaController();
        terkinMalzemeSorguController=new TerkinMalzemeSorguController();
        yevmiyeDefteriController = new YevmiyeDefteriController();
        ambaraDevirSorguController = new AmbaraDevirSorguController();
    }

    public static AnaController getInstance() {
        return instance;
    }
    
    //METHODS
    public void malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(String panelName) {
        malzemeHareketleriAnaPanel.getMalzemeCardLayout().show( malzemeHareketleriAnaPanel.getCardLayoutPanel(), panelName);
        if (panelName.equals(MALZEME_CIKISI))
            setCurrentVisiblePanel(MALZEME_CIKISI);
        else if (panelName.equals(MALZEME_GIRISI))
            setCurrentVisiblePanel(MALZEME_GIRISI);
        else if (panelName.equals(MALZEME_ZAYI))
            setCurrentVisiblePanel(MALZEME_ZAYI);
        else if (panelName.equals(MALZEME_TERKIN))
            setCurrentVisiblePanel(MALZEME_TERKIN);
        else if (panelName.equals(MALZEME_DEVIR))
            setCurrentVisiblePanel(MALZEME_DEVIR);
        else if (panelName.equals(MALZEME_BOZUK))
            setCurrentVisiblePanel(MALZEME_BOZUK);
        else if (panelName.equals(ZIMMET_SORGULAMA))
            setCurrentVisiblePanel(ZIMMET_SORGULAMA);
        else if (panelName.equals(MALZEME_IZLEME))
            setCurrentVisiblePanel(MALZEME_IZLEME);
        else if (panelName.equals(STOK_SORGULAMA))
            setCurrentVisiblePanel(STOK_SORGULAMA);
        else if(panelName.equals(YEVMIYE_DEFTERI))
            setCurrentVisiblePanel(YEVMIYE_DEFTERI);
        else if (panelName.equals(BOZUKMALZEME_SORGULAMA))
            setCurrentVisiblePanel(BOZUKMALZEME_SORGULAMA);  
        else if (panelName.equals(TERKINMALZEME_SORGULAMA))
            setCurrentVisiblePanel(TERKINMALZEME_SORGULAMA);  
        else if (panelName.equals(ZAYIMALZEME_SORGULAMA))
            setCurrentVisiblePanel(ZAYIMALZEME_SORGULAMA);  
    }
    
    public void malzemeCikisPanelindeZimmetAlaniDoldur(IZimmetAlan zimmetAlan){
        if(currentVisiblePanel == MALZEME_CIKISI)
            malzemeHareketleriAnaPanel.getMalzemeCikisi().getHareketBilgileriPanelForCikis().setTemporary(zimmetAlan);
        else if(currentVisiblePanel == MALZEME_DEVIR)
            malzemeHareketleriAnaPanel.getMalzemeDevri().getDevirBilgileriPanel().setTemporary(zimmetAlan);
        else if(currentVisiblePanel == ZIMMET_SORGULAMA)
            getZimmetSorgulamaController().zimmetSahibindenSorgula((AbstractZimmetAlan)zimmetAlan);
    }

    public void secilenZimmetliMalzemeyiListele(Vector zimmetliMalzemeler){
        if(currentVisiblePanel ==MALZEME_ZAYI) 
            malzemeZayiatiController.secilenDemirbaslariTabloyaGonder(zimmetliMalzemeler);
        else if(currentVisiblePanel ==MALZEME_TERKIN) 
            malzemeTerkinController.secilenDemirbaslariTabloyaGonder(zimmetliMalzemeler);
        else if(currentVisiblePanel == MALZEME_DEVIR)
            malzemeDevriController.secilenDemirbaslariTabloyaKoy(zimmetliMalzemeler);
        else if(currentVisiblePanel ==MALZEME_BOZUK) 
            malzemeBozulmasiController.secilenDemirbaslariBozukTablosunaGonder(zimmetliMalzemeler);
        else if(currentVisiblePanel ==MALZEME_IZLEME) 
            malzemeIzlemeController.zimmetlerdenIzle(zimmetliMalzemeler);
    }
    
    public void sorgulananDemirbaslariGoruntule(Vector demirbaslar){
        if(currentVisiblePanel == ZIMMET_SORGULAMA)
            zimmetSorgulamaController.sendDemirbaslarToTable(demirbaslar);
    }
	public void setCurrentTanimlarController(ITanimlarController controller){
		tanimlarController=controller;
	}
	public ITanimlarController getCurrentTanimlarController(){
		return tanimlarController;
	}
 
	public String getCurrentMalzemeTanimlariPanelRole() {
		return currentMalzemeTanimlariPanelRole;
	}
	
	public void setCurrentMalzemeTanimlariPanelRole(String currentMalzemeTanimlariPanelRole) {
		this.currentMalzemeTanimlariPanelRole = currentMalzemeTanimlariPanelRole;
	}
	
	public String getCurrentYerlesimPanelRole() {
		return currentYerlesimPanelRole;
	}
	
	public void setCurrentYerlesimPanelRole(String currentYerlesimPanelRole) {
		this.currentYerlesimPanelRole = currentYerlesimPanelRole;
	}
   public String getCurrentVisiblePanel() {
        return currentVisiblePanel;
    }
    
    public void setCurrentVisiblePanel(String currentVisiblePanel) {
        this.currentVisiblePanel = currentVisiblePanel;
    }
    
    public MalzemeTanimlariController getMalzemeTanimlariController() {
        return malzemeTanimlariController;
    }

    public YerlesimController getYerlesimController() {
        return yerlesimController;
    }

    public MalzemeCikisiController getMalzemeCikisiController() {
        return malzemeCikisiController;
    }
    
    public MalzemeDevriController getMalzemeDevriController(){
        return malzemeDevriController;
    }

    public MalzemeGirisiController getMalzemeGirisiController() {
        return malzemeGirisiController;
    }
    
    public MalzemeBozulmasiController getMalzemeBozulmasiController() {
        return malzemeBozulmasiController;
    }
    
    public ZimmetSorgulamaController getZimmetSorgulamaController() {
        return zimmetSorgulamaController;
    }
    
    public MalzemeIzlemeController getMalzemeIzlemeController() {
        return malzemeIzlemeController;
    }
    
    public StokSorguController getStokSorguController() {
        return stokSorguController;
    }
    public BozukMalzemeSorguController getBozukMalzemeSorgulamaController() {
		return bozukMalzemeSorguController;
	}

    public MalzemeSorguController getTerkinMalzemeSorguController() {
		return terkinMalzemeSorguController;
	}

	public MalzemeSorguController getZayiMalzemeSorguController() {
		return zayiMalzemeSorguController;
	}
    public YevmiyeDefteriController getYevmiyeDefteriController(){
        return yevmiyeDefteriController;
    }
    
    public AmbaraDevirSorguController getAmbaraDevirSorguController() {
        return ambaraDevirSorguController;
    }
    public MalzemeZayiatiController getMalzemeZayiatiController() {
        return malzemeZayiatiController;
    }
    public MalzemeTerkinController getMalzemeTerkinController(){
    	return malzemeTerkinController;
    }
    
    
    public void sendSeciliNode(IKategorilendirilebilir seciliNode){
        if(currentVisiblePanel ==MALZEME_GIRISI)
            malzemeGirisiController.sendSeciliNodeToMalzemeGirisi((AbstractMalzemeTanimi)seciliNode);
        else  if(currentVisiblePanel == ZIMMET_SORGULAMA)
            zimmetSorgulamaController.tanimindanSorgula((AbstractMalzemeTanimi)seciliNode);
        else  if(currentVisiblePanel == MALZEME_IZLEME)
            malzemeIzlemeController.tanimindanIzle((AbstractMalzemeTanimi)seciliNode);
        else
          malzemeCikisiController.sendSeciliNodeToMalzemeCikisi((AbstractMalzemeTanimi)seciliNode);
    }
    
   
    
    public void sendDemirbaslar(Vector demirbaslar){
        if(currentVisiblePanel ==MALZEME_GIRISI)
            malzemeGirisiController.sendDemirbaslarToMalzemeGirisi(demirbaslar);
        else if(currentVisiblePanel ==MALZEME_IZLEME)
            malzemeIzlemeController.malzemeBilgileriniGoster((AbstractMalzeme)demirbaslar.get(0));
        else if(currentVisiblePanel ==MALZEME_ZAYI) 
            malzemeZayiatiController.secilenDemirbaslariTabloyaGonder(demirbaslar);
        else if(currentVisiblePanel==MALZEME_TERKIN)
        	malzemeTerkinController.secilenDemirbaslariTabloyaGonder(demirbaslar);
    }
    
    public MalzemeHareketleriAnaPanel getAnaPanel(){
        return malzemeHareketleriAnaPanel;
    }
    
    public IZimmetAlan getIslemYapanAmbar() {
        return islemYapanAmbar;
    }
    
    public void setIslemYapanAmbar(Ambar ambar) {
        islemYapanAmbar = ambar;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }
    
    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }
    
    public boolean showConfirmationDialogBox(Component parentComponent,String message){
    	Object[] options = {"Evet","Hayýr"};
    	if (JOptionPane.showOptionDialog(parentComponent,message,"Onay",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1])==JOptionPane.YES_OPTION)
    		return true;
    	return false;
    }
    
    public void showWarningDialogBox(Component parentComponent,String message){
    	Object[] options = {"Tamam"};
    	JOptionPane.showOptionDialog(parentComponent,message,"Uyarý",JOptionPane.YES_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
    }
    
    public void ambardanGelenleriOneGetirMalzemeGirisiIcin(){
        malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(AnaController.AMBARA_DEVIR_GELENLER);
        getAnaPanel().getAmbaraDevirGelenMalzemeler().loadYourself(null,null);
    }
    
    //REGISTRATIONS
    public void registerMalzemeHareketleriAnaPanel(
            MalzemeHareketleriAnaPanel malzemeHareketleriAnaPanel) {
        this.malzemeHareketleriAnaPanel = malzemeHareketleriAnaPanel;
    }

	
	

	
}
