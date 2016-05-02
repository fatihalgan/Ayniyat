package com.iztek.ayniyat.sorgular.ambaraDevir.gui;

import java.text.NumberFormat;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemePahasi;
import com.iztek.ayniyat.model.treetable.DynamicTreeTableModel;
import com.iztek.ayniyat.model.treetable.TreeTableNode;
import com.iztek.commons.money.Money;

public class FisMalzemeleriTableModel extends DynamicTreeTableModel {
    private String chooserColumn;    
    
	public FisMalzemeleriTableModel(TreeTableNode root,String[] demirbasTablecolumnNames,Class[] demirbasTableClassTypes) {
		super(root,demirbasTablecolumnNames,null,null,demirbasTableClassTypes);
		chooserColumn = demirbasTablecolumnNames[1];
	}
	
	public Object getValueAt(Object node, int column) {
		if(node == null) return null;
		NumberFormat numberFormat = NumberFormat.getInstance();
		TreeTableNode tn = (TreeTableNode) node;
		
		if(tn.isLeaf()){
			DemirbasMalzeme demirbas = (DemirbasMalzeme) tn.getUserObject();
			if(demirbas != null){
				//TODO bu paha islemi kaldirilcak, pahasý set edilmeyen datalar icin kondu
	  			MalzemePahasi paha = demirbas.getPaha();
				if (paha==null){
					paha = new MalzemePahasi(); 
					paha.setVergisizBirimFiyat(Money.yeniTurkLirasi(0));
					paha.setKdvOrani(new Float(0));
					demirbas.setPaha(paha);
				}
			    switch (column) {
				    case 0:
				        return demirbas.getDemirbasNo();
				    case 1:
				        return demirbas.getMalzemeTanimi().getNodeValue();
				    case 2:
				        return numberFormat.format(paha.getVergisizBirimFiyatMiktar())+" "+paha.getVergisizBirimFiyatKur();
			        case 3:
			            return new Float(paha.getAsilOran()*100);
				    default:
		    		    break;
	    	    }
	    	}
	    }else{
	    	  AbstractMalzemeHareketi hareket = (AbstractMalzemeHareketi)tn.getUserObject();
	  
	    		if(hareket != null) {
				switch (column) {
					case 0:
						return hareket.getHareketFisi().getBelgeNo().getBelgeNo();
					case 1:
					    if (chooserColumn.equals("Gönderilen Ambar"))
					        return hareket.getHareketHedefi().getHareketYeri().getTanim();
					    else 
					        return hareket.getHareketKaynagi().getHareketYeri().getTanim();
					case 2:
						return hareket.getGeciciSahip().getHareketYeri().getTanim();
					case 3:
					    return hareket.getHareketTarihi();
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
