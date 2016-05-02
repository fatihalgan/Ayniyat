package com.iztek.ayniyat.sorgular.ambaraDevir.gui;

import javax.swing.*;
import javax.swing.tree.TreePath;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.model.treetable.DynamicTreeTableModel;
import com.iztek.ayniyat.model.treetable.JTreeTable;
import com.iztek.ayniyat.model.treetable.TreeTableModel;
import com.iztek.ayniyat.model.treetable.TreeTableNode;
import com.iztek.ayniyat.panel.IAyniyatPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Iterator;
import java.util.Vector;
/**
 * @author Cagdas CIRIT
 **/
public class AmbaraDevirCikanMalzemeler extends JFrame implements IAyniyatPanel{

	private JPanel jContentPane = null;
	private JTreeTable cikisFisleriTable = null;
	private JScrollPane cikisFisleriScrollPane = null;
	private JButton cikisButton = null;
	private JPanel buttonPanel = null;
	private String panelName = null;
	private DynamicTreeTableModel cikisFisleriTableModel;
    private final static String[] cikisFisleriTablecolumnNames = { "Belge Numarasý", "Gönderilen Ambar","Taþýyan Personel", "Hareket Tarihi" };
    private final static Class[] cikisFisleriTableClassTypes = { TreeTableModel.class, String.class,String.class, String.class };

	public AmbaraDevirCikanMalzemeler(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}

	public void initialize() {
	    AnaController.getInstance().getAmbaraDevirSorguController().registerAmbaraDevirCikanMalzemeler(AmbaraDevirCikanMalzemeler.this);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Ambara Devir - Çýkan Malzemeler");
	}

	public javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getCikisFisleriScrollPane(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}
  
	private JTreeTable getCikisFisleriTable() {
		if (cikisFisleriTable == null) {
			cikisFisleriTable = createCikisFisleriTable();
		}
		return cikisFisleriTable;
	}
   
	private JScrollPane getCikisFisleriScrollPane() {
		if (cikisFisleriScrollPane == null) {
			cikisFisleriScrollPane = new JScrollPane();
			cikisFisleriScrollPane.setViewportView(getCikisFisleriTable());
			cikisFisleriScrollPane.getViewport().setBackground(Color.WHITE);
		}
		return cikisFisleriScrollPane;
	}
   
	private JButton getCikisButton() {
		if (cikisButton == null) {
			cikisButton = new JButton();
			cikisButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/home.png")));
			cikisButton.setToolTipText("Çýkýþ");
			cikisButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					AnaController.getInstance().malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir("bosPanel");
				}
			});
		}
		return cikisButton;
	}
   
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.setPreferredSize(new java.awt.Dimension(1,36));
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = -34;
			gridBagConstraints1.ipady = -10;
			gridBagConstraints1.insets = new java.awt.Insets(6,378,6,390);
			buttonPanel.add(getCikisButton(), gridBagConstraints1);
		}
		return buttonPanel;
	}
	
    public JTreeTable createCikisFisleriTable() {
        TreeTableNode root = new TreeTableNode();
        cikisFisleriTableModel = new FisMalzemeleriTableModel(root,cikisFisleriTablecolumnNames,cikisFisleriTableClassTypes);
        return new JTreeTable(cikisFisleriTableModel);
    }
    
	private void cikisFisleriTablosunaVeriEkle(Vector hareketler){
	    TreeTableNode root = (TreeTableNode) cikisFisleriTableModel.getRoot();
	    TreeTableNode hareketNode = null;

	    Iterator iter = hareketler.iterator();
	    while (iter.hasNext()) {
            AbstractMalzemeHareketi hareket = (AbstractMalzemeHareketi) iter.next();
            hareketNode = new TreeTableNode(hareket);
            AnaController.getInstance().getAmbaraDevirSorguController().hareketeMalzemeleriniEkle(hareketNode);
            root.add(hareketNode);
            cikisFisleriTableModel.fireTreeNodesInserted(this,cikisFisleriTableModel.getPathToRoot(hareketNode),new int[] {cikisFisleriTableModel.getIndexOfChild(root,hareketNode)},new Object[] { hareketNode });          
	    }
	    //scroll2Last Added TreeTableNode
	    if (hareketNode!=null)
	        getCikisFisleriTable().getTree().scrollPathToVisible(new TreePath(cikisFisleriTableModel.getPathToRoot(hareketNode)));
	}
	
	private void removeAllNodesAndCleanCikisFisleriTable(){
	    TreeTableNode root =  (TreeTableNode) cikisFisleriTableModel.getRoot();
	    if (root.getChildren()!=null){
	        Iterator iter = root.getChildren().iterator();
	    
	        while (iter.hasNext()) {
	            TreeTableNode element = (TreeTableNode) iter.next();
	            int index = cikisFisleriTableModel.getIndexOfChild(root,element);
	            iter.remove();
	            cikisFisleriTableModel.fireTreeNodesRemoved(this,element.getPath(),new int[] {index},new Object[] { element });	            	
	        }
	        cikisFisleriTableModel.fireTreeStructureChanged(this, ((TreeTableNode) cikisFisleriTableModel.getRoot()).getPath(), null, null);
	    }
	}

    public String getPanelName() {
        return panelName;
    }

    public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
        unLoadYourself();      
        cikisFisleriTablosunaVeriEkle(AnaController.getInstance().getAmbaraDevirSorguController().baskaAmbaraDevirCikisHareketleriniCek());
    }

    public void unLoadYourself() {
        removeAllNodesAndCleanCikisFisleriTable();
    }
    
    public static void main(String[] args) {
        new AmbaraDevirCikanMalzemeler("").setVisible(true);
    }
}
