package com.iztek.ayniyat.tanimlar.gui;

import java.awt.CardLayout;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.TreePath;

import com.iztek.ayniyat.event.KategoriEventServiceImpl;
import com.iztek.ayniyat.gui.service.Search;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.util.uicomponents.MalzemeTanimlariMenuler;
import com.iztek.ayniyat.util.uiservice.PanelFactory;
import com.iztek.util.persistence.DAOFactory;

public class MalzemeTanimlariAnaPanel extends AbstractTanimlarPanel {

	
	private MalzemeGirisPanel malzemeGirisPanel = null;

	private MalzemeGosterisPanel malzemeGosterisPanel = null;

	private KategoriGosterisPanel kategoriGosterisPanel = null;
	private JPanel bosPanel=null;
	private com.iztek.ayniyat.tanimlar.gui.KategoriGirisPanel kategoriGirisPanel=null;

	private Collection result;

	public MalzemeTanimlariAnaPanel(JFrame parent, String panelName) {
		super(parent, "Malzeme Tanýmlarý",panelName,new MalzemeTanimlariMenuler());
		AnaController.getInstance().getCurrentTanimlarController()
				.registerAnaPanel(this);
	}

	public JPanel getRightPanel() {
		if (rightPanel == null) {
			rightPanel = new JPanel();
			rightPanel.setLayout(getCardLayout());
			rightPanel.setSize(new java.awt.Dimension(450, 350));
			// Card layouts
			rightPanel.add(getBosPanel(), "bos");
			rightPanel.add(getKategoriPanel(), getKategoriPanel()
					.getPanelName());
			rightPanel.add(getMalzemeGirisPanel(), getMalzemeGirisPanel()
					.getPanelName());
			rightPanel.add(getMalzemeGosterisPanel(), getMalzemeGosterisPanel()
					.getPanelName());
			rightPanel.add(getKategoriGosterisPanel(),
					getKategoriGosterisPanel().getPanelName());
		}
		return rightPanel;
	}
	
	public CardLayout getCardLayout() {
		if (cardLayout == null) {
			cardLayout = new CardLayout();
		}
		return cardLayout;
	}

	private MalzemeGirisPanel getMalzemeGirisPanel() {
		if (malzemeGirisPanel == null) {
			malzemeGirisPanel = new MalzemeGirisPanel("malzemeGirisPanel");
			PanelFactory.getService().registerMalzemeGirisPanel(malzemeGirisPanel);
		}
		return malzemeGirisPanel;
	}
	
	private JPanel getBosPanel() {
		if (bosPanel == null) {
			bosPanel = new JPanel();
			bosPanel.setName("jPanel");
		}
		return bosPanel;
	}
	 
	private KategoriGirisPanel getKategoriPanel() {
		if (kategoriGirisPanel == null) {
			kategoriGirisPanel = new KategoriGirisPanel("kategoriGirisPanel");
			PanelFactory.getService().registerKategoriGirisPanel(
					kategoriGirisPanel);
		}
		return kategoriGirisPanel;
	}

	private MalzemeGosterisPanel getMalzemeGosterisPanel() {
		if (malzemeGosterisPanel == null) {
			malzemeGosterisPanel = new MalzemeGosterisPanel("malzemeGosterisPanel");
			PanelFactory.getService().registerMalzemeGosterisPanel(malzemeGosterisPanel);
		}
		return malzemeGosterisPanel;
	}

	public KategoriGosterisPanel getKategoriGosterisPanel() {
		if (kategoriGosterisPanel == null) {
			kategoriGosterisPanel = new KategoriGosterisPanel("kategoriGosterisPanel");
			PanelFactory.getService().registerKategoriGosterisPanel(kategoriGosterisPanel);
		}
		return kategoriGosterisPanel;
	}

	public void dispose() {
		super.disposed();
		KategoriEventServiceImpl.getTanimlarEventService()
				.removeAllKategoriEventListeners();
		AnaController.getInstance().getCurrentTanimlarController()
				.removeAllRegisteredElements();
		PanelFactory.getService().removeAllRegisteredElements();
	}

	public void loadYourself(IKategorilendirilebilir node,
			IKategorilendirilebilir parentNode) {

		unLoadYourself();
		//do something to load
		
	}

	public void unLoadYourself() {
		super.getSearchPanel().getAraTextField().setText("");
		getTreePanel().loadYourself();
		AnaController.getInstance().getCurrentTanimlarController()
				.panelOneGetir("bos");
		getSearchPanel().switchButtons(true);
	}

	//arama sonunda resultSet'in ilk nodunu guide gösterir
	public void showResultOfSearch() {
		IKategorilendirilebilir seciliNode = null;

		TreePath path = Search.findNext(result);
		super.getTreePanel().getJTree().scrollPathToVisible(path);
		getTreePanel().getJTree().setSelectionPath(path);
		seciliNode = (IKategorilendirilebilir) path.getLastPathComponent();
		IAyniyatPanel panel = PanelFactory.getService().getGosterisPanelByNode(
				seciliNode);
		panel.loadYourself(seciliNode, null);
		AnaController.getInstance().getCurrentTanimlarController()
				.panelOneGetir(panel.getPanelName());
	}

	public void araAction() {
		result = MalzemeTanimlariManager.findKategoriByMatchingTanimIgnoreCase(
				getSearchPanel().getAraTextField().getText(),
				DAOFactory.MALZEME_TANIMI);
		showResultOfSearch();
		if (result.size() != 0) {
			getSearchPanel().switchButtons(false);
		}
	}

	public void sonrakiAction() {
		showResultOfSearch();
		if (result.size() == 0) {
			getSearchPanel().switchButtons(true);
		}
	}
}
