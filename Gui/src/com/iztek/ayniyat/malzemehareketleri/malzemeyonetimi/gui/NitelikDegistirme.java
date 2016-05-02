package com.iztek.ayniyat.malzemehareketleri.malzemeyonetimi.gui;

import java.awt.Font;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;
import com.iztek.ayniyat.malzemehareketleri.service.MalzemeManager;
import com.iztek.ayniyat.model.AyniyatTableModel;
import com.iztek.ayniyat.model.NiteliklerTableModel;

public class NitelikDegistirme extends JFrame {
	/**
	 * Füsun Çetin
	 */
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JLabel demirbasNoLabel = null;
	private JTextField demirbasNoTextField = null;
	private JButton bulButton = null;
	private JLabel tanimLabel = null;
	private DemirbasMalzeme demirbasMalzeme=null;
	private JTextField tanimTextField = null;
	private JPanel jPanel1 = null;
	private JButton degistirButton = null;
	private JTable niteliklerTable = null;
	private JScrollPane nitelikScrollPane=null;
	Set temp=new HashSet();
	public NitelikDegistirme() {
		super();
		initialize();
	}
	private void initialize() {
		this.setSize(450, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		 this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle("Malzeme Nitelik Deðiþtirme");
	}
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
		}
		return jContentPane;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			
			tanimLabel = new JLabel();
			tanimLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			tanimLabel.setBounds(new java.awt.Rectangle(10,70,140,25));
			tanimLabel.setText("Malzeme Tanýmý");

			 demirbasNoLabel = new JLabel();
			 demirbasNoLabel.setText("Demirbas Numarasý");
			 demirbasNoLabel.setSize(new java.awt.Dimension(140,25));
			 demirbasNoLabel.setLocation(new java.awt.Point(10,30));
			 demirbasNoLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new java.awt.Rectangle(0,0,442,121));
			jPanel.add( demirbasNoLabel, null);
			jPanel.add(getDemirbasNoTextField(), null);
			jPanel.add(getBulButton(), null);
			jPanel.add(tanimLabel, null);
			
			jPanel.add(getTanimTextField(), null);
			
		}
		return jPanel;
	}
	private JTextField getDemirbasNoTextField() {
		if (demirbasNoTextField == null) {
			demirbasNoTextField = new JTextField();
			demirbasNoTextField.setBounds(new java.awt.Rectangle(150,30,111,25));
		}
		return demirbasNoTextField;
	}

	private JButton getBulButton() {
		if (bulButton == null) {
			bulButton = new JButton();
			bulButton.setBounds(new java.awt.Rectangle(300,60,100,25));
			bulButton.setText("Bul");
			bulButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					demirbasMalzeme = (DemirbasMalzeme) MalzemeManager.findDemirbasByDemirbasNo(getDemirbasNoTextField().getText());
					((AyniyatTableModel)getNiteliklerTable().getModel()).removeAllRows();
					if(demirbasMalzeme!=null){
						degistirButton.setEnabled(true);
						getTanimTextField().setText(demirbasMalzeme.getMalzemeTanimi().getTanim());
						
						Vector t1=new Vector();
						Vector t2=new Vector();
							t1.addAll(demirbasMalzeme.getMalzemeTanimi().getNitelikTanimlari());
							t2.addAll(demirbasMalzeme.getNitelikDegerleri());
							for(int i=0;i<t2.size();i++){
								Vector temp=new Vector();
								temp.add(t1.elementAt(i));
								NitelikDegeri nitelik=(NitelikDegeri)t2.elementAt(i);
								temp.add(nitelik.getNitelikDegeri());
								((AyniyatTableModel)getNiteliklerTable().getModel()).addRow2LastRow(temp);
							}
						
					}else{
					    Object[] options = {"Tamam"};
					  	JOptionPane.showOptionDialog(NitelikDegistirme.this,
					   	        getDemirbasNoTextField().getText()+" numaralý demirbaþ bulunamadý. ",
								"Uyarý",JOptionPane.YES_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);
						degistirButton.setEnabled(false);
					}					
				}
			});
		}
		return bulButton;
	}
	private JTextField getTanimTextField() {
		if (tanimTextField == null) {
			tanimTextField = new JTextField();
			tanimTextField.setBounds(new java.awt.Rectangle(150,70,120,25));
		}
		return tanimTextField;
	}

	private JButton getDegistirButton() {
		if (degistirButton == null) {
			degistirButton = new JButton();
			degistirButton.setBounds(new java.awt.Rectangle(300,40,100,25));
			degistirButton.setText("Deðiþtir");
			degistirButton.setEnabled(false);
			degistirButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int rowcount=getNiteliklerTable().getModel().getRowCount();
					
					for(int j=0;j<rowcount;j++){
						NitelikDegeri nitelik=new NitelikDegeri(getNiteliklerTable().
								getModel().getValueAt(j,0).toString(),
								getNiteliklerTable().getModel().getValueAt(j,1).toString());
						nitelik.setOwningMalzeme(demirbasMalzeme);
						temp.add(nitelik);
					}
					MalzemeManager.updateMalzeme(demirbasMalzeme,temp);
					NitelikDegistirme.this.dispose();
				}	
			});
		}
		return degistirButton;
	}

	private JPanel getJPanel1(){
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(new java.awt.Rectangle(1,121,441,142));			jPanel1.add(getDegistirButton(), null);
			jPanel1.add(getNiteliklerScrollPane(), null);
			}
		return jPanel1;
	}

	private JScrollPane getNiteliklerScrollPane() {
		if (nitelikScrollPane == null) {
			nitelikScrollPane = new JScrollPane();
			nitelikScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Nitelik Tanýmlarý", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
			nitelikScrollPane.setBounds(new java.awt.Rectangle(-2,0,291,145));
			nitelikScrollPane.setViewportView(getNiteliklerTable());
		}
		return nitelikScrollPane;
	}
	private JTable getNiteliklerTable() {
		if (niteliklerTable == null) {
		    NiteliklerTableModel ntm = new NiteliklerTableModel(new String[] {"Nitelik Adý","Nitelik Deðeri"});
		    niteliklerTable = new JTable(ntm);
		    
	           //  Determine editor to be used by row
			niteliklerTable.setBounds(new java.awt.Rectangle(8,12,264,116));
			niteliklerTable.setCellSelectionEnabled(true);
			niteliklerTable.setRowHeight(20);
			
		}
		return niteliklerTable;
	}

	
}  
