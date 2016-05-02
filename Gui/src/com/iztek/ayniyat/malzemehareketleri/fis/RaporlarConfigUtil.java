/*
 * Created on 04.Mar.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.iztek.ayniyat.malzemehareketleri.fis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;


/**
 * @author db2admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RaporlarConfigUtil {
	
	
	
	public static boolean compileReport(String compileDir, String filename) throws JRException {
		String jasperFileName = (compileDir + filename +".jasper");
		String xmlFileName;
		File jasperFile = new File(jasperFileName);
		if(jasperFile.exists()){  
			return true; // jasper file already exists, do not compile again
		}
		try {
			// jasper file has not yet been constructed , so compile the xml file
			xmlFileName = jasperFileName.substring(0, jasperFileName.indexOf(".jasper"))+".jrxml";			
			JasperCompileManager.compileReportToFile(xmlFileName);
			return true;
		}catch(Exception e) {
			 System.out.println("Compile Error: "+e.getMessage());
			 return false;
		}
	}
	
	public static String getJasperFilePath(String compileDir, String jasperFile) {
		return (compileDir + jasperFile);
	}
	
	public static byte[] getReport(File reportFile,Map parameters, JRDataSource ds) throws JRException {
		byte[] reportContent=JasperRunManager.runReportToPdf(reportFile.getPath(),parameters,ds);
		return reportContent;
	}
	
	public static void writeReport(byte[] reportContent) throws IOException {
		OutputStream out = new FileOutputStream("fis.pdf");
		out.write(reportContent);
		out.flush();
		out.close();
	}
	

	// Fatih

	public static void fillReport(JasperReport jasperReport, Map parameters, JRDataSource ds) throws IOException {
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,ds ); // fill the MasterReport
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
public static void fillReporttoFile(String file, Map parameters, JRDataSource ds) throws IOException {
		
		try {
			 JasperFillManager.fillReportToFile(file, parameters,ds ); // fill the MasterReport
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	
	public static JasperReport compileStringReport(String compileDir, String filename) throws JRException {
		String jasperFileName = (compileDir + filename +".jasper");
		String xmlFileName;
		File jasperFile = new File(jasperFileName);
		
	//	if(jasperFile.exists()){  
	//		return null; // jasper file already exists, do not compile again
	//	}
		
		try {
			// jasper file has not yet been constructed , so compile the xml file
			xmlFileName = jasperFileName.substring(0, jasperFileName.indexOf(".jasper"))+".xml";			

			JasperReport subRepPos = null;
			JasperCompileManager.compileReportToFile(xmlFileName);
			subRepPos = JasperCompileManager.compileReport(xmlFileName); 
			return subRepPos;
		}catch(Exception e) {
			 System.out.println("Compile Error: "+e.getMessage());
			 return null;
		}
	}
}
