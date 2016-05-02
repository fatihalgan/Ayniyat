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
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
/**
 * @author Cagdas CIRIT
 **/
public class AmbaraDevirGelenMalzemeler extends JFrame implements IAyniyatPanel{

	private JPanel jContentPane = null;
	private JTreeTable girisFisleriTable = null;
	private JScrollPane girisFisleriScrollPane = null;
	private JButton cikisButton = null;
	private JPanel buttonPanel = null;
	private String panelName = null;
	private DynamicTreeTableModel girisFisleriTableModel;
    private final static String[] girisFisleriTablecolumnNames = { "Belge Numarasý", "Gönderen Ambar","Taþýyan Personel", "Hareket Tarihi" };
    private final static Class[] girisFisleriTableClassTypes = { TreeTableModel.class, String.class,String.class, String.class };

	private JButton ambaraGirisButton = null;
	private JPopupMenu popupMenu = null;
	private JMenuItem secileniGirMenuItem = null;
	public AmbaraDevirGelenMalzemeler(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}

	public void initialize() {
	    AnaController.getInstance().getAmbaraDevirSorguController().registerAmbaraDevirGelenMalzemeler(AmbaraDevirGelenMalzemeler.this);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Ambara Devir - Gelen Malzemeler");
	}

	public javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getGirisFisleriScrollPane(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}
  
	private JTreeTable getGirisFisleriTable() {
		if (girisFisleriTable == null) {
			girisFisleriTable = createCikisFisleriTable();
			girisFisleriTable.addMouseListener(new java.awt.event.MouseListener() { 
				public void mouseReleased(java.awt.event.MouseEvent e) {    
					if(e.isPopupTrigger()) {
						popupMenu = getPopupMenu();
						popupMenu.show(girisFisleriTable, e.getX(), e.getY());
					}
				}
				public void mouseClicked(java.awt.event.MouseEvent e) {
				    getSecileniGirMenuItem().setEnabled(true);
				    getAmbaraGirisButton().setEnabled(true);
				} 
				public void mousePressed(java.awt.event.MouseEvent e) {} 
				public void mouseEntered(java.awt.event.MouseEvent e) {} 
				public void mouseExited(java.awt.event.MouseEvent e) {} 
			});
		}
		return girisFisleriTable;
	}
   
