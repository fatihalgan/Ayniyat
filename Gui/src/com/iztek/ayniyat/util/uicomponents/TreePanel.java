package com.iztek.ayniyat.util.uicomponents;

import java.awt.Toolkit;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.iztek.ayniyat.event.KategoriCutEvent;
import com.iztek.ayniyat.event.KategoriEvent;
import com.iztek.ayniyat.event.KategoriEventServiceImpl;
import com.iztek.ayniyat.event.KategoriRemoveEvent;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.IMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.malzemetanimi.service.MalzemeTanimlariManager;
import com.iztek.ayniyat.model.AbstractTreeModel;
import com.iztek.ayniyat.model.AyniyatTreeModel;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.tanimlar.controller.YerlesimController;
import com.iztek.ayniyat.util.uiservice.MenuItemNameFactory;
import com.iztek.ayniyat.util.uiservice.PanelFactory;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.service.YerlesimManager;
import com.iztek.util.persistence.DAOFactory;

public class TreePanel extends JPanel {
/**
 * @author fusun
 */
	private IKategorilendirilebilir rootNode = null;
	private JScrollPane jScrollPane = null;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTree jTree = null;
	private PopUpMenuler pop = null;
	public boolean isSecilenMalzemeTasinacak = false;
	
	public TreePanel(PopUpMenuler popM) {
		super();
		this.pop=popM;
		initialize();
	}

