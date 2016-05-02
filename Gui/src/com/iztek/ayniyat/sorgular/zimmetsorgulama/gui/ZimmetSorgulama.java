/* Created on 12.Haz.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.sorgular.zimmetsorgulama.gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.jdesktop.jdnc.JNTable;
import org.jdesktop.swing.decorator.Filter;
import org.jdesktop.swing.decorator.FilterPipeline;
import org.jdesktop.swing.decorator.PatternFilter;
import org.jdesktop.swing.decorator.ShuttleSorter;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.fis.PrintZimmetSorgulama;
import com.iztek.ayniyat.malzemehareketleri.malzemebozuk.gui.BozukMalzemeAramaSecimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.model.FilterComboModel;
import com.iztek.ayniyat.model.ZimmetSorguFilter;
import com.iztek.ayniyat.model.ZimmetSorguTableModel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.panel.ISorgularPanel;
import com.iztek.ayniyat.sorgular.malzemeizleme.gui.MalzemeIzleme;
import com.iztek.ayniyat.util.uicomponents.SorguButtonPanel;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZimmetSorgulama extends JFrame implements IAyniyatPanel,ISorgularPanel{

	private javax.swing.JPanel jContentPane = null;
	private JNTable sorguTable = null; 
	private String panelName=null;
	private IZimmetAlan temporary = null;
	private javax.swing.JPopupMenu jPopupMenu = null;  
	private javax.swing.JMenuItem secMenuItem = null;
	private JPanel labelPanel = null;
	private JLabel toplamLabel = null;
	private JLabel tLabel = null;
	private JLabel birimLabel = null;
	private SorguButtonPanel buttonPanel=null;
	private JComboBox criteriaCmbBox = null;
	private FilterPipeline filters;
	public ZimmetSorguTableModel model=null;
	public ZimmetSorguFilter filter;
	public  JLabel criteriaLabel = null;
	public  JLabel criteria1Label = null;
	private JComboBox Criteria1CmbBox = null;
	/**
	 * This is the default constructor
	 */
	public ZimmetSorgulama(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
	    AnaController.getInstance().getZimmetSorgulamaController().registerZimmetSorgulama(this);
	    this.setSize(800, 565);
		this.setContentPane(getJContentPane());
		this.setTitle("Zimmet Sorgulama");
		this.setResizable(false);	
	
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jContentPane.setPreferredSize(new java.awt.Dimension(462,493));
			jContentPane.add(getCriteriaLabel(),null);
			jContentPane.add(getSorguTable(), null);
			jContentPane.add(getLabelPanel(), null);
			jContentPane.add(getButtonPanel(), null);
			jContentPane.add(getCriteriaCmbBox(), null);
			jContentPane.add(getCriteria1CmbBox(), null);
			jContentPane.add(getCriteria1Label(), null);
			jContentPane.add(getCriteriaLabel(), null);
		}
		return jContentPane;
	}	
	public JLabel getCriteria1Label(){
		if(criteria1Label==null){
			criteria1Label=new JLabel();
			criteria1Label.setBounds(new Rectangle(320, 14, 106, 21));
			criteria1Label.setText("ZimmetSahibi");
		}
		return criteria1Label;
	}
	public JLabel getCriteriaLabel(){
		if(criteriaLabel==null){
			criteriaLabel=new JLabel();
			criteriaLabel.setBounds(new Rectangle(13, 14, 106, 21));
			criteriaLabel.setText("Cinsi");
		}
		return criteriaLabel;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */  
	private SorguButtonPanel getButtonPanel(){
		if(buttonPanel==null){ 
			buttonPanel=new SorguButtonPanel();
			buttonPanel.setBounds(new java.awt.Rectangle(0,500,794,35));
			buttonPanel.registerPanel(this);
			
		}
		return buttonPanel;
	}
	/**
	 * @author fusun
	 *  */
	
	public JNTable getSorguTable() {
		if (sorguTable == null) {
			AyniyatTableModel model = new AyniyatTableModel(new String[]{"Demirbaþ Numarasý","Cinsi","Ölçek","Zimmet Sahibi"});
			sorguTable = new JNTable(model);
			sorguTable.setHasColumnControl(true);
			sorguTable.setHeaderBackground(new java.awt.Color(204, 255, 255));
			sorguTable.setBounds(new java.awt.Rectangle(6,54,788,416));
			 sorguTable.setHeaderFont(new java.awt.Font("Dialog",
			 java.awt.Font.BOLD, 12));
			 sorguTable.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED),
			 "Zimmet Sorgulama", javax.swing.border.TitledBorder.LEFT,
			 javax.swing.border.TitledBorder.DEFAULT_POSITION, new
			 java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new
			 java.awt.Color(113,7,113)));
			sorguTable.setPopupMenu(getJPopupMenu());
		}
		return sorguTable;
	}

	private JPopupMenu getJPopupMenu() {
		if (jPopupMenu == null) {
			jPopupMenu = new JPopupMenu(); 
			jPopupMenu.add(getSecMenuItem());				
		}
			
		return jPopupMenu;
	}
	/**
	 * @author fusun
	 *  */	    
	private JMenuItem getSecMenuItem() {
		if (secMenuItem == null) {
			secMenuItem = new JMenuItem();
			secMenuItem.setText("Malzemeyi Ýzle");
			secMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getSorguTable().getTable().getSelectedRow();
					DemirbasMalzeme demirbas=(DemirbasMalzeme)((AyniyatTableModel)getSorguTable().getModel()).getValueAt(row,0);
					showMalzemeIzlemeDialog(demirbas);
				}
			});
		}
		return secMenuItem;
	}
	public void showMalzemeIzlemeDialog(DemirbasMalzeme demirbas){
		
		MalzemeIzleme dialog=new MalzemeIzleme(this,AnaController.MALZEME_IZLEME);
		AnaController.getInstance().getMalzemeIzlemeController().registerMalzemeIzleme(dialog);
		AnaController.getInstance().getMalzemeIzlemeController()
		.malzemeBilgileriniGoster(demirbas);
		dialog.getButtonPanel().setVisible(false);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		
	}
	private boolean isRowSelected(){
		if(sorguTable.getTable().getSelectedRow()==-1)
			return false;	
		else
			return true;		
	}
	public IZimmetAlan getTemporary() {
		return temporary;
	}
	
	public void setTemporary(IZimmetAlan temporary) {
		this.temporary = temporary;
	}
   
    public String getPanelName() {
        return panelName;
    }

    public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
    	 
    }
    
    public void unLoadYourself() {
    	((AyniyatTableModel)getSorguTable().getModel()).removeAllRows();
    	((FilterComboModel)getCriteriaCmbBox().getModel()).removeAll();
    	((FilterComboModel)getCriteria1CmbBox().getModel()).removeAll();
      }
       
	public void inceleAction(){
	    unLoadYourself();
	    AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir(getPanelName());				
	    new BozukMalzemeAramaSecimi(AnaController.getInstance().getAnaPanel()).setVisible(true);
	}
	/**
	 * This method initializes labelPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLabelPanel() {
		if (labelPanel== null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new java.awt.Insets(0,4,0,47);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.ipadx = 66;
			gridBagConstraints2.ipady = 24;
			gridBagConstraints2.gridheight = 1;
			gridBagConstraints2.gridx = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new java.awt.Insets(5,1,11,4);
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 57;
			gridBagConstraints1.ipady = 8;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new java.awt.Insets(5,555,9,0);
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 4;
			gridBagConstraints.ipady = 10;
			gridBagConstraints.gridx = 0;
			toplamLabel = new JLabel();
			toplamLabel.setText("Toplam :");
			toplamLabel.setPreferredSize(new java.awt.Dimension(50,16));
			labelPanel = new JPanel();
			labelPanel.setLayout(new GridBagLayout());
			labelPanel.setPreferredSize(new java.awt.Dimension(0,40));
			labelPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray,1));
			labelPanel.setBounds(new java.awt.Rectangle(2,468,794,31));
			labelPanel.add(toplamLabel, gridBagConstraints);
			labelPanel.add(gettLabel(), gridBagConstraints1);
			labelPanel.add(getbirimLabel(), gridBagConstraints2);
		}
		return labelPanel;
	}
	
	public JLabel gettLabel(){
		if(tLabel==null){
			tLabel=new JLabel();
			tLabel.setText(" ");
	}
		return tLabel;
	}
	
	public JLabel getbirimLabel(){
		
		if(birimLabel==null){
			birimLabel=new JLabel();
			birimLabel.setText(" ");
	}
		return birimLabel;
	}
	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCriteriaCmbBox() {
		if (criteriaCmbBox == null) {
			FilterComboModel model=new FilterComboModel();
			criteriaCmbBox = new JComboBox(model);
			criteriaCmbBox.setBounds(new java.awt.Rectangle(119,14,134,22));
			criteriaCmbBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					createFilter();
				}		
			});
		}	
		return criteriaCmbBox;
	}
	
	public void printAction(){
		try{
		PrintZimmetSorgulama.prepareReport(((AyniyatTableModel)getSorguTable().getModel()).getTableData(),"zimmetSorgulama",AnaController.getInstance().getZimmetSorgulamaController().zSahibi);
		}catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	private void createFilter(){
		PatternFilter pattern1=new PatternFilter(getCriteriaCmbBox().getSelectedItem().toString(), 0, 1);
		PatternFilter pattern2=new PatternFilter(getCriteria1CmbBox().getSelectedItem().toString(), 0, 3);
		if(getCriteria1CmbBox().getSelectedItem().equals("All")&&!(getCriteriaCmbBox().getSelectedItem().equals("All"))){
			filters = new FilterPipeline(
	    			new Filter[] {pattern1,new ShuttleSorter(1, true)
	    			});
		}
		else if (getCriteriaCmbBox().getSelectedItem().equals("All")&&!(getCriteria1CmbBox().getSelectedItem().equals("All"))){
			filters = new FilterPipeline(
	    			new Filter[] {pattern2,new ShuttleSorter(1, true)
	    			});
		}
		else if((getCriteria1CmbBox().getSelectedItem().equals("All"))&&(getCriteriaCmbBox().getSelectedItem().equals("All"))){
			filters=new FilterPipeline(
				new Filter[] {
				new PatternFilter(".*",0,0)
			}); 
		}
		else{
			filters = new FilterPipeline(
	    			new Filter[] {pattern1,pattern2,new ShuttleSorter(1, true)
	    			});
		}
		getSorguTable().setFilters(filters);
		gettLabel().setText(""+getSorguTable().getTable().getRowCount());
	}
	/**
	 * This method initializes Criteria1CmbBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCriteria1CmbBox() {
		if (Criteria1CmbBox == null) {
			FilterComboModel model1=new FilterComboModel();
			Criteria1CmbBox = new JComboBox(model1);
			Criteria1CmbBox.setBounds(new Rectangle(429, 14, 136, 24));
			Criteria1CmbBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					createFilter();
					}
			});
		}	
		return Criteria1CmbBox;
	}
 }  //  @jve:decl-index=0:visual-constraint="10,10"
