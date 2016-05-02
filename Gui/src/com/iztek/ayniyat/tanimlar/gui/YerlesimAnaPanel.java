package com.iztek.ayniyat.tanimlar.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.tree.TreePath;

import com.iztek.ayniyat.event.KategoriEventServiceImpl;
import com.iztek.ayniyat.gui.service.Search;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.util.uicomponents.SearchPanel;
import com.iztek.ayniyat.util.uicomponents.YerlesimMenuler;
import com.iztek.ayniyat.util.uiservice.PanelFactory;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;
import com.iztek.util.persistence.DAOFactory;
/**
 * @author Umit Akyol + Cagdas CIRIT
 */
public class YerlesimAnaPanel extends AbstractTanimlarPanel{

	private SearchPanel searchPanel = null;
	private javax.swing.JTextField araTextField = null;
	private Collection result;
	private PersonelGirisPanel personelGirisPanel = null;
	private PersonelGosterisPanel personelGosterisPanel = null;
	private OdaGirisPanel odaGirisPanel = null;
	private OdaGosterisPanel odaGosterisPanel = null;
	private BinaGirisPanel binaGirisPanel = null;
	private BinaGosterisPanel binaGosterisPanel = null;
	private BirimGirisPanel birimGirisPanel = null;
	private BirimGosterisPanel birimGosterisPanel = null;
	private AmbarGirisPanel ambarGirisPanel = null;
	private AmbarGosterisPanel ambarGosterisPanel = null;
	private JPanel bosPanel=null;
	
	public YerlesimAnaPanel(JFrame parent, String panelName) {
		super(parent,"Yerleþim ve Organizasyon",panelName,new YerlesimMenuler());
	}

	public JPanel getRightPanel() {
		if (rightPanel == null) {
			rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.setPreferredSize(new java.awt.Dimension(450,450));
			rightPanel.setBounds(50, 30, 450, 450);
			rightPanel.add(getKategoriPanel(), java.awt.BorderLayout.CENTER);
			rightPanel.add(getDemirbasTablePanel(), java.awt.BorderLayout.SOUTH);
		}
		return rightPanel;
	}
	public JPanel getKategoriPanel() {
		if (kategoriPanel == null) {
			kategoriPanel = new JPanel();
			kategoriPanel.setLayout(getCardLayout());
			kategoriPanel.add(getBosPanel(), "bos");
			kategoriPanel.add(getPersonelGirisPanel(),getPersonelGirisPanel().getPanelName());
			kategoriPanel.add(getPersonelGosterisPanel(),getPersonelGosterisPanel().getPanelName());
			kategoriPanel.add(getOdaGirisPanel(),getOdaGirisPanel().getPanelName());
			kategoriPanel.add(getOdaGosterisPanel(), getOdaGosterisPanel().getPanelName());
			kategoriPanel.add(getBinaGirisPanel(), getBinaGirisPanel().getPanelName());
			kategoriPanel.add(getBinaGosterisPanel(), getBinaGosterisPanel().getPanelName());
			kategoriPanel.add(getBirimGirisPanel(), getBirimGirisPanel().getPanelName());
			kategoriPanel.add(getBirimGosterisPanel(), getBirimGosterisPanel().getPanelName());
			kategoriPanel.add(getAmbarGirisPanel(), getAmbarGirisPanel().getPanelName());
			kategoriPanel.add(getAmbarGosterisPanel(), getAmbarGosterisPanel().getPanelName());
		}
		return kategoriPanel;
	}
	private JPanel getBosPanel() {
		if (bosPanel == null) {
			bosPanel = new JPanel();
			bosPanel.setName("jPanel");
		}
		return bosPanel;
		}

	public CardLayout getCardLayout() {
		if (cardLayout == null) {
			cardLayout = new CardLayout();
		}
		return cardLayout;
	}

	public PersonelGirisPanel getPersonelGirisPanel() {
		if (personelGirisPanel == null) {
			personelGirisPanel = new PersonelGirisPanel(
					PanelFactory.PERSONELGIRISPANEL);
			PanelFactory.getService().registerPersonelGirisPanel(
					personelGirisPanel);
		}
		return personelGirisPanel;
	}

	public AmbarGirisPanel getAmbarGirisPanel() {
		if (ambarGirisPanel == null) {
			ambarGirisPanel = new AmbarGirisPanel(PanelFactory.AMBARGIRISPANEL);
			PanelFactory.getService().registerAmbarGirisPanel(ambarGirisPanel);
		}
		return ambarGirisPanel;
	}
	
	public AmbarGosterisPanel getAmbarGosterisPanel() {
		if (ambarGosterisPanel == null) {
			ambarGosterisPanel = new AmbarGosterisPanel(PanelFactory.AMBARGOSTERISPANEL);
			PanelFactory.getService().registerAmbarGosterisPanel(ambarGosterisPanel);
		}
		return ambarGosterisPanel;
	}

	public BinaGirisPanel getBinaGirisPanel() {
		if (binaGirisPanel == null) {
			binaGirisPanel = new BinaGirisPanel(PanelFactory.BINAGIRISPANEL);
			PanelFactory.getService().registerBinaGirisPanel(binaGirisPanel);
		}
		return binaGirisPanel;
	}
	
	public BinaGosterisPanel getBinaGosterisPanel() {
		if (binaGosterisPanel == null) {
			binaGosterisPanel = new BinaGosterisPanel(PanelFactory.BINAGOSTERISPANEL);
			PanelFactory.getService().registerBinaGosterisPanel(binaGosterisPanel);
		}
		return binaGosterisPanel;
	}

	public BirimGirisPanel getBirimGirisPanel() {
		if (birimGirisPanel == null) {
			birimGirisPanel = new BirimGirisPanel(PanelFactory.BIRIMGIRISPANEL);
			PanelFactory.getService().registerBirimGirisPanel(birimGirisPanel);
		}
		return birimGirisPanel;
	}

