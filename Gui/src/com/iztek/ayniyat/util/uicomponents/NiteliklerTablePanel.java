package com.iztek.ayniyat.util.uicomponents;

import java.awt.Component;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.iztek.ayniyat.malzemetanimi.domain.NitelikTanimi;
import com.iztek.ayniyat.model.AyniyatTableModel;

public class NiteliklerTablePanel extends JPanel {

	private JScrollPane jScrollPane=null;
	private JTable niteliklerTable=null;
	private Vector cellEditors;
	private Vector cellRenderers;
	
	
	public NiteliklerTablePanel() {
		super();
		initialize();
	}
	public void initialize()  {     
		this.setLayout(null);
		this.setBounds(new java.awt.Rectangle(0,0,405,156));
		this.setBounds(21, 54, 405, 156);
		this.add(getJScrollPane(), null);
		this.add(getJScrollPane(), null);
		cellEditors = new Vector();
		cellRenderers = new Vector();
		
	}
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Nitelikler", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
			jScrollPane.setViewportView(getNiteliklerTable());
			jScrollPane.setBounds(2, 3, 397, 146);
		}
		return jScrollPane;
	}
	public JTable getNiteliklerTable() {
		if (niteliklerTable == null) {
		    NitelikTableModel ntm = new NitelikTableModel(new String[] {"Nitelik Adý","Nitelik Deðeri"});
			niteliklerTable = new JTable(ntm) {
	            //  Determine editor to be used by row
	           public TableCellEditor getCellEditor(int row, int column)
	            {
	                if (column == 1)
	                {
	                    return (TableCellEditor)cellEditors.get(row);
	                }
	                else
	                    return super.getCellEditor(row, column);
	            }
	            
	     /*       public TableCellRenderer getCellRenderer(int row, int column) {
	                if (column == 1)
	                {
	                    return (TableCellRenderer)cellRenderers.get(row);
	                }
	                else
	                    return super.getCellRenderer(row, column);
	            }*/
	        };			
	        //niteliklerTable.setBounds(new java.awt.Rectangle(1,1,405,156));    
		}
		return niteliklerTable;
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
	/*
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
    }*/
	public void loadNiteliklerTable(HashMap nitelikHashMap, Iterator tanimlar){
	    
	    while (tanimlar.hasNext()) {
            NitelikTanimi tanim = (NitelikTanimi) tanimlar.next();
            Vector nitelikDegerleri = new Vector((Collection) nitelikHashMap.get(tanim.getNitelikAdi()));
            nitelikDegerleri.insertElementAt("",0);           
            JComboBox comboBox = new JComboBox( nitelikDegerleri );
            comboBox.setEditable(true);
            DefaultCellEditor dce = new DefaultCellEditor( comboBox );
   //         NitelikComboBoxRenderer ncbr = new NitelikComboBoxRenderer(nitelikDegerleri);
       
            cellEditors.add(dce);
  //          cellRenderers.add(ncbr);
            
            Vector data = new Vector(2);
            data.add(tanim.getNitelikAdi());
            data.add("");
            ((AyniyatTableModel)getNiteliklerTable().getModel()).addRow2LastRow(data);
        }
	}

	
}  //  @jve:decl-index=0:visual-constraint="-320,-9"