	private void initialize() {
	//	this.setLayout(null);
		this.setLayout(null);
		this.setSize(292, 371);
		pop.registerTreePanel(this);
		this.add(getJScrollPane(), null);
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new java.awt.Dimension(288,363));
			jScrollPane.setLocation(new java.awt.Point(3,5));
			jScrollPane.setViewportView(getJTree());
		}
		return jScrollPane;
	}

	public JTree getJTree() {
		if (jTree == null) {
			jTree = new JTree();
			jTree.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseClicked(java.awt.event.MouseEvent e) { 
            		IKategorilendirilebilir seciliNode = getSeciliNode();
                    if (seciliNode != null) {
                    	IAyniyatPanel panel = PanelFactory.getService().getGosterisPanelByNode(seciliNode);
                    	panel.loadYourself(seciliNode,null);
                    	AnaController.getInstance().getCurrentTanimlarController().panelOneGetir(panel.getPanelName());
                    	if(AnaController.getInstance().getCurrentTanimlarController() instanceof YerlesimController){
                    	((YerlesimController)AnaController.getInstance().getCurrentTanimlarController()).demirbasTablosunuTemizle();
                    	((YerlesimController)AnaController.getInstance().getCurrentTanimlarController()).zimmetliDemirbaslariTabloyaYukle((AbstractZimmetAlan) seciliNode);
                    	}
                    }
            	}
            	public void mouseReleased(java.awt.event.MouseEvent e) {
                    if (e.isPopupTrigger() && (getSeciliNode() != null)) {
                 //       pop = getJPopupMenu();
                    	
                        loadPopUpMenu(AnaController.getInstance().getCurrentTanimlarController().getMenuItemNamesForTree(getSeciliNode()));                   
                        pop.show(jTree, e.getX(), e.getY());
                    }	
            	} 
            	public void mousePressed(java.awt.event.MouseEvent e) {} 
            	public void mouseEntered(java.awt.event.MouseEvent e) {} 
            	public void mouseExited(java.awt.event.MouseEvent e) {} 
            });
        }
        return jTree;
    }
	
	public boolean addKategori(IKategorilendirilebilir child) {
        return addKategori(getSeciliNode(), child, true);
    }
	
	public IKategorilendirilebilir getSeciliNode() {
        IKategorilendirilebilir node = null;
        TreePath currentPath = getJTree().getSelectionPath();

        if (currentPath == null) {
            node = rootNode;
        } else {
            node = (IKategorilendirilebilir) (currentPath
                    .getLastPathComponent());
        }
        return node;
    }
	
	public boolean addKategori(IKategorilendirilebilir parent,IKategorilendirilebilir child) {
        return addKategori(parent, child, false);
    }
	
	public boolean addKategori(IKategorilendirilebilir parent,IKategorilendirilebilir child, boolean shouldBeVisible) {
	        if (parent == null) 
	            parent = rootNode;
	        
	        if(!hasChildByNodeValue(parent,child.getNodeValue())){
	        	if(child instanceof AbstractMalzemeTanimi && MalzemeTanimlariManager.isMalzemeKodExist(((Kategori)child).getKod())){
	            	Object[] d={"Tamam"};
	            	JOptionPane.showOptionDialog(null,"Girmiþ olduðunuz malzeme kodu sistemde mevcuttur! Lütfen baþka bir kod giriniz.","Uyarý",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_OPTION,null,d,d[0]);
	            	return false;
	        	}else{
		        	KategoriEventServiceImpl.getTanimlarEventService().fireKategoriAddEvent(new KategoriEvent(this, parent, child));      		
		        	if(shouldBeVisible) 
		        		setTreeNodePath2Visible(child);
		        	return true;
	        	}
	        }else{
	        	Object[] d={"Tamam"};
	        	JOptionPane.showOptionDialog(null,parent+" alt dallarý arasýnda "+child+" isimli bir eleman halihazýrda mevcuttur!","Uyarý",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_OPTION,null,d,d[0]);
	        	return false;
	        }
	  }
	
	 public boolean hasChildByNodeValue(IKategorilendirilebilir parent,String childNodeValue){
	    	if (parent.getAltKategoriler() == null)
	    		return false;
	    	
	    	Iterator iter = parent.getAltKategoriler().iterator();
	    	
	    	while (iter.hasNext()) {
				IKategorilendirilebilir child = (IKategorilendirilebilir) iter.next();
				if (child.getNodeValue().equalsIgnoreCase(childNodeValue)){
					getJTree().setSelectionPath(new TreePath(((AbstractTreeModel) getJTree().getModel()).getPathToRoot(child)));
					return true;
				}
			}  	
	    	return false;
	    }

	 public void setTreeNodePath2Visible(IKategorilendirilebilir node){
	   		if (node!=null)
	   			getJTree().setSelectionPath(new TreePath(((AbstractTreeModel) getJTree().getModel()).getPathToRoot(node)));
	   }
	 
	 public void removeKategori() {
	       IKategorilendirilebilir currentNode = getSeciliNode();
	       IKategorilendirilebilir parentNode = currentNode.getAnaKategori();
	            
	       if(currentNode != rootNode){
	       		if (AnaController.getInstance().getCurrentTanimlarController().showConfirmationDialogBox(null,"Sil iþlemini gerçekleþtirmek istediðinizden emin misiniz?")){
	       	    	removeKategori(parentNode,currentNode);
	       			AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
	       		}
	       }else// Either there was no selection, or the root was selected.
	        	toolkit.beep();
	    }
	 
	  public void removeKategori(IKategorilendirilebilir parent,IKategorilendirilebilir child) {
	        if (parent != null){
	        	int index = parent.getIndexOfChild(child);
	        	try {
	        		if (index!=-1){
	        			KategoriEventServiceImpl.getTanimlarEventService().fireKategoriRemoveEvent(new KategoriRemoveEvent(this, parent, child, index));
	        		 }
	        	 } catch(Throwable t) {
	     	        AnaController.getInstance().getCurrentTanimlarController().showErrorDialogBox(null, t.getMessage());
	     	    }
	        }
	    }
	 
	  public void updateKategori(IKategorilendirilebilir updateNode) {
	        if (updateNode != null){
	    		KategoriEventServiceImpl.getTanimlarEventService().fireKategoriChangeEvent(new KategoriEvent(this, null, updateNode));      		
	        }
	    }
	  
	  public void updateKategori(IKategorilendirilebilir updateNode, Collection retains) {
	        if (updateNode != null){
	        	if(retains!=null)
	        		((IMalzemeTanimi)updateNode).retainNitelikTanimlari(retains);
	    		KategoriEventServiceImpl.getTanimlarEventService().fireKategoriChangeEvent(new KategoriEvent(this, null, updateNode));      		
	        }
	    }
	  
	  public void copyKategori(IKategorilendirilebilir destination, IKategorilendirilebilir source){
	        if(destination == null){
	            destination = rootNode;
	        }
	        KategoriEventServiceImpl.getTanimlarEventService().fireKategoriCopyEvent(new KategoriEvent(this,source,destination));  
	        getJTree().expandPath(new TreePath(((AbstractTreeModel) getJTree().getModel()).getPathToRoot(destination)));
	   }
	  
	  public void cutKategori(IKategorilendirilebilir destination, IKategorilendirilebilir source){
	        IKategorilendirilebilir oldParent = source.getAnaKategori();
	        if(!oldParent.equals(destination)){ // kesip yapýþtýrdýðý yer aynýysa hiç biþey yapma 
	        	int index = oldParent.getIndexOfChild(source);
	        
	        	if(destination == null)
	        		destination = rootNode;
	        
	        	KategoriEventServiceImpl.getTanimlarEventService().fireKategoriCutEvent(new KategoriCutEvent(this, source, destination, index, oldParent)); 
	        	getJTree().expandPath(new TreePath(((AbstractTreeModel) getJTree().getModel()).getPathToRoot(destination)));
	        }
	    }

	  public void zimmetAlanEklePaneliniAc(String panelName){
	        IAyniyatPanel panel = PanelFactory.getService().getPanel(panelName);
			panel.loadYourself(null,getSeciliNode());
	        AnaController.getInstance().getCurrentTanimlarController().panelOneGetir(panel.getPanelName());
	    }
	 
	   public void loadYourself() {
		   AyniyatTreeModel atm=null;
		   if(AnaController.getInstance().getCurrentTanimlarController() instanceof YerlesimController){
			   atm = new AyniyatTreeModel(YerlesimManager.findRootKategori(DAOFactory.YERLESIM));
		   }
		   else{
			   atm = new AyniyatTreeModel(MalzemeTanimlariManager.findRootKategori(DAOFactory.MALZEME_TANIMI));   
		   }
	    	KategoriEventServiceImpl.getTanimlarEventService().addKategoriEventListener(atm);
	        getJTree().setModel(atm);
	    }
	   
	   private void loadPopUpMenu(String[] items){
		   
	    	pop.unLoadPopUpMenu();//clear popUp
	    	
	    	if(items!=null){
	    		for (int i=0;i<items.length;i++){
	    			if (items[i].equals(MenuItemNameFactory.SEPERATOR))
	    				pop.addSeparator();
	    			else
	    				pop.add(getMenuItemByName(items[i]));
	    		}
	    		// yapýþtýrý kontrol et
	    		pop.getYapistirMenuItem().setEnabled(!AnaController.getInstance().getCurrentTanimlarController().isClipboardEmpty());
	    	}
	    }
  
	   private JMenuItem getMenuItemByName(String name){
	    	Iterator iter = pop.menuItems.iterator();
	    	
	    	while (iter.hasNext()) {
				JMenuItem element = (JMenuItem) iter.next();
				if(element.getText().equals(name))
					return element;
			}  	
	    	return null;
	   }
}
