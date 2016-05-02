package com.iztek.ayniyat.event;

import java.util.ArrayList;
import com.iztek.ayniyat.event.IKategoriEventListener;
import com.iztek.ayniyat.event.KategoriCutEvent;
import com.iztek.ayniyat.event.KategoriEvent;
import com.iztek.ayniyat.event.KategoriRemoveEvent;
/**
 * @author Cagdas CIRIT
 */
public class KategoriEventServiceImpl {
    private ArrayList listenerList =  null;
    
    private static KategoriEventServiceImpl tanimlarService = null;
    private KategoriEventServiceImpl() {
    }
    
  public static KategoriEventServiceImpl getTanimlarEventService(){
    	 if(tanimlarService==null)
             tanimlarService = new KategoriEventServiceImpl();
      
         return tanimlarService;
    }

    public synchronized void addKategoriEventListener(IKategoriEventListener listener) {
        if (listenerList == null ) {
            listenerList = new ArrayList(1);//initially there are 1 listeners
        }
        listenerList.add(listener);
    }
    
    public synchronized void removeKategoriEventListener(IKategoriEventListener listener) {
        listenerList.remove(listener);
    }
    
    public void removeAllKategoriEventListeners(){
        if (listenerList == null) 
        	return;
        
        Object[] listeners = listenerList.toArray();
        
        for (int i = 0 ; i<listeners.length; i++) {
            listeners[i] = null;
        }
    }
    
    public void fireKategoriAddEvent(KategoriEvent event) {
        if (listenerList == null) 
        	return;
        
        Object[] listeners = listenerList.toArray();
        
        for (int i = 0 ; i<listeners.length; i++) {
            ((IKategoriEventListener)listeners[i]).addKategori(event);
        }
    }
    
    public void fireKategoriRemoveEvent(KategoriRemoveEvent event) {
        if (listenerList == null) 
        	return;
        
        Object[] listeners = listenerList.toArray();
        
        for (int i = 0 ; i<listeners.length; i++) {
            ((IKategoriEventListener)listeners[i]).removeKategori(event);
        }
    }
    
    public void fireKategoriChangeEvent(KategoriEvent event) {
        if (listenerList == null) 
        	return;
        
        Object[] listeners = listenerList.toArray();
        
        for (int i = 0 ; i<listeners.length; i++) {
            ((IKategoriEventListener)listeners[i]).changeKategori(event);
        }
    }
    
    public void fireKategoriCopyEvent(KategoriEvent event) {
        if (listenerList == null) 
        	return;
        
        Object[] listeners = listenerList.toArray();
        
        for (int i = 0 ; i<listeners.length; i++) {
            ((IKategoriEventListener)listeners[i]).copyKategori(event);
        }
    }
    
    public void fireKategoriCutEvent(KategoriCutEvent event) {
        if (listenerList == null) 
        	return;
        
        Object[] listeners = listenerList.toArray();
        
        for (int i = 0 ; i<listeners.length; i++) {
            ((IKategoriEventListener)listeners[i]).cutKategori(event);
        }
    }
    
}

