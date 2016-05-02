package com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.gui;

import javax.swing.JOptionPane;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.gui.MalzemeTerkinZayi;

/**
 * @author Cagdas CIRIT
 */
public class MalzemeZayiati extends MalzemeTerkinZayi{

	
	public MalzemeZayiati(String panelName) {
		super(panelName);
		AnaController.getInstance().getMalzemeZayiatiController().registerMalzemeTerkinZayi(this);
		super.setTitle("Malzeme Zayi Hareketi");
		getTerkinBilgileriPanel().setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Zayiat Bilgileri", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(113,7,113)));
		terkinTarihiLabel.setText("Zayiat Tarihi :");
		terkinNedeniLabel.setText("Zayiat Nedeni :");
		terkinEdenLabel.setText("Zayi Eden :");
	}
	
	public boolean isMalzemeTerkinZayi(IDemirbasMalzeme demirbas){
	    if(demirbas.getState().getType().equals(IMalzemeState.ZAYI)){
	        JOptionPane.showMessageDialog(AnaController.getInstance().getAnaPanel(),demirbas+" Nolu demirbaþ halihazýrda Zayidir!","Zayi Malzeme Uyarýsý",JOptionPane.WARNING_MESSAGE);
	        return true;
	    }
	    return false;
	}

	public boolean cikisIslemiOnayiAl(){
	    return AnaController.getInstance().showConfirmationDialogBox(this,"Zayiat iþlemini onaylýyor musunuz?");   
	}
	 
    public void kaydetAction(){
	    //Gerekli bilgiler mevcut mu
	    if(!gerekliBilgilerMevcutOnayiAl()){
	        AnaController.getInstance().showWarningDialogBox(AnaController.getInstance().getAnaPanel(),"Lütfen gerekli bilgileri giriniz!");
	        return;
	    }
	    //kayýt yapmak icin onay al
	    boolean onay = AnaController.getInstance().showConfirmationDialogBox(AnaController.getInstance().getAnaPanel(),"Terkin iþlemini onaylýyor musunuz?");
	    if(!onay){
	        return;
	    }
	    
	    AnaController.getInstance().getMalzemeZayiatiController().malzemelerinHareketiniGerceklestir();
		unLoadYourself();
    }

 }
