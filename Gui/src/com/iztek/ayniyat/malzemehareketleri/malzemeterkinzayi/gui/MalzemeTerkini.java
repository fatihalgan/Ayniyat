package com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.gui;

import javax.swing.JOptionPane;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.gui.MalzemeTerkinZayi;

/**
 * @author Cagdas CIRIT
 */
public class MalzemeTerkini extends MalzemeTerkinZayi{
	/**
	 * @modified by f�sun
	 */

	public MalzemeTerkini(String panelName) {
		super(panelName);
		AnaController.getInstance().getMalzemeTerkinController().registerMalzemeTerkinZayi(this);
		super.setTitle("Malzeme Terkin Hareketi");
		getTerkinBilgileriPanel().setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Terkin Bilgileri", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
		terkinTarihiLabel.setText("Terkin Tarihi :");
		terkinNedeniLabel.setText("Terkin Nedeni :");
		terkinEdenLabel.setText("Terkin Eden :");
		
		
	}
	
	public boolean isMalzemeTerkinZayi(IDemirbasMalzeme demirbas){
	    if(demirbas.getState().getType().equals(IMalzemeState.TERK�N)){
	        JOptionPane.showMessageDialog(AnaController.getInstance().getAnaPanel(),demirbas+" Nolu demirba� halihaz�rda Terkindir!","Terkin Malzeme Uyar�s�",JOptionPane.WARNING_MESSAGE);
	        return true;
	    }
	    return false;
	}

	
	public boolean cikisIslemiOnayiAl(){
	    return AnaController.getInstance().showConfirmationDialogBox(this,"Terkin i�lemini onayl�yor musunuz?");   
	}
	 public void kaydetAction(){
		    //Gerekli bilgiler mevcut mu
		    if(!gerekliBilgilerMevcutOnayiAl()){
		        AnaController.getInstance().showWarningDialogBox(AnaController.getInstance().getAnaPanel(),"L�tfen gerekli bilgileri giriniz!");
		        return;
		    }
		    //kay�t yapmak icin onay al
		    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Terkin i�lemini onayl�yor musunuz?");
		    if(!onay){
		        return;
		    }
		    
		    AnaController.getInstance().getMalzemeTerkinController().malzemelerinHareketiniGerceklestir();
			unLoadYourself();
	    }
 
}
