package com.iztek.ayniyat.event;

import java.util.EventListener;

public interface IKategoriEventListener extends EventListener{
	public void addKategori(KategoriEvent kategoriEvent);
	public void removeKategori(KategoriRemoveEvent kategoriRemoveEvent);
	public void changeKategori(KategoriEvent kategoriEvent);
	public void copyKategori(KategoriEvent kategoriEvent);
	public void cutKategori(KategoriCutEvent kategoriCutEvent);
}
