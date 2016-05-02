package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.panel.ISorgularPanel;

public class PrintAction implements Command {
private ISorgularPanel activePanel;
	
	public PrintAction(ISorgularPanel panel){
		this.activePanel=panel;
	}
	public void execute() {
	activePanel.printAction(); 
	}

}
