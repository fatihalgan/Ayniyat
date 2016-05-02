package com.iztek.ayniyat.malzemehareketleri.fis;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;

import net.sf.jasperreports.engine.JRException;

public class PrintZimmetSorgulama {
//	private static final String FILE = PluginFactory.getParamforFisler("hareketFisi_report");
	
	public static void prepareReport(Collection demirbaslar,String file,String node) throws JRException, IOException {		
/**
 * @author fusun
 */   
		String COMPILE_DIR =System.getProperty("user.dir")+"/";
        File reportFile;
		reportFile = new File(RaporlarConfigUtil.getJasperFilePath(COMPILE_DIR, file+ ".jasper"));
		 
		RaporlarConfigUtil.compileReport(COMPILE_DIR, file);
		Map parameters = new HashMap(); 
		ZimmetSorgulamaParameters.commonParameters(parameters,node,demirbaslar.size());
	   // HareketFisiParemeters.commonParameters(parameters,hareketFisi);
	    RaporlarConfigUtil.fillReporttoFile(reportFile.getPath(),parameters,new ZimmetSorgulamaDatasource(demirbaslar));
	    new ReportViewerDialog( COMPILE_DIR+file +".jrprint").setVisible(true);
	}
}