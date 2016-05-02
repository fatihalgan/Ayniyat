/*
 * Created on 08.Tem.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.ayniyat.kullanici;

import java.util.Collection;

/**
 * @author Sevgi Uslu
 *
 */
public interface IKullaniciDAO {
    public Kullanici getKullaniciByUserNameAndPassword(String userName,String password);
    public Collection getMalzemelerVisibleToKullanici(Kullanici kullanici);
}
