package com.iztek.ayniyat.malzemehareketleri.malzemeterkinzayi.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;
import com.iztek.ayniyat.malzemehareketleri.malzemeGirisi.gui.BozukMalzemeSecimi;
import com.iztek.ayniyat.malzemehareketleri.malzemebozuk.gui.BozukMalzemeAramaSecimi;

public class TerkinZayiAramaSecimi extends BozukMalzemeAramaSecimi {

	private JButton bozuklarArasindanButton = null;
	
	public TerkinZayiAramaSecimi(JFrame parent){
		super(parent);
		super.setSize(277, 250);
		getJContentPane().add(getBozuklarArasindanButton(), null);
		
	}
	private JButton getBozuklarArasindanButton() {
		if (bozuklarArasindanButton == null) {
			bozuklarArasindanButton = new JButton();
			bozuklarArasindanButton.setIcon(new ImageIcon(getClass().getResource("/com/iztek/ayniyat/buttonIcons/navigate_right2.png")));
			bozuklarArasindanButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
			bozuklarArasindanButton.setText("Bozuklar Arasýndan");
			bozuklarArasindanButton.setFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 13));
			bozuklarArasindanButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			bozuklarArasindanButton.setBounds(21, 168, 229, 26);
			bozuklarArasindanButton.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					dispose();
					new BozukMalzemeSecimi(AnaController.getInstance().getAnaPanel()).setVisible(true);
				}
			});
		}
		return bozuklarArasindanButton;
	}
	
}
