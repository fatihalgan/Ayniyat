package com.iztek.ayniyat.sorgular.yevmiyedefteri.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.TreePath;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeHareketi;
import com.iztek.ayniyat.model.treetable.DynamicTreeTableModel;
import com.iztek.ayniyat.model.treetable.JTreeTable;
import com.iztek.ayniyat.model.treetable.TreeTableModel;
import com.iztek.ayniyat.model.treetable.TreeTableNode;
import com.iztek.ayniyat.panel.IAyniyatPanel;
import com.iztek.ayniyat.panel.ISorgularPanel;
import com.iztek.ayniyat.util.uicomponents.SorguButtonPanel;
import com.iztek.commons.money.Bank;
import com.iztek.commons.money.Money;

/**
 * @author Umit Akyol
 * */
public class YevmiveDefteri extends JFrame implements IAyniyatPanel,ISorgularPanel{

	private javax.swing.JPanel jContentPane = null;

	private javax.swing.JPanel jPanel = null;
	private javax.swing.JScrollPane jScrollPane = null;
	private JTreeTable yevmiyeTable = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField jGirenTutarTextField = null;
	private JTextField jCikanTutarTextField = null;
	private JLabel jLabel7 = null;
	private JPanel jPanel4 = null;
	private JTextField baslangicTarihTextField = null;
	private JTextField bitisTarihTextField = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JButton filtreleButton = null;
	private String panelName = null;
	private final static String[] yevmiyeTablecolumnNames = {"Belge No","Kayýt Tarihi","Düzenleme Tarihi","Tutarý","Ýþlem Tipi"};
    private final static Class[] yevmiyeTableClassTypes = { TreeTableModel.class, String.class,String.class,String.class,String.class};
    private DynamicTreeTableModel yevmiyeTableModel;
    private Bank bank = new Bank();
    private Money cikisPahasi = null;
	private Money girisPahasi = null;
	private SorguButtonPanel buttonPanel=null;
    
	public YevmiveDefteri(String panelName) {
		super();
		initialize();
		this.panelName = panelName;
	}
	
	public void initialize() {
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setResizable(false);
		this.setTitle("Yevmiye Defteri");
		AnaController.getInstance().getYevmiyeDefteriController().registerYevmiyeDefteri(this);
	}
	
