package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.panel.ITanimlarPanel;

public class AraAction implements Command {
/**
 * @author fusun
 */
	private ITanimlarPanel activePanel;
	
	public AraAction(ITanimlarPanel panel){
		this.activePanel=panel;
	}
	
	public void execute() {
		activePanel.araAction();
	}
}
