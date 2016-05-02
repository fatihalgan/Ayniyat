package com.iztek.ayniyat.util.uicomponents;

import javax.swing.JMenuItem;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.tanimlar.controller.YerlesimController;
import com.iztek.ayniyat.util.uiservice.MenuItemNameFactory;
import com.iztek.ayniyat.util.uiservice.PanelFactory;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Personel;

public class YerlesimMenuler extends PopUpMenuler {

	private JMenuItem birimEkleMenuItem = null;
    private JMenuItem binaEkleMenuItem = null;
    private JMenuItem odaEkleMenuItem = null;
    private JMenuItem ambarEkleMenuItem = null;
    private JMenuItem personelEkleMenuItem = null;
	private JMenuItem demirbaslariGoruntuleMenuItem = null;
  
	public YerlesimMenuler() {
		super();
	        // create menuItems and than they will load themselves to menuItemsList
	        getSecMenuItem();
	        getBirimEkleMenuItem();
	        getAmbarEkleMenuItem();
	        getBinaEkleMenuItem();
	        getOdaEkleMenuItem();
	        getPersonelEkleMenuItem();
			getYapistirMenuItem();
			getDemirbaslariGoruntuleMenuItem();
		
	}
	  private JMenuItem getBirimEkleMenuItem() {
	        if (birimEkleMenuItem == null) {
	            birimEkleMenuItem = new JMenuItem();
	            birimEkleMenuItem.setText(MenuItemNameFactory.BIRIM_EKLE);
	            menuItems.add(birimEkleMenuItem);
	            birimEkleMenuItem.addActionListener(new java.awt.event.ActionListener() { 
	            	public void actionPerformed(java.awt.event.ActionEvent e) {    
	            		treePanel.zimmetAlanEklePaneliniAc(PanelFactory.BIRIMGIRISPANEL);
	            	}
	            });
	        }
	        return birimEkleMenuItem;
	    }
	    
	    private JMenuItem getBinaEkleMenuItem() {
	        if (binaEkleMenuItem == null) {
	            binaEkleMenuItem = new JMenuItem();
	            binaEkleMenuItem.setText(MenuItemNameFactory.BINA_EKLE);
	            menuItems.add(binaEkleMenuItem);
	            binaEkleMenuItem.addActionListener(new java.awt.event.ActionListener() { 
	            	public void actionPerformed(java.awt.event.ActionEvent e) {    
	            		treePanel.zimmetAlanEklePaneliniAc(PanelFactory.BINAGIRISPANEL);
	            	}
	            });
	        }
	        return binaEkleMenuItem;
	    }
	    
	    private JMenuItem getOdaEkleMenuItem() {
	        if (odaEkleMenuItem == null) {
	            odaEkleMenuItem = new JMenuItem();
	            odaEkleMenuItem.setText(MenuItemNameFactory.ODA_EKLE);
	            menuItems.add(odaEkleMenuItem);
	            odaEkleMenuItem.addActionListener(new java.awt.event.ActionListener() { 
	            	public void actionPerformed(java.awt.event.ActionEvent e) {    
	            		treePanel.zimmetAlanEklePaneliniAc(PanelFactory.ODAGIRISPANEL);
	            	}
	            });
	        }
	        return odaEkleMenuItem;
	    }
	    
	    private JMenuItem getAmbarEkleMenuItem() {
	        if (ambarEkleMenuItem == null) {
	            ambarEkleMenuItem = new JMenuItem();
	            ambarEkleMenuItem.setText(MenuItemNameFactory.AMBAR_EKLE);
	            menuItems.add(ambarEkleMenuItem);
	            ambarEkleMenuItem.addActionListener(new java.awt.event.ActionListener() { 
	            	public void actionPerformed(java.awt.event.ActionEvent e) {    
	            		treePanel.zimmetAlanEklePaneliniAc(PanelFactory.AMBARGIRISPANEL);
	            	}
	            });
	        }
	        return ambarEkleMenuItem;
	    }
	    
	    private JMenuItem getPersonelEkleMenuItem() {
	        if (personelEkleMenuItem == null) {
	            personelEkleMenuItem = new JMenuItem();
	            personelEkleMenuItem.setText(MenuItemNameFactory.PERSONEL_EKLE);
	            menuItems.add(personelEkleMenuItem);
	            personelEkleMenuItem.addActionListener(new java.awt.event.ActionListener() { 
	            	public void actionPerformed(java.awt.event.ActionEvent e) {    
	            		treePanel.zimmetAlanEklePaneliniAc(PanelFactory.PERSONELGIRISPANEL);
	            	}
	            });
	        }
	        return personelEkleMenuItem;
	    }

	    private JMenuItem getDemirbaslariGoruntuleMenuItem() {
			if (demirbaslariGoruntuleMenuItem == null) {
				demirbaslariGoruntuleMenuItem = new JMenuItem();
				demirbaslariGoruntuleMenuItem.setText(MenuItemNameFactory.DEMIRBAS_GORUNTULE);
				menuItems.add(demirbaslariGoruntuleMenuItem);
				demirbaslariGoruntuleMenuItem.addActionListener(new java.awt.event.ActionListener() { 
					public void actionPerformed(java.awt.event.ActionEvent e) {    
						((YerlesimController)AnaController.getInstance().getCurrentTanimlarController()).demirbasTablosunuTemizle();
						if(treePanel.getSeciliNode() instanceof Personel) {
							((YerlesimController)AnaController.getInstance().getCurrentTanimlarController()).personelUzerindekiTumDemirbaslariTabloyaYukle((Personel)treePanel. getSeciliNode());
						}
						else{
							((YerlesimController)AnaController.getInstance().getCurrentTanimlarController()).zimmetAlanAltindakiTumDemirbaslariTabloyaYukle((AbstractZimmetAlan)treePanel.getSeciliNode());
						}
						
					}
				});
			}
			return demirbaslariGoruntuleMenuItem;
		}
		
		public JMenuItem getSecMenuItem() {
			if (secMenuItem==null){
				secMenuItem = new JMenuItem();
				secMenuItem.setText("Seç");
				menuItems.add(secMenuItem);
				secMenuItem.addActionListener(new java.awt.event.ActionListener() { 
					public void actionPerformed(java.awt.event.ActionEvent e) {  
					    if(AnaController.getInstance().getCurrentYerlesimPanelRole().equals(AnaController.YERLESIM_FOR_DIALOG_DEMIRBAS_SEC))
					        AnaController.getInstance().secilenZimmetliMalzemeyiListele((((YerlesimController)AnaController.getInstance().getCurrentTanimlarController()).demirbasTablosundakiSeciliDemirbaslariDondur()));
					    else
							AnaController.getInstance().malzemeCikisPanelindeZimmetAlaniDoldur((IZimmetAlan)treePanel. getSeciliNode());

					    AnaController.getInstance().getCurrentTanimlarController().disposeAnaPanel();
					}
				});
			}
			return secMenuItem;
		}
	   
}