	public javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	public javax.swing.JPanel getJPanel() {
		if(jPanel == null) {
			jPanel = new javax.swing.JPanel();
			javax.swing.border.TitledBorder titledBorder1 = javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED),"Yevmiye Defteri",javax.swing.border.TitledBorder.LEFT,javax.swing.border.TitledBorder.DEFAULT_POSITION,new java.awt.Font("Dialog",java.awt.Font.BOLD,12),new java.awt.Color(113,7,113));
			jPanel.setLayout(new java.awt.BorderLayout());
			jPanel.setBorder(titledBorder1);
			jPanel.add(getJPanel4(), java.awt.BorderLayout.NORTH);
			jPanel.add(getJPanel2(), java.awt.BorderLayout.CENTER);
			jPanel.add(getButtonPanel(),BorderLayout.SOUTH);
		}
		return jPanel;
	}
	private SorguButtonPanel getButtonPanel(){
		if(buttonPanel==null){
			buttonPanel=new SorguButtonPanel();
			buttonPanel.registerPanel(this);
			buttonPanel.getInceleButton().setVisible(false);
		}
		return buttonPanel;
	}
	public JTreeTable getYevmiyeTable() {
		if(yevmiyeTable == null) {
		    yevmiyeTable = createKayitlarTable();
		}
		return yevmiyeTable;
	}

	private javax.swing.JScrollPane getJScrollPane() {
		if(jScrollPane == null) {
			jScrollPane = new javax.swing.JScrollPane();
			jScrollPane.setViewportView(getYevmiyeTable());
			jScrollPane.getViewport().setBackground(Color.WHITE);
		}
		return jScrollPane;
	}
	
	private void disposeThis() {
		this.dispose();					
	}
	   
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BorderLayout());
			jPanel2.add(getJPanel3(), java.awt.BorderLayout.SOUTH);
			jPanel2.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return jPanel2;
	}
	  
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel7 = new JLabel();
			jLabel2 = new JLabel();
			jLabel1 = new JLabel();
			jLabel = new JLabel();
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setPreferredSize(new java.awt.Dimension(1,80));
			jLabel.setText("Tutarý:");
			jLabel.setBounds(358, 34, 46, 18);
			jLabel1.setText("                       Giren");
			jLabel1.setBounds(412, 10, 172, 15);
			jLabel2.setText("                    Çýkan");
			jLabel2.setBounds(604, 10, 170, 15);
			jLabel7.setText("Kayýt edenin adý, soyadý, ünvaný, imzasý");
			jLabel7.setBounds(11, 12, 227, 13);
			jPanel3.add(jLabel, null);
			jPanel3.add(jLabel1, null);
			jPanel3.add(jLabel2, null);
			jPanel3.add(getJGirenTutarTextField(), null);
			jPanel3.add(getJCikanTutarTextField(), null);
			jPanel3.add(jLabel7, null);
		}
		return jPanel3;
	}
	  
	public JTextField getJGirenTutarTextField() {
		if (jGirenTutarTextField == null) {
			jGirenTutarTextField = new JTextField();
			jGirenTutarTextField.setEditable(false);
			jGirenTutarTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jGirenTutarTextField.setBounds(412, 34, 180, 18);
		}
		return jGirenTutarTextField;
	}
 
	public JTextField getJCikanTutarTextField() {
		if (jCikanTutarTextField == null) {
			jCikanTutarTextField = new JTextField();
			jCikanTutarTextField.setEditable(false);
			jCikanTutarTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			jCikanTutarTextField.setBounds(599, 34, 179, 18);
		}
		return jCikanTutarTextField;
	}
	
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jLabel9 = new JLabel();
			jLabel8 = new JLabel();
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.setPreferredSize(new java.awt.Dimension(1,120));
			jLabel8.setBounds(389, 34, 20, 24);
			jLabel8.setText("  /");
			jLabel8.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 18));
			jLabel9.setBounds(357, 10, 84, 24);
			jLabel9.setText("Tarih Aralýðý");
			jLabel9.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			jPanel4.add(getBaslangicTarihTextField(), null);
			jPanel4.add(getBitisTarihTextField(), null);
			jPanel4.add(jLabel8, null);
			jPanel4.add(jLabel9, null);
			jPanel4.add(getFiltreleButton(), null);
		}
		return jPanel4;
	}
	    
	private JTextField getBaslangicTarihTextField() {
		if (baslangicTarihTextField == null) {
			baslangicTarihTextField = new JTextField();
			baslangicTarihTextField.setBounds(294, 34, 97, 24);
			baslangicTarihTextField.setText("");
			baslangicTarihTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			baslangicTarihTextField.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					suzmeAction();
				}
			});
		}
		return baslangicTarihTextField;
	}
	   
	private JTextField getBitisTarihTextField() {
		if (bitisTarihTextField == null) {
			bitisTarihTextField = new JTextField();
			bitisTarihTextField.setBounds(409, 34, 97, 24);
			bitisTarihTextField.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
			bitisTarihTextField.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					suzmeAction();
				}
			});
		}
		return bitisTarihTextField;
	}
	    
	private JButton getFiltreleButton() {
		if (filtreleButton == null) {
			filtreleButton = new JButton();
			filtreleButton.setToolTipText("Süz");
			filtreleButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/funnel_down.png")));
			filtreleButton.setLocation(388, 70);
			filtreleButton.setSize(24, 24);
			filtreleButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {
				    suzmeAction();
				}
			});
		}
		return filtreleButton;
	}

    public String getPanelName() {
        return panelName;
    }

    public void loadYourself(IKategorilendirilebilir node, IKategorilendirilebilir parentNode) {
        Date tarih = new Date();
    }

    public void unLoadYourself() {
        removeAllNodesAndCleanYevmiyeTable();
        getBaslangicTarihTextField().setText("");
        getBitisTarihTextField().setText("");
    }

    private Date getBaslangicTarihi(){
        Date tarih = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String tarihStr=getBaslangicTarihTextField().getText();
        if(tarihStr.equals(""))
            return null;
        
	    try {
            tarih = (Date) dateFormat.parseObject(tarihStr);
        } catch (ParseException e) {
           e.printStackTrace();
        }
        return tarih;
    }
    
    private Date getBitisTarihi(){
        Date tarih = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String tarihStr=getBitisTarihTextField().getText();
        if(tarihStr.equals(""))
            return null;
        
	    try {
            tarih = (Date) dateFormat.parseObject(tarihStr);
        } catch (ParseException e) {
           e.printStackTrace();
        }
        return tarih;
    }
    
    public void removeAllNodesAndCleanYevmiyeTable(){
	    TreeTableNode root =  (TreeTableNode) yevmiyeTableModel.getRoot();
	    if (root.getChildren()!=null){
	        Iterator iter = root.getChildren().iterator();
	    
	        while (iter.hasNext()) {
	            TreeTableNode element = (TreeTableNode) iter.next();
	            int index = yevmiyeTableModel.getIndexOfChild(root,element);
	            iter.remove();
	            yevmiyeTableModel.fireTreeNodesRemoved(this,element.getPath(),new int[] {index},new Object[] { element });	            	
	        }
	        yevmiyeTableModel.fireTreeStructureChanged(this, ((TreeTableNode) yevmiyeTableModel.getRoot()).getPath(), null, null);
	    }
	    root.setSelected(false);
	}
    
    private Money yevmiyeTablosunaVeriEkle(Vector hareketler,String type){
	    TreeTableNode root = (TreeTableNode) yevmiyeTableModel.getRoot();
	    TreeTableNode hareketNode = null;
	    Money tempPaha = Money.yeniTurkLirasi(0);

	    Iterator iter = hareketler.iterator();
	    while (iter.hasNext()) {
            IMalzemeHareketi malzemeHareketi = (IMalzemeHareketi) iter.next();
            hareketNode = new TreeTableNode(malzemeHareketi);
            if(type.equals("Giriþ"))
                hareketNode.setSelected(true);
            else
                hareketNode.setSelected(false);
            AnaController.getInstance().getYevmiyeDefteriController().hareketeMalzemeleriniEkle(hareketNode);
            root.add(hareketNode);
            yevmiyeTableModel.fireTreeNodesInserted(this,yevmiyeTableModel.getPathToRoot(hareketNode),new int[] {yevmiyeTableModel.getIndexOfChild(root,hareketNode)},new Object[] { hareketNode });

            //tutara ekle
            Money toplamTutar = malzemeHareketi.getHareketFisi().getPaha().getToplamTutar();
            if(toplamTutar.getAmount() != null){
                tempPaha = bank.reduce(tempPaha.plus(toplamTutar),Money.YTL);
            }
	    }
	    //scroll2Last Added TreeTableNode
	    yevmiyeTableModel.fireTreeStructureChanged(this,root.getPath(),null,null);
	    if (hareketNode!=null)
	        getYevmiyeTable().getTree().scrollPathToVisible(new TreePath(yevmiyeTableModel.getPathToRoot(hareketNode)));
	    return tempPaha;
	}
    
    private JTreeTable createKayitlarTable() {
        TreeTableNode root = new TreeTableNode();
        yevmiyeTableModel = new YevmiyeDefteriTableModel(root,yevmiyeTablecolumnNames,yevmiyeTableClassTypes);
	    return new JTreeTable(yevmiyeTableModel);	
    }
    
    private void suzmeAction() {
        
        removeAllNodesAndCleanYevmiyeTable();
	    Vector hareketler = AnaController.getInstance().getYevmiyeDefteriController().yevmiyeDefteriGirisBilgileriniSorgula(getBaslangicTarihi(),getBitisTarihi());
	    girisPahasi = yevmiyeTablosunaVeriEkle(hareketler,"Giriþ");
	    hareketler = AnaController.getInstance().getYevmiyeDefteriController().yevmiyeDefteriCikisBilgileriniSorgula(getBaslangicTarihi(),getBitisTarihi());
	    cikisPahasi = yevmiyeTablosunaVeriEkle(hareketler,"Çýkýþ");
	    getJCikanTutarTextField().setText(""+cikisPahasi);
	    getJGirenTutarTextField().setText(""+girisPahasi);
        
    }
    public void inceleAction(){
    	
    }
 public void printAction(){
    	
    }
 }
