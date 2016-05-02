/*
 * Created on 01.Kas.2005
 *
 */
package com.iztek.ayniyat.malzemehareketleri.fis;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketFisi;

/**
 * @author Administrator
 *
 */
public class PrintHareketFisi {

//	private static final String FILE = PluginFactory.getParamforFisler("hareketFisi_report");
	
	public static void prepareReport(AbstractMalzemeHareketFisi hareketFisi,String file,FisParameters fisParameters) throws JRException, IOException {		
/**
 * @author fusun
 */   
		String COMPILE_DIR =System.getProperty("user.dir")+"/";
        File reportFile;
		reportFile = new File(RaporlarConfigUtil.getJasperFilePath(COMPILE_DIR, file+ ".jasper"));
		 
		RaporlarConfigUtil.compileReport(COMPILE_DIR, file);
		Map parameters = new HashMap(); 
		 fisParameters.commonParameters(parameters,hareketFisi);
	   // HareketFisiParemeters.commonParameters(parameters,hareketFisi);
	    RaporlarConfigUtil.fillReporttoFile(reportFile.getPath(),parameters,new HareketFisiDataSource(hareketFisi));
	    new ReportViewerDialog( COMPILE_DIR+file +".jrprint").setVisible(true);
	}
}
