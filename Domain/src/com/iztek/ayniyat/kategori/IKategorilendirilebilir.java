package com.iztek.ayniyat.kategori;

import java.util.Set;

import com.iztek.ayniyat.kategori.service.KategoriMergeStateManager;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IKategorilendirilebilir extends IKopyalanabilir,IKopyasiAlinabilir {  
    public void addKategori(IKategorilendirilebilir kategori);
    public void removeKategori(IKategorilendirilebilir kategori);
    public void  mergeKategori (IKategorilendirilebilir kategori,KategoriMergeStateManager stateManager);
    public Set getAltKategoriler();
    public String getTanim();
    public String getNodeValue(); // nodlarýn aðaçta temsil edildiði deðerleri döndürür
    								// yerleþim de taným,malzeme tanýmýnda kod
    public IKategorilendirilebilir getAnaKategori();
    public void setAnaKategori(IKategorilendirilebilir kategori);
    public boolean hasInChildren(IKategorilendirilebilir kategori);
    public IKategorilendirilebilir getChildByNodeValue(String nodeValue);
    public IKategorilendirilebilir getFirstLevelChildByNodeValue(String nodeValue);
    public int getIndexOfChild(IKategorilendirilebilir child);
    public Long getId();
    public Object getMemento();
    public void setMemento(Object object);
    public boolean isKopyasiAlinabilir();
    public boolean isDeletable();
}
