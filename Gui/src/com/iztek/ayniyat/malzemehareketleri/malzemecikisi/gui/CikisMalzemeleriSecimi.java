package com.iztek.ayniyat.malzemehareketleri.malzemecikisi.gui;

import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreePath;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemePahasi;
import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.model.treetable.DynamicTreeTableModel;
import com.iztek.ayniyat.model.treetable.JTreeTable;
import com.iztek.ayniyat.model.treetable.TreeTableModel;
import com.iztek.ayniyat.model.treetable.TreeTableNode;
import com.iztek.ayniyat.util.uicomponents.NiteliklerTablePanel;
import com.iztek.commons.money.Money;

/**
 * @author Cagdas CIRIT
 **/
public class CikisMalzemeleriSecimi extends JDialog {

	private javax.swing.JPanel jContentPane = null;
	private JPanel secimPanel = null;
	private JPanel buttonPanel = null;
	private JButton kapatButton = null;
	private JButton tamamButton = null;
	private JLabel miktarLabel = null;
	private JTextField miktarTextField = null;
	private JLabel tanimLabel = null;
	private JLabel birimLabel = null;
	private JTextField tanimTextField = null;
	private JTextField birimTextField = null;
	private JButton suzmeButton = null;
	private JTreeTable demirbaslarTable = null;
	private JScrollPane jScrollPane1 = null;
	private DemirbasMalzemeTanimi demirbasMalzemeTanimi;
	private DynamicTreeTableModel demirbasTableModel;
	private String[] demirbasTablecolumnNames = { "Demirbaþ Numarasý", "Vergisiz Birim Fiyatý","Vergi Oraný(%)", "Seç" };
	
	private final static Class[] demirbasTableClassTypes = { TreeTableModel.class, String.class,String.class, Boolean.class };
	private static String[] demirbasSorguTablecolumnNames = { "Demirbaþ Numarasý", "Vergisiz Birim Fiyatý","Vergi Oraný(%)"};
	static Class[] demirbasSorguTableClassTypes = { TreeTableModel.class, String.class,String.class};
	private NiteliklerTablePanel niteliklerTable=null;
	
	
	public CikisMalzemeleriSecimi(JFrame parent) {
		super(parent,true);
		initialize();
	}

	private void initialize() {
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Demirbaþ Malzeme Seçimi");
		this.setSize(460, 582);
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
	}

	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getSecimPanel(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}
 
