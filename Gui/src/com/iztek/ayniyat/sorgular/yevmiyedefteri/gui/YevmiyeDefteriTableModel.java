package com.iztek.ayniyat.sorgular.yevmiyedefteri.gui;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemeFisPahasi;
import com.iztek.ayniyat.malzemehareketleri.domain.MalzemePahasi;
import com.iztek.ayniyat.model.treetable.DynamicTreeTableModel;
import com.iztek.ayniyat.model.treetable.TreeTableNode;
import com.iztek.commons.money.Money;

public class YevmiyeDefteriTableModel extends DynamicTreeTableModel {
   
	public YevmiyeDefteriTableModel(TreeTableNode root,String[] demirbasTablecolumnNames,Class[] demirbasTableClassTypes) {
		super(root,demirbasTablecolumnNames,null,null,demirbasTableClassTypes);
	}
	
	public Object getValueAt(Object node, int column) {
		if(node == null) return null;
		NumberFormat numberFormat = NumberFormat.getInstance();
		TreeTableNode tn = (TreeTableNode) node;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    String type ="";
	    MalzemeFisPahasi fisPahasi = null;
		
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
				        return "1";//FIXME gruplandýrýlabilir sonra bakýlacak
				    case 3:
				        return demirbas.getMalzemeTanimi().getBirim();
				    case 4:   
				        return numberFormat.format(paha.getVergisizBirimFiyatMiktar())+" "+paha.getVergisizBirimFiyatKur();
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
					    Date tarih = hareket.getHareketTarihi();
					    if(tarih!=null)
					        return dateFormat.format(tarih);
					    else
					        return "";
					case 2:
					    return dateFormat.format(hareket.getHareketFisi().getDuzenlemeTarihi());
					case 3:
					    fisPahasi = hareket.getHareketFisi().getPaha();
				        return numberFormat.format(fisPahasi.getGenelToplamTutari().getAmount())+" "+fisPahasi.getGenelToplamTutari().getCurrency();
					case 4:
					    if(tn.isSelected())
					        type = "Giriþ";
					    else
					        type = "Çýkýþ";
					    return type;
					default:
						break;
				}
			}
		}
		return null;
	}
	
	public boolean isCellEditable(Object node, int column) {
	    if(column == 0)
	        return true;
	   return false;
	}
	
	
	public void setValueAt(Object aValue, Object node, int column) {}
}
