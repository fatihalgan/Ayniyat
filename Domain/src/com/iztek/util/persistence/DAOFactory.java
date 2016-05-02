package com.iztek.util.persistence;

import com.iztek.ayniyat.kullanici.IKullaniciDAO;
import com.iztek.ayniyat.malzemehareketleri.persistence.IBelgeNoGeneratorDAO;
import com.iztek.ayniyat.malzemehareketleri.persistence.IDemirbasNoGeneratorDAO;
import com.iztek.ayniyat.malzemehareketleri.persistence.IMalzemeDAO;
import com.iztek.ayniyat.malzemehareketleri.persistence.IMalzemeHareketFisiDAO;
import com.iztek.ayniyat.malzemehareketleri.persistence.IMalzemeHareketiDAO;
import com.iztek.ayniyat.malzemetanimi.persistence.IMalzemeTanimiDAO;
import com.iztek.ayniyat.yerlesim.persistence.IYerlesimDAO;

/**
 * @author Cagdas CIRIT
 */
public class DAOFactory {
	public static final String MALZEME_TANIMI = "MalzemeTanimi";
	public static final String YERLESIM = "Yerlesim";
	public static final String MALZEME ="Malzeme";
	public static final String DEMIRBAS_NO ="DemirbasNo";
	public static final String BELGE_NO ="BelgeNo";
	public static final String MALZEME_HAREKETI = "MalzemeHareketi";
	public static final String MALZEME_HAREKET_FISI="MalzemeHareketFisi";
	
	public static IMalzemeTanimiDAO getMalzemeTanimiDAO() {
	    IMalzemeTanimiDAO instance = (IMalzemeTanimiDAO)PluginFactory.getPlugin(IMalzemeTanimiDAO.class);
	    return instance;
	}
	
	public static IYerlesimDAO getYerlesimDAO() {
		IYerlesimDAO instance = (IYerlesimDAO)PluginFactory.getPlugin(IYerlesimDAO.class);
	    return instance;
	}
	
	public static IDemirbasNoGeneratorDAO getDemirbasNoGenDAO() {
		IDemirbasNoGeneratorDAO instance = (IDemirbasNoGeneratorDAO)PluginFactory.getPlugin(IDemirbasNoGeneratorDAO.class);
		return instance;
	}
	
	public static IBelgeNoGeneratorDAO getBelgeNoGenDAO() {
		IBelgeNoGeneratorDAO instance = (IBelgeNoGeneratorDAO)PluginFactory.getPlugin(IBelgeNoGeneratorDAO.class);
		return instance;
	}
	
	public static IMalzemeDAO getMalzemeDAO(){
	    IMalzemeDAO instance = (IMalzemeDAO)PluginFactory.getPlugin(IMalzemeDAO.class);
        return instance;
	}
	
	public static IMalzemeHareketiDAO getMalzemeHareketiDAO(){
	    IMalzemeHareketiDAO instance = (IMalzemeHareketiDAO)PluginFactory.getPlugin(IMalzemeHareketiDAO.class);
        return instance;
	}
	
	public static IMalzemeHareketFisiDAO getMalzemeHareketFisiDAO(){
	    IMalzemeHareketFisiDAO instance = (IMalzemeHareketFisiDAO)PluginFactory.getPlugin(IMalzemeHareketFisiDAO.class);
        return instance;
	}
	
	public static IKullaniciDAO getKullaniciDAO(){
	    IKullaniciDAO instance = (IKullaniciDAO)PluginFactory.getPlugin(IKullaniciDAO.class);
        return instance;
	}
}