	private JScrollPane getGirisFisleriScrollPane() {
		if (girisFisleriScrollPane == null) {
			girisFisleriScrollPane = new JScrollPane();
			girisFisleriScrollPane.setViewportView(getGirisFisleriTable());
			girisFisleriScrollPane.getViewport().setBackground(Color.WHITE);
		}
		return girisFisleriScrollPane;
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
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.setPreferredSize(new java.awt.Dimension(1,36));
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = -34;
			gridBagConstraints1.ipady = -10;
			gridBagConstraints1.insets = new java.awt.Insets(6,124,6,248);
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.ipadx = -34;
			gridBagConstraints2.ipady = -10;
			gridBagConstraints2.insets = new java.awt.Insets(6,248,6,124);
			buttonPanel.add(getCikisButton(), gridBagConstraints1);
			buttonPanel.add(getAmbaraGirisButton(), gridBagConstraints2);
		}
		return buttonPanel;
	}
	
    public JTreeTable createCikisFisleriTable() {
        TreeTableNode root = new TreeTableNode();
        girisFisleriTableModel = new FisMalzemeleriTableModel(root,girisFisleriTablecolumnNames,girisFisleriTableClassTypes);
        return new JTreeTable(girisFisleriTableModel);
    }
    
	private JButton getAmbaraGirisButton() {
		if (ambaraGirisButton == null) {
			ambaraGirisButton = new JButton();
			ambaraGirisButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/server_add.png")));
			ambaraGirisButton.setToolTipText("Seçilen fiþin ambara giriþini yap");
			ambaraGirisButton.setSelected(false);
			ambaraGirisButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					secilenFisinGirisiniYapAction();
				}
			});
		}
		return ambaraGirisButton;
	}
	
	private JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.add(getSecileniGirMenuItem());
		}
		return popupMenu;
	}
  
	private JMenuItem getSecileniGirMenuItem() {
		if (secileniGirMenuItem == null) {
			secileniGirMenuItem = new JMenuItem();
			secileniGirMenuItem.setText("Giriþini Yap");
			secileniGirMenuItem.setEnabled(false);
			secileniGirMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					secilenFisinGirisiniYapAction();
				}
			});
		}
		return secileniGirMenuItem;
	}
    
	private void cikisFisleriTablosunaVeriEkle(Vector hareketler){
	    TreeTableNode root = (TreeTableNode) girisFisleriTableModel.getRoot();
	    TreeTableNode hareketNode = null;

	    Iterator iter = hareketler.iterator();
	    while (iter.hasNext()) {
            AbstractMalzemeHareketi hareket = (AbstractMalzemeHareketi) iter.next();
            hareketNode = new TreeTableNode(hareket);
            AnaController.getInstance().getAmbaraDevirSorguController().hareketeMalzemeleriniEkle(hareketNode);
            root.add(hareketNode);
            girisFisleriTableModel.fireTreeNodesInserted(this,girisFisleriTableModel.getPathToRoot(hareketNode),new int[] {girisFisleriTableModel.getIndexOfChild(root,hareketNode)},new Object[] { hareketNode });          
	    }
	    //scroll2Last Added TreeTableNode
	    if (hareketNode!=null)
	        getGirisFisleriTable().getTree().scrollPathToVisible(new TreePath(girisFisleriTableModel.getPathToRoot(hareketNode)));
	}
	
	private void removeAllNodesAndCleanCikisFisleriTable(){
	    TreeTableNode root =  (TreeTableNode) girisFisleriTableModel.getRoot();
	    if (root.getChildren()!=null){
	        Iterator iter = root.getChildren().iterator();
	    
	        while (iter.hasNext()) {
	            TreeTableNode element = (TreeTableNode) iter.next();
	            int index = girisFisleriTableModel.getIndexOfChild(root,element);
	            iter.remove();
	            girisFisleriTableModel.fireTreeNodesRemoved(this,element.getPath(),new int[] {index},new Object[] { element });	            	
	        }
	        girisFisleriTableModel.fireTreeStructureChanged(this, ((TreeTableNode) girisFisleriTableModel.getRoot()).getPath(), null, null);
	    }
	}
	
    private TreeTableNode getSelectedNode() {
        TreeTableNode node = null;
        TreePath currentPath = getGirisFisleriTable().getTree().getSelectionPath();
        if (currentPath != null)
            node = (TreeTableNode) (currentPath.getLastPathComponent());
        
        if(node!=girisFisleriTableModel.getRoot())
            return node;
        return null;
    }

    public String getPanelName() {
        return panelName;
    }

    public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
        unLoadYourself();      
        cikisFisleriTablosunaVeriEkle(AnaController.getInstance().getAmbaraDevirSorguController().baskaAmbaraDevirGirisHareketleriniCek());
    }

    public void unLoadYourself() {
        removeAllNodesAndCleanCikisFisleriTable();
        getSecileniGirMenuItem().setEnabled(false);
        getAmbaraGirisButton().setEnabled(false);
    }
    
    private void secilenFisinGirisiniYapAction(){
        TreeTableNode hareketNode = null;
        if(getSelectedNode()!=null){
	        if(getSelectedNode().isLeaf()){
	            hareketNode = (TreeTableNode)getSelectedNode().getParent();
	        }else
	            hareketNode = getSelectedNode();
    	
	        AnaController.getInstance().getAmbaraDevirSorguController().gelenFisMalzemeleriniMalzemeGirisEkraninaGonder(hareketNode);
        }
    }
    
    public static void main(String[] args) {
        new AmbaraDevirGelenMalzemeler("").setVisible(true);
    }
}
