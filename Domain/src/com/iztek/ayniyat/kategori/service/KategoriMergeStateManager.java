package com.iztek.ayniyat.kategori.service;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import com.iztek.ayniyat.kategori.IKategorilendirilebilir;

/**
 * @author Umit Akyol
 *
 */
public class KategoriMergeStateManager {
    private Hashtable states = new Hashtable();
    
    public void putState(Object key ,Object state){
        if(!states.containsKey(key)){
            states.put(key,state);
        }
    }
    public Object getState(Object key){
        return states.get(key);
    }
    public void undo(IKategorilendirilebilir kategori){
        Object state = getState(kategori);
        if(state != null){
            kategori.setMemento(state);
            Collection altKategoriler = kategori.getAltKategoriler();
            Iterator iterator = altKategoriler.iterator();
            while(iterator.hasNext()){
                undo((IKategorilendirilebilir) iterator.next());
            }
        }
        
    }
    public void clearStates(){
        states.clear();
    }
}