	private JPanel getSecimPanel() {
		if (secimPanel == null) {
			birimLabel = new JLabel();
			tanimLabel = new JLabel();
			secimPanel = new JPanel();
			secimPanel.setLayout(null);
			tanimLabel.setText("Taným :");
			tanimLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			birimLabel.setText("  Birim :");
			birimLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			birimLabel.setSize(54, 24);
			birimLabel.setLocation(268, 19);
			tanimLabel.setLocation(17, 19);
			tanimLabel.setSize(54, 24);
			secimPanel.add(tanimLabel, null);
			secimPanel.add(birimLabel, null);
			secimPanel.add(getTanimTextField(), null);
			secimPanel.add(getBirimTextField(), null);
			secimPanel.add(getNiteliklerTable(),null);
			secimPanel.add(getSuzmeButton(), null);
			secimPanel.add(getJScrollPane1(), null);
		}
		return secimPanel;
	}
  
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			miktarLabel = new JLabel();
			buttonPanel = new JPanel();
			buttonPanel.setLayout(null);
			miktarLabel.setText("Miktar :");
			miktarLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
			miktarLabel.setPreferredSize(new java.awt.Dimension(100,18));
			miktarLabel.setLocation(174, 5);
			miktarLabel.setSize(50, 24);
			buttonPanel.setPreferredSize(new java.awt.Dimension(1,35));
			buttonPanel.add(getKapatButton(), null);
			buttonPanel.add(getTamamButton(), null);
			buttonPanel.add(miktarLabel, null);
			buttonPanel.add(getMiktarTextField(), null);
		}
		return buttonPanel;
	}
   
	private JButton getKapatButton() {
		if (kapatButton == null) {
			kapatButton = new JButton();
			kapatButton.setToolTipText("Kapat");
			kapatButton.setBounds(75, 5, 24, 24);
			kapatButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/error.png")));
			kapatButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    dispose();
				}
			});
			
		}
		return kapatButton;
	}
  
	private JButton getTamamButton() {
		if (tamamButton == null) {
			tamamButton = new JButton();
			tamamButton.setBounds(349, 5, 24, 24);
			tamamButtonuDuzenle();
		}
		return tamamButton;
	}
	
	private void tamamButtonuDuzenle(){
		if(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.STOK_SORGULAMA)||
				AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.ZAYIMALZEME_SORGULAMA)||
				AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.TERKINMALZEME_SORGULAMA)||
				AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.BOZUKMALZEME_SORGULAMA)){
		    tamamButton.setToolTipText("Baþka Bir Sorgu");
		    tamamButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/view_next.png")));
			tamamButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					dispose();
				}
			});
		}else{
		    tamamButton.setToolTipText("Tamam");
		    tamamButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/check.png")));
		    tamamButton.setEnabled(false);
			tamamButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					AnaController.getInstance().getMalzemeCikisiController().secilenDemirbaslariMalzemeCikisiTablosunaGonder((TreeTableNode) demirbasTableModel.getRoot());
					dispose();
				}
			});
		}
	}
   
	private JTextField getMiktarTextField() {
		if (miktarTextField == null) {
			miktarTextField = new JTextField();
			miktarTextField.setText("0"); 
			miktarTextField.setEditable(false);
			miktarTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			miktarTextField.setFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 13));
			miktarTextField.setLocation(224, 7);
			miktarTextField.setSize(50, 20);
		}
		return miktarTextField;
	}
  
	private JTextField getTanimTextField() {
		if (tanimTextField == null) {
			tanimTextField = new JTextField();
			tanimTextField.setEditable(false);
			tanimTextField.setLocation(71, 21);
			tanimTextField.setSize(188, 20);
			tanimTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
		}
		return tanimTextField;
	}
  
	private JTextField getBirimTextField() {
		if (birimTextField == null) {
			birimTextField = new JTextField();
			birimTextField.setEditable(false);
			birimTextField.setLocation(322, 21);
			birimTextField.setSize(100, 20);
			birimTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 13));
		}
		return birimTextField;
	}
  
	private NiteliklerTablePanel getNiteliklerTable(){
		if(niteliklerTable==null){
			niteliklerTable=new NiteliklerTablePanel();
		}
		return niteliklerTable;
	}
 
	private JButton getSuzmeButton() {
		if (suzmeButton == null) {
			suzmeButton = new JButton();
			suzmeButton.setLocation(218, 227);
			suzmeButton.setSize(24, 24);
			suzmeButton.setToolTipText("Niteliklere Göre Süz");
			suzmeButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/funnel_down.png")));
			suzmeButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					removeAllNodesAndCleanDemirbasTable();
				    demirbasTablosunaVeriEkle(AnaController.getInstance().getMalzemeCikisiController().getNitelikSuzgecindenGecmisDemirbaslar(getDemirbasMalzemeTanimi(),(AyniyatTableModel) getNiteliklerTable().getNiteliklerTable().getModel()));
				}
			});
		}
		return suzmeButton;
	}
  
	private JTreeTable getDemirbaslarTable() {
		if (demirbaslarTable == null) {
			demirbaslarTable = createDemirbasTable();			
		}
		return demirbaslarTable;
	}
  
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getDemirbaslarTable());
			jScrollPane1.setSize(405, 249);
			jScrollPane1.setLocation(21, 250);
			jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Demirbaþlar", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
			jScrollPane1.getViewport().setBackground(Color.white);
		}
		return jScrollPane1;
	}
	
    public void setDemirbaslarTable(JTreeTable demirbaslarTable) {
        this.demirbaslarTable = demirbaslarTable;
    }
    
    public DemirbasMalzemeTanimi getDemirbasMalzemeTanimi() {
        return demirbasMalzemeTanimi;
    }
    
    public void setDemirbasMalzemeTanimi(
            DemirbasMalzemeTanimi demirbasMalzemeTanimi) {
        this.demirbasMalzemeTanimi = demirbasMalzemeTanimi;
    }
    
	public void loadYourself(DemirbasMalzemeTanimi demirbasTanimi){
	    setDemirbasMalzemeTanimi(demirbasTanimi);
	    getTanimTextField().setText(demirbasTanimi.getTanim());
	    getBirimTextField().setText(demirbasTanimi.getBirim());
	    getNiteliklerTable().loadNiteliklerTable(AnaController.getInstance().getMalzemeCikisiController().getNiteliklerForMalzemeSecimleriTable(demirbasTanimi),demirbasTanimi.getNitelikTanimlari().iterator());
	}
	
	public void increaseMiktar(){
	    int current = Integer.parseInt(getMiktarTextField().getText());
	    current++;
	    getMiktarTextField().setText(new Integer(current).toString());
	    if(current>0)
	        getTamamButton().setEnabled(true);
	}
	
	public void decreaseMiktar(){
	    int current = Integer.parseInt(getMiktarTextField().getText());
	    current--;
	    getMiktarTextField().setText(new Integer(current).toString());
	    if(current<1)
	        getTamamButton().setEnabled(false);
	}
	
	private void demirbasTablosunaVeriEkle(Vector demirbaslar){
	    TreeTableNode root = (TreeTableNode) demirbasTableModel.getRoot();
	    TreeTableNode demirbasNode = null;

	    Iterator iter = demirbaslar.iterator();
	    while (iter.hasNext()) {
            DemirbasMalzeme demirbas = (DemirbasMalzeme) iter.next();
            demirbasNode = new TreeTableNode(demirbas);
            demirbasaNitelikTreeTableNodlariniEkle(demirbasNode);
            root.add(demirbasNode);
            demirbasTableModel.fireTreeNodesInserted(this,demirbasTableModel.getPathToRoot(demirbasNode),new int[] {demirbasTableModel.getIndexOfChild(root,demirbasNode)},new Object[] { demirbasNode });          
	    }
	    //scroll2Last Added TreeTableNode
	    if (demirbasNode!=null)
	        getDemirbaslarTable().getTree().scrollPathToVisible(new TreePath(demirbasTableModel.getPathToRoot(demirbasNode)));
	    //Sorgu ekranýnda açýldýysa miktarý ekrana yansýz
	   
	    if ((AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.STOK_SORGULAMA)))
	        getMiktarTextField().setText(String.valueOf(demirbaslar.size()));
	    
	   
	}
	
	public void removeAllNodesAndCleanDemirbasTable(){
	    TreeTableNode root =  (TreeTableNode) demirbasTableModel.getRoot();
	    if (root.getChildren()!=null){
	        Iterator iter = root.getChildren().iterator();
	    
	        while (iter.hasNext()) {
	            TreeTableNode element = (TreeTableNode) iter.next();
	            int index = demirbasTableModel.getIndexOfChild(root,element);
	            iter.remove();
	            demirbasTableModel.fireTreeNodesRemoved(this,element.getPath(),new int[] {index},new Object[] { element });	            	
	        }
	        demirbasTableModel.fireTreeStructureChanged(this, ((TreeTableNode) demirbasTableModel.getRoot()).getPath(), null, null);
	    }
	    getMiktarTextField().setText(String.valueOf(0));
	    root.setSelected(false);
	}
	
	private void demirbasaNitelikTreeTableNodlariniEkle(TreeTableNode demirbas){
	    Iterator iter = ((AbstractMalzeme) demirbas.getUserObject()).getNitelikDegerleri().iterator();
	    while (iter.hasNext()) {
            NitelikDegeri nitelik = (NitelikDegeri) iter.next();
            TreeTableNode nitelikNode = new TreeTableNode(nitelik);
            demirbas.add(nitelikNode);
        }
	}
	
	public JTreeTable createDemirbasTable() {
	    TreeTableNode root = new TreeTableNode();
	    
	    if ((AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.STOK_SORGULAMA))
	    		||(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.BOZUKMALZEME_SORGULAMA))
	    		||(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.ZAYIMALZEME_SORGULAMA))
	    		||(AnaController.getInstance().getCurrentVisiblePanel().equals(AnaController.TERKINMALZEME_SORGULAMA)))
	        demirbasTableModel = new DemirbasSorguTableModel(root);
	    else
	        demirbasTableModel = new DemirbasTableModel(root);
	    
	    return new JTreeTable(demirbasTableModel);	
	}	

	public class NitelikTableModel extends AyniyatTableModel{

        public NitelikTableModel(String[] columnNames) {
            super(columnNames);
        }
        
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex>0)
                return true;
        	return false;
        }    
	}
	
    public class NitelikComboBoxRenderer extends JComboBox implements TableCellRenderer {
        public NitelikComboBoxRenderer(Vector items) {
            super(items);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }

            // Select the current value
            setSelectedItem(value);
            return this;
        }
    }
    
    public class DemirbasTableModel extends DynamicTreeTableModel {
    	
    	public DemirbasTableModel(TreeTableNode root) {
    		super(root,demirbasTablecolumnNames,null,null,demirbasTableClassTypes);
    	}
    	
    	public Object getValueAt(Object node, int column) {
    		if(node == null) return null;
    		
    		TreeTableNode tn = (TreeTableNode) node;
    		
    		if(column==3)
    		    return new Boolean(tn.isSelected());
    		else if(tn.isLeaf()){
    			NitelikDegeri nitelik = (NitelikDegeri) tn.getUserObject();
    			if(nitelik != null){
    			    switch (column) {
    			    case 0:
    			        return nitelik.getNitelikAdi();
    			    case 1:
    			        return nitelik.getNitelikDegeri();
    			    case 2:
    			        return "";
    			    default:
    			        break;
    			   }
    			}
    		}else{
    			DemirbasMalzeme demirbas = (DemirbasMalzeme)tn.getUserObject();
  
    			if(demirbas != null) {
    				//TODO bu paha islemi kaldirilcak, pahasý set edilmeyen datalar icin kondu
    	  			MalzemePahasi paha = demirbas.getPaha();
        			if (paha==null){
        				paha = new MalzemePahasi(); 
        				paha.setVergisizBirimFiyat(Money.yeniTurkLirasi(0));
        				paha.setKdvOrani(new Float(0));
        				demirbas.setPaha(paha);
        			}
    				NumberFormat numberFormat = NumberFormat.getInstance();
    				switch (column) {
    				case 0:
    					return demirbas.getDemirbasNo().toString();
    				case 1:
    					return numberFormat.format(paha.getVergisizBirimFiyatMiktar())+" "+paha.getVergisizBirimFiyatKur();
    				case 2:
    					return new Float(paha.getAsilOran()*100).toString();
    				default:
    					break;
    				}
    			}
    		}
    		return null;
    	}
    	
    	public boolean isCellEditable(Object node, int column) {
    		if(column>2){
    			TreeTableNode tn = (TreeTableNode) node;		
    			if(!tn.isLeaf())
    				return true;
    		}
    		else if(column==0)
    			return true;
    		
    		return false;
    	}
    	
    	
    	public void setValueAt(Object aValue, Object node, int column) {
    		if(column == 3){
    				if(!((TreeTableNode)node).isLeaf()){
    					DemirbasMalzeme demirbas = (DemirbasMalzeme) ((TreeTableNode)node).getUserObject();
    					Boolean oldValue = new Boolean(((TreeTableNode)node).isSelected());
    					((TreeTableNode)node).setSelected(((Boolean)aValue).booleanValue());
    					if (demirbas!=null){ //demirbas nullsa bu node root dur.
    						miktariAyarla((Boolean) aValue,oldValue);
    					    TreeTableNode parent = (TreeTableNode) ((TreeTableNode) node).getParent();
    						fireTreeNodesChanged(this,getPathToRoot(parent),new int[] { getIndexOfChild(parent, node)},	new Object[] { node });
    					}
    					
    					Iterator iter = ((TreeTableNode)node).getChildren().iterator();
    					while (iter.hasNext())
    						setValueAt(aValue,iter.next(),column);
    				}else{
    					((TreeTableNode)node).setSelected(((Boolean)aValue).booleanValue());
    					TreeTableNode parent = (TreeTableNode) ((TreeTableNode) node).getParent();
    					fireTreeNodesChanged(this,getPathToRoot(parent),new int[] { getIndexOfChild(parent, node)},	new Object[] { node });
    				}
    		}
    	}
    	
    	public void miktariAyarla(Boolean newValue,Boolean oldValue){
    	    if (!newValue.equals(oldValue)){
    	        if(newValue.booleanValue())
    			    increaseMiktar();
    	        else
    	            decreaseMiktar();  	        
    	    }
    	}
    }
    
    public class DemirbasSorguTableModel extends DynamicTreeTableModel{
        public DemirbasSorguTableModel(TreeTableNode root){
            super(root,demirbasSorguTablecolumnNames,null,null,demirbasSorguTableClassTypes);
        }
        
    	public Object getValueAt(Object node, int column) {
    		if(node == null) return null;
    		
    		TreeTableNode tn = (TreeTableNode) node;
    		
    		if(column==3)
    		    return new Boolean(tn.isSelected());
    		else if(tn.isLeaf()){
    			NitelikDegeri nitelik = (NitelikDegeri) tn.getUserObject();
    			if(nitelik != null){
    			    switch (column) {
    			    case 0:
    			        return nitelik.getNitelikAdi();
    			    case 1:
    			        return nitelik.getNitelikDegeri();
    			    case 2:
    			        return "";
    			    default:
    			        break;
    			   }
    			}
    		}else{
    			DemirbasMalzeme demirbas = (DemirbasMalzeme)tn.getUserObject();
  
    			if(demirbas != null) {
    				//TODO bu paha islemi kaldirilcak, pahasý set edilmeyen datalar icin kondu
    	  			MalzemePahasi paha = demirbas.getPaha();
        			if (paha==null){
        				paha = new MalzemePahasi(); 
        				paha.setVergisizBirimFiyat(Money.yeniTurkLirasi(0));
        				paha.setKdvOrani(new Float(0));
        				demirbas.setPaha(paha);
        			}
    				NumberFormat numberFormat = NumberFormat.getInstance();
    				switch (column) {
    				case 0:
    					return demirbas.getDemirbasNo().toString();
    				case 1:
    					return numberFormat.format(paha.getVergisizBirimFiyatMiktar())+" "+paha.getVergisizBirimFiyatKur();
    				case 2:
    					return new Float(paha.getAsilOran()*100).toString();
    				default:
    					break;
    				}
    			}
    		}
    		return null;
    	}
    	
    	public boolean isCellEditable(Object node, int column) {
    		if(column==0)
    			return true;
    		return false;
    	}
    	
    	public void setValueAt(Object aValue, Object node, int column) {}
    }
}
