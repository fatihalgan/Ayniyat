package com.iztek.ayniyat.tanimlar.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.util.Collection;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.panel.ITanimlarPanel;
import com.iztek.ayniyat.util.uicomponents.PopUpMenuler;
import com.iztek.ayniyat.util.uicomponents.SearchPanel;
import com.iztek.ayniyat.util.uicomponents.TreePanel;


public abstract class AbstractTanimlarPanel extends JDialog  implements IAyniyatPanel,ITanimlarPanel{

	/**
	 * @author fusun
	 */
	private javax.swing.JPanel jContentPane = null;
	private javax.swing.JPanel leftPanel = null;
	private SearchPanel searchPanel = null;
	private TreePanel treePanel = null;
	private Collection result;
	private String panelName;
	private JPanel rightImagePanel = null;
	public JPanel rightPanel = null;
	public CardLayout cardLayout = null;
	public JPanel kategoriPanel = null;
	public JPanel demirbasTablePanel = null;
	public JTable demirbasTable = null;
	public JScrollPane jScrollPane = null;
	public PopUpMenuler pop = null;
	
	public AbstractTanimlarPanel(JFrame parent, String str, String pName,
			PopUpMenuler menu) {
		super(parent, str, true);
		this.panelName = pName;
		this.pop = menu;
		initialize();
	}

	public void initialize() {
		this.setBounds(0, 0, 800, 550);
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		AnaController.getInstance().getCurrentTanimlarController().registerAnaPanel(this);
	}

	public com.iztek.ayniyat.util.uicomponents.TreePanel getTreePanel() {
		if (treePanel == null) {
			treePanel = new TreePanel(pop);
			AnaController.getInstance().getCurrentTanimlarController().registerTreePanel(treePanel);
			treePanel.loadYourself();
		}
		return treePanel;
	}

	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getLeftPanel(), java.awt.BorderLayout.WEST);
			jContentPane.add(getRightImagePanel(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

	public JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			leftPanel.setPreferredSize(new java.awt.Dimension(250, 1));
			leftPanel.add(getSearchPanel(), java.awt.BorderLayout.NORTH);
			leftPanel.add(getTreePanel(), BorderLayout.CENTER);
		}
		return leftPanel;
	}

	public SearchPanel getSearchPanel() {
		if (searchPanel == null) {
			searchPanel = new SearchPanel();
			searchPanel.registerActivePanel((ITanimlarPanel)this);
		}
		return searchPanel;
	}
	
   private JPanel getRightImagePanel() {
        if (rightImagePanel == null) {
            rightImagePanel=new JPanel();
            rightImagePanel.setLayout(new GridBagLayout());
            rightImagePanel.add(getRightPanel(), null);
        }
        return rightImagePanel;
    }
    
   public abstract JPanel getRightPanel() ;
  
   public abstract void dispose() ;
	
 
   public String getPanelName() {
		return panelName;
	}
   public void disposed(){
	   super.dispose();
	}
	
   public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
	   unLoadYourself();
		//do something to load
	}

   public abstract void  unLoadYourself();
	
	
	//arama sonunda resultSet'in ilk nodunu guide gösterir
	
   public abstract void showResultOfSearch();
	
   public abstract void araAction();
    
   public void sonrakiAction(){
	   showResultOfSearch();
		if (result.size() == 0) {
			searchPanel.switchButtons(true);
		}
    }
	   
}

