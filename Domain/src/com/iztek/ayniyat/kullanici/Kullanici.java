/*
 * Created on 04.Tem.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.kullanici;

import java.util.HashSet;
import java.util.Set;

import com.iztek.ayniyat.yerlesim.domain.AbstractZimmetAlan;
import com.iztek.ayniyat.yerlesim.domain.Ambar;


/**
 * @author Sevgi Uslu
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Kullanici {
    private Long id;
    private int version;
    private String userName;
    private AbstractZimmetAlan personel;
    private String password;
    private Set ambarlar = new HashSet();
    
	public Kullanici(){};
	
   	public Kullanici(String userName,String password){
        this.userName=userName;
        this.password=password;
    } 
    	
   	public Kullanici(AbstractZimmetAlan personel,String userName,String password){
   	   this.personel=personel;
       this.userName=userName;
       this.password=password;
   	} 

    public Long getId() {
        return id;
    } 
    
    public void setId(Long id) {
        this.id = id;
    }
    
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getUserName() {
	    return userName;
	}
	
	public void setUserName(String userName) {
	    this.userName = userName;
	}
	
    public AbstractZimmetAlan getPersonel() {
        return personel;
    }

    public void setPersonel(AbstractZimmetAlan personel) {
        this.personel = personel;
    }  

	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}

    public Set getAmbarlar() {
        return ambarlar;
    }
    
    public void setAmbarlar(Set ambarlar) {
        this.ambarlar = ambarlar;
    }
    
    public void addAmbar(Ambar ambar){
        ambarlar.add(ambar);
        ambar.getKullanicilar().add(this);
    }
}
