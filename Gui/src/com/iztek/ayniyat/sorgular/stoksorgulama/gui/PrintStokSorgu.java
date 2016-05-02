package com.iztek.ayniyat.sorgular.stoksorgulama.gui;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import com.iztek.ayniyat.malzemehareketleri.fis.RaporlarConfigUtil;
import com.iztek.ayniyat.malzemehareketleri.fis.ReportViewerDialog;

public class PrintStokSorgu {
private static final String FILE ="stokSorgu";
	
	public static void prepareReport(Collection data) throws JRException, IOException {		
/**
 * @author fusun
 */   
		String COMPILE_DIR =System.getProperty("user.dir")+"/";
        File reportFile;
		reportFile = new File(RaporlarConfigUtil.getJasperFilePath(COMPILE_DIR, FILE + ".jasper"));
		 
		RaporlarConfigUtil.compileReport(COMPILE_DIR, FILE);
		Map parameters = new HashMap(); 
	    RaporlarConfigUtil.fillReporttoFile(reportFile.getPath(),null,new StokSorguDataSource(data));
	    new ReportViewerDialog( COMPILE_DIR+FILE +".jrprint").setVisible(true);
	}
}

