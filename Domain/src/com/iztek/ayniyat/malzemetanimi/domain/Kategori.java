package com.iztek.ayniyat.malzemetanimi.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;
import com.iztek.ayniyat.kategori.KategoriComparator;
import com.iztek.ayniyat.kategori.service.KategoriMergeStateManager;


/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Kategori implements IKategorilendirilebilir, Serializable {
    
    protected Long id;
    protected String tanim;
    protected String kod;
    protected Set altKategoriler =new TreeSet(new KategoriComparator());
    protected IKategorilendirilebilir anaKategori;
    protected int version;
        
    public Kategori() {
    	super();
    }
    
    public Kategori(String tanim,String kod) {
        this.tanim = tanim;
        this.kod=kod;
    }
    
    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return Returns the tanim.
     */
    public String getTanim() {
        return tanim;
    }
    /**
     * @param tanim The tanim to set.
     */
    public void setTanim(String tanim) {
        this.tanim = tanim;
    }
        
	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}
    
    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#addKategori(com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir)
     */
    public void addKategori(IKategorilendirilebilir kategori) {
        if(kategori == null) 
            throw new IllegalStateException("Boþ Kategori Eklenemez.");

        if(kategori.getAnaKategori() != null) 
                kategori.getAnaKategori().getAltKategoriler().remove(kategori);
       
        kategori.setAnaKategori(this);
        getAltKategoriler().add(kategori);    
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
    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#removeKategori(com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir)
     */
    public void removeKategori(IKategorilendirilebilir kategori) {
        if (kategori!=null){
        	getAltKategoriler().remove(kategori);
        	kategori.setAnaKategori(null);
        }
    }
    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#getAltKategoriler()
     */
    public Set getAltKategoriler() {
        return altKategoriler;
    }
    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#getAnaKategori()
     */
    public IKategorilendirilebilir getAnaKategori() {
       return anaKategori;
    }  

    /**
     * @param altKategoriler The altKategoriler to set.
     */
    public void setAltKategoriler(Set altKategoriler) {
        this.altKategoriler = altKategoriler;
    }
    /**
     * @param anaKategori The anaKategori to set.
     */
    public void setAnaKategori(IKategorilendirilebilir anaKategori) {
        this.anaKategori = anaKategori;
    }
    
    public String toString() {
        return getNodeValue();
    }
    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#hasInChildren(com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir)
     */
    public boolean hasInChildren(IKategorilendirilebilir kategori) {
        if(getAltKategoriler() == null) return false;
        Iterator it = getAltKategoriler().iterator();
        while(it.hasNext()) {
            IKategorilendirilebilir altKategori = (IKategorilendirilebilir)it.next();
            if(altKategori.equals(kategori)) return true;
            else if(altKategori.hasInChildren(kategori)) return true;
        }
        return false;
    }
    /* (non-Javadoc)
     * @see com.iztek.ayniyat.malzemetanimi.domain.IKategorilendirilebilir#getChildByTanim(java.lang.String)
     */
    public IKategorilendirilebilir getChildByNodeValue(String nodeValue) {
        if(getAltKategoriler() == null) return null;
        Iterator it = getAltKategoriler().iterator();
        while(it.hasNext()) {
            IKategorilendirilebilir altKategori = (IKategorilendirilebilir)it.next();
            if(altKategori.getNodeValue().trim().equals(nodeValue.trim())) return altKategori;
            else {
              IKategorilendirilebilir  returnVal = altKategori.getChildByNodeValue(nodeValue);
              if(returnVal != null) return returnVal;
            }
        }
        return null;
    }
    
    /**
     * @authorCagdas CIRIT
     */
	public int getIndexOfChild(IKategorilendirilebilir child) {
		Object[] altKategoriler = getAltKategoriler().toArray();
		
		for(int i = 0; i < altKategoriler.length; i++) {
			IKategorilendirilebilir temp = (IKategorilendirilebilir)altKategoriler[i];
			if(temp.equals(child)) return i;
		}
		return -1;
	}
	
    /**
     * @author Umit Akyol
     */
    public IKategorilendirilebilir getFirstLevelChildByNodeValue(String nodeValue) {
        if(getAltKategoriler() == null) return null;
        Iterator it = getAltKategoriler().iterator();
        while(it.hasNext()) {
            IKategorilendirilebilir altKategori = (IKategorilendirilebilir)it.next();
            if(altKategori.getNodeValue().trim().equals(nodeValue.trim())) 
                return altKategori;
            
        }
        return null;
    }
	/**
	 * @return Returns the version.
	 */
	private int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	private void setVersion(int version) {
		this.version = version;
	}
    
	/**
     * @author Cagdas CIRIT
    **/
    public int hashCode() {
        String anaKategoriTanimi = null;
        if (getAnaKategori()!=null)
            anaKategoriTanimi = getAnaKategori().getNodeValue();
        
        return new HashCodeBuilder(-11241015, -1392753393).append(getNodeValue() )
                .append(anaKategoriTanimi).toHashCode();
    }

    /**
     * @author Cagdas CIRIT
    **/
	public boolean equals(final Object other) {
	    IKategorilendirilebilir thisAnaKategori = null;
	    IKategorilendirilebilir castOtherAnaKategori = null;
	    String thisAnaKategoriPath = "";
	    String castOtherAnaKategoriPath = "";
		if (this == other)
			return true;
		if (!(other instanceof Kategori))
			return false;
		Kategori castOther = (Kategori) other;
		
		//node deðeri Kategoriye ait unique bir özellik olmayabileceðinden
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
		return new EqualsBuilder().append(getNodeValue(), castOther.getNodeValue()).append(
				thisAnaKategoriPath, castOtherAnaKategoriPath).isEquals();
	}

	/**
     * @author Umit Akyol
     */
	public Object copy() throws Exception {
		IKategorilendirilebilir newInstance = new Kategori(tanim,kod);
		Iterator iterator = getAltKategoriler().iterator();
		while(iterator.hasNext()){
			IKategorilendirilebilir altKategori = (IKategorilendirilebilir)iterator.next();
			newInstance.addKategori((IKategorilendirilebilir) altKategori.copy());
            
		}
		return newInstance;
	}
	/**
     * @author Umit Akyol
     */
	public void setMemento(Object object){
	    if (object instanceof KategoriMemento){
            KategoriMemento kategoriMemento = (KategoriMemento)object;
            altKategoriler.clear();
            Iterator iterator = kategoriMemento.state.iterator();
            while(iterator.hasNext()){
                IKategorilendirilebilir altKategori = (IKategorilendirilebilir)iterator.next();
                addKategori(altKategori);
                iterator.remove();
            }
            
        }
		 
	}
	/**
     * @author Umit Akyol
     */
	public Object getMemento(){
		return new KategoriMemento(altKategoriler);
	}
	/**
     * @author Umit Akyol
    */
	private class KategoriMemento{
		private Set state = new TreeSet(new KategoriComparator());
				
		public KategoriMemento(Set altKategoriler) {
			this.state.addAll(altKategoriler);
			
		}
	}
	/**
     * @author Umit Akyol
    */
    public boolean isKopyasiAlinabilir() {
        return true;
    }

    /* (non-Javadoc)
     * @see com.iztek.ayniyat.kategori.IKategorilendirilebilir#isDeletable()
     */
    public boolean isDeletable() {
     	Iterator iter=getAltKategoriler().iterator();
    	while(iter.hasNext()){
    		IKategorilendirilebilir altKategori=(IKategorilendirilebilir)iter.next();
    		if(!altKategori.isDeletable())return false;
    	}
    	return true;
    }

	public String getNodeValue() {
		return getKod()+"-"+getTanim();
	}
	
}
