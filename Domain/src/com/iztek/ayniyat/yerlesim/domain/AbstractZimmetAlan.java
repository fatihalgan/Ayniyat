package com.iztek.ayniyat.yerlesim.domain;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collection;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.KategoriComparator;
import com.iztek.ayniyat.kategori.DemirbasMalzemeComparator;
import com.iztek.ayniyat.kategori.service.KategoriMergeStateManager;
import com.iztek.ayniyat.malzemehareketleri.domain.IDemirbasMalzeme;


/**
 * @author Cagdas CIRIT
 */
public abstract class AbstractZimmetAlan implements IKategorilendirilebilir,IZimmetAlan {
    protected Long id;
   protected String tanim;
    protected String kod;
    protected int version;
    protected Set altKategoriler = new TreeSet(new KategoriComparator());
    protected Set zimmetliMalzemeler = new TreeSet(new DemirbasMalzemeComparator());
    protected IKategorilendirilebilir anaKategori;

    public AbstractZimmetAlan() {
    }

    public AbstractZimmetAlan(String tanim) {
        this.tanim = tanim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTanim() {
        return tanim;
    }

    public void setTanim(String tanim) {
        this.tanim = tanim;
    }
	
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void addKategori(IKategorilendirilebilir kategori) {
        if (kategori == null)
            throw new IllegalStateException("Boþ Kategori Eklenemez!");

        if (kategori.getAnaKategori() != null)
            kategori.getAnaKategori().getAltKategoriler().remove(kategori);

        kategori.setAnaKategori(this);
        getAltKategoriler().add(kategori);
    }

    public void removeKategori(IKategorilendirilebilir kategori) {
        if (kategori != null) {
            getAltKategoriler().remove(kategori);
            kategori.setAnaKategori(null);
        }
    }

    public Set getAltKategoriler() {
        return altKategoriler;
    }

    public IKategorilendirilebilir getAnaKategori() {
        return anaKategori;
    }

    public void setAltKategoriler(Set altKategoriler) {
        this.altKategoriler = altKategoriler;
    }

    public void setAnaKategori(IKategorilendirilebilir anaKategori) {
        this.anaKategori = anaKategori;
    }

    public boolean hasInChildren(IKategorilendirilebilir kategori) {
        if (getAltKategoriler() == null)
            return false;
        Iterator it = getAltKategoriler().iterator();
        while (it.hasNext()) {
            IKategorilendirilebilir altKategori = (IKategorilendirilebilir) it
                    .next();
            if (altKategori.equals(kategori))
                return true;
            else if (altKategori.hasInChildren(kategori))
                return true;
        }
        return false;
    }

    public IKategorilendirilebilir getChildByNodeValue(String tanim) {
        if (getAltKategoriler() == null)
            return null;
        Iterator it = getAltKategoriler().iterator();
        while (it.hasNext()) {
            IKategorilendirilebilir altKategori = (IKategorilendirilebilir) it
                    .next();
            if (altKategori.getNodeValue().trim().equals(tanim.trim()))
                return altKategori;
            else {
                IKategorilendirilebilir returnVal = altKategori
                        .getChildByNodeValue(tanim);
                if (returnVal != null)
                    return returnVal;
            }
        }
        return null;
    }

    public int getIndexOfChild(IKategorilendirilebilir child) {
        Object[] altKategoriler = getAltKategoriler().toArray();

        for (int i = 0; i < altKategoriler.length; i++) {
            IKategorilendirilebilir temp = (IKategorilendirilebilir) altKategoriler[i];
            if (temp.equals(child))
                return i;
        }
        return -1;
    }

    public Set getZimmetliMalzemeler() {
        return zimmetliMalzemeler;
    }
    
    

    public void setZimmetliMalzemeler(Set zimmetliMalzemeler) {
        this.zimmetliMalzemeler = zimmetliMalzemeler;
    }

    public void addDemirbasMalzeme(IDemirbasMalzeme demirbas) {
        if (demirbas == null)
            throw new IllegalStateException("Boþ Demirbaþ Eklenemez!");
        if (demirbas.getZimmetSahibi() != null)
            demirbas.getZimmetSahibi().getZimmetliMalzemeler().remove(demirbas);

        demirbas.setZimmetSahibi(this);
        getZimmetliMalzemeler().add(demirbas);
    }

    public void removeDemirbasMalzeme(IDemirbasMalzeme demirbas) {
        if (demirbas != null) {
            getZimmetliMalzemeler().remove(demirbas);
            demirbas.setZimmetSahibi(null);
        }
    }

    public boolean hasDemirbas(IDemirbasMalzeme demirbas) {
        if (getZimmetliMalzemeler() == null)
            return false;

        Iterator iter = getZimmetliMalzemeler().iterator();
        while (iter.hasNext()) {
            IDemirbasMalzeme zimmetliDemirbas = (IDemirbasMalzeme) iter.next();
            if (zimmetliDemirbas.equals(demirbas))
                return true;
        }
        return false;
    }

    public IDemirbasMalzeme getZimmetliDemirbasByNodeValue(String nodeValue) {
        if (getZimmetliMalzemeler() == null)
            return null;

        Iterator iter = getZimmetliMalzemeler().iterator();
        while (iter.hasNext()) {
            IDemirbasMalzeme zimmetliDemirbas = (IDemirbasMalzeme) iter.next();
            if (zimmetliDemirbas.getMalzemeTanimi().getNodeValue().trim().equals(nodeValue.trim()))
                return zimmetliDemirbas;
        }
        return null;
    }

    public int hashCode() {
        String anaKategoriTanimi = null;
        if (getAnaKategori() != null)
            anaKategoriTanimi = getAnaKategori().getNodeValue();

        return new HashCodeBuilder(-11241015, -1392753393).append(getNodeValue())
                .append(anaKategoriTanimi).toHashCode();
    }

    public boolean equals(final Object other) {
	    IKategorilendirilebilir thisAnaKategori = null;
	    IKategorilendirilebilir castOtherAnaKategori = null;
	    String thisAnaKategoriPath = "";
	    String castOtherAnaKategoriPath = "";
        if (this == other)
            return true;
        if (!(other instanceof AbstractZimmetAlan))
            return false;
        AbstractZimmetAlan castOther = (AbstractZimmetAlan) other;
		
		//node deðeri AbstractZimmetAlana ait unique bir özellik olmayabileceðinden
		//equality kontrolü için  en üstteki root elemente kadar olan path kullanýlýyor
		
	    thisAnaKategori = getAnaKategori();	    
	    while(thisAnaKategori != null){
	        thisAnaKategoriPath =thisAnaKategoriPath + thisAnaKategori.getNodeValue();
	        thisAnaKategori= thisAnaKategori.getAnaKategori();
	    }

	    castOtherAnaKategori = castOther.getAnaKategori();
	    while(castOtherAnaKategori != null){
	        castOtherAnaKategoriPath =castOtherAnaKategoriPath + castOtherAnaKategori.getNodeValue();
	        castOtherAnaKategori= castOtherAnaKategori.getAnaKategori();
	    }
        return new EqualsBuilder().append(getNodeValue(), castOther.getNodeValue())
                .append(thisAnaKategoriPath, castOtherAnaKategoriPath)
                .isEquals();
    }

    public String toString() {
        return getNodeValue();
    }

    /**
     * @author Umit Akyol
     */
    public Object getMemento() {
        return new ZimmetAlanMemento(altKategoriler, zimmetliMalzemeler);
    }

    /**
     * @author Umit Akyol
     */
    public void setMemento(Object object) {
        if (object instanceof ZimmetAlanMemento) {
            ZimmetAlanMemento zimmetAlanMemento = (ZimmetAlanMemento) object;
            //set altkategoriler
            altKategoriler.clear();
            Iterator iterator = zimmetAlanMemento.altKategoriler.iterator();
            while(iterator.hasNext()){
                IKategorilendirilebilir altKategori = (IKategorilendirilebilir)iterator.next();
                addKategori(altKategori);
                iterator.remove();
            }
            //set zimmetli malzemeler
            zimmetliMalzemeler.clear();
            iterator = zimmetAlanMemento.zimmetliMalzemeler.iterator();
            while(iterator.hasNext()){
                IDemirbasMalzeme demirbas = (IDemirbasMalzeme)iterator.next();
                addDemirbasMalzeme(demirbas);
                iterator.remove();
            }
        }
    }

    /**
     * @author Umit Akyol
     */
    private class ZimmetAlanMemento {
        private Set altKategoriler = new TreeSet(new KategoriComparator());
        private Set zimmetliMalzemeler = new TreeSet(new DemirbasMalzemeComparator());

        public ZimmetAlanMemento(Set altKategoriler, Set malzemeler) {
            this.altKategoriler.addAll(altKategoriler);
            this.zimmetliMalzemeler.addAll(malzemeler);
        }
    }

    /**
     * @author Umit Akyol
     */
    public IKategorilendirilebilir getFirstLevelChildByNodeValue(String nodeValue) {
        if (getAltKategoriler() == null)
            return null;
        Iterator iterator = getAltKategoriler().iterator();
        while (iterator.hasNext()) {
            IKategorilendirilebilir altKategori = (IKategorilendirilebilir) iterator.next();
            if (altKategori.getNodeValue().trim().equals(getNodeValue().trim()))
                return altKategori;

        }
        return null;
    }
    
    /**
     * @author Umit Akyol
     */ 
    public void mergeKategori(IKategorilendirilebilir kategori,KategoriMergeStateManager stateManager){
        if(kategori == null) 
            throw new IllegalStateException("Boþ Kategori Eklenemez.");
               
        if(getNodeValue().equals(kategori.getNodeValue())){ //node deðerleri ayný birlestirilecek
          Iterator iterator = kategori.getAltKategoriler().iterator();
            while(iterator.hasNext()){
            	IKategorilendirilebilir altKategori = (IKategorilendirilebilir) iterator.next();
            	iterator.remove();
            	mergeKategori(altKategori,stateManager);
            }
        }
        else{
            IKategorilendirilebilir node = this.getFirstLevelChildByNodeValue(kategori.getNodeValue());
            if(node==null){
                //save memento due to make undo operations
                Object state = this.getMemento();
                stateManager.putState(this,state);
                addKategori(kategori);
            }else{
                node.mergeKategori(kategori,stateManager);
                
            }
        }
    }
        
    /**
     * @author Umit Akyol
    */
    public boolean isKopyasiAlinabilir(){
        if(getZimmetliMalzemeler().size()!=0)
            return false;
        else 
            return true;
    }
    /**
     * @author Füsun Çetin
    */
    public boolean isDeletable() {
    	Iterator iter=getAltKategoriler().iterator();
    	while(iter.hasNext()){
    	AbstractZimmetAlan zimmetAlan=(AbstractZimmetAlan)iter.next();
    		if(zimmetAlan.getZimmetliMalzemeler().size()!=0)return false;
    	}
    	return true;
    }
    
    public String getNodeValue() {
		return getTanim();
	}
    
    /**
     * @author fusun
     */
    
    public abstract Collection getAltKategorilerindekiZimmetliMalzemeler();
    
   
}