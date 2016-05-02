package com.iztek.ayniyat.test;

import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasNo;
import com.iztek.ayniyat.malzemehareketleri.util.BelgeNoGenerator;

/**
 * @author Umit Akyol
 *
 */
public class MainTestCaseWithGeneratedNumbers extends MainTestCase {
    private int belgeNo=1;
    private int demirbasNo=1;
    
    protected void setUp() throws Exception {
        super.setUp();
 /*       ((DemirbasMalzeme)masa0).setDemirbasNo(new DemirbasNo(new Long(demirbasNo++)));
        ((DemirbasMalzeme)masa1).setDemirbasNo(new DemirbasNo(new Long(demirbasNo++)));
        ((DemirbasMalzeme)koltuk).setDemirbasNo(new DemirbasNo(new Long(demirbasNo++)));
        ((DemirbasMalzeme)dolap).setDemirbasNo(new DemirbasNo(new Long(demirbasNo++)));
        ((DemirbasMalzeme)yazici).setDemirbasNo(new DemirbasNo(new Long(demirbasNo++)));
        ((DemirbasMalzeme)monitor).setDemirbasNo(new DemirbasNo(new Long(demirbasNo++)));
        ((DemirbasMalzeme)sahipsizMalzeme).setDemirbasNo(new DemirbasNo(new Long(demirbasNo++)));
*/
        ((DemirbasMalzeme)masa0).setDemirbasNo(new DemirbasNo(new String(""+demirbasNo++)));
        ((DemirbasMalzeme)masa1).setDemirbasNo(new DemirbasNo(new String(""+demirbasNo++)));
        ((DemirbasMalzeme)koltuk).setDemirbasNo(new DemirbasNo(new String(""+demirbasNo++)));
        ((DemirbasMalzeme)dolap).setDemirbasNo(new DemirbasNo(new String(""+demirbasNo++)));
        ((DemirbasMalzeme)yazici).setDemirbasNo(new DemirbasNo(new String(""+demirbasNo++)));
        ((DemirbasMalzeme)monitor).setDemirbasNo(new DemirbasNo(new String(""+demirbasNo++)));
        ((DemirbasMalzeme)sahipsizMalzeme).setDemirbasNo(new DemirbasNo(new String(""+demirbasNo++)));
        malzemeGirisFisi.setBelgeNo(new BelgeNoGenerator(new Long(belgeNo++)));
        malzemeCikisFisi1.setBelgeNo(new BelgeNoGenerator(new Long(belgeNo++)));
        malzemeCikisFisi2.setBelgeNo(new BelgeNoGenerator(new Long(belgeNo++)));
        malzemeCikisFisi3.setBelgeNo(new BelgeNoGenerator(new Long(belgeNo++)));
        malzemeDevirFisi0.setBelgeNo(new BelgeNoGenerator(new Long(belgeNo++)));
        malzemeDevirFisi1.setBelgeNo(new BelgeNoGenerator(new Long(belgeNo++)));
        malzemeBozukFisi.setBelgeNo(new BelgeNoGenerator(new Long(belgeNo++)));
        malzemeZayiFisi.setBelgeNo(new BelgeNoGenerator(new Long(belgeNo++)));
    }
    
}
