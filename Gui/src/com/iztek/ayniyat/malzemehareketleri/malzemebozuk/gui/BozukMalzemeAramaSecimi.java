package com.iztek.ayniyat.malzemehareketleri.malzemebozuk.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.malzemedevri.gui.DevirMalzemeleriSecimi;
import com.iztek.ayniyat.tanimlar.gui.MalzemeTanimlariAnaPanel;
/**
 * @author Cagdas CIRIT
 **/
public class BozukMalzemeAramaSecimi extends DevirMalzemeleriSecimi {

	private JButton tanimiylaButton;

	public BozukMalzemeAramaSecimi(JFrame parent) {
		super(parent);
		super.setSize(277, 190);
		getJContentPane().add(getTanimiylaButton(), null);
	}
  
	private JButton getTanimiylaButton() {
		if (tanimiylaButton == null) {
			tanimiylaButton = new JButton();
			tanimiylaButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/navigate_right2.png")));
			tanimiylaButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
			tanimiylaButton.setText("Tanýmýyla          ");
			tanimiylaButton.setFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 13));
			tanimiylaButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			tanimiylaButton.setBounds(21, 120, 229, 26);
			tanimiylaButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
				    malzemeTanimiDondurAction();
				}
			});
		}
		return tanimiylaButton;
	}
  				
	private void malzemeTanimiDondurAction(){
		dispose();
	    AnaController.getInstance().setCurrentMalzemeTanimlariPanelRole(AnaController.MALZEMETANIMLARI_FOR_DIALOG);
	    AnaController.getInstance().setCurrentTanimlarController(AnaController.getInstance().getMalzemeTanimlariController());
		new MalzemeTanimlariAnaPanel(AnaController.getInstance().getAnaPanel(),"malzeme").setVisible(true);
		}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"