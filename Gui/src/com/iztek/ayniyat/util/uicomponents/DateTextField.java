package com.iztek.ayniyat.util.uicomponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

public class DateTextField extends JPanel{
	private JXDatePicker datePicker ;
	private JLabel labelDate;
	 public DateTextField() {
        super();
        initialize();
    }
	 private void initialize() {
		  this.setLayout(null);
		  this.setSize(new Dimension(128, 25));
		 datePicker = new JXDatePicker();
	
		 datePicker.setBounds(new Rectangle(2, 0, 113, 19));
		 datePicker.setFormats(new String[] { "dd/MM/yyyy" });
		 labelDate = new JLabel();
		 this.add(datePicker, BorderLayout.NORTH);
		this.add(labelDate, BorderLayout.SOUTH);
		 datePicker.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent arg0) {
		 labelDate.setText("Selected date: " + datePicker.getDate());
		 }
		 });
	 }
	 public Date getDate(){
		 return datePicker.getDate();
	 }
	 public void setEnabled(boolean value){
		 datePicker.setEnabled(value);
	 }
}  //  @jve:decl-index=0:visual-constraint="10,4"
