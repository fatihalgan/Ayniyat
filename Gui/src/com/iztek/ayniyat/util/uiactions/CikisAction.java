package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.malzemehareketleri.controller.AnaController;

public class CikisAction implements Command {

	public void execute() {
		AnaController.getInstance()
		.malzemeHareketleriAnaPanelCardLayoutaPanelOneGetir("bosPanel");
	}

}