	public BirimGosterisPanel getBirimGosterisPanel() {
		if(birimGosterisPanel == null){
			birimGosterisPanel = new BirimGosterisPanel(PanelFactory.BIRIMGOSTERISPANEL);
			PanelFactory.getService().registerBirimGosterisPanel(birimGosterisPanel);
		}
		return birimGosterisPanel;
	}
	
	public OdaGirisPanel getOdaGirisPanel() {
		if (odaGirisPanel == null) {
			odaGirisPanel = new OdaGirisPanel(PanelFactory.ODAGIRISPANEL);
			PanelFactory.getService().registerOdaGirisPanel(odaGirisPanel);
		}
		return odaGirisPanel;
	}

	public OdaGosterisPanel getOdaGosterisPanel() {
		if (odaGosterisPanel == null) {
			odaGosterisPanel = new OdaGosterisPanel(PanelFactory.ODAGOSTERISPANEL);
			PanelFactory.getService().registerOdaGosterisPanel(odaGosterisPanel);
		}
		return odaGosterisPanel;
	}

	public PersonelGosterisPanel getPersonelGosterisPanel() {
		if (personelGosterisPanel == null) {
			personelGosterisPanel = new PersonelGosterisPanel(
					PanelFactory.PERSONELGOSTERISPANEL);
			PanelFactory.getService().registerPersonelGosterisPanel(
					personelGosterisPanel);
		}
		return personelGosterisPanel;
	}

	public void dispose() {
		super.disposed();
		KategoriEventServiceImpl.getTanimlarEventService().removeAllKategoriEventListeners();
		AnaController.getInstance().getCurrentTanimlarController().removeAllRegisteredElements();
		PanelFactory.getService().removeAllRegisteredElements();
	}
   
	private JPanel getDemirbasTablePanel() {
		if (demirbasTablePanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			demirbasTablePanel = new JPanel();
			demirbasTablePanel.setLayout(new GridBagLayout());
			demirbasTablePanel.setPreferredSize(new java.awt.Dimension(1,200));
			demirbasTablePanel.setSize(new java.awt.Dimension(446,250));
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints1.ipadx = 449;
			gridBagConstraints1.ipady = 149;
			demirbasTablePanel.add(getJScrollPane(), gridBagConstraints1);
		}
		return demirbasTablePanel;
	}
  
	private JTable getDemirbasTable() {
		if (demirbasTable == null) {
		    AyniyatTableModel ayniyatTableModel;
		    if(AnaController.getInstance().getCurrentYerlesimPanelRole().equals(AnaController.YERLESIM_FOR_DIALOG_DEMIRBAS_SEC)){
		        String [] columnName = {"Seç","Demirbaþ Numarasý","Tanýmý","Oda Numarasý"};
				ayniyatTableModel = new AyniyatTableModel(columnName){
				    public boolean isCellEditable(int row, int col) {
				        if (col < 1) 
				            return true;
				        else 
				            return false;
				    }
				};	    
		    }else{
		        String [] columnName = {"Demirbaþ Numarasý","Tanýmý","Oda Numarasý"};
				ayniyatTableModel = new AyniyatTableModel(columnName);	
		    } 
	    	demirbasTable = new JTable(ayniyatTableModel);
		}
		return demirbasTable;
	}
   
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getDemirbasTable());
			jScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Zimmetli Demirbaþlar", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
			jScrollPane.setSize(new java.awt.Dimension(550,250));		
		}
		return jScrollPane;
	}
	
	public void loadDemirbasTable(Vector demirbaslar){
    	((AyniyatTableModel)getDemirbasTable().getModel()).addRows2LastRow(demirbaslar);
	}
	
	
	
	public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
		unLoadYourself();
		
	}

	public void unLoadYourself() {
		araTextField.setText("");
		getTreePanel().loadYourself();
		((AyniyatTableModel)getDemirbasTable().getModel()).removeAllRows();
		AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
		searchPanel.switchButtons(true);
	}
	
	//arama sonunda resultSet'in ilk nodunu guide gösterir
	public void showResultOfSearch(){
	    IKategorilendirilebilir seciliNode=null;
	    
	    TreePath path=Search.findNext(result);
		getTreePanel().getJTree().scrollPathToVisible(path);
		getTreePanel().getJTree().setSelectionPath(path);
		seciliNode=(IKategorilendirilebilir)path.getLastPathComponent();
		IAyniyatPanel panel = PanelFactory.getService().getGosterisPanelByNode(seciliNode);
    	panel.loadYourself(seciliNode,null);
    	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir(panel.getPanelName());
    
	}    
    public Vector getSeciliRowsForDemirbaslar(){
        Vector rows = new Vector();            
        for(int i=0;i<getDemirbasTable().getModel().getRowCount();i++){
            if(((Boolean)getDemirbasTable().getModel().getValueAt(i,0)).booleanValue())
                rows.add(getDemirbasTable().getModel().getValueAt(i,1));                    
        }            
        return rows;
    }
    
    public void demirbasTablonuTemizle(){
    	((AyniyatTableModel)getDemirbasTable().getModel()).removeAllRows();
    }
    public void araAction(){
    	
    	result=YerlesimManager.findKategoriByMatchingTanimIgnoreCase(getSearchPanel().getAraTextField().getText(), DAOFactory.YERLESIM);
		if(result!=null){
			showResultOfSearch();
			if (result.size() != 0) {
				getSearchPanel().switchButtons(false);
			}
		}
    }
    public void sonrakiAction(){
    	showResultOfSearch();
		if (result.size() == 0) {
			getSearchPanel().switchButtons(true);
		}
    	
    }
    
}

