package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.panel.ISorgularPanel;

public class InceleAction implements Command {

	private ISorgularPanel activePanel;
	
	public InceleAction(ISorgularPanel panel){
		this.activePanel=panel;
	}
	public void execute() {
	activePanel.inceleAction();
	}

}
