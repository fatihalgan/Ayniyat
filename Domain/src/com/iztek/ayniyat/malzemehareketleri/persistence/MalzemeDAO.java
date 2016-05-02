package com.iztek.ayniyat.malzemehareketleri.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi;
import com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzeme;
import com.iztek.ayniyat.malzemehareketleri.domain.IMalzemeState;
import com.iztek.ayniyat.malzemehareketleri.domain.NitelikDegeri;
import com.iztek.ayniyat.malzemehareketleri.util.BelgeNoGenerator;
import com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.DemirbasMalzemeTanimi;
import com.iztek.ayniyat.malzemetanimi.domain.Kategori;
import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Ambar;
import com.iztek.ayniyat.yerlesim.domain.IZimmetAlan;
import com.iztek.util.persistence.AyniyatTableModelData;
import com.iztek.util.persistence.HibernateUtil;
import com.iztek.util.persistence.InfrastructureException;

/**
 * @author Umit Akyol + Cagdas CIRIT + Sevgi USLU
 *
*/
public class MalzemeDAO implements IMalzemeDAO {
    
    public IMalzeme getMalzemeById(Long id){
        IMalzeme malzeme = null;
        try {
            Session session = HibernateUtil.getSession();
            malzeme = (IMalzeme) session.get(AbstractMalzeme.class, id, LockMode.UPGRADE);
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return malzeme;
    }
    
    public IDemirbasMalzeme findDemirbasByDemirbasNo(String demirbasNo){
        IDemirbasMalzeme returnVal = null;
    	String query = "from com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme as demirbas " +
    			"where demirbas.demirbasNo=:demirbasNo";    	
        try {
            Session session = HibernateUtil.getSession();
            returnVal =(IDemirbasMalzeme) session.createQuery(query).setParameter("demirbasNo",demirbasNo).uniqueResult();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
    public Collection getNitelikTaniminaAitFarkliDegerler(AbstractMalzemeTanimi malzemeTanimi, String nitelikAdi){
        Collection returnVal = null;
    	String query = "select distinct nitelikler.nitelikDegeri from com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme demirbas " +
    			"join demirbas.nitelikDegerleri nitelikler " +
    			"where demirbas.malzemeTanimi=:malzemeTanimi and " +
    			"nitelikler.nitelikAdi=:nitelikAdi";
        try {
            Session session = HibernateUtil.getSession();
            returnVal = session.createQuery(query).setParameter("malzemeTanimi",malzemeTanimi).setParameter("nitelikAdi",nitelikAdi).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
    //TODO bu metod DEGISTIRILECEK!!!! simdilik bole.....
    public Collection getDemirbaslarNitelikDegeriBelirlenmis(AbstractMalzemeTanimi malzemeTanimi, HashMap nitelikler, IZimmetAlan currentAmbar){
        Vector returnVal = new Vector();
    	String queryString = "select demirbas from com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme demirbas " +
    			"where demirbas.malzemeTanimi=:malzemeTanimi ";
    	if (currentAmbar!=null)
    	    queryString = queryString+"and demirbas.zimmetSahibi=:ambar";
    	
        try {
            Session session = HibernateUtil.getSession();
            Query query = session.createQuery(queryString).setParameter("malzemeTanimi",malzemeTanimi);
            if (currentAmbar!=null)
                query = query.setParameter("ambar",currentAmbar);
            
            List demirbaslar = query.list();
            
            Iterator iter = demirbaslar.iterator();
            while (iter.hasNext()) {
                boolean suzgectenGecti = true;
                DemirbasMalzeme demirbas = (DemirbasMalzeme) iter.next();
                Iterator innerIter = nitelikler.keySet().iterator();
                while (innerIter.hasNext()) {
                    String nitelikAdi = (String) innerIter.next();
                    String nitelikDegeri = (String) nitelikler.get(nitelikAdi);
                    if (!nitelikDegeri.equals("")){
                        NitelikDegeri nitelik = new NitelikDegeri(nitelikAdi,nitelikDegeri);
                        if (!demirbas.hasInNitelikDegerleri(nitelik)){
                            suzgectenGecti = false;
                            break;
                        }
                    }
                }
                
                if(suzgectenGecti){
                    returnVal.add(demirbas);
                    iter.remove();
                }                   
            }
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
     
    public Collection getDemirbaslarByState(IMalzemeState state){
        Collection returnVal = null;
    	String query = "from com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme as malzeme " +
		"where malzeme.state=:state";  	
        try {
            Session session = HibernateUtil.getSession();
            returnVal =(Collection) session.createQuery(query).setParameter("state",state.getType()).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
    public Collection getDemirbaslarByMalzemeTanimi(AbstractMalzemeTanimi malzemeTanimi){
        Collection returnVal = null;
    	String queryString = "select demirbas from com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme demirbas " +
    			"where demirbas.malzemeTanimi=:malzemeTanimi ";
        try {
            Session session = HibernateUtil.getSession();
            returnVal =(Collection) session.createQuery(queryString).setParameter("malzemeTanimi",malzemeTanimi).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
    public Collection getZimmetliDemirbaslarByMalzemeTanimi(AbstractMalzemeTanimi malzemeTanimi){
        Collection returnVal = null;
    	String queryString = "select demirbas from com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme demirbas " +
    			"where demirbas.malzemeTanimi=:malzemeTanimi and demirbas.state='Zimmetli' ";
    
        try {
            Session session = HibernateUtil.getSession();
            returnVal =(Collection) session.createQuery(queryString).setParameter("malzemeTanimi",malzemeTanimi).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
    public Collection getMalzemeTanimlariVisibleToKullanici(String kullanici){
        Collection returnVal = null;
    	String queryString = "select malzemeTanimi from com.iztek.ayniyat.malzemetanimi.domain.AbstractMalzemeTanimi malzemeTanimi "
    	    +"join malzemeTanimi.malzemeler malzemeler where malzemeler in (from com.iztek.ayniyat.malzemehareketleri.domain.DemirbasMalzeme demirbas " +
			"where demirbas.zimmetSahibi in (select ambar from com.iztek.ayniyat.kullanici.Kullanici kullanici "+
    		"join kullanici.ambarlar ambar where kullanici.userName =:userName))"; 
        try {
            Session session = HibernateUtil.getSession();
            returnVal =(Collection) session.createQuery(queryString).setParameter("userName",kullanici).list();
        } catch(HibernateException he) {
            throw new InfrastructureException(he.getMessage());
        }
        return returnVal;
    }
    
    /**
     * Füsun Çetin
     */
    
    public Collection bozukMalzemeSorgulama(){
    	
    	Collection returnVal=null;
    	String queryString="select malzeme.demirbasNo,malzeme.malzemeTanimi,onayveren,"+
		"hareketfisi.bozulmaNedeni,hareketfisi.bozulusTarihi"+
    	" from com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme malzeme " +
		"join malzeme.malzemeHareketleri malzemehareketi " +
		"join malzemehareketi.hareketFisi hareketfisi " +
		"join hareketfisi.onayVeren onayveren " +
		"where malzeme.state='Bozuk' and hareketfisi.class=MalzemeBozukFisi";
    	try{
    		Session session=HibernateUtil.getSession();
    		returnVal=session.createQuery(queryString).list();
    	 } catch(HibernateException he) {
             throw new InfrastructureException(he.getMessage());
         }
    	 
    	 return returnVal;
    }	
    public Collection zayiMalzemeSorgulama() {
    	
    	Collection returnVal=null;
    	String queryString="select malzeme.demirbasNo,malzeme.malzemeTanimi," +
    	"hareketfisi.hareketBilgileri.tutanakNo,hareketfisi.hareketBilgileri.tutanakTarihi," +
    	"hareketfisi.hareketBilgileri.zayi_terkinNedeni," +
    	"hareketfisi.hareketBilgileri.zayi_terkinEden," +
    	"hareketfisi.hareketBilgileri.aciklama"+
    	" from com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme malzeme " +
    	"join malzeme.malzemeHareketleri malzemehareketi " +
		"join malzemehareketi.hareketFisi hareketfisi " +
		"where malzeme.state='Zayi' and hareketfisi.class=MalzemeZayiFisi";
    	try{
    		Session session=HibernateUtil.getSession();
    		returnVal=session.createQuery(queryString).list();
    	 } catch(HibernateException he) {
             throw new InfrastructureException(he.getMessage());
         }
    	 
    	 return returnVal;
    }	
public Collection terkinMalzemeSorgulama() {
    	
    	Collection returnVal=null;
    	String queryString="select malzeme.demirbasNo,malzeme.malzemeTanimi," +
    	"hareketfisi.hareketBilgileri.tutanakNo,hareketfisi.hareketBilgileri.tutanakTarihi," +
    	"hareketfisi.hareketBilgileri.zayi_terkinNedeni," +
    	"hareketfisi.hareketBilgileri.zayi_terkinEden," +
    	"hareketfisi.hareketBilgileri.aciklama"+
    	" from com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme malzeme " +
    	"join malzeme.malzemeHareketleri malzemehareketi " +
		"join malzemehareketi.hareketFisi hareketfisi " +
		"where malzeme.state='Terkin' and hareketfisi.class=MalzemeTerkinFisi";
    	try{
    		Session session=HibernateUtil.getSession();
    		returnVal=session.createQuery(queryString).list();
    	 } catch(HibernateException he) {
             throw new InfrastructureException(he.getMessage());
         }
    	 
    	 return returnVal;
    }	
    public Collection ambarStokSorgulama(Ambar ambar){
    	
    	Vector returnVal=new Vector();
    	Collection data=null;
    	String queryString="select zimmetalan.tanim, kategori.kod, kategori.tanim,count(kategori.tanim),kategori.birim " +
		"from com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan zimmetalan," +
		"com.iztek.ayniyat.malzemetanimi.domain.Kategori kategori," +
		"com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme malzeme " +
		"where zimmetalan.tanim=:tanim and malzeme.zimmetSahibi=zimmetalan " +
		"and malzeme.state=:state and malzeme.malzemeTanimi.id=kategori.id group by kategori.tanim,kategori.birim," +
		"kategori.kod ,zimmetalan.tanim" ;	
    	
    	String state="Stokta";
    	String tanim=ambar.getTanim();
    	
    	try{
    		Session session=HibernateUtil.getSession();
    		data=session.createQuery(queryString).setString("state",state).setString("tanim",tanim).list();
    	 } catch(HibernateException he) {
             throw new InfrastructureException(he.getMessage());
         }
    
    	
    	 for(int j=0;j<data.size();j++){ 			
    		Object[] obj=(Object[])data.toArray()[j];  
 			AyniyatTableModelData tableData=new AyniyatTableModelData();
			tableData.setAmbarTanimi((String)obj[0]); 
			tableData.setKod((String)obj[1]);				
			tableData.setTanim((String)obj[2]);
			tableData.setAdet((Integer)obj[3]);
			tableData.setBirim((String)obj[4]);
			returnVal.add(tableData);
 			}
    	 
        return returnVal;
    }
 
   public Collection getTransferMalzemeHareketleri(IZimmetAlan hedefZimmetAlan,IZimmetAlan kaynakZimmetAlan){
	   boolean arananHareket = false; 
       HashMap returnVal = new HashMap();
	   String queryString = "select malzeme from com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzeme malzeme " +
	    		"where malzeme.state=:state ";
        
	    try {
	        Session session = HibernateUtil.getSession();
            
	        Iterator iter = session.createQuery(queryString).setParameter("state","Askida").list().iterator();
	        while (iter.hasNext()) {
                AbstractMalzeme malzeme = (AbstractMalzeme) iter.next();
                BelgeNoGenerator belgeNo = (BelgeNoGenerator) session.createFilter(malzeme.getMalzemeHareketleri(),"select max(this.hareketFisi.belgeNo)").uniqueResult();
                AbstractMalzemeHareketi hareket = (AbstractMalzemeHareketi) session.createQuery("from com.iztek.ayniyat.malzemehareketleri.domain.AbstractMalzemeHareketi hareket " +
                       							"where hareket.hareketFisi.belgeNo=:belgeNo").setParameter("belgeNo",belgeNo.getBelgeNo()).uniqueResult();
                
                if(hedefZimmetAlan!=null && kaynakZimmetAlan!=null)
                    arananHareket = ((AbstractZimmetAlan)hareket.getHareketHedefi().getHareketYeri()).getId().equals(((AbstractZimmetAlan)hedefZimmetAlan).getId())&&((AbstractZimmetAlan)hareket.getHareketKaynagi().getHareketYeri()).getId().equals(((AbstractZimmetAlan)kaynakZimmetAlan).getId()); 
                else{
	                if (hedefZimmetAlan!=null)
	                    arananHareket = ((AbstractZimmetAlan)hareket.getHareketHedefi().getHareketYeri()).getId().equals(((AbstractZimmetAlan)hedefZimmetAlan).getId());
	                if (kaynakZimmetAlan!=null)
	                    arananHareket = ((AbstractZimmetAlan)hareket.getHareketKaynagi().getHareketYeri()).getId().equals(((AbstractZimmetAlan)kaynakZimmetAlan).getId());
                }
                
                if (arananHareket)
                    returnVal.put(belgeNo,hareket);
            }
	    } catch(HibernateException he) {
	        throw new InfrastructureException(he.getMessage());
	    }    
	    
	    return returnVal.values();
    }
   
   //Stok sorgulama Tablosu için
   public Collection getMalzemeTanimi(AyniyatTableModelData tableData){
	   
	  Collection returnVal=null;
	   try {
		   Session session = HibernateUtil.getSession();
		   IKategorilendirilebilir tanim = new DemirbasMalzemeTanimi(tableData.getTanim(),tableData.getKod(),
				   tableData.getBirim());
		   Example example=Example.create(tanim);
		   example.excludeNone().excludeZeroes();
		   returnVal = session.createCriteria(Kategori.class)
		   .add(example)
		   .list();
	   } catch(HibernateException he) {
           throw new InfrastructureException(he.getMessage());
       }
	   
	   return returnVal;
   }
   
   
}
