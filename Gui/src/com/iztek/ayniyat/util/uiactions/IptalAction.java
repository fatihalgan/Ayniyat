package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;

public class IptalAction implements Command{

	public void execute() {
		AnaController.getInstance().getCurrentTanimlarController().panelOneGetir("bos");
	}
}
