/*
 * Created on 02.Haz.2005
 *
 */
package com.iztek.ayniyat.malzemehareketleri.fis;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JDialog;

import net.sf.jasperreports.engine.JRException;

/**
 * @author Selim HENDRICKSON
 *
 */
public class ReportViewerDialog extends JDialog {

	private javax.swing.JPanel jContentPane = null;

	private JButton jButton = null;
	/**
	 * This is the default constructor
	 */
	public ReportViewerDialog(String reportFileName) {
		super();
		initialize(reportFileName);
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(String reportFileName) {
		this.setTitle("Rapor Önizleme");
		this.setSize(800, 600);
		this.setContentPane(getJContentPane(reportFileName));
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane(String reportFileName) {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getJRViewer(reportFileName), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private Component getJRViewer(String reportFileName) {
        try {
            return new JRViewer(reportFileName,false);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }
 }
